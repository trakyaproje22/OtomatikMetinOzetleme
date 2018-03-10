package textsummarization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRW {
	public String[] kelime = null, kok;

	private String satir;
	public List<String> lst = new ArrayList<String>();
	private File dosya;

	public String FileRead(String path) throws IOException {
		dosya = new File(path);

		FileReader fr = new FileReader(dosya);
		BufferedReader br = new BufferedReader(fr);

		while ((satir = br.readLine()) != null) {
			lst.add(satir);
		}

		String temp;
		for (String list : lst) {
			temp = list.toLowerCase();
		}

		String text;
		text = lst.toString();

		br.close();
		fr.close();

		return text;
	}

	public void FileWrite(String path) throws IOException {
		dosya = new File("C:/YeniMetinler/OZET METINLER/Metinler(1-5)/1.txt");
		if (!dosya.exists())
			dosya.createNewFile();

		FileWriter fw = new FileWriter(dosya, true);
		BufferedWriter bw = new BufferedWriter(fw);

		// bw.write(str + " ");

		bw.close();
		fw.close();
	}
}
