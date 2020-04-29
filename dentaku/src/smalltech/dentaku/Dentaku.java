package smalltech.dentaku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Dentaku extends JFrame{
	
	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result_text_field = new JTextField(""); 
	private String dentakuState;
	private double firstNum;
	
	public Dentaku() {
		//ウィンドウについての設定
		this.setTitle("dentaku");
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(borderLayout1);
		this.setContentPane(contentPane);
		
		//出力用テキストフィールド
		contentPane.add(result_text_field, BorderLayout.NORTH); 
		
		//ボタン部分
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(4, 4)); 
		contentPane.add(keyPanel, BorderLayout.CENTER);
		
		keyPanel.add(new NumButton(7), 0); 
		keyPanel.add(new NumButton(8), 1); 
		keyPanel.add(new NumButton(9), 2); 
		keyPanel.add(new CalcButton("÷"), 3);
		keyPanel.add(new NumButton(4), 4);
		keyPanel.add(new NumButton(5), 5);
		keyPanel.add(new NumButton(6), 6); 
		keyPanel.add(new CalcButton("×"), 7);
		keyPanel.add(new NumButton(1), 8);
		keyPanel.add(new NumButton(2), 9);
		keyPanel.add(new NumButton(3), 10);
		keyPanel.add(new CalcButton("－"), 11);
		keyPanel.add(new NumButton(0), 12); 
		keyPanel.add(new JButton("."), 13);
		keyPanel.add(new CalcButton("＋"), 14);
		keyPanel.add(new JButton("＝"), 15);
		
		contentPane.add(new JButton("C"), BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	public void appendResult(String c) {
		result_text_field.setText(result_text_field.getText() + c);
	}
	
	public class NumButton extends JButton implements ActionListener {
		private int button_num;
		
		public NumButton(int num) {
			super(Integer.toString(num));
			button_num = num;
			this.addActionListener(this); 
		}
		
		public void actionPerformed(ActionEvent e) {
			String keyNumber = Integer.toString(button_num);
			appendResult(keyNumber);
		}
	}
	
	public class CalcButton extends JButton implements ActionListener {
		private String calcKind;
		public CalcButton(String kind) {
			super(kind);		
			calcKind = kind;
			this.addActionListener(this); 
		}
		
		public void actionPerformed(ActionEvent e) {
			dentakuState = calcKind;
			firstNum = Double.parseDouble(result_text_field.getText());
			java.awt.Toolkit.getDefaultToolkit().beep();//ビープ音お試し
			JOptionPane.showMessageDialog(Dentaku.this, "calc button pushed");//警告表示お試し
			System.out.println("calc button pushed");
		}
	}

}
