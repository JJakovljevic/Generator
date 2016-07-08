package gui.tablemodel;

import javax.swing.table.DefaultTableModel;

import model.collections.ITableModel;

@SuppressWarnings("serial")
public class DialogTableModel extends DefaultTableModel {

	private String[] columnNames ;
    private ITableModel itableModel;

    public DialogTableModel(String[] columnNames, ITableModel itableModel) {
    	this.columnNames = columnNames;
    	this.itableModel = itableModel;
   
    }
    
    public DialogTableModel() {
    	super();
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return itableModel.getRowCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return itableModel.getValueAt(rowIndex, columnIndex);
	}

}
