package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import gui.MainForm;
import gui.forms.${class.name}Form;

public class Open${class.name}FormAction extends AbstractAction {

    public Open${class.name}FormAction() {
        putValue(SHORT_DESCRIPTION, "${class.uiClass.label}");
		putValue(SMALL_ICON, new ImageIcon("images/${class.name?lower_case}.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ${class.name}Form form = new ${class.name}Form(MainForm.getInstance());
        form.setVisible(true);
    }

}
