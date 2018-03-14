package textsummarization;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.zemberek.erisim.Zemberek;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;

public class Summarizing {
	// ********
	// Burası pozitif kelimeler dizisi, yeni sayac eklemedim sendekinin ismiyle
	// değiştirdim
	private String[] PositiveWords = { "özetle", "sonuçta", "kısaca", "kısacası", "sonuç olarak", "neticede",
			"en önemlisi" };
	private String satir;
	
	//harf dizisi 
	String[] harf;
	//Buyuk Harf dizisi tanımı
	private String[] buyukHarf= {"A","B","C","D","E","F","G","H","I","İ","K","L","M","N","O","Ö","P","R","S","T","U","Ü","V","Y","Z"};
	
	
	//KisiSözlugunu diziye çevirdim hala da devam etmekteyim eklemeleri yapıcam 
	//Aynı şekilde organizasyon ve yer isimleri içinde yapıyorum
	//Sizce hepsi bir dizide mi olsun ayrı dizide mi??
	private String[] KisiSozlugu= {"Ladin","Usame","Richard Holbrooke","Timothy Garton Ash","Charles de Gaulle","Ferid Zekeriya","Giscard","Dominique Moisi",
			"Baykal","Uğur Dündar","Neşe Düzel","Mehmet Ali Talat","Sami KohenKemal Derviş","Baykal","Şükrü Sina Gürel","Ali Coşkun",
			"Nelson Mandela","Makarios","Demirel","Erbakan","Doğan Avcıoğlu","Uluç Gürkan","Şahin Alpay","Gorbaçov","Memet Fuat",
			"Nazım Hikmet","Mümtaz Soysal","Necip Hablemitoğlu","Abdullah Gül","erdem beyaz","Megi","megi berberi","Tayyip","Gül",
			"Tayyip Erdogan","Ricard Haass","Yoshinori Imai","Dani Rodrik","Mandela","Atatürk","Tayyip","Özal","Annan","Demirel","Ecevit",
			"Kofi Annan","Bahçeli","Colin Powell","Elif Melike Demir","Demet Şen","hasan cemal","ABİDE","AÇELYA","ADALET","ADİLE","AFET",
			"AFİFE","AFİTAP","AFŞAR","AHSEN","AHU","AJDA","AKASYA","AKGÜL","AKGÜN","AKİFE","ALARA","ALARCIN","ALÇİN","ALEV","ALEYNA","ALİYE","ALTAN",
			"ALTIN","ANDAÇ","ARİFE","ARZU","ASENA","ASİYE","ASLI","ASLIHAN","ASRIN","ASU","ASUDE","ASUMAN","ASYA","AŞKIN","ATEŞ","ATIFET","ATİKE","AYBEL"
			,"AYBEN","AYBÜKE","AYCA","AYCAN","AYÇA","AYÇİN","AYDA" ,"AYDAN","AYDİL","AYFER","AYGEN","AYGÜL","AYLA ","AYLİN","AYNUR","AYPARE"
			,"AYSEL","AYSEN","AYSU" ,"AYSUN","AYŞAN","AYŞE","AYŞEGÜL","AYŞEM","AYŞEN","AYŞENUR","AYŞİN","AYTAÇ","AYTEN","AYTÜL","AZİME","AZİZE","AZMİYE"
			,"AZRA","ABAY","ABBAS","ABDULLAH","ABİDİN","ACUN","ADEM","ADİL","ADNAN","AFFAN","AGAH","AHMET","AKAD","AKAY","AKEL","AKGÜN","AKIN","AKİF","AKİL"
			,"AKTAÇ","AKTAN","ALAADDİN","ALDEMİR","ALİ","ALİCAN","ALİM","ALİŞAN","ALKAN","ALKIN","ALP","ALPAR","ALPASLAN","ALPAY","ALPER","ALPHAN"
			,"ALPTEKİN","ALTAN","ALTAY","ALTUĞ","ANDAÇ","ANIL","ARAL","ARAS","ARCAN","ARDA","AREL","ARGÜN","ARIKAN","ARIN","ARİF","ARKAN","ARMAĞAN"
			,"ARMAN","ARSLAN","ARTAÇ","ASIM","ASİL","ASLAN","ASRIN","ASUTAY","AŞKIN","ATA","ATABEK","ATABEY","ATACAN ","ATAHAN","ATAK","ATAKAN","ATALAY"
			,"ATAMAN","ATANUR","ATASOY","ATAY","ATEŞ","ATIF","ATIL","ATILAY","ATALAY","ATILGAN","ATINÇ","ATİLLA","ATTİLA","ATLIHAN","AVNİ"
			,"AYBAR","AYBARS","AYBERK","AYDEMİR","AYDIN","AYGÜN","AYHAN","AYKAN","AYKUT","AYTAÇ","AYTEK","AYTEKİN","AYTUNÇ","AYYÜCE","AZER","AZİM"
			,"AZİZ","AZMİ","BAHAR","BAHRİYE","BALCA","BALIM","BANU","BARAN","BAŞAK","BAŞAR","BEDİA","BEDİHE","BEDRİYE","BEGÜM","BEHİCE","BEHİRE"
			,"BEHİYE","BELGİN","BELİN","BELKIS","BELMA","BENAN","BENGİ","BENGİSU","BENGÜ","BENGÜL","BENİAN","BENSU","BERAY","BERFİN","BERİA"
			,"BERİL","BERİN","BERNA","BERRA","BERRAK","BERRİN","BERŞAN","BESTE","BESTEGÜL","BETİGÜN","BETİL","BETÜL","BEYHAN","BEYZA"
			,"BİHTER","BİKE","BİKEM","BİLGE","BİLLUR","BİLUN","BİNGÜL","BİNNAZ","BİNNUR","BİRCAN","BİRCE","BİRGÜL","BİRİCİK","BİRSEN","BİRSU","BUCAK"
			,"BUKET","BURCU","BURÇAK" ,"BURÇİN","BUSE","BÜŞRA","BABÜR","BAHA","BAHADIR","BAHATTİN","BAHİR","BAHRİ","BAHTİYAR","BAKİ","BALA","BALABAN"
			,"BALER","BARAN","BARANSEL","BARBAROS","BARIN","BARIŞ","BARKAN","BARKIN","BARLAS","BARS","BASRİ","BAŞAR","BAŞER","BATI","BATIHAN","BATIKAN"
			,"BATIRAY","BATTAL","BATU","BATUHAN","BATUR","BATURALP","BAYAR","BAYBARS","BAYBORA","BAYCAN","BAYEZİT","BEYAZIT","BAYHAN","BAYKAL"
			,"BAYRAKTAR","BAYRAM","BAYSAL","BAYÜLKEN","BEDİR","BEDİRHAN","BEDRETTİN","BEDRİ","BEHÇET","BEHİÇ","BEHLÜL","BEHRAM","BEHZAT","BEKİ"
			,"BEKRİ","BEKİR","BEKTAŞ","BERAT","BERHAN","BERK","BERKAN","BERKANT","BERKAY","BERKE","BERMAL","BESİM","BİLAL","BİLGE","BİLGEHAN","BİLGİN"
			,"BİRANT","BİRCAN","BİROL","BOĞAÇ","BOĞAÇHAN","BORA","BORAN","BOZKURT","BUĞRA","BUĞRAHAN","BULUT","BUMİN","BURAK","BURÇ","BURÇAK","BURÇİN"
			,"BURHAN","BURHANETTİN","BÜLENT","BÜNYAMİN","CAHİDE ","CANAN","CANDAN","CANEL","CANKAT","CANSEL","CANSIN","CANSU","CAVİDAN","CELİLE"
			,"CEMİLE","CEMRE","CENNET","CEREN","CEVHER","CEVRİYE","CEYDA","CEYLA","CEYLAN","CEYLİN","CİHAN","CİHANNUR","CİLVENAZ","ÇAĞLA","ÇAĞLAYAN"
			,"ÇAĞRI","ÇİÇEK","ÇİĞDEM","ÇİLAY","ÇİLER","ÇİSEM","ÇOLPAN","CABBAR","CAFER","CAHİT","CAN","CANALP","CANBERK","CANDAŞ","CANDEMİR","CANDOĞAN"
			,"CANEL","CANER","CANKAT","CANKUT","CANSIN","CANTEKİN","CAVİT","CELAL","CELALETTİN","CELAYİR","CELİL","CEM","CEMAL","CEMALETTİN","CEMİL"
			,"CEMRE","CENAN","CENAP","CENGİZ","CENGİZHAN","CENK","CEVAHİR","CEVAT","CEVDET","CEYHAN","CEYHUN","CEZMİ","CİHAN","CİHANGİR","CİHAT","CİVAN"
			,"COŞAR","COŞKU","COŞKUN","CUMA","CUMHUR","CÜNEYT","ÇAĞAN","ÇAĞATAY","ÇAĞDAŞ","ÇAĞIN","ÇAĞLAR","ÇAĞMAN","ÇAĞRI","ÇAKABEY","ÇAKAR","ÇAKIN"
			,"ÇAKIR","ÇAVUŞ","ÇELEBİ","ÇELEN","ÇELİK","ÇELİKER","ÇETİN","ÇEVİK","ÇEVRİM","ÇIĞIR","ÇINAR","DAMLA","DEFNE","DELFİN","DEMET","DENİZ","DEREN"
			,"DERİN","DERYA","DESTAN","DESTE","DEVRAN","DİBA","DİCLE","DİCLEHAN","DİDE","DİDEM","DİLAN","DİLARA","DİLAY","DİLBERAN","DİLBERAY","DİLDAR"
			,"DİLEK","DİLER","DİLHAN","DİLRUBA","DİLŞAD","DOLUNAY","DÖNDÜ","DURU","DUYGU","DÜNYA","DÜRDANE","DÜRRİYE","DALAN","DALAY","DANİŞ","DARCAN","DAVUT"
			,"DEHA","DEMİR","DEMİRALP","DEMİRCAN","DEMİREL","DEMİRHAN","DEMİRKAN","DENİZ","DENİZHAN","DENKTAŞ","DERİN","DERVİŞ","DERYA","DEVLET"
			,"DEVRAN","DEVRİM","DİLAVER","DİLMEN","DİNÇ","DİNÇER","DOĞA","DOĞAN","DOĞU","DOĞUHAN","DOĞUKAN","DOĞUŞ","DORUK","DORUKHAN","DURAN","DURMUŞ"
			,"DURSUN","DURUKAN","DURUL","DUYAL","DÜNDAR","DÜNYA","EBRU","ECE","ECEHAN","ECEM","EDA","EDİBE","EDİS","EFSER","EFTALYA ","EGE","EKİM","EKİN"
			,"ELA","ELÇİN","ELİF","ELMAS","ELVAN","EMEL","EMİNE","ENGİN","ENİSE","ERDEN","ERGÜL","ERTAÇ","ESEN","ESER","ESİN","ESMA","ESMERAY","ESRA","EVREN"
			,"EVRİM","EVŞEN","EYLEM","EYLÜL","EYŞAN","EZGİ","EZRA","ECEVİT","EDİP","EDİZ","EFDAL","EFTAL","EFE","EFGAN","EFLATUN","EGE","EGEMEN","EJDER","EKBER"
			,"EKİN","EKREM","ELDEM","ELVAN","EMİN","EMİR","EMİRHAN","EMRAH","EMRE","EMRULLAH","ENDER","ENER","ENGİN","ENGİNSU","ENİS","ENSAR","ENVER","ERALP"
			,"ERAY","ERBATUR","ERBERK","ERCAN","ERCE","ERCÜMENT","ERÇİN","ERDAL","ERDEM","ERDEN","ERDİNÇ","ERDOĞAN","EREM","EREN","ERENAY","ERGİN","ERGUN"
			,"ERGÜN","ERHAN","ERHUN","ERİM","ERİNÇ","ERKAL","ERKAN","ERKİN","ERKUT","ERMAN","EROL","ERSAN","ERSEN","ERSİN","ERŞAT","ERTAÇ","ERTAN","ERTEM"
			,"ERTEN","ERTUĞRUL","ERYAMAN","ESAT","ESEN","ESER","EŞREF","EVGİN","EVREN","EVRİM","EYÜP","EZEL","FADİK","FADİME","FADİŞ","FAHİRE" ,"FAHRİYE"
			,"FAHRÜNİSSA","FATMA","FATMANUR","FATOŞ","FAZİLET","FEHİME","FERAH","FERAY","FERAYE","FERDA","FERHAN","FERHUNDE","FERİDE","FERİHA"
			,"FERZAN","FEVZİYE","FEYMAN","FEYZA","FEZA","FİDAN","FİGEN","FİKRET","FİKRİYE","FİLİZ","FİRDEVS","FİRUZE","FULDEM","FULDEN","FULYA","FUNDA"
			,"FÜREYYA","FÜRUZAN","FÜSUN","FADIL","FAHİR","FAHRETTİN","FAHRİ","FAİK","FAKİR","FALİH","FARUK","FATİH","FAZIL","FEHİM","FERDİ","FERHAN","FERHAT"
			,"FERİD","FERİDUN","FERİT","FERKAN","FERRUH","FETHİ","FEVZİ","FEYYAZ","FEYZİ","FEYZULLAH","FEZA","FIRAT","FİKRET","FİKRİ","FUAT","FURKAN"
			,"GAMZE","GAYE","GAZAL","GENCAY","GİZEM","GONCA","GÖKBEN","GÖKÇE","GÖKÇEN","GÖKNİL","GÖKNUR","GÖKSU","GÖKŞİN","GÖNÜL","GÖRKEM","GÖZDE","GÜHER"
			,"GÜL","GÜLAY","GÜLBAHAR","GÜLBEN","GÜLBİN","GÜLBİZ","GÜLCAN","GÜLÇİN","GÜLDEM","GÜLDEN","GÜLDEREN","GÜLDESTE","GÜLEN","GÜLENDAM","GÜLER"
			,"GÜLFEM","GÜLFİDAN","GÜLGÜN","GÜLHANIM","GÜLİN","GÜLİSTAN","GÜLİZ","GÜLİZAR","GÜLLÜ","GÜLNAZ","GÜLNİHAL","GÜLNUR","GÜLPEMBE","GÜLRİZ","GÜLSELİ"
			,"GÜLSEN","GÜLSEREN","GÜLSOY","GÜLSÜM","GÜLSÜN","GÜLŞAH","GÜLŞEN","GÜLTEN","GÜLÜMSER","GÜN","GÜNAL","GÜNER","GÜNEŞ","GÜNGÖR","GÜNİZ","GÜNNUR"
			,"GÜNSEL","GÜNSELİ","GÜRCAN","GÜVEN","GÜZİDE","GÜZİN","GAFFAR","GAFUR","GALİP","GANİ","GARİP","GAZANFER","GAZİ","GEDİZ","GENCAL","GENCALP"
			,"GENCAY","GENCER","GENCO","GİRAY","GİRGİN","GÖKALP","GÖKAY","GÖKBERK","GÖKCAN","GÖKÇE","GÖKÇEN","GÖKER","GÖKHAN","GÖKHUN","GÖKMEN"
			,"GÖKSEL","GÖKTAN","GÖKTUĞ","GÖKTÜRK","GÜÇHAN","GÜÇLÜ","GÜLHAN","GÜLTEKİN","GÜNALP","GÜNAY","GÜNDOĞDU","GÜNDÜZ","GÜNER","GÜNERİ","GÜNEŞ"
			,"GÜNEY","GÜNGÖR","GÜNHAN","GÜNSEL","GÜNSER","GÜNTAN","GÜNTEKİN","GÜRAL","GÜRALP","GÜRAY","GÜRBÜZ","GÜRCAN","GÜREL","GÜRKAN","GÜROL","GÜRSEL"
			,"GÜRSOY","GÜRTAN","GÜVEN","GÜVENÇ","GÜZEY","HABİBE","HACER","HAFİZE","HALE","HALENUR","HALİDE","HALİME","HAMİDE","HAMİYET","HANDAN","HANDE"
			,"HANIM","HANİFE","HARİKA","HASİBE","HASRET","HATIRA","HATİCE","HAVVA","HAYAL","HAYAT","HAYRİYE","HAYRÜNİSSA","HAZAL","HAZAN","HAZER","HEDİYE"
			,"HİCRAN","HİLAL","HURİ","HURİYE","HÜLYA","HÜMEYRA","HÜNER","HÜRMÜZ","HÜRREM","HÜRRİYET","HÜSNİYE","HABİB","HACI","HAFIZ","HAKAN","HAKKI"
			,"HALDUN","HALİL","HALİM","HALİS","HALİT","HALUK","HAMDİ","HAMDULLAH","HAMİ","HAMİT","HAMZA","HANEFİ","HARUN","HASAN","HASİP","HASBİ","HASRET"
			,"HAŞİM","HAŞMET","HATAY","HATEM","HATEMİ","HAYATİ","HAYDAR","HAYRETTİN","HAYRİ","HAYRULLAH","HAZAR","HAZIM","HEYBET","HIFZI","HINCAL","HIZIR"
			,"HİCRİ","HİDAYET","HİKMET","HİLMİ","HİMMET","HİRAM","HİŞAM","HULKİ","HULUSİ","HURŞİT","HÜDAVERDİ","HÜRAY","HÜRKAN","HÜSAM","HÜSAMETTİN"
			,"HÜSEYİN","HÜSMEN","HÜSNÜ","HÜSREV","ILGAZ","ILGIN","IRMAK","IŞIK","IŞIL","IŞILAY","IŞILTI","IŞIN","ITIR","İCLAL","İDİL","İFFET","İKBAL","İLAYDA"
			,"İLCAN","İLGİN","İLHAN","İLKAY","İLKBEN","İLKCAN","İLKE","İLKİN","İLKNUR","İLKSEN","İLKYAZ","İLSU","İLTER","İMGE","İMRAN","İMREN","İNCİ","İNCİNUR"
			,"İPEK","İREM","İSMİHAN","İYEM","İZEL","İZGİ","ILDIR","ILDIZ","ILGAR","ILGAZ","IŞIK","IŞIKHAN","IŞIN","IŞITAN","ITRİ","İBRAHİM","İDRİS","İHSAN"
			,"İLBEY","İLCAN","İLGİ","İLHAM","İLHAMİ","İLHAN","İLKAN","İLKAY","İLKCAN","İLKE","İLKER","İLKİN","İLTEKİN","İLTER","İLYAS","İMDAT","İNAL","İNAN"
			,"İNANÇ","İNAYET","İRFAN","İSA","İSHAK","İSKENDER","İSLAM","İSMAİL","İSMET","İSRAFİL","İSTEMİ","HAN","İŞCAN","İZZET","İZZETTİN","JALE","JALENUR"
			,"JÜLİDE","KADER","KADRİYE","KAMELYA","KAMİLE","KAMURAN","KARANFİL","KARDELEN","KARMEN","KAYRA","KERİMAN","KERİME","KEVSER","KEZBAN","KISMET"
			,"KIVILCIM","KIYMET","KİBARİYE","KİRAZ","KÖSEM","KUMRU","KÜBRA","KAAN","KAĞAN","KADEM","KADİR","KADRİ","KAHRAMAN","KAMBER","KAMER","KAMİL"
			,"KAMURAN","KANDEMİR","KANER","KAPLAN","KARABEY","KARACAN","KARAHAN","KARAKAN","KARAN","KARANALP","KARATAY","KARTAL","KARTAY","KASIM"
			,"KAYA","KAYAHAN","KAYHAN","KAZIM","KEMAL","KEMALETTİN","KENAN","KERAMETTİN","KEREM","KEREMŞAH","KERİM","KEYHAN","KILIÇ","KILIÇALP","KILIÇHAN"
			,"KIRCA","KIRDAR","KIRHAN","KIVANÇ","KIVILCIM","KOLÇAK","KONUR","KORAL","KORALP","KORAY","KORCAN","KORÇAK","KOREL","KORHAN","KORKMAZ","KORKUT"
			,"KORTAN","KÖKER","KÖKSAL","KUBAT","KUBİLAY","KUDRET","KUNTAY","KUNTER","KURT","KURTBEY","KURTULUŞ","KUTAN","KUTAY","KUTBAY","KUTER","KUTHAN"
			,"KUTLAY","KUTLU","KUTSAL","KUTSİ","KUZEY","KÜRŞAT","LALE","LALEHAN","LAMİA","LATİFE","LEMAN","LEMİDE","LERZAN","LETAFET","LEYLA","LÜTFİYE"
			,"LÜTUF","LAÇİN","LAMİ","LATİF","LEBİB","LEMA","LEMİ","LEVENT","LOKMAN","LÜTFİ","LÜTFULLAH","LÜTFÜ","MACİDE","MAHİNUR","MAHMURE","MAKBULE"
			,"MANOLYA","MARAL","MEDİHA","MEFHARET","MEFKURE","MEFTUN","MEHPARE","MEHTAP","MEHVEŞ","MELAHAT","MELDA","MELEK","MELİHA","MELİKE","MELİS"
			,"MELİSA","MELODİ","MELTEM","MENEKŞE","MENGÜ","MERİÇ","MERİH","MERVE","MERYEM","MESUDE","MISRA","MİHRİBAN","MİMOZA","MİNE","MİRAY","MUALLA","MUAZZEZ"
			,"MUHTEREM","MUKADDER","MUKADDES","MUNİSE","MUZAFFER","MÜBERRA","MÜCELLA","MÜESSER","MÜGE","MÜJDE","MÜJGAN","MÜKRİME","MÜNEVVER","MÜNİRE"
			,"MÜRÜVVET","MÜŞERREF","MÜYESSER","MÜZEYYEN","MACİT","MAHİR","MAHMUT","MAHSUN","MAHZUN","MAKBUL","MAKSUT","MALİK","MANÇO","MANSUR","MAZHAR"
			,"MAZLUM","MECİT","MECNUN","MEDENİ","MEDET","MEHMET","MELİH","MELİK","MEMDUH","MEMNUN","MENDERES","MENGÜ","MENGÜÇ","MENSUR","MERİÇ","MERİH"
			,"MERT","MESTAN","MESUT","METE","METİN","MEVLÜT","MİKAİL","MİRKELAM","MİRZ","MİTHAT","MUAMMER","MUCİP","MUHAMMED","MUHARREM","MUHİP","MUHİTTİN"
			,"MUHLİS","MUHSİN","MUHTAR","MUHTEŞEM","MUKBİL","MUNİS","MURAT","MURATHAN","MURTAZA","MUSA","MUSTAFA","MUTİ","MUTLU","MUTLUHAN","MUZAFFER"
			,"MÜCAHİT","MÜFİT","MÜJDAT","MÜKERREM","MÜKREMİN","MÜMİN","MÜMTAZ","MÜNİR","MÜREN","MÜRSEL","MÜRŞİT","MÜRŞİD","MÜSLÜM","MÜŞFİK","MÜŞTAK"
			,"NACİYE","NADİDE","NADİRE","NAFİA","NAGEHAN","NAĞME","NAHİDE","NAİLE","NALAN","NAME","NARİN","NAŞİDE","NAZ","NAZAN","NAZENDE","NAZİFE","NAZLI","NAZMİYE"
			,"NEBAHAT","NECLA","NECMİYE","NEDİME","NEDRET","NEFİSE","NEHİR","NERGİS","NERİMAN","NERMİN","NESLİHAN","NESLİŞAH","NESRİN","NEŞE","NEVAL","NEVCAN"
			,"NEVİN","NEVRA","NEZAHAT","NEZAKET","NEZİHE","NİDA","NİGAR","NİHAL","NİHAN","NİL","NİLAY","NİLGÜN","NİLSU","NİLÜFER","NİMET","NİSA","NİSAN"
			,"NUR","NURAL","NURAN","NURAY","NURCAN","NURÇİN","NURDAN","NURGÜL","NURHAN","NURİYE","NURPERİ","NURSEL","NURSELİ","NURSEN","NURŞEN"
			,"NÜKHET","NABİ","NACİ","NADİ","NADİR","NAFİ","NAFİZ","NAHİT","NAİL","NAİM","NAMIK","NAMİ","NASIR","NASRETTİN","NASUH","NASUHİ","NAŞİT","NAŞİD"
			,"NAZIM","NAZIR","NAZİF","NAZMİ","NEBİ","NECAT","NECATİ","NECDET","NECİP","NECMETTİN","NECMİ","NEDİM","NEDRET","NEHAR","NEJAT","NESİM","NEŞAT"
			,"NEŞET","NEVZAT","NEYZEN","NEZİH","NEZİHİ","NİHAT","NİYAZİ","NİZAM","NİZAMETTİN","NİZAMİ","NUH","NUMAN","NURETTİN","NURİ","NURKAN","NURŞAT"
			,"NURTAÇ","NUSRET","NUSRETTİN","NÜVİT","NÜZHET","OKŞAN","OLCA","OLCAY","ORKİDE","OYA","ÖĞÜN","ÖĞÜT","ÖMÜR","ÖNGÜL","ÖVGÜ","ÖVGÜL","ÖVÜN","ÖYKÜ"
			,"ÖZDEN","ÖZGE","ÖZGEN","ÖZGÜL","ÖZLEM","ÖZLEN","ÖZNUR","ÖZÜN","OFLAZ","OGÜN","OĞAN","OĞUL","OĞUR","OĞUZ","OĞUZHAN","OKAN","OKAY","OKCAN","OKER","OKTAR"
			,"OKTAY","OLCAY","OLCAYTO","OLGUN","OMAÇ","OMAY","ONAT","ONAY","ONGAR","ONGUN","ONUR","ONURAL","ONURALP","ONURHAN","ORBAY","ORÇUN","ORHAN"
			,"ORHUN","ORKUN","ORKUT","ORTAÇ","ORTUN","ORTUNÇ","ORUÇ","OSMAN","OYTUN","OZAN","ÖCAL","ÖDÜL","ÖGEDAY","ÖĞÜN","ÖĞÜNÇ","ÖĞÜT","ÖKER","ÖKKEŞ"
			,"ÖKMEN","ÖKTEM","ÖKTEN","ÖMER","ÖMÜR","ÖNAL","ÖNAY","ÖNDER","ÖNEL","ÖNER","ÖRSAN","ÖRSEL","ÖVÜL","ÖVÜNÇ","ÖYMEN","ÖZAL","ÖZALP","ÖZAY","ÖZBEK"
			,"ÖZCAN","ÖZDEMİR","ÖZDEN","ÖZEN","ÖZER","ÖZGÜN","ÖZGÜR","ÖZHAN","ÖZKAN","ÖZMEN","ÖZTÜRK","ÖZÜN","PAKİZE","PAPATYA","PARLA","PELİN","PELİNSU"
			,"PEMBE","PERÇEM","PEREN","PERİ","PERİHAN","PERRAN","PERVİN","PETEK","PINAR","PIRILTI","PİRAYE","PÜREN","PAKEL","PAKER","PAKSOY","PALA","PAMİR"
			,"PARS","PAŞA","PAYDAŞ","PAYİDAR","PEHLİVAN","PEKCAN","PEKER","PERKER","PERTEV","PEYAM","PEYAMİ","PEYKAN","PEYMAN","POLAT","POYRAZ","POZAN","RABİA"
			,"RAHİME","RAHŞAN","RANA","RAZİYE","REBİA","REFAH","REFİKA","REMZİYE","RENAN","RENGİN","REŞİDE","REVAN","REYHAN","REZZAN","RUHAN","RUHSAR"
			,"RUHŞEN","RÜYA","RÜYET","RACİ","RAFET","REFET","RAGIP","RAHİM","RAHMAN","RAHMİ","RAİF","RAKIM","RAMAZAN","RAMİ","RAMİZ","RASİM" ,"RASİN"
			,"RAŞİT","RAUF","RECAİ","RECEP","REFET","REFİĞ","REFİK","REHA","REMZİ","RENAN","RESUL","REŞAT","REŞİT","REŞİD","RIDVAN","RIFAT","RIFKI"
			,"RIZA","RUHİ","RUŞEN","RÜÇHAN","RÜKNETTİN","RÜSTEM","RÜŞTÜ","SAADET","SABAHAT","SABİHA","SABİTE","SABRİYE","SACİDE","SADRİYE","SAFİYE"
			,"SAHİBA","SAHURE","SAİME","SAKİNE","SALİHA","SALİME","SAMİME","SAMİYE","SANAY","SANEM","SANİA","SANİYE","SEBLA","SEÇİL","SEDA","SEDEF"
			,"SEDEN","SEHER","SELCAN","SELDA","SELEN","SELİN","SELMA","SELVİ","SEMA","SEMAHAT","SEMİHA","SEMİN","SEMİRAMİS","SEMRA","SENA","SENAY"
			,"SENEM","SERAP","SERAY","SEREN","SERİN","SERPİL","SERRA","SERTAP","SERVA","SEVAL","SEVCAN","SEVDA","SEVGİ","SEVİL","SEVİLAY","SEVİM"
			,"SEVİN","SEVİNÇ","SEVTAP","SEYHAN","SEYRAN","SEYYAL","SEZA","SEZAL","SEZEN","SEZER","SEZGİ","SEZGİN","SIDIKA","SILA","SIRMA","SİBEL"
			,"SİMA","SİMGE","SİNEM","SOLMAZ","SONAT","SONAY","SONGÜL","SONNUR","SU","SULTAN","SUNA","SUNAY","SUZAN","SÜHANDAN","SÜHEYLA","ŞADİYE","ŞAFAK"
			,"ŞAHİKA","ŞAZİMENT","ŞAZİYE","ŞEBNEM","ŞEFİKA","ŞEHNAZ","ŞEHRAZAT","ŞEHRİBAN","ŞELALE","ŞENAY","ŞENGÜL","ŞENİZ","ŞENNUR","ŞERİFE","ŞERMİN"
			,"ŞEVVAL","ŞEYDA","ŞEYMA","ŞİRİN","ŞULE","ŞÜKRAN","ŞÜKRİYE","SAADETTİN","SABAH","SABAHATTİN","SABİR","SABİT","SABRİ","SACİT","SACİD","SADETTİN"
			,"SADIK","SADRİ","SADULLAH","SADUN","SAFA","SAFFET","SAFİ","SAİM","SAİT","SAİD","SAKIP","SAKİN","SALİH","SALİM","SALTUK","SAMET","SAMED"
			,"SAMİ","SAMİH","SAMİM","SANBER","SANCAR","SANER","SANVER","SARGIN","SARP","SARPER","SARUHAN","SAVAŞ","SAYHAN","SAZAK","SEÇKİN"
			,"SEDAT","SEFA","SEFA","SAFA","SEFER","SEHA","SELAHATTİN","SELAMİ","SELCAN","SELÇUK","SELİM","SELMAN","SEMİH","SENİH","SERALP","SERBÜLENT"
			,"SERCAN","SERDAR","SERGEN","SERHAN","SERHAT","SERKAN","SERKUT","SERMET","SERTAÇ","SERTER","SERVER","SERVET","SEYFETTİN","SEYFİ","SEYHAN"
			,"SEYİT","SEZAİ","SEZER","SEZGİN","SIDDIK","SITKI","SİMAVİ","SİNA","SİNAN","SİPAHİ","SONER","SONGUR","SOYSAL","SÖKMEN","SÖNMEZ","SUAT","SUAVİ"
			,"SUAY","SUPHİ","SÜLEYMAN","SÜMER","SÜREYYA","SÜRURİ","ŞABAN","ŞADİ","ŞAFAK","ŞAHAP","ŞAHİN","ŞAHZAT","ŞAİR","ŞAKİR","ŞAMİL","ŞANSAL","ŞANVER"
			,"ŞARIK","ŞECAATTİN","ŞEFİK","ŞEHMUZ","ŞEHZADE","ŞEMSETTİN","ŞENEL","ŞENER","ŞENOL","ŞENSOY","ŞENTÜRK","ŞERAFETTİN","ŞEREF","ŞERİF","ŞEVKET","ŞEVKİ"
			,"ŞİNASİ","ŞÜKRÜ","TAÇNUR","TAHİRE","TAHSİNE","TALHA","TALİA","TAMAY","TANAY","TANSU","TANYEL","TARA","TAYYİBE","TEKGÜL","TENAY","TENDÜ","TENNUR","TEZER"
			,"TİJEN","TİLBE","TUBA","TUĞÇE","TURNA","TUTAM","TUTKU","TÜLAY","TÜLİN","TÜNAY","TUNAY","TÜRKAN","TÜRKÜ","TACETTİN","TACİ","TAÇKIN"
			,"TAHİR","TAHSİN","TAKİ","TALAT","TALAY","TALİP","TAMAY","TAMER","TAN","TANAY","TANBERK","TANER","TANJU","TANKUT","TANSEL","TANSU"
			,"TARHAN","TARIK","TARKAN","TAŞKIN","TAYFUN","TAYFUR","TAYGUN","TAYLAN","TAYYAR","TAYYİB","TEKCAN","TEKİN","TEMEL","TEOMAN","TERCAN","TEVFİK"
			,"TEZALP","TEZCAN","TEZKAN","TINAZ","TİMUÇİN","TİMUR","TOKCAN","TOKER","TOKTAMIŞ","TOLGA","TOLUNAY","TONGUÇ","TOPRAK","TOYGAR","TUFAN","TUGAY"};

	
	
	
	
	
	// *****
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

	public int[] counter = new int[stopWords.length];

	// **************Burası yeni eklenen yerler************

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

		//for (int i = 0; i < 100; i++) {
		//	System.out.println("PositiveWord - " + i + " :" + counter[i]);
		//}
	}


	public void titleWords(String[][] Sentence) {

		String[][] str = Sentence;
		String[] word;

		String[] title=str[0][0].split(", ,"); // İlk cümle başlık cümlesi olarak diziye atılıyor.
		String[] titleWords=title[0].split(" "); //başlık cümlesi kelimelere ayrılıyor.

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

		//for (int i = 0; i < 100; i++) {
		//	System.out.println("TitleWord - " + i + " :" + counter[i]);
		//}
	}


	// *****
	
	public void ProperNoun(String[][] Sentence) {
		String[][] str = Sentence;
		String[] word;
	
		try {
			
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");
				
				for (int j = 0; j < word.length; j++)
					for (int k = 0; k < KisiSozlugu.length; k++)
						if (word[j].equals(KisiSozlugu[k]) && !word[j].equals(null)) {
							counter[i] += 3;
						}
			}
			//denemek adına koydum 
			
			/*
				for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");
				for(int j=0;j<word.length;j++) {
					harf[i]=word[i].substring(0);
					for(int k=0;k<harf.length;k++) {
						for(int m=0;m<buyukHarf.length;m++) {
							if (harf[k].equals(buyukHarf[m]) && !harf[k].equals(null)) {
								counter[i] += 3;
						}
					}
				}
			}
			}
			*/
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		//for (int i = 0; i < 100; i++) {
		//	System.out.println("ProperNoun - " + i + " :" + counter[i]);
		//}

	}
	// ****************Burada son buluyor yaptıklarım****************

	// ****
	// Negatif kelime kontrolü yapılmaktadır.
	public void NegativeWord(String[][] Sentence) {
		String[][] str = Sentence;
		String[] word = new String[100];

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

		// for (int i = 0; i < 100; i++) {
		// System.out.println( i + "- NegativeWord - " + str[i][0] +" :" + counter[i]);
		// }

	}

	// ****
	// Eğer anahtar kelimler girilirse ona göre puan değerleri atanıyor.
	public void KeyWord(String[][] Sentence, String key) {
		// Zemberek zemberek = new Zemberek(new TurkiyeTurkcesi());

		ArrayList<String> list = new ArrayList<>();

		list.add(key);

		for (String lst : list) {
			key = lst.toLowerCase();
		}

		String[] KeyWord = key.split(",");
		String[][] str = Sentence;
		String[] word;

		/*
		 * String[] KeyWordRoot = new String[KeyWord.length];
		 * 
		 * for (int j = 0; j < KeyWord.length; j++) { if
		 * (zemberek.kelimeDenetle(KeyWord[j])) KeyWordRoot[j] =
		 * zemberek.kelimeCozumle(KeyWord[j])[0].kok().icerik(); else KeyWordRoot[j] =
		 * KeyWord[j]; }
		 */

		try {
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");
				/*
				 * String[] wordRoot = new String[word.length]; for (int j = 0; j < word.length;
				 * j++) { if (zemberek.kelimeDenetle(word[j])) wordRoot[j] =
				 * zemberek.kelimeCozumle(word[j])[0].kok().icerik(); else wordRoot[j] =
				 * KeyWord[j]; }
				 */

				for (int j = 0; j < word.length; j++) {
					for (int k = 0; k < KeyWord.length; k++)
						if (word[j].equals(KeyWord[k]) && !word[j].equals(null))
							counter[i] += 8;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		/*
		 * for (int i = 0; i < 100; i++) { System.out.println("KeyWord - " + i + " :" +
		 * counter[i]); }
		 */
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

		/*
		 * for (int i = 0; i < 100; i++) { System.out.println("SentencePosition - " + i
		 * + " :" + counter[i]); }
		 */

	}

	// YENİ EKLENEN YERLER //

	// burada noktalama işaretlerine göre puanlama yapılıyor(kelimenin sonunda soru
	// veya ünlem işareti varsa +2 puan veriliyor)
	public void PunctuationPointing(String[][] Sentence) {
		String[][] str = Sentence;
		String[] word;

		try {
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");

				for (int j = 0; j < word.length; j++)
					for (int k = 0; k < stopWords.length; k++)
						if (((word[j].substring(word[j].length() - 1) == "?")
								|| (word[j].substring(word[j].length() - 1) == "!")) && !word[j].equals(null))
							counter[i] += 2;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("SpecialPunctuation - " + i + " :" + counter[i]);
		}
	}

	// çift tırnağa göre puanlama(kelimenin sonunda veya başında tırnak işareti
	// varsa +1 puan veriliyor,
	// böylece algoritma 2 kelime bulduğunda toplamda +2 puan veriyor)
	public void QuotationPointing(String[][] Sentence) {
		String[][] str = Sentence;
		String[] word;

		try {
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");

				for (int j = 0; j < word.length; j++)
					for (int k = 0; k < stopWords.length; k++)
						if (((word[j].substring(word[j].length() - 1) == "\"") || (word[j].substring(0, 1) == "\""))
								&& !word[j].equals(null))
							counter[i] += 1;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("Quote - " + i + " :" + counter[i]);
		}
	}

	// tarih puanlandırması
	// kelimede 2 adet . veya / geçiyorsa tarih sayıyor
	// ay isimleri içinde bir değerlendirme yapılması gerekecek
	// DAHA TEST EDİLMEDİ
	public void DatePointing(String[][] Sentence) {
		String[][] str = Sentence;
		String[] word;

		try {
			for (int i = 0; i < str.length; i++) {
				word = str[i][0].split("( )|(\\.)|(\\,)|(\\?)|(\\[)|(\\])");

				for (int j = 0; j < word.length; j++)
					for (int k = 0; k < stopWords.length; k++)
						if (((word[j].length() - word[j].replace(".", "").length() == 2)
								|| (word[j].length() - word[j].replace("/", "").length() == 2))
								&& !word[j].equals(null))
							counter[i] += 1;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("Date - " + i + " :" + counter[i]);
		}
	}

	// *****
	// Burda her cümlenin puanına bakılarak özet e eklenecek olan cümle
	// belirlencektir.
	public String SortignSentence(String Sentence) {

		String[] paragraph = new String[100];
		String str = null;
		
		
		int temp=0;
		int c=0;
		
		for(int i=0; i < counter.length;i++){
			c++;
		}
		
		for(int i=0; i<counter.length;i++) {
			temp+=counter[i];
		}
		
		temp= temp / c;
		paragraph = Sentence.split("(\\.)");

		for (int i = 0; i < c; i++) {
			if (counter[i] > temp) {
				str += paragraph[i] + " .";
			}
		}

		return str;

	}

}
