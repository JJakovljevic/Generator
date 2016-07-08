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
public class PanelDetailAutor extends AbstractPanelDetail {
	
	
	private JLabel lblidAutor;
	private JTextField idAutorField;
	private JLabel lblime;
	private JTextField imeField;
	private JLabel lblprezime;
	private JTextField prezimeField;
	private JLabel lblbiografija;
	private JTextArea biografijaField;
	
	public PanelDetailAutor(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidAutor = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidAutor = new JLabel("ID autora");
		lblidAutor.setPreferredSize(lblDimension);
		idAutorField = new JTextField(6);
		idAutorField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idAutorField.addKeyListener(new BojenjeKeyListener(idAutorField));
		panidAutor.add(lblidAutor);
		panidAutor.add((Component)idAutorField);
		 panidAutor.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidAutor);
		
		JPanel panime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblime = new JLabel("Ime autora");
		lblime.setPreferredSize(lblDimension);
		imeField = new JTextField(30);
		imeField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		imeField.addKeyListener(new BojenjeKeyListener(imeField));
		panime.add(lblime);
		panime.add((Component)imeField);
		
		boxCentar.add(panime);
		
		JPanel panprezime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblprezime = new JLabel("Prezime autora");
		lblprezime.setPreferredSize(lblDimension);
		prezimeField = new JTextField(35);
		prezimeField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		prezimeField.addKeyListener(new BojenjeKeyListener(prezimeField));
		panprezime.add(lblprezime);
		panprezime.add((Component)prezimeField);
		
		boxCentar.add(panprezime);
		
		JPanel panbiografija = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblbiografija = new JLabel("Biografija");
		lblbiografija.setPreferredSize(lblDimension);
		biografijaField = new JTextArea(5,20);
		biografijaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		biografijaField.setLineWrap(true);
		JScrollPane jsp3 = new JScrollPane(biografijaField);
		jsp3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panbiografija.add(lblbiografija);
		panbiografija.add(jsp3);
		
		boxCentar.add(panbiografija);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (imeField.getText().trim().equals("")) {
			imeField.setBackground(Color.RED);
			ok = false;
		}
		
		if (prezimeField.getText().trim().equals("")) {
			prezimeField.setBackground(Color.RED);
			ok = false;
		}
		
		
	return ok;
	}
	public JTextField getIdAutorField() {
		return idAutorField;
	}
	public JTextField getImeField() {
		return imeField;
	}
	public JTextField getPrezimeField() {
		return prezimeField;
	}
	public JTextArea getBiografijaField() {
		return biografijaField;
	}
	
}
