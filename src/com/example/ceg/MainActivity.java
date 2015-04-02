package com.example.ceg;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 *
 * @author manish
 *
 */
public class MainActivity extends Activity  {


	private Spinner recipient;
	private EditText subject;
	private EditText body;
	 ImageView sendBtn;
	 ImageView buttonHome;
       @Override
       protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.dp);
              recipient = (Spinner) findViewById(R.id.spinner1);
              subject = (EditText) findViewById(R.id.editTextSubject);
              body = (EditText) findViewById(R.id.editTextMessage);
              
              sendBtn = (ImageView) findViewById(R.id.buttonSend);
              sendBtn.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View view) {
                	 sendEmail();
                	 // after sending the email, clear the fields
                	 //recipient.setText("");
                	 subject.setText("");
                	 body.setText("");
                 }
           });
               buttonHome=(ImageView)findViewById(R.id.ButtonSendHome);
              buttonHome.setOnClickListener(new View.OnClickListener(){
          	public void onClick(View arg1){
          		Intent nextScreen=new Intent(getApplicationContext(),PostActivity.class);
          		startActivity(nextScreen);
          	}
              });
        }


           
           protected void sendEmail() {

        	   
              String recipients = String.valueOf(recipient.getSelectedItem());
              Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
              // prompts email clients only
              email.setType("message/rfc822");

              email.putExtra(Intent.EXTRA_EMAIL, recipients);
              email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
              email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

              try {
        	    // the user can choose the email client
                 startActivity(Intent.createChooser(email, "Choose an email client from..."));
             
              } catch (android.content.ActivityNotFoundException ex) {
                 Toast.makeText(MainActivity.this, "No email client installed.",
                		 Toast.LENGTH_LONG).show();
              }
           }
           
        }
