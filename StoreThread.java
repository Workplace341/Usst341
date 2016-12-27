package finalServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collector.Characteristics;

public class StoreThread extends Thread {
	Socket client;
	BufferedReader br;

	PrintWriter pw;
	StoreThread(Socket s){
		this.client=s;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void run(){
		String info;
		try {
			while(true){
			info=br.readLine();
			analysis(info);  //��������
			System.out.println("�������Ӳֿ��յ���Ϣ��"+info);
			}
		} catch (IOException e) {
			          //��ͻ�������ʧ��
			e.printStackTrace(); 
			//break;  
		}	}
	
	//info=boolean,price,name,sex,age,id#medicine,count-..
	private void analysis(String info) {
		String str[]=info.split("#");
		String property[]=str[0].split(",");
		if(property[0].equals("true")){
			
			String Minfo[]=str[1].split("-");
			for(String s:Minfo){
				String m[]=s.split(",");
				SQLOperate.declineMedicineCount(m[0], Integer.parseInt(m[1]));
			}
			System.out.println(ChargeThread.allPatient.get(property[5]).department);
			
			Server.sendMessageToPresident();      //���͸�����Ϣ��Ժ��
			Server.sendMessageToAdmin();        //���͸�����Ϣ������Ա
			
			ChargeThread.allPatient.remove(property[5]);
			//ChargeThread.patientDepart.remove(property[5]);
		}
	
	}

}
