Stream: It is simply a stream of records where it is not known when it will end. Simplest example is 'tail -f <logfile>'.

Camel Project for Blueprint 
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache Karaf.
You can run the following command from its shell:

	features:install camel-stream

    osgi:install -s mvn:com.mycompany/camel-stream-example/1.0.0-SNAPSHOT

For more help see the Apache Camel documentation

    http://camel.apache.org/
