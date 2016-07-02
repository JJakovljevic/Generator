package action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import gui.MainForm;
import gui.forms.${class.name}Form;

public class Open${class.name}FormAction extends AbstractAction {

    public Open${class.name}FormAction() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ${class.name}Form form = new ${class.name}Form(MainForm.getInstance());
        form.setVisible(true);
    }

}
