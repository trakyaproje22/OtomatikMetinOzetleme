package textsummarization;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private Summarizing SM = new Summarizing();

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
		KeyTextField.setBounds(959, 5, 288, 27);
		KeyTextField.setColumns(10);

		JLabel lblAnahtarKelimeler = new JLabel("Anahtar Kelimeler");
		lblAnahtarKelimeler.setBounds(800, 2, 147, 27);
		lblAnahtarKelimeler.setFont(new Font("Serif", Font.PLAIN, 20));

		JButton btnSummarization = new JButton("Özetle");
		btnSummarization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dictionary=("Sözlük konumu neresiyse orayı yazınız");
				if (!OrjinalTextArea.getText().equals("")) {
					SM.SentencePosition(OrjinalTextArea.getText().toString());// Cümlenin konumunu kontrol ediliyor.
					try {
						SM.NegativeWord(SI.Cumle(OrjinalTextArea.getText().toString()));// Negatif kelime kontrol
						SM.PositiveWord(SI.Cumle(OrjinalTextArea.getText().toString()));//Pozitif kelime kontrol
						SM.titleWords(SI.Cumle(OrjinalTextArea.getText().toString()));//Başlıkta geçen kelime kontrol
						SM.ProperNoun(SI.Cumle(OrjinalTextArea.getText().toString()),dictionary);//Ozel isim kontol
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (!KeyTextField.getText().equals("")) // Eğer anahtar kelime girilirse.
						try {
							SM.KeyWord(SI.Cumle(OrjinalTextArea.getText().toString()),
									KeyTextField.getText().toString());// Anahtar kelimeleri kontrol ediliyor.
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					// Özetleme işlemi gerçekleştirilecektir.
					SummarizationTextArea.setText("");
					// String str = SM.SortignSentence(OrjinalTextArea.getText().toString());

				}

			}
		});
		btnSummarization.setBounds(552, 227, 133, 58);
		btnSummarization.setFont(new Font("Serif", Font.PLAIN, 17));

		JButton btnFileRead = new JButton("Dosyadan Oku");
		btnFileRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					OrjinalTextArea.append(txt);

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
		btnClear.setBounds(1123, 622, 124, 50);
		btnClear.setFont(new Font("Serif", Font.PLAIN, 17));

		contentPane.setLayout(null);
		contentPane.add(lblAnahtarKelimeler);
		contentPane.add(KeyTextField);
		contentPane.add(btnSummarization);
		contentPane.add(btnFileRead);
		contentPane.add(btnClear);

		JLabel OrjinalTextLabel = new JLabel("Orjinal Metin");
		OrjinalTextLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		OrjinalTextLabel.setBounds(170, 42, 163, 27);
		contentPane.add(OrjinalTextLabel);

		JLabel TextSummarizationLabel = new JLabel("Özet Metin");
		TextSummarizationLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		TextSummarizationLabel.setBounds(975, 45, 163, 27);
		contentPane.add(TextSummarizationLabel);

		JScrollPane OrjinalTextScrollPane = new JScrollPane();
		OrjinalTextScrollPane.setBounds(22, 82, 455, 536);
		contentPane.add(OrjinalTextScrollPane);

		OrjinalTextArea = new JTextArea();
		OrjinalTextArea.setFont(new Font("Serif", Font.PLAIN, 17));
		OrjinalTextScrollPane.setViewportView(OrjinalTextArea);
		OrjinalTextArea.setLineWrap(true);
		OrjinalTextArea.setWrapStyleWord(true);

		JScrollPane SummarizationTextScrollPane = new JScrollPane();
		SummarizationTextScrollPane.setBounds(800, 82, 450, 536);
		contentPane.add(SummarizationTextScrollPane);

		SummarizationTextArea = new JTextArea();
		SummarizationTextArea.setFont(new Font("Serif", Font.PLAIN, 17));
		SummarizationTextScrollPane.setViewportView(SummarizationTextArea);
		SummarizationTextArea.setLineWrap(true);

	}
}
