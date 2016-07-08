package gui.forms;

import gui.forms.detailpanels.AbstractPanelDetail;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Klasa služi kao osnova za sve dijaloge za unos, izmenu i pretragu.
 * Sadrži atribut panelDetail, u kojem se nalazi konkretan panel sa detaljima,
 * kao i dugmiće za potvrdu i odustanak (isti za sve).
 */
@SuppressWarnings("serial")
public class AddUpdateFindDialog extends JDialog {
	
	public static final int OK = 0;
	public static final int CANCEL = 1;
	
	AbstractPanelDetail panelDetail;
	private int closingMode = AddUpdateFindDialog.CANCEL; // informacija o tome na koji način je zatvoren dijalog
	
	
	public AddUpdateFindDialog(JDialog parent, String title, AbstractPanelDetail panDetail) {

		super(parent, title, true);
		
		this.panelDetail = panDetail;
		add(panDetail, BorderLayout.CENTER);
		
		JPanel panSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnOk = new JButton("Potvrda");
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panelDetail.proveriIspravnostPodataka()) {
					closingMode = AddUpdateFindDialog.OK;
					setVisible(false);		
				}
					
			}
		});
		
		JButton btnCancel = new JButton("Odustanak");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closingMode = AddUpdateFindDialog.CANCEL;
				setVisible(false);
			}
		});
		
		panSouth.add(btnOk);
		panSouth.add(btnCancel);
		add(panSouth, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(parent);
		setModal(true);

	}

	public int getClosingMode() {
		return closingMode;
	}

}
