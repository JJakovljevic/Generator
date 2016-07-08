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
import enumeration.Pol;

@SuppressWarnings("serial")
public class PanelDetailBibliotekar extends AbstractPanelDetail {
	
	private OgranakDaoBean ogranakDao = new OgranakDaoBean();
	
	private JLabel lblmaticniBrojBibliotekara;
	private JTextField maticniBrojBibliotekaraField;
	private JLabel lblogranak;
	private JComboBox ogranakField;
	private JLabel lblkorisnickoIme;
	private JTextField korisnickoImeField;
	private JLabel lblimeZaposlenog;
	private JTextField imeZaposlenogField;
	private JLabel lblprezimeZaposlenog;
	private JTextField prezimeZaposlenogField;
	private JLabel lbllozinka;
	private JTextField lozinkaField;
	private JLabel lblpol;
	private JComboBox polField;
	
	public PanelDetailBibliotekar(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panmaticniBrojBibliotekara = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblmaticniBrojBibliotekara = new JLabel("Maticni broj bibliotekara");
		lblmaticniBrojBibliotekara.setPreferredSize(lblDimension);
		maticniBrojBibliotekaraField = new JTextField(10);
		maticniBrojBibliotekaraField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		maticniBrojBibliotekaraField.addKeyListener(new BojenjeKeyListener(maticniBrojBibliotekaraField));
		panmaticniBrojBibliotekara.add(lblmaticniBrojBibliotekara);
		panmaticniBrojBibliotekara.add((Component)maticniBrojBibliotekaraField);
		 panmaticniBrojBibliotekara.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panmaticniBrojBibliotekara);
		
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
		
		JPanel pankorisnickoIme = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblkorisnickoIme = new JLabel("Korisnicko ime");
		lblkorisnickoIme.setPreferredSize(lblDimension);
		korisnickoImeField = new JTextField(30);
		korisnickoImeField.setEnabled(mode != StanjeDijaloga.BROWSE   && false);
		korisnickoImeField.addKeyListener(new BojenjeKeyListener(korisnickoImeField));
		pankorisnickoIme.add(lblkorisnickoIme);
		pankorisnickoIme.add((Component)korisnickoImeField);
		
		boxCentar.add(pankorisnickoIme);
		
		JPanel panimeZaposlenog = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblimeZaposlenog = new JLabel("Ime zaposlenog");
		lblimeZaposlenog.setPreferredSize(lblDimension);
		imeZaposlenogField = new JTextField(30);
		imeZaposlenogField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		imeZaposlenogField.addKeyListener(new BojenjeKeyListener(imeZaposlenogField));
		panimeZaposlenog.add(lblimeZaposlenog);
		panimeZaposlenog.add((Component)imeZaposlenogField);
		
		boxCentar.add(panimeZaposlenog);
		
		JPanel panprezimeZaposlenog = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblprezimeZaposlenog = new JLabel("Prezime zaposlenog");
		lblprezimeZaposlenog.setPreferredSize(lblDimension);
		prezimeZaposlenogField = new JTextField(35);
		prezimeZaposlenogField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		prezimeZaposlenogField.addKeyListener(new BojenjeKeyListener(prezimeZaposlenogField));
		panprezimeZaposlenog.add(lblprezimeZaposlenog);
		panprezimeZaposlenog.add((Component)prezimeZaposlenogField);
		
		boxCentar.add(panprezimeZaposlenog);
		
		JPanel panlozinka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbllozinka = new JLabel("Lozinka");
		lbllozinka.setPreferredSize(lblDimension);
		lozinkaField = new JTextField(15);
		lozinkaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		lozinkaField.addKeyListener(new BojenjeKeyListener(lozinkaField));
		panlozinka.add(lbllozinka);
		panlozinka.add((Component)lozinkaField);
		
		boxCentar.add(panlozinka);
		
		JPanel panpol = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblpol = new JLabel("Pol");
		lblpol.setPreferredSize(lblDimension);
		polField = new JComboBox();
		
	    polField.addItem(Pol.Musko);
	    polField.addItem(Pol.Zensko);
		polField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panpol.add(lblpol);
		panpol.add((Component)polField);
		
		boxCentar.add(panpol);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		
		if (korisnickoImeField.getText().trim().equals("")) {
			korisnickoImeField.setBackground(Color.RED);
			ok = false;
		}
		
		if (imeZaposlenogField.getText().trim().equals("")) {
			imeZaposlenogField.setBackground(Color.RED);
			ok = false;
		}
		
		if (prezimeZaposlenogField.getText().trim().equals("")) {
			prezimeZaposlenogField.setBackground(Color.RED);
			ok = false;
		}
		
		if (lozinkaField.getText().trim().equals("")) {
			lozinkaField.setBackground(Color.RED);
			ok = false;
		}
		
		
	return ok;
	}
	public JTextField getMaticniBrojBibliotekaraField() {
		return maticniBrojBibliotekaraField;
	}
	public JComboBox getOgranakField() {
		return ogranakField;
	}
	public JTextField getKorisnickoImeField() {
		return korisnickoImeField;
	}
	public JTextField getImeZaposlenogField() {
		return imeZaposlenogField;
	}
	public JTextField getPrezimeZaposlenogField() {
		return prezimeZaposlenogField;
	}
	public JTextField getLozinkaField() {
		return lozinkaField;
	}
	public JComboBox getPolField() {
		return polField;
	}
	
}
