/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Administrator")
public class Administrator extends Zaposlen implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "maticniBrojAdministratora")
	private Integer maticniBrojAdministratora;
	
	public Administrator() {
        super();
    }
	
	public Integer getMaticniBrojAdministratora(){
		return maticniBrojAdministratora;
	}
	
	public void setMaticniBrojAdministratora(Integer maticniBrojAdministratora){
		this.maticniBrojAdministratora = maticniBrojAdministratora;
	}
	
	@Override
	public String toString() {
		return "" + " " + maticniBrojAdministratora.toString() + " " + imeZaposlenog.toString() + " " + prezimeZaposlenog.toString();
	}
}