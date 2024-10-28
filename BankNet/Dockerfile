FROM cp.icr.io/cp/appc/ace-server-prod@sha256:694f8dc75fc40ecdb63b028d1a31158f07a821c9c14c07016b9d9e49edfa5595
# ACE 12.0.12.3-r1 

USER root
WORKDIR /opt/ibm

COPY BARfiles/Banking.bar /home/aceuser/initial-config/Banking.bar
COPY server.conf.yaml     /home/aceuser/initial-config/serverconf/server.conf.yaml
RUN  source /opt/ibm/ace-12/server/bin/mqsiprofile && \
     /opt/ibm/ace-12/server/bin/mqsibar -a /home/aceuser/initial-config/Banking.bar -c -w /home/aceuser/ace-server

