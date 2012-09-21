repositories.remote << 'http://repo1.maven.org/maven2'

#dependencies
INPROCTESTER_CORE = transitive('com.thoughtworks.inproctester:inproctester-core:jar:1.0.14')
INPROCTESTER_JETTY = transitive('com.thoughtworks.inproctester:inproctester-jetty:jar:1.0.14')
INPROCTESTER_HTMLUNIT = transitive('com.thoughtworks.inproctester:inproctester-htmlunit:jar:1.0.14')
INPROCTESTER_WEBDRIVER = transitive('com.thoughtworks.inproctester:inproctester-webdriver:jar:1.0.14')
SELENIUM_HTMLUNIT = transitive('org.seleniumhq.selenium:selenium-htmlunit-driver:jar:2.21.0')
SELENIUM = transitive('org.seleniumhq.selenium:selenium-java:jar:2.22.0')
JETTY_SERVLET = transitive('org.eclipse.jetty:test-jetty-servlet:jar:8.1.3.v20120416')
SERVLET_API = transitive('javax.servlet:servlet-api:jar:2.5')
JUNIT = transitive('junit:junit:jar:4.8.2')
MOCKITO= transitive('org.mockito:mockito-all:jar:1.9.0')
JETTY_WEBAPP = transitive('org.eclipse.jetty:jetty-webapp:jar:7.4.2.v20110526')
STRINGTEMPLATE = transitive('org.antlr:stringtemplate:jar:4.0.2')
HIBERNATE_CORE = transitive('org.hibernate:hibernate-core:jar:4.1.2')
HIBERNATE_C3P0 = transitive('org.hibernate:hibernate-c3p0:jar:4.1.2')
LOG4J = transitive('log4j:log4j:jar:1.2.16')
RHINO = 'rhino:js:jar:1.7R2'

define 'UniversityRegistration' do

	project.version = '0.1.0'

	define 'domain' do
		test.with MOCKITO
		package :jar
	end

	define 'infrastructure' do
		compile.with projects('domain')
		test.with MOCKITO
		package :jar
	end

	define 'web' do
		compile.with projects('infrastructure','domain'), SERVLET_API, JETTY_WEBAPP, STRINGTEMPLATE, HIBERNATE_CORE, HIBERNATE_C3P0 
		test.with MOCKITO
		package(:jar)
		package :war
	end

	functional_layout = Layout.new
	functional_layout[:source, :test, :java] = _('functional/src/main/java')
	functional_layout[:source, :test, :resources] = _('functional/resources')
	define 'functional', :layout => functional_layout do
		compile.with JETTY_SERVLET, INPROCTESTER_CORE, INPROCTESTER_JETTY, SELENIUM, SELENIUM_HTMLUNIT, INPROCTESTER_HTMLUNIT, INPROCTESTER_WEBDRIVER
		test.with projects('web', 'domain', 'infrastructure'), STRINGTEMPLATE, HIBERNATE_CORE, compile.dependencies + [compile.target.to_s]
	end

end

