package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import client.ClientAdminThread.accountDetail;
import client.ClientAdminThread.medicineDetail;

import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;

public class Admin extends JFrame {

	 JPanel contentPane;
	static JTextField textField;
	static JTextField textField_1;
	 static JTextField textField_2;
	 static JTextField textField_3;
	 static JTextField textField_4;
	 static JTextField textField_5;
	
	 static JTextField textField_6;
	 static JTextField textField_7;
	static JTextField textField_8;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static int medicineIndex=0;
	static int accountIndex=0;
	public static JTextArea textArea;
	public static JTextArea textArea_1;
//	
	
	
	/**
	 * Create the frame.
	 */
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout card=new CardLayout(0, 0);
		contentPane.setLayout(card);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "panel");
		panel.setLayout(null);
		//card.show(contentPane, "panel");
		
		JButton btnNewButton = new JButton("账户信息管理");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel_2");
			}
		});
		btnNewButton.setBounds(198, 79, 240, 104);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("药品管理");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel_1");
			}
		});
		btnNewButton_1.setBounds(198, 199, 240, 104);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("管理员操作界面");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(247, 28, 133, 39);
		panel.add(lblNewLabel);
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, "panel_1");
		panel_1.setLayout(null);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(28, 47, 287, 352);
		panel_1.add(textArea_1);
		
		
		
		JLabel lblMedicineInformation = new JLabel("medicine information");
		lblMedicineInformation.setBounds(18, 19, 152, 16);
		panel_1.add(lblMedicineInformation);
		
		JLabel lblNewLabel_3 = new JLabel("name");
		lblNewLabel_3.setBounds(367, 59, 61, 16);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblCount = new JLabel("count");
		lblCount.setBounds(367, 87, 61, 16);
		panel_1.add(lblCount);
		
		JLabel lblPrice = new JLabel("price");
		lblPrice.setBounds(367, 115, 61, 16);
		panel_1.add(lblPrice);
		
		textField_6 = new JTextField();
		textField_6.setBounds(454, 54, 130, 26);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(454, 82, 130, 26);
		panel_1.add(textField_7);
		
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(454, 110, 130, 26);
		panel_1.add(textField_8);
		
		
		JButton btnEdit = new JButton("edit");         //medicine edit
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newName=textField_6.getText();
				String newCount=textField_7.getText();
				String newPrice=textField_8.getText();
				ClientAdminThread.medicineDetail a=ClientAdminThread.medicine1.get(medicineIndex);
				String oldName=a.name;
				a.name=newName;
				a.count=newCount;
				a.price=newPrice;
				ClientAdminThread.medicineInfo="";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"name"+"\t";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"count"+"\t";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"price"+"\n";
				for(int i=0;i<ClientAdminThread.medicine1.size();i++)
				{
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).name+"\t";
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).count+"\t";
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).price+"\n";
				}
				textArea_1.setText(ClientAdminThread.medicineInfo);
				
				
				///send
				String sendMsg="changeMedicineInfo,"+oldName+","+a.name+","+a.count+","+a.price;
				ClientAdminThread.sendInfo(sendMsg);
				
			}
		});
		btnEdit.setBounds(341, 159, 130, 37);
		panel_1.add(btnEdit);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel");
			}
		});
		btnBack.setBounds(488, 257, 130, 37);
		panel_1.add(btnBack);
		
		JButton button = new JButton("next");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(medicineIndex==ClientAdminThread.medicine1.size()-1)return;
				medicineIndex++;
				textField_6.setText(ClientAdminThread.medicine1.get(medicineIndex).name);
				textField_7.setText(ClientAdminThread.medicine1.get(medicineIndex).count);
				textField_8.setText(ClientAdminThread.medicine1.get(medicineIndex).price);
				textArea_1.setText(ClientAdminThread.medicineInfo);
			}
		});
		button.setBounds(488, 208, 130, 37);
		panel_1.add(button);
		
		JButton btnPre_1 = new JButton("pre");
		btnPre_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(medicineIndex==0)return;
				medicineIndex--;
				textField_6.setText(ClientAdminThread.medicine1.get(medicineIndex).name);
				textField_7.setText(ClientAdminThread.medicine1.get(medicineIndex).count);
				textField_8.setText(ClientAdminThread.medicine1.get(medicineIndex).price);
			}
		});
		btnPre_1.setBounds(488, 159, 130, 37);
		panel_1.add(btnPre_1);
		
		JButton btnDelete_1 = new JButton("delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deleteName=ClientAdminThread.medicine1.get(medicineIndex).name;
				ClientAdminThread.medicine1.remove(medicineIndex);
				ClientAdminThread.medicineInfo="";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"name"+"\t";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"count"+"\t";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"price"+"\n";
		
				for(int i=0;i<ClientAdminThread.medicine1.size();i++)
				{
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).name+"\t";
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).count+"\t";
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).price+"\n";
					
				}
				textField_6.setText(ClientAdminThread.medicine1.get(medicineIndex).name);
				textField_7.setText(ClientAdminThread.medicine1.get(medicineIndex).count);
				textField_8.setText(ClientAdminThread.medicine1.get(medicineIndex).price);
				String sendMsg="deleteMedicineInfo,"+deleteName;
				ClientAdminThread.sendInfo(sendMsg);
			}
		});
		btnDelete_1.setBounds(341, 208, 130, 37);
		panel_1.add(btnDelete_1);
		
		JButton btnAdd_1 = new JButton("add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicineDetail a=new medicineDetail();
				a.name=textField_6.getText();
				for(int i=0;i<ClientAdminThread.medicine1.size();i++)
				{
					if(ClientAdminThread.medicine1.get(i).name==a.name)
						return;
				}
				a.count=textField_7.getText();
				a.price=textField_8.getText();
				ClientAdminThread.medicine1.add(a);
				ClientAdminThread.medicineInfo="";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"name"+"\t";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"count"+"\t";
				ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+"price"+"\n";
		
				for(int i=0;i<ClientAdminThread.medicine1.size();i++)
				{
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).name+"\t";
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).count+"\t";
					ClientAdminThread.medicineInfo=ClientAdminThread.medicineInfo+ClientAdminThread.medicine1.get(i).price+"\n";
					
				}
				medicineIndex=0;
				textField_6.setText(ClientAdminThread.medicine1.get(medicineIndex).name);
				textField_7.setText(ClientAdminThread.medicine1.get(medicineIndex).count);
				textField_8.setText(ClientAdminThread.medicine1.get(medicineIndex).price);
				String sendMsg="addMedicineInfo,"+a.name+","+a.count+","+a.price;
				ClientAdminThread.sendInfo(sendMsg);
				
				
				
			}
		});
		btnAdd_1.setBounds(341, 257, 130, 37);
		panel_1.add(btnAdd_1);
		
		Panel panel_2 = new Panel();
		contentPane.add(panel_2, "panel_2");
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("doctor information");
		lblNewLabel_1.setBounds(21, 16, 250, 54);
		panel_2.add(lblNewLabel_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 64,331,331);
		panel_2.add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		//设置水平滚动条自动出现 
		scrollPane.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		
		
		
		JButton btnNewButton_6 = new JButton("edit");    		//account   edit
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newAccount=textField.getText();
				String newPassword=textField_1.getText();
				String newName=textField_2.getText();
				String newDepartment=textField_3.getText();
				String newAge=textField_4.getText();
				String newSex=textField_5.getText();
				ClientAdminThread.accountDetail a=ClientAdminThread.account1.get(accountIndex);
				a.name=newName;
				a.account=newAccount;
				a.password=newPassword;
				a.department=newDepartment;
				a.age=newAge;
				a.sex=newSex;
				ClientAdminThread.accountInfo="";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"account"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"password"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"name"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"department"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"age"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"sex"+"\n";
				for(int i=0;i<ClientAdminThread.account1.size();i++)
				{
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).account+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).password+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).name+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).department+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).age+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).sex+"\n";
					
				}
				textArea.setText(ClientAdminThread.accountInfo);
				
				///send
				String sendMsg="changeDoctorInfo,"+a.account+","+a.password+","+a.name+","+a.age+","+a.sex+","+a.department;
				ClientAdminThread.sendInfo(sendMsg);
				
				
			}
		});
		btnNewButton_6.setBounds(374, 230, 120, 37);
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
		
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(455, 59, 130, 26);
		panel_2.add(textField_1);
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(455, 87, 130, 26);
		panel_2.add(textField_2);
		
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(455, 115, 130, 26);
		panel_2.add(textField_3);
		
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(455, 143, 130, 26);
		panel_2.add(textField_4);
		
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(455, 170, 130, 26);
		panel_2.add(textField_5);
		
		
		JButton btnBack_1 = new JButton("back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel");
			}
		});
		btnBack_1.setBounds(495, 328, 130, 37);
		panel_2.add(btnBack_1);
		
		JButton btnNewButton_2 = new JButton("next");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accountIndex==ClientAdminThread.account1.size()-1)return;
				accountIndex++;
				textField.setText(ClientAdminThread.account1.get(accountIndex).account);
				textField_1.setText(ClientAdminThread.account1.get(accountIndex).password);
				textField_2.setText(ClientAdminThread.account1.get(accountIndex).name);
				textField_3.setText(ClientAdminThread.account1.get(accountIndex).department);
				textField_4.setText(ClientAdminThread.account1.get(accountIndex).age);
				textField_5.setText(ClientAdminThread.account1.get(accountIndex).sex);
				textArea.setText(ClientAdminThread.accountInfo);
			}
		});
		btnNewButton_2.setBounds(495, 279, 130, 37);
		panel_2.add(btnNewButton_2);
		
		
		JButton btnPre = new JButton("pre");
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accountIndex==0)return;
				accountIndex--;
				textField.setText(ClientAdminThread.account1.get(accountIndex).account);
				textField_1.setText(ClientAdminThread.account1.get(accountIndex).password);
				textField_2.setText(ClientAdminThread.account1.get(accountIndex).name);
				textField_3.setText(ClientAdminThread.account1.get(accountIndex).department);
				textField_4.setText(ClientAdminThread.account1.get(accountIndex).age);
				textField_5.setText(ClientAdminThread.account1.get(accountIndex).sex);
				textArea.setText(ClientAdminThread.accountInfo);
			}
		});
		btnPre.setBounds(495, 230, 130, 37);
		panel_2.add(btnPre);
		
		JButton btnDelete = new JButton("delete");           //delete account
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deleteAccount=ClientAdminThread.account1.get(accountIndex).account;
				ClientAdminThread.account1.remove(accountIndex);
				ClientAdminThread.accountInfo="";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"account"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"password"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"name"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"department"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"age"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"sex"+"\n";
				for(int i=0;i<ClientAdminThread.account1.size();i++)
				{
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).account+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).password+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).name+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).department+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).age+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).sex+"\n";
					
				}
				textArea.setText(ClientAdminThread.accountInfo);
				textField.setText(ClientAdminThread.account1.get(accountIndex).account);
				textField_1.setText(ClientAdminThread.account1.get(accountIndex).password);
				textField_2.setText(ClientAdminThread.account1.get(accountIndex).name);
				textField_3.setText(ClientAdminThread.account1.get(accountIndex).department);
				textField_4.setText(ClientAdminThread.account1.get(accountIndex).age);
				textField_5.setText(ClientAdminThread.account1.get(accountIndex).sex);
				String sendMsg="deleteDoctorInfo,"+deleteAccount;
				ClientAdminThread.sendInfo(sendMsg);
			}
		});
		btnDelete.setBounds(374, 279, 120, 37);
		panel_2.add(btnDelete);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountDetail a=new accountDetail();
				a.account=textField.getText();
				for(int i=0;i<ClientAdminThread.account1.size();i++)
				{
					if(ClientAdminThread.account1.get(i).account==a.account)
						return;
				}
				a.password=textField_1.getText();
				a.name=textField_2.getText();
				a.department=textField_3.getText();
				a.age=textField_4.getText();
				a.sex=textField_5.getText();
				ClientAdminThread.account1.add(a);
				ClientAdminThread.accountInfo="";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"account"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"password"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"name"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"department"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"age"+"\t";
				ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+"sex"+"\n";
		
				for(int i=0;i<ClientAdminThread.account1.size();i++)
				{
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).account+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).password+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).name+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).department+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).age+"\t";
					ClientAdminThread.accountInfo=ClientAdminThread.accountInfo+ClientAdminThread.account1.get(i).sex+"\n";
					
				}
				medicineIndex=0;
				textField.setText(ClientAdminThread.account1.get(accountIndex).account);
				textField_1.setText(ClientAdminThread.account1.get(accountIndex).password);
				textField_2.setText(ClientAdminThread.account1.get(accountIndex).name);
				textField_3.setText(ClientAdminThread.account1.get(accountIndex).department);
				textField_4.setText(ClientAdminThread.account1.get(accountIndex).age);
				textField_5.setText(ClientAdminThread.account1.get(accountIndex).sex);
				String sendMsg="addDoctorInfo,"+a.account+","+a.password+","+a.name+","+a.age+","+a.sex+","+a.department;
				ClientAdminThread.sendInfo(sendMsg);
				
				
			}
		});
		btnAdd.setBounds(374, 328, 120, 37);
		panel_2.add(btnAdd);
	}
}
