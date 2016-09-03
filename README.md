# 5-EJBDemo
Maven +  EJB3 + MDB (Message Driven Bean) + JMS + JPA + JSF + JAX-WS + REST + CDI (Contexts and Dependency Injection for Java)-API + WildFly + MySQL 
  
 - Mettre en oeuvre la spécification JAAS (Java Authentication and Authorisation Service) pour sécuriser l’application.
 
 - L'exploitation des ejb se fait via la classe "PersonneManagedBean" qui possède les annotations de configuration.
 
 - Création d'un MDB (Message Driven Bean) de JMS via  la classe "PersonneMessageListener", qui implémente l'interface 
   "MessageListener", dans laquelle on déclare la "javax.jms.Queue": "java:/jms/EcommerceQueue" déjà créer sous la console d'admin de WildFly. 
	 La Queue de destination: "EcommerceQueue" va recevoir un message à chaque fois on ajoute une personne dans la BD.
	 Pour visualiser "EcommerceQueue" il faut accéder via la console d'admin http://localhost:9990/ --> Runtime --> Standalone Server -->  Subsystems
	 Messaging - ActiveMQ (view).
	   
 - WebService: on a créé la classe "PersonneWS" qui possède  l'implémentaion de ce WS (sauvegarder une personne).
	  Le déploiement du projet génère le fichier de config. "PersonneWSService.wsdl" qui contient toutes les informations, et méthodes .. 
	  pour consomer.
	  
 - Pour consommer ce WS on a utilisé dans un premier temps l'outil de JDK "wsimport" avec la ligne de commande suivante :
	 wsimport -keep -verbose /opt/wildfly-10.0.0.Final/standalone/data/wsdl/ejbsample.war/PersonneWSService.wsdl, pour génèrer des classes Java de ce WS vers un rép. dans home.
   Et dans deuxième temps on a créé un projet client nommé "consumerwsclient" qui va consommer ce service, pour cela il faut copier le rép. générer (contenant les  classes du WS) 	         
   dans  src/main/java. Dans ce projet il y a une méthode main de la classe "Runtime" qui sert à utiliser ce service (pour persister une personne dans la bd).
