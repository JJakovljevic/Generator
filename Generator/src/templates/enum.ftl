package destination.model;

public enum ${enum.name} {
<#list enum.literals as literal>
    ${literal.name}<#if literal?has_next>,<#else>;</#if>
</#list>

	 public String toString() {
	 	switch(this){
			<#list enum.literals as literal>
			case ${literal.name}:
				return "${literal.name?cap_first}";
			</#list>
			
			default:
	 			return super.toString();
	 	 }
	  }

}
