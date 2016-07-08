/**********************************************************************/
/*          Generisano na osnovu templejta: action.ftl          */
/**********************************************************************/

package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.KnjigaForm;

import bean.Zanr;
import bean.Autor;
import bean.Jezik;

public class OpenKnjigaFormAction extends AbstractAction {
	
		private Zanr zanr;
		private Autor autor;
		private Jezik jezik;
	
    public OpenKnjigaFormAction() {
        putValue(SHORT_DESCRIPTION, "Knjiga");
		putValue(SMALL_ICON, new ImageIcon("images/knjiga.png"));
    }
    public OpenKnjigaFormAction(Zanr zanr) {
        putValue(SHORT_DESCRIPTION, "Knjiga");
		putValue(SMALL_ICON, new ImageIcon("images/knjiga.png"));
		this.zanr = zanr;
    }
    public OpenKnjigaFormAction(Autor autor) {
        putValue(SHORT_DESCRIPTION, "Knjiga");
		putValue(SMALL_ICON, new ImageIcon("images/knjiga.png"));
		this.autor = autor;
    }
    public OpenKnjigaFormAction(Jezik jezik) {
        putValue(SHORT_DESCRIPTION, "Knjiga");
		putValue(SMALL_ICON, new ImageIcon("images/knjiga.png"));
		this.jezik = jezik;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	KnjigaForm form;
    	if(zanr != null){
        	form = new KnjigaForm(MainForm.getInstance(),zanr);
        }
    	else if(autor != null){
        	form = new KnjigaForm(MainForm.getInstance(),autor);
        }
    	else if(jezik != null){
        	form = new KnjigaForm(MainForm.getInstance(),jezik);
        }
        else{
	    	form = new KnjigaForm(MainForm.getInstance());
        }
        form.setVisible(true);
    }

}
