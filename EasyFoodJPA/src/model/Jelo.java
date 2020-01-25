package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the jelo database table.
 * 
 */
@Entity
@NamedQuery(name="Jelo.findAll", query="SELECT j FROM Jelo j")
public class Jelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idJelo;

	private int cena;

	private String detalji;

	private String naziv;

	private String vrstaKuhinje;
	
	private String slika;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	@JoinColumn(name="idKategorija")
	private Kategorija kategorija;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="jelo")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Narucenojelo
	@OneToMany(mappedBy="jelo")
	private List<Narucenojelo> narucenojelos;

	public Jelo() {
	}

	public int getIdJelo() {
		return this.idJelo;
	}

	public void setIdJelo(int idJelo) {
		this.idJelo = idJelo;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getDetalji() {
		return this.detalji;
	}

	public void setDetalji(String detalji) {
		this.detalji = detalji;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getVrstaKuhinje() {
		return this.vrstaKuhinje;
	}

	public void setVrstaKuhinje(String vrstaKuhinje) {
		this.vrstaKuhinje = vrstaKuhinje;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	
	

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setJelo(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setJelo(null);

		return komentar;
	}

	public List<Narucenojelo> getNarucenojelos() {
		return this.narucenojelos;
	}

	public void setNarucenojelos(List<Narucenojelo> narucenojelos) {
		this.narucenojelos = narucenojelos;
	}

	public Narucenojelo addNarucenojelo(Narucenojelo narucenojelo) {
		getNarucenojelos().add(narucenojelo);
		narucenojelo.setJelo(this);

		return narucenojelo;
	}

	public Narucenojelo removeNarucenojelo(Narucenojelo narucenojelo) {
		getNarucenojelos().remove(narucenojelo);
		narucenojelo.setJelo(null);

		return narucenojelo;
	}

}