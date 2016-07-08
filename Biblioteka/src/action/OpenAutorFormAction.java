/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.AutorForm;


public class OpenAutorFormAction extends AbstractAction {
	
	
    public OpenAutorFormAction() {
        putValue(SHORT_DESCRIPTION, "Autor");
		putValue(SMALL_ICON, new ImageIcon("images/autor.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	AutorForm form;
        form = new AutorForm(MainForm.getInstance());
        form.setVisible(true);
    }

}
