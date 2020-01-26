package easyFood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Jelo;
import model.Kategorija;

public interface JeloRepo extends JpaRepository<Jelo, Integer> {
	
	@Query("select distinct j.vrstaKuhinje from Jelo j")
	
	List<String> getVrstaKuhinje();
	
	List<Jelo> findByVrstaKuhinje(String vrstaKuhinje);
	
	List<Jelo> findByKategorija(Kategorija k);

}
