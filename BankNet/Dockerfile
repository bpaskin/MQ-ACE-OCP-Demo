FROM cp.icr.io/cp/appc/ace-server-prod@sha256:77bc219596615b3b9e531867c20a008233e87e9ccf9da2148512501c860f0070

USER root
WORKDIR /opt/ibm

COPY BARfiles/Banking.bar /home/aceuser/initial-config/Banking.bar
COPY server.conf.yaml     /home/aceuser/initial-config/serverconf/server.conf.yaml
RUN  source /opt/ibm/ace-12/server/bin/mqsiprofile && \
     /opt/ibm/ace-12/server/bin/mqsibar -a /home/aceuser/initial-config/Banking.bar -c -w /home/aceuser/ace-server

