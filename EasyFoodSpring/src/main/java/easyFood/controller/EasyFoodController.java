package easyFood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easyFood.repository.DostavaRepo;
import easyFood.repository.JeloRepo;
import easyFood.repository.KategorijaRepo;
import easyFood.repository.KomentarRepo;
import easyFood.repository.KorisnikRepo;
import easyFood.repository.NarucenojeloRepo;
import easyFood.repository.NarudzbinaRepo;
import model.Jelo;
import model.Kategorija;
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
		
		List<Kategorija> kategorije = ka.findAll();
		request.getSession().setAttribute("kategorije", kategorije);

		List<String> vrstaKuhinje = jr.getVrstaKuhinje();
		request.getSession().setAttribute("vrsta", vrstaKuhinje);

		List<Jelo> jela = jr.findAll();
		request.getSession().setAttribute("jela", jela);


		return k.getRoll();

	}
	
	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public String registration(HttpServletRequest request, String username, String pass, String ime, String prezime,
			String email, String tel) {
		Korisnik k = new Korisnik();
		k.setUsername(username);
		k.setPass(pass);
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setEmail(email);
		k.setTel(tel);
		k.setRoll("korisnik");

		String msg;

		if (kr.findByUsername(username).size() == 0) {
			kr.save(k);
			msg = "Uspesno ste se registrovali";
		} else {
			msg = "Username vec postoji u bazi!";

			request.getSession().setAttribute("msg", msg);
		}

		return "index";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}
	
	@RequestMapping(value = "getByVrsta", method = RequestMethod.GET)
	public String getByVrsta(String vrstaKuh, Model m) {
		List<Jelo> jelo = jr.findByVrstaKuhinje(vrstaKuh);
		m.addAttribute("jela", jelo);
		return "korisnik";
	}

	@RequestMapping(value = "getByKategorija", method = RequestMethod.GET)
	public String getByKategorija(int kategorija, Model m) {
		Kategorija k = ka.getOne(kategorija);
		List<Jelo> jeloK = jr.findByKategorija(k);
		m.addAttribute("jela", jeloK);
		return "korisnik";
	}
	
	@RequestMapping(value = "getJela", method = RequestMethod.POST)
	public String getJela(HttpServletRequest request) {

		List<Jelo> jela = jr.findAll();
		request.getSession().setAttribute("jela", jela);

		return "korisnik";
	}






	
	
	
}
