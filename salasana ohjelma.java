import java.util.Scanner;

public class salasanan_vahvuus {

	private static final Scanner user_input = new Scanner( System.in );
	
	public static void main(String[] args) {
		System.out.println("Tervetuloa testaamaan salasanasi vahvuutta!");
		System.out.println("Salasanasi tulee täyttää seuraavat kriteerit:");
		System.out.println("Kriteeri 1: Salasanan tulee olla 10 merkkiä pitkä");
		System.out.println("Kriteeri 2: Salasanan tulee sisältää vähintään yksi numero");
		System.out.println("Kriteeri 3: Salasanan tulee sisältää pieniä ja isoja kirjaimia");
		System.out.println("Kriteeri 4: Salasanan tulee sisältää vähintään yksi erikoismerkki");
		System.out.println("Kriteeri 5: Salasanan tulee sisältää sana 'hupsu'");
		System.out.println("Syötä testattava salasana:");
		String salasana = user_input.next();
		int tulokset[] = tarkista_kriteerit(salasana);
		System.out.println("Salasanasi täytti " + tulokset[0] + "/" + tulokset[1] + " kriteereistä.");
		vahvuus(tulokset);
		
	}//main

	public static int[] tarkista_kriteerit(String salasana) {
		boolean kriteerit[] = {pituus(salasana), tarkista_numero(salasana), kirjainkoko(salasana),
		tarkista_erikoismerkki(salasana), tarkista_hupsu(salasana)};
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
		

	public static boolean kirjainkoko(String salasana) {
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
	}//kirjainkoko
	
	public static boolean tarkista_hupsu(String salasana) {
		if (salasana.indexOf("hupsu") != -1)
			return true;
		else
			return false;
	}//tarkista_hupsu
	
	
}//salasanan_vahvuus
