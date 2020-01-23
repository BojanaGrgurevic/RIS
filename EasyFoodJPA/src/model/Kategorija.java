package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the kategorija database table.
 * 
 */
@Entity
@NamedQuery(name="Kategorija.findAll", query="SELECT k FROM Kategorija k")
public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKategorija;

	private String nazivKategorije;

	//bi-directional many-to-one association to Jelo
	@OneToMany(mappedBy="kategorija")
	private List<Jelo> jelos;

	public Kategorija() {
	}

	public int getIdKategorija() {
		return this.idKategorija;
	}

	public void setIdKategorija(int idKategorija) {
		this.idKategorija = idKategorija;
	}

	public String getNazivKategorije() {
		return this.nazivKategorije;
	}

	public void setNazivKategorije(String nazivKategorije) {
		this.nazivKategorije = nazivKategorije;
	}

	public List<Jelo> getJelos() {
		return this.jelos;
	}

	public void setJelos(List<Jelo> jelos) {
		this.jelos = jelos;
	}

	public Jelo addJelo(Jelo jelo) {
		getJelos().add(jelo);
		jelo.setKategorija(this);

		return jelo;
	}

	public Jelo removeJelo(Jelo jelo) {
		getJelos().remove(jelo);
		jelo.setKategorija(null);

		return jelo;
	}

}