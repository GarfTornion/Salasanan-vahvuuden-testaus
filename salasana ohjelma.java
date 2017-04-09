import java.util.Scanner;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *   <code>salasanan_vahvuus</code> luokka esittää salasanoja testaavan ohjelman toiminnan.
 * @author Billy Ward
 * @author Emil Oksanen
 */
public class salasanan_vahvuus {

	private static final Scanner user_input = new Scanner( System.in );
	private static final DateFormat aikatyyli = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		boolean lopetus = false;
		System.out.println("Tervetuloa salasanoja testaavaan ohjelmaan!");
		System.out.println("Pääset alkuun seuraavilla komennoilla:");
		System.out.println("testaa	 = käynnistä salasanan testaus");
		System.out.println("historia = näytä testattujen salasanojen historia ja tulokset");
		System.out.println("tyhjennä = tyhjennä historia");
		System.out.println("lopeta	 = sulje ohjelma");
		do {
			System.out.print(">");
			String komento = user_input.next();
			if (komento.equals("testaa")) {
				try {
				final Scanner lukija = new Scanner(new File("kriteerit.txt"));
				String rivi = " ";
				
				while ( lukija.hasNext() ) {
					rivi = lukija.nextLine();
					System.out.println(rivi);
				}//tiedoston lukeminen - while
				lukija.close();
				}//try
				catch (FileNotFoundException e) {
					System.out.println("Salasanasi tulee täyttää seuraavat kriteerit:");
					System.out.println("Kriteeri 1: Salasanan tulee olla 10 merkkiä pitkä");
					System.out.println("Kriteeri 2: Salasanan tulee sisältää vähintään yksi numero");
					System.out.println("Kriteeri 3: Salasanan tulee sisältää pieniä ja isoja kirjaimia");
					System.out.println("Kriteeri 4: Salasanan tulee sisältää vähintään yksi erikoismerkki");
					System.out.println("Kriteeri 5: Salasanan tulee sisältää sana 'hupsu'");
					System.out.println("Kriteeri 6: Salasanan tulee sisältää yhtälön 5-10+2^4 vastaus");
					System.out.println("Kriteeri 7: Salasanan tulee sisältää sana 'gaben'");
					System.out.println("Kriteeri 8: Salasanan tulee sisältää hymiö ':D'");
					System.out.println("Syötä testattava salasana: ");
				}//catch
				System.out.print(">");
				String salasana = user_input.next();
				int tulokset[] = tarkista_kriteerit(salasana);
				System.out.println("Salasanasi täytti " + tulokset[0] + "/" + tulokset[1] + " kriteereistä.");
				tarkista_vahvuus(tulokset);
				
				Date paivamaara = new Date();
				
				PrintWriter kirjoittaja = new PrintWriter(new FileWriter("historia.txt", true));
				kirjoittaja.println(aikatyyli.format(paivamaara) + " " + salasana + " " + tulokset[0] + "/" + tulokset[1]);
				kirjoittaja.close();
			}//if
			
			else if (komento.equals("historia")) {
				try {
					final Scanner tiedosto_lukija = new Scanner(new File("historia.txt"));
				while (tiedosto_lukija.hasNext()) {
					String sisalto = tiedosto_lukija.nextLine();
					System.out.println(sisalto);
				}//while
				tiedosto_lukija.close();
				}//try
				catch (FileNotFoundException f) {
					System.out.println("Hupsista! Tapahtui " + f + "-virhe.");
					System.out.println("Kokeile ensin testata ohjelmalla jotain salasanaa, ja sitten kokeile komentoa uudestaan.");
				}//catch
			}//else if
			else if (komento.equals("tyhjennä")) {
				try {
					System.out.println("Historia tyhjennetään...");
					PrintWriter kirjoittaja = new PrintWriter(new FileWriter("historia.txt"));
					kirjoittaja.flush();
					kirjoittaja.close();
					System.out.println("Historia on tyhjennetty!");
				}//try
				catch (FileNotFoundException f) {
					System.out.println("Ei ole tyhjennettävää historiaa!");
				}//catch
			}//else if
			else if (komento.equals("lopeta")) {
				lopetus = true;
			}//else if
			else
				System.out.println("Ohjelma ei tunnista komentoa.");
		}//do
		
		while (lopetus == false);
		
	}//main

	/**
	 * <code>Tarkista_kriteerit</code> metodi tarkistaa täyttääkö annettu syöte
	 * kriteerit. Metodi kutsuu sisällää alempia metodeja jotka palauttavat
	 * boolean-tyyppisiä arvoja.
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa taulukon, joka sisältää kaksi arvoa. Ensimmäinen arvo
	 *         on kriteerit täyttäneet ominaisuudet, ja toinen arvo on
	 *         testattujen kriteerien lukumäärä.
	 */
	public static int[] tarkista_kriteerit(String salasana) {
		boolean kriteerit[] = {tarkista_pituus(salasana), tarkista_numero(salasana), tarkista_kirjainkoko(salasana),
		tarkista_erikoismerkki(salasana), tarkista_hupsu(salasana), tarkista_vastaus(salasana),
		tarkista_gaben(salasana), tarkista_hymio(salasana)};
		int lapaissyt = 0;
		int totaali = 0;
		for (int i = 0; i < kriteerit.length; i++) {
			if (kriteerit[i]) {
				System.out.println("Kriteeri " + (i+1) + " täytetty: TRUE");
				lapaissyt = lapaissyt + 1;
				totaali = totaali + 1;
				}//if
			else {
				System.out.println("Kriteeri " + (i+1) + " täytetty: FALSE");
				totaali = totaali + 1;
			}//else
		}//for
		return new int[] {lapaissyt, totaali};
	}//kriteerit
		
	/**
	 * <code>Tarkista_pituus</code>-metodi tarkistaa onko syötteen pituus
	 * annettujen ohjeiden mukainen.
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa totuus-arvon.
	 */
	public static boolean tarkista_pituus(String salasana) {
		if (salasana.length() >= 10) {
			return true;
		}
		else {
			return false;
		}
	}//pituus
	
	/**
	 * <code>Tarkista_vahvuus</code>-metodi antaa käyttäjälle viimeisen
	 * palautteen salasanasta.
	 * 
	 * @param tulokset
	 *            Täytettyjen kriteerien ja kaikkien kriteerien lukumäärä.
	 *            Saadaan <code>tarkista_kriteerit</code>-metodista.
	 */
	public static void tarkista_vahvuus(int[] tulokset) {
		if (tulokset[0] == tulokset[1]) {
			System.out.println("Onneksi olkoon! Salasanasi täyttää kaikki kriteerit!");
		}
		else if (tulokset[0] == 0) {
			System.out.println("Salasanasi ei täyttänyt yhtään kriteeriä! ÄLÄ MISSÄÄN NIMESSÄ KÄYTÄ TÄTÄ SALASANAA!");
		}
		else
			System.out.println("Salasanasi ei täyttäny kaikkia kriteerejä!");
	}//vahvuus
	
	/**
	 * <code>Tarkista_numero</code>-metodi tarkistaa onko annetussa syötteessä
	 * numeroa.
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa totuus-arvon.
	 */
	public static boolean tarkista_numero(String salasana) {
		int i = 0;
		int numero = 0;
		
		do {
			char a = salasana.charAt(i);
			if (Character.isDigit(a))
				numero = 1;
			i++;
		} while (i < salasana.length() && (numero == 0));
		if (numero == 1)
			return true;
		else
			return false;
	}//tarkista_numero
	
	/**
	 * <code>Tarkista_erikoismerkki</code>-metodi tarkistaa onko annetussa
	 * syötteessä erikoismerkkiä.
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa totuus-arvon.
	 */
	public static boolean tarkista_erikoismerkki(String salasana) {
		int i = 0;
		int erikoismerkki = 0;
		do {
			char a = salasana.charAt(i);
			if (Character.isJavaIdentifierPart(a) && !Character.isLetterOrDigit(a))
				erikoismerkki = 1;
			i++;
		} while (i < salasana.length() && (erikoismerkki == 0));
		if (erikoismerkki == 1)
			return true;
		else
			return false;
	}//tarkista_erikoismerkki
		
	/**
	 * <code>Tarkista_kirjainkoko</code>-metodi tarkistaa löytyykö annetusta
	 * syötteestä sekä isoja, että pienie kirjaimia.
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa totuus-arvon.
	 */
	public static boolean tarkista_kirjainkoko(String salasana) {
		int i = 0;
		int numero1 = 0;
		int numero2 = 0;
		
		do {
			char a = salasana.charAt(i);
			if (Character.isUpperCase(a) && (numero1 == 0)) 
				numero1 = 1;
			
			if (Character.isLowerCase(a) && (numero2 == 0))
				numero2 = 1;
			i++;

		} while (i < salasana.length() &&! (numero1 == 1 && numero2 == 1));
		if (numero1 == 1 && numero2 == 1)
			return true;
		else
			return false;
	}//tarkista_kirjainkoko
	
	/**
	 * <code>Tarkista_hupsu</code>-metodi tarkistaa onko syötteessä merkkijonoa
	 * "hupsu".
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa totuus-arvon.
	 */
	public static boolean tarkista_hupsu(String salasana) {
		if (salasana.indexOf("hupsu") != -1)
			return true;
		else
			return false;
	}//tarkista_hupsu
	
	/**
	 * <code>Tarkista_vastaus</code>-metodi tarkistaa onko syötteessä vastausta
	 * kysyttyyn yhtälöön.
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa totuus-arvon.
	 */
	public static boolean tarkista_vastaus(String salasana) {
		if (salasana.indexOf("11") != -1)
			return true;
		else
			return false;
	}//tarkista_vastaus
	
	/**
	 * <code>Tarkista_gaben</code>-metodi tarkistaa onko syötteessä merkkijonoa
	 * "gaben".
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa totuus-arvon.
	 */
	public static boolean tarkista_gaben(String salasana) {
		if (salasana.indexOf("gaben") != -1)
			return true;
		else
			return false;
	}//tarkista_gaben
	
	/**
	 * <code>Tarkista_hymio</code>-metodi tarkistaa löytyykö syötteestä
	 * :D-hymiötä.
	 * 
	 * @param salasana
	 *            Käyttäjän antama salasana-syöte.
	 * @return Palauttaa totuus-arvon.
	 */
	public static boolean tarkista_hymio(String salasana) {
		if (salasana.indexOf(":D") != -1)
			return true;
		else
			return false;
	}//tarkista_hymio
	
	
}//salasanan_vahvuus
