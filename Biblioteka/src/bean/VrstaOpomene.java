/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "VrstaOpomene")
public class VrstaOpomene  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "idVrste")
	private Integer idVrste;
	
	@Column(name = "nazivVrsteOpomene", unique = true, nullable = false)
	private String nazivVrsteOpomene;
	
	@Column(name = "opisVrsteOpomene", unique = false, nullable = true)
	private String opisVrsteOpomene;
	
	@Column(name = "kazna", unique = false, nullable = false)
	private Integer kazna;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "vrstaopomene")
    private Set<Opomena> opomena;
	
	public VrstaOpomene() {
        super();
    }
	
	public Integer getIdVrste(){
		return idVrste;
	}
	
	public void setIdVrste(Integer idVrste){
		this.idVrste = idVrste;
	}
	
	public String getNazivVrsteOpomene(){
		return nazivVrsteOpomene;
	}
	
	public void setNazivVrsteOpomene(String nazivVrsteOpomene){
		this.nazivVrsteOpomene = nazivVrsteOpomene;
	}
	
	public String getOpisVrsteOpomene(){
		return opisVrsteOpomene;
	}
	
	public void setOpisVrsteOpomene(String opisVrsteOpomene){
		this.opisVrsteOpomene = opisVrsteOpomene;
	}
	
	public Integer getKazna(){
		return kazna;
	}
	
	public void setKazna(Integer kazna){
		this.kazna = kazna;
	}
	
	public Set<Opomena> getOpomena(){
		return opomena;
	}
	
	public void setOpomena(Set<Opomena> opomena){
		this.opomena = opomena;
	}
	
	@Override
	public String toString() {
		return "" + " " + idVrste.toString() + " " + nazivVrsteOpomene.toString();
	}
}