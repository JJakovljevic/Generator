/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.ZanrForm;


public class OpenZanrFormAction extends AbstractAction {
	
	
    public OpenZanrFormAction() {
        putValue(SHORT_DESCRIPTION, "Zanr");
		putValue(SMALL_ICON, new ImageIcon("images/zanr.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	ZanrForm form;
        form = new ZanrForm(MainForm.getInstance());
        form.setVisible(true);
    }

}
