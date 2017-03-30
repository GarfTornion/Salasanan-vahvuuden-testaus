import java.util.Scanner;
import java.io.*;

public class salasanan_vahvuus {

	private static final Scanner user_input = new Scanner( System.in );
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		boolean lopetus = false;
		System.out.println("Tervetuloa salasanoja testaavaan ohjelmaan!");
		System.out.println("Pääset alkuun seuraavilla komennoilla:");
		System.out.println("testaa	 = käynnistä salasanan testaus");
		System.out.println("historia = näytä testattujen salasanojen historia ja tulokset");
		System.out.println("lopeta	 = sulje ohjelma");
		do {
			String komento = user_input.next();
			if (komento.equals("testaa")) {
				final Scanner lukija = new Scanner(new File("kriteerit.txt"));
				String rivi = " ";
				
				while ( lukija.hasNext() ) {
					rivi = lukija.nextLine();
					System.out.println(rivi);
				}//tiedoston lukeminen
				lukija.close();
				
				String salasana = user_input.next();
				int tulokset[] = tarkista_kriteerit(salasana);
				System.out.println("Salasanasi täytti " + tulokset[0] + "/" + tulokset[1] + " kriteereistä.");
				vahvuus(tulokset);
				
				PrintWriter kirjoittaja = new PrintWriter(new FileWriter("historia.txt", true));
				kirjoittaja.println(salasana + " " + tulokset[0] + "/" + tulokset[1]);
				kirjoittaja.close();
			}//if
			else if (komento.equals("historia")) {
				final Scanner tiedosto_lukija = new Scanner(new File("historia.txt"));
				while (tiedosto_lukija.hasNext()) {
					String sisältö = tiedosto_lukija.nextLine();
					System.out.println(sisältö);
				}//while
				tiedosto_lukija.close();
			}//else if
			else if (komento.equals("lopeta")) {
				lopetus = true;
			}//else if
			else
				System.out.println("Ohjelma ei tunnista komentoa.");
		} while (lopetus == false);
		
	}//main

	public static int[] tarkista_kriteerit(String salasana) {
		boolean kriteerit[] = {pituus(salasana), tarkista_numero(salasana), tarkista_kirjainkoko(salasana),
		tarkista_erikoismerkki(salasana), tarkista_hupsu(salasana), tarkista_vastaus(salasana),
		tarkista_gaben(salasana), tarkista_hymio(salasana)};
		int läpäissyt = 0;
		int totaali = 0;
		for (int i = 0; i < kriteerit.length; i++) {
			if (kriteerit[i]) {
				System.out.println("Kriteeri " + (i+1) + " täytetty: TRUE");
				läpäissyt = läpäissyt + 1;
				totaali = totaali + 1;
				}
			else {
				System.out.println("Kriteeri " + (i+1) + " täytetty: FALSE");
				totaali = totaali + 1;
			}
		}
		return new int[] {läpäissyt, totaali};
	}//kriteerit
		
	public static boolean pituus(String salasana) {
		if (salasana.length() >= 10) {
			return true;
		}
		else {
			return false;
		}
	}//pituus
	
	public static void vahvuus(int[] tulokset) {
		if (tulokset[0] == tulokset[1]) {
			System.out.println("Onneksi olkoon! Salasanasi täyttää kaikki kriteerit!");
		}
		else {
			System.out.println("Valitettavasti salasanasi ei täytä kaikki kriteerejä, kokeile uudelleen!");
		}
	}//vahvuus
	
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
	
	public static boolean tarkista_hupsu(String salasana) {
		if (salasana.indexOf("hupsu") != -1)
			return true;
		else
			return false;
	}//tarkista_hupsu
	
	public static boolean tarkista_vastaus(String salasana) {
		if (salasana.indexOf("11") != -1)
			return true;
		else
			return false;
	}//tarkista_vastaus
	
	public static boolean tarkista_gaben(String salasana) {
		if (salasana.indexOf("gaben") != -1)
			return true;
		else
			return false;
	}//tarkista_gaben
	
	public static boolean tarkista_hymio(String salasana) {
		if (salasana.indexOf(":D") != -1)
			return true;
		else
			return false;
	}//tarkista_hymio
	
	
}//salasanan_vahvuus
