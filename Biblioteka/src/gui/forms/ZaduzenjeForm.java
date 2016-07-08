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
import gui.forms.detailpanels.PanelDetailZaduzenje;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

import action.OpenOpomenaFormAction;

import bean.Zaduzenje;

import dao.ZaduzenjeDaoBean;
import dao.PrimerakDaoBean;
import bean.Primerak;
import dao.ClanDaoBean;
import bean.Clan;

import bean.Primerak;
import bean.Clan;



@SuppressWarnings("serial")
public class ZaduzenjeForm extends AbstractForm {

	private ZaduzenjeDaoBean zaduzenjeDao = new ZaduzenjeDaoBean();
	private Primerak primerak;
	private Clan clan;
	JMenuItem jmi0;

	public ZaduzenjeForm(JFrame parent) {
		super(parent);
		setTitle("Zaduzenja");
		panelDetail = new PanelDetailZaduzenje(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "idZaduzenja",
           "datumVracanjaMax",
           "datumVracanja",
           "primerak",
           "clan"
        });
		List<Zaduzenje> zaduzenjeList = zaduzenjeDao.findAll();
       
        for (Zaduzenje obj : zaduzenjeList) {
            tableModel.addRow(new Object[] {
                obj.getIdZaduzenja(),
                obj.getDatumVracanjaMax(),
                obj.getDatumVracanja(),
                obj.getPrimerak(),
                obj.getClan()
            });
        }
		
		initGUI();
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);  
		
		JButton btZoom = new JButton();
		btZoom.setIcon(new ImageIcon("images/icons/chain.gif"));
		JPopupMenu jpm = new JPopupMenu();
		jmi0 = new JMenuItem();
		jmi0.setAction(new OpenOpomenaFormAction());
		jmi0.setText("Opomena");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ZaduzenjeSelectionListener());
	}
	
	
	public ZaduzenjeForm(JFrame parent,Primerak primerak) {
		super(parent);
		setTitle("Zaduzenja");
		panelDetail = new PanelDetailZaduzenje(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "idZaduzenja",
           "datumVracanjaMax",
           "datumVracanja",
           "primerak",
           "clan"
        });
		List<Zaduzenje> zaduzenjeList = zaduzenjeDao.findAll();
       	List<Zaduzenje> zaduzenjeList1 = new ArrayList<>();
		if(primerak!=null){
			this.primerak = primerak;
			for(Zaduzenje zaduzenje :zaduzenjeList){
				if( zaduzenje.getPrimerak().getIdPrimerka().equals(primerak.getIdPrimerka())){
					zaduzenjeList1.add(zaduzenje);
				}
			}
		}else{
			zaduzenjeList1 = zaduzenjeList;
		}
        for (Zaduzenje obj : zaduzenjeList1) {
            tableModel.addRow(new Object[] {
                obj.getIdZaduzenja(),
                obj.getDatumVracanjaMax(),
                obj.getDatumVracanja(),
                obj.getPrimerak(),
                obj.getClan()
            });
        }
		
		initGUI();  
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);
		
		JButton btZoom = new JButton();
		btZoom.setIcon(new ImageIcon("images/icons/chain.gif"));
		JPopupMenu jpm = new JPopupMenu();
		jmi0 = new JMenuItem();
		jmi0.setAction(new OpenOpomenaFormAction());
		jmi0.setText("Opomena");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ZaduzenjeSelectionListener());
	}
	public ZaduzenjeForm(JFrame parent,Clan clan) {
		super(parent);
		setTitle("Zaduzenja");
		panelDetail = new PanelDetailZaduzenje(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "idZaduzenja",
           "datumVracanjaMax",
           "datumVracanja",
           "primerak",
           "clan"
        });
		List<Zaduzenje> zaduzenjeList = zaduzenjeDao.findAll();
       	List<Zaduzenje> zaduzenjeList1 = new ArrayList<>();
		if(clan!=null){
			this.clan = clan;
			for(Zaduzenje zaduzenje :zaduzenjeList){
				if( zaduzenje.getClan().getBrClanskeKarte().equals(clan.getBrClanskeKarte())){
					zaduzenjeList1.add(zaduzenje);
				}
			}
		}else{
			zaduzenjeList1 = zaduzenjeList;
		}
        for (Zaduzenje obj : zaduzenjeList1) {
            tableModel.addRow(new Object[] {
                obj.getIdZaduzenja(),
                obj.getDatumVracanjaMax(),
                obj.getDatumVracanja(),
                obj.getPrimerak(),
                obj.getClan()
            });
        }
		
		initGUI();  
		toolbar.getBtnDodavanje().setEnabled(true);
		toolbar.getBtnBrisanje().setEnabled(true);
		toolbar.getBtnIzmena().setEnabled(true);
		
		JButton btZoom = new JButton();
		btZoom.setIcon(new ImageIcon("images/icons/chain.gif"));
		JPopupMenu jpm = new JPopupMenu();
		jmi0 = new JMenuItem();
		jmi0.setAction(new OpenOpomenaFormAction());
		jmi0.setText("Opomena");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ZaduzenjeSelectionListener());
	}

	@Override
	public void dodavanje() {
		
		PanelDetailZaduzenje panelDetailDodavanje = new PanelDetailZaduzenje(StanjeDijaloga.ADD);
		if(primerak!=null){
			panelDetailDodavanje.getPrimerakField().removeAllItems();
			panelDetailDodavanje.getPrimerakField().addItem(primerak);
		}
		if(clan!=null){
			panelDetailDodavanje.getClanField().removeAllItems();
			panelDetailDodavanje.getClanField().addItem(clan);
		}
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Zaduzenja", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Zaduzenje zaduzenje= new Zaduzenje();
	        zaduzenje.setDatumIznajmljivanja((Date)panelDetailDodavanje.getDatumIznajmljivanjaField().getModel().getValue());
	        zaduzenje.setDatumVracanjaMax((Date)panelDetailDodavanje.getDatumVracanjaMaxField().getModel().getValue());
	        zaduzenje.setDatumVracanja((Date)panelDetailDodavanje.getDatumVracanjaField().getModel().getValue());
	        zaduzenje.setPrimerak((Primerak)panelDetailDodavanje.getPrimerakField().getSelectedItem());
	        zaduzenje.setClan((Clan)panelDetailDodavanje.getClanField().getSelectedItem());
			
			
			zaduzenjeDao.persist(zaduzenje);
		 	tableModel.addRow(new Object[] {
            	zaduzenje.getIdZaduzenja(),
            	zaduzenje.getDatumVracanjaMax(),
            	zaduzenje.getDatumVracanja(),
            	zaduzenje.getPrimerak(),
            	zaduzenje.getClan()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(zaduzenje.getIdZaduzenja().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailZaduzenje panel = new PanelDetailZaduzenje(StanjeDijaloga.UPDATE);
			if(primerak!=null){
				panel.getPrimerakField().removeAllItems();
				panel.getPrimerakField().addItem(primerak);
			}
			if(clan!=null){
				panel.getClanField().removeAllItems();
				panel.getClanField().addItem(clan);
			}
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Zaduzenje", panel);
			Zaduzenje zaduzenje = null;
			zaduzenje = zaduzenjeDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdZaduzenjaField().setText(zaduzenje.getIdZaduzenja()+"");
			panel.getDatumIznajmljivanjaField().getModel().setDate(zaduzenje.getDatumIznajmljivanja().getYear()+1900, zaduzenje.getDatumIznajmljivanja().getMonth(), zaduzenje.getDatumIznajmljivanja().getDate());
			panel.getDatumVracanjaMaxField().getModel().setDate(zaduzenje.getDatumVracanjaMax().getYear()+1900, zaduzenje.getDatumVracanjaMax().getMonth(), zaduzenje.getDatumVracanjaMax().getDate());
			panel.getDatumVracanjaField().getModel().setDate(zaduzenje.getDatumVracanja().getYear()+1900, zaduzenje.getDatumVracanja().getMonth(), zaduzenje.getDatumVracanja().getDate());
			panel.getPrimerakField().setSelectedItem(zaduzenje.getPrimerak());
			panel.getClanField().setSelectedItem(zaduzenje.getClan());
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        zaduzenje.setIdZaduzenja(Integer.parseInt(panel.getIdZaduzenjaField().getText()));
	        	zaduzenje.setDatumIznajmljivanja((Date)panel.getDatumIznajmljivanjaField().getModel().getValue());
	        	zaduzenje.setDatumVracanjaMax((Date)panel.getDatumVracanjaMaxField().getModel().getValue());
	        	zaduzenje.setDatumVracanja((Date)panel.getDatumVracanjaField().getModel().getValue());
		        zaduzenje.setPrimerak((Primerak)panel.getPrimerakField().getSelectedItem());
		        zaduzenje.setClan((Clan)panel.getClanField().getSelectedItem());
				zaduzenjeDao.merge(zaduzenje);
        		tableModel.setValueAt(zaduzenje.getIdZaduzenja(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(zaduzenje.getDatumVracanjaMax(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(zaduzenje.getDatumVracanja(),table.getSelectedRow() , 2);
        		tableModel.setValueAt(zaduzenje.getPrimerak(),table.getSelectedRow() , 3);
        		tableModel.setValueAt(zaduzenje.getClan(),table.getSelectedRow() , 4);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(zaduzenje.getIdZaduzenja().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete zaduzenje?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Zaduzenje zaduzenje = zaduzenjeDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				zaduzenjeDao.remove(zaduzenje);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class ZaduzenjeSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailZaduzenje panel = (PanelDetailZaduzenje)panelDetail;
					panel.getIdZaduzenjaField().setText("");
					panel.getDatumIznajmljivanjaField().getModel().setDate(2016, 1, 1);
					panel.getDatumVracanjaMaxField().getModel().setDate(2016, 1, 1);
					panel.getDatumVracanjaField().getModel().setDate(2016, 1, 1);
					panel.getPrimerakField().setSelectedItem(null);
					panel.getClanField().setSelectedItem(null);
					jmi0.setText("Opomena");
					jmi0.setEnabled(false);
	        		return;
	        	}
	        		
				Zaduzenje zaduzenje = zaduzenjeDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailZaduzenje panel = (PanelDetailZaduzenje)panelDetail;
				panel.getIdZaduzenjaField().setText(zaduzenje.getIdZaduzenja()+"");
				panel.getDatumIznajmljivanjaField().getModel().setDate(zaduzenje.getDatumIznajmljivanja().getYear()+1900, zaduzenje.getDatumIznajmljivanja().getMonth(), zaduzenje.getDatumIznajmljivanja().getDate());
				panel.getDatumVracanjaMaxField().getModel().setDate(zaduzenje.getDatumVracanjaMax().getYear()+1900, zaduzenje.getDatumVracanjaMax().getMonth(), zaduzenje.getDatumVracanjaMax().getDate());
				panel.getDatumVracanjaField().getModel().setDate(zaduzenje.getDatumVracanja().getYear()+1900, zaduzenje.getDatumVracanja().getMonth(), zaduzenje.getDatumVracanja().getDate());
				panel.getPrimerakField().setSelectedItem(zaduzenje.getPrimerak());
				panel.getClanField().setSelectedItem(zaduzenje.getClan());
			
				jmi0.setAction(new OpenOpomenaFormAction(zaduzenje));
				jmi0.setText("Opomena");
				jmi0.setEnabled(true);

	        }
		}
	}
	

}
