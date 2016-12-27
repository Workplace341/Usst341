package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

//import client.ClientAdminThread.accountDetail;
//import client.ClientAdminThread.medicineDetail;
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
public class ClientPresidentThread extends Thread {
	public static class paediatricsDetail{
		String number;
		String totalNumber;
		String money;
	}
	public static class surgeryDetail{
		String number;
		String totalNumber;
		String money;
	}
	public static class internalDetail{
		String number;
		String totalNumber;
		String money;
	}
	public static class medicineDetail{
		String name;
		String count;
		String price;
	}
	public static class doctorDetail{
		String name;
		String department;
		String count;
		String income;
	}
	private Socket server;
	private PrintWriter pw;
    private BufferedReader br;
    
    
    
    static paediatricsDetail pae=new paediatricsDetail();
    static surgeryDetail sur=new surgeryDetail();
    static internalDetail inter=new internalDetail();
    static public ArrayList<medicineDetail> medicine2=new ArrayList<medicineDetail>();
    static public ArrayList<doctorDetail> doctor2=new ArrayList<doctorDetail>();
    static public String medicineInfo2="";
    static public String accountInfo2="";
    static public String paediatricsInfo2="";
    static public String surgeryInfo2="";
    static public String internalInfo2="";
    
    static public double surgeryM;
    static public double internalM;
    static public double paediatricsM;
    static public double totalMoney;
	ClientPresidentThread(Socket server){
		this.server=server;	 
		try {
			//System.out.println("shoudaooooo"); 
			br=new BufferedReader(new InputStreamReader(server.getInputStream()));
			pw=new PrintWriter(server.getOutputStream());
		} catch (IOException e) {
			            //与服务器连接失败
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		//System.out.println("president从服务器收到的信息：");
		String message;
		try {
			while(true){
				message=br.readLine();
				System.out.println("president从服务器收到的信息："+message);
				analysis(message);  //分析数据
				}
								
		} catch (IOException e) {
			           //连接失败
			e.printStackTrace();
		}
	}
	
	void analysis(String message)
	{
		String[] kind=message.split("-");
		if(kind[0].equals("paediatrics"))
		{
			paediatricsInfo2="";
			String[] paediatrics=kind[1].split(",");
			pae.number=paediatrics[0];
			pae.totalNumber=paediatrics[1];
			pae.money=paediatrics[2];
			paediatricsM=Double.parseDouble(pae.money);
			paediatricsInfo2+=pae.number+"\t"+pae.totalNumber+"\t"+pae.money;
			President.textField_2.setText(paediatricsInfo2);
			
			totalMoney=paediatricsM+internalM+surgeryM;
			int x=(int)(surgeryM/totalMoney*100);
			int y=(int)(internalM/totalMoney*100);
			int z=(int)(paediatricsM/totalMoney*100);
			//System.out.println(surgeryM/totalMoney);
			//President.lblNewLabel_4.setBounds(135, 50,34,200);
			President.lblNewLabel_4.setBounds(135, 250-y*2,34,y*2);
			President.label_4.setBounds(234,250-x*2,34,x*2);
			President.label_5.setBounds(329,250-z*2,34,z*2);
			President.label_9.setText( String.valueOf(internalM));
			President.label_10.setText( String.valueOf(surgeryM));
			President.label_11.setText( String.valueOf(paediatricsM));
			
			
			
			
		}
		else if(kind[0].equals("surgery"))
		{
			surgeryInfo2="";
			String[] surgery=kind[1].split(",");
			sur.number=surgery[0];
			sur.totalNumber=surgery[1];
			sur.money=surgery[2];
			surgeryM=Double.parseDouble(sur.money);
			surgeryInfo2+=sur.number+"\t"+sur.totalNumber+"\t"+sur.money;
			President.textField_1.setText(surgeryInfo2);
		}
		else if(kind[0].equals("internal"))
		{
			internalInfo2="";
			String[] internal=kind[1].split(",");
			inter.number=internal[0];
			inter.totalNumber=internal[1];
			inter.money=internal[2];
			internalM=Double.parseDouble(inter.money);
			internalInfo2+=inter.number+"\t"+inter.totalNumber+"\t"+inter.money;
			President.textField.setText(internalInfo2);
		}
		else if(kind[0].equals("medicine"))
		{
			String[] medicine=kind[1].split("#");
			medicine2.clear();
			for(int i=0;i<medicine.length;i++)
			{
				medicineDetail a=new medicineDetail();
				String content[]=medicine[i].split(",");
				a.name=content[0];
				a.count=content[1];
				a.price=content[2];
				medicine2.add(a);
			}
			President.textArea.setText("");
			medicineInfo2="";
			medicineInfo2=medicineInfo2+"name"+"\t";
			medicineInfo2=medicineInfo2+"count"+"\t";
			medicineInfo2=medicineInfo2+"price"+"\n";
			for(int i=0;i<medicine2.size();i++)
			{
				
				medicineInfo2=medicineInfo2+medicine2.get(i).name+"\t";
				medicineInfo2=medicineInfo2+medicine2.get(i).count+"\t";
				medicineInfo2=medicineInfo2+medicine2.get(i).price+"\n";
			}
			President.textArea.setText(medicineInfo2);
			
		}
		else if(kind[0].equals("doctor"))
		{
			String[] account=kind[1].split("#");
			doctor2.clear();
			for(int i=0;i<account.length;i++)
			{
				doctorDetail a=new doctorDetail();
				String content[]=account[i].split(",");
				a.name=content[0];
				a.department=content[1];
				a.count =content[2];
				a.income=content[3];
				doctor2.add(a);
			}
			President.textArea_1.setText("");
			accountInfo2="";
			accountInfo2=accountInfo2+"name"+"\t";
			accountInfo2=accountInfo2+"department"+"\t";
			accountInfo2=accountInfo2+"count"+"\t";
			accountInfo2=accountInfo2+"income"+"\n";
			
			for(int i=0;i<doctor2.size();i++)
			{
				accountInfo2=accountInfo2+doctor2.get(i).name+"\t";
				accountInfo2=accountInfo2+doctor2.get(i).department+"\t";
				accountInfo2=accountInfo2+doctor2.get(i).count+"\t";
				accountInfo2=accountInfo2+doctor2.get(i).income+"\n";
			}
			President.textArea_1.setText(accountInfo2);
		}
	}

}
