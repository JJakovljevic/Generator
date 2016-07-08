/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "OdobrenaRezervacija")
public class OdobrenaRezervacija  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idOdobreneRezervacije")
	private Integer idOdobreneRezervacije;
	
	@Column(name = "datumOdobrenja", unique = false, nullable = false)
	private Date datumOdobrenja;
	
	@Column(name = "brojDana", unique = false, nullable = false)
	private Integer brojDana;
	
    @ManyToOne
    private Rezervacija rezervacija;
	
    @ManyToOne
    private Primerak primerak;
	
	public OdobrenaRezervacija() {
        super();
    }
	
	public Integer getIdOdobreneRezervacije(){
		return idOdobreneRezervacije;
	}
	
	public void setIdOdobreneRezervacije(Integer idOdobreneRezervacije){
		this.idOdobreneRezervacije = idOdobreneRezervacije;
	}
	
	public Date getDatumOdobrenja(){
		return datumOdobrenja;
	}
	
	public void setDatumOdobrenja(Date datumOdobrenja){
		this.datumOdobrenja = datumOdobrenja;
	}
	
	public Integer getBrojDana(){
		return brojDana;
	}
	
	public void setBrojDana(Integer brojDana){
		this.brojDana = brojDana;
	}
	
	public Rezervacija getRezervacija(){
		return rezervacija;
	}
	
	public void setRezervacija(Rezervacija rezervacija){
		this.rezervacija = rezervacija;
	}
	
	public Primerak getPrimerak(){
		return primerak;
	}
	
	public void setPrimerak(Primerak primerak){
		this.primerak = primerak;
	}
	
}