package easyFood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Komentar;

public interface KomentarRepo extends JpaRepository<Komentar, Integer> {

}
