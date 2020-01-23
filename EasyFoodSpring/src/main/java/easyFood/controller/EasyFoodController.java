package easyFood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easyFood.repository.DostavaRepo;
import easyFood.repository.JeloRepo;
import easyFood.repository.KategorijaRepo;
import easyFood.repository.KomentarRepo;
import easyFood.repository.KorisnikRepo;
import easyFood.repository.NarucenojeloRepo;
import easyFood.repository.NarudzbinaRepo;
import model.Korisnik;

@Controller
public class EasyFoodController {
	
	@Autowired
	KorisnikRepo kr;
	
	@Autowired
	KategorijaRepo ka;

	@Autowired
	JeloRepo jr;

	@Autowired
	KomentarRepo komR;

	@Autowired
	NarucenojeloRepo njr;

	@Autowired
	NarudzbinaRepo nr;

	@Autowired
	DostavaRepo dr;

	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String username, String pass, HttpServletRequest request) {
		String msgLogin;
		List<Korisnik> ks = kr.findByUsername(username);
		if (ks.size() == 0) {
			msgLogin = "Korisnicko ime ne postoji u bazi";
			request.getSession().setAttribute("msgLogin", msgLogin);
			return "index";

		}
		Korisnik k = ks.get(0);
		if (!k.getPass().equals(pass)) {
			msgLogin = "Pogresna sifra";
			request.getSession().setAttribute("msgLogin", msgLogin);
			return "index";
		}

		request.getSession().setAttribute("user", k);

		return k.getRoll();

	}

	
	
	
}
