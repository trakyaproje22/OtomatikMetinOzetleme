package textsummarization;

public class Summarizing {
	//********
	//Burası pozitif kelimeler dizisi, yeni sayac eklemedim sendekinin ismiyle değiştirdim
	private String[] PositiveWords= {"özetle","sonuçta","kısaca","kısacası","sonuç olarak","neticede", "en önemlisi"};
	private String satir;
	//********
	private String[] stopWords = { "acaba", "ama", "ancak", "artık", "asla", "aslında", "az", "altmış", "altı", "arada",
			"ayrıca", "bana", "bazen", "bazı", "bazıları", "bazısı", "belki", "ben", "beni", "benden", "beri", "benim",
			"beş", "bile", "bin", "bir", "birçoğu", "birçok", "birçokları", "biri", "birisi", "birkaç", "birkaçı",
			"birkez", "birşey", "birşeyi", "biz", "bize", "bizden", "bizi", "bizim", "böyle", "böylece", "bu", "buna",
			"bunda", "bundan", "bunlar", "bunları", "bunların", "bunu", "bunun", "burada", "bütün", "çoğu", "çoğuna",
			"çoğunu", "çok", "çünkü", "da", "daha", "de", "dahi", "defa", "değil", "demek", "diğer", "diğeri",
			"diğerleri", "diye", "dolayı", "doksan", "dokuz", "dolayı", "dolayısıyla", "dört", "elbette", "en",
			"edecek", "eden", "ederek", "edilecek", "ediliyor", "edilmesi", "ediyor", "eğer", "elli", "etmesi", "etti",
			"ettiği", "ettiğini", "fakat", "falan", "felan", "filan", "gene", "gibi", "göre", "halen", "hangi",
			"hangisi", "hani", "hatta", "hem", "henüz", "hep", "hepsi", "hepsine", "hepsini", "her", "her biri",
			"herhangi", "herkes", "herkese", "herkesi", "herkesin", "hiç", "hiç kimse", "hiçbiri", "hiçbirine",
			"hiçbirini", "için", "içinde", "ile", "ise", "işte", "iki", "ilgili", "itibaren", "itibariyle", "kaç",
			"kadar", "karşın", "katrilyon", "kendi", "kendine", "kendilerine", "kendini", "kendisi", "kendisine",
			"kendisini", "kez", "ki", "kim", "kimden", "kime", "kimi", "kimse", "kırk", "kimin", "kimisi", "madem",
			"mı", "mi", "milyar", "milyon", "mu", "mü", "nasıl", "ne", "ne kadar", "ne zaman", "neden", "nedenle",
			"nedir", "nerde", "nerede", "nereden", "nereye", "nesi", "neyse", "niçin", "niye", "o", "olan", "olarak",
			"oldu", "olduğu", "olduğunu", "olduklarını", "olmadı", "olmadığı", "olmak", "olması", "olmayan", "olmaz",
			"olsa", "olsun", "olup", "olur", "olursa", "oluyor", "on", "ona", "ondan", "onlar", "onlara", "onlardan",
			"onları", "onların", "onu", "onun", "otuz", "orada", "oysa", "oysaki", "öbürü", "ön", "önce", "ötürü",
			"öyle", "pek", "rağmen", "sadece", "sanki", "sana", "sekiz", "seksen", "sen", "senden", "seni", "senin",
			"siz", "sizden", "size", "sizi", "sizin", "son", "sonra", "seobilog", "şayet", "şey", "şeyden", "şeyi",
			"şeyler", "şimdi", "şöyle", "şu", "şuna", "şunda", "şundan", "şunlar", "şunları", "şunu", "şunun",
			"tarafından", "trilyon", "tabi", "tamam", "tüm", "tümü", "üzere", "üç", "üzere", "var", "vardı", "ve",
			"veya", "veyahut", "ya", "ya da", "yani", "yapacak", "yapılan", "yapılması", "yapıyor", "yapmak", "yaptı",
			"yaptığı", "yaptığını", "yaptıkları", "yedi", "yerine", "yetmiş", "yine", "yirmi", "yoksa", "yüz", "zaten",
			"zira" };

	private int[] counter = new int[stopWords.length];
	
	//**************Burası yeni eklenen yerler************
	
	
	// Pozitif kelime kontrolü yapılmakta.
	public void PositiveWord(String[][] Sentence) {
		String[][] str = Sentence;
		String[] word;

		try {
			
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");

				for (int j = 0; j < word.length; j++)
					for (int k = 0; k < PositiveWords.length; k++)
						if (word[j].equals(PositiveWords[k]) && !word[j].equals(null))
							counter[i] += 15;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("PositiveWord - " + i + " :" + counter[i]);
		}
	}
	
	//Baslikta geçen kelimelerin kontrolü
	public void titleWords(String[][] Sentence) {
	
		String[][] str = Sentence;
		String[] word;

		String[] titleWords;
		//Burada çift boyutlu dizide ki ilk cümleye ait olan kelimeleri başlık kelimesi olarak kabul ediyorum
		for(int j=0;j<str.length;j++) {
		    titleWords[j]+=str[0][j];
		    
		}
		
		try {
			
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");

				for (int j = 0; j < word.length; j++)
					for (int k = 0; k < titleWords.length; k++)
						if (word[j].equals(titleWords[k]) && !word[j].equals(null))
							counter[i] += 20;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("TitleWord - " + i + " :" + counter[i]);
		}
	}
	
	//Sozluk okuma kısmını buraya aldım ama başka alternatifte düşünebiliriz
	//OzelIsımler sınıfında da kalabilir oradan çağırırız fonksiyonu
	  public String[] SozlukOku(String str) throws IOException{
		List<String> liste = new ArrayList <String>();
		String[] dizi = new String[liste.size()];
		File dosya = new File(str);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dosya), "ISO-8859-9"));
		while((satir = br.readLine()) != null){
			liste.add(satir);	
		}
		
		for(int i=0;i<liste.size();i++) {
			dizi[i]=liste.get(i);
		}
		

		br.close();
		return dizi;
	}
	//*****
	//OzelIsım buldurma kısmında istediğimiz sozluk yolunu verip okutabiliriz organizasyon ,yer ismi
	// ve ozel isim dahildir bu kısma
	//sözlükler bende silinmiş mailden de bulamıyorum kontrolünü yapamadım ama hata vermiyor kodda.
	public void ProperNoun(String[][] Sentence,String dictionary) {
		String[][] str = Sentence;
		String[] word=new String[str.length];
		String[] sozluk=new String[str.length];
		try {
			sozluk = SozlukOku(dictionary);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		try {
			
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");

				for (int j = 0; j < word.length; j++)
					for (int k = 0; k < sozluk.length; k++)
						if (word[j].equals(sozluk[k]) && !word[j].equals(null))
							counter[i] += 3;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("ProperNoun - " + i + " :" + counter[i]);
		}
			
		}
	//****************Burada son buluyor yaptıklarım****************

	// ****
	// Negatif kelime kontrolü yapılmaktadır.
	public void NegativeWord(String[][] Sentence) {
		String[][] str = Sentence;
		String[] word;

		try {
			// Gelen çift boyutlu diziyi bir string değişkenine atadık.
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");

				for (int j = 0; j < word.length; j++)
					for (int k = 0; k < stopWords.length; k++)
						if (word[j].equals(stopWords[k]) && !word[j].equals(null))
							counter[i] += -20;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("NegativeWord - " + i + " :" + counter[i]);
		}
	}

	// ****
	// Eğer anahtar kelimler girilirse ona göre puan değerleri atanıyor.
	public void KeyWord(String[][] Sentence, String key) {
		String[] decomposition = key.split(",");
		String[][] str = Sentence;
		String[] word;

		// Burada kelimelerin kökünü alınacak mı ??
		try {
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");
				for (int j = 0; j < str[i].length; j++) {
					for (int k = 0; k < decomposition.length; k++)
						if (word[j].equals(decomposition[k]) && !word[j].equals(null))
							counter[i] += 8;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("KeyWord - " + i + " :" + counter[i]);
		}
	}

	// ****
	// Buranın kontrolü yapıldı şuanda ilk ve son paragraftaki cümlelerin puanlarını
	// atanıyor.
	public void SentencePosition(String Sentence) {
		String[] paragraph;
		String[] firstParagraph, lastParagraph;
		String[] SentenceCount;

		String firstParagraphSentence, lastParagraphSentence;

		paragraph = Sentence.split("(, ,)|(\\[)|(\\])");
		SentenceCount = Sentence.split("(\\.)|(\\[)|(\\])");

		firstParagraphSentence = paragraph[2];
		firstParagraph = firstParagraphSentence.split("\\.");

		for (int i = 0; i < firstParagraph.length; i++) {
			counter[i] += 10;
		}

		lastParagraphSentence = paragraph[paragraph.length - 1];
		lastParagraph = lastParagraphSentence.split("\\.");
		for (int i = SentenceCount.length - 2; i >= (SentenceCount.length - lastParagraph.length - 1); i--) {
			counter[i] += 10;
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("SentencePosition - " + i + " :" + counter[i]);
		}

	}

	// *****
	// Burda her cümlenin puanına bakılarak özet e eklenecek olan cümle
	// belirlencektir.
	// Burası henüz denenmedi, kontroller yapıldıktan sonra tekrar düzeltmeler
	// yapılabilir.
	public String SortignSentence(String Sentence) {

		String[] paragraph;
		String str = null;

		paragraph = Sentence.split("(, ,)|(\\[)|(\\])|(\\.)");
		for (int i = 0; i < counter.length; i++) {
			if (counter[i] > 0) {
				str = paragraph[i] + " ";
			}
		}

		return str;

	}
}
