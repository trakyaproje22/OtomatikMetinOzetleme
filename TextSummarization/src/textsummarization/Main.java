package textsummarization;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField KeyTextField;
	public final JTextArea OrjinalTextArea;
	public final JTextArea SummarizationTextArea;
	JScrollPane scroll;

	private SentenceIdentification SI = new SentenceIdentification();
	private SummarizingFile SMF = new SummarizingFile();
	private SummarizingTxt SMT = new SummarizingTxt();

	private boolean FileReadClick = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		KeyTextField = new JTextField();
		KeyTextField.setFont(new Font("Serif", Font.PLAIN, 17));
		KeyTextField.setBounds(910, 5, 352, 27);
		KeyTextField.setColumns(10);

		JLabel lblAnahtarKelimeler = new JLabel("Anahtar Kelimeler");
		lblAnahtarKelimeler.setBounds(751, 4, 147, 27);
		lblAnahtarKelimeler.setFont(new Font("Serif", Font.PLAIN, 20));

		JButton btnSummarization = new JButton("Özetle");
		btnSummarization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!FileReadClick) {

					if (!OrjinalTextArea.getText().equals("")) {
						// Burada bazı sorunlar olabilir tekrar kontrol edilmesi gerekir.
						SMT.SentencePosition(OrjinalTextArea.getText().toString());

						try {

							// Başlık da geçen kelimelerin kontrolü yapılacak.
							SMT.titleWords(SI.Cumle(OrjinalTextArea.getText().toString()));

							// Pozitif kelime kontrolü yapılıyor.
							SMT.PositiveWord(SI.Cumle(OrjinalTextArea.getText().toString()));

							// Negatif kelime kontrolü yapılıyor.
							SMT.NegativeWord(SI.Cumle(OrjinalTextArea.getText().toString()));

							// Noktalamalara göre puanlandırma
							SMT.PunctuationPointing(SI.Cumle(OrjinalTextArea.getText().toString()));

							//
							SMT.QuotationPointing(SI.Cumle(OrjinalTextArea.getText().toString()));

							// Tarihe göre puanlandırma
							SMT.DatePointing(SI.Cumle(OrjinalTextArea.getText().toString()));

							// Özel İsim kelime kontrol
							// Burda bazı sıkıntılar olabilir tekrar kontrol edilmesi gerekir.
							 SMT.properNoun(SI.Cumle(OrjinalTextArea.getText().toString()));
							 SMT.ProperNoun(SI.Cumle(OrjinalTextArea.getText().toString()));
						
						} catch (Exception io) {
							// TODO: handle exception
							System.out.println(io.getMessage());
						}

						if (!KeyTextField.getText().equals("")) { // Eğer anahtar kelime girilirse.
							try { // Anahtar kelimeleri kontrol yapılıyor.
								SMT.KeyWord(SI.Cumle(OrjinalTextArea.getText().toString()),
										KeyTextField.getText().toString());
							}

							catch (IOException e1) { // TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						// Özetleme işlemi gerçekleştirilecektir.
						SummarizationTextArea.setText("");
						String[] str = SMT.SortignSentence(OrjinalTextArea.getText().toString()).split("(, ,)|(null)");
						for (int i = 0; i < str.length; i++) {
							SummarizationTextArea.append(str[i]);
							SummarizationTextArea.setCaretPosition(SummarizationTextArea.getDocument().getLength());
							
						}

					}
				} else {
					FileReadClick = false;
					if (!OrjinalTextArea.getText().equals("")) {
						SMF.SentencePosition(OrjinalTextArea.getText().toString());// Cümlenin konumunu kontrol
																					// ediliyor.
						try {

							SMF.NegativeWord(SI.Cumle(OrjinalTextArea.getText().toString()));// Negatif kelime kontrol
																								// ediliyor.

							SMF.titleWords(SI.Cumle(OrjinalTextArea.getText().toString()));// Baslik kontrol
							SMF.PositiveWord(SI.Cumle(OrjinalTextArea.getText().toString()));// Pozitif kelime kontrol
							SMF.ProperNoun(SI.Cumle(OrjinalTextArea.getText().toString()));// Özel İsim kelime kontrol

							SMF.PunctuationPointing(SI.Cumle(OrjinalTextArea.getText().toString()));
							SMF.QuotationPointing(SI.Cumle(OrjinalTextArea.getText().toString()));
							SMF.DatePointing(SI.Cumle(OrjinalTextArea.getText().toString()));

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (!KeyTextField.getText().equals("")) // Eğer anahtar kelime girilirse.
							try {
								SMF.KeyWord(SI.Cumle(OrjinalTextArea.getText().toString()),
										KeyTextField.getText().toString());// Anahtar kelimeleri kontrol ediliyor.
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						// Özetleme işlemi gerçekleştirilecektir.
						SummarizationTextArea.setText("");
						String[] str = SMF.SortignSentence(OrjinalTextArea.getText().toString()).split("(, ,)|(null)");

						for (int i = 0; i < str.length; i++) {
							SummarizationTextArea.append(str[i]);
						}
							
						String fixedResult= SummarizationTextArea.getText().replaceAll(", , ","\r\n\r\n");
						SummarizationTextArea.setText(fixedResult);
					}
				}
			}
		});
		btnSummarization.setBounds(545, 273, 194, 58);
		btnSummarization.setFont(new Font("Serif", Font.PLAIN, 17));

		JButton btnFileRead = new JButton("Dosyadan Oku");
		btnFileRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileReadClick = true;

				OrjinalTextArea.setText("");
				FileRW temp = new FileRW();
				File f = null;
				String path = null;

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(fileChooser);
				f = fileChooser.getSelectedFile();
				path = f.getAbsolutePath();

				try {
					String txt = temp.FileRead(path);
					String txt2=txt.replaceAll(", , ","\r\n\r\n");
					OrjinalTextArea.append(txt2);

				} catch (IOException e1) { 
					e1.printStackTrace();
				}

			}
		});
		btnFileRead.setBounds(22, 622, 163, 50);
		btnFileRead.setFont(new Font("Serif", Font.PLAIN, 17));

		JButton btnClear = new JButton("Temizle");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrjinalTextArea.setText("");
				SummarizationTextArea.setText("");
			}
		});
		btnClear.setBounds(1138, 622, 124, 50);
		btnClear.setFont(new Font("Serif", Font.PLAIN, 17));

		contentPane.setLayout(null);
		contentPane.add(lblAnahtarKelimeler);
		contentPane.add(KeyTextField);
		contentPane.add(btnSummarization);
		contentPane.add(btnFileRead);
		contentPane.add(btnClear);

		JLabel OrjinalTextLabel = new JLabel("Orjinal Metin");
		OrjinalTextLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		OrjinalTextLabel.setBounds(201, 45, 163, 27);
		contentPane.add(OrjinalTextLabel);

		JLabel TextSummarizationLabel = new JLabel("Özet Metin");
		TextSummarizationLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		TextSummarizationLabel.setBounds(959, 45, 163, 27);
		contentPane.add(TextSummarizationLabel);

		JScrollPane OrjinalTextScrollPane = new JScrollPane();
		OrjinalTextScrollPane.setBounds(22, 85, 511, 536);
		contentPane.add(OrjinalTextScrollPane);

		OrjinalTextArea = new JTextArea();
		OrjinalTextArea.setFont(new Font("Serif", Font.PLAIN, 17));
		OrjinalTextScrollPane.setViewportView(OrjinalTextArea);
		OrjinalTextArea.setLineWrap(true);
		OrjinalTextArea.setWrapStyleWord(true);

		JScrollPane SummarizationTextScrollPane = new JScrollPane();
		SummarizationTextScrollPane.setBounds(751, 85, 511, 536);
		contentPane.add(SummarizationTextScrollPane);

		SummarizationTextArea = new JTextArea();
		SummarizationTextScrollPane.setViewportView(SummarizationTextArea);
		SummarizationTextArea.setFont(new Font("Serif", Font.PLAIN, 17));
		SummarizationTextArea.setLineWrap(true);

	}
}

