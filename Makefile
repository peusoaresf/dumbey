include .env
export

.PHONY: run
run: 
	./mvnw clean install
	java -jar target/java-dumbey-agent.jar 