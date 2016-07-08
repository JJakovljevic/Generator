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
import gui.forms.detailpanels.PanelDetailAutor;
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

import bean.Autor;

import dao.AutorDaoBean;




@SuppressWarnings("serial")
public class AutorForm extends AbstractForm {

	private AutorDaoBean autorDao = new AutorDaoBean();
	JMenuItem jmi0;

	public AutorForm(JFrame parent) {
		super(parent);
		setTitle("Autor");
		panelDetail = new PanelDetailAutor(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "ID autora",
           "Ime autora",
           "Prezime autora"
        });
		List<Autor> autorList = autorDao.findAll();
       
        for (Autor obj : autorList) {
            tableModel.addRow(new Object[] {
                obj.getIdAutor(),
                obj.getIme(),
                obj.getPrezime()
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
		lsm.addListSelectionListener(new AutorSelectionListener());
	}
	
	

	@Override
	public void dodavanje() {
		
		PanelDetailAutor panelDetailDodavanje = new PanelDetailAutor(StanjeDijaloga.ADD);
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Autor", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Autor autor= new Autor();
	        autor.setIme(panelDetailDodavanje.getImeField().getText());
	        autor.setPrezime(panelDetailDodavanje.getPrezimeField().getText());
	        autor.setBiografija(panelDetailDodavanje.getBiografijaField().getText());
			
			
			autorDao.persist(autor);
		 	tableModel.addRow(new Object[] {
            	autor.getIdAutor(),
            	autor.getIme(),
            	autor.getPrezime()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(autor.getIdAutor().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailAutor panel = new PanelDetailAutor(StanjeDijaloga.UPDATE);
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Autor", panel);
			Autor autor = null;
			autor = autorDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdAutorField().setText(autor.getIdAutor()+"");
			panel.getImeField().setText(autor.getIme()+"");
			panel.getPrezimeField().setText(autor.getPrezime()+"");
			panel.getBiografijaField().setText(autor.getBiografija()+"");
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        autor.setIdAutor(Integer.parseInt(panel.getIdAutorField().getText()));
		        autor.setIme(panel.getImeField().getText());
		        autor.setPrezime(panel.getPrezimeField().getText());
		        autor.setBiografija(panel.getBiografijaField().getText());
				autorDao.merge(autor);
        		tableModel.setValueAt(autor.getIdAutor(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(autor.getIme(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(autor.getPrezime(),table.getSelectedRow() , 2);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(autor.getIdAutor().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete autor?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Autor autor = autorDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				autorDao.remove(autor);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class AutorSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailAutor panel = (PanelDetailAutor)panelDetail;
					panel.getIdAutorField().setText("");
					panel.getImeField().setText("");
					panel.getPrezimeField().setText("");
					panel.getBiografijaField().setText("");
					jmi0.setText("Knjiga");
					jmi0.setEnabled(false);
	        		return;
	        	}
	        		
				Autor autor = autorDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailAutor panel = (PanelDetailAutor)panelDetail;
				panel.getIdAutorField().setText(autor.getIdAutor()+"");
				panel.getImeField().setText(autor.getIme()+"");
				panel.getPrezimeField().setText(autor.getPrezime()+"");
				panel.getBiografijaField().setText(autor.getBiografija()+"");
			
				jmi0.setAction(new OpenKnjigaFormAction(autor));
				jmi0.setText("Knjiga");
				jmi0.setEnabled(true);

	        }
		}
	}
	

}
