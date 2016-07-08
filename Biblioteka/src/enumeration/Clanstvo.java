/*****************************************************************/
/*          Generisano na osnovu templejta: enum.ftl             */
/*****************************************************************/


package enumeration;

public enum Clanstvo {
    vazece,
    suspendovan,
    isteklo;

	 public String toString() {
	 	switch(this){
			case vazece:
				return "Vazece";
			case suspendovan:
				return "Suspendovan";
			case isteklo:
				return "Isteklo";
			
			default:
	 			return super.toString();
	 	 }
	  }

}
