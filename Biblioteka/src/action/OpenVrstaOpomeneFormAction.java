/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.VrstaOpomeneForm;


public class OpenVrstaOpomeneFormAction extends AbstractAction {
	
	
    public OpenVrstaOpomeneFormAction() {
        putValue(SHORT_DESCRIPTION, "Vrsta opomene");
		putValue(SMALL_ICON, new ImageIcon("images/vrstaopomene.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	VrstaOpomeneForm form;
        form = new VrstaOpomeneForm(MainForm.getInstance());
        form.setVisible(true);
    }

}
