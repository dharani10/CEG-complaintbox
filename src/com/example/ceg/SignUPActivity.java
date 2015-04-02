package com.example.ceg;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
 
public class SignUPActivity extends Activity
{
    EditText editTextUserName,editTextPassword,editTextConfirmPassword,editTextCollegeId;
    ImageView btnCreateAccount;
    ImageView buttonLogin;
 
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
 
        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
 
       
        
        // Get Refferences of Views
        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
        editTextCollegeId=(EditText)findViewById(R.id.editTextCollegeId);
 
        btnCreateAccount=(ImageView)findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
 
        public void onClick(View v) {
            // TODO Auto-generated method stub
        	
        	
                /// Create Intent for SignUpActivity  abd Start The Activity
              

 
            String userName=editTextUserName.getText().toString();
            String password=editTextPassword.getText().toString();
            String confirmPassword=editTextConfirmPassword.getText().toString();
            String collegeId=editTextCollegeId.getText().toString();
            
        
 
            // check if any of the fields are vaccant
            if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
            {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
            }
            
            
                if(collegeId.length()<10 || collegeId.length()>10 )
                {
                	 Toast.makeText(getApplicationContext(), "Invalid CollegeID", Toast.LENGTH_LONG).show();
                     return;
                	
                 
                }
               
            
            
            // check if both password matches
            if(!password.equals(confirmPassword))
            {
                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                // Save the Data in Database
                loginDataBaseAdapter.insertEntry(userName, password, collegeId);
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
               
            }
            
            
            
        }
    });
        buttonLogin=(ImageView)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
    	public void onClick(View arg1){
    		Intent nextScreen=new Intent(getApplicationContext(),HomeActivity.class);
    		startActivity(nextScreen);
    	}
        });
}
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
 
        loginDataBaseAdapter.close();
    }
}
