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
MYSQL_CONNECTOR = transitive('mysql:mysql-connector-java:jar:5.1.21')
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
		compile.with projects('domain'), MYSQL_CONNECTOR
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
		compile.with JETTY_SERVLET, INPROCTESTER_CORE, INPROCTESTER_JETTY, SELENIUM, SELENIUM_HTMLUNIT, INPROCTESTER_HTMLUNIT, INPROCTESTER_WEBDRIVER, MYSQL_CONNECTOR
		test.with projects('web', 'domain', 'infrastructure'), STRINGTEMPLATE, HIBERNATE_CORE, compile.dependencies + [compile.target.to_s]
		task :test => ['UniversityRegistration:database:dbdeploy']
	end

	define 'database' do
	    project.no_iml

        db_driver = "com.mysql.jdbc.Driver"
        db_username = "unireg"
        db_password = "unireg"
        db_url = "jdbc:mysql://localhost/UniversityRegistration"
        db_root_url = "jdbc:mysql://localhost"
        db_root_user = "root"
        db_root_password = "password"

        task :dbdeploy do
          Buildr.ant "dbdeploy" do |ant|
            define_ojdbc_path ant
            define_db_deploy_task ant

            ant.dbdeploy(
                :driver => "#{db_driver}",
                :url => "#{db_url}",
                :userid => "#{db_username}",
                :password => "#{db_password}",
                :dir => "database/deltas")
          end
        end

        task :drop_database do
          Buildr.ant "dropdatabase" do |ant|
            define_ojdbc_path ant
            ant.sql(:driver => "#{db_driver}", :url => "#{db_root_url}",
                    :userid => "#{db_root_user}", :password => "#{db_root_password}",
                    :onerror => "continue", :classpathref => "ojdbc.classpath",
                    :src => "database/scripts/dropSchema.sql")

          end
        end

        task :create_database do
          Buildr.ant "createdatabase" do |ant|
            define_ojdbc_path ant
            ant.sql(
                :driver => "#{db_driver}", :url => "#{db_root_url}",
                :userid => "#{db_root_user}", :password => "#{db_root_password}",
                :classpathref => "ojdbc.classpath") { |ant|
              ant.fileset(:file => "database/scripts/createSchema.sql")
            }

          end
        end

        task :create_changelog_table do
          Buildr.ant "createchangelog" do |ant|
            define_ojdbc_path ant
            ant.sql(
                :driver => "#{db_driver}", :url => "#{db_url}",
                :userid => "#{db_username}", :password => "#{db_password}",
                :classpathref => "ojdbc.classpath") { |ant|
              ant.fileset(:file => "database/scripts/createSchemaVersionTable.mysql.sql")
            }
          end
        end

        task :db_clean => [:drop_database, :create_database, :create_changelog_table]
        task :clean_deploy => [:db_clean, :dbdeploy]

        def define_db_deploy_task(ant)
          ant.taskdef(:name => "dbdeploy", :classname => "com.dbdeploy.AntTarget") { |ant|
            ant.classpath { |ant|
              ant.fileset(:dir => "tools/dbdeploy-3.0M3") { |ant|
                ant.include(:name => "dbdeploy-ant-*.jar")
              }
              ant.path(:refid => "ojdbc.classpath")
            }
          }
        end

        def define_ojdbc_path(ant)
          ant.path(:id => "ojdbc.classpath") { |ant|
            ant.fileset(:dir => "database/driver") { |ant|
              ant.include(:name => "*.jar")
            }
          }
        end
	end

end

