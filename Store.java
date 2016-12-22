package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Store extends JFrame {

	private JPanel contentPane;
	static public JTextField sage;
	static public JTextField ssex;
	static public JTextField sname;
	static public JTextArea content;
	static public JTextArea wait;
	static public JLabel mark;
	static public JTextField ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Store frame = new Store();
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
	public Store() {
		this.setTitle("Ò©·¿¿Í»§¶Ë");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		wait = new JTextArea();
		wait.setEditable(false);
		wait.setBounds(277, 51, 343, 201);
		contentPane.add(wait);
		
		JLabel label = new JLabel("\u5F53\u524D\u7B49\u5F85\u961F\u5217");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		label.setBounds(374, 10, 179, 30);
		contentPane.add(label);
		
		content = new JTextArea();
		content.setEditable(false);
		content.setBounds(10, 227, 231, 174);
		contentPane.add(content);
		
		JLabel label_1 = new JLabel("\u836F\u54C1\u5185\u5BB9");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		label_1.setBounds(36, 187, 179, 30);
		contentPane.add(label_1);
		
		sage = new JTextField();
		sage.setEditable(false);
		sage.setColumns(10);
		sage.setBounds(136, 121, 106, 21);
		contentPane.add(sage);
		
		JLabel label_2 = new JLabel("\u5E74\u9F84\uFF1A");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_2.setBounds(24, 127, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u6027\u522B\uFF1A");
		label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_3.setBounds(22, 97, 54, 16);
		contentPane.add(label_3);
		
		ssex = new JTextField();
		ssex.setEditable(false);
		ssex.setColumns(10);
		ssex.setBounds(135, 92, 106, 21);
		contentPane.add(ssex);
		
		sname = new JTextField();
		sname.setEditable(false);
		sname.setColumns(10);
		sname.setBounds(135, 61, 106, 21);
		contentPane.add(sname);
		
		JLabel label_4 = new JLabel("\u59D3\u540D\uFF1A");
		label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_4.setBounds(22, 62, 54, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u5F53\u524D\u53D6\u836F\u7684\u4EBA");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		label_5.setBounds(36, 10, 179, 30);
		contentPane.add(label_5);
		
		JButton button = new JButton("\u4E0B\u4E00\u4E2A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientStoreThread.nextFunctioin();
			}
		});
		button.setBounds(447, 347, 106, 35);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u53D6\u836F\u5B8C\u6210");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ClientStoreThread.myWaitPatient.size()==0){
					return;
				}
				
				ClientStoreThread.sendInfo();
				ClientStoreThread.updatePaitenInfo();
				ClientStoreThread.showCurrentMedicineInfo();
				if(ClientStoreThread.myWaitPatient.size()!=0){
					ClientStoreThread.StorePatientInfo cs=ClientStoreThread.myWaitPatient.get(0);
					Store.sname.setText(cs.name);
					Store.ssex.setText(cs.sex);
					Store.sage.setText(cs.age);
					Store.ID.setText(cs.id);
					if(cs.isPay){
						Store.mark.setText("´Ë²¡ÈËÒÑ¾­¸¶¿î");
					}
					else{
						Store.mark.setText("ÉÐÎ´¸¶¿î");
					}
					
				}
					
				
			}
		});
		button_1.setBounds(327, 347, 106, 35);
		contentPane.add(button_1);
		
		mark = new JLabel("\u662F\u5426\u5DF2\u4ED8\u6B3E");
		mark.setHorizontalAlignment(SwingConstants.CENTER);
		mark.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		mark.setBounds(355, 282, 179, 30);
		contentPane.add(mark);
		
		JLabel label_6 = new JLabel("\u75C5\u4EBA\u53F7\u7801\uFF1A");
		label_6.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label_6.setBounds(22, 153, 106, 17);
		contentPane.add(label_6);
		
		ID = new JTextField();
		ID.setEditable(false);
		ID.setColumns(10);
		ID.setBounds(135, 152, 106, 21);
		contentPane.add(ID);
	}
}
