/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Bibliotekar")
public class Bibliotekar extends Zaposlen implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "maticniBrojBibliotekara")
	private Integer maticniBrojBibliotekara;
	
    @ManyToOne
    private Ogranak ogranak;
	
	public Bibliotekar() {
        super();
    }
	
	public Integer getMaticniBrojBibliotekara(){
		return maticniBrojBibliotekara;
	}
	
	public void setMaticniBrojBibliotekara(Integer maticniBrojBibliotekara){
		this.maticniBrojBibliotekara = maticniBrojBibliotekara;
	}
	
	public Ogranak getOgranak(){
		return ogranak;
	}
	
	public void setOgranak(Ogranak ogranak){
		this.ogranak = ogranak;
	}
	
	@Override
	public String toString() {
		return "" + " " + maticniBrojBibliotekara.toString() + " " + imeZaposlenog.toString() + " " + prezimeZaposlenog.toString();
	}
}