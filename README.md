# CloudProject


# Lancer la simulation
(Zookeeper -> Kafka -> Générateur de données)

# Lancer le consommateur kafka (pas encore intégré à Streaming Spark)
Dans le dossier src/ :

javac -cp .:kafka-clients-0.10.0.0.jar:slf4j-api-1.7.21.jar:slf4j-simple-1.7.25.jar KafkaConsumerTest.java <br />

java -cp .:kafka-clients-0.10.0.0.jar:slf4j-api-1.7.21.jar:slf4j-simple-1.7.25.jar KafkaConsumerTest
