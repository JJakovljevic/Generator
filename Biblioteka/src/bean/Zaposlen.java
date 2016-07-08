/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Zaposlen  implements Serializable{

	@Column(name = "korisnickoIme", unique = true, nullable = false)
	protected String korisnickoIme;
	
	@Column(name = "imeZaposlenog", unique = false, nullable = false)
	protected String imeZaposlenog;
	
	@Column(name = "prezimeZaposlenog", unique = false, nullable = false)
	protected String prezimeZaposlenog;
	
	@Column(name = "lozinka", unique = false, nullable = false)
	protected String lozinka;
	
	@Column(name = "pol", unique = false, nullable = false)
	protected Pol pol;
	
	public Zaposlen() {
        super();
    }
	
	public String getKorisnickoIme(){
		return korisnickoIme;
	}
	
	public void setKorisnickoIme(String korisnickoIme){
		this.korisnickoIme = korisnickoIme;
	}
	
	public String getImeZaposlenog(){
		return imeZaposlenog;
	}
	
	public void setImeZaposlenog(String imeZaposlenog){
		this.imeZaposlenog = imeZaposlenog;
	}
	
	public String getPrezimeZaposlenog(){
		return prezimeZaposlenog;
	}
	
	public void setPrezimeZaposlenog(String prezimeZaposlenog){
		this.prezimeZaposlenog = prezimeZaposlenog;
	}
	
	public String getLozinka(){
		return lozinka;
	}
	
	public void setLozinka(String lozinka){
		this.lozinka = lozinka;
	}
	
	public Pol getPol(){
		return pol;
	}
	
	public void setPol(Pol pol){
		this.pol = pol;
	}
	
	@Override
	public String toString() {
		return "" + " " + imeZaposlenog.toString() + " " + prezimeZaposlenog.toString();
	}
}