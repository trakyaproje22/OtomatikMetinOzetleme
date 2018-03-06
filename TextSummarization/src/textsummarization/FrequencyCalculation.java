package textsummarization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FrequencyCalculation {
	static FileRW dI = new FileRW();
	List<String> liste = new ArrayList<String>();
	private String[] kelime = new String[750];
	private int[] KTekrar = new int[750];

	static int FToplam = 0;

	static float ortalama = 0;
	private String satir;

	public void frekansHesapla() throws IOException {
		File file = new File("C:/YeniMetinler/OZET METINLER/Metinler(1-5)/4.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

		// String line;

		while ((satir = br.readLine()) != null) {
			kelime = satir.split("( )|(\\.)|(\\')|(\\,)|(\\?)");
			for (int i = 0; i < kelime.length; i++) {
				liste.add(kelime[i]);
			}
		}

		// kok = new String[kelime.length*15];

		satir = liste.toString();
		kelime = satir.split("( )|(\\.)|(\\')|(\\,)|(\\?)");

		int i, j, k;
		List<String> list = new ArrayList<String>();
		for (i = 0; i < kelime.length; i++) {
			if (list.contains(kelime[i]))
				continue;
			list.add(kelime[i]);
			for (j = 0; j < kelime.length; j++) {
				if (kelime[i].equals(kelime[j])) {
					KTekrar[i]++;
				}

			}

			FToplam += KTekrar[i];
			ortalama = FToplam / list.size();
		}

		for (k = 0; k < KTekrar.length; k++) {
			if (KTekrar[k] > ortalama && KTekrar[k] < 375) {
				System.out.println("Kelime: " + kelime[k] + " - Frekansı: " + KTekrar[k]);
			}
		}

		//
		// for(i = 0; i < kelime.length; i++ ){
		// if(kelime[i] != null && KTekrar[i] != 0)
		// System.out.println("Kelime: "+kelime[i]+" - Frekansı: "+KTekrar[i]);
		//
		// }
		System.out.println("Toplam Frekans: " + FToplam + " - Ortalama: " + ortalama);

		fr.close();
		br.close();

	}
}
