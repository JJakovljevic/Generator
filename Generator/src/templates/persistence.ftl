<?xml version="1.0" encoding="UTF-8"?>
<persistence version="1.0" xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd">
	<persistence-unit name="${persistenceName?lower_case}" transaction-type="RESOURCE_LOCAL">
		<provider>org.apache.openjpa.persistence.PersistenceProviderImpl</provider>
		<#list classes as class>
		<class>bean.${class.name}</class>
		</#list>
		<properties>
			<property name="openjpa.ConnectionDriverName" value="oracle.jdbc.driver.OracleDriver" />
			<property name="openjpa.ConnectionURL" value="jdbc:oracle:thin:@127.0.0.1:1521:xe"/>
			<property name="openjpa.ConnectionUserName" value="mbrs" />
			<property name="openjpa.ConnectionPassword" value="ftn"/>
			<property name="openjpa.jdbc.SynchronizeMappings" value="buildSchema(ForeignKeys=true)" />
			<property name="openjpa.jdbc.DBDictionary" value="BatchLimit=100" />
			<property name="openjpa.Log" value="DefaultLevel=WARN, Tool=INFO" />
			<property name="openjpa.RuntimeUnenhancedClasses" value="unsupported"/>
		</properties>
	</persistence-unit>
</persistence>
