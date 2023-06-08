FROM websphere-liberty:full-java17-openj9

COPY --chown=1001:0 liberty/server.xml liberty/bootstrap.properties /config/
COPY --chown=1001:0 liberty/lib/wmq.jmsra.rar /config/lib/wmq.jmsra.rar
COPY --chown=1001:0 target/BankAuth-1.0.war /config/apps/BankAuth-1.0.war

