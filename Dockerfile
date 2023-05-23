FROM jboss/wildfly:16.0.0.Final

ENV JBOSS_MODE standalone

RUN /opt/jboss/wildfly/bin/add-user.sh -u root -p Admin123! --silent

ADD target/web-app.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080 9990