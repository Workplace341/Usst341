

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;



public class clientAdminThread extends Thread {
	class medicineDetail{
		String name;
		int count;
		int price;
	}
	class accountDetail{
		String account;
		String password;
		String name;
		String department;
		int age;
		String sex;
	}
	private Socket server;
	private PrintWriter pw;
    private BufferedReader br;
    static public ArrayList<medicineDetail> medicine1=new ArrayList<medicineDetail>();
    static public ArrayList<accountDetail> account1=new ArrayList<accountDetail>();
	clientAdminThread(Socket server){
		this.server=server;	    
	}
	@Override
	public void run() {
		String message;
		try {
			while(true){
				message=br.readLine();
				analysis(message);  //∑÷Œˆ ˝æ›
				System.out.println("¥”∑˛ŒÒ∆˜ ’µΩ≤°»Àµƒ–≈œ¢£∫"+message);   
				}
								
		} catch (IOException e) {
			           //¡¨Ω” ß∞‹
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
				a.count=Integer.parseInt(content[1]);
				a.price=Integer.parseInt(content[2]);
				medicine1.add(a);
			}
		}
		 if(kind[1]=="account")
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
				a.age=Integer.parseInt(content[4]);
				a.sex=content[5];
				
				account1.add(a);
			}
		}
		
		
		
	}
	
}