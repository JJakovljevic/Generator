/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.ZaduzenjeForm;

import bean.Primerak;
import bean.Clan;

public class OpenZaduzenjeFormAction extends AbstractAction {
	
		private Primerak primerak;
		private Clan clan;
	
    public OpenZaduzenjeFormAction() {
        putValue(SHORT_DESCRIPTION, "Zaduzenja");
		putValue(SMALL_ICON, new ImageIcon("images/zaduzenje.png"));
    }
    public OpenZaduzenjeFormAction(Primerak primerak) {
        putValue(SHORT_DESCRIPTION, "Zaduzenja");
		putValue(SMALL_ICON, new ImageIcon("images/zaduzenje.png"));
		this.primerak = primerak;
    }
    public OpenZaduzenjeFormAction(Clan clan) {
        putValue(SHORT_DESCRIPTION, "Zaduzenja");
		putValue(SMALL_ICON, new ImageIcon("images/zaduzenje.png"));
		this.clan = clan;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	ZaduzenjeForm form;
    	if(primerak != null){
        	form = new ZaduzenjeForm(MainForm.getInstance(),primerak);
        }
    	else if(clan != null){
        	form = new ZaduzenjeForm(MainForm.getInstance(),clan);
        }
        else{
	    	form = new ZaduzenjeForm(MainForm.getInstance());
        }
        form.setVisible(true);
    }

}
