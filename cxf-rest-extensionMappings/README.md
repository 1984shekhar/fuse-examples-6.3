rest: Demonstrates how to create a REST Web service

jaxrs:extensionMappings example.

Build and Deploy the Quickstart
-------------------------------

1. Change your working directory to `rest` directory.
* Run `mvn clean install` to build the quickstart.
* Start JBoss Fuse 6 by running bin/fuse (on Linux) or bin\fuse.bat (on Windows).
* In the JBoss Fuse console, enter the following command:

        osgi:install -s mvn:org.jboss.quickstarts.fuse/cxf-rest/6.3.0.redhat-187

* Fuse should give you an id when the bundle is deployed
* You can check that everything is ok by issuing  the command:

        osgi:list
   your bundle should be present at the end of the list


Use the bundle
--------------
Place any pdf with title renamed to output.pdf in user's home directory like /home/cpandey if user is cpandey. This example should work in linux environment.

http://localhost:9000/cxf/CxfRsRouterTest/rest/.pdf

