-Check an available algorithms running sh(bat) jboss-fuse/extras/jasypt/bin/listAlgorithms. The output should looks like:
DIGEST ALGORITHMS:   [MD2, MD5, SHA, SHA-224, SHA-256, SHA-384, SHA-512]
PBE ALGORITHMS:      [PBEWITHHMACSHA1ANDAES_128, PBEWITHHMACSHA1ANDAES_256, PBEWITHHMACSHA224ANDAES_128, PBEWITHHMACSHA224ANDAES_256, PBEWITHHMACSHA256ANDAES_128, PBEWITHHMACSHA256ANDAES_256, PBEWITHHMACSHA384ANDAES_128, PBEWITHHMACSHA384ANDAES_256, PBEWITHHMACSHA512ANDAES_128, PBEWITHHMACSHA512ANDAES_256, PBEWITHMD5ANDDES, PBEWITHMD5ANDTRIPLEDES, PBEWITHSHA1ANDDESEDE, PBEWITHSHA1ANDRC2_128, PBEWITHSHA1ANDRC2_40, PBEWITHSHA1ANDRC4_128, PBEWITHSHA1ANDRC4_40]

- In the Fuse console try to run
fabric:crypt-algorithm-set PBEWITHHMACSHA512ANDAES_256

- Install attached profile into Fabric using command.
fabric:profile-import file:/path/to/encryption.test.profile.zip

- Create and start new container with default profile
Open PIDs in profile to edit and replace encrypted data with yours. To encrypt string run this command in the Fuse console

JBossFuse:karaf@root> fabric:encrypt-message admin
Encrypting message admin
 Using algorithm PBEWITHHMACSHA512ANDAES_256 and password admin
 Result: 9+0AlsKl7cMOwqV35V80iRnsiA5RYeJy14J8VDiTuD4=

JBossFuse:karaf@root> profile-edit --pid ru.sbi.soi/test.pwd='${crypt:9+0AlsKl7cMOwqV35V80iRnsiA5RYeJy14J8VDiTuD4=}' encryption.test 
Setting value:${crypt:9+0AlsKl7cMOwqV35V80iRnsiA5RYeJy14J8VDiTuD4=} key:test.pwd on pid:ru.sbi.soi and profile:encryption.test version:1.0


JBossFuse:karaf@root> profile-edit --pid ru.sbi.soi/broker.password='${crypt:9+0AlsKl7cMOwqV35V80iRnsiA5RYeJy14J8VDiTuD4=}' encryption.test 
Setting value:${crypt:9+0AlsKl7cMOwqV35V80iRnsiA5RYeJy14J8VDiTuD4=} key:broker.password on pid:ru.sbi.soi and profile:encryption.test version:1.0


- Attach profile to the newly created container
- Initially it should be ok - messages could be sent to queue and consumed from. Container logs looks fine - 
2018-01-16 19:07:26,941 | INFO  | #1 - timer://foo | myRoute                          | 259 - org.apache.camel.camel-core - 2.17.0.redhat-630310 | Received payload: 3: crypt:9+0AlsKl7cMOwqV35V80iRnsiA5RYeJy14J8VDiTuD4=
2018-01-16 19:07:26,942 | INFO  | #1 - timer://foo | myRoute                          | 259 - org.apache.camel.camel-core - 2.17.0.redhat-630310 | Received DB payload: DB pass: crypt:BUH+QEq0XrxsKBt81E58rCAUbXSluD5NRkHWy3lNADM=

