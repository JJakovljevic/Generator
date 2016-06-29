
<#function checkType testSting>
	<#if testSting == "EDate" || testSting == "EBoolean">
  	<#return testSting?substring(1)>
  	<#else>
  	<#return testSting>
  	</#if>
</#function>
package beans;

import javax.persistence.*;

@Entity
@Table(name = "${class.name}")
${class.visibility} class ${class.name} {

	<#list class.properties as property>
	<#if property.dbProperty?exists>
		<#if property.dbProperty.id>
	@Id
    @GeneratedValue
		</#if>
	@Column(name = "${property.name}", nullable = false)
	private ${property.name} ${checkType(property.type)};
	
	</#if>
	</#list>
	
	public ${class.name}() {
        super();
    }
    <#list class.properties as property>
	<#if property.dbProperty?exists>
	${property.visibility} ${checkType(property.type)} get${property.name?cap_first}(){
		return ${property.name};
	}
	
	${property.visibility} void set${property.name?cap_first}(${checkType(property.type)} ${property.name}){
		this.${property.name} = ${property.name};
	}
	
	</#if>
	</#list>
}