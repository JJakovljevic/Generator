/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Knjiga")
public class Knjiga  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idKnjiga")
	private Integer idKnjiga;
	
	@Column(name = "naslov", unique = false, nullable = false)
	private String naslov;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "knjiga")
    private Set<KnjigaOgranka> knjigaogranka;
	
    @ManyToOne
    private Zanr zanr;
	
    @ManyToOne
    private Autor autor;
	
    @ManyToOne
    private Jezik jezik;
	
	public Knjiga() {
        super();
    }
	
	public Integer getIdKnjiga(){
		return idKnjiga;
	}
	
	public void setIdKnjiga(Integer idKnjiga){
		this.idKnjiga = idKnjiga;
	}
	
	public String getNaslov(){
		return naslov;
	}
	
	public void setNaslov(String naslov){
		this.naslov = naslov;
	}
	
	public Set<KnjigaOgranka> getKnjigaogranka(){
		return knjigaogranka;
	}
	
	public void setKnjigaogranka(Set<KnjigaOgranka> knjigaogranka){
		this.knjigaogranka = knjigaogranka;
	}
	
	public Zanr getZanr(){
		return zanr;
	}
	
	public void setZanr(Zanr zanr){
		this.zanr = zanr;
	}
	
	public Autor getAutor(){
		return autor;
	}
	
	public void setAutor(Autor autor){
		this.autor = autor;
	}
	
	public Jezik getJezik(){
		return jezik;
	}
	
	public void setJezik(Jezik jezik){
		this.jezik = jezik;
	}
	
	@Override
	public String toString() {
		return "" + " " + idKnjiga.toString() + " " + naslov.toString();
	}
}