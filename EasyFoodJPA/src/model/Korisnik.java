package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;

	private String email;

	private String ime;

	private String pass;

	private String prezime;

	private String roll;

	private String tel;

	private String username;

	//bi-directional many-to-one association to Dostava
	@OneToMany(mappedBy="korisnik")
	private List<Dostava> dostavas;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Narudzbina
	@OneToMany(mappedBy="korisnik")
	private List<Narudzbina> narudzbinas;

	public Korisnik() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getRoll() {
		return this.roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Dostava> getDostavas() {
		return this.dostavas;
	}

	public void setDostavas(List<Dostava> dostavas) {
		this.dostavas = dostavas;
	}

	public Dostava addDostava(Dostava dostava) {
		getDostavas().add(dostava);
		dostava.setKorisnik(this);

		return dostava;
	}

	public Dostava removeDostava(Dostava dostava) {
		getDostavas().remove(dostava);
		dostava.setKorisnik(null);

		return dostava;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

	public List<Narudzbina> getNarudzbinas() {
		return this.narudzbinas;
	}

	public void setNarudzbinas(List<Narudzbina> narudzbinas) {
		this.narudzbinas = narudzbinas;
	}

	public Narudzbina addNarudzbina(Narudzbina narudzbina) {
		getNarudzbinas().add(narudzbina);
		narudzbina.setKorisnik(this);

		return narudzbina;
	}

	public Narudzbina removeNarudzbina(Narudzbina narudzbina) {
		getNarudzbinas().remove(narudzbina);
		narudzbina.setKorisnik(null);

		return narudzbina;
	}

}