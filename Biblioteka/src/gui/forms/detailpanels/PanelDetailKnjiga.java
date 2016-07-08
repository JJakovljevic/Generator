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

import dao.ZanrDaoBean;
import bean.Zanr;
import dao.AutorDaoBean;
import bean.Autor;
import dao.JezikDaoBean;
import bean.Jezik;

@SuppressWarnings("serial")
public class PanelDetailKnjiga extends AbstractPanelDetail {
	
	private ZanrDaoBean zanrDao = new ZanrDaoBean();
	private AutorDaoBean autorDao = new AutorDaoBean();
	private JezikDaoBean jezikDao = new JezikDaoBean();
	
	private JLabel lblidKnjiga;
	private JTextField idKnjigaField;
	private JLabel lblnaslov;
	private JTextField naslovField;
	private JLabel lblzanr;
	private JComboBox zanrField;
	private JLabel lblautor;
	private JComboBox autorField;
	private JLabel lbljezik;
	private JComboBox jezikField;
	
	public PanelDetailKnjiga(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidKnjiga = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidKnjiga = new JLabel("Id knjige");
		lblidKnjiga.setPreferredSize(lblDimension);
		idKnjigaField = new JTextField(6);
		idKnjigaField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idKnjigaField.addKeyListener(new BojenjeKeyListener(idKnjigaField));
		panidKnjiga.add(lblidKnjiga);
		panidKnjiga.add((Component)idKnjigaField);
		 panidKnjiga.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidKnjiga);
		
		JPanel pannaslov = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblnaslov = new JLabel("Naslov");
		lblnaslov.setPreferredSize(lblDimension);
		naslovField = new JTextField(100);
		naslovField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		naslovField.addKeyListener(new BojenjeKeyListener(naslovField));
		pannaslov.add(lblnaslov);
		pannaslov.add((Component)naslovField);
		
		boxCentar.add(pannaslov);
		
		JPanel panzanr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblzanr = new JLabel("zanr");
		lblzanr.setPreferredSize(lblDimension);
		zanrField = new JComboBox();
		
	    List<Zanr> zanrList = zanrDao.findAll();     
	    for (Zanr obj : zanrList) {
	    	zanrField.addItem(obj);
	    }
		zanrField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panzanr.add(lblzanr);
		panzanr.add((Component)zanrField);
		
		boxCentar.add(panzanr);
		
		JPanel panautor = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblautor = new JLabel("autor");
		lblautor.setPreferredSize(lblDimension);
		autorField = new JComboBox();
		
	    List<Autor> autorList = autorDao.findAll();     
	    for (Autor obj : autorList) {
	    	autorField.addItem(obj);
	    }
		autorField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panautor.add(lblautor);
		panautor.add((Component)autorField);
		
		boxCentar.add(panautor);
		
		JPanel panjezik = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbljezik = new JLabel("jezik");
		lbljezik.setPreferredSize(lblDimension);
		jezikField = new JComboBox();
		
	    List<Jezik> jezikList = jezikDao.findAll();     
	    for (Jezik obj : jezikList) {
	    	jezikField.addItem(obj);
	    }
		jezikField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panjezik.add(lbljezik);
		panjezik.add((Component)jezikField);
		
		boxCentar.add(panjezik);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (naslovField.getText().trim().equals("")) {
			naslovField.setBackground(Color.RED);
			ok = false;
		}
		
		
		
		
	return ok;
	}
	public JTextField getIdKnjigaField() {
		return idKnjigaField;
	}
	public JTextField getNaslovField() {
		return naslovField;
	}
	public JComboBox getZanrField() {
		return zanrField;
	}
	public JComboBox getAutorField() {
		return autorField;
	}
	public JComboBox getJezikField() {
		return jezikField;
	}
	
}
