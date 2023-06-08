FROM cp.icr.io/cp/appc/ace-server-prod@sha256:c41154c17a30bbbb6e1e4593c965f49ef3c86260e71143b8f33a6fbca277a3b9

USER root
WORKDIR /opt/ibm

COPY BARfiles/Banking.bar /home/aceuser/initial-config/Banking.bar
COPY server.conf.yaml     /home/aceuser/initial-config/serverconf/server.conf.yaml
RUN  source /opt/ibm/ace-12/server/bin/mqsiprofile && \
     /opt/ibm/ace-12/server/bin/mqsibar -a /home/aceuser/initial-config/Banking.bar -c -w /home/aceuser/ace-server

