/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Zanr")
public class Zanr  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idZanr")
	private Integer idZanr;
	
	@Column(name = "nazivZanr", unique = true, nullable = false)
	private String nazivZanr;
	
	@Column(name = "opisZanr", unique = false, nullable = true)
	private String opisZanr;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "zanr")
    private Set<Knjiga> knjiga;
	
	public Zanr() {
        super();
    }
	
	public Integer getIdZanr(){
		return idZanr;
	}
	
	public void setIdZanr(Integer idZanr){
		this.idZanr = idZanr;
	}
	
	public String getNazivZanr(){
		return nazivZanr;
	}
	
	public void setNazivZanr(String nazivZanr){
		this.nazivZanr = nazivZanr;
	}
	
	public String getOpisZanr(){
		return opisZanr;
	}
	
	public void setOpisZanr(String opisZanr){
		this.opisZanr = opisZanr;
	}
	
	public Set<Knjiga> getKnjiga(){
		return knjiga;
	}
	
	public void setKnjiga(Set<Knjiga> knjiga){
		this.knjiga = knjiga;
	}
	
	@Override
	public String toString() {
		return "" + " " + idZanr.toString() + " " + nazivZanr.toString();
	}
}