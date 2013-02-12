Bookish
=======

Book management and content platform

This application is currently undergoing initial development and is therefore highly unstable and experimental.

* Run the following command before launching the webapp:
	mvn exec:java -Dexec.mainClass="org.hsqldb.Server" -Dexec.args="-database.0 file:target/data/tutorial"
* Use mvn eclipse:eclipse to generate an eclipse project
* Optionally, create a local eclipse tomcat server instance to deploy to.
