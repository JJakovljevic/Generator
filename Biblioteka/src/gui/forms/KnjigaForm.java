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
import gui.forms.detailpanels.PanelDetailKnjiga;
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

import bean.Knjiga;

import dao.KnjigaDaoBean;
import dao.ZanrDaoBean;
import bean.Zanr;
import dao.AutorDaoBean;
import bean.Autor;
import dao.JezikDaoBean;
import bean.Jezik;

import bean.Zanr;
import bean.Autor;
import bean.Jezik;



@SuppressWarnings("serial")
public class KnjigaForm extends AbstractForm {

	private KnjigaDaoBean knjigaDao = new KnjigaDaoBean();
	private Zanr zanr;
	private Autor autor;
	private Jezik jezik;
	JMenuItem jmi0;

	public KnjigaForm(JFrame parent) {
		super(parent);
		setTitle("Knjiga");
		panelDetail = new PanelDetailKnjiga(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Id knjige",
           "Naslov",
           "zanr",
           "autor",
           "jezik"
        });
		List<Knjiga> knjigaList = knjigaDao.findAll();
       
        for (Knjiga obj : knjigaList) {
            tableModel.addRow(new Object[] {
                obj.getIdKnjiga(),
                obj.getNaslov(),
                obj.getZanr(),
                obj.getAutor(),
                obj.getJezik()
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
		
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new KnjigaSelectionListener());
	}
	
	
	public KnjigaForm(JFrame parent,Zanr zanr) {
		super(parent);
		setTitle("Knjiga");
		panelDetail = new PanelDetailKnjiga(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Id knjige",
           "Naslov",
           "zanr",
           "autor",
           "jezik"
        });
		List<Knjiga> knjigaList = knjigaDao.findAll();
       	List<Knjiga> knjigaList1 = new ArrayList<>();
		if(zanr!=null){
			this.zanr = zanr;
			for(Knjiga knjiga :knjigaList){
				if( knjiga.getZanr().getIdZanr().equals(zanr.getIdZanr())){
					knjigaList1.add(knjiga);
				}
			}
		}else{
			knjigaList1 = knjigaList;
		}
        for (Knjiga obj : knjigaList1) {
            tableModel.addRow(new Object[] {
                obj.getIdKnjiga(),
                obj.getNaslov(),
                obj.getZanr(),
                obj.getAutor(),
                obj.getJezik()
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
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new KnjigaSelectionListener());
	}
	public KnjigaForm(JFrame parent,Autor autor) {
		super(parent);
		setTitle("Knjiga");
		panelDetail = new PanelDetailKnjiga(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Id knjige",
           "Naslov",
           "zanr",
           "autor",
           "jezik"
        });
		List<Knjiga> knjigaList = knjigaDao.findAll();
       	List<Knjiga> knjigaList1 = new ArrayList<>();
		if(autor!=null){
			this.autor = autor;
			for(Knjiga knjiga :knjigaList){
				if( knjiga.getAutor().getIdAutor().equals(autor.getIdAutor())){
					knjigaList1.add(knjiga);
				}
			}
		}else{
			knjigaList1 = knjigaList;
		}
        for (Knjiga obj : knjigaList1) {
            tableModel.addRow(new Object[] {
                obj.getIdKnjiga(),
                obj.getNaslov(),
                obj.getZanr(),
                obj.getAutor(),
                obj.getJezik()
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
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new KnjigaSelectionListener());
	}
	public KnjigaForm(JFrame parent,Jezik jezik) {
		super(parent);
		setTitle("Knjiga");
		panelDetail = new PanelDetailKnjiga(StanjeDijaloga.BROWSE);
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {
           "Id knjige",
           "Naslov",
           "zanr",
           "autor",
           "jezik"
        });
		List<Knjiga> knjigaList = knjigaDao.findAll();
       	List<Knjiga> knjigaList1 = new ArrayList<>();
		if(jezik!=null){
			this.jezik = jezik;
			for(Knjiga knjiga :knjigaList){
				if( knjiga.getJezik().getIdJezik().equals(jezik.getIdJezik())){
					knjigaList1.add(knjiga);
				}
			}
		}else{
			knjigaList1 = knjigaList;
		}
        for (Knjiga obj : knjigaList1) {
            tableModel.addRow(new Object[] {
                obj.getIdKnjiga(),
                obj.getNaslov(),
                obj.getZanr(),
                obj.getAutor(),
                obj.getJezik()
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
		btZoom.addMouseListener(new PopupListener(jpm));
		toolbar.add(btZoom);
	
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new KnjigaSelectionListener());
	}

	@Override
	public void dodavanje() {
		
		PanelDetailKnjiga panelDetailDodavanje = new PanelDetailKnjiga(StanjeDijaloga.ADD);
		if(zanr!=null){
			panelDetailDodavanje.getZanrField().removeAllItems();
			panelDetailDodavanje.getZanrField().addItem(zanr);
		}
		if(autor!=null){
			panelDetailDodavanje.getAutorField().removeAllItems();
			panelDetailDodavanje.getAutorField().addItem(autor);
		}
		if(jezik!=null){
			panelDetailDodavanje.getJezikField().removeAllItems();
			panelDetailDodavanje.getJezikField().addItem(jezik);
		}
		AddUpdateFindDialog addUpdateDialog = new AddUpdateFindDialog(this, 
  				"Add Knjiga", panelDetailDodavanje);
		addUpdateDialog.setVisible(true);
		
		if (addUpdateDialog.getClosingMode() == AddUpdateFindDialog.OK) {

			Knjiga knjiga= new Knjiga();
	        knjiga.setNaslov(panelDetailDodavanje.getNaslovField().getText());
	        knjiga.setZanr((Zanr)panelDetailDodavanje.getZanrField().getSelectedItem());
	        knjiga.setAutor((Autor)panelDetailDodavanje.getAutorField().getSelectedItem());
	        knjiga.setJezik((Jezik)panelDetailDodavanje.getJezikField().getSelectedItem());
			
			
			knjigaDao.persist(knjiga);
		 	tableModel.addRow(new Object[] {
            	knjiga.getIdKnjiga(),
            	knjiga.getNaslov(),
            	knjiga.getZanr(),
            	knjiga.getAutor(),
            	knjiga.getJezik()
			});
			tableModel.fireTableDataChanged();
			
			for(int i = 0; i < table.getRowCount(); i++){
			if(knjiga.getIdKnjiga().equals(tableModel.getValueAt(i, 0))){
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
			PanelDetailKnjiga panel = new PanelDetailKnjiga(StanjeDijaloga.UPDATE);
			if(zanr!=null){
				panel.getZanrField().removeAllItems();
				panel.getZanrField().addItem(zanr);
			}
			if(autor!=null){
				panel.getAutorField().removeAllItems();
				panel.getAutorField().addItem(autor);
			}
			if(jezik!=null){
				panel.getJezikField().removeAllItems();
				panel.getJezikField().addItem(jezik);
			}
			AddUpdateFindDialog dialog = new AddUpdateFindDialog(this, "Update Knjiga", panel);
			Knjiga knjiga = null;
			knjiga = knjigaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
			panel.getIdKnjigaField().setText(knjiga.getIdKnjiga()+"");
			panel.getNaslovField().setText(knjiga.getNaslov()+"");
			panel.getZanrField().setSelectedItem(knjiga.getZanr());
			panel.getAutorField().setSelectedItem(knjiga.getAutor());
			panel.getJezikField().setSelectedItem(knjiga.getJezik());
			dialog.setVisible(true);
			if(dialog.getClosingMode() == AddUpdateFindDialog.OK){
		        knjiga.setIdKnjiga(Integer.parseInt(panel.getIdKnjigaField().getText()));
		        knjiga.setNaslov(panel.getNaslovField().getText());
		        knjiga.setZanr((Zanr)panel.getZanrField().getSelectedItem());
		        knjiga.setAutor((Autor)panel.getAutorField().getSelectedItem());
		        knjiga.setJezik((Jezik)panel.getJezikField().getSelectedItem());
				knjigaDao.merge(knjiga);
        		tableModel.setValueAt(knjiga.getIdKnjiga(),table.getSelectedRow() , 0);
        		tableModel.setValueAt(knjiga.getNaslov(),table.getSelectedRow() , 1);
        		tableModel.setValueAt(knjiga.getZanr(),table.getSelectedRow() , 2);
        		tableModel.setValueAt(knjiga.getAutor(),table.getSelectedRow() , 3);
        		tableModel.setValueAt(knjiga.getJezik(),table.getSelectedRow() , 4);
				tableModel.fireTableDataChanged();
				
				for(int i = 0; i < table.getRowCount(); i++){
					if(knjiga.getIdKnjiga().equals(tableModel.getValueAt(i, 0))){
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
			int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da obrisete knjiga?",
					"", JOptionPane.YES_NO_OPTION);
			if(odgovor == JOptionPane.YES_OPTION){
				int row = table.getSelectedRow();
				boolean ok = true;
				
				Knjiga knjiga = knjigaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				knjigaDao.remove(knjiga);
				tableModel.removeRow(table.getSelectedRow());	
				tableModel.fireTableDataChanged();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Mora biti selektovan neki red u tabeli!!!");
		}
		
	}
	
	class KnjigaSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
	
	        if (!e.getValueIsAdjusting()) {
	        	int row = table.getSelectedRow();
	        		
	        	if (row == -1){ // ništa nije selektovano 
	        		PanelDetailKnjiga panel = (PanelDetailKnjiga)panelDetail;
					panel.getIdKnjigaField().setText("");
					panel.getNaslovField().setText("");
					panel.getZanrField().setSelectedItem(null);
					panel.getAutorField().setSelectedItem(null);
					panel.getJezikField().setSelectedItem(null);
					jmi0.setText("Knjige ogranka");
					jmi0.setEnabled(false);
	        		return;
	        	}
	        		
				Knjiga knjiga = knjigaDao.findById((Integer)(table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(0))));
				PanelDetailKnjiga panel = (PanelDetailKnjiga)panelDetail;
				panel.getIdKnjigaField().setText(knjiga.getIdKnjiga()+"");
				panel.getNaslovField().setText(knjiga.getNaslov()+"");
				panel.getZanrField().setSelectedItem(knjiga.getZanr());
				panel.getAutorField().setSelectedItem(knjiga.getAutor());
				panel.getJezikField().setSelectedItem(knjiga.getJezik());
			
				jmi0.setAction(new OpenKnjigaOgrankaFormAction(knjiga));
				jmi0.setText("Knjige ogranka");
				jmi0.setEnabled(true);

	        }
		}
	}
	

}
