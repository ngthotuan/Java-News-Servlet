FROM tomcat:9-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps
COPY target/JavaWeb-1.0.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080