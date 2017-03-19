import java.util.Scanner;

public class salasanan_vahvuus {

	private static final Scanner user_input = new Scanner( System.in );
	
	public static void main(String[] args) {
		System.out.println("Tervetuloa testaamaan salasanasi vahvuutta!");
		System.out.println("Salasanasi tulee t‰ytt‰‰ seuraavat kriteerit:");
		System.out.println("Kriteeri 1: Salasanan tulee olla 10 merkki‰ pitk‰");
		System.out.println("Syˆt‰ testattava salasana:");
		String salasana = user_input.next();
		int tulokset[] = kriteerit(salasana);
		System.out.println("Salasanasi t‰ytti " + tulokset[0] + "/" + tulokset[1] + " kriteereist‰.");
		vahvuus(tulokset);
		
	}//main

	public static int[] kriteerit(String salasana) {
		int l‰p‰issyt = 0;
		int totaali = 0;
		if (pituus(salasana)) {
			System.out.println("Kriteeri 1 t‰ytetty: TRUE");
			l‰p‰issyt = l‰p‰issyt + 1;
			totaali = totaali + 1;
			}
		else {
			System.out.println("Kriteeri 1 t‰ytetty: FALSE");
			totaali = totaali + 1;
		}
		return new int[] {l‰p‰issyt, totaali};
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
			System.out.println("Onneksi olkoon! Salasanasi t‰ytt‰‰ kaikki kriteerit!");
		}
		else {
			System.out.println("Valitettavasti salasanasi ei t‰yt‰ kaikki kriteerej‰, kokeile uudelleen!");
		}
	}//vahvuus
	
		
}//salasanan_vahvuus
