package textsummarization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CustomNames {
	private String satir;

	public void OzelIsim() throws IOException {

		// Organizasyon("C:/YeniMetinler/OZET METINLER/Metinler(1-5)/4.txt");

		OzelIsim(satir, "C:/YeniMetinler/OZET METINLER/Metinler(1-5)/4.txt");

		// YerIsmi("C:/YeniMetinler/OZET METINLER/Metinler(1-5)/4.txt");

	}

	private List<String> SozcukleriOku(String str) throws IOException {
		List<String> liste = new ArrayList<String>();
		File dosya = new File(str);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dosya), "ISO-8859-9"));
		while ((satir = br.readLine()) != null) {
			liste.add(satir);
		}

		br.close();
		return liste;
	}

	private List<String> MetinlerOku(String str) throws IOException {
		List<String> liste = new ArrayList<String>();
		File dosya = new File(str);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dosya), "UTF-8"));
		while ((satir = br.readLine()) != null) {
			liste.add(satir);
		}

		br.close();
		return liste;
	}

	private void Organizasyon(String sozluk, String metin) throws IOException {
		List<String> OrganizasyonSozlugu = SozcukleriOku(sozluk);
		List<String> Metin = MetinlerOku(metin);

		String[] str = new String[Metin.size()];
		String gecici;
		gecici = Metin.toString();
		str = gecici.split("( )|(\\')");

		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < OrganizasyonSozlugu.size(); j++) {
				if (str[i].equals(OrganizasyonSozlugu.get(j)) && !str[i].equals("")) {
					System.out.println(str[i] + " - Organizasyon ismidir.");
				}
			}
		}
	}

	private void OzelIsim(String sozluk, String metin) throws IOException {
		List<String> KisiSozlugu = SozcukleriOku(sozluk);
		List<String> Metin = MetinlerOku(metin);

		String[] str = new String[Metin.size()];
		String gecici;
		gecici = Metin.toString();
		str = gecici.split("( )|(\\')");

		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < KisiSozlugu.size(); j++) {
				if (str[i].equals(KisiSozlugu.get(j)) && !str[i].equals("")) {
					System.out.println(str[i] + " - Özel Bir İsimdir.");
				}
			}
		}
	}

	private void YerIsmi(String sozluk, String metin) throws IOException {
		List<String> YerIsimleriSozlugu = SozcukleriOku(sozluk);
		List<String> Metin = MetinlerOku(metin);

		String[] str = new String[Metin.size()];
		String gecici;
		gecici = Metin.toString();
		str = gecici.split("( )|(\\')");

		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < YerIsimleriSozlugu.size(); j++) {
				if (str[i].equals(YerIsimleriSozlugu.get(j)) && !str[i].equals("")) {
					System.out.println(str[i] + " - Yer(Ülke-Şehir) İsmidir.");
				}
			}
		}
	}
}
