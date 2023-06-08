FROM websphere-liberty:full-java17-openj9

COPY --chown=1001:0 liberty/server.xml /config/server.xml
COPY --chown=1001:0 target/FakeOLM-1.0.war /config/apps/FakeOLM-1.0.war

RUN  echo "<server></server>" > /config/configDropins/defaults/keystore.xml
