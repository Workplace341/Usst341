package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Button;
import javax.swing.JButton;

public class President extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					President frame = new President();
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
	public President() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Panel panel = new Panel();
		contentPane.add(panel, "name_39842166200974");
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u9662\u957Fpresident");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel.setBounds(382, 6, 122, 51);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5185\u79D1");
		lblNewLabel_1.setBounds(25, 69, 54, 33);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u5916\u79D1");
		label.setBounds(25, 114, 54, 33);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u513F\u79D1");
		label_1.setBounds(25, 159, 54, 33);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(72, 69, 269, 33);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(72, 114, 269, 33);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(72, 159, 269, 33);
		panel.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("\u5F53\u524D\u6302\u53F7\u6570");
		lblNewLabel_2.setBounds(72, 41, 65, 16);
		panel.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("\u603B\u6302\u53F7\u6570");
		label_2.setBounds(154, 41, 65, 16);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u603B\u91D1\u989D");
		label_3.setBounds(231, 41, 65, 16);
		panel.add(label_3);
		
		JButton btnNewButton = new JButton("refresh");
		btnNewButton.setBounds(376, 69, 72, 33);
		panel.add(btnNewButton);
		
		JButton btnMedicine = new JButton("Medicine");
		btnMedicine.setBounds(72, 214, 128, 45);
		panel.add(btnMedicine);
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.setBounds(231, 214, 128, 45);
		panel.add(btnDoctor);
	}
}
