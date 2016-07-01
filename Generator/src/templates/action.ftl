package action;

import javax.swing.AbstractAction;

import forms.${class.label}Form;

public class Show${class.label}FormAction extends AbstractAction {

    public Open${class.label}FormAction() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ${class.label}Form form = new ${class.label}Form();
        form.setVisible(true);
    }

}
