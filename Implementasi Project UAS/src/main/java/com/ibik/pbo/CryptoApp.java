package com.ibik.pbo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CryptoApp {
	
	private String data;
	private JTextField textData;
	private JComboBox AlgorithmList;
	private JFrame cryptoFrame;
	
	CryptoApp(){
		cryptoFrame = new JFrame();
		cryptoFrame.setVisible(true);
		cryptoFrame.setTitle("Cryptograhy");
		cryptoFrame.pack();
		cryptoFrame.setResizable(true);
		cryptoFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		cryptoFrame.setSize(350,300);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		JLabel lblData = new JLabel("Data : ");
		lblData.setBounds(30,50,50,30);
		mainPanel.add(lblData);
		
		textData = new JTextField();
		textData.setBounds(100, 50, 150, 30);
		mainPanel.add(textData);
		
		String List[] = {"AES", "RSA", "Blowfish"};
		AlgorithmList = new JComboBox(List);
		AlgorithmList.setBounds(75, 100, 150, 30);
		mainPanel.add(AlgorithmList);
		
		JButton btn = new JButton("Encrypt");
		btn.setBounds(100,150,80,30);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedList = (String) AlgorithmList.getSelectedItem();
				if (textData.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,  "Masukkan Data!");
				} else {
					setData(textData.getText());
					switch(selectedList) {
					case "AES":
						try {
							AES aes = new AES();
							aes.Algorithm(getData());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
						
					case "RSA":
						try {
							RSA rsa = new RSA();
							rsa.Algorithm(getData());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
						
					case "Blowfish":
						Blowfish blowfish = new Blowfish();
						try {
							blowfish.Algorithm(getData());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
		
					default:
						JOptionPane.showMessageDialog(null,  "Error");
					}
				}
			}
		});
		mainPanel.add(btn);
		cryptoFrame.add(mainPanel);
	}
	
	public static void main(String args[]) {
		new CryptoApp();
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
}