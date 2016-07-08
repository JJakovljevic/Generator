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
import gui.forms.detailpanels.PanelDetailZanr;
import gui.forms.detailpanels.akcije.PopupListener;
import gui.tablemodel.DialogTableModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

import action.OpenKnjigaFormAction;

import bean.Zanr;

import dao.ZanrDaoBean;




@SuppressWarnings("serial")
public class ZanrForm extends AbstractForm {

	private ZanrDaoBean zanrDao = new ZanrDaoBean();
	JMenuItem jmi0;

	public ZanrForm(JFrame parent) {
		super(parent);
		setTitle("Zanr");
		panelDetail = new PanelDetailZanr(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Id zanra",
           "Naziv zanra"
        });
		List<Zanr> zanrList = zanrDao.findAll();
       
        for (Zanr obj : zanrList) {
            tableModel.addRow(new Object[] {
                obj.getIdZanr(),
                obj.getNazivZanr()
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
		jmi0.setAction(new OpenKnjigaFormAction());
		jmi0.setText("Knjiga");
		jmi0.setEnabled(false);
		jpm.add(jmi0);
		
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ZanrSelectionListener());
	}
	
	

	@Override
	public void dodavanje() {
		
		PanelDetailZanr panelDetailDodavanje = new PanelDetailZanr(StanjeDijaloga.ADD);
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Zanr", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Zanr zanr= new Zanr();
	        zanr.setNazivZanr(panelDetailDodavanje.getNazivZanrField().getText());
	        zanr.setOpisZanr(panelDetailDodavanje.getOpisZanrField().getText());
			
			
			zanrDao.persist(zanr);
		 	tableModel.addRow(new Object[] {
            	zanr.getIdZanr(),
            	zanr.getNazivZanr()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(zanr.getIdZanr().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailZanr panel = new PanelDetailZanr(StanjeDijaloga.UPDATE);
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Zanr", panel);
			Zanr zanr = null;
			zanr = zanrDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdZanrField().setText(zanr.getIdZanr()+"");
			panel.getNazivZanrField().setText(zanr.getNazivZanr()+"");
			panel.getOpisZanrField().setText(zanr.getOpisZanr()+"");
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        zanr.setIdZanr(Integer.parseInt(panel.getIdZanrField().getText()));
		        zanr.setNazivZanr(panel.getNazivZanrField().getText());
		        zanr.setOpisZanr(panel.getOpisZanrField().getText());
				zanrDao.merge(zanr);
        		tableModel.setValueAt(zanr.getIdZanr(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(zanr.getNazivZanr(),table.getSelectedRow() , 1);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(zanr.getIdZanr().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete zanr?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Zanr zanr = zanrDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				zanrDao.remove(zanr);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class ZanrSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailZanr panel = (PanelDetailZanr)panelDetail;
					panel.getIdZanrField().setText("");
					panel.getNazivZanrField().setText("");
					panel.getOpisZanrField().setText("");
					jmi0.setText("Knjiga");
					jmi0.setEnabled(false);
	        		return;
	        	}
	        		
				Zanr zanr = zanrDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailZanr panel = (PanelDetailZanr)panelDetail;
				panel.getIdZanrField().setText(zanr.getIdZanr()+"");
				panel.getNazivZanrField().setText(zanr.getNazivZanr()+"");
				panel.getOpisZanrField().setText(zanr.getOpisZanr()+"");
			
				jmi0.setAction(new OpenKnjigaFormAction(zanr));
				jmi0.setText("Knjiga");
				jmi0.setEnabled(true);

	        }
		}
	}
	

}
