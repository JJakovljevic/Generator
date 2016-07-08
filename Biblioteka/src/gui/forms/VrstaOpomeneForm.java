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
import gui.forms.detailpanels.PanelDetailVrstaOpomene;
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

import bean.VrstaOpomene;

import dao.VrstaOpomeneDaoBean;




@SuppressWarnings("serial")
public class VrstaOpomeneForm extends AbstractForm {

	private VrstaOpomeneDaoBean vrstaopomeneDao = new VrstaOpomeneDaoBean();
	JMenuItem jmi0;

	public VrstaOpomeneForm(JFrame parent) {
		super(parent);
		setTitle("Vrsta opomene");
		panelDetail = new PanelDetailVrstaOpomene(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Vrsta opomene",
           "Naziv",
           "Kazna"
        });
		List<VrstaOpomene> vrstaopomeneList = vrstaopomeneDao.findAll();
       
        for (VrstaOpomene obj : vrstaopomeneList) {
            tableModel.addRow(new Object[] {
                obj.getIdVrste(),
                obj.getNazivVrsteOpomene(),
                obj.getKazna()
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
		lsm.addListSelectionListener(new VrstaOpomeneSelectionListener());
	}
	
	

	@Override
	public void dodavanje() {
		
		PanelDetailVrstaOpomene panelDetailDodavanje = new PanelDetailVrstaOpomene(StanjeDijaloga.ADD);
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Vrsta opomene", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			VrstaOpomene vrstaOpomene= new VrstaOpomene();
	        vrstaOpomene.setNazivVrsteOpomene(panelDetailDodavanje.getNazivVrsteOpomeneField().getText());
	        vrstaOpomene.setOpisVrsteOpomene(panelDetailDodavanje.getOpisVrsteOpomeneField().getText());
	        vrstaOpomene.setKazna(Integer.parseInt(panelDetailDodavanje.getKaznaField().getText()));
			
			
			vrstaopomeneDao.persist(vrstaOpomene);
		 	tableModel.addRow(new Object[] {
            	vrstaOpomene.getIdVrste(),
            	vrstaOpomene.getNazivVrsteOpomene(),
            	vrstaOpomene.getKazna()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(vrstaOpomene.getIdVrste().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailVrstaOpomene panel = new PanelDetailVrstaOpomene(StanjeDijaloga.UPDATE);
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update VrstaOpomene", panel);
			VrstaOpomene vrstaOpomene = null;
			vrstaOpomene = vrstaopomeneDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdVrsteField().setText(vrstaOpomene.getIdVrste()+"");
			panel.getNazivVrsteOpomeneField().setText(vrstaOpomene.getNazivVrsteOpomene()+"");
			panel.getOpisVrsteOpomeneField().setText(vrstaOpomene.getOpisVrsteOpomene()+"");
			panel.getKaznaField().setText(vrstaOpomene.getKazna()+"");
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        vrstaOpomene.setIdVrste(Integer.parseInt(panel.getIdVrsteField().getText()));
		        vrstaOpomene.setNazivVrsteOpomene(panel.getNazivVrsteOpomeneField().getText());
		        vrstaOpomene.setOpisVrsteOpomene(panel.getOpisVrsteOpomeneField().getText());
		        vrstaOpomene.setKazna(Integer.parseInt(panel.getKaznaField().getText()));
				vrstaopomeneDao.merge(vrstaOpomene);
        		tableModel.setValueAt(vrstaOpomene.getIdVrste(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(vrstaOpomene.getNazivVrsteOpomene(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(vrstaOpomene.getKazna(),table.getSelectedRow() , 2);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(vrstaOpomene.getIdVrste().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete vrstaopomene?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				VrstaOpomene vrstaOpomene = vrstaopomeneDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				vrstaopomeneDao.remove(vrstaOpomene);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class VrstaOpomeneSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailVrstaOpomene panel = (PanelDetailVrstaOpomene)panelDetail;
					panel.getIdVrsteField().setText("");
					panel.getNazivVrsteOpomeneField().setText("");
					panel.getOpisVrsteOpomeneField().setText("");
					panel.getKaznaField().setText("");
					jmi0.setText("Opomena");
					jmi0.setEnabled(false);
	        		return;
	        	}
	        		
				VrstaOpomene vrstaOpomene = vrstaopomeneDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailVrstaOpomene panel = (PanelDetailVrstaOpomene)panelDetail;
				panel.getIdVrsteField().setText(vrstaOpomene.getIdVrste()+"");
				panel.getNazivVrsteOpomeneField().setText(vrstaOpomene.getNazivVrsteOpomene()+"");
				panel.getOpisVrsteOpomeneField().setText(vrstaOpomene.getOpisVrsteOpomene()+"");
				panel.getKaznaField().setText(vrstaOpomene.getKazna()+"");
			
				jmi0.setAction(new OpenOpomenaFormAction(vrstaOpomene));
				jmi0.setText("Opomena");
				jmi0.setEnabled(true);

	        }
		}
	}
	

}
