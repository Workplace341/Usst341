

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;



public class clientAdminThread extends Thread {
	class medicineDetail{
		String name;
		String count;
		String price;
	}
	class accountDetail{
		String account;
		String password;
		String name;
		String department;
		String age;
		String sex;
	}
	private Socket server;
	private PrintWriter pw;
    private BufferedReader br;
    static public ArrayList<medicineDetail> medicine1=new ArrayList<medicineDetail>();
    static public ArrayList<accountDetail> account1=new ArrayList<accountDetail>();
    static public String medicineInfo="";
    static public String accountInfo="";
	clientAdminThread(Socket server){
		this.server=server;	    
	}
	public clientAdminThread() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		String message;
		try {
			while(true){
				message=br.readLine();
				analysis(message); 
				System.out.println(""+message);   
				}
								
		} catch (IOException e) {
			           
			e.printStackTrace();
		}
	}
	
	void analysis(String message)
	{
		String[] kind=message.split("-");
		if(kind[0]=="medicine")
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
		}
		else if(kind[1]=="account")
		{
			String[] account=kind[1].split("#");
			
			for(int i=1;i<=account.length;i++)
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
		}
		for(int i=0;i<medicine1.size();i++)
		{
			medicineInfo=medicineInfo+medicine1.get(i).name+"\t";
			medicineInfo=medicineInfo+medicine1.get(i).count+"\t";
			medicineInfo=medicineInfo+medicine1.get(i).price+"\n";
			
		}
		for(int i=0;i<account1.size();i++)
		{
			accountInfo=accountInfo+account1.get(i).account+"\t";
			accountInfo=accountInfo+account1.get(i).password+"\t";
			accountInfo=accountInfo+account1.get(i).name+"\t";
			accountInfo=accountInfo+account1.get(i).department+"\t";
			accountInfo=accountInfo+account1.get(i).age+"\t";
			accountInfo=accountInfo+account1.get(i).sex+"\n";
		}
		 
		
		
		
	}
	
}