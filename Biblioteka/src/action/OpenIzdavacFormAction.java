/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.IzdavacForm;


public class OpenIzdavacFormAction extends AbstractAction {
	
	
    public OpenIzdavacFormAction() {
        putValue(SHORT_DESCRIPTION, "Izdavac");
		putValue(SMALL_ICON, new ImageIcon("images/izdavac.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	IzdavacForm form;
        form = new IzdavacForm(MainForm.getInstance());
        form.setVisible(true);
    }

}
