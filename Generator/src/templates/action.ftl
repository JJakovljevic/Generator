package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.${class.name}Form;

<#list reference as ref>
import bean.${ref.type};
</#list>

public class Open${class.name}FormAction extends AbstractAction {
	
	<#list reference as ref>
		private ${ref.type} ${ref.type?lower_case};
	</#list>
	
    public Open${class.name}FormAction() {
        putValue(SHORT_DESCRIPTION, "${class.uiClass.label}");
		putValue(SMALL_ICON, new ImageIcon("images/${class.name?lower_case}.png"));
    }
    <#list reference as ref>
    public Open${class.name}FormAction(${ref.type} ${ref.type?lower_case}) {
        putValue(SHORT_DESCRIPTION, "${class.uiClass.label}");
		putValue(SMALL_ICON, new ImageIcon("images/${class.name?lower_case}.png"));
		this.${ref.type?lower_case} = ${ref.type?lower_case};
    }
    </#list>

    @Override
    public void actionPerformed(ActionEvent e) {
    	${class.name}Form form;
    	<#if refer>
    	<#list reference as ref>
    	<#if ref?index != 0>else </#if>if(${ref.type?lower_case} != null){
        	form = new ${class.name}Form(MainForm.getInstance(),${ref.type?lower_case});
        }
        </#list>
        else{
	    	form = new ${class.name}Form(MainForm.getInstance());
        }
        <#else>
        form = new ${class.name}Form(MainForm.getInstance());
        </#if>
        form.setVisible(true);
    }

}
