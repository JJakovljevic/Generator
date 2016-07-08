/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.BibliotekarForm;

import bean.Ogranak;

public class OpenBibliotekarFormAction extends AbstractAction {
	
		private Ogranak ogranak;
	
    public OpenBibliotekarFormAction() {
        putValue(SHORT_DESCRIPTION, "Bibliotekar");
		putValue(SMALL_ICON, new ImageIcon("images/bibliotekar.png"));
    }
    public OpenBibliotekarFormAction(Ogranak ogranak) {
        putValue(SHORT_DESCRIPTION, "Bibliotekar");
		putValue(SMALL_ICON, new ImageIcon("images/bibliotekar.png"));
		this.ogranak = ogranak;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	BibliotekarForm form;
    	if(ogranak != null){
        	form = new BibliotekarForm(MainForm.getInstance(),ogranak);
        }
        else{
	    	form = new BibliotekarForm(MainForm.getInstance());
        }
        form.setVisible(true);
    }

}
