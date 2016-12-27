package finalServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import finalServer.Server;

import finalServer.Server.DoctorInfo;

public class SQLOperate {
	
	Connection con;
//	static public ResultSet rs=null;
	static public Statement st = null;
	public SQLOperate(Connection con) {
		this.con=con;
		try {
			 st =Server.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void doctorReadMedicineData() {//从文件读取数据，更新到medicineData
		try {
		    
			Statement st =Server.con.createStatement();
			ResultSet rs=st.executeQuery("select * from medicine");
			
			while(rs.next()){
				Server.MedicineInfo mi=new Server.MedicineInfo(rs.getString(1).trim(),rs.getInt(2),rs.getInt(3));
				DoctorThread.medicineData.put(rs.getString(1).trim(), mi);
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	static public void updateDoctorRegisterIncome(String doctorAccount){
		String command="update doctor set income=income+10,registerIncome=registerIncome+10 where account='"+doctorAccount+"'";
		try {
			st.execute(command);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void updateDoctorMedicineIncome(String doctorAccount,int price){
		String command="update doctor set income=income+"+price+" ,medicineIncome=medicineIncome+"+price+" where account='"+doctorAccount+"'";
		try {
			st.execute(command);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void updateDepartmentRegisterIncome(String depart){
		String command1="update departmentIncome set income=income+10 where department='"+depart+"'";
		String command2="update departmentIncome set registerIncome=registerIncome+10 where department='"+depart+"'";
		try {
			st.execute(command1);
			st.execute(command2);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public void  updateDepartmentMedicineIncome(String depart,int price){
		String command1="update departmentIncome set income=income+"+price+" where department='"+depart+"'";
		String command2="update departmentIncome set medicineIncome=medicineIncome+"+price+" where department='"+depart+"'";
		try {
			st.execute(command1);
			st.execute(command2);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void declineMedicineCount(String name,int count){
		String command="update medicine set count=count-"+count+" where name='"+name+"'";
		try {
			st.execute(command);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public boolean login(String type,String account, String password) { //登录函数
		
		
		String command="select * from account where type='";
		command+=(type+"' and account='");
		command+=(account+"' and password='");
		command+=(password+"'");
		try {
			ResultSet rs=st.executeQuery(command);
			if(rs.next()){
				rs.close();
				return true;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	     return false;
//		
	}
	
	
	

	
	static public String sendMedicineInfoToAdmin(){
		
		//Medicine m=new MedicineInfo(name, count, price);
		String message="";
		String command="select * from medicine";
		
		try {
			ResultSet rs=st.executeQuery(command);
			while(rs.next()){
				message+=(rs.getString(1).trim()+",");
				message+=(rs.getString(2).trim()+",");
				message+=(rs.getString(3).trim()+"#");
			}
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	
	static public void addOnlineDoctorInfo(String string){
		String command="select * from doctor where account='"+string+"'";
		try {
			ResultSet rs=st.executeQuery(command);
			rs.next();
			Server.DoctorInfo di=new Server.DoctorInfo();
			di.account=rs.getString(1).trim();
			di.name=rs.getString(2).trim();
			di.waitCount=0;
			di.department=rs.getString(5).trim();
			Server.onlineDoctor.add(di);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	static public void addMedicineInfo(String medicine,String count,String price){
		String command="insert into medicine values('"+medicine+"',"+count+","+price+")";
		try {
			st.execute(command);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	static public void changeMedicineInfo(String medicine,String newName,String count,String price){
		String command="update medicine set name='"+newName+"', count="+count+",price="+price+" where name='"+medicine+"'";
		try {
			st.execute(command);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	static public void deleteMedicineInfo(String medicine){
		String command="delete medicine where name='"+medicine+"'";
		try {
			st.execute(command);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
	static public String sendDoctorInfoToAdmin(){
		String message="";
		String command1="select * from account";
		String command2="select * from doctor";
		HashMap<String,String> ps=new HashMap<String,String>();
		try {
			ResultSet rs1=st.executeQuery(command1);
			
			while(rs1.next()){
				ps.put(rs1.getString(1).trim(), rs1.getString(2).trim());
			}
		
			ResultSet rs2=st.executeQuery(command2);	
			while(rs2.next()){
				String a=rs2.getString(1).trim();
				message+=(a+",");
				message+=(ps.get(a)+",");
				message+=(rs2.getString(2).trim()+",");
				message+=(rs2.getString(5).trim()+",");
				message+=(rs2.getString(3).trim()+",");
				message+=(rs2.getString(4).trim()+"#");
			}
			rs1.close();
			rs2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(message);
		return message;
	}
	

	
	static public void addDoctorInfo(String account,String password,String name,String age,String sex,String depart){
		String command1="insert into account values ('"+account+"','"+password+"','doctor')";
		String command2="insert into doctor values('"+account+"','"+name+"','"+age+"','"+sex+"','"+depart+"',0,0,0)";
		try {
			st.execute(command1);
			st.execute(command2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	static public void changeDoctorInfo(String account,String password,String name,String age,String sex,String depart){
		String command1="update doctor set name='"+password+"' where account='"+account+"'";
		String command2="update account set name='"+name+"',age='"+age+"',sex='"+sex+"',department='"+depart+"' where account='"+account+"'";
		try {
			st.execute(command1);
			st.execute(command2);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	static public void deleteDoctorInfo(String account){
		String command1="delete doctor where account='"+account+"'";
		String command2="delete account where account='"+account+"'";
		try {
			st.execute(command1);
			st.execute(command2);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	static public String sendDoctorInfoToPresident(){
	
		String message="";
		String command="select * from doctor";
		try {
			ResultSet rs=st.executeQuery(command);
					
			while(rs.next()){
				message+=(rs.getString(2).trim()+",");
				message+=(rs.getString(5).trim()+",");
				message+=((rs.getInt(7)/10)+",");
				message+=(rs.getString(8).trim()+"#");
			}
			rs.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(message);
		return message;
	}
	
	static public String sendDepartmentInfoToPresident(){
		
		String message1="";
		String message2="";
		String message3="";
		String command="select * from departmentIncome";
		try {
			ResultSet rs=st.executeQuery(command);
					
			while(rs.next()){
				String depart=rs.getString(1).trim();
				if(depart.equals("internal")){
					message1+=((rs.getInt(2)/10)+",");
					message1+=(rs.getString(4).trim());
				}
				else if(depart.equals("surgery")){
					message2+=((rs.getInt(2)/10)+",");
					message2+=(rs.getString(4).trim());
				}
				else if(depart.equals("paediatrics")){
					message3+=((rs.getInt(2)/10)+",");
					message3+=(rs.getString(4).trim());
				}
			}
			rs.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(message);
		return (message1+"="+message2+"="+message3);
			
	}
	
}
