package com.example.ceg;





import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
 
public class MailDataBaseHelper 
{
        static final String DATABASE_NAME = "fee.db";
        static final int DATABASE_VERSION = 1;
        public static final int NAME_COLUMN = 1;
        // TODO: Create public field for each column in your table.
        // SQL Statement to create a new database.
        static final String DATABASE_CREATE = "create table "+"FEE"+
                                     "( " +"ID"+" integer primary key autoincrement,"+ "NAME text,EMAIL text,FEEDBACK text); ";
        // Variable to hold the database instance
        public  SQLiteDatabase db1;
        // Context of the application using the database.
        private final Context context;
        // Database open/upgrade helper
        private DataBaseHelper dbHelper;
        public  MailDataBaseHelper(Context _context) 
        {
            context = _context;
            dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public  MailDataBaseHelper open() throws SQLException 
        {
            db1 = dbHelper.getWritableDatabase();
            return this;
        }
        public void close() 
        {
            db1.close();
        }
 
        public  SQLiteDatabase getDatabaseInstance()
        {
            return db1;
        }
 
        public void insertEntry(String complaintType,String complaintSubject,String complaint)
        {
           ContentValues newValues = new ContentValues();
            // Assign values for each row.
            newValues.put("NAME", complaintType);
            newValues.put("EMAIL",complaintSubject);
			 newValues.put("FEEDBACK",complaint);
 
            // Insert the row into your table
            db1.insert("FEE", null, newValues);
            ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
        }
        public int deleteEntry(String complaintSubject)
        {
            //String id=String.valueOf(ID);
            String where="SUBJECT=?";
            int numberOFEntriesDeleted= db1.delete("FEE", where, new String[]{complaintSubject}) ;
           // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
            return numberOFEntriesDeleted;
        }    
        public String getSinlgeEntry(String complaintSubject)
        {
            Cursor cursor=db1.query("FEE", null, " SUBJECT=?", new String[]{complaintSubject}, null, null, null);
            if(cursor.getCount()<1) // UserName Not Exist
            {
                cursor.close();
                return "NOT EXIST";
            }
            cursor.moveToFirst();
            String complaintType = cursor.getString(cursor.getColumnIndex("NAME"));
			 String complaint = cursor.getString(cursor.getColumnIndex("EMAIL"));
            cursor.close();
            return complaintType;                
        }
        public void  updateEntry(String complaintType,String complaintSubject, String complaint)
        {
            // Define the updated row content.
            ContentValues updatedValues = new ContentValues();
            // Assign values for each row.
            updatedValues.put("NAME", complaintType);
            updatedValues.put("EMAIL",complaintSubject);
			updatedValues.put("FEEDBACK",complaint);
 
            String where="SUBJECT = ?";
            db1.update("FEE",updatedValues, where, new String[]{complaintSubject});               
        }        
}
