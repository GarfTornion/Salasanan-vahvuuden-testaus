import java.util.Scanner;

public class salasanan_vahvuus {

	private static final Scanner user_input = new Scanner( System.in );
	
	public static void main(String[] args) {
		System.out.println("Tervetuloa testaamaan salasanasi vahvuutta!");
		System.out.println("Salasanasi tulee täyttää seuraavat kriteerit:");
		System.out.println("Kriteeri 1: Salasanan tulee olla 10 merkkiä pitkä");
		System.out.println("Syötä testattava salasana:");
		String salasana = user_input.next();
		int tulokset[] = kriteerit(salasana);
		System.out.println("Salasanasi täytti " + tulokset[0] + "/" + tulokset[1] + " kriteereistä.");
		vahvuus(tulokset);
		
	}//main

	public static int[] kriteerit(String salasana) {
		int läpäissyt = 0;
		int totaali = 0;
		if (pituus(salasana)) {
			System.out.println("Kriteeri 1 täytetty: TRUE");
			läpäissyt = läpäissyt + 1;
			totaali = totaali + 1;
			}
		else {
			System.out.println("Kriteeri 1 täytetty: FALSE");
			totaali = totaali + 1;
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
	
		
}//salasanan_vahvuus
