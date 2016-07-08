/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.ClanForm;


public class OpenClanFormAction extends AbstractAction {
	
	
    public OpenClanFormAction() {
        putValue(SHORT_DESCRIPTION, "Clan");
		putValue(SMALL_ICON, new ImageIcon("images/clan.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	ClanForm form;
        form = new ClanForm(MainForm.getInstance());
        form.setVisible(true);
    }

}
