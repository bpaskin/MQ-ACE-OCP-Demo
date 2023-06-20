FROM websphere-liberty:full-java17-openj9

COPY --chown=1001:0 liberty/server.xml /config/
COPY --chown=1001:0 liberty/lib/wmq.jmsra.rar /config/lib/wmq.jmsra.rar
COPY --chown=1001:0 target/BankClient-1.0.war /config/apps/BankClient-1.0.war

