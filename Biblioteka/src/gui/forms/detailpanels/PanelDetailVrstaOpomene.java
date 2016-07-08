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
public class PanelDetailVrstaOpomene extends AbstractPanelDetail {
	
	
	private JLabel lblidVrste;
	private JTextField idVrsteField;
	private JLabel lblnazivVrsteOpomene;
	private JTextField nazivVrsteOpomeneField;
	private JLabel lblopisVrsteOpomene;
	private JTextArea opisVrsteOpomeneField;
	private JLabel lblkazna;
	private JTextField kaznaField;
	
	public PanelDetailVrstaOpomene(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidVrste = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidVrste = new JLabel("Vrsta opomene");
		lblidVrste.setPreferredSize(lblDimension);
		idVrsteField = new JTextField(6);
		idVrsteField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idVrsteField.addKeyListener(new BojenjeKeyListener(idVrsteField));
		panidVrste.add(lblidVrste);
		panidVrste.add((Component)idVrsteField);
		 panidVrste.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidVrste);
		
		JPanel pannazivVrsteOpomene = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblnazivVrsteOpomene = new JLabel("Naziv");
		lblnazivVrsteOpomene.setPreferredSize(lblDimension);
		nazivVrsteOpomeneField = new JTextField(20);
		nazivVrsteOpomeneField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		nazivVrsteOpomeneField.addKeyListener(new BojenjeKeyListener(nazivVrsteOpomeneField));
		pannazivVrsteOpomene.add(lblnazivVrsteOpomene);
		pannazivVrsteOpomene.add((Component)nazivVrsteOpomeneField);
		
		boxCentar.add(pannazivVrsteOpomene);
		
		JPanel panopisVrsteOpomene = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblopisVrsteOpomene = new JLabel("Opis");
		lblopisVrsteOpomene.setPreferredSize(lblDimension);
		opisVrsteOpomeneField = new JTextArea(5,20);
		opisVrsteOpomeneField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		opisVrsteOpomeneField.setLineWrap(true);
		JScrollPane jsp2 = new JScrollPane(opisVrsteOpomeneField);
		jsp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panopisVrsteOpomene.add(lblopisVrsteOpomene);
		panopisVrsteOpomene.add(jsp2);
		
		boxCentar.add(panopisVrsteOpomene);
		
		JPanel pankazna = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblkazna = new JLabel("Kazna");
		lblkazna.setPreferredSize(lblDimension);
		kaznaField = new JTextField(6);
		kaznaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		kaznaField.addKeyListener(new BojenjeKeyListener(kaznaField));
		pankazna.add(lblkazna);
		pankazna.add((Component)kaznaField);
		
		boxCentar.add(pankazna);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (nazivVrsteOpomeneField.getText().trim().equals("")) {
			nazivVrsteOpomeneField.setBackground(Color.RED);
			ok = false;
		}
		
		
		if (kaznaField.getText().trim().equals("")) {
			kaznaField.setBackground(Color.RED);
			ok = false;
		}
		
	return ok;
	}
	public JTextField getIdVrsteField() {
		return idVrsteField;
	}
	public JTextField getNazivVrsteOpomeneField() {
		return nazivVrsteOpomeneField;
	}
	public JTextArea getOpisVrsteOpomeneField() {
		return opisVrsteOpomeneField;
	}
	public JTextField getKaznaField() {
		return kaznaField;
	}
	
}
