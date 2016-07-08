/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Opomena")
public class Opomena  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "oznakaOpomene")
	private Integer oznakaOpomene;
	
	@Column(name = "nazivOpomene", unique = false, nullable = false)
	private String nazivOpomene;
	
	@Column(name = "opisOpomene", unique = false, nullable = true)
	private String opisOpomene;
	
	@Column(name = "statusOpomene", unique = false, nullable = false)
	private Boolean statusOpomene;
	
    @ManyToOne
    private VrstaOpomene vrstaopomene;
	
    @ManyToOne
    private Zaduzenje zaduzenje;
	
	public Opomena() {
        super();
    }
	
	public Integer getOznakaOpomene(){
		return oznakaOpomene;
	}
	
	public void setOznakaOpomene(Integer oznakaOpomene){
		this.oznakaOpomene = oznakaOpomene;
	}
	
	public String getNazivOpomene(){
		return nazivOpomene;
	}
	
	public void setNazivOpomene(String nazivOpomene){
		this.nazivOpomene = nazivOpomene;
	}
	
	public String getOpisOpomene(){
		return opisOpomene;
	}
	
	public void setOpisOpomene(String opisOpomene){
		this.opisOpomene = opisOpomene;
	}
	
	public Boolean getStatusOpomene(){
		return statusOpomene;
	}
	
	public void setStatusOpomene(Boolean statusOpomene){
		this.statusOpomene = statusOpomene;
	}
	
	public VrstaOpomene getVrstaopomene(){
		return vrstaopomene;
	}
	
	public void setVrstaopomene(VrstaOpomene vrstaopomene){
		this.vrstaopomene = vrstaopomene;
	}
	
	public Zaduzenje getZaduzenje(){
		return zaduzenje;
	}
	
	public void setZaduzenje(Zaduzenje zaduzenje){
		this.zaduzenje = zaduzenje;
	}
	
	@Override
	public String toString() {
		return "" + " " + oznakaOpomene.toString() + " " + nazivOpomene.toString();
	}
}