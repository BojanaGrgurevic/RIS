package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dostava database table.
 * 
 */
@Entity
@NamedQuery(name="Dostava.findAll", query="SELECT d FROM Dostava d")
public class Dostava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDostava;

	//bi-directional many-to-one association to Narudzbina
	@ManyToOne
	@JoinColumn(name="idNarudzbina")
	private Narudzbina narudzbina;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idUser")
	private Korisnik korisnik;

	public Dostava() {
	}

	public int getIdDostava() {
		return this.idDostava;
	}

	public void setIdDostava(int idDostava) {
		this.idDostava = idDostava;
	}

	public Narudzbina getNarudzbina() {
		return this.narudzbina;
	}

	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}