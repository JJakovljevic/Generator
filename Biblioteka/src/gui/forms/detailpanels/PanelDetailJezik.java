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
public class PanelDetailJezik extends AbstractPanelDetail {
	
	
	private JLabel lblidJezik;
	private JTextField idJezikField;
	private JLabel lblimeJezik;
	private JTextField imeJezikField;
	
	public PanelDetailJezik(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidJezik = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidJezik = new JLabel("Id jezika");
		lblidJezik.setPreferredSize(lblDimension);
		idJezikField = new JTextField(6);
		idJezikField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idJezikField.addKeyListener(new BojenjeKeyListener(idJezikField));
		panidJezik.add(lblidJezik);
		panidJezik.add((Component)idJezikField);
		 panidJezik.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidJezik);
		
		JPanel panimeJezik = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblimeJezik = new JLabel("Ime jezika");
		lblimeJezik.setPreferredSize(lblDimension);
		imeJezikField = new JTextField(30);
		imeJezikField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		imeJezikField.addKeyListener(new BojenjeKeyListener(imeJezikField));
		panimeJezik.add(lblimeJezik);
		panimeJezik.add((Component)imeJezikField);
		
		boxCentar.add(panimeJezik);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (imeJezikField.getText().trim().equals("")) {
			imeJezikField.setBackground(Color.RED);
			ok = false;
		}
		
	return ok;
	}
	public JTextField getIdJezikField() {
		return idJezikField;
	}
	public JTextField getImeJezikField() {
		return imeJezikField;
	}
	
}
