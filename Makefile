include .env
export

.PHONY: run
run: 
	./mvnw clean install
	java -jar target/dumbey.jar