/*****************************************************************/
/*          Generisano na osnovu templejta: enum.ftl             */
/*****************************************************************/


package enumeration;

public enum Pol {
    Musko,
    Zensko;

	 public String toString() {
	 	switch(this){
			case Musko:
				return "Musko";
			case Zensko:
				return "Zensko";
			
			default:
	 			return super.toString();
	 	 }
	  }

}
