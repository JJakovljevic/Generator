/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.RezervacijaForm;

import bean.Clan;
import bean.KnjigaOgranka;

public class OpenRezervacijaFormAction extends AbstractAction {
	
		private Clan clan;
		private KnjigaOgranka knjigaogranka;
	
    public OpenRezervacijaFormAction() {
        putValue(SHORT_DESCRIPTION, "Rezervacija");
		putValue(SMALL_ICON, new ImageIcon("images/rezervacija.png"));
    }
    public OpenRezervacijaFormAction(Clan clan) {
        putValue(SHORT_DESCRIPTION, "Rezervacija");
		putValue(SMALL_ICON, new ImageIcon("images/rezervacija.png"));
		this.clan = clan;
    }
    public OpenRezervacijaFormAction(KnjigaOgranka knjigaogranka) {
        putValue(SHORT_DESCRIPTION, "Rezervacija");
		putValue(SMALL_ICON, new ImageIcon("images/rezervacija.png"));
		this.knjigaogranka = knjigaogranka;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	RezervacijaForm form;
    	if(clan != null){
        	form = new RezervacijaForm(MainForm.getInstance(),clan);
        }
    	else if(knjigaogranka != null){
        	form = new RezervacijaForm(MainForm.getInstance(),knjigaogranka);
        }
        else{
	    	form = new RezervacijaForm(MainForm.getInstance());
        }
        form.setVisible(true);
    }

}
