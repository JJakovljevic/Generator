package gui.forms.detailpanels.akcije;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class BojenjeKeyListener implements KeyListener {
	
	JTextField tfPolje;
	
	public BojenjeKeyListener(JTextField text){
		tfPolje = text;
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!tfPolje.getText().trim().equals("")){
			tfPolje.setBackground(Color.WHITE);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!tfPolje.getText().trim().equals("")){
			tfPolje.setBackground(Color.WHITE);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!tfPolje.getText().trim().equals("")){
			tfPolje.setBackground(Color.WHITE);
		}
	}

}
