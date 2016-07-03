package gui.forms;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class DialogToolbar extends JToolBar {

	AbstractForm parent;

	JButton btnPogled;

	JButton btnPrvi;
	JButton btnSledeci;
	JButton btnPrethodni;
	JButton btnPoslednji;

	JButton btnDodavanje;
	JButton btnIzmena;
	JButton btnBrisanje;
	JButton btnPretraga;

	public DialogToolbar(AbstractForm parent) {
		super();
		this.parent = parent;

		btnPogled = new JButton();
		btnPogled.setIcon(new ImageIcon("images/icons/pogled.png"));
		btnPogled.setMnemonic('P');
		btnPogled.setToolTipText("Pogled");
		btnPogled.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPogled.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogToolbar.this.parent.splitPane.getBottomComponent()
						.setVisible(!DialogToolbar.this.parent.splitPane.getBottomComponent().isVisible());
				DialogToolbar.this.parent.splitPane.resetToPreferredSizes();

			}
		});
		add(btnPogled);
		addSeparator();

		btnPrvi = new JButton();
		btnPrvi.setIcon(new ImageIcon("images/icons/home.gif"));
		btnPrvi.setToolTipText("Prvi");
		btnPrvi.setMnemonic('1');
		btnPrvi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPrvi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogToolbar.this.parent.table.setRowSelectionInterval(0, 0);
			}
		});
		add(btnPrvi);

		btnSledeci = new JButton();
		btnSledeci.setIcon(new ImageIcon("images/icons/down.gif"));
		btnSledeci.setToolTipText("Sledeci");
		btnSledeci.setMnemonic('2');
		btnSledeci.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSledeci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = DialogToolbar.this.parent.table.getSelectedRow();
				int rowCount = DialogToolbar.this.parent.table.getRowCount() - 1;
				if (selectedRow == -1)
					JOptionPane.showMessageDialog(DialogToolbar.this.parent, "Nije selektovan nijedan red u tabeli!");
				else if (selectedRow == rowCount)
					JOptionPane.showMessageDialog(DialogToolbar.this.parent, "Dosli ste do kraja tabele!");
				else
					DialogToolbar.this.parent.table.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);

			}
		});
		add(btnSledeci);

		btnPrethodni = new JButton();
		btnPrethodni.setIcon(new ImageIcon("images/icons/up.gif"));
		btnPrethodni.setToolTipText("Prethodni");
		btnPrethodni.setMnemonic('3');
		btnPrethodni.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPrethodni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = DialogToolbar.this.parent.table.getSelectedRow();
				if (selectedRow == -1)
					JOptionPane.showMessageDialog(DialogToolbar.this.parent, "Nije selektovan nijedan red u tabeli!");
				else if (selectedRow == 0)
					JOptionPane.showMessageDialog(DialogToolbar.this.parent, "Dosli ste do kraja tabele!");
				else
					DialogToolbar.this.parent.table.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
			}
		});
		add(btnPrethodni);

		btnPoslednji = new JButton();
		btnPoslednji.setIcon(new ImageIcon("images/icons/end.gif"));
		btnPoslednji.setToolTipText("Poslednji");
		btnPoslednji.setMnemonic('4');
		btnPoslednji.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPoslednji.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rowCount = DialogToolbar.this.parent.table.getRowCount() - 1;
				DialogToolbar.this.parent.table.setRowSelectionInterval(rowCount, rowCount);
			}
		});
		add(btnPoslednji);

		addSeparator();

		btnDodavanje = new JButton();
		btnDodavanje.setIcon(new ImageIcon("images/icons/add.gif"));
		btnDodavanje.setToolTipText("Dodavanje");
		btnDodavanje.setMnemonic('5');
		btnDodavanje.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDodavanje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogToolbar.this.parent.dodavanje();
			}
		});
		add(btnDodavanje);

		btnIzmena = new JButton();
		btnIzmena.setIcon(new ImageIcon("images/icons/update.gif"));
		btnIzmena.setToolTipText("Izmena");
		btnIzmena.setMnemonic('6');
		btnIzmena.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIzmena.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogToolbar.this.parent.izmena();
			}
		});
		add(btnIzmena);

		btnBrisanje = new JButton();
		btnBrisanje.setIcon(new ImageIcon("images/icons/remove.gif"));
		btnBrisanje.setToolTipText("Brisanje");
		btnBrisanje.setMnemonic('7');
		btnBrisanje.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBrisanje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogToolbar.this.parent.brisanje();
			}
		});
		add(btnBrisanje);

	}

	public JButton getBtnPrvi() {
		return btnPrvi;
	}

	public JButton getBtnSledeci() {
		return btnSledeci;
	}

	public JButton getBtnPrethodni() {
		return btnPrethodni;
	}

	public JButton getBtnPoslednji() {
		return btnPoslednji;
	}

	public JButton getBtnDodavanje() {
		return btnDodavanje;
	}

	public JButton getBtnIzmena() {
		return btnIzmena;
	}

	public JButton getBtnBrisanje() {
		return btnBrisanje;
	}

	public JButton getBtnPretraga() {
		return btnPretraga;
	}

}
