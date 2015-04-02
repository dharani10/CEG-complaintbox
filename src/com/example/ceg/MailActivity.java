package com.example.ceg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
 
public class  MailActivity extends Activity
{
    EditText editTextComplaintSubject,editTextComplaintType,editTextComplaints;
    ImageView buttonSend;
 
    MailDataBaseHelper launchDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
 
        // get Instance  of Database Adapter
        launchDataBaseAdapter=new  MailDataBaseHelper(this);
        launchDataBaseAdapter=launchDataBaseAdapter.open();
 
        // Get Refferences of Views
        editTextComplaintType=(EditText)findViewById(R.id.EditTextEmail);
        editTextComplaintSubject =(EditText)findViewById(R.id.EditTextName);
        
        editTextComplaints=(EditText)findViewById(R.id.EditTextFeedbackBody);
       
 
        buttonSend=(ImageView)findViewById(R.id.ButtonSendFeedback);
        buttonSend.setOnClickListener(new View.OnClickListener() {
 
        public void onClick(View v) {
            // TODO Auto-generated method stub
 
            String complaintSubject=editTextComplaintSubject.getText().toString();
            String complaintType=editTextComplaintType.getText().toString();
            String Complaint=editTextComplaints.getText().toString();
           
 
            // check if any of the fields are vaccant
            if(complaintSubject.equals("")||complaintType.equals("")||Complaint.equals(""))
            {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                   
                    
              
                  
            }
            // check if both password matches
            else
            {
            launchDataBaseAdapter.insertEntry(complaintSubject,complaintType,Complaint);
            Toast.makeText(getApplicationContext(), "sent successfully", Toast.LENGTH_LONG).show();
            }
        }
    });
        ImageView ButtonSendHome=(ImageView)findViewById(R.id.ButtonSendHome);
        ButtonSendHome.setOnClickListener(new View.OnClickListener(){
    	public void onClick(View arg1){
    		Intent nextScreen=new Intent(getApplicationContext(),PostActivity.class);
    		startActivity(nextScreen);
    	}
        });
}
    @Override
    protected void onDestroy() {
        // TODO Auto-generated +method stub
        super.onDestroy();
 
        launchDataBaseAdapter.close();
    }
}


