package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Show extends JFrame {

	private JPanel contentPane;
    static public JTextArea textArea;
    static public JLabel departName;
    public ClientShowThread myThread;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Show frame = new Show();
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
	public Show() {
		this.setTitle("滚动屏");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		CardLayout card=new CardLayout(0, 0);
		contentPane.setLayout(card);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "name_174806398410156");
		panel.setLayout(null);
		
		departName = new JLabel("\u79D1\u5BA4\u540D\u5B57");
		departName.setBounds(214, 22, 185, 56);
		departName.setHorizontalAlignment(SwingConstants.CENTER);
		departName.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		panel.add(departName);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		textArea.setBounds(72, 94, 463, 211);
		panel.add(textArea);
		
		JButton btnNewButton = new JButton("\u67E5\u770B\u4E0A\u4E00\u4E2A\u79D1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(myThread.currentIndex==0){
					//提示信息
					myThread.currentIndex=2;
					myThread.update();
					return ;
				}
				myThread.currentIndex--;
				myThread.update();
			}
		});
		btnNewButton.setBounds(153, 335, 116, 36);
		panel.add(btnNewButton);
		
		JButton button = new JButton("\u67E5\u770B\u4E0B\u4E00\u4E2A\u79D1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(myThread.currentIndex==2){
					//提示信息
					myThread.currentIndex=0;
					myThread.update();
					return ;
				}
				myThread.currentIndex++;
				myThread.update();
			}
		});
		button.setBounds(334, 335, 116, 36);
		panel.add(button);
		myThread=new ClientShowThread();
		myThread.start();
	}
}
