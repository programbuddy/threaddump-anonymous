FROM maven:3.3.9-jdk-8

MAINTAINER programbuddy <info@programbuddy.com>

RUN wget https://github.com/programbuddy/stacktrace-anonymous/archive/master.zip && \
    unzip master.zip && \
    rm master.zip && \
    cd stacktrace-anonymous-master && \
    mvn clean install

WORKDIR /stacktrace-anonymous-master/target

CMD ["java", "-jar", "stacktrace.anonymous-1.0-SNAPSHOT.jar", "out_anonymous_map.txt"]
