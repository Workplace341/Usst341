package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.DropMode;

public class Client extends JFrame {

	private final String IP="192.168.1.107";//"10.20.179.1";//"10.40.140.14";////"10.20.160.19";////"172.30.42.6";
	private final int PORT=12000;
	private JPanel contentPane;
	private JTextField account;
//	private JTextField password;
	JPasswordField password;
    private ButtonGroup bg=new ButtonGroup();
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    private ClientChargeThread cct;
    private ClientDoctorThread cdt;
    private ClientStoreThread  cst;
    private ClientAdminThread cat;
    private ClientPresidentThread cpt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	public Client() {
		try {
			socket=new Socket(IP,PORT);
			pw= new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			         //”Î∑˛ŒÒ∆˜¡¨Ω” ß∞‹
			e.printStackTrace();
		}
		this.setTitle("µ«¬ºøÕªß∂À");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		account = new JTextField();
		account.setBounds(290, 171, 104, 21);
		contentPane.add(account);
		account.setColumns(10);
		
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setBounds(232, 174, 48, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(232, 202, 48, 15);
		contentPane.add(label_1);
		
		password = new JPasswordField();
		//password = new JTextField();
		password.setBounds(290, 202, 104, 21);
		password.setColumns(10);
		contentPane.add(password);
		
		JRadioButton doctor = new JRadioButton("\u533B\u751F");
		doctor.setBounds(186, 242, 66, 23);
		contentPane.add(doctor);
		
		JRadioButton charge = new JRadioButton("\u6536\u8D39\u4EBA\u5458",true);
		charge.setBounds(282, 242, 104, 23);
		contentPane.add(charge);
		
		JRadioButton store = new JRadioButton("\u836F\u5E08");
		store.setBounds(398, 242, 88, 23);
		contentPane.add(store);
		
		JRadioButton admin = new JRadioButton("\u7BA1\u7406\u5458");
		admin.setBounds(282, 267, 90, 23);
		contentPane.add(admin);
		
		JRadioButton president = new JRadioButton("\u9662\u957F");
		president.setBounds(186, 267, 79, 23);
		contentPane.add(president);
		
		
		
		bg.add(doctor);
		bg.add(store);
		bg.add(president);
		bg.add(charge);
		bg.add(admin);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setBounds(325, 313, 93, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(account.getText().equals("")||password.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane,"«Î ‰»Î’À∫≈√‹¬Î");
					return ;
				}
				
				
				if(charge.isSelected()){
					login("charge");
				}
				else if(doctor.isSelected()){
					login("doctor");
				}
				else if(store.isSelected()){
					login("store");
				}
				else if(admin.isSelected()){
					login("admin");
				}
				else if(president.isSelected()){
					login("president");
				}
			}
		});
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("\u533B\u9662\u7CFB\u7EDF\u767B\u5F55\u754C\u9762");
		lblNewLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(165, 32, 321, 82);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(196, 313, 93, 23);
		contentPane.add(btnNewButton);
	}
	
	public void login(String type){
		String acc=account.getText();
		String pas=password.getText();
		pw.println(type+","+acc+","+pas);
		System.out.println(type+","+acc+","+pas);
		pw.flush();
		try {
			String t=br.readLine();
			System.out.println(t);
			String[] sign=t.split(",");
			System.out.println("sign:"+sign[0]);
			if(sign[0].equals("OK")){
				Point p=this.getLocation();
				setVisible(false);
				switch(type){
				case "charge":new Charge(p).setVisible(true);cct=new ClientChargeThread(socket);cct.start();break;
				case "doctor":new Doctor(sign[1],sign[2],p).setVisible(true);cdt=new ClientDoctorThread(socket);cdt.start();break;
				case "store":new Store(p).setVisible(true);cst =new ClientStoreThread(socket);cst.start();break;
				case "admin":new Admin(p).setVisible(true);cat =new ClientAdminThread(socket);cat.start();break;
				case "president":new Charge(p).setVisible(true);cpt =new ClientPresidentThread(socket);cpt.start();break;
				default :break;
				}
			}
			else{
				if(account.getText().equals("")||password.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane,"’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
					return ;
				}
			}
		} catch (IOException e) {
			                   //¡¨Ω” ß∞‹
			e.printStackTrace();
		}	
			
	}		
}
