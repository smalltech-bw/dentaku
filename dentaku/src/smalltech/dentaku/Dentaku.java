package smalltech.dentaku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Dentaku extends JFrame{
	
	final String ZERO_DIV_ERR_MESSAGE = "エラー：0による割り算\n演算子から入力し直して下さい";
	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result_text_field = new JTextField(""); 
	private String currentOp;
	private double stackedValue;
	private double currentTFValue;
	private boolean afterOpButton = false;
	
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
		keyPanel.add(new CalcButton("-"), 11);
		keyPanel.add(new NumButton(0), 12); 
		keyPanel.add(new JButton("."), 13);
		keyPanel.add(new CalcButton("+"), 14);
		keyPanel.add(new CalcButton("="), 15);
		
		contentPane.add(new JButton("C"), BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	public double readValueTF() {
		return Double.parseDouble(result_text_field.getText());
	}
	
	public void appendCharTF(String c) {
		result_text_field.setText(result_text_field.getText() + c);
	}
	
	public void resetTF() {
		result_text_field.setText("");
	}
	
	public void alert(String message) {
		java.awt.Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, message);
	}
	
	public class NumButton extends JButton implements ActionListener {
		private int buttonNum;
		
		public NumButton(int num) {
			super(Integer.toString(num));
			buttonNum = num;
			this.addActionListener(this); 
		}
		
		public void actionPerformed(ActionEvent e) {
			String keyNumber = Integer.toString(buttonNum);
			if(afterOpButton) {
				resetTF();
				afterOpButton = false;
			}
			appendCharTF(keyNumber);
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
			if(calcKind.equals("=")) {
				currentTFValue = readValueTF();
				if (currentOp.equals("+")) {
					stackedValue += currentTFValue;
				} else if (currentOp.equals("-")) {
					stackedValue -= currentTFValue;
				} else if (currentOp.equals("×")) {
					stackedValue *= currentTFValue;
				} else if (currentOp.equals("÷")) {
					if (currentTFValue == 0) {
						alert(ZERO_DIV_ERR_MESSAGE);//stackedValueの値を据え置く
					} else {
						stackedValue /= currentTFValue;
					}
				}
				resetTF();
				appendCharTF(Double.toString(stackedValue));
			} else {
				currentOp = calcKind;
				afterOpButton = true;
			}
			stackedValue = readValueTF();
		}
	}

}
