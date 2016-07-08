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

import dao.PrimerakDaoBean;
import bean.Primerak;
import dao.ClanDaoBean;
import bean.Clan;

@SuppressWarnings("serial")
public class PanelDetailZaduzenje extends AbstractPanelDetail {
	
	private PrimerakDaoBean primerakDao = new PrimerakDaoBean();
	private ClanDaoBean clanDao = new ClanDaoBean();
	
	private JLabel lblidZaduzenja;
	private JTextField idZaduzenjaField;
	private JLabel lbldatumIznajmljivanja;
	private JDatePicker datumIznajmljivanjaField;
	private JLabel lbldatumVracanjaMax;
	private JDatePicker datumVracanjaMaxField;
	private JLabel lbldatumVracanja;
	private JDatePicker datumVracanjaField;
	private JLabel lblprimerak;
	private JComboBox primerakField;
	private JLabel lblclan;
	private JComboBox clanField;
	
	public PanelDetailZaduzenje(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidZaduzenja = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidZaduzenja = new JLabel("idZaduzenja");
		lblidZaduzenja.setPreferredSize(lblDimension);
		idZaduzenjaField = new JTextField(6);
		idZaduzenjaField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idZaduzenjaField.addKeyListener(new BojenjeKeyListener(idZaduzenjaField));
		panidZaduzenja.add(lblidZaduzenja);
		panidZaduzenja.add((Component)idZaduzenjaField);
		 panidZaduzenja.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidZaduzenja);
		
		JPanel pandatumIznajmljivanja = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbldatumIznajmljivanja = new JLabel("datumIznajmljivanja");
		lbldatumIznajmljivanja.setPreferredSize(lblDimension);
		UtilDateModel model1 = new UtilDateModel();
		Properties p1 = new Properties();
		JDatePanelImpl jdp1 = new JDatePanelImpl(model1, p1);
		AbstractFormatter cf1 = new CustDateFormatter();
		datumIznajmljivanjaField = new JDatePickerImpl(jdp1, cf1 );
		datumIznajmljivanjaField.setTextEditable(mode != StanjeDijaloga.BROWSE  && true);
		datumIznajmljivanjaField.getModel().setSelected(true);
		pandatumIznajmljivanja.add(lbldatumIznajmljivanja);
		pandatumIznajmljivanja.add((Component)datumIznajmljivanjaField);
		
		boxCentar.add(pandatumIznajmljivanja);
		
		JPanel pandatumVracanjaMax = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbldatumVracanjaMax = new JLabel("datumVracanjaMax");
		lbldatumVracanjaMax.setPreferredSize(lblDimension);
		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		JDatePanelImpl jdp2 = new JDatePanelImpl(model2, p2);
		AbstractFormatter cf2 = new CustDateFormatter();
		datumVracanjaMaxField = new JDatePickerImpl(jdp2, cf2 );
		datumVracanjaMaxField.setTextEditable(mode != StanjeDijaloga.BROWSE  && true);
		datumVracanjaMaxField.getModel().setSelected(true);
		pandatumVracanjaMax.add(lbldatumVracanjaMax);
		pandatumVracanjaMax.add((Component)datumVracanjaMaxField);
		
		boxCentar.add(pandatumVracanjaMax);
		
		JPanel pandatumVracanja = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbldatumVracanja = new JLabel("datumVracanja");
		lbldatumVracanja.setPreferredSize(lblDimension);
		UtilDateModel model3 = new UtilDateModel();
		Properties p3 = new Properties();
		JDatePanelImpl jdp3 = new JDatePanelImpl(model3, p3);
		AbstractFormatter cf3 = new CustDateFormatter();
		datumVracanjaField = new JDatePickerImpl(jdp3, cf3 );
		datumVracanjaField.setTextEditable(mode != StanjeDijaloga.BROWSE  && true);
		datumVracanjaField.getModel().setSelected(true);
		pandatumVracanja.add(lbldatumVracanja);
		pandatumVracanja.add((Component)datumVracanjaField);
		
		boxCentar.add(pandatumVracanja);
		
		JPanel panprimerak = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblprimerak = new JLabel("primerak");
		lblprimerak.setPreferredSize(lblDimension);
		primerakField = new JComboBox();
		
	    List<Primerak> primerakList = primerakDao.findAll();     
	    for (Primerak obj : primerakList) {
	    	primerakField.addItem(obj);
	    }
		primerakField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panprimerak.add(lblprimerak);
		panprimerak.add((Component)primerakField);
		
		boxCentar.add(panprimerak);
		
		JPanel panclan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblclan = new JLabel("clan");
		lblclan.setPreferredSize(lblDimension);
		clanField = new JComboBox();
		
	    List<Clan> clanList = clanDao.findAll();     
	    for (Clan obj : clanList) {
	    	clanField.addItem(obj);
	    }
		clanField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panclan.add(lblclan);
		panclan.add((Component)clanField);
		
		boxCentar.add(panclan);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		
		
		
		
		
	return ok;
	}
	public JTextField getIdZaduzenjaField() {
		return idZaduzenjaField;
	}
	public JDatePicker getDatumIznajmljivanjaField() {
		return datumIznajmljivanjaField;
	}
	public JDatePicker getDatumVracanjaMaxField() {
		return datumVracanjaMaxField;
	}
	public JDatePicker getDatumVracanjaField() {
		return datumVracanjaField;
	}
	public JComboBox getPrimerakField() {
		return primerakField;
	}
	public JComboBox getClanField() {
		return clanField;
	}
	
}
