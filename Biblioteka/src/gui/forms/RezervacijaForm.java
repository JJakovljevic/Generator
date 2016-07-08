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
import gui.forms.detailpanels.PanelDetailRezervacija;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;


import bean.Rezervacija;

import dao.RezervacijaDaoBean;
import dao.ClanDaoBean;
import bean.Clan;
import dao.KnjigaOgrankaDaoBean;
import bean.KnjigaOgranka;

import bean.Clan;
import bean.KnjigaOgranka;



@SuppressWarnings("serial")
public class RezervacijaForm extends AbstractForm {

	private RezervacijaDaoBean rezervacijaDao = new RezervacijaDaoBean();
	private Clan clan;
	private KnjigaOgranka knjigaogranka;

	public RezervacijaForm(JFrame parent) {
		super(parent);
		setTitle("Rezervacija");
		panelDetail = new PanelDetailRezervacija(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID rezervacije",
           "Datum rezervacije",
           "Status rezervacije",
           "clan",
           "knjigaogranka"
        });
		List<Rezervacija> rezervacijaList = rezervacijaDao.findAll();
       
        for (Rezervacija obj : rezervacijaList) {
            tableModel.addRow(new Object[] {
                obj.getIdRezervacije(),
                obj.getDatumRezervacije(),
                obj.getStatusRezervacije(),
                obj.getClan(),
                obj.getKnjigaogranka()
            });
        }
		
		initGUI();
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);  
		
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new RezervacijaSelectionListener());
	}
	
	
	public RezervacijaForm(JFrame parent,Clan clan) {
		super(parent);
		setTitle("Rezervacija");
		panelDetail = new PanelDetailRezervacija(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID rezervacije",
           "Datum rezervacije",
           "Status rezervacije",
           "clan",
           "knjigaogranka"
        });
		List<Rezervacija> rezervacijaList = rezervacijaDao.findAll();
       	List<Rezervacija> rezervacijaList1 = new ArrayList<>();
		if(clan!=null){
			this.clan = clan;
			for(Rezervacija rezervacija :rezervacijaList){
				if( rezervacija.getClan().getBrClanskeKarte().equals(clan.getBrClanskeKarte())){
					rezervacijaList1.add(rezervacija);
				}
			}
		}else{
			rezervacijaList1 = rezervacijaList;
		}
        for (Rezervacija obj : rezervacijaList1) {
            tableModel.addRow(new Object[] {
                obj.getIdRezervacije(),
                obj.getDatumRezervacije(),
                obj.getStatusRezervacije(),
                obj.getClan(),
                obj.getKnjigaogranka()
            });
        }
		
		initGUI();  
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);
		
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new RezervacijaSelectionListener());
	}
	public RezervacijaForm(JFrame parent,KnjigaOgranka knjigaogranka) {
		super(parent);
		setTitle("Rezervacija");
		panelDetail = new PanelDetailRezervacija(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID rezervacije",
           "Datum rezervacije",
           "Status rezervacije",
           "clan",
           "knjigaogranka"
        });
		List<Rezervacija> rezervacijaList = rezervacijaDao.findAll();
       	List<Rezervacija> rezervacijaList1 = new ArrayList<>();
		if(knjigaogranka!=null){
			this.knjigaogranka = knjigaogranka;
			for(Rezervacija rezervacija :rezervacijaList){
				if( rezervacija.getKnjigaogranka().getIdKnjigeOgranka().equals(knjigaogranka.getIdKnjigeOgranka())){
					rezervacijaList1.add(rezervacija);
				}
			}
		}else{
			rezervacijaList1 = rezervacijaList;
		}
        for (Rezervacija obj : rezervacijaList1) {
            tableModel.addRow(new Object[] {
                obj.getIdRezervacije(),
                obj.getDatumRezervacije(),
                obj.getStatusRezervacije(),
                obj.getClan(),
                obj.getKnjigaogranka()
            });
        }
		
		initGUI();  
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);
		
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new RezervacijaSelectionListener());
	}

	@Override
	public void dodavanje() {
		
		PanelDetailRezervacija panelDetailDodavanje = new PanelDetailRezervacija(StanjeDijaloga.ADD);
		if(clan!=null){
			panelDetailDodavanje.getClanField().removeAllItems();
			panelDetailDodavanje.getClanField().addItem(clan);
		}
		if(knjigaogranka!=null){
			panelDetailDodavanje.getKnjigaogrankaField().removeAllItems();
			panelDetailDodavanje.getKnjigaogrankaField().addItem(knjigaogranka);
		}
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Rezervacija", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Rezervacija rezervacija= new Rezervacija();
	        rezervacija.setDatumRezervacije((Date)panelDetailDodavanje.getDatumRezervacijeField().getModel().getValue());
	       	rezervacija.setStatusRezervacije(panelDetailDodavanje.getStatusRezervacijeField().isSelected());
	        rezervacija.setClan((Clan)panelDetailDodavanje.getClanField().getSelectedItem());
	        rezervacija.setKnjigaogranka((KnjigaOgranka)panelDetailDodavanje.getKnjigaogrankaField().getSelectedItem());
			
			
			rezervacijaDao.persist(rezervacija);
		 	tableModel.addRow(new Object[] {
            	rezervacija.getIdRezervacije(),
            	rezervacija.getDatumRezervacije(),
            	rezervacija.getStatusRezervacije(),
            	rezervacija.getClan(),
            	rezervacija.getKnjigaogranka()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(rezervacija.getIdRezervacije().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailRezervacija panel = new PanelDetailRezervacija(StanjeDijaloga.UPDATE);
			if(clan!=null){
				panel.getClanField().removeAllItems();
				panel.getClanField().addItem(clan);
			}
			if(knjigaogranka!=null){
				panel.getKnjigaogrankaField().removeAllItems();
				panel.getKnjigaogrankaField().addItem(knjigaogranka);
			}
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Rezervacija", panel);
			Rezervacija rezervacija = null;
			rezervacija = rezervacijaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdRezervacijeField().setText(rezervacija.getIdRezervacije()+"");
			panel.getDatumRezervacijeField().getModel().setDate(rezervacija.getDatumRezervacije().getYear()+1900, rezervacija.getDatumRezervacije().getMonth(), rezervacija.getDatumRezervacije().getDate());
			panel.getStatusRezervacijeField().setSelected(rezervacija.getStatusRezervacije());
			panel.getClanField().setSelectedItem(rezervacija.getClan());
			panel.getKnjigaogrankaField().setSelectedItem(rezervacija.getKnjigaogranka());
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        rezervacija.setIdRezervacije(Integer.parseInt(panel.getIdRezervacijeField().getText()));
	        	rezervacija.setDatumRezervacije((Date)panel.getDatumRezervacijeField().getModel().getValue());
	       		rezervacija.setStatusRezervacije(panel.getStatusRezervacijeField().isSelected());
		        rezervacija.setClan((Clan)panel.getClanField().getSelectedItem());
		        rezervacija.setKnjigaogranka((KnjigaOgranka)panel.getKnjigaogrankaField().getSelectedItem());
				rezervacijaDao.merge(rezervacija);
        		tableModel.setValueAt(rezervacija.getIdRezervacije(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(rezervacija.getDatumRezervacije(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(rezervacija.getStatusRezervacije(),table.getSelectedRow() , 2);
        		tableModel.setValueAt(rezervacija.getClan(),table.getSelectedRow() , 3);
        		tableModel.setValueAt(rezervacija.getKnjigaogranka(),table.getSelectedRow() , 4);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(rezervacija.getIdRezervacije().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete rezervacija?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Rezervacija rezervacija = rezervacijaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				rezervacijaDao.remove(rezervacija);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class RezervacijaSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailRezervacija panel = (PanelDetailRezervacija)panelDetail;
					panel.getIdRezervacijeField().setText("");
					panel.getDatumRezervacijeField().getModel().setDate(2016, 1, 1);
					panel.getStatusRezervacijeField().setSelected(false);
					panel.getClanField().setSelectedItem(null);
					panel.getKnjigaogrankaField().setSelectedItem(null);
	        		return;
	        	}
	        		
				Rezervacija rezervacija = rezervacijaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailRezervacija panel = (PanelDetailRezervacija)panelDetail;
				panel.getIdRezervacijeField().setText(rezervacija.getIdRezervacije()+"");
				panel.getDatumRezervacijeField().getModel().setDate(rezervacija.getDatumRezervacije().getYear()+1900, rezervacija.getDatumRezervacije().getMonth(), rezervacija.getDatumRezervacije().getDate());
				panel.getStatusRezervacijeField().setSelected(rezervacija.getStatusRezervacije());
				panel.getClanField().setSelectedItem(rezervacija.getClan());
				panel.getKnjigaogrankaField().setSelectedItem(rezervacija.getKnjigaogranka());
			

	        }
		}
	}
	

}
