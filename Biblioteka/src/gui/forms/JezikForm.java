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
import gui.forms.detailpanels.PanelDetailJezik;
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

import bean.Jezik;

import dao.JezikDaoBean;




@SuppressWarnings("serial")
public class JezikForm extends AbstractForm {

	private JezikDaoBean jezikDao = new JezikDaoBean();
	JMenuItem jmi0;

	public JezikForm(JFrame parent) {
		super(parent);
		setTitle("Jezik");
		panelDetail = new PanelDetailJezik(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Id jezika",
           "Ime jezika"
        });
		List<Jezik> jezikList = jezikDao.findAll();
       
        for (Jezik obj : jezikList) {
            tableModel.addRow(new Object[] {
                obj.getIdJezik(),
                obj.getImeJezik()
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
		lsm.addListSelectionListener(new JezikSelectionListener());
	}
	
	

	@Override
	public void dodavanje() {
		
		PanelDetailJezik panelDetailDodavanje = new PanelDetailJezik(StanjeDijaloga.ADD);
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Jezik", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Jezik jezik= new Jezik();
	        jezik.setImeJezik(panelDetailDodavanje.getImeJezikField().getText());
			
			
			jezikDao.persist(jezik);
		 	tableModel.addRow(new Object[] {
            	jezik.getIdJezik(),
            	jezik.getImeJezik()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(jezik.getIdJezik().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailJezik panel = new PanelDetailJezik(StanjeDijaloga.UPDATE);
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Jezik", panel);
			Jezik jezik = null;
			jezik = jezikDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdJezikField().setText(jezik.getIdJezik()+"");
			panel.getImeJezikField().setText(jezik.getImeJezik()+"");
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        jezik.setIdJezik(Integer.parseInt(panel.getIdJezikField().getText()));
		        jezik.setImeJezik(panel.getImeJezikField().getText());
				jezikDao.merge(jezik);
        		tableModel.setValueAt(jezik.getIdJezik(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(jezik.getImeJezik(),table.getSelectedRow() , 1);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(jezik.getIdJezik().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete jezik?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Jezik jezik = jezikDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				jezikDao.remove(jezik);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class JezikSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailJezik panel = (PanelDetailJezik)panelDetail;
					panel.getIdJezikField().setText("");
					panel.getImeJezikField().setText("");
					jmi0.setText("Knjiga");
					jmi0.setEnabled(false);
	        		return;
	        	}
	        		
				Jezik jezik = jezikDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailJezik panel = (PanelDetailJezik)panelDetail;
				panel.getIdJezikField().setText(jezik.getIdJezik()+"");
				panel.getImeJezikField().setText(jezik.getImeJezik()+"");
			
				jmi0.setAction(new OpenKnjigaFormAction(jezik));
				jmi0.setText("Knjiga");
				jmi0.setEnabled(true);

	        }
		}
	}
	

}
