/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.JezikForm;


public class OpenJezikFormAction extends AbstractAction {
	
	
    public OpenJezikFormAction() {
        putValue(SHORT_DESCRIPTION, "Jezik");
		putValue(SMALL_ICON, new ImageIcon("images/jezik.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	JezikForm form;
        form = new JezikForm(MainForm.getInstance());
        form.setVisible(true);
    }

}
