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
public class PanelDetailZanr extends AbstractPanelDetail {
	
	
	private JLabel lblidZanr;
	private JTextField idZanrField;
	private JLabel lblnazivZanr;
	private JTextField nazivZanrField;
	private JLabel lblopisZanr;
	private JTextArea opisZanrField;
	
	public PanelDetailZanr(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidZanr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidZanr = new JLabel("Id zanra");
		lblidZanr.setPreferredSize(lblDimension);
		idZanrField = new JTextField(6);
		idZanrField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idZanrField.addKeyListener(new BojenjeKeyListener(idZanrField));
		panidZanr.add(lblidZanr);
		panidZanr.add((Component)idZanrField);
		 panidZanr.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidZanr);
		
		JPanel pannazivZanr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblnazivZanr = new JLabel("Naziv zanra");
		lblnazivZanr.setPreferredSize(lblDimension);
		nazivZanrField = new JTextField(20);
		nazivZanrField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		nazivZanrField.addKeyListener(new BojenjeKeyListener(nazivZanrField));
		pannazivZanr.add(lblnazivZanr);
		pannazivZanr.add((Component)nazivZanrField);
		
		boxCentar.add(pannazivZanr);
		
		JPanel panopisZanr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblopisZanr = new JLabel("Opis zanra");
		lblopisZanr.setPreferredSize(lblDimension);
		opisZanrField = new JTextArea(5,20);
		opisZanrField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		opisZanrField.setLineWrap(true);
		JScrollPane jsp2 = new JScrollPane(opisZanrField);
		jsp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panopisZanr.add(lblopisZanr);
		panopisZanr.add(jsp2);
		
		boxCentar.add(panopisZanr);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (nazivZanrField.getText().trim().equals("")) {
			nazivZanrField.setBackground(Color.RED);
			ok = false;
		}
		
		
	return ok;
	}
	public JTextField getIdZanrField() {
		return idZanrField;
	}
	public JTextField getNazivZanrField() {
		return nazivZanrField;
	}
	public JTextArea getOpisZanrField() {
		return opisZanrField;
	}
	
}
