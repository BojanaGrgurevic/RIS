package easyFood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Dostava;
import model.Korisnik;

public interface DostavaRepo extends JpaRepository<Dostava, Integer> {

	List<Dostava> findByKorisnik(Korisnik k);
	
}
