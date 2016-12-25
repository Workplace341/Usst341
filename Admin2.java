import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JTextArea;

public class Admin2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin2 frame = new Admin2();
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
	public Admin2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, "name_1482331022094156000");
		panel_1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("doctor");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(64, 51, 159, 80);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("病人");
		btnNewButton_3.setBounds(344, 51, 130, 80);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(88, 245, 117, 29);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(344, 245, 117, 29);
		panel_1.add(btnNewButton_5);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "name_1482331153113465000");
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("账户信息管理");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(231, 97, 190, 72);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("药品管理");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(231, 195, 190, 72);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("管理员操作界面");
		lblNewLabel.setBounds(280, 42, 100, 25);
		panel.add(lblNewLabel);
		
		Panel panel_2 = new Panel();
		contentPane.add(panel_2, "name_1482335001517998000");
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("doctor information");
		lblNewLabel_1.setBounds(21, 16, 250, 54);
		panel_2.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 64, 293, 331);
		panel_2.add(textArea);
		
		JButton btnNewButton_6 = new JButton("修改");
		btnNewButton_6.setBounds(434, 90, 130, 46);
		panel_2.add(btnNewButton_6);
	}
}
