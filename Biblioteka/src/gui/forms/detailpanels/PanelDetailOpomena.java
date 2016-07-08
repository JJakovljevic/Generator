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

import dao.VrstaOpomeneDaoBean;
import bean.VrstaOpomene;
import dao.ZaduzenjeDaoBean;
import bean.Zaduzenje;

@SuppressWarnings("serial")
public class PanelDetailOpomena extends AbstractPanelDetail {
	
	private VrstaOpomeneDaoBean vrstaopomeneDao = new VrstaOpomeneDaoBean();
	private ZaduzenjeDaoBean zaduzenjeDao = new ZaduzenjeDaoBean();
	
	private JLabel lbloznakaOpomene;
	private JTextField oznakaOpomeneField;
	private JLabel lblnazivOpomene;
	private JTextField nazivOpomeneField;
	private JLabel lblopisOpomene;
	private JTextArea opisOpomeneField;
	private JLabel lblstatusOpomene;
	private JCheckBox statusOpomeneField;
	private JLabel lblvrstaopomene;
	private JComboBox vrstaopomeneField;
	private JLabel lblzaduzenje;
	private JComboBox zaduzenjeField;
	
	public PanelDetailOpomena(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panoznakaOpomene = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbloznakaOpomene = new JLabel("Oznaka opomene");
		lbloznakaOpomene.setPreferredSize(lblDimension);
		oznakaOpomeneField = new JTextField(6);
		oznakaOpomeneField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		oznakaOpomeneField.addKeyListener(new BojenjeKeyListener(oznakaOpomeneField));
		panoznakaOpomene.add(lbloznakaOpomene);
		panoznakaOpomene.add((Component)oznakaOpomeneField);
		 panoznakaOpomene.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panoznakaOpomene);
		
		JPanel pannazivOpomene = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblnazivOpomene = new JLabel("Naziv opomene");
		lblnazivOpomene.setPreferredSize(lblDimension);
		nazivOpomeneField = new JTextField(30);
		nazivOpomeneField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		nazivOpomeneField.addKeyListener(new BojenjeKeyListener(nazivOpomeneField));
		pannazivOpomene.add(lblnazivOpomene);
		pannazivOpomene.add((Component)nazivOpomeneField);
		
		boxCentar.add(pannazivOpomene);
		
		JPanel panopisOpomene = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblopisOpomene = new JLabel("Opis opomene");
		lblopisOpomene.setPreferredSize(lblDimension);
		opisOpomeneField = new JTextArea(5,20);
		opisOpomeneField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		opisOpomeneField.setLineWrap(true);
		JScrollPane jsp2 = new JScrollPane(opisOpomeneField);
		jsp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panopisOpomene.add(lblopisOpomene);
		panopisOpomene.add(jsp2);
		
		boxCentar.add(panopisOpomene);
		
		JPanel panstatusOpomene = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblstatusOpomene = new JLabel("Status opomene");
		lblstatusOpomene.setPreferredSize(lblDimension);
		statusOpomeneField = new JCheckBox();
		statusOpomeneField.setEnabled(mode != StanjeDijaloga.BROWSE  && true);
		panstatusOpomene.add(lblstatusOpomene);
		panstatusOpomene.add((Component)statusOpomeneField);
		
		boxCentar.add(panstatusOpomene);
		
		JPanel panvrstaopomene = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblvrstaopomene = new JLabel("vrstaopomene");
		lblvrstaopomene.setPreferredSize(lblDimension);
		vrstaopomeneField = new JComboBox();
		
	    List<VrstaOpomene> vrstaopomeneList = vrstaopomeneDao.findAll();     
	    for (VrstaOpomene obj : vrstaopomeneList) {
	    	vrstaopomeneField.addItem(obj);
	    }
		vrstaopomeneField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panvrstaopomene.add(lblvrstaopomene);
		panvrstaopomene.add((Component)vrstaopomeneField);
		
		boxCentar.add(panvrstaopomene);
		
		JPanel panzaduzenje = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblzaduzenje = new JLabel("zaduzenje");
		lblzaduzenje.setPreferredSize(lblDimension);
		zaduzenjeField = new JComboBox();
		
	    List<Zaduzenje> zaduzenjeList = zaduzenjeDao.findAll();     
	    for (Zaduzenje obj : zaduzenjeList) {
	    	zaduzenjeField.addItem(obj);
	    }
		zaduzenjeField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panzaduzenje.add(lblzaduzenje);
		panzaduzenje.add((Component)zaduzenjeField);
		
		boxCentar.add(panzaduzenje);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (nazivOpomeneField.getText().trim().equals("")) {
			nazivOpomeneField.setBackground(Color.RED);
			ok = false;
		}
		
		
		
		
		
	return ok;
	}
	public JTextField getOznakaOpomeneField() {
		return oznakaOpomeneField;
	}
	public JTextField getNazivOpomeneField() {
		return nazivOpomeneField;
	}
	public JTextArea getOpisOpomeneField() {
		return opisOpomeneField;
	}
	public JCheckBox getStatusOpomeneField() {
		return statusOpomeneField;
	}
	public JComboBox getVrstaopomeneField() {
		return vrstaopomeneField;
	}
	public JComboBox getZaduzenjeField() {
		return zaduzenjeField;
	}
	
}
