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

import dao.ClanDaoBean;
import bean.Clan;
import dao.KnjigaOgrankaDaoBean;
import bean.KnjigaOgranka;

@SuppressWarnings("serial")
public class PanelDetailRezervacija extends AbstractPanelDetail {
	
	private ClanDaoBean clanDao = new ClanDaoBean();
	private KnjigaOgrankaDaoBean knjigaogrankaDao = new KnjigaOgrankaDaoBean();
	
	private JLabel lblidRezervacije;
	private JTextField idRezervacijeField;
	private JLabel lbldatumRezervacije;
	private JDatePicker datumRezervacijeField;
	private JLabel lblstatusRezervacije;
	private JCheckBox statusRezervacijeField;
	private JLabel lblclan;
	private JComboBox clanField;
	private JLabel lblknjigaogranka;
	private JComboBox knjigaogrankaField;
	
	public PanelDetailRezervacija(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidRezervacije = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidRezervacije = new JLabel("ID rezervacije");
		lblidRezervacije.setPreferredSize(lblDimension);
		idRezervacijeField = new JTextField(6);
		idRezervacijeField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idRezervacijeField.addKeyListener(new BojenjeKeyListener(idRezervacijeField));
		panidRezervacije.add(lblidRezervacije);
		panidRezervacije.add((Component)idRezervacijeField);
		 panidRezervacije.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidRezervacije);
		
		JPanel pandatumRezervacije = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbldatumRezervacije = new JLabel("Datum rezervacije");
		lbldatumRezervacije.setPreferredSize(lblDimension);
		UtilDateModel model1 = new UtilDateModel();
		Properties p1 = new Properties();
		JDatePanelImpl jdp1 = new JDatePanelImpl(model1, p1);
		AbstractFormatter cf1 = new CustDateFormatter();
		datumRezervacijeField = new JDatePickerImpl(jdp1, cf1 );
		datumRezervacijeField.setTextEditable(mode != StanjeDijaloga.BROWSE  && true);
		datumRezervacijeField.getModel().setSelected(true);
		pandatumRezervacije.add(lbldatumRezervacije);
		pandatumRezervacije.add((Component)datumRezervacijeField);
		
		boxCentar.add(pandatumRezervacije);
		
		JPanel panstatusRezervacije = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblstatusRezervacije = new JLabel("Status rezervacije");
		lblstatusRezervacije.setPreferredSize(lblDimension);
		statusRezervacijeField = new JCheckBox();
		statusRezervacijeField.setEnabled(mode != StanjeDijaloga.BROWSE  && true);
		panstatusRezervacije.add(lblstatusRezervacije);
		panstatusRezervacije.add((Component)statusRezervacijeField);
		
		boxCentar.add(panstatusRezervacije);
		
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
		
		JPanel panknjigaogranka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblknjigaogranka = new JLabel("knjigaogranka");
		lblknjigaogranka.setPreferredSize(lblDimension);
		knjigaogrankaField = new JComboBox();
		
	    List<KnjigaOgranka> knjigaogrankaList = knjigaogrankaDao.findAll();     
	    for (KnjigaOgranka obj : knjigaogrankaList) {
	    	knjigaogrankaField.addItem(obj);
	    }
		knjigaogrankaField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panknjigaogranka.add(lblknjigaogranka);
		panknjigaogranka.add((Component)knjigaogrankaField);
		
		boxCentar.add(panknjigaogranka);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		
		
		
		
	return ok;
	}
	public JTextField getIdRezervacijeField() {
		return idRezervacijeField;
	}
	public JDatePicker getDatumRezervacijeField() {
		return datumRezervacijeField;
	}
	public JCheckBox getStatusRezervacijeField() {
		return statusRezervacijeField;
	}
	public JComboBox getClanField() {
		return clanField;
	}
	public JComboBox getKnjigaogrankaField() {
		return knjigaogrankaField;
	}
	
}
