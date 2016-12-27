package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientShowThread extends Thread {

	class OtherThread extends Thread{
		@Override
		public void run() {
			while(true){
				try {
					sleep(10000);
					
					if(ClientShowThread.this.currentIndex==2){
						ClientShowThread.this.currentIndex=0;
					}
					else{
						ClientShowThread.this.currentIndex++;
					}
					ClientShowThread.this.update();	
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class Message{
		Message(String d){
			message="ҽ������\t�Ŷ�����\n";
			depart=d;
		}
		String depart;
		String message;
	}
	
	final String IP="192.168.1.107";//"10.20.179.1";//"10.40.140.14";//"192.168.1.107";
	final int PORT=12000;
    public PrintWriter pw;
	private BufferedReader br;
	public ArrayList<Message> myMessage=new ArrayList<Message>();	
	public int currentIndex=0;
	
    public ClientShowThread() {	
	    Socket socket;
	    try {
			socket = new Socket(IP,PORT);
			pw= new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    pw.println("show,show");
			pw.flush();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    initMyMessage();
	    
	    new OtherThread().start();
    } 
    
   

	@Override
    public void run() {
    	String message;
		try {
			while(true){
				message=br.readLine();
				//System.out.println("ԤԼ�˴ӷ������յ�����Ϣ��"+message);
				analysis(message);  //��������
				System.out.println(message);
				update();
				}
								
		} catch (IOException e) {
			           //����ʧ��
			e.printStackTrace();
		}
    }

    //message=department-doctorName,count*doctorName,count#
	private void analysis(String message) {
		String str[]=message.split("#");
		clearMyMessage();
		for(String s:str){
			String str1[]=s.split("-");
			Message m=chooseMessage(str1[0]);
			System.out.println(s);		
			if((!s.equals("surgery-"))&&(!s.equals("internal-"))&&(!s.equals("paediatrics-"))){
				String str2[]=str1[1].split("=");
				for(String t:str2){
					String y[]=t.split(",");
					m.message+=(y[0]+"\t");
					m.message+=(y[1]+"\n");
				}
			}		
			//myMessage.add(m);
		}
	}
	
	 private void initMyMessage() {
			Message m1=new Message("surgery"); 
			Message m2=new Message("internal"); 
			Message m3=new Message("paediatrics"); 
			myMessage.add(m1);
			myMessage.add(m2);
			myMessage.add(m3);
		}
	
	private void clearMyMessage() {
		for(Message i:myMessage){
			i.message="ҽ������\t�Ŷ�����\n";
		}
		
	}

	private Message chooseMessage(String string) {
		for(Message i:myMessage){
			if(i.depart.equals(string)){
				return i;
			}
		}
		return null;
	}

	 public void sendInfo(String info){
//		pw.println("predict,"+info);
//		pw.flush();
	}
	
	 public void update(){
		Show.departName.setText(myMessage.get(currentIndex).depart);
		Show.textArea.setText(myMessage.get(currentIndex).message);
	}

}


