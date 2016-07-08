/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "KnjigaOgranka")
public class KnjigaOgranka  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idKnjigeOgranka")
	private Integer idKnjigeOgranka;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "knjigaogranka")
    private Set<Rezervacija> rezervacija;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "knjigaogranka")
    private Set<Primerak> primerak;
	
    @ManyToOne
    private Ogranak ogranak;
	
    @ManyToOne
    private Knjiga knjiga;
	
	public KnjigaOgranka() {
        super();
    }
	
	public Integer getIdKnjigeOgranka(){
		return idKnjigeOgranka;
	}
	
	public void setIdKnjigeOgranka(Integer idKnjigeOgranka){
		this.idKnjigeOgranka = idKnjigeOgranka;
	}
	
	public Set<Rezervacija> getRezervacija(){
		return rezervacija;
	}
	
	public void setRezervacija(Set<Rezervacija> rezervacija){
		this.rezervacija = rezervacija;
	}
	
	public Set<Primerak> getPrimerak(){
		return primerak;
	}
	
	public void setPrimerak(Set<Primerak> primerak){
		this.primerak = primerak;
	}
	
	public Ogranak getOgranak(){
		return ogranak;
	}
	
	public void setOgranak(Ogranak ogranak){
		this.ogranak = ogranak;
	}
	
	public Knjiga getKnjiga(){
		return knjiga;
	}
	
	public void setKnjiga(Knjiga knjiga){
		this.knjiga = knjiga;
	}
	
	@Override
	public String toString() {
		return "" + " " + idKnjigeOgranka.toString();
	}
}