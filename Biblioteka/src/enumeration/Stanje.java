/*****************************************************************/
/*          Generisano na osnovu templejta: enum.ftl             */
/*****************************************************************/


package enumeration;

public enum Stanje {
    ostecena,
    dobar;

	 public String toString() {
	 	switch(this){
			case ostecena:
				return "Ostecena";
			case dobar:
				return "Dobar";
			
			default:
	 			return super.toString();
	 	 }
	  }

}
