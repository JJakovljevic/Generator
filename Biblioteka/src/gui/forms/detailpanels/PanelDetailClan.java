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

import enumeration.Pol;
import enumeration.Clanstvo;

@SuppressWarnings("serial")
public class PanelDetailClan extends AbstractPanelDetail {
	
	
	private JLabel lblbrClanskeKarte;
	private JTextField brClanskeKarteField;
	private JLabel lblimeClana;
	private JTextField imeClanaField;
	private JLabel lblprezimeClana;
	private JTextField prezimeClanaField;
	private JLabel lbljmbg;
	private JTextField jmbgField;
	private JLabel lbldatumRodjenja;
	private JDatePicker datumRodjenjaField;
	private JLabel lbladresaClana;
	private JTextField adresaClanaField;
	private JLabel lbltelefonClana;
	private JTextField telefonClanaField;
	private JLabel lblemailClana;
	private JTextField emailClanaField;
	private JLabel lblpolClana;
	private JComboBox polClanaField;
	private JLabel lblclanstvo;
	private JComboBox clanstvoField;
	private JLabel lbldatumUplate;
	private JDatePicker datumUplateField;
	
	public PanelDetailClan(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panbrClanskeKarte = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblbrClanskeKarte = new JLabel("Broj clanske karte");
		lblbrClanskeKarte.setPreferredSize(lblDimension);
		brClanskeKarteField = new JTextField(6);
		brClanskeKarteField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		brClanskeKarteField.addKeyListener(new BojenjeKeyListener(brClanskeKarteField));
		panbrClanskeKarte.add(lblbrClanskeKarte);
		panbrClanskeKarte.add((Component)brClanskeKarteField);
		 panbrClanskeKarte.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panbrClanskeKarte);
		
		JPanel panimeClana = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblimeClana = new JLabel("Ime clana");
		lblimeClana.setPreferredSize(lblDimension);
		imeClanaField = new JTextField(30);
		imeClanaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		imeClanaField.addKeyListener(new BojenjeKeyListener(imeClanaField));
		panimeClana.add(lblimeClana);
		panimeClana.add((Component)imeClanaField);
		
		boxCentar.add(panimeClana);
		
		JPanel panprezimeClana = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblprezimeClana = new JLabel("Prezime clana");
		lblprezimeClana.setPreferredSize(lblDimension);
		prezimeClanaField = new JTextField(35);
		prezimeClanaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		prezimeClanaField.addKeyListener(new BojenjeKeyListener(prezimeClanaField));
		panprezimeClana.add(lblprezimeClana);
		panprezimeClana.add((Component)prezimeClanaField);
		
		boxCentar.add(panprezimeClana);
		
		JPanel panjmbg = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbljmbg = new JLabel("JMBG");
		lbljmbg.setPreferredSize(lblDimension);
		jmbgField = new JTextField(13);
		jmbgField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		jmbgField.addKeyListener(new BojenjeKeyListener(jmbgField));
		panjmbg.add(lbljmbg);
		panjmbg.add((Component)jmbgField);
		
		boxCentar.add(panjmbg);
		
		JPanel pandatumRodjenja = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbldatumRodjenja = new JLabel("Datum rodjenja");
		lbldatumRodjenja.setPreferredSize(lblDimension);
		UtilDateModel model4 = new UtilDateModel();
		Properties p4 = new Properties();
		JDatePanelImpl jdp4 = new JDatePanelImpl(model4, p4);
		AbstractFormatter cf4 = new CustDateFormatter();
		datumRodjenjaField = new JDatePickerImpl(jdp4, cf4 );
		datumRodjenjaField.setTextEditable(mode != StanjeDijaloga.BROWSE  && true);
		datumRodjenjaField.getModel().setSelected(true);
		pandatumRodjenja.add(lbldatumRodjenja);
		pandatumRodjenja.add((Component)datumRodjenjaField);
		
		boxCentar.add(pandatumRodjenja);
		
		JPanel panadresaClana = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbladresaClana = new JLabel("Adresa clana");
		lbladresaClana.setPreferredSize(lblDimension);
		adresaClanaField = new JTextField(60);
		adresaClanaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		adresaClanaField.addKeyListener(new BojenjeKeyListener(adresaClanaField));
		panadresaClana.add(lbladresaClana);
		panadresaClana.add((Component)adresaClanaField);
		
		boxCentar.add(panadresaClana);
		
		JPanel pantelefonClana = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbltelefonClana = new JLabel("Telefon clana");
		lbltelefonClana.setPreferredSize(lblDimension);
		telefonClanaField = new JTextField(15);
		telefonClanaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		telefonClanaField.addKeyListener(new BojenjeKeyListener(telefonClanaField));
		pantelefonClana.add(lbltelefonClana);
		pantelefonClana.add((Component)telefonClanaField);
		
		boxCentar.add(pantelefonClana);
		
		JPanel panemailClana = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblemailClana = new JLabel("Email clana");
		lblemailClana.setPreferredSize(lblDimension);
		emailClanaField = new JTextField(30);
		emailClanaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		emailClanaField.addKeyListener(new BojenjeKeyListener(emailClanaField));
		panemailClana.add(lblemailClana);
		panemailClana.add((Component)emailClanaField);
		
		boxCentar.add(panemailClana);
		
		JPanel panpolClana = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblpolClana = new JLabel("Pol clana");
		lblpolClana.setPreferredSize(lblDimension);
		polClanaField = new JComboBox();
		
	    polClanaField.addItem(Pol.Musko);
	    polClanaField.addItem(Pol.Zensko);
		polClanaField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panpolClana.add(lblpolClana);
		panpolClana.add((Component)polClanaField);
		
		boxCentar.add(panpolClana);
		
		JPanel panclanstvo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblclanstvo = new JLabel("Clanstvo");
		lblclanstvo.setPreferredSize(lblDimension);
		clanstvoField = new JComboBox();
		
	    clanstvoField.addItem(Clanstvo.vazece);
	    clanstvoField.addItem(Clanstvo.suspendovan);
	    clanstvoField.addItem(Clanstvo.isteklo);
		clanstvoField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panclanstvo.add(lblclanstvo);
		panclanstvo.add((Component)clanstvoField);
		
		boxCentar.add(panclanstvo);
		
		JPanel pandatumUplate = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbldatumUplate = new JLabel("Datum uplate");
		lbldatumUplate.setPreferredSize(lblDimension);
		UtilDateModel model10 = new UtilDateModel();
		Properties p10 = new Properties();
		JDatePanelImpl jdp10 = new JDatePanelImpl(model10, p10);
		AbstractFormatter cf10 = new CustDateFormatter();
		datumUplateField = new JDatePickerImpl(jdp10, cf10 );
		datumUplateField.setTextEditable(mode != StanjeDijaloga.BROWSE  && true);
		datumUplateField.getModel().setSelected(true);
		pandatumUplate.add(lbldatumUplate);
		pandatumUplate.add((Component)datumUplateField);
		
		boxCentar.add(pandatumUplate);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (imeClanaField.getText().trim().equals("")) {
			imeClanaField.setBackground(Color.RED);
			ok = false;
		}
		
		if (prezimeClanaField.getText().trim().equals("")) {
			prezimeClanaField.setBackground(Color.RED);
			ok = false;
		}
		
		if (jmbgField.getText().trim().equals("")) {
			jmbgField.setBackground(Color.RED);
			ok = false;
		}
		
		
		if (adresaClanaField.getText().trim().equals("")) {
			adresaClanaField.setBackground(Color.RED);
			ok = false;
		}
		
		if (telefonClanaField.getText().trim().equals("")) {
			telefonClanaField.setBackground(Color.RED);
			ok = false;
		}
		
		if (emailClanaField.getText().trim().equals("")) {
			emailClanaField.setBackground(Color.RED);
			ok = false;
		}
		
		
		
		
	return ok;
	}
	public JTextField getBrClanskeKarteField() {
		return brClanskeKarteField;
	}
	public JTextField getImeClanaField() {
		return imeClanaField;
	}
	public JTextField getPrezimeClanaField() {
		return prezimeClanaField;
	}
	public JTextField getJmbgField() {
		return jmbgField;
	}
	public JDatePicker getDatumRodjenjaField() {
		return datumRodjenjaField;
	}
	public JTextField getAdresaClanaField() {
		return adresaClanaField;
	}
	public JTextField getTelefonClanaField() {
		return telefonClanaField;
	}
	public JTextField getEmailClanaField() {
		return emailClanaField;
	}
	public JComboBox getPolClanaField() {
		return polClanaField;
	}
	public JComboBox getClanstvoField() {
		return clanstvoField;
	}
	public JDatePicker getDatumUplateField() {
		return datumUplateField;
	}
	
}
