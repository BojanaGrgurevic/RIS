package easyFood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepo extends JpaRepository<Korisnik, Integer> {
	
	List<Korisnik> findByUsername(String username);
	
	

}
