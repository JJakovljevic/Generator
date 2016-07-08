/*****************************************************************/
/*         Generisano na osnovu templejta: javaBean.ftl     */
/*****************************************************************/

package bean;

import javax.persistence.*;
import java.util.*;
import enumeration.*;
import java.io.Serializable;

@Entity
@Table(name = "Clan")
public class Clan  implements Serializable{

	@Id
    @GeneratedValue
	@Column(name = "brClanskeKarte")
	private Integer brClanskeKarte;
	
	@Column(name = "imeClana", unique = false, nullable = false)
	private String imeClana;
	
	@Column(name = "prezimeClana", unique = false, nullable = false)
	private String prezimeClana;
	
	@Column(name = "jmbg", unique = true, nullable = false)
	private String jmbg;
	
	@Column(name = "datumRodjenja", unique = false, nullable = false)
	private Date datumRodjenja;
	
	@Column(name = "adresaClana", unique = false, nullable = false)
	private String adresaClana;
	
	@Column(name = "telefonClana", unique = false, nullable = false)
	private String telefonClana;
	
	@Column(name = "emailClana", unique = false, nullable = false)
	private String emailClana;
	
	@Column(name = "polClana", unique = false, nullable = false)
	private Pol polClana;
	
	@Column(name = "clanstvo", unique = false, nullable = false)
	private Clanstvo clanstvo;
	
	@Column(name = "datumUplate", unique = false, nullable = false)
	private Date datumUplate;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "clan")
    private Set<Zaduzenje> zaduzenje;
	
  	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "clan")
    private Set<Rezervacija> rezervacija;
	
	public Clan() {
        super();
    }
	
	public Integer getBrClanskeKarte(){
		return brClanskeKarte;
	}
	
	public void setBrClanskeKarte(Integer brClanskeKarte){
		this.brClanskeKarte = brClanskeKarte;
	}
	
	public String getImeClana(){
		return imeClana;
	}
	
	public void setImeClana(String imeClana){
		this.imeClana = imeClana;
	}
	
	public String getPrezimeClana(){
		return prezimeClana;
	}
	
	public void setPrezimeClana(String prezimeClana){
		this.prezimeClana = prezimeClana;
	}
	
	public String getJmbg(){
		return jmbg;
	}
	
	public void setJmbg(String jmbg){
		this.jmbg = jmbg;
	}
	
	public Date getDatumRodjenja(){
		return datumRodjenja;
	}
	
	public void setDatumRodjenja(Date datumRodjenja){
		this.datumRodjenja = datumRodjenja;
	}
	
	public String getAdresaClana(){
		return adresaClana;
	}
	
	public void setAdresaClana(String adresaClana){
		this.adresaClana = adresaClana;
	}
	
	public String getTelefonClana(){
		return telefonClana;
	}
	
	public void setTelefonClana(String telefonClana){
		this.telefonClana = telefonClana;
	}
	
	public String getEmailClana(){
		return emailClana;
	}
	
	public void setEmailClana(String emailClana){
		this.emailClana = emailClana;
	}
	
	public Pol getPolClana(){
		return polClana;
	}
	
	public void setPolClana(Pol polClana){
		this.polClana = polClana;
	}
	
	public Clanstvo getClanstvo(){
		return clanstvo;
	}
	
	public void setClanstvo(Clanstvo clanstvo){
		this.clanstvo = clanstvo;
	}
	
	public Date getDatumUplate(){
		return datumUplate;
	}
	
	public void setDatumUplate(Date datumUplate){
		this.datumUplate = datumUplate;
	}
	
	public Set<Zaduzenje> getZaduzenje(){
		return zaduzenje;
	}
	
	public void setZaduzenje(Set<Zaduzenje> zaduzenje){
		this.zaduzenje = zaduzenje;
	}
	
	public Set<Rezervacija> getRezervacija(){
		return rezervacija;
	}
	
	public void setRezervacija(Set<Rezervacija> rezervacija){
		this.rezervacija = rezervacija;
	}
	
	@Override
	public String toString() {
		return "" + " " + brClanskeKarte.toString() + " " + imeClana.toString() + " " + prezimeClana.toString();
	}
}