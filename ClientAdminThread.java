package client;






import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.text.JTextComponent;



public class ClientAdminThread extends Thread {
	public static class medicineDetail{
		String name;
		String count;
		String price;
	}
	public static class accountDetail{
		String account;
		String password;
		String name;
		String department;
		String age;
		String sex;
	}
	private Socket server;
	private static PrintWriter pw;
    private BufferedReader br;
    static public ArrayList<medicineDetail> medicine1=new ArrayList<medicineDetail>();
    static public ArrayList<accountDetail> account1=new ArrayList<accountDetail>();
    static public String medicineInfo="";
    static public String accountInfo="";
	ClientAdminThread(Socket server){
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
		//System.out.println("admin从服务器收到的信息：");
		String message;
		try {
			while(true){
				message=br.readLine();
				System.out.println("admin从服务器收到的信息："+message);
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
		if(kind[0].equals("medicine"))
		{
			String[] medicine=kind[1].split("#");
			
			for(int i=0;i<medicine.length;i++)
			{
				medicineDetail a=new medicineDetail();
				String content[]=medicine[i].split(",");
				a.name=content[0];
				a.count=content[1];
				a.price=content[2];
				medicine1.add(a);
			}
			medicineInfo=medicineInfo+"name"+"\t";
			medicineInfo=medicineInfo+"count"+"\t";
			medicineInfo=medicineInfo+"price"+"\n";
			for(int i=0;i<medicine1.size();i++)
			{
				
				medicineInfo=medicineInfo+medicine1.get(i).name+"\t";
				medicineInfo=medicineInfo+medicine1.get(i).count+"\t";
				medicineInfo=medicineInfo+medicine1.get(i).price+"\n";
			}
//			Admin.textArea_1.setText(medicineInfo);
//			Admin.textField_6.setText(medicine1.get(Admin.medicineIndex).name);
//			Admin.textField_7.setText(medicine1.get(Admin.medicineIndex).count);
//			Admin.textField_8.setText(medicine1.get(Admin.medicineIndex).price);
			
		}
		else if(kind[0].equals("account"))
		{
			String[] account=kind[1].split("#");
			
			for(int i=0;i<account.length;i++)
			{
				accountDetail a=new accountDetail();
				String content[]=account[i].split(",");
				a.account=content[0];
				a.password=content[1];
				a.name=content[2];
				a.department=content[3];
				a.age=content[4];
				a.sex=content[5];
				
				account1.add(a);
			}
			accountInfo=accountInfo+"account"+"\t";
			accountInfo=accountInfo+"password"+"\t";
			accountInfo=accountInfo+"name"+"\t";
			accountInfo=accountInfo+"department"+"\t";
			accountInfo=accountInfo+"age"+"\t";
			accountInfo=accountInfo+"sex"+"\n";
			for(int i=0;i<account1.size();i++)
			{
				accountInfo=accountInfo+account1.get(i).account+"\t";
				accountInfo=accountInfo+account1.get(i).password+"\t";
				accountInfo=accountInfo+account1.get(i).name+"\t";
				accountInfo=accountInfo+account1.get(i).department+"\t";
				accountInfo=accountInfo+account1.get(i).age+"\t";
				accountInfo=accountInfo+account1.get(i).sex+"\n";
			}
//			Admin.textArea.setText(accountInfo);
//			Admin.textField.setText(ClientAdminThread.account1.get(Admin.accountIndex).account);
//			Admin.textField_1.setText(ClientAdminThread.account1.get(Admin.accountIndex).password);
//			Admin.textField_2.setText(ClientAdminThread.account1.get(Admin.accountIndex).name);
//			Admin.textField_3.setText(ClientAdminThread.account1.get(Admin.accountIndex).department);
//			Admin.textField_4.setText(ClientAdminThread.account1.get(Admin.accountIndex).age);
//			Admin.textField_5.setText(ClientAdminThread.account1.get(Admin.accountIndex).sex);
		}
		//System.out.println("123");
		
		 
		
		
		
	}
	 static public void sendInfo(String str){
	    	pw.println(str);
			pw.flush();
			System.out.println("发送给服务器："+str);
	    }
}
