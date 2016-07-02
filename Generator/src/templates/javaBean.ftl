
<#function checkType testSting>
	<#if testSting == "EDate" || testSting == "EBoolean">
  	<#return testSting?substring(1)>
  	<#else>
  	<#return testSting>
  	</#if>
</#function>

<#function mandatory value>
	<#if value>
  	<#return false>
  	<#else>
  	<#return true>
  	</#if>
</#function>
package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

<#if class.abstract>
@MappedSuperclass
<#else>
@Entity
@Table(name = "${class.name}")
</#if>
${class.visibility} <#if class.abstract>abstract </#if>class ${class.name} <#if class.parent?exists>extends ${class.parent.name}</#if> implements Serializable{

	<#list class.properties as property>
	<#if property.dbProperty?exists>
		<#if property.dbProperty.id>
	@Id
    @GeneratedValue
		</#if>
	@Column(name = "${property.name}"<#if property.dbProperty?exists><#if !property.dbProperty.id>, unique = ${property.dbProperty.unique?c}, nullable = ${mandatory(property.dbProperty.mandatory)?c}</#if></#if>)
	private ${checkType(property.type)} ${property.name};
	<#else>
    	<#if property.upper == 100>
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "${class.name?lower_case}")
    private Set<${property.type}> ${property.name};
    	<#else>
    @ManyToOne
    private ${property.type} ${property.name};
   		 </#if>
	</#if>
	
	</#list>
	public ${class.name}() {
        super();
    }
    <#list class.properties as property>
	
	<#if property.upper == 100>
	public Set<${property.type}> get${property.name?cap_first}(){
		return ${property.name};
	}
	
	public void set${property.name?cap_first}(Set<${property.type}> ${property.name}){
		this.${property.name} = ${property.name};
	}
	<#else>
	public ${checkType(property.type)} get${property.name?cap_first}(){
		return ${property.name};
	}
	
	public void set${property.name?cap_first}(${checkType(property.type)} ${property.name}){
		this.${property.name} = ${property.name};
	}
	</#if>
	</#list>
}