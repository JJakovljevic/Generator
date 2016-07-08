/*****************************************************************/
/*       Generisano na osnovu templejta: dialogStatusBar.ftl     */
/*****************************************************************/


package gui.forms;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DialogStatusBar extends JPanel {
	private StatusLabel slFuture1;
	private StatusLabel slFuture2;
	private StatusLabel slFuture3;
	AbstractForm parent;

	public DialogStatusBar(AbstractForm parent) {
		this.parent = parent;

		setLayout(new GridLayout(1, 3, 5, 5));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		slFuture1 = new StatusLabel(parent.getTitle());
		slFuture2 = new StatusLabel("");
		slFuture3 = new StatusLabel("0/" + parent.table.getRowCount());

		add(slFuture1);
		add(slFuture2);
		add(slFuture3);

	}

	class StatusLabel extends JLabel {
		public StatusLabel(String text) {
			super(text);
			setBorder(BorderFactory.createLoweredBevelBorder());
			setHorizontalAlignment(CENTER);
		}

	}

	public StatusLabel getSlFuture1() {
		return slFuture1;
	}

	public StatusLabel getSlFuture2() {
		return slFuture2;
	}

	public StatusLabel getSlFuture3() {
		return slFuture3;
	}

}
