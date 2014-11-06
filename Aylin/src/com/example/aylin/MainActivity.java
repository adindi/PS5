package com.example.aylin;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.text.Editable;
public class MainActivity extends Activity implements OnItemSelectedListener{
	
	EditText INV;
	EditText RATE;
	TextView FV;
	Spinner YRS;
	Object TOTALYRS;
	Button BUTTON;
	Integer[] NUMS = new Integer [24];
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		INV = (EditText) findViewById(R.id.editText2);
		RATE = (EditText) findViewById(R.id.editText1);
		FV = (TextView) findViewById(R.id.textView5);
		
		INV.addTextChangedListener(
				new TextWatcher(){
					@Override
					public void afterTextChanged(Editable editable) {FV.setText(" "); }
					@Override
					public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3){}
					@Override
					public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
					});
					RATE.addTextChangedListener(
							new TextWatcher(){
							@Override
							public void afterTextChanged(Editable editable) {
								FV.setText(" ");
							}
							@Override
							 public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
				            @Override
				            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
				        });
					 	
						int i=0;
						while (i<24){
				            NUMS[i] = i + 1;
				            i=+1;
							}
						ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, NUMS);
				        YRS = (Spinner) findViewById(R.id.spinner1);
				        YRS.setAdapter(adapter);
				        YRS.setOnItemSelectedListener(this);
				        Button button1 = (Button)findViewById(R.id.button1);
				        button1.setOnClickListener(new View.OnClickListener(){			        
	@Override
	public void onClick(View view) {
		   double init;
	        double rate;
	        Editable initial = INV.getText();
	        Editable rt = RATE.getText();

	        if (initial.length() != 0 && rt.length() != 0) {

	            init = Double.parseDouble(initial.toString());
	            rate = Double.parseDouble(rt.toString());

	            // Main formula
	            double result = (double) (init * Math.pow((1 + rate / 100), (Integer) TOTALYRS));
	            result = Math.round(result);

	            // Final value
	            FV.setText("The Future Value is $" + ((Double) result).toString());

	            // For debug
	            System.out.println("Result:" + result);
	        }
	    }
	});}

	 public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        int position = YRS.getSelectedItemPosition();
	        TOTALYRS = YRS.getItemAtPosition(position);
	        FV.setText(" "); 
	    }

	    public void onNothingSelected(AdapterView<?> arg0) {


	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	}
