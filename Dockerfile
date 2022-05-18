FROM tomcat:8.5-jdk8-temurin

ADD ./edb-jdbc17.jar /usr/local/tomcat/lib/
ADD ./udb.war /usr/local/tomcat/webapps/
RUN chmod -R 777 /usr/local/tomcat/webapps
RUN mkdir -p /usr/local/tomcat/conf/Catalina/localhost
ADD ./udb.xml /usr/local/tomcat/conf/Catalina/localhost/
ADD ./udb.properties /usr/local/tomcat/conf/

EXPOSE 8080

CMD ["catalina.sh", "run"]
