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
import gui.forms.detailpanels.PanelDetailIzdavac;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

import action.OpenPrimerakFormAction;

import bean.Izdavac;

import dao.IzdavacDaoBean;




@SuppressWarnings("serial")
public class IzdavacForm extends AbstractForm {

	private IzdavacDaoBean izdavacDao = new IzdavacDaoBean();
	JMenuItem jmi0;

	public IzdavacForm(JFrame parent) {
		super(parent);
		setTitle("Izdavac");
		panelDetail = new PanelDetailIzdavac(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID izdavaca",
           "Naziv izdavaca"
        });
		List<Izdavac> izdavacList = izdavacDao.findAll();
       
        for (Izdavac obj : izdavacList) {
            tableModel.addRow(new Object[] {
                obj.getIdIzdavaca(),
                obj.getNazivIzdavaca()
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
		jmi0.setAction(new OpenPrimerakFormAction());
		jmi0.setText("Primerak");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new IzdavacSelectionListener());
	}
	
	

	@Override
	public void dodavanje() {
		
		PanelDetailIzdavac panelDetailDodavanje = new PanelDetailIzdavac(StanjeDijaloga.ADD);
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Izdavac", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Izdavac izdavac= new Izdavac();
	        izdavac.setNazivIzdavaca(panelDetailDodavanje.getNazivIzdavacaField().getText());
			
			
			izdavacDao.persist(izdavac);
		 	tableModel.addRow(new Object[] {
            	izdavac.getIdIzdavaca(),
            	izdavac.getNazivIzdavaca()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(izdavac.getIdIzdavaca().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailIzdavac panel = new PanelDetailIzdavac(StanjeDijaloga.UPDATE);
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Izdavac", panel);
			Izdavac izdavac = null;
			izdavac = izdavacDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdIzdavacaField().setText(izdavac.getIdIzdavaca()+"");
			panel.getNazivIzdavacaField().setText(izdavac.getNazivIzdavaca()+"");
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        izdavac.setIdIzdavaca(Integer.parseInt(panel.getIdIzdavacaField().getText()));
		        izdavac.setNazivIzdavaca(panel.getNazivIzdavacaField().getText());
				izdavacDao.merge(izdavac);
        		tableModel.setValueAt(izdavac.getIdIzdavaca(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(izdavac.getNazivIzdavaca(),table.getSelectedRow() , 1);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(izdavac.getIdIzdavaca().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete izdavac?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Izdavac izdavac = izdavacDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				izdavacDao.remove(izdavac);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class IzdavacSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailIzdavac panel = (PanelDetailIzdavac)panelDetail;
					panel.getIdIzdavacaField().setText("");
					panel.getNazivIzdavacaField().setText("");
					jmi0.setText("Primerak");
					jmi0.setEnabled(false);
	        		return;
	        	}
	        		
				Izdavac izdavac = izdavacDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailIzdavac panel = (PanelDetailIzdavac)panelDetail;
				panel.getIdIzdavacaField().setText(izdavac.getIdIzdavaca()+"");
				panel.getNazivIzdavacaField().setText(izdavac.getNazivIzdavaca()+"");
			
				jmi0.setAction(new OpenPrimerakFormAction(izdavac));
				jmi0.setText("Primerak");
				jmi0.setEnabled(true);

	        }
		}
	}
	

}
