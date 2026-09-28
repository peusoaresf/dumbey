include .env
export

.PHONY: clean
clean:
	./mvnw clean

.PHONY: run
run:
	./mvnw install
	java -jar target/dumbey.jar
