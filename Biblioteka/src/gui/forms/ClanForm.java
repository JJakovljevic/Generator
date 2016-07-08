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
import gui.forms.detailpanels.PanelDetailClan;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

import action.OpenZaduzenjeFormAction;
import action.OpenRezervacijaFormAction;

import bean.Clan;

import dao.ClanDaoBean;
import enumeration.Pol;
import enumeration.Clanstvo;




@SuppressWarnings("serial")
public class ClanForm extends AbstractForm {

	private ClanDaoBean clanDao = new ClanDaoBean();
	JMenuItem jmi0;
	JMenuItem jmi1;

	public ClanForm(JFrame parent) {
		super(parent);
		setTitle("Clan");
		panelDetail = new PanelDetailClan(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Broj clanske karte",
           "Ime clana",
           "Prezime clana",
           "JMBG",
           "Datum rodjenja",
           "Adresa clana",
           "Telefon clana",
           "Email clana",
           "Pol clana",
           "Clanstvo",
           "Datum uplate"
        });
		List<Clan> clanList = clanDao.findAll();
       
        for (Clan obj : clanList) {
            tableModel.addRow(new Object[] {
                obj.getBrClanskeKarte(),
                obj.getImeClana(),
                obj.getPrezimeClana(),
                obj.getJmbg(),
                obj.getDatumRodjenja(),
                obj.getAdresaClana(),
                obj.getTelefonClana(),
                obj.getEmailClana(),
                obj.getPolClana(),
                obj.getClanstvo(),
                obj.getDatumUplate()
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
		jmi0.setAction(new OpenZaduzenjeFormAction());
		jmi0.setText("Zaduzenja");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		
		jmi1 = new JMenuItem();
		jmi1.setAction(new OpenRezervacijaFormAction());
		jmi1.setText("Rezervacija");
		jmi1.setEnabled(false);
		jpm.add(jmi1);
		
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ClanSelectionListener());
	}
	
	

	@Override
	public void dodavanje() {
		
		PanelDetailClan panelDetailDodavanje = new PanelDetailClan(StanjeDijaloga.ADD);
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Clan", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Clan clan= new Clan();
	        clan.setImeClana(panelDetailDodavanje.getImeClanaField().getText());
	        clan.setPrezimeClana(panelDetailDodavanje.getPrezimeClanaField().getText());
	        clan.setJmbg(panelDetailDodavanje.getJmbgField().getText());
	        clan.setDatumRodjenja((Date)panelDetailDodavanje.getDatumRodjenjaField().getModel().getValue());
	        clan.setAdresaClana(panelDetailDodavanje.getAdresaClanaField().getText());
	        clan.setTelefonClana(panelDetailDodavanje.getTelefonClanaField().getText());
	        clan.setEmailClana(panelDetailDodavanje.getEmailClanaField().getText());
	        clan.setPolClana((Pol)panelDetailDodavanje.getPolClanaField().getSelectedItem());
	        clan.setClanstvo((Clanstvo)panelDetailDodavanje.getClanstvoField().getSelectedItem());
	        clan.setDatumUplate((Date)panelDetailDodavanje.getDatumUplateField().getModel().getValue());
			
			
			clanDao.persist(clan);
		 	tableModel.addRow(new Object[] {
            	clan.getBrClanskeKarte(),
            	clan.getImeClana(),
            	clan.getPrezimeClana(),
            	clan.getJmbg(),
            	clan.getDatumRodjenja(),
            	clan.getAdresaClana(),
            	clan.getTelefonClana(),
            	clan.getEmailClana(),
            	clan.getPolClana(),
            	clan.getClanstvo(),
            	clan.getDatumUplate()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(clan.getBrClanskeKarte().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailClan panel = new PanelDetailClan(StanjeDijaloga.UPDATE);
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Clan", panel);
			Clan clan = null;
			clan = clanDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getBrClanskeKarteField().setText(clan.getBrClanskeKarte()+"");
			panel.getImeClanaField().setText(clan.getImeClana()+"");
			panel.getPrezimeClanaField().setText(clan.getPrezimeClana()+"");
			panel.getJmbgField().setText(clan.getJmbg()+"");
			panel.getDatumRodjenjaField().getModel().setDate(clan.getDatumRodjenja().getYear()+1900, clan.getDatumRodjenja().getMonth(), clan.getDatumRodjenja().getDate());
			panel.getAdresaClanaField().setText(clan.getAdresaClana()+"");
			panel.getTelefonClanaField().setText(clan.getTelefonClana()+"");
			panel.getEmailClanaField().setText(clan.getEmailClana()+"");
			panel.getPolClanaField().setSelectedItem(clan.getPolClana());
			panel.getClanstvoField().setSelectedItem(clan.getClanstvo());
			panel.getDatumUplateField().getModel().setDate(clan.getDatumUplate().getYear()+1900, clan.getDatumUplate().getMonth(), clan.getDatumUplate().getDate());
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        clan.setBrClanskeKarte(Integer.parseInt(panel.getBrClanskeKarteField().getText()));
		        clan.setImeClana(panel.getImeClanaField().getText());
		        clan.setPrezimeClana(panel.getPrezimeClanaField().getText());
		        clan.setJmbg(panel.getJmbgField().getText());
	        	clan.setDatumRodjenja((Date)panel.getDatumRodjenjaField().getModel().getValue());
		        clan.setAdresaClana(panel.getAdresaClanaField().getText());
		        clan.setTelefonClana(panel.getTelefonClanaField().getText());
		        clan.setEmailClana(panel.getEmailClanaField().getText());
		        clan.setPolClana((Pol)panel.getPolClanaField().getSelectedItem());
		        clan.setClanstvo((Clanstvo)panel.getClanstvoField().getSelectedItem());
	        	clan.setDatumUplate((Date)panel.getDatumUplateField().getModel().getValue());
				clanDao.merge(clan);
        		tableModel.setValueAt(clan.getBrClanskeKarte(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(clan.getImeClana(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(clan.getPrezimeClana(),table.getSelectedRow() , 2);
        		tableModel.setValueAt(clan.getJmbg(),table.getSelectedRow() , 3);
        		tableModel.setValueAt(clan.getDatumRodjenja(),table.getSelectedRow() , 4);
        		tableModel.setValueAt(clan.getAdresaClana(),table.getSelectedRow() , 5);
        		tableModel.setValueAt(clan.getTelefonClana(),table.getSelectedRow() , 6);
        		tableModel.setValueAt(clan.getEmailClana(),table.getSelectedRow() , 7);
        		tableModel.setValueAt(clan.getPolClana(),table.getSelectedRow() , 8);
        		tableModel.setValueAt(clan.getClanstvo(),table.getSelectedRow() , 9);
        		tableModel.setValueAt(clan.getDatumUplate(),table.getSelectedRow() , 10);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(clan.getBrClanskeKarte().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete clan?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Clan clan = clanDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				clanDao.remove(clan);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class ClanSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailClan panel = (PanelDetailClan)panelDetail;
					panel.getBrClanskeKarteField().setText("");
					panel.getImeClanaField().setText("");
					panel.getPrezimeClanaField().setText("");
					panel.getJmbgField().setText("");
					panel.getDatumRodjenjaField().getModel().setDate(2016, 1, 1);
					panel.getAdresaClanaField().setText("");
					panel.getTelefonClanaField().setText("");
					panel.getEmailClanaField().setText("");
					panel.getPolClanaField().setSelectedItem(null);
					panel.getClanstvoField().setSelectedItem(null);
					panel.getDatumUplateField().getModel().setDate(2016, 1, 1);
					jmi0.setText("Zaduzenja");
					jmi0.setEnabled(false);
					jmi1.setText("Rezervacija");
					jmi1.setEnabled(false);
	        		return;
	        	}
	        		
				Clan clan = clanDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailClan panel = (PanelDetailClan)panelDetail;
				panel.getBrClanskeKarteField().setText(clan.getBrClanskeKarte()+"");
				panel.getImeClanaField().setText(clan.getImeClana()+"");
				panel.getPrezimeClanaField().setText(clan.getPrezimeClana()+"");
				panel.getJmbgField().setText(clan.getJmbg()+"");
				panel.getDatumRodjenjaField().getModel().setDate(clan.getDatumRodjenja().getYear()+1900, clan.getDatumRodjenja().getMonth(), clan.getDatumRodjenja().getDate());
				panel.getAdresaClanaField().setText(clan.getAdresaClana()+"");
				panel.getTelefonClanaField().setText(clan.getTelefonClana()+"");
				panel.getEmailClanaField().setText(clan.getEmailClana()+"");
				panel.getPolClanaField().setSelectedItem(clan.getPolClana());
				panel.getClanstvoField().setSelectedItem(clan.getClanstvo());
				panel.getDatumUplateField().getModel().setDate(clan.getDatumUplate().getYear()+1900, clan.getDatumUplate().getMonth(), clan.getDatumUplate().getDate());
			
				jmi0.setAction(new OpenZaduzenjeFormAction(clan));
				jmi0.setText("Zaduzenja");
				jmi0.setEnabled(true);
				jmi1.setAction(new OpenRezervacijaFormAction(clan));
				jmi1.setText("Rezervacija");
				jmi1.setEnabled(true);

	        }
		}
	}
	

}
