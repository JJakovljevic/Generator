/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.PrimerakForm;

import bean.KnjigaOgranka;
import bean.Izdavac;

public class OpenPrimerakFormAction extends AbstractAction {
	
		private KnjigaOgranka knjigaogranka;
		private Izdavac izdavac;
	
    public OpenPrimerakFormAction() {
        putValue(SHORT_DESCRIPTION, "Primerak");
		putValue(SMALL_ICON, new ImageIcon("images/primerak.png"));
    }
    public OpenPrimerakFormAction(KnjigaOgranka knjigaogranka) {
        putValue(SHORT_DESCRIPTION, "Primerak");
		putValue(SMALL_ICON, new ImageIcon("images/primerak.png"));
		this.knjigaogranka = knjigaogranka;
    }
    public OpenPrimerakFormAction(Izdavac izdavac) {
        putValue(SHORT_DESCRIPTION, "Primerak");
		putValue(SMALL_ICON, new ImageIcon("images/primerak.png"));
		this.izdavac = izdavac;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	PrimerakForm form;
    	if(knjigaogranka != null){
        	form = new PrimerakForm(MainForm.getInstance(),knjigaogranka);
        }
    	else if(izdavac != null){
        	form = new PrimerakForm(MainForm.getInstance(),izdavac);
        }
        else{
	    	form = new PrimerakForm(MainForm.getInstance());
        }
        form.setVisible(true);
    }

}
