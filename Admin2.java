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
import javax.swing.JTextField;

public class Admin2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	/**
	 * Launch the application.
	 */
	CardLayout card=new CardLayout(0, 0);
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
	static int medicineIndex=0;
	static int accountIndex=0;
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
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(28, 47, 281, 322);
		panel_1.add(textArea_1);
		textArea_1.setText(clientAdminThread.medicineInfo);
		
		
		JLabel lblMedicineInformation = new JLabel("medicine information");
		lblMedicineInformation.setBounds(18, 19, 152, 16);
		panel_1.add(lblMedicineInformation);
		
		JLabel lblNewLabel_3 = new JLabel("name");
		lblNewLabel_3.setBounds(367, 43, 61, 16);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblCount = new JLabel("count");
		lblCount.setBounds(367, 71, 61, 16);
		panel_1.add(lblCount);
		
		JLabel lblPrice = new JLabel("price");
		lblPrice.setBounds(367, 99, 61, 16);
		panel_1.add(lblPrice);
		
		textField_6 = new JTextField();
		textField_6.setBounds(454, 38, 130, 26);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setText(clientAdminThread.medicine1.get(medicineIndex).name);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(454, 66, 130, 26);
		panel_1.add(textField_7);
		textField_7.setText(clientAdminThread.medicine1.get(medicineIndex).count);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(454, 94, 130, 26);
		panel_1.add(textField_8);
		textField_8.setText(clientAdminThread.medicine1.get(medicineIndex).price);
		
		JButton btnEdit = new JButton("edit");         //medicine edit
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newName=textField_6.getText();
				String newCount=textField_7.getText();
				String newPrice=textField_8.getText();
				clientAdminThread.medicineDetail a=clientAdminThread.medicine1.get(medicineIndex);
				a.name=newName;
				a.count=newCount;
				a.price=newPrice;
				for(int i=0;i<clientAdminThread.medicine1.size();i++)
				{
					clientAdminThread.medicineInfo=clientAdminThread.medicineInfo+clientAdminThread.medicine1.get(i).name+"\t";
					clientAdminThread.medicineInfo=clientAdminThread.medicineInfo+clientAdminThread.medicine1.get(i).count+"\t";
					clientAdminThread.medicineInfo=clientAdminThread.medicineInfo+clientAdminThread.medicine1.get(i).price+"\n";
					
				}
				
				
				///send
				
				
				
			}
		});
		btnEdit.setBounds(413, 157, 130, 37);
		panel_1.add(btnEdit);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel");
			}
		});
		btnBack.setBounds(413, 257, 130, 37);
		panel_1.add(btnBack);
		
		JButton button = new JButton("next");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(medicineIndex==clientAdminThread.medicine1.size()-1)return;
				medicineIndex++;
				textField_6.setText(clientAdminThread.medicine1.get(medicineIndex).name);
				textField_7.setText(clientAdminThread.medicine1.get(medicineIndex).count);
				textField_8.setText(clientAdminThread.medicine1.get(medicineIndex).price);
			}
		});
		button.setBounds(488, 208, 130, 37);
		panel_1.add(button);
		
		JButton btnPre_1 = new JButton("pre");
		btnPre_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(medicineIndex==0)return;
				medicineIndex--;
				textField_6.setText(clientAdminThread.medicine1.get(medicineIndex).name);
				textField_7.setText(clientAdminThread.medicine1.get(medicineIndex).count);
				textField_8.setText(clientAdminThread.medicine1.get(medicineIndex).price);
			}
		});
		btnPre_1.setBounds(341, 208, 130, 37);
		panel_1.add(btnPre_1);
		
		Panel panel_2 = new Panel();
		contentPane.add(panel_2, "name_1482335001517998000");
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("doctor information");
		lblNewLabel_1.setBounds(21, 16, 250, 54);
		panel_2.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 64, 331, 331);
		panel_2.add(textArea);
		textArea.setText(clientAdminThread.accountInfo);
		
		JButton btnNewButton_6 = new JButton("edit");    		//account   edit
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newAccount=textField.getText();
				String newPassword=textField_1.getText();
				String newName=textField_2.getText();
				String newDepartment=textField_3.getText();
				String newAge=textField_4.getText();
				String newSex=textField_5.getText();
				clientAdminThread.accountDetail a=clientAdminThread.account1.get(accountIndex);
				a.name=newName;
				a.account=newAccount;
				a.password=newPassword;
				a.department=newDepartment;
				a.age=newAge;
				a.sex=newSex;
				for(int i=0;i<clientAdminThread.account1.size();i++)
				{
					clientAdminThread.accountInfo=clientAdminThread.accountInfo+clientAdminThread.account1.get(i).account+"\t";
					clientAdminThread.accountInfo=clientAdminThread.accountInfo+clientAdminThread.account1.get(i).password+"\t";
					clientAdminThread.accountInfo=clientAdminThread.accountInfo+clientAdminThread.account1.get(i).name+"\t";
					clientAdminThread.accountInfo=clientAdminThread.accountInfo+clientAdminThread.account1.get(i).department+"\t";
					clientAdminThread.accountInfo=clientAdminThread.accountInfo+clientAdminThread.account1.get(i).age+"\t";
					clientAdminThread.accountInfo=clientAdminThread.accountInfo+clientAdminThread.account1.get(i).sex+"\n";
					
				}
				
				
				///send
				
				
				
			}
		});
		btnNewButton_6.setBounds(433, 213, 130, 46);
		panel_2.add(btnNewButton_6);
		
		JLabel lblNewLabel_2 = new JLabel("name");
		lblNewLabel_2.setBounds(381, 92, 61, 16);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblAccount = new JLabel("account");
		lblAccount.setBounds(381, 35, 61, 16);
		panel_2.add(lblAccount);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(381, 64, 61, 16);
		panel_2.add(lblPassword);
		
		JLabel lblDepartment = new JLabel("department");
		lblDepartment.setBounds(381, 120, 79, 16);
		panel_2.add(lblDepartment);
		
		JLabel lblAge = new JLabel("age");
		lblAge.setBounds(381, 147, 61, 16);
		panel_2.add(lblAge);
		
		JLabel lblSex = new JLabel("sex");
		lblSex.setBounds(381, 175, 61, 16);
		panel_2.add(lblSex);
		
		textField = new JTextField();
		textField.setBounds(455, 30, 130, 26);
		panel_2.add(textField);
		textField.setColumns(10);
		textField.setText(clientAdminThread.account1.get(accountIndex).account);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(455, 59, 130, 26);
		panel_2.add(textField_1);
		textField_1.setText(clientAdminThread.account1.get(accountIndex).password);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(455, 87, 130, 26);
		panel_2.add(textField_2);
		textField_2.setText(clientAdminThread.account1.get(accountIndex).name);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(455, 115, 130, 26);
		panel_2.add(textField_3);
		textField_3.setText(clientAdminThread.account1.get(accountIndex).department);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(455, 143, 130, 26);
		panel_2.add(textField_4);
		textField_4.setText(clientAdminThread.account1.get(accountIndex).age);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(455, 170, 130, 26);
		panel_2.add(textField_5);
		textField_5.setText(clientAdminThread.account1.get(accountIndex).sex);
		
		JButton btnBack_1 = new JButton("back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel");
			}
		});
		btnBack_1.setBounds(433, 312, 130, 37);
		panel_2.add(btnBack_1);
		
		JButton btnNewButton_2 = new JButton("next");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accountIndex==clientAdminThread.account1.size()-1)return;
				accountIndex++;
				textField.setText(clientAdminThread.account1.get(accountIndex).account);
				textField_1.setText(clientAdminThread.account1.get(accountIndex).password);
				textField_2.setText(clientAdminThread.account1.get(accountIndex).name);
				textField_3.setText(clientAdminThread.account1.get(accountIndex).department);
				textField_4.setText(clientAdminThread.account1.get(accountIndex).age);
				textField_5.setText(clientAdminThread.account1.get(accountIndex).sex);
			}
		});
		btnNewButton_2.setBounds(505, 263, 130, 37);
		panel_2.add(btnNewButton_2);
		
		
		JButton btnPre = new JButton("pre");
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accountIndex==0)return;
				accountIndex--;
				textField.setText(clientAdminThread.account1.get(accountIndex).account);
				textField_1.setText(clientAdminThread.account1.get(accountIndex).password);
				textField_2.setText(clientAdminThread.account1.get(accountIndex).name);
				textField_3.setText(clientAdminThread.account1.get(accountIndex).department);
				textField_4.setText(clientAdminThread.account1.get(accountIndex).age);
				textField_5.setText(clientAdminThread.account1.get(accountIndex).sex);
			}
		});
		btnPre.setBounds(363, 263, 130, 37);
		panel_2.add(btnPre);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "name_11064744727640");
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("账户信息管理");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel_2");
			}
		});
		btnNewButton.setBounds(231, 97, 190, 72);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("药品管理");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel_1");
			}
		});
		btnNewButton_1.setBounds(231, 195, 190, 72);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("管理员操作界面");
		lblNewLabel.setBounds(280, 42, 100, 25);
		panel.add(lblNewLabel);
	}
}
