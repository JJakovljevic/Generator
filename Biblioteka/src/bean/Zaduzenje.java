/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Zaduzenje")
public class Zaduzenje  implements Serializable{

	@Column(name = "datumIznajmljivanja", unique = false, nullable = false)
	private Date datumIznajmljivanja;
	
	@Id
    @GeneratedValue
	@Column(name = "idZaduzenja")
	private Integer idZaduzenja;
	
	@Column(name = "datumVracanjaMax", unique = false, nullable = false)
	private Date datumVracanjaMax;
	
	@Column(name = "datumVracanja", unique = false, nullable = true)
	private Date datumVracanja;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "zaduzenje")
    private Set<Opomena> opomena;
	
    @ManyToOne
    private Primerak primerak;
	
    @ManyToOne
    private Clan clan;
	
	public Zaduzenje() {
        super();
    }
	
	public Date getDatumIznajmljivanja(){
		return datumIznajmljivanja;
	}
	
	public void setDatumIznajmljivanja(Date datumIznajmljivanja){
		this.datumIznajmljivanja = datumIznajmljivanja;
	}
	
	public Integer getIdZaduzenja(){
		return idZaduzenja;
	}
	
	public void setIdZaduzenja(Integer idZaduzenja){
		this.idZaduzenja = idZaduzenja;
	}
	
	public Date getDatumVracanjaMax(){
		return datumVracanjaMax;
	}
	
	public void setDatumVracanjaMax(Date datumVracanjaMax){
		this.datumVracanjaMax = datumVracanjaMax;
	}
	
	public Date getDatumVracanja(){
		return datumVracanja;
	}
	
	public void setDatumVracanja(Date datumVracanja){
		this.datumVracanja = datumVracanja;
	}
	
	public Set<Opomena> getOpomena(){
		return opomena;
	}
	
	public void setOpomena(Set<Opomena> opomena){
		this.opomena = opomena;
	}
	
	public Primerak getPrimerak(){
		return primerak;
	}
	
	public void setPrimerak(Primerak primerak){
		this.primerak = primerak;
	}
	
	public Clan getClan(){
		return clan;
	}
	
	public void setClan(Clan clan){
		this.clan = clan;
	}
	
	@Override
	public String toString() {
		return "" + " " + idZaduzenja.toString();
	}
}