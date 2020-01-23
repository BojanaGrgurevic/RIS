package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the narudzbina database table.
 * 
 */
@Entity
@NamedQuery(name="Narudzbina.findAll", query="SELECT n FROM Narudzbina n")
public class Narudzbina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNarudzbina;

	private String adresa;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private int ukupno;

	//bi-directional many-to-one association to Dostava
	@OneToMany(mappedBy="narudzbina")
	private List<Dostava> dostavas;

	//bi-directional many-to-one association to Narucenojelo
	@OneToMany(mappedBy="narudzbina")
	private List<Narucenojelo> narucenojelos;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idUser")
	private Korisnik korisnik;

	public Narudzbina() {
	}

	public int getIdNarudzbina() {
		return this.idNarudzbina;
	}

	public void setIdNarudzbina(int idNarudzbina) {
		this.idNarudzbina = idNarudzbina;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getUkupno() {
		return this.ukupno;
	}

	public void setUkupno(int ukupno) {
		this.ukupno = ukupno;
	}

	public List<Dostava> getDostavas() {
		return this.dostavas;
	}

	public void setDostavas(List<Dostava> dostavas) {
		this.dostavas = dostavas;
	}

	public Dostava addDostava(Dostava dostava) {
		getDostavas().add(dostava);
		dostava.setNarudzbina(this);

		return dostava;
	}

	public Dostava removeDostava(Dostava dostava) {
		getDostavas().remove(dostava);
		dostava.setNarudzbina(null);

		return dostava;
	}

	public List<Narucenojelo> getNarucenojelos() {
		return this.narucenojelos;
	}

	public void setNarucenojelos(List<Narucenojelo> narucenojelos) {
		this.narucenojelos = narucenojelos;
	}

	public Narucenojelo addNarucenojelo(Narucenojelo narucenojelo) {
		getNarucenojelos().add(narucenojelo);
		narucenojelo.setNarudzbina(this);

		return narucenojelo;
	}

	public Narucenojelo removeNarucenojelo(Narucenojelo narucenojelo) {
		getNarucenojelos().remove(narucenojelo);
		narucenojelo.setNarudzbina(null);

		return narucenojelo;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}