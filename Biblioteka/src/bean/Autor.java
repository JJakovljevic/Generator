/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Autor")
public class Autor  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idAutor")
	private Integer idAutor;
	
	@Column(name = "ime", unique = false, nullable = false)
	private String ime;
	
	@Column(name = "prezime", unique = false, nullable = false)
	private String prezime;
	
	@Column(name = "biografija", unique = false, nullable = true)
	private String biografija;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "autor")
    private Set<Knjiga> knjiga;
	
	public Autor() {
        super();
    }
	
	public Integer getIdAutor(){
		return idAutor;
	}
	
	public void setIdAutor(Integer idAutor){
		this.idAutor = idAutor;
	}
	
	public String getIme(){
		return ime;
	}
	
	public void setIme(String ime){
		this.ime = ime;
	}
	
	public String getPrezime(){
		return prezime;
	}
	
	public void setPrezime(String prezime){
		this.prezime = prezime;
	}
	
	public String getBiografija(){
		return biografija;
	}
	
	public void setBiografija(String biografija){
		this.biografija = biografija;
	}
	
	public Set<Knjiga> getKnjiga(){
		return knjiga;
	}
	
	public void setKnjiga(Set<Knjiga> knjiga){
		this.knjiga = knjiga;
	}
	
	@Override
	public String toString() {
		return "" + " " + idAutor.toString() + " " + ime.toString() + " " + prezime.toString();
	}
}