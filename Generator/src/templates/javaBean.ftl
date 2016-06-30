
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
package beans;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "${class.name}")
${class.visibility} class ${class.name} {

	<#list class.properties as property>
	<#if property.dbProperty?exists>
		<#if property.dbProperty.id>
	@Id
    @GeneratedValue
		</#if>
	@Column(name = "${property.name}", unique = "${property.dbProperty.unique?c}", nullable = "${mandatory(property.dbProperty.mandatory)?c}")
	private ${checkType(property.type)} ${property.name};
	</#if>
	<#if !property.dbProperty?exists>
    	<#if property.upper == 100>
    @OneToMany
    @JoinColumn(name="${property.name}", unique = "false", nullable = "false")
    private Set<${property.type}> ${property.name};
    	</#if>	
    @ManyToOne
    @JoinColumn(name="${property.name}", unique = "false", nullable = "false")
    private ${property.type} ${property.name};
	</#if>
	
	</#list>
	public ${class.name}() {
        super();
    }
    <#list class.properties as property>
	
	${property.visibility} ${checkType(property.type)} get${property.name?cap_first}(){
		return ${property.name};
	}
	
	${property.visibility} void set${property.name?cap_first}(${checkType(property.type)} ${property.name}){
		this.${property.name} = ${property.name};
	}
	</#list>
}