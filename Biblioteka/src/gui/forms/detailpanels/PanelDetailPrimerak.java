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

import enumeration.Stanje;
import dao.KnjigaOgrankaDaoBean;
import bean.KnjigaOgranka;
import dao.IzdavacDaoBean;
import bean.Izdavac;

@SuppressWarnings("serial")
public class PanelDetailPrimerak extends AbstractPanelDetail {
	
	private KnjigaOgrankaDaoBean knjigaogrankaDao = new KnjigaOgrankaDaoBean();
	private IzdavacDaoBean izdavacDao = new IzdavacDaoBean();
	
	private JLabel lblidPrimerka;
	private JTextField idPrimerkaField;
	private JLabel lblgodinaIzdavanja;
	private JTextField godinaIzdavanjaField;
	private JLabel lblstanje;
	private JComboBox stanjeField;
	private JLabel lblknjigaogranka;
	private JComboBox knjigaogrankaField;
	private JLabel lblizdavac;
	private JComboBox izdavacField;
	
	public PanelDetailPrimerak(StanjeDijaloga mode) {
		
		setLayout(new BorderLayout());

		Dimension lblDimension = new Dimension(150,20);
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		JPanel panidPrimerka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblidPrimerka = new JLabel("ID primerka");
		lblidPrimerka.setPreferredSize(lblDimension);
		idPrimerkaField = new JTextField(6);
		idPrimerkaField.setEnabled(mode != StanjeDijaloga.BROWSE  && mode != StanjeDijaloga.UPDATE && mode != StanjeDijaloga.ADD && false);
		idPrimerkaField.addKeyListener(new BojenjeKeyListener(idPrimerkaField));
		panidPrimerka.add(lblidPrimerka);
		panidPrimerka.add((Component)idPrimerkaField);
		 panidPrimerka.setVisible(mode != StanjeDijaloga.ADD);
		boxCentar.add(panidPrimerka);
		
		JPanel pangodinaIzdavanja = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblgodinaIzdavanja = new JLabel("Godina izdavanja");
		lblgodinaIzdavanja.setPreferredSize(lblDimension);
		godinaIzdavanjaField = new JTextField(4);
		godinaIzdavanjaField.setEnabled(mode != StanjeDijaloga.BROWSE   && true);
		godinaIzdavanjaField.addKeyListener(new BojenjeKeyListener(godinaIzdavanjaField));
		pangodinaIzdavanja.add(lblgodinaIzdavanja);
		pangodinaIzdavanja.add((Component)godinaIzdavanjaField);
		
		boxCentar.add(pangodinaIzdavanja);
		
		JPanel panstanje = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblstanje = new JLabel("Stanje primerka");
		lblstanje.setPreferredSize(lblDimension);
		stanjeField = new JComboBox();
		
	    stanjeField.addItem(Stanje.ostecena);
	    stanjeField.addItem(Stanje.dobar);
		stanjeField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panstanje.add(lblstanje);
		panstanje.add((Component)stanjeField);
		
		boxCentar.add(panstanje);
		
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
		
		JPanel panizdavac = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblizdavac = new JLabel("izdavac");
		lblizdavac.setPreferredSize(lblDimension);
		izdavacField = new JComboBox();
		
	    List<Izdavac> izdavacList = izdavacDao.findAll();     
	    for (Izdavac obj : izdavacList) {
	    	izdavacField.addItem(obj);
	    }
		izdavacField.setEnabled(mode != StanjeDijaloga.BROWSE && true);
		panizdavac.add(lblizdavac);
		panizdavac.add((Component)izdavacField);
		
		boxCentar.add(panizdavac);
		
		add(boxCentar);
	}
	
	public boolean proveriIspravnostPodataka() {
		
		boolean ok = true;
		
		if (godinaIzdavanjaField.getText().trim().equals("")) {
			godinaIzdavanjaField.setBackground(Color.RED);
			ok = false;
		}
		
		
		
		
	return ok;
	}
	public JTextField getIdPrimerkaField() {
		return idPrimerkaField;
	}
	public JTextField getGodinaIzdavanjaField() {
		return godinaIzdavanjaField;
	}
	public JComboBox getStanjeField() {
		return stanjeField;
	}
	public JComboBox getKnjigaogrankaField() {
		return knjigaogrankaField;
	}
	public JComboBox getIzdavacField() {
		return izdavacField;
	}
	
}
