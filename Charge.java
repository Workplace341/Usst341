package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class Charge extends JFrame {

	private JPanel contentPane;
	private JTextField age;
	private JTextField sex;
	private JTextField name;
	private ButtonGroup bg=new ButtonGroup();
	static public JLabel notice;
	static public JTextField cage;
	static public JTextField csex;
	static public JTextField cname;
	static public JTextArea wait;
	static public JLabel pay;
	static public JTextField ID;
	static public JTextField pname;
	static public JTextField psex;
	static public JTextField page;
	static public JTextField pdepart;
	static public JTextArea pwait;
	static public JLabel pnotice;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Charge frame = new Charge();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Charge(Point p) {
		this.setLocation(p);
		this.setTitle("ÊÕ·Ñ¿Í»§¶Ë");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 650, 450);
		this.setSize(650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout card=new CardLayout(0, 0);
		contentPane.setLayout(card);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "main");
		panel.setLayout(null);
		
		
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(289, 225, 106, 21);
		panel.add(age);
		
		sex = new JTextField();
		sex.setColumns(10);
		sex.setBounds(289, 181, 106, 21);
		panel.add(sex);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(289, 141, 106, 21);
		panel.add(name);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label.setBounds(176, 142, 54, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_1.setBounds(176, 186, 54, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u5E74\u9F84\uFF1A");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_2.setBounds(176, 231, 54, 15);
		panel.add(label_2);
		
		JButton button = new JButton("\u67E5\u770B\u9884\u7EA6\u75C5\u4EBA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "predict");
				Charge.pnotice.setText("¹ÒºÅÍ¨Öª");
			}
		});
		button.setBounds(85, 307, 145, 33);
		panel.add(button);
		
		notice = new JLabel("\u6302\u53F7\u6D88\u606F");
		notice.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		notice.setBounds(85, 40, 428, 57);
		panel.add(notice);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "charge");
		panel_2.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("\u4ED8\u6B3E");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientChargeThread.sendPayInfo();
				ClientChargeThread.myChargePatientInfo.remove(0);
				if(ClientChargeThread.myChargePatientInfo.size()!=0){
					ClientChargeThread.ChargePatientInfo cp=ClientChargeThread.myChargePatientInfo.get(0);
					Charge.cname.setText(cp.name);
					Charge.csex.setText(cp.sex);
					Charge.cage.setText(cp.age);
					Charge.ID.setText(cp.id);
					Charge.pay.setText("ÐèÒªÖ§¸¶"+cp.price+"Ôª");
				}
				else{
					Charge.cname.setText("");
					Charge.csex.setText("");
					Charge.cage.setText("");
					Charge.ID.setText("");
					Charge.pay.setText("");
				}
				
				ClientChargeThread.updatePaitenInfo();
				
			}
		});
		btnNewButton_3.setBounds(109, 333, 106, 35);
		panel_2.add(btnNewButton_3);
		
		JButton button_3 = new JButton("\u8FD4\u56DE");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "main");
			}
		});
		button_3.setBounds(400, 333, 106, 35);
		panel_2.add(button_3);
		
		cage = new JTextField();
		cage.setEditable(false);
		cage.setColumns(10);
		cage.setBounds(123, 123, 106, 21);
		panel_2.add(cage);
		
		wait = new JTextArea();
		wait.setEditable(false);
		wait.setBounds(265, 51, 343, 201);
		panel_2.add(wait);
		
		csex = new JTextField();
		csex.setEditable(false);
		csex.setColumns(10);
		csex.setBounds(123, 92, 106, 21);
		panel_2.add(csex);
		
		cname = new JTextField();
		cname.setEditable(false);
		cname.setColumns(10);
		cname.setBounds(123, 61, 106, 21);
		panel_2.add(cname);
		
		JLabel label_5 = new JLabel("\u5F53\u524D\u4ED8\u6B3E\u7684\u4EBA");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		label_5.setBounds(24, 10, 179, 30);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("\u59D3\u540D\uFF1A");
		label_6.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_6.setBounds(10, 62, 54, 15);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("\u6027\u522B\uFF1A");
		label_7.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_7.setBounds(10, 92, 54, 16);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("\u5E74\u9F84\uFF1A");
		label_8.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_8.setBounds(10, 124, 54, 15);
		panel_2.add(label_8);
		
		JButton button_4 = new JButton("\u4E0B\u4E00\u4E2A");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 ClientChargeThread.nextFunction();
			}
		});
		button_4.setBounds(256, 333, 106, 35);
		panel_2.add(button_4);
		
		JLabel label_9 = new JLabel("\u5F53\u524D\u7B49\u5F85\u961F\u5217");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		label_9.setBounds(348, 11, 179, 30);
		panel_2.add(label_9);
		
		pay = new JLabel("\u9700\u4ED8\u6B3E");
		pay.setHorizontalAlignment(SwingConstants.CENTER);
		pay.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		pay.setBounds(147, 273, 251, 30);
		panel_2.add(pay);
		
		ID = new JTextField();
		ID.setEditable(false);
		ID.setColumns(10);
		ID.setBounds(123, 154, 106, 21);
		panel_2.add(ID);
		
		JLabel label_10 = new JLabel("\u75C5\u4EBA\u53F7\u7801\uFF1A");
		label_10.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_10.setBounds(10, 155, 106, 17);
		panel_2.add(label_10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "predict");
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A\u6302\u53F7");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ClientChargeThread.myPredictPatinetInfo.size()==0){
					return;
				}
				ClientChargeThread.sendPredictInfo();
				ClientChargeThread.updatePredictPatienInfo();
				if(ClientChargeThread.myPredictPatinetInfo.size()!=0){
					ClientChargeThread.PredictPatientInfo p=ClientChargeThread.myPredictPatinetInfo.get(0);
					Charge.pname.setText(p.name);
					Charge.psex.setText(p.sex);
					Charge.page.setText(p.age);
					Charge.pdepart.setText(p.department);
				}
				else{
					Charge.pname.setText("");
					Charge.psex.setText("");
					Charge.page.setText("");
					Charge.pdepart.setText("");
				}
				Charge.pnotice.setText("¹ÒºÅÍ¨Öª");
			}
		});
		btnNewButton.setBounds(77, 342, 81, 37);
		panel_1.add(btnNewButton);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane,"main");
				Charge.notice.setText("¹ÒºÅÍ¨Öª");
	    		//Charge.pnotice.setText(noti);
			}
		});
		button_1.setBounds(431, 342, 91, 37);
		panel_1.add(button_1);
		
		JLabel label_3 = new JLabel("\u5F53\u524D\u64CD\u4F5C\u7684\u4EBA");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		label_3.setBounds(25, 10, 179, 30);
		panel_1.add(label_3);
		
		JLabel label_11 = new JLabel("\u59D3\u540D\uFF1A");
		label_11.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_11.setBounds(11, 62, 54, 15);
		panel_1.add(label_11);
		
		pname = new JTextField();
		pname.setEditable(false);
		pname.setColumns(10);
		pname.setBounds(124, 61, 106, 21);
		panel_1.add(pname);
		
		psex = new JTextField();
		psex.setEditable(false);
		psex.setColumns(10);
		psex.setBounds(123, 90, 106, 21);
		panel_1.add(psex);
		
		JLabel label_12 = new JLabel("\u6027\u522B\uFF1A");
		label_12.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_12.setBounds(10, 95, 54, 16);
		panel_1.add(label_12);
		
		JLabel label_13 = new JLabel("\u5E74\u9F84\uFF1A");
		label_13.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_13.setBounds(11, 125, 54, 15);
		panel_1.add(label_13);
		
		page = new JTextField();
		page.setEditable(false);
		page.setColumns(10);
		page.setBounds(124, 119, 106, 21);
		panel_1.add(page);
		
		pdepart = new JTextField();
		pdepart.setEditable(false);
		pdepart.setColumns(10);
		pdepart.setBounds(124, 149, 106, 21);
		panel_1.add(pdepart);
		
		JLabel label_14 = new JLabel("\u9884\u7EA6\u79D1\u5BA4\uFF1A");
		label_14.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_14.setBounds(11, 150, 106, 17);
		panel_1.add(label_14);
		
		pwait = new JTextArea();
		pwait.setEditable(false);
		pwait.setBounds(266, 51, 343, 201);
		panel_1.add(pwait);
		
		JLabel label_15 = new JLabel("\u5F53\u524D\u7B49\u5F85\u961F\u5217");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		label_15.setBounds(363, 10, 179, 30);
		panel_1.add(label_15);
		
		JButton button_5 = new JButton("\u53D6\u6D88\u6302\u53F7");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientChargeThread.cancelPredictFunction();
			}
		});
		button_5.setBounds(198, 342, 81, 37);
		panel_1.add(button_5);
		
		JButton button_6 = new JButton("\u8DF3\u8FC7");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientChargeThread.nextPredictFunction();
			}
		});
		button_6.setBounds(316, 342, 81, 37);
		panel_1.add(button_6);
		
		pnotice = new JLabel("\u6302\u53F7\u901A\u77E5");
		pnotice.setHorizontalAlignment(SwingConstants.CENTER);
		pnotice.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		pnotice.setBounds(204, 282, 179, 30);
		panel_1.add(pnotice);
		
		
		
		JLabel lable111 = new JLabel("\u79D1\uFF1A");
		lable111.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lable111.setBounds(176, 265, 54, 15);
		panel.add(lable111);
		
		JRadioButton internal = new JRadioButton("\u5185\u79D1",true);
		internal.setBounds(259, 265, 64, 23);
		panel.add(internal);
		
		JRadioButton surgery = new JRadioButton("\u5916\u79D1");
		surgery.setBounds(343, 265, 72, 23);
		panel.add(surgery);
		
		JRadioButton paediatrics = new JRadioButton("\u513F\u79D1");
		paediatrics.setBounds(427, 265, 121, 23);
		panel.add(paediatrics);
		
		//JRadioButton radioButton_2 = new JRadioButton("\u4E13\u79D1");
	//	radioButton_2.setBounds(409, 265, 54, 23);
		//panel.add(radioButton_2);
		bg.add(surgery);
		bg.add(internal);
		bg.add(paediatrics);
		
		JButton btnNewButton_1 = new JButton("\u786E\u5B9A\u6302\u53F7");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String department = null;
				if(internal.isSelected()){
					department="internal";
				}
				else if(surgery.isSelected()){
					department="surgery";
				}
				else if(paediatrics.isSelected()){
					department="paediatrics";
				}
				ClientChargeThread.Send(department, name.getText(), sex.getText(), age.getText());
				notice.setText("µÈ´ý¹ÒºÅÐÅÏ¢");
			}
		});
		btnNewButton_1.setBounds(389, 307, 139, 33);
		panel.add(btnNewButton_1);
		
		JButton button_2 = new JButton("\u6536\u8D39");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "charge");
			}
		});
		button_2.setBounds(240, 307, 139, 33);
		panel.add(button_2);
		
		
	}
}
