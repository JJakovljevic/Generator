/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Primerak")
public class Primerak  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idPrimerka")
	private Integer idPrimerka;
	
	@Column(name = "godinaIzdavanja", unique = false, nullable = false)
	private Integer godinaIzdavanja;
	
	@Column(name = "stanje", unique = false, nullable = false)
	private Stanje stanje;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "primerak")
    private Set<Zaduzenje> zaduzenje;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "primerak")
    private Set<OdobrenaRezervacija> odobrenarezervacija;
	
    @ManyToOne
    private KnjigaOgranka knjigaogranka;
	
    @ManyToOne
    private Izdavac izdavac;
	
	public Primerak() {
        super();
    }
	
	public Integer getIdPrimerka(){
		return idPrimerka;
	}
	
	public void setIdPrimerka(Integer idPrimerka){
		this.idPrimerka = idPrimerka;
	}
	
	public Integer getGodinaIzdavanja(){
		return godinaIzdavanja;
	}
	
	public void setGodinaIzdavanja(Integer godinaIzdavanja){
		this.godinaIzdavanja = godinaIzdavanja;
	}
	
	public Stanje getStanje(){
		return stanje;
	}
	
	public void setStanje(Stanje stanje){
		this.stanje = stanje;
	}
	
	public Set<Zaduzenje> getZaduzenje(){
		return zaduzenje;
	}
	
	public void setZaduzenje(Set<Zaduzenje> zaduzenje){
		this.zaduzenje = zaduzenje;
	}
	
	public Set<OdobrenaRezervacija> getOdobrenarezervacija(){
		return odobrenarezervacija;
	}
	
	public void setOdobrenarezervacija(Set<OdobrenaRezervacija> odobrenarezervacija){
		this.odobrenarezervacija = odobrenarezervacija;
	}
	
	public KnjigaOgranka getKnjigaogranka(){
		return knjigaogranka;
	}
	
	public void setKnjigaogranka(KnjigaOgranka knjigaogranka){
		this.knjigaogranka = knjigaogranka;
	}
	
	public Izdavac getIzdavac(){
		return izdavac;
	}
	
	public void setIzdavac(Izdavac izdavac){
		this.izdavac = izdavac;
	}
	
	@Override
	public String toString() {
		return "" + " " + idPrimerka.toString();
	}
}