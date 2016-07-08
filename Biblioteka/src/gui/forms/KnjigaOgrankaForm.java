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
import gui.forms.detailpanels.PanelDetailKnjigaOgranka;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

import action.OpenRezervacijaFormAction;
import action.OpenPrimerakFormAction;

import bean.KnjigaOgranka;

import dao.KnjigaOgrankaDaoBean;
import dao.OgranakDaoBean;
import bean.Ogranak;
import dao.KnjigaDaoBean;
import bean.Knjiga;

import bean.Ogranak;
import bean.Knjiga;



@SuppressWarnings("serial")
public class KnjigaOgrankaForm extends AbstractForm {

	private KnjigaOgrankaDaoBean knjigaogrankaDao = new KnjigaOgrankaDaoBean();
	private Ogranak ogranak;
	private Knjiga knjiga;
	JMenuItem jmi0;
	JMenuItem jmi1;

	public KnjigaOgrankaForm(JFrame parent) {
		super(parent);
		setTitle("Knjige ogranka");
		panelDetail = new PanelDetailKnjigaOgranka(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID knjige ogranka",
           "ogranak",
           "knjiga"
        });
		List<KnjigaOgranka> knjigaogrankaList = knjigaogrankaDao.findAll();
       
        for (KnjigaOgranka obj : knjigaogrankaList) {
            tableModel.addRow(new Object[] {
                obj.getIdKnjigeOgranka(),
                obj.getOgranak(),
                obj.getKnjiga()
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
		jmi0.setAction(new OpenRezervacijaFormAction());
		jmi0.setText("Rezervacija");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		
		jmi1 = new JMenuItem();
		jmi1.setAction(new OpenPrimerakFormAction());
		jmi1.setText("Primerak");
		jmi1.setEnabled(false);
		jpm.add(jmi1);
		
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new KnjigaOgrankaSelectionListener());
	}
	
	
	public KnjigaOgrankaForm(JFrame parent,Ogranak ogranak) {
		super(parent);
		setTitle("Knjige ogranka");
		panelDetail = new PanelDetailKnjigaOgranka(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID knjige ogranka",
           "ogranak",
           "knjiga"
        });
		List<KnjigaOgranka> knjigaogrankaList = knjigaogrankaDao.findAll();
       	List<KnjigaOgranka> knjigaogrankaList1 = new ArrayList<>();
		if(ogranak!=null){
			this.ogranak = ogranak;
			for(KnjigaOgranka knjigaogranka :knjigaogrankaList){
				if( knjigaogranka.getOgranak().getOznakaOgranka().equals(ogranak.getOznakaOgranka())){
					knjigaogrankaList1.add(knjigaogranka);
				}
			}
		}else{
			knjigaogrankaList1 = knjigaogrankaList;
		}
        for (KnjigaOgranka obj : knjigaogrankaList1) {
            tableModel.addRow(new Object[] {
                obj.getIdKnjigeOgranka(),
                obj.getOgranak(),
                obj.getKnjiga()
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
		jmi0.setAction(new OpenRezervacijaFormAction());
		jmi0.setText("Rezervacija");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		jmi1 = new JMenuItem();
		jmi1.setAction(new OpenPrimerakFormAction());
		jmi1.setText("Primerak");
		jmi1.setEnabled(false);
		jpm.add(jmi1);
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new KnjigaOgrankaSelectionListener());
	}
	public KnjigaOgrankaForm(JFrame parent,Knjiga knjiga) {
		super(parent);
		setTitle("Knjige ogranka");
		panelDetail = new PanelDetailKnjigaOgranka(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID knjige ogranka",
           "ogranak",
           "knjiga"
        });
		List<KnjigaOgranka> knjigaogrankaList = knjigaogrankaDao.findAll();
       	List<KnjigaOgranka> knjigaogrankaList1 = new ArrayList<>();
		if(knjiga!=null){
			this.knjiga = knjiga;
			for(KnjigaOgranka knjigaogranka :knjigaogrankaList){
				if( knjigaogranka.getKnjiga().getIdKnjiga().equals(knjiga.getIdKnjiga())){
					knjigaogrankaList1.add(knjigaogranka);
				}
			}
		}else{
			knjigaogrankaList1 = knjigaogrankaList;
		}
        for (KnjigaOgranka obj : knjigaogrankaList1) {
            tableModel.addRow(new Object[] {
                obj.getIdKnjigeOgranka(),
                obj.getOgranak(),
                obj.getKnjiga()
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
		jmi0.setAction(new OpenRezervacijaFormAction());
		jmi0.setText("Rezervacija");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		jmi1 = new JMenuItem();
		jmi1.setAction(new OpenPrimerakFormAction());
		jmi1.setText("Primerak");
		jmi1.setEnabled(false);
		jpm.add(jmi1);
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new KnjigaOgrankaSelectionListener());
	}

	@Override
	public void dodavanje() {
		
		PanelDetailKnjigaOgranka panelDetailDodavanje = new PanelDetailKnjigaOgranka(StanjeDijaloga.ADD);
		if(ogranak!=null){
			panelDetailDodavanje.getOgranakField().removeAllItems();
			panelDetailDodavanje.getOgranakField().addItem(ogranak);
		}
		if(knjiga!=null){
			panelDetailDodavanje.getKnjigaField().removeAllItems();
			panelDetailDodavanje.getKnjigaField().addItem(knjiga);
		}
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Knjige ogranka", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			KnjigaOgranka knjigaOgranka= new KnjigaOgranka();
	        knjigaOgranka.setOgranak((Ogranak)panelDetailDodavanje.getOgranakField().getSelectedItem());
	        knjigaOgranka.setKnjiga((Knjiga)panelDetailDodavanje.getKnjigaField().getSelectedItem());
			
			
			knjigaogrankaDao.persist(knjigaOgranka);
		 	tableModel.addRow(new Object[] {
            	knjigaOgranka.getIdKnjigeOgranka(),
            	knjigaOgranka.getOgranak(),
            	knjigaOgranka.getKnjiga()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(knjigaOgranka.getIdKnjigeOgranka().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailKnjigaOgranka panel = new PanelDetailKnjigaOgranka(StanjeDijaloga.UPDATE);
			if(ogranak!=null){
				panel.getOgranakField().removeAllItems();
				panel.getOgranakField().addItem(ogranak);
			}
			if(knjiga!=null){
				panel.getKnjigaField().removeAllItems();
				panel.getKnjigaField().addItem(knjiga);
			}
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update KnjigaOgranka", panel);
			KnjigaOgranka knjigaOgranka = null;
			knjigaOgranka = knjigaogrankaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdKnjigeOgrankaField().setText(knjigaOgranka.getIdKnjigeOgranka()+"");
			panel.getOgranakField().setSelectedItem(knjigaOgranka.getOgranak());
			panel.getKnjigaField().setSelectedItem(knjigaOgranka.getKnjiga());
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        knjigaOgranka.setIdKnjigeOgranka(Integer.parseInt(panel.getIdKnjigeOgrankaField().getText()));
		        knjigaOgranka.setOgranak((Ogranak)panel.getOgranakField().getSelectedItem());
		        knjigaOgranka.setKnjiga((Knjiga)panel.getKnjigaField().getSelectedItem());
				knjigaogrankaDao.merge(knjigaOgranka);
        		tableModel.setValueAt(knjigaOgranka.getIdKnjigeOgranka(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(knjigaOgranka.getOgranak(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(knjigaOgranka.getKnjiga(),table.getSelectedRow() , 2);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(knjigaOgranka.getIdKnjigeOgranka().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete knjigaogranka?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				KnjigaOgranka knjigaOgranka = knjigaogrankaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				knjigaogrankaDao.remove(knjigaOgranka);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class KnjigaOgrankaSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailKnjigaOgranka panel = (PanelDetailKnjigaOgranka)panelDetail;
					panel.getIdKnjigeOgrankaField().setText("");
					panel.getOgranakField().setSelectedItem(null);
					panel.getKnjigaField().setSelectedItem(null);
					jmi0.setText("Rezervacija");
					jmi0.setEnabled(false);
					jmi1.setText("Primerak");
					jmi1.setEnabled(false);
	        		return;
	        	}
	        		
				KnjigaOgranka knjigaOgranka = knjigaogrankaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailKnjigaOgranka panel = (PanelDetailKnjigaOgranka)panelDetail;
				panel.getIdKnjigeOgrankaField().setText(knjigaOgranka.getIdKnjigeOgranka()+"");
				panel.getOgranakField().setSelectedItem(knjigaOgranka.getOgranak());
				panel.getKnjigaField().setSelectedItem(knjigaOgranka.getKnjiga());
			
				jmi0.setAction(new OpenRezervacijaFormAction(knjigaOgranka));
				jmi0.setText("Rezervacija");
				jmi0.setEnabled(true);
				jmi1.setAction(new OpenPrimerakFormAction(knjigaOgranka));
				jmi1.setText("Primerak");
				jmi1.setEnabled(true);

	        }
		}
	}
	

}
