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
<#function checkType testSting>
	<#if testSting == "EDate" || testSting == "EBoolean">
  	<#return testSting?substring(1)>
  	<#else>
  	<#return testSting>
  	</#if>
</#function>
package gui.forms;

import java.awt.Toolkit;
import java.util.Iterator;
import java.util.*;

import gui.forms.AbstractForm;
import gui.forms.AbstractForm.StanjeDijaloga;
import gui.forms.AddUpdateFindDialog;
import gui.forms.detailpanels.PanelDetail${class.name};
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

import bean.${class.name};

import dao.${class.name}DaoBean;
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
public class ${class.name}Form extends AbstractForm {

	private ${class.name}DaoBean ${class.name?lower_case}Dao = new ${class.name}DaoBean();
<#list properties as property>
	<#if !property.uiProperty??>
	private ${property.type}DaoBean ${property.type?lower_case}Dao = new ${property.type}DaoBean();
	<#elseif property.uiProperty?? && property.uiProperty.componentKind == "combobox" && !property.enumerated>
	private ${property.type}DaoBean ${property.type?lower_case}Dao = new ${property.type}DaoBean();
	</#if>
</#list>

	public ${class.name}Form(JFrame parent) {
		super(parent);
		setTitle("${class.uiClass.label}");
		panelDetail = new PanelDetail${class.name}(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
<#list columns as column>
           <#if column.uiProperty??>"${column.uiProperty.label}"<#if column?has_next>,</#if><#else>"${column.name}"<#if column?has_next>,</#if></#if>
</#list>
        });
		List<${class.name}> ${class.name?lower_case}List = ${class.name?lower_case}Dao.findAll();
       
        for (${class.name} obj : ${class.name?lower_case}List) {
            tableModel.addRow(new Object[] {
<#list columns as column>
                obj.get${column.name?cap_first}()<#if column?has_next>,</#if>
</#list>
            });
        }
		
		initGUI();  // metodu initGUI() treba pozvati NA KRAJU konstruktora naslednika
		
		
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ${class.name}SelectionListener());
	}

	@Override
	public void dodavanje() {
		
		PanelDetail${class.name} panelDetailDodavanje = new PanelDetail${class.name}(StanjeDijaloga.ADD);
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add ${class.uiClass.label}", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			${class.name} ${class.name?uncap_first}= new ${class.name}();
		<#list properties as property>
			<#if !property.dbProperty?? || !property.dbProperty.id>
	        <#if property.type == "Integer">
	        ${class.name?uncap_first}.set${property.name?cap_first}(Integer.parseInt(panelDetailDodavanje.get${property.name?cap_first}Field().getText()));
	        <#elseif property.type == "Double">
	        ${class.name?uncap_first}.set${property.name?cap_first}(${property.type}.parse${property.type}(panelDetailDodavanjeget.${property.name?cap_first}Field().getText()));
	        <#elseif property.type == "String">
	        ${class.name?uncap_first}.set${property.name?cap_first}(panelDetailDodavanje.get${property.name?cap_first}Field().getText());
	        <#elseif fieldType(property) == "JDatePicker">
	        ${class.name?uncap_first}.set${property.name?cap_first}((${checkType(property.type)})panelDetailDodavanje.get${property.name?cap_first}Field().getModel().getValue());
	       	<#elseif fieldType(property) == "JCheckBox">
	       	${class.name?uncap_first}.set${property.name?cap_first}(panelDetailDodavanje.get${property.name?cap_first}Field().isSelected());
	        <#elseif property.uiProperty??>
	        ${class.name?uncap_first}.set${property.name?cap_first}((${checkType(property.type)})panelDetailDodavanje.get${property.name?cap_first}Field().getSelectedItem());
	        <#else>
	        ${class.name?uncap_first}.set${property.name?cap_first}((${checkType(property.type)})panelDetailDodavanje.get${property.name?cap_first}Field().getSelectedItem());
	        </#if>
	        </#if>
		</#list>
			
			
			${class.name?lower_case}Dao.persist(${class.name?uncap_first});
		 	tableModel.addRow(new Object[] {
			<#list columns as column>
            	${class.name?uncap_first}.get${column.name?cap_first}()<#if column?has_next>,</#if>
			</#list>
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(${class.name?uncap_first}.get${id.name?cap_first}().equals(tableModel.getValueAt(i, 0))){
				table.setRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
				break;
			}
		}
			
		}
		

	}

	@Override
	public void izmena() {
		// TODO Auto-generated method stub
		if(table.getSelectedRow()!=-1){
			PanelDetail${class.name} panel = new PanelDetail${class.name}(StanjeDijaloga.UPDATE);
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update ${class.name}", panel);
			${class.name} ${class.name?uncap_first} = null;
			${class.name?uncap_first} = ${class.name?lower_case}Dao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
		<#list properties as property>
			<#if fieldType(property) == "JComboBox">
			panel.get${property.name?cap_first}Field().setSelectedItem(${class.name?uncap_first}.get${property.name?cap_first}());
			<#elseif fieldType(property) == "JDatePicker">
			panel.get${property.name?cap_first}Field().getModel().setDate(${class.name?uncap_first}.get${property.name?cap_first}().getYear()+1900, ${class.name?uncap_first}.get${property.name?cap_first}().getDate(), ${class.name?uncap_first}.get${property.name?cap_first}().getMonth()+1);
			<#elseif fieldType(property) == "JCheckBox">
			panel.get${property.name?cap_first}Field().setSelected(${class.name?uncap_first}.get${property.name?cap_first}());
			<#else>
			panel.get${property.name?cap_first}Field().setText(${class.name?uncap_first}.get${property.name?cap_first}()+"");
			</#if>
		</#list>
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
			<#list properties as property>
		        <#if property.type == "Integer">
		        ${class.name?uncap_first}.set${property.name?cap_first}(Integer.parseInt(panel.get${property.name?cap_first}Field().getText()));
		        <#elseif property.type == "Double">
		        ${class.name?uncap_first}.set${property.name?cap_first}(${property.type}.parse${property.type}(panel.get${property.name?cap_first}Field().getText()));
		        <#elseif property.type == "String">
		        ${class.name?uncap_first}.set${property.name?cap_first}(panel.get${property.name?cap_first}Field().getText());
		        <#elseif fieldType(property) == "JDatePicker">
	        	${class.name?uncap_first}.set${property.name?cap_first}((${checkType(property.type)})panel.get${property.name?cap_first}Field().getModel().getValue());
		        <#elseif fieldType(property) == "JCheckBox">
	       		${class.name?uncap_first}.set${property.name?cap_first}(panel.get${property.name?cap_first}Field().isSelected());
		        <#elseif property.uiProperty??>
		        ${class.name?uncap_first}.set${property.name?cap_first}((${checkType(property.type)})panel.get${property.name?cap_first}Field().getSelectedItem());
		        <#else>
		        ${class.name?uncap_first}.set${property.name?cap_first}((${checkType(property.type)})panel.get${property.name?cap_first}Field().getSelectedItem());
		        </#if>
			</#list>
				${class.name?lower_case}Dao.merge(${class.name?uncap_first});
			<#list columns as column>
        		tableModel.setValueAt(${class.name?uncap_first}.get${column.name?cap_first}(),table.getSelectedRow() , ${column?index});
			</#list>
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(${class.name?uncap_first}.get${id.name?cap_first}().equals(tableModel.getValueAt(i, 0))){
						table.setRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
						break;
					}
				}
			}
			
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}

	@Override
	public void brisanje() {
		// TODO Auto-generated method stub
		
		if(table.getSelectedRow()!=-1){
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete drzavu?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				${class.name} ${class.name?uncap_first} = ${class.name?lower_case}Dao.findById((${id.type})(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				${class.name?lower_case}Dao.remove(${class.name?uncap_first});
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class ${class.name}SelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetail${class.name} panel = (PanelDetail${class.name})panelDetail;
					<#list properties as property>
						<#if fieldType(property) == "JComboBox">
						panel.get${property.name?cap_first}Field().setSelectedItem(null);
						<#elseif fieldType(property) == "JDatePicker">
						panel.get${property.name?cap_first}Field().getModel().setDate(2016, 1, 1);
						<#elseif fieldType(property) == "JCheckBox">
						panel.get${property.name?cap_first}Field().setSelected(false);
						<#else>
						panel.get${property.name?cap_first}Field().setText("");
						</#if>
					</#list>
	        		return;
	        	}
	        		
				${class.name} ${class.name?uncap_first} = ${class.name?lower_case}Dao.findById((${id.type})(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetail${class.name} panel = (PanelDetail${class.name})panelDetail;
			<#list properties as property>
				<#if fieldType(property) == "JComboBox">
				panel.get${property.name?cap_first}Field().setSelectedItem(${class.name?uncap_first}.get${property.name?cap_first}());
				<#elseif fieldType(property) == "JDatePicker">
				panel.get${property.name?cap_first}Field().getModel().setDate(${class.name?uncap_first}.get${property.name?cap_first}().getYear()+1900, ${class.name?uncap_first}.get${property.name?cap_first}().getDate(), ${class.name?uncap_first}.get${property.name?cap_first}().getMonth()+1);
				<#elseif fieldType(property) == "JCheckBox">
				panel.get${property.name?cap_first}Field().setSelected(${class.name?uncap_first}.get${property.name?cap_first}());
				<#else>
				panel.get${property.name?cap_first}Field().setText(${class.name?uncap_first}.get${property.name?cap_first}()+"");
				</#if>
			</#list>

	        }
		}
	}
	

}
