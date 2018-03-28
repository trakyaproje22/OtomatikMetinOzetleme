package textsummarization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileRW {
	public String[] kelime = null, kok;

	private String satir;
	public List<String> lst = new ArrayList<String>();
	private File dosya;

	public String FileRead(String path) throws IOException {
		dosya = new File(path);

		System.setProperty("file.encoding", "UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dosya),"UTF8"));
		//dosyayı utf-8 formatında çözüyor.

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
