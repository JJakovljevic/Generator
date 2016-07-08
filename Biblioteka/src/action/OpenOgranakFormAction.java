/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.OgranakForm;


public class OpenOgranakFormAction extends AbstractAction {
	
	
    public OpenOgranakFormAction() {
        putValue(SHORT_DESCRIPTION, "Ogranak");
		putValue(SMALL_ICON, new ImageIcon("images/ogranak.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	OgranakForm form;
        form = new OgranakForm(MainForm.getInstance());
        form.setVisible(true);
    }

}
