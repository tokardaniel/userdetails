FROM maven:3.8.5-openjdk-17

WORKDIR /app

ADD . .

RUN mvn clean install

CMD ["./entrypoint.sh"]
