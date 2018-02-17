osgi:install -s mvn:com.test/kafkaTest/1.0


in kafka server's config/server.properties set below property, so that topics are not created automatically.

auto.create.topics.enable=false

Than run zookeeper server and kafka server:
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
./bin/kafka-server-start.sh ./config/server.properties

