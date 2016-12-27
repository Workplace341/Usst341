package client;
//import org.jfree.chart.ChartFactory;  
//import org.jfree.chart.ChartPanel;  
//import org.jfree.chart.ChartUtilities;  
//import org.jfree.chart.JFreeChart;  
//import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
//import org.jfree.chart.plot.MultiplePiePlot;  
//import org.jfree.chart.plot.PiePlot;  
//import org.jfree.chart.plot.PiePlot3D;  
//import org.jfree.chart.title.TextTitle;  
//import org.jfree.data.category.CategoryDataset;  
//import org.jfree.data.general.DatasetUtilities;  
//import org.jfree.data.general.DefaultPieDataset;  
//import org.jfree.data.general.PieDataset;  
//import org.jfree.util.Rotation;  
//import org.jfree.util.TableOrder;  
//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Point;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class President extends JFrame {

	private JPanel contentPane;
	static JTextField textField;
	static JTextField textField_1;
	static JTextField textField_2;
	public static JTextArea textArea;
	public static JTextArea textArea_1;
	
	public static JLabel lblNewLabel_4;
	public static JLabel label_4;
	public static JLabel label_5;
	public static JLabel label_9;
	public static JLabel label_10;
	public static JLabel label_11;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//President frame = new President();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public President(Point p) {
		this.setTitle("president‘∫≥§");
		this.setSize(564,374);
		this.setLocation(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 552, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout card=new CardLayout(0, 0);
		contentPane.setLayout(card);
		
		Panel panel = new Panel();
		contentPane.add(panel, "panel");
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u9662\u957Fpresident");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(387, 22, 135, 51);
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
		textField.setEditable(false);
		textField.setBounds(72, 69, 269, 33);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(72, 114, 269, 33);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(72, 159, 269, 33);
		panel.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("\u5F53\u524D\u6302\u53F7\u6570");
		lblNewLabel_2.setBounds(72, 41, 65, 16);
		panel.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("\u603B\u6302\u53F7\u6570");
		label_2.setBounds(171, 41, 65, 16);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u603B\u91D1\u989D");
		label_3.setBounds(263, 41, 65, 16);
		panel.add(label_3);
		
		JButton btnMedicine = new JButton("Medicine");
		btnMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel_1");
			}
		});
		btnMedicine.setBounds(72, 240, 128, 45);
		panel.add(btnMedicine);
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel_2");
			}
		});
		btnDoctor.setBounds(229, 240, 128, 45);
		panel.add(btnDoctor);
		
		JButton button = new JButton("\u79D1\u5BA4\u6536\u76CA\u7EDF\u8BA1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane,"panel_3");
			}
		});
		button.setBounds(387, 85, 117, 45);
		panel.add(button);
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, "panel_1");
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Medicine  detail");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(20, 6, 195, 42);
		panel_1.add(lblNewLabel_3);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(20, 60, 300, 267);
		panel_1.add(textArea);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel");
			}
		});
		btnBack.setBounds(387, 281, 138, 37);
		panel_1.add(btnBack);
		
		Panel panel_2 = new Panel();
		contentPane.add(panel_2, "panel_2");
		panel_2.setLayout(null);
		
		JLabel lblDoctorDetail = new JLabel("Doctor  detail");
		lblDoctorDetail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDoctorDetail.setBounds(21, 16, 124, 28);
		panel_2.add(lblDoctorDetail);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(16, 51, 343, 269);
		panel_2.add(textArea_1);
		
		JButton btnBack_1 = new JButton("back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel");
			}
		});
		btnBack_1.setBounds(405, 275, 116, 45);
		panel_2.add(btnBack_1);
		
		Panel panel_3 = new Panel();
		contentPane.add(panel_3, "panel_3");
		panel_3.setLayout(null);
		
		JLabel lblinternal = new JLabel("\u5185\u79D1internal");
		lblinternal.setBounds(114, 283, 74, 16);
		panel_3.add(lblinternal);
		
		JLabel lblsuegery = new JLabel("\u5916\u79D1sugery");
		lblsuegery.setBounds(213, 283, 87, 16);
		panel_3.add(lblsuegery);
		
		JLabel lblpae = new JLabel("\u513F\u79D1paediatrics");
		lblpae.setBounds(312, 283, 112, 16);
		panel_3.add(lblpae);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(new Color(106, 90, 205));
		lblNewLabel_4.setBackground(new Color(135, 206, 250));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBounds(135, 123, 34, 127);
		panel_3.add(lblNewLabel_4);
		
		label_4 = new JLabel("");
		label_4.setForeground(new Color(106, 90, 205));
		label_4.setBackground(new Color(0, 250, 154));
		label_4.setBounds(234, 123, 34, 127);
		label_4.setOpaque(true);
		panel_3.add(label_4);
		
		label_5 = new JLabel("");
		label_5.setOpaque(true);
		label_5.setForeground(new Color(106, 90, 205));
		label_5.setBackground(new Color(255, 69, 0));
		label_5.setBounds(329, 123, 34, 127);
		panel_3.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setOpaque(true);
		label_6.setForeground(new Color(106, 90, 205));
		label_6.setBackground(new Color(0, 0, 0));
		label_6.setBounds(440, 50, 2, 200);
		panel_3.add(label_6);
		
		JLabel lblNewLabel_5 = new JLabel("0%");
		lblNewLabel_5.setBounds(458, 234, 74, 16);
		panel_3.add(lblNewLabel_5);
		
		JLabel label_7 = new JLabel("50%");
		label_7.setBounds(459, 134, 74, 16);
		panel_3.add(label_7);
		
		JLabel label_8 = new JLabel("100%");
		label_8.setBounds(458, 34, 74, 16);
		panel_3.add(label_8);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBackground(new Color(0, 0, 0));
		lblNewLabel_6.setBounds(104, 250, 338, 2);
		lblNewLabel_6.setOpaque(true);
		panel_3.add(lblNewLabel_6);
		
		label_9 = new JLabel(" ");
		label_9.setBounds(127, 262, 61, 16);
		panel_3.add(label_9);
		
		label_10 = new JLabel(" ");
		label_10.setBounds(226, 262, 61, 16);
		panel_3.add(label_10);
		
		label_11 = new JLabel(" ");
		label_11.setBounds(323, 262, 61, 16);
		panel_3.add(label_11);
		
		JButton button_1 = new JButton("<back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "panel");
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setBounds(23, 20, 113, 44);
		panel_3.add(button_1);
		
		JLabel lblNewLabel_7 = new JLabel("\u79D1\u5BA4\u6536\u76CA\u7EDF\u8BA1");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(197, 20, 112, 23);
		panel_3.add(lblNewLabel_7);
		
		 
	}
}
