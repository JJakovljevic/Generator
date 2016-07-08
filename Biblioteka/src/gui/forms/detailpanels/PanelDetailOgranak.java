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


@SuppressWarnings("serial")
public class PanelDetailOgranak extends AbstractPanelDetail {
	
	
	private JLabel lbloznakaOgranka;
	private JTextField oznakaOgrankaField;
	private JLabel lblimeOgranka;
	private JTextField imeOgrankaField;
	private JLabel lbladresaOgranka;
	private JTextField adresaOgrankaField;
	private JLabel lbltelefonOgranka;
	private JTextField telefonOgrankaField;
	
	public PanelDetailOgranak(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panoznakaOgranka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbloznakaOgranka = new JLabel("Oznaka ogranka");
		lbloznakaOgranka.setPreferredSize(lblDimension);
		oznakaOgrankaField = new JTextField(6);
		oznakaOgrankaField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		oznakaOgrankaField.addKeyListener(new BojenjeKeyListener(oznakaOgrankaField));
		panoznakaOgranka.add(lbloznakaOgranka);
		panoznakaOgranka.add((Component)oznakaOgrankaField);
		 panoznakaOgranka.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panoznakaOgranka);
		
		JPanel panimeOgranka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblimeOgranka = new JLabel("Ime ogranka");
		lblimeOgranka.setPreferredSize(lblDimension);
		imeOgrankaField = new JTextField(60);
		imeOgrankaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		imeOgrankaField.addKeyListener(new BojenjeKeyListener(imeOgrankaField));
		panimeOgranka.add(lblimeOgranka);
		panimeOgranka.add((Component)imeOgrankaField);
		
		boxCentar.add(panimeOgranka);
		
		JPanel panadresaOgranka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbladresaOgranka = new JLabel("Adresa ogranka");
		lbladresaOgranka.setPreferredSize(lblDimension);
		adresaOgrankaField = new JTextField(60);
		adresaOgrankaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		adresaOgrankaField.addKeyListener(new BojenjeKeyListener(adresaOgrankaField));
		panadresaOgranka.add(lbladresaOgranka);
		panadresaOgranka.add((Component)adresaOgrankaField);
		
		boxCentar.add(panadresaOgranka);
		
		JPanel pantelefonOgranka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbltelefonOgranka = new JLabel("Telefon ogranka");
		lbltelefonOgranka.setPreferredSize(lblDimension);
		telefonOgrankaField = new JTextField(15);
		telefonOgrankaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		telefonOgrankaField.addKeyListener(new BojenjeKeyListener(telefonOgrankaField));
		pantelefonOgranka.add(lbltelefonOgranka);
		pantelefonOgranka.add((Component)telefonOgrankaField);
		
		boxCentar.add(pantelefonOgranka);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (imeOgrankaField.getText().trim().equals("")) {
			imeOgrankaField.setBackground(Color.RED);
			ok = false;
		}
		
		if (adresaOgrankaField.getText().trim().equals("")) {
			adresaOgrankaField.setBackground(Color.RED);
			ok = false;
		}
		
		
	return ok;
	}
	public JTextField getOznakaOgrankaField() {
		return oznakaOgrankaField;
	}
	public JTextField getImeOgrankaField() {
		return imeOgrankaField;
	}
	public JTextField getAdresaOgrankaField() {
		return adresaOgrankaField;
	}
	public JTextField getTelefonOgrankaField() {
		return telefonOgrankaField;
	}
	
}
