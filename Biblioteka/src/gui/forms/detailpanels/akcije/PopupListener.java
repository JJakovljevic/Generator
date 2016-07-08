package gui.forms.detailpanels.akcije;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

public class PopupListener extends MouseAdapter {
	 private JPopupMenu jpm;
	 public PopupListener(JPopupMenu jpm) {
		 this.jpm = jpm;
	 }
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		showPopup(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		showPopup(e);
	}
	
	private void showPopup(MouseEvent e){
		
			jpm.show(e.getComponent(),e.getX(),e.getY());
	}
}
