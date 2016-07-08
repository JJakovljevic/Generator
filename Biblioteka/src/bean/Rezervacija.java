/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Rezervacija")
public class Rezervacija  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idRezervacije")
	private Integer idRezervacije;
	
	@Column(name = "datumRezervacije", unique = false, nullable = false)
	private Date datumRezervacije;
	
	@Column(name = "statusRezervacije", unique = false, nullable = false)
	private Boolean statusRezervacije;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "rezervacija")
    private Set<OdobrenaRezervacija> odobrenarezervacija;
	
    @ManyToOne
    private Clan clan;
	
    @ManyToOne
    private KnjigaOgranka knjigaogranka;
	
	public Rezervacija() {
        super();
    }
	
	public Integer getIdRezervacije(){
		return idRezervacije;
	}
	
	public void setIdRezervacije(Integer idRezervacije){
		this.idRezervacije = idRezervacije;
	}
	
	public Date getDatumRezervacije(){
		return datumRezervacije;
	}
	
	public void setDatumRezervacije(Date datumRezervacije){
		this.datumRezervacije = datumRezervacije;
	}
	
	public Boolean getStatusRezervacije(){
		return statusRezervacije;
	}
	
	public void setStatusRezervacije(Boolean statusRezervacije){
		this.statusRezervacije = statusRezervacije;
	}
	
	public Set<OdobrenaRezervacija> getOdobrenarezervacija(){
		return odobrenarezervacija;
	}
	
	public void setOdobrenarezervacija(Set<OdobrenaRezervacija> odobrenarezervacija){
		this.odobrenarezervacija = odobrenarezervacija;
	}
	
	public Clan getClan(){
		return clan;
	}
	
	public void setClan(Clan clan){
		this.clan = clan;
	}
	
	public KnjigaOgranka getKnjigaogranka(){
		return knjigaogranka;
	}
	
	public void setKnjigaogranka(KnjigaOgranka knjigaogranka){
		this.knjigaogranka = knjigaogranka;
	}
	
	@Override
	public String toString() {
		return "" + " " + idRezervacije.toString();
	}
}