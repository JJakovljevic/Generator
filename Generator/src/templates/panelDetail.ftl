<#function fieldType prop>
	<#if prop.uiProperty??>
		<#if prop.uiProperty.componentKind == "textField">
			<#return "JTextField">
		<#elseif prop.uiProperty.componentKind == "editor">
			<#return "JTextArea">
		<#elseif prop.uiProperty.componentKind == "combobox">
			<#return "JComboBox">
		<#elseif prop.uiProperty.componentKind == "checkbox">
			<#return "JCheckBox">
		<#elseif prop.uiProperty.componentKind == "dateChooser">
			<#return "JDatePicker">
		<#elseif prop.uiProperty.componentKind == "textArea">
			<#return "JTextArea">
		</#if>
	<#else>
		<#return "JComboBox">
	</#if>
</#function>
package gui.forms.detailpanels;

import gui.forms.AbstractForm.StanjeDijaloga;
import gui.forms.detailpanels.akcije.BojenjeKeyListener;
import gui.forms.detailpanels.formatter.CustDateFormatter;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

<#list properties as property>
	<#if !property.uiProperty??>
import dao.${property.type}DaoBean;
import bean.${property.type};
	<#elseif property.uiProperty?? && property.uiProperty.componentKind == "combobox" && !property.enumerated>
import dao.${property.type}DaoBean;
import bean.${property.type};
	</#if>
	<#if property.enumerated>
import enumeration.${property.type};
	</#if>
</#list>

@SuppressWarnings("serial")
public class PanelDetail${class.name} extends AbstractPanelDetail {
	
	<#list properties as property>
	<#if !property.uiProperty??>
	private ${property.type}DaoBean ${property.type?lower_case}Dao = new ${property.type}DaoBean();
	<#elseif property.uiProperty?? && property.uiProperty.componentKind == "combobox" && !property.enumerated>
	private ${property.type}DaoBean ${property.type?lower_case}Dao = new ${property.type}DaoBean();
	</#if>
</#list>
	
<#list properties as property>
	private JLabel lbl${property.name};
	private ${fieldType(property)} ${property.name}Field;
</#list>
	
	public PanelDetail${class.name}(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
<#list properties as property>
		JPanel pan${property.name} = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbl${property.name} = new JLabel(<#if property.uiProperty??>"${property.uiProperty.label}"<#else>"${property.name}"</#if>);
		lbl${property.name}.setPreferredSize(lblDimension);
		<#if fieldType(property) == "JComboBox">
		${property.name}Field = new JComboBox();
		
		<#if property.enumerated>
	    	<#list property.reference.literals as literal>
	    ${property.name}Field.addItem(${property.type}.${literal.name});
	    	</#list>
	    <#else>
	    List<${property.type}> ${property.name}List = ${property.name}Dao.findAll();     
	    for (${property.type} obj : ${property.name}List) {
	    	${property.name}Field.addItem(obj);
	    }
	    </#if>
		${property.name}Field.setEnabled(mode != StanjeDijaloga.BROWSE && ${property.isEditable()?c});
		<#elseif fieldType(property) == "JDatePicker">
		UtilDateModel model${property?index} = new UtilDateModel();
		Properties p${property?index} = new Properties();
		JDatePanelImpl jdp${property?index} = new JDatePanelImpl(model${property?index}, p${property?index});
		AbstractFormatter cf${property?index} = new CustDateFormatter();
		${property.name}Field = new JDatePickerImpl(jdp${property?index}, cf${property?index} );
		${property.name}Field.setTextEditable(mode != StanjeDijaloga.BROWSE  && ${property.isEditable()?c});
		${property.name}Field.getModel().setSelected(true);
		<#elseif fieldType(property) == "JCheckBox">
		${property.name}Field = new JCheckBox();
		${property.name}Field.setEnabled(mode != StanjeDijaloga.BROWSE  && ${property.isEditable()?c});
		<#elseif fieldType(property) == "JTextField">
		${property.name}Field = new JTextField(${property.uiProperty.length});
		${property.name}Field.setEnabled(mode != StanjeDijaloga.BROWSE  <#if property.dbProperty.id>&& mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD</#if> && ${property.isEditable()?c});
		${property.name}Field.addKeyListener(new BojenjeKeyListener(${property.name}Field));
		<#elseif fieldType(property) == "JTextArea">
		${property.name}Field = new JTextArea(5,20);
		${property.name}Field.setEnabled(mode != StanjeDijaloga.BROWSE  <#if property.dbProperty.id>&& mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD</#if> && ${property.isEditable()?c});
		${property.name}Field.setLineWrap(true);
		JScrollPane jsp${property?index} = new JScrollPane(${property.name}Field);
		jsp${property?index}.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		</#if>
		pan${property.name}.add(lbl${property.name});
		<#if fieldType(property) == "JTextArea">pan${property.name}.add(jsp${property?index});<#else>pan${property.name}.add((Component)${property.name}Field);</#if>
		<#if property.dbProperty?? && property.dbProperty.id> pan${property.name}.setVisible(mode != StanjeDijaloga.ADD);</#if>
		boxCentar.add(pan${property.name});
		
</#list>
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
<#list properties as property>
	<#if fieldType(property) == "JTextField" && property.dbProperty.mandatory && !property.dbProperty.id>
		if (${property.name}Field.getText().trim().equals("")) {
			${property.name}Field.setBackground(Color.RED);
			ok = false;
		}
	</#if>
		
</#list>
	return ok;
	}
<#list properties as property>
	public ${fieldType(property)} get${property.name?cap_first}Field() {
		return ${property.name}Field;
	}
</#list>
	
}
