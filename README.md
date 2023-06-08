# Demo with MQ, ACE, Liberty on OCP

![flow image](images/Liberty-MQ-ACE.png)

1. Create namespace (demo), catalog source, and install the operators
```
oc apply -f install/setup.yaml
```
2. Add your [entitlement key](https://myibm.ibm.com/products-services/containerlibrary):
```
oc create secret docker-registry ibm-entitlement-key --docker-server=cp.icr.io --docker-username=cp --docker-password={entitlement key}
```
3. Update the `install/create-instances.yam`l with the correct storage classes for MQ and ACE
4. Create the the Platform Navigator, BuildConfigs, and instances of the Liberty, MQ, and ACE
```
oc apply -f install/create-instances.yaml
```
---
To get the URL of the Bank Client application:
``` 
oc get route bank-client-app -n demo --output jsonpath={.spec.host}
```
This then can be used on this comamnd to test the flow.
```
curl -k -v -H "Content-Type: application/json" -H "Accept: application/json" -d '{"customerName":"Brian","swiftCode":"ABCDEFHSS","IBAN":"12345","currency":"EUR","amount":123456.00}' -X POST https://{url}/BankClient/services/withdrawal
```
