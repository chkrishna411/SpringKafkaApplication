# SpringKafkaApplication
Spring Boot application for kafka integration

# How to Run
Start the ApplicationMain which will internally kick start the Tomcat server.

# Kafka Brokers
Kafka brokers and zookeeper are assumed that they have started in local, and probably can update later to consumer it through application properties file.

# Producer
Producer is configured through spring-kafka module and exposed through Rest services.
call http://localhost:8080/send/message to send a post message to Kafka Brokers.

# Consumer
Consumers are started with concurrent message processors, which are at 5 threads now, 
It can be updated later to a configure property to specify the number of threads. 




