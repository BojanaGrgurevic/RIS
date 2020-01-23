package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the narucenojelo database table.
 * 
 */
@Entity
@NamedQuery(name="Narucenojelo.findAll", query="SELECT n FROM Narucenojelo n")
public class Narucenojelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNarucenoJelo;

	private int kolicina;

	//bi-directional many-to-one association to Jelo
	@ManyToOne
	@JoinColumn(name="idJelo")
	private Jelo jelo;

	//bi-directional many-to-one association to Narudzbina
	@ManyToOne
	@JoinColumn(name="idNarudzbina")
	private Narudzbina narudzbina;

	public Narucenojelo() {
	}

	public int getIdNarucenoJelo() {
		return this.idNarucenoJelo;
	}

	public void setIdNarucenoJelo(int idNarucenoJelo) {
		this.idNarucenoJelo = idNarucenoJelo;
	}

	public int getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Jelo getJelo() {
		return this.jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

	public Narudzbina getNarudzbina() {
		return this.narudzbina;
	}

	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

}