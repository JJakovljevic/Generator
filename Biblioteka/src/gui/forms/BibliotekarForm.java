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
import gui.forms.detailpanels.PanelDetailBibliotekar;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;


import bean.Bibliotekar;

import dao.BibliotekarDaoBean;
import dao.OgranakDaoBean;
import bean.Ogranak;
import enumeration.Pol;

import bean.Ogranak;



@SuppressWarnings("serial")
public class BibliotekarForm extends AbstractForm {

	private BibliotekarDaoBean bibliotekarDao = new BibliotekarDaoBean();
	private Ogranak ogranak;

	public BibliotekarForm(JFrame parent) {
		super(parent);
		setTitle("Bibliotekar");
		panelDetail = new PanelDetailBibliotekar(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Maticni broj bibliotekara",
           "ogranak",
           "Korisnicko ime",
           "Ime zaposlenog",
           "Prezime zaposlenog",
           "Pol"
        });
		List<Bibliotekar> bibliotekarList = bibliotekarDao.findAll();
       
        for (Bibliotekar obj : bibliotekarList) {
            tableModel.addRow(new Object[] {
                obj.getMaticniBrojBibliotekara(),
                obj.getOgranak(),
                obj.getKorisnickoIme(),
                obj.getImeZaposlenog(),
                obj.getPrezimeZaposlenog(),
                obj.getPol()
            });
        }
		
		initGUI();
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);  
		
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new BibliotekarSelectionListener());
	}
	
	
	public BibliotekarForm(JFrame parent,Ogranak ogranak) {
		super(parent);
		setTitle("Bibliotekar");
		panelDetail = new PanelDetailBibliotekar(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Maticni broj bibliotekara",
           "ogranak",
           "Korisnicko ime",
           "Ime zaposlenog",
           "Prezime zaposlenog",
           "Pol"
        });
		List<Bibliotekar> bibliotekarList = bibliotekarDao.findAll();
       	List<Bibliotekar> bibliotekarList1 = new ArrayList<>();
		if(ogranak!=null){
			this.ogranak = ogranak;
			for(Bibliotekar bibliotekar :bibliotekarList){
				if( bibliotekar.getOgranak().getOznakaOgranka().equals(ogranak.getOznakaOgranka())){
					bibliotekarList1.add(bibliotekar);
				}
			}
		}else{
			bibliotekarList1 = bibliotekarList;
		}
        for (Bibliotekar obj : bibliotekarList1) {
            tableModel.addRow(new Object[] {
                obj.getMaticniBrojBibliotekara(),
                obj.getOgranak(),
                obj.getKorisnickoIme(),
                obj.getImeZaposlenog(),
                obj.getPrezimeZaposlenog(),
                obj.getPol()
            });
        }
		
		initGUI();  
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);
		
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new BibliotekarSelectionListener());
	}

	@Override
	public void dodavanje() {
		
		PanelDetailBibliotekar panelDetailDodavanje = new PanelDetailBibliotekar(StanjeDijaloga.ADD);
		if(ogranak!=null){
			panelDetailDodavanje.getOgranakField().removeAllItems();
			panelDetailDodavanje.getOgranakField().addItem(ogranak);
		}
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Bibliotekar", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Bibliotekar bibliotekar= new Bibliotekar();
	        bibliotekar.setOgranak((Ogranak)panelDetailDodavanje.getOgranakField().getSelectedItem());
	        bibliotekar.setKorisnickoIme(panelDetailDodavanje.getKorisnickoImeField().getText());
	        bibliotekar.setImeZaposlenog(panelDetailDodavanje.getImeZaposlenogField().getText());
	        bibliotekar.setPrezimeZaposlenog(panelDetailDodavanje.getPrezimeZaposlenogField().getText());
	        bibliotekar.setLozinka(panelDetailDodavanje.getLozinkaField().getText());
	        bibliotekar.setPol((Pol)panelDetailDodavanje.getPolField().getSelectedItem());
			
			
			bibliotekarDao.persist(bibliotekar);
		 	tableModel.addRow(new Object[] {
            	bibliotekar.getMaticniBrojBibliotekara(),
            	bibliotekar.getOgranak(),
            	bibliotekar.getKorisnickoIme(),
            	bibliotekar.getImeZaposlenog(),
            	bibliotekar.getPrezimeZaposlenog(),
            	bibliotekar.getPol()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(bibliotekar.getMaticniBrojBibliotekara().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailBibliotekar panel = new PanelDetailBibliotekar(StanjeDijaloga.UPDATE);
			if(ogranak!=null){
				panel.getOgranakField().removeAllItems();
				panel.getOgranakField().addItem(ogranak);
			}
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Bibliotekar", panel);
			Bibliotekar bibliotekar = null;
			bibliotekar = bibliotekarDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getMaticniBrojBibliotekaraField().setText(bibliotekar.getMaticniBrojBibliotekara()+"");
			panel.getOgranakField().setSelectedItem(bibliotekar.getOgranak());
			panel.getKorisnickoImeField().setText(bibliotekar.getKorisnickoIme()+"");
			panel.getImeZaposlenogField().setText(bibliotekar.getImeZaposlenog()+"");
			panel.getPrezimeZaposlenogField().setText(bibliotekar.getPrezimeZaposlenog()+"");
			panel.getLozinkaField().setText(bibliotekar.getLozinka()+"");
			panel.getPolField().setSelectedItem(bibliotekar.getPol());
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        bibliotekar.setMaticniBrojBibliotekara(Integer.parseInt(panel.getMaticniBrojBibliotekaraField().getText()));
		        bibliotekar.setOgranak((Ogranak)panel.getOgranakField().getSelectedItem());
		        bibliotekar.setKorisnickoIme(panel.getKorisnickoImeField().getText());
		        bibliotekar.setImeZaposlenog(panel.getImeZaposlenogField().getText());
		        bibliotekar.setPrezimeZaposlenog(panel.getPrezimeZaposlenogField().getText());
		        bibliotekar.setLozinka(panel.getLozinkaField().getText());
		        bibliotekar.setPol((Pol)panel.getPolField().getSelectedItem());
				bibliotekarDao.merge(bibliotekar);
        		tableModel.setValueAt(bibliotekar.getMaticniBrojBibliotekara(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(bibliotekar.getOgranak(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(bibliotekar.getKorisnickoIme(),table.getSelectedRow() , 2);
        		tableModel.setValueAt(bibliotekar.getImeZaposlenog(),table.getSelectedRow() , 3);
        		tableModel.setValueAt(bibliotekar.getPrezimeZaposlenog(),table.getSelectedRow() , 4);
        		tableModel.setValueAt(bibliotekar.getPol(),table.getSelectedRow() , 5);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(bibliotekar.getMaticniBrojBibliotekara().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete bibliotekar?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Bibliotekar bibliotekar = bibliotekarDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				bibliotekarDao.remove(bibliotekar);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class BibliotekarSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailBibliotekar panel = (PanelDetailBibliotekar)panelDetail;
					panel.getMaticniBrojBibliotekaraField().setText("");
					panel.getOgranakField().setSelectedItem(null);
					panel.getKorisnickoImeField().setText("");
					panel.getImeZaposlenogField().setText("");
					panel.getPrezimeZaposlenogField().setText("");
					panel.getLozinkaField().setText("");
					panel.getPolField().setSelectedItem(null);
	        		return;
	        	}
	        		
				Bibliotekar bibliotekar = bibliotekarDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailBibliotekar panel = (PanelDetailBibliotekar)panelDetail;
				panel.getMaticniBrojBibliotekaraField().setText(bibliotekar.getMaticniBrojBibliotekara()+"");
				panel.getOgranakField().setSelectedItem(bibliotekar.getOgranak());
				panel.getKorisnickoImeField().setText(bibliotekar.getKorisnickoIme()+"");
				panel.getImeZaposlenogField().setText(bibliotekar.getImeZaposlenog()+"");
				panel.getPrezimeZaposlenogField().setText(bibliotekar.getPrezimeZaposlenog()+"");
				panel.getLozinkaField().setText(bibliotekar.getLozinka()+"");
				panel.getPolField().setSelectedItem(bibliotekar.getPol());
			

	        }
		}
	}
	

}
