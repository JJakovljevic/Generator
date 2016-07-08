/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.KnjigaOgrankaForm;

import bean.Ogranak;
import bean.Knjiga;

public class OpenKnjigaOgrankaFormAction extends AbstractAction {
	
		private Ogranak ogranak;
		private Knjiga knjiga;
	
    public OpenKnjigaOgrankaFormAction() {
        putValue(SHORT_DESCRIPTION, "Knjige ogranka");
		putValue(SMALL_ICON, new ImageIcon("images/knjigaogranka.png"));
    }
    public OpenKnjigaOgrankaFormAction(Ogranak ogranak) {
        putValue(SHORT_DESCRIPTION, "Knjige ogranka");
		putValue(SMALL_ICON, new ImageIcon("images/knjigaogranka.png"));
		this.ogranak = ogranak;
    }
    public OpenKnjigaOgrankaFormAction(Knjiga knjiga) {
        putValue(SHORT_DESCRIPTION, "Knjige ogranka");
		putValue(SMALL_ICON, new ImageIcon("images/knjigaogranka.png"));
		this.knjiga = knjiga;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	KnjigaOgrankaForm form;
    	if(ogranak != null){
        	form = new KnjigaOgrankaForm(MainForm.getInstance(),ogranak);
        }
    	else if(knjiga != null){
        	form = new KnjigaOgrankaForm(MainForm.getInstance(),knjiga);
        }
        else{
	    	form = new KnjigaOgrankaForm(MainForm.getInstance());
        }
        form.setVisible(true);
    }

}
