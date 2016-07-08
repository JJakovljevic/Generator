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
import gui.forms.detailpanels.PanelDetailPrimerak;
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

import bean.Primerak;

import dao.PrimerakDaoBean;
import enumeration.Stanje;
import dao.KnjigaOgrankaDaoBean;
import bean.KnjigaOgranka;
import dao.IzdavacDaoBean;
import bean.Izdavac;

import bean.KnjigaOgranka;
import bean.Izdavac;



@SuppressWarnings("serial")
public class PrimerakForm extends AbstractForm {

	private PrimerakDaoBean primerakDao = new PrimerakDaoBean();
	private KnjigaOgranka knjigaogranka;
	private Izdavac izdavac;
	JMenuItem jmi0;

	public PrimerakForm(JFrame parent) {
		super(parent);
		setTitle("Primerak");
		panelDetail = new PanelDetailPrimerak(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID primerka",
           "Godina izdavanja",
           "Stanje primerka",
           "knjigaogranka",
           "izdavac"
        });
		List<Primerak> primerakList = primerakDao.findAll();
       
        for (Primerak obj : primerakList) {
            tableModel.addRow(new Object[] {
                obj.getIdPrimerka(),
                obj.getGodinaIzdavanja(),
                obj.getStanje(),
                obj.getKnjigaogranka(),
                obj.getIzdavac()
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
		
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new PrimerakSelectionListener());
	}
	
	
	public PrimerakForm(JFrame parent,KnjigaOgranka knjigaogranka) {
		super(parent);
		setTitle("Primerak");
		panelDetail = new PanelDetailPrimerak(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID primerka",
           "Godina izdavanja",
           "Stanje primerka",
           "knjigaogranka",
           "izdavac"
        });
		List<Primerak> primerakList = primerakDao.findAll();
       	List<Primerak> primerakList1 = new ArrayList<>();
		if(knjigaogranka!=null){
			this.knjigaogranka = knjigaogranka;
			for(Primerak primerak :primerakList){
				if( primerak.getKnjigaogranka().getIdKnjigeOgranka().equals(knjigaogranka.getIdKnjigeOgranka())){
					primerakList1.add(primerak);
				}
			}
		}else{
			primerakList1 = primerakList;
		}
        for (Primerak obj : primerakList1) {
            tableModel.addRow(new Object[] {
                obj.getIdPrimerka(),
                obj.getGodinaIzdavanja(),
                obj.getStanje(),
                obj.getKnjigaogranka(),
                obj.getIzdavac()
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
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new PrimerakSelectionListener());
	}
	public PrimerakForm(JFrame parent,Izdavac izdavac) {
		super(parent);
		setTitle("Primerak");
		panelDetail = new PanelDetailPrimerak(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID primerka",
           "Godina izdavanja",
           "Stanje primerka",
           "knjigaogranka",
           "izdavac"
        });
		List<Primerak> primerakList = primerakDao.findAll();
       	List<Primerak> primerakList1 = new ArrayList<>();
		if(izdavac!=null){
			this.izdavac = izdavac;
			for(Primerak primerak :primerakList){
				if( primerak.getIzdavac().getIdIzdavaca().equals(izdavac.getIdIzdavaca())){
					primerakList1.add(primerak);
				}
			}
		}else{
			primerakList1 = primerakList;
		}
        for (Primerak obj : primerakList1) {
            tableModel.addRow(new Object[] {
                obj.getIdPrimerka(),
                obj.getGodinaIzdavanja(),
                obj.getStanje(),
                obj.getKnjigaogranka(),
                obj.getIzdavac()
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
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new PrimerakSelectionListener());
	}

	@Override
	public void dodavanje() {
		
		PanelDetailPrimerak panelDetailDodavanje = new PanelDetailPrimerak(StanjeDijaloga.ADD);
		if(knjigaogranka!=null){
			panelDetailDodavanje.getKnjigaogrankaField().removeAllItems();
			panelDetailDodavanje.getKnjigaogrankaField().addItem(knjigaogranka);
		}
		if(izdavac!=null){
			panelDetailDodavanje.getIzdavacField().removeAllItems();
			panelDetailDodavanje.getIzdavacField().addItem(izdavac);
		}
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Primerak", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Primerak primerak= new Primerak();
	        primerak.setGodinaIzdavanja(Integer.parseInt(panelDetailDodavanje.getGodinaIzdavanjaField().getText()));
	        primerak.setStanje((Stanje)panelDetailDodavanje.getStanjeField().getSelectedItem());
	        primerak.setKnjigaogranka((KnjigaOgranka)panelDetailDodavanje.getKnjigaogrankaField().getSelectedItem());
	        primerak.setIzdavac((Izdavac)panelDetailDodavanje.getIzdavacField().getSelectedItem());
			
			
			primerakDao.persist(primerak);
		 	tableModel.addRow(new Object[] {
            	primerak.getIdPrimerka(),
            	primerak.getGodinaIzdavanja(),
            	primerak.getStanje(),
            	primerak.getKnjigaogranka(),
            	primerak.getIzdavac()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(primerak.getIdPrimerka().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailPrimerak panel = new PanelDetailPrimerak(StanjeDijaloga.UPDATE);
			if(knjigaogranka!=null){
				panel.getKnjigaogrankaField().removeAllItems();
				panel.getKnjigaogrankaField().addItem(knjigaogranka);
			}
			if(izdavac!=null){
				panel.getIzdavacField().removeAllItems();
				panel.getIzdavacField().addItem(izdavac);
			}
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Primerak", panel);
			Primerak primerak = null;
			primerak = primerakDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdPrimerkaField().setText(primerak.getIdPrimerka()+"");
			panel.getGodinaIzdavanjaField().setText(primerak.getGodinaIzdavanja()+"");
			panel.getStanjeField().setSelectedItem(primerak.getStanje());
			panel.getKnjigaogrankaField().setSelectedItem(primerak.getKnjigaogranka());
			panel.getIzdavacField().setSelectedItem(primerak.getIzdavac());
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        primerak.setIdPrimerka(Integer.parseInt(panel.getIdPrimerkaField().getText()));
		        primerak.setGodinaIzdavanja(Integer.parseInt(panel.getGodinaIzdavanjaField().getText()));
		        primerak.setStanje((Stanje)panel.getStanjeField().getSelectedItem());
		        primerak.setKnjigaogranka((KnjigaOgranka)panel.getKnjigaogrankaField().getSelectedItem());
		        primerak.setIzdavac((Izdavac)panel.getIzdavacField().getSelectedItem());
				primerakDao.merge(primerak);
        		tableModel.setValueAt(primerak.getIdPrimerka(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(primerak.getGodinaIzdavanja(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(primerak.getStanje(),table.getSelectedRow() , 2);
        		tableModel.setValueAt(primerak.getKnjigaogranka(),table.getSelectedRow() , 3);
        		tableModel.setValueAt(primerak.getIzdavac(),table.getSelectedRow() , 4);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(primerak.getIdPrimerka().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete primerak?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Primerak primerak = primerakDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				primerakDao.remove(primerak);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class PrimerakSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailPrimerak panel = (PanelDetailPrimerak)panelDetail;
					panel.getIdPrimerkaField().setText("");
					panel.getGodinaIzdavanjaField().setText("");
					panel.getStanjeField().setSelectedItem(null);
					panel.getKnjigaogrankaField().setSelectedItem(null);
					panel.getIzdavacField().setSelectedItem(null);
					jmi0.setText("Zaduzenja");
					jmi0.setEnabled(false);
	        		return;
	        	}
	        		
				Primerak primerak = primerakDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailPrimerak panel = (PanelDetailPrimerak)panelDetail;
				panel.getIdPrimerkaField().setText(primerak.getIdPrimerka()+"");
				panel.getGodinaIzdavanjaField().setText(primerak.getGodinaIzdavanja()+"");
				panel.getStanjeField().setSelectedItem(primerak.getStanje());
				panel.getKnjigaogrankaField().setSelectedItem(primerak.getKnjigaogranka());
				panel.getIzdavacField().setSelectedItem(primerak.getIzdavac());
			
				jmi0.setAction(new OpenZaduzenjeFormAction(primerak));
				jmi0.setText("Zaduzenja");
				jmi0.setEnabled(true);

	        }
		}
	}
	

}
