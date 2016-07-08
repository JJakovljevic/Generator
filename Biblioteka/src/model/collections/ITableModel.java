package model.collections;


/**
 *  * @author Igor Zečević
 *
 * Ovaj interfejs služi za povezivanje JTable-a i klase u kojoj se nalazi mape podataka
 * Interfejs implementiraju sve klase u kojima se nalaze mape (Studenti, Predmeti...)
 * On će obezbediti da svaka tabela koristi istu klasu za Model - DialogTableModel
 * 
 */
public interface ITableModel {

	/*
	 *  Vraca broj elemenata u hash mapi
	 */
	int getRowCount();
	
	
	/*
	 *  Vraca vrednost za zadati red i kolonu 
	 */
	Object getValueAt(int rowIndex, int columnIndex);

}
