package com.example.androidclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	static public boolean connect=false;
	static String s;
	private Button btn;
	static public TextView notice;
	private EditText name;
	private EditText sex;
	private EditText age;
	private EditText department;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		notice=(TextView)this.findViewById(R.id.textView1);
		btn=(Button)this.findViewById(R.id.button1);
		name=(EditText)this.findViewById(R.id.editText7);
		sex =(EditText)this.findViewById(R.id.editText5);
		age =(EditText)this.findViewById(R.id.editText6);
		department=(EditText)this.findViewById(R.id.editText8);
		
		//new Thread(){
		//	public void run() {
				new ClientAndroid().start();
		//	};	
		//}.start();
	   
		   
		btn.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				String info="predict,";
				//String depart=department.getText().toString();
				String sname=name.getText().toString();
				String ssex=sex.getText().toString();
				String sage=age.getText().toString();
				String sdepart=department.getText().toString();
//				if(sname.equals("")||ssex.equals("")||sage.equals("")){
//					return;
//				}
					
				if(sdepart.equals("surgery")||sdepart.equals("外科")){
					s=sname+","+ssex+","+sage+",surgery";
					//new Thread(){
					//	public void run() {
							ClientAndroid.sendInfo(MainActivity.s);
						//};
				//	}.start();
					//notice.setText("预约成功");
				Toast.makeText(MainActivity.this, "预约成功", Toast.LENGTH_LONG).show();
				}
				else if(sdepart.equals("internal")||sdepart.equals("内科")){
					s=sname+","+ssex+","+sage+",internal";
					ClientAndroid.sendInfo(MainActivity.s);
					Toast.makeText(MainActivity.this, "预约成功", Toast.LENGTH_LONG).show();
				}
				else if(sdepart.equals("paediatrics")||sdepart.equals("儿科")){
					s=sname+","+ssex+","+sage+",paediatrics";
					ClientAndroid.sendInfo(MainActivity.s);
					Toast.makeText(MainActivity.this, "预约成功", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		menu.add(Menu.NONE,  Menu.FIRST+1 , 0, "退出"); 
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		
		switch(item.getItemId()){
		case 2: System.exit(0);
		//case 2:// Toast.makeText(this, "123hao", Toast.LENGTH_LONG);
		}
		return false;
		
		//return super.onOptionsItemSelected(item);
	}
	
	
	
}
