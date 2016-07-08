/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Izdavac")
public class Izdavac  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idIzdavaca")
	private Integer idIzdavaca;
	
	@Column(name = "nazivIzdavaca", unique = true, nullable = false)
	private String nazivIzdavaca;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "izdavac")
    private Set<Primerak> primerak;
	
	public Izdavac() {
        super();
    }
	
	public Integer getIdIzdavaca(){
		return idIzdavaca;
	}
	
	public void setIdIzdavaca(Integer idIzdavaca){
		this.idIzdavaca = idIzdavaca;
	}
	
	public String getNazivIzdavaca(){
		return nazivIzdavaca;
	}
	
	public void setNazivIzdavaca(String nazivIzdavaca){
		this.nazivIzdavaca = nazivIzdavaca;
	}
	
	public Set<Primerak> getPrimerak(){
		return primerak;
	}
	
	public void setPrimerak(Set<Primerak> primerak){
		this.primerak = primerak;
	}
	
	@Override
	public String toString() {
		return "" + " " + idIzdavaca.toString() + " " + nazivIzdavaca.toString();
	}
}