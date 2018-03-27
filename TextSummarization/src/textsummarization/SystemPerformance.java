package textsummarization;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemPerformance {

	
	private Summarizing SM = new Summarizing();
	private FileRW fileRead = new FileRW();
	private static SystemPerformance SP = new SystemPerformance();
	private SentenceIdentification SI = new SentenceIdentification();
	
	private String file;
	private String[][] str;
	public List<String> list = new ArrayList<String>();

	private int counter=0,userCounter=0, ortalama=0;
	
	//Kullanıcının kendi çıkardığı özetlerin bulunduğu dosyayı okuma fonksiyonu 
	public String[][] userSummar(String filePath) {
	
		try {
			
			
			file=fileRead.FileRead(filePath);// FileRead fonksiyonu ile dosyayı okuyup 
			str=SI.Cumle(file);              //Cümlelerine ayırdım ve bu değişkeni döndürdüm
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	
	//özetleme fonksiyonumuzu dosyadan okuduğumuz metne uygulama
	public String[][] systemSummar(String txt) {
		try {
			
			String fp=fileRead.FileRead(txt);					  //dosyayı okuyoruz
			String summarStr=SM.SortignSentence(fp);  //okunan dosyayı kendi özetleme fonksiyonumuza göre özetliyoruz
			
			str=SI.Cumle(summarStr.toString());// bunu çift boyutlu bir değişkene cümlelere ayrılmış bir şekilde atıyoruz
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return str;
		
		
	}
	
	//Hesaplama fonksiyonumuzu yazıyoruz 
	public void calculatorSystem(String systemfilePath, String userfilePath, int d ) {
		
		
		String[][] sistemozeti=SP.systemSummar(systemfilePath); //sistemin yaptığı özetleme fonksiyonunun sonucunu diziye atıyoruz
		String[][] kullaniciozeti=SP.userSummar(userfilePath);	// kullanıcının yaptığı özeti diziye atıyoruz
		
		for(int i=0;i<kullaniciozeti.length;i++) {  // burada sistem özeti ile kullanıcı özeti karşılaştırılıp
			for(int j=0;j<sistemozeti.length;j++) { // her ikisinde de ortak bulunan cümle sayısı hesaplanıyor
				if(kullaniciozeti[i].equals(sistemozeti[j]) && !kullaniciozeti[i].equals(null) && !sistemozeti[j].equals(null)) {
					counter++;
				}
			}
		}
		
				for(int k=0;k<kullaniciozeti.length;k++) { // kullanıcının yaptığı özette ki cümle sayısı hesaplanıyor
					userCounter++;
		}
		ortalama=counter/userCounter; // ortalama alınıyor
		
		System.out.println(ortalama);
	}
	public static void main(String[] args) throws IOException{
		
		String filePath=("C://Users//Kubra//Documents//2_ozet.txt");
		String filePathOrjinal=("C://Users//Kubra//Documents//2.txt");
		
		
		SP.calculatorSystem(filePathOrjinal, filePath, 1);
		
	}
}
