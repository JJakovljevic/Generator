/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Jezik")
public class Jezik  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idJezik")
	private Integer idJezik;
	
	@Column(name = "imeJezik", unique = true, nullable = false)
	private String imeJezik;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "jezik")
    private Set<Knjiga> knjiga;
	
	public Jezik() {
        super();
    }
	
	public Integer getIdJezik(){
		return idJezik;
	}
	
	public void setIdJezik(Integer idJezik){
		this.idJezik = idJezik;
	}
	
	public String getImeJezik(){
		return imeJezik;
	}
	
	public void setImeJezik(String imeJezik){
		this.imeJezik = imeJezik;
	}
	
	public Set<Knjiga> getKnjiga(){
		return knjiga;
	}
	
	public void setKnjiga(Set<Knjiga> knjiga){
		this.knjiga = knjiga;
	}
	
	@Override
	public String toString() {
		return "" + " " + idJezik.toString() + " " + imeJezik.toString();
	}
}