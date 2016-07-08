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
import gui.forms.detailpanels.PanelDetailOgranak;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

import action.OpenKnjigaOgrankaFormAction;
import action.OpenBibliotekarFormAction;

import bean.Ogranak;

import dao.OgranakDaoBean;




@SuppressWarnings("serial")
public class OgranakForm extends AbstractForm {

	private OgranakDaoBean ogranakDao = new OgranakDaoBean();
	JMenuItem jmi0;
	JMenuItem jmi1;

	public OgranakForm(JFrame parent) {
		super(parent);
		setTitle("Ogranak");
		panelDetail = new PanelDetailOgranak(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Oznaka ogranka",
           "Ime ogranka",
           "Adresa ogranka",
           "Telefon ogranka"
        });
		List<Ogranak> ogranakList = ogranakDao.findAll();
       
        for (Ogranak obj : ogranakList) {
            tableModel.addRow(new Object[] {
                obj.getOznakaOgranka(),
                obj.getImeOgranka(),
                obj.getAdresaOgranka(),
                obj.getTelefonOgranka()
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
		jmi0.setAction(new OpenKnjigaOgrankaFormAction());
		jmi0.setText("Knjige ogranka");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		
		jmi1 = new JMenuItem();
		jmi1.setAction(new OpenBibliotekarFormAction());
		jmi1.setText("Bibliotekar");
		jmi1.setEnabled(false);
		jpm.add(jmi1);
		
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new OgranakSelectionListener());
	}
	
	

	@Override
	public void dodavanje() {
		
		PanelDetailOgranak panelDetailDodavanje = new PanelDetailOgranak(StanjeDijaloga.ADD);
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Ogranak", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Ogranak ogranak= new Ogranak();
	        ogranak.setImeOgranka(panelDetailDodavanje.getImeOgrankaField().getText());
	        ogranak.setAdresaOgranka(panelDetailDodavanje.getAdresaOgrankaField().getText());
	        ogranak.setTelefonOgranka(panelDetailDodavanje.getTelefonOgrankaField().getText());
			
			
			ogranakDao.persist(ogranak);
		 	tableModel.addRow(new Object[] {
            	ogranak.getOznakaOgranka(),
            	ogranak.getImeOgranka(),
            	ogranak.getAdresaOgranka(),
            	ogranak.getTelefonOgranka()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(ogranak.getOznakaOgranka().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailOgranak panel = new PanelDetailOgranak(StanjeDijaloga.UPDATE);
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Ogranak", panel);
			Ogranak ogranak = null;
			ogranak = ogranakDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getOznakaOgrankaField().setText(ogranak.getOznakaOgranka()+"");
			panel.getImeOgrankaField().setText(ogranak.getImeOgranka()+"");
			panel.getAdresaOgrankaField().setText(ogranak.getAdresaOgranka()+"");
			panel.getTelefonOgrankaField().setText(ogranak.getTelefonOgranka()+"");
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        ogranak.setOznakaOgranka(Integer.parseInt(panel.getOznakaOgrankaField().getText()));
		        ogranak.setImeOgranka(panel.getImeOgrankaField().getText());
		        ogranak.setAdresaOgranka(panel.getAdresaOgrankaField().getText());
		        ogranak.setTelefonOgranka(panel.getTelefonOgrankaField().getText());
				ogranakDao.merge(ogranak);
        		tableModel.setValueAt(ogranak.getOznakaOgranka(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(ogranak.getImeOgranka(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(ogranak.getAdresaOgranka(),table.getSelectedRow() , 2);
        		tableModel.setValueAt(ogranak.getTelefonOgranka(),table.getSelectedRow() , 3);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(ogranak.getOznakaOgranka().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete ogranak?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Ogranak ogranak = ogranakDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				ogranakDao.remove(ogranak);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class OgranakSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailOgranak panel = (PanelDetailOgranak)panelDetail;
					panel.getOznakaOgrankaField().setText("");
					panel.getImeOgrankaField().setText("");
					panel.getAdresaOgrankaField().setText("");
					panel.getTelefonOgrankaField().setText("");
					jmi0.setText("Knjige ogranka");
					jmi0.setEnabled(false);
					jmi1.setText("Bibliotekar");
					jmi1.setEnabled(false);
	        		return;
	        	}
	        		
				Ogranak ogranak = ogranakDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailOgranak panel = (PanelDetailOgranak)panelDetail;
				panel.getOznakaOgrankaField().setText(ogranak.getOznakaOgranka()+"");
				panel.getImeOgrankaField().setText(ogranak.getImeOgranka()+"");
				panel.getAdresaOgrankaField().setText(ogranak.getAdresaOgranka()+"");
				panel.getTelefonOgrankaField().setText(ogranak.getTelefonOgranka()+"");
			
				jmi0.setAction(new OpenKnjigaOgrankaFormAction(ogranak));
				jmi0.setText("Knjige ogranka");
				jmi0.setEnabled(true);
				jmi1.setAction(new OpenBibliotekarFormAction(ogranak));
				jmi1.setText("Bibliotekar");
				jmi1.setEnabled(true);

	        }
		}
	}
	

}
