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
public class PanelDetailIzdavac extends AbstractPanelDetail {
	
	
	private JLabel lblidIzdavaca;
	private JTextField idIzdavacaField;
	private JLabel lblnazivIzdavaca;
	private JTextField nazivIzdavacaField;
	
	public PanelDetailIzdavac(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidIzdavaca = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidIzdavaca = new JLabel("ID izdavaca");
		lblidIzdavaca.setPreferredSize(lblDimension);
		idIzdavacaField = new JTextField(6);
		idIzdavacaField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idIzdavacaField.addKeyListener(new BojenjeKeyListener(idIzdavacaField));
		panidIzdavaca.add(lblidIzdavaca);
		panidIzdavaca.add((Component)idIzdavacaField);
		 panidIzdavaca.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidIzdavaca);
		
		JPanel pannazivIzdavaca = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblnazivIzdavaca = new JLabel("Naziv izdavaca");
		lblnazivIzdavaca.setPreferredSize(lblDimension);
		nazivIzdavacaField = new JTextField(30);
		nazivIzdavacaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		nazivIzdavacaField.addKeyListener(new BojenjeKeyListener(nazivIzdavacaField));
		pannazivIzdavaca.add(lblnazivIzdavaca);
		pannazivIzdavaca.add((Component)nazivIzdavacaField);
		
		boxCentar.add(pannazivIzdavaca);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (nazivIzdavacaField.getText().trim().equals("")) {
			nazivIzdavacaField.setBackground(Color.RED);
			ok = false;
		}
		
	return ok;
	}
	public JTextField getIdIzdavacaField() {
		return idIzdavacaField;
	}
	public JTextField getNazivIzdavacaField() {
		return nazivIzdavacaField;
	}
	
}
