/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Ogranak")
public class Ogranak  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "oznakaOgranka")
	private Integer oznakaOgranka;
	
	@Column(name = "imeOgranka", unique = true, nullable = false)
	private String imeOgranka;
	
	@Column(name = "adresaOgranka", unique = false, nullable = false)
	private String adresaOgranka;
	
	@Column(name = "telefonOgranka", unique = false, nullable = true)
	private String telefonOgranka;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "ogranak")
    private Set<KnjigaOgranka> knjigaogranka;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "ogranak")
    private Set<Bibliotekar> bibliotekar;
	
	public Ogranak() {
        super();
    }
	
	public Integer getOznakaOgranka(){
		return oznakaOgranka;
	}
	
	public void setOznakaOgranka(Integer oznakaOgranka){
		this.oznakaOgranka = oznakaOgranka;
	}
	
	public String getImeOgranka(){
		return imeOgranka;
	}
	
	public void setImeOgranka(String imeOgranka){
		this.imeOgranka = imeOgranka;
	}
	
	public String getAdresaOgranka(){
		return adresaOgranka;
	}
	
	public void setAdresaOgranka(String adresaOgranka){
		this.adresaOgranka = adresaOgranka;
	}
	
	public String getTelefonOgranka(){
		return telefonOgranka;
	}
	
	public void setTelefonOgranka(String telefonOgranka){
		this.telefonOgranka = telefonOgranka;
	}
	
	public Set<KnjigaOgranka> getKnjigaogranka(){
		return knjigaogranka;
	}
	
	public void setKnjigaogranka(Set<KnjigaOgranka> knjigaogranka){
		this.knjigaogranka = knjigaogranka;
	}
	
	public Set<Bibliotekar> getBibliotekar(){
		return bibliotekar;
	}
	
	public void setBibliotekar(Set<Bibliotekar> bibliotekar){
		this.bibliotekar = bibliotekar;
	}
	
	@Override
	public String toString() {
		return "" + " " + oznakaOgranka.toString() + " " + imeOgranka.toString();
	}
}