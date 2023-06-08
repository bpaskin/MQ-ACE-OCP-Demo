# Demo with MQ, ACE, Liberty on OCP

![flow image](images/Liberty-MQ-ACE.png)

```
curl -k -v -H "Content-Type: application/json" -H "Accept: application/json" -d '{"customerName":"Brian","swiftCode":"ABCDEFHSS","IBAN":"12345","currency":"USD","amount":123456.00}' -X POST https://bank-client-app-demo.apps.ocp-110000ejcn-xtmn.cloud.techzone.ibm.com/BankClient/services/withdrawal
```
