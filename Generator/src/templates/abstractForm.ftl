package abstractForm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public abstract class AbstractForm extends JDialog {

	public enum StanjeDijaloga {
		BROWSE, ADD, UPDATE, FIND
	};

	protected JFrame parent;
	protected DialogToolbar toolbar;
	protected JTable table;
	protected AbstractPanelDetail panelDetail;
	protected String[] tableColumns;
	protected DialogStatusBar statusbar;
	protected DefaultTableModel tableModel;
	JSplitPane splitPane;

	public AbstractForm(JFrame parent) {

		super(parent, false);
		this.parent = parent;
		setModal(true);
		setSize((int) (parent.getWidth() * 0.75), (int) (parent.getHeight() * 0.75));
		setLocationRelativeTo(parent);
	}

	protected void initGUI() {

		toolbar = new DialogToolbar(this);
		add(toolbar, BorderLayout.NORTH);

		JPanel panTable = new JPanel(new BorderLayout());
		panTable.setBackground(Color.WHITE);
		table = new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		table.setUpdateSelectionOnSort(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel lModel = table.getSelectionModel();
		lModel.addListSelectionListener(new StatusBarSelectionListener());
		TableModel tModel = table.getModel();
		tModel.addTableModelListener(new TabelaModelListener());
		panTable.add(new JScrollPane(table), BorderLayout.CENTER);

		if (table.getRowCount() == 0) {
			toolbar.btnPrvi.setEnabled(false);
			toolbar.btnSledeci.setEnabled(false);
			toolbar.btnPrethodni.setEnabled(false);
			toolbar.btnPoslednji.setEnabled(false);
		} else {
			toolbar.btnPrvi.setEnabled(true);
			toolbar.btnSledeci.setEnabled(true);
			toolbar.btnPrethodni.setEnabled(true);
			toolbar.btnPoslednji.setEnabled(true);
		}

		statusbar = new DialogStatusBar(this);
		add(statusbar, BorderLayout.SOUTH);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panTable, panelDetail);
		splitPane.setResizeWeight(1.0);
		add(splitPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}

	public abstract void dodavanje();

	public abstract void izmena();

	public abstract void brisanje();

	class TabelaModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent arg0) {
			if (table.getRowCount() == 0) {
				toolbar.btnPrvi.setEnabled(false);
				toolbar.btnSledeci.setEnabled(false);
				toolbar.btnPrethodni.setEnabled(false);
				toolbar.btnPoslednji.setEnabled(false);
			} else {
				toolbar.btnPrvi.setEnabled(true);
				toolbar.btnSledeci.setEnabled(true);
				toolbar.btnPrethodni.setEnabled(true);
				toolbar.btnPoslednji.setEnabled(true);
			}
		}

	}

	class StatusBarSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			statusbar.getSlFuture3().setText((table.getSelectedRow() + 1) + "/" + table.getRowCount());
		}

	}

}
