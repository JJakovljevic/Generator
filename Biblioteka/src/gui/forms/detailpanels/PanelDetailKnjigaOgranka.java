/*********************************************************************/
/*          Generisano na osnovu templejta: panelDetail.ftl          */
/*********************************************************************/

package gui.forms.detailpanels;

import gui.forms.AbstractForm.StanjeDijaloga;
import gui.forms.detailpanels.akcije.BojenjeKeyListener;
import gui.forms.detailpanels.formatter.CustDateFormatter;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dao.OgranakDaoBean;
import bean.Ogranak;
import dao.KnjigaDaoBean;
import bean.Knjiga;

@SuppressWarnings("serial")
public class PanelDetailKnjigaOgranka extends AbstractPanelDetail {
	
	private OgranakDaoBean ogranakDao = new OgranakDaoBean();
	private KnjigaDaoBean knjigaDao = new KnjigaDaoBean();
	
	private JLabel lblidKnjigeOgranka;
	private JTextField idKnjigeOgrankaField;
	private JLabel lblogranak;
	private JComboBox ogranakField;
	private JLabel lblknjiga;
	private JComboBox knjigaField;
	
	public PanelDetailKnjigaOgranka(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidKnjigeOgranka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidKnjigeOgranka = new JLabel("ID knjige ogranka");
		lblidKnjigeOgranka.setPreferredSize(lblDimension);
		idKnjigeOgrankaField = new JTextField(6);
		idKnjigeOgrankaField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idKnjigeOgrankaField.addKeyListener(new BojenjeKeyListener(idKnjigeOgrankaField));
		panidKnjigeOgranka.add(lblidKnjigeOgranka);
		panidKnjigeOgranka.add((Component)idKnjigeOgrankaField);
		 panidKnjigeOgranka.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidKnjigeOgranka);
		
		JPanel panogranak = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblogranak = new JLabel("ogranak");
		lblogranak.setPreferredSize(lblDimension);
		ogranakField = new JComboBox();
		
	    List<Ogranak> ogranakList = ogranakDao.findAll();     
	    for (Ogranak obj : ogranakList) {
	    	ogranakField.addItem(obj);
	    }
		ogranakField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panogranak.add(lblogranak);
		panogranak.add((Component)ogranakField);
		
		boxCentar.add(panogranak);
		
		JPanel panknjiga = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblknjiga = new JLabel("knjiga");
		lblknjiga.setPreferredSize(lblDimension);
		knjigaField = new JComboBox();
		
	    List<Knjiga> knjigaList = knjigaDao.findAll();     
	    for (Knjiga obj : knjigaList) {
	    	knjigaField.addItem(obj);
	    }
		knjigaField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panknjiga.add(lblknjiga);
		panknjiga.add((Component)knjigaField);
		
		boxCentar.add(panknjiga);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		
		
	return ok;
	}
	public JTextField getIdKnjigeOgrankaField() {
		return idKnjigeOgrankaField;
	}
	public JComboBox getOgranakField() {
		return ogranakField;
	}
	public JComboBox getKnjigaField() {
		return knjigaField;
	}
	
}
