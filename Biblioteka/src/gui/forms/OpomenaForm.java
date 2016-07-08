/**********************************************************************/
/*          Generisano na osnovu templejta: standardForm.ftl          */
/**********************************************************************/

package gui.forms;

import java.awt.Toolkit;
import java.util.Iterator;
import java.util.*;

import gui.forms.AbstractForm;
import gui.forms.AbstractForm.StanjeDijaloga;
import gui.forms.AddUpdateFindDialog;
import gui.forms.detailpanels.PanelDetailOpomena;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;


import bean.Opomena;

import dao.OpomenaDaoBean;
import dao.VrstaOpomeneDaoBean;
import bean.VrstaOpomene;
import dao.ZaduzenjeDaoBean;
import bean.Zaduzenje;

import bean.VrstaOpomene;
import bean.Zaduzenje;



@SuppressWarnings("serial")
public class OpomenaForm extends AbstractForm {

	private OpomenaDaoBean opomenaDao = new OpomenaDaoBean();
	private VrstaOpomene vrstaopomene;
	private Zaduzenje zaduzenje;

	public OpomenaForm(JFrame parent) {
		super(parent);
		setTitle("Opomena");
		panelDetail = new PanelDetailOpomena(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Oznaka opomene",
           "Naziv opomene",
           "Status opomene",
           "vrstaopomene",
           "zaduzenje"
        });
		List<Opomena> opomenaList = opomenaDao.findAll();
       
        for (Opomena obj : opomenaList) {
            tableModel.addRow(new Object[] {
                obj.getOznakaOpomene(),
                obj.getNazivOpomene(),
                obj.getStatusOpomene(),
                obj.getVrstaopomene(),
                obj.getZaduzenje()
            });
        }
		
		initGUI();
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);  
		
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new OpomenaSelectionListener());
	}
	
	
	public OpomenaForm(JFrame parent,VrstaOpomene vrstaopomene) {
		super(parent);
		setTitle("Opomena");
		panelDetail = new PanelDetailOpomena(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Oznaka opomene",
           "Naziv opomene",
           "Status opomene",
           "vrstaopomene",
           "zaduzenje"
        });
		List<Opomena> opomenaList = opomenaDao.findAll();
       	List<Opomena> opomenaList1 = new ArrayList<>();
		if(vrstaopomene!=null){
			this.vrstaopomene = vrstaopomene;
			for(Opomena opomena :opomenaList){
				if( opomena.getVrstaopomene().getIdVrste().equals(vrstaopomene.getIdVrste())){
					opomenaList1.add(opomena);
				}
			}
		}else{
			opomenaList1 = opomenaList;
		}
        for (Opomena obj : opomenaList1) {
            tableModel.addRow(new Object[] {
                obj.getOznakaOpomene(),
                obj.getNazivOpomene(),
                obj.getStatusOpomene(),
                obj.getVrstaopomene(),
                obj.getZaduzenje()
            });
        }
		
		initGUI();  
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);
		
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new OpomenaSelectionListener());
	}
	public OpomenaForm(JFrame parent,Zaduzenje zaduzenje) {
		super(parent);
		setTitle("Opomena");
		panelDetail = new PanelDetailOpomena(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Oznaka opomene",
           "Naziv opomene",
           "Status opomene",
           "vrstaopomene",
           "zaduzenje"
        });
		List<Opomena> opomenaList = opomenaDao.findAll();
       	List<Opomena> opomenaList1 = new ArrayList<>();
		if(zaduzenje!=null){
			this.zaduzenje = zaduzenje;
			for(Opomena opomena :opomenaList){
				if( opomena.getZaduzenje().getIdZaduzenja().equals(zaduzenje.getIdZaduzenja())){
					opomenaList1.add(opomena);
				}
			}
		}else{
			opomenaList1 = opomenaList;
		}
        for (Opomena obj : opomenaList1) {
            tableModel.addRow(new Object[] {
                obj.getOznakaOpomene(),
                obj.getNazivOpomene(),
                obj.getStatusOpomene(),
                obj.getVrstaopomene(),
                obj.getZaduzenje()
            });
        }
		
		initGUI();  
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);
		
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new OpomenaSelectionListener());
	}

	@Override
	public void dodavanje() {
		
		PanelDetailOpomena panelDetailDodavanje = new PanelDetailOpomena(StanjeDijaloga.ADD);
		if(vrstaopomene!=null){
			panelDetailDodavanje.getVrstaopomeneField().removeAllItems();
			panelDetailDodavanje.getVrstaopomeneField().addItem(vrstaopomene);
		}
		if(zaduzenje!=null){
			panelDetailDodavanje.getZaduzenjeField().removeAllItems();
			panelDetailDodavanje.getZaduzenjeField().addItem(zaduzenje);
		}
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Opomena", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Opomena opomena= new Opomena();
	        opomena.setNazivOpomene(panelDetailDodavanje.getNazivOpomeneField().getText());
	        opomena.setOpisOpomene(panelDetailDodavanje.getOpisOpomeneField().getText());
	       	opomena.setStatusOpomene(panelDetailDodavanje.getStatusOpomeneField().isSelected());
	        opomena.setVrstaopomene((VrstaOpomene)panelDetailDodavanje.getVrstaopomeneField().getSelectedItem());
	        opomena.setZaduzenje((Zaduzenje)panelDetailDodavanje.getZaduzenjeField().getSelectedItem());
			
			
			opomenaDao.persist(opomena);
		 	tableModel.addRow(new Object[] {
            	opomena.getOznakaOpomene(),
            	opomena.getNazivOpomene(),
            	opomena.getStatusOpomene(),
            	opomena.getVrstaopomene(),
            	opomena.getZaduzenje()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(opomena.getOznakaOpomene().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailOpomena panel = new PanelDetailOpomena(StanjeDijaloga.UPDATE);
			if(vrstaopomene!=null){
				panel.getVrstaopomeneField().removeAllItems();
				panel.getVrstaopomeneField().addItem(vrstaopomene);
			}
			if(zaduzenje!=null){
				panel.getZaduzenjeField().removeAllItems();
				panel.getZaduzenjeField().addItem(zaduzenje);
			}
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Opomena", panel);
			Opomena opomena = null;
			opomena = opomenaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getOznakaOpomeneField().setText(opomena.getOznakaOpomene()+"");
			panel.getNazivOpomeneField().setText(opomena.getNazivOpomene()+"");
			panel.getOpisOpomeneField().setText(opomena.getOpisOpomene()+"");
			panel.getStatusOpomeneField().setSelected(opomena.getStatusOpomene());
			panel.getVrstaopomeneField().setSelectedItem(opomena.getVrstaopomene());
			panel.getZaduzenjeField().setSelectedItem(opomena.getZaduzenje());
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        opomena.setOznakaOpomene(Integer.parseInt(panel.getOznakaOpomeneField().getText()));
		        opomena.setNazivOpomene(panel.getNazivOpomeneField().getText());
		        opomena.setOpisOpomene(panel.getOpisOpomeneField().getText());
	       		opomena.setStatusOpomene(panel.getStatusOpomeneField().isSelected());
		        opomena.setVrstaopomene((VrstaOpomene)panel.getVrstaopomeneField().getSelectedItem());
		        opomena.setZaduzenje((Zaduzenje)panel.getZaduzenjeField().getSelectedItem());
				opomenaDao.merge(opomena);
        		tableModel.setValueAt(opomena.getOznakaOpomene(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(opomena.getNazivOpomene(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(opomena.getStatusOpomene(),table.getSelectedRow() , 2);
        		tableModel.setValueAt(opomena.getVrstaopomene(),table.getSelectedRow() , 3);
        		tableModel.setValueAt(opomena.getZaduzenje(),table.getSelectedRow() , 4);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(opomena.getOznakaOpomene().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete opomena?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Opomena opomena = opomenaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				opomenaDao.remove(opomena);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class OpomenaSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailOpomena panel = (PanelDetailOpomena)panelDetail;
					panel.getOznakaOpomeneField().setText("");
					panel.getNazivOpomeneField().setText("");
					panel.getOpisOpomeneField().setText("");
					panel.getStatusOpomeneField().setSelected(false);
					panel.getVrstaopomeneField().setSelectedItem(null);
					panel.getZaduzenjeField().setSelectedItem(null);
	        		return;
	        	}
	        		
				Opomena opomena = opomenaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailOpomena panel = (PanelDetailOpomena)panelDetail;
				panel.getOznakaOpomeneField().setText(opomena.getOznakaOpomene()+"");
				panel.getNazivOpomeneField().setText(opomena.getNazivOpomene()+"");
				panel.getOpisOpomeneField().setText(opomena.getOpisOpomene()+"");
				panel.getStatusOpomeneField().setSelected(opomena.getStatusOpomene());
				panel.getVrstaopomeneField().setSelectedItem(opomena.getVrstaopomene());
				panel.getZaduzenjeField().setSelectedItem(opomena.getZaduzenje());
			

	        }
		}
	}
	

}
