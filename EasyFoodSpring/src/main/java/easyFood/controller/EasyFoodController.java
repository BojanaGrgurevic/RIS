package easyFood.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import model.Dostava;
import model.Jelo;
import model.Kategorija;
import model.Komentar;
import model.Korisnik;
import model.Narucenojelo;
import model.Narudzbina;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
		
		if (k.getRoll().equals("admin")) {
			List<Korisnik> dostavljaci = kr.findByRoll("dostavljac");
			request.getSession().setAttribute("dostavljaci", dostavljaci);
			List<Narudzbina> n = nr.bezDostavljaca(new java.util.Date());
			request.getSession().setAttribute("neisporuceno", n);
			List<Date> datumi = nr.getDatumi();
			request.getSession().setAttribute("datumi", datumi);
		}

		if (k.getRoll().equals("dostavljac")) {
			List<Dostava> dostave = dr.findByKorisnik(k);
			
			request.getSession().setAttribute("dostave", dostave);
		}
		
		

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

	@RequestMapping(value = "detalji", method = RequestMethod.GET)
	public String getDetalji(int idJelo, HttpServletRequest request) {
		Jelo j = jr.getOne(idJelo);
		request.getSession().setAttribute("detalji", j);

		return "jelo";
	}

	@RequestMapping(value = "dodajKorpu", method = RequestMethod.POST)
	public String dodajKorpu(String kolicina, int j, HttpServletRequest request) {

		List<Narucenojelo> korpa = (List<Narucenojelo>) request.getSession().getAttribute("korpa");

		if (korpa == null) {
			korpa = new ArrayList<Narucenojelo>();

		}
		Narucenojelo narucenojelo = new Narucenojelo();
		Jelo jela = jr.getOne(j);
		narucenojelo.setJelo(jela);
		int kol = 0;

		if (kolicina.equals("")) {
			kol = 1;
			narucenojelo.setKolicina(kol);
			korpa.add(narucenojelo);
			request.getSession().setAttribute("korpa", korpa);
			request.setAttribute("korpaMsg", "Uspesno dodato kom" + kol);
			int ukupno;
			Object o = (Object) request.getSession().getAttribute("ukupno");
			if (o == null) {
				ukupno = 0;
			} else {
				ukupno = (int) o;
			}
			ukupno = ukupno + kol * jela.getCena();
			request.getSession().setAttribute("ukupno", ukupno);

		} else {

			try {

				kol = Integer.parseInt(kolicina);

				narucenojelo.setKolicina(kol);

				korpa.add(narucenojelo);

				request.getSession().setAttribute("korpa", korpa);

				int ukupno;
				Object o = (Object) request.getSession().getAttribute("ukupno");
				if (o == null) {
					ukupno = 0;
				} else {
					ukupno = (int) o;
				}

				ukupno = ukupno + kol * jela.getCena();
				request.getSession().setAttribute("ukupno", ukupno);
				System.out.println("Dodato");

			} catch (Exception e) {
				e.printStackTrace();

				request.setAttribute("korpaMsg", "Greska prilokom dodavanja");
			}
		}

		return "korisnik";
	}
	
	
	@RequestMapping(value = "dodajKomentar", method = RequestMethod.POST)
	public String getDetalji(String text, HttpServletRequest request) {
		Komentar k = new Komentar();
		k.setText(text);
		k.setKorisnik((Korisnik) request.getSession().getAttribute("user"));
		k.setJelo((Jelo) request.getSession().getAttribute("detalji"));

		komR.save(k);

		Jelo j = jr.getOne(k.getJelo().getIdJelo());
		request.getSession().setAttribute("detalji", j);

		return "jelo";
	}

	@RequestMapping(value = "obrisiKomentar", method = RequestMethod.GET)
	public String obrisiKom(int idKomentara, HttpServletRequest request) {

		Komentar k = komR.getOne(idKomentara);
		komR.delete(k);

		Jelo j = jr.getOne(k.getJelo().getIdJelo());
		request.getSession().setAttribute("detalji", j);

		return "jelo";
	}

	@RequestMapping(value = "azururajCenu", method = RequestMethod.POST)
	public String azururajCenu(int cena, HttpServletRequest request) {
		Jelo j = (Jelo) request.getSession().getAttribute("detalji");

		j.setCena(cena);
		j = jr.save(j);
		request.getSession().setAttribute("detalji", j);
		List<Jelo> jela = jr.findAll();
		request.getSession().setAttribute("jela", jela);

		return "jelo";
	}
	
	
	@RequestMapping(value = "naruci", method = RequestMethod.POST)
	public String naruci(String adresa, HttpServletRequest request) {

		List<Narucenojelo> korpa = (List<Narucenojelo>) request.getSession().getAttribute("korpa");
		Korisnik k = (Korisnik) request.getSession().getAttribute("user");

		
		if (adresa.equals("")|| korpa.size()==0) {
			return "korpa";
		}
		
		int ukupno;
		Object o = (Object) request.getSession().getAttribute("ukupno");
		if (o == null) {
			ukupno = 0;
		} else {
			ukupno = (int) o;
		}
		Narudzbina n = new Narudzbina();
		n.setAdresa(adresa);
		n.setKorisnik(k);
		n.setUkupno(ukupno);
		// datum
		n.setDatum(new java.util.Date());

		n = nr.save(n);
		for (Narucenojelo nj : korpa) {
			nj.setNarudzbina(n);
			njr.save(nj);
		}

		request.getSession().setAttribute("korpa", null);
		request.getSession().setAttribute("ukupno", null);

		return "korpa";
	}

	@RequestMapping(value = "dodajJelo", method = RequestMethod.POST)
	public String dodajJelo(String naziv, int cena, String vrstaKuhinje, int kategorija, HttpServletRequest request,
			String detalji) {
		Jelo j = new Jelo();
		j.setNaziv(naziv);
		j.setCena(cena);
		j.setVrstaKuhinje(vrstaKuhinje);
		Kategorija k = ka.getOne(kategorija);
		j.setKategorija(k);
		j.setSlika("NoImage.jpg");
		j.setDetalji(detalji);

		jr.save(j);

		List<Jelo> jela = jr.findAll();
		request.getSession().setAttribute("jela", jela);

		return "admin";
	}

	@RequestMapping(value = "izbaciKorpa", method = RequestMethod.GET)
	public String izbaciKorpa(int index, HttpServletRequest request) {
		List<Narucenojelo> korpa = (List<Narucenojelo>) request.getSession().getAttribute("korpa");
		int ukupno = (int) request.getSession().getAttribute("ukupno");

		Narucenojelo j = korpa.get(index);
		ukupno = ukupno - j.getKolicina() * j.getJelo().getCena();

		korpa.remove(index);

		request.getSession().setAttribute("korpa", korpa);
		request.getSession().setAttribute("ukupno", ukupno);
		return "korpa";
	}

	@RequestMapping(value = "dodajDostavu", method = RequestMethod.POST)
	public String dodajDostavu(int idN, int idD, HttpServletRequest request) {
		Dostava d = new Dostava();
		Narudzbina n = nr.getOne(idN);
		d.setNarudzbina(n);
		Korisnik k = kr.getOne(idD);
		d.setKorisnik(k);

		dr.save(d);
		List<Narudzbina> nra = nr.bezDostavljaca(new java.util.Date());
		request.getSession().setAttribute("neisporuceno", nra);
		return "admin";
	}

	@RequestMapping(value = "osveziNarudzbine", method = RequestMethod.POST)
	public String osveziNarudzbine(HttpServletRequest request) {
		List<Narudzbina> n = nr.bezDostavljaca(new java.util.Date());
		request.getSession().setAttribute("neisporuceno", n);
		return "admin";
	}
	
	
	@RequestMapping(value="getPDF", method=RequestMethod.GET)
	public void generatePDF(String datumOdS, String datumDoS, HttpServletResponse response) throws Exception{
		
		
		int suma=0;
		
		Date datumOd = new SimpleDateFormat("yyyy-MM-dd").parse(datumOdS);
		Date datumDo = new SimpleDateFormat("yyyy-MM-dd").parse(datumDoS);
		
		
		List<Narudzbina> narudzbine =  nr.izmedjuDatuma(datumOd, datumDo); 

		List<Narucenojelo> jela = new ArrayList<Narucenojelo>();
		
		for (Narudzbina n:narudzbine) {
			suma+=n.getUkupno();
			for (Narucenojelo nj:n.getNarucenojelos()) {
				boolean ok=false;
				for(Narucenojelo jelo:jela) {
					if (jelo.getJelo().getNaziv().equals(nj.getJelo().getNaziv())) {
						ok=true;
						jelo.setKolicina(jelo.getKolicina()+nj.getKolicina());
						break;
					}
				}
				if(!ok) {
					jela.add(nj);
				}
			}
		}
		
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jela);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/izvestaj.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("od", datumOd);
		params.put("do", datumDo);
		params.put("suma", suma);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=Izvestaj.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}
}
	
