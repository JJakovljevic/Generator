/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.OpomenaForm;

import bean.VrstaOpomene;
import bean.Zaduzenje;

public class OpenOpomenaFormAction extends AbstractAction {
	
		private VrstaOpomene vrstaopomene;
		private Zaduzenje zaduzenje;
	
    public OpenOpomenaFormAction() {
        putValue(SHORT_DESCRIPTION, "Opomena");
		putValue(SMALL_ICON, new ImageIcon("images/opomena.png"));
    }
    public OpenOpomenaFormAction(VrstaOpomene vrstaopomene) {
        putValue(SHORT_DESCRIPTION, "Opomena");
		putValue(SMALL_ICON, new ImageIcon("images/opomena.png"));
		this.vrstaopomene = vrstaopomene;
    }
    public OpenOpomenaFormAction(Zaduzenje zaduzenje) {
        putValue(SHORT_DESCRIPTION, "Opomena");
		putValue(SMALL_ICON, new ImageIcon("images/opomena.png"));
		this.zaduzenje = zaduzenje;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	OpomenaForm form;
    	if(vrstaopomene != null){
        	form = new OpomenaForm(MainForm.getInstance(),vrstaopomene);
        }
    	else if(zaduzenje != null){
        	form = new OpomenaForm(MainForm.getInstance(),zaduzenje);
        }
        else{
	    	form = new OpomenaForm(MainForm.getInstance());
        }
        form.setVisible(true);
    }

}
