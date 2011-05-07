package org.skubi.przelicznik;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;

import java.lang.String;
import java.util.regex.Pattern;

public class mainacy extends Activity {
	private Button przelicz;
	private EditText predkosc;
	private EditText wielkosc;
	private EditText wynik;
	private RadioButton rb2;
	private RadioButton rb4;
	
	private OnClickListener przeliczlistener = new OnClickListener() {
        @Override
        public void onClick(View arg0) 
        {
        	Double pred,wiel,predb,czas,godz,min;
        	String gs,ms;
        	
    		if (predkosc.getText().toString().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+") && wielkosc.getText().toString().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) 
    		{
        	
        	pred = Double.valueOf(predkosc.getText().toString());
        	wiel = Double.valueOf(wielkosc.getText().toString());
        	
        	if (rb2.isChecked()==true)
        	{
        		pred = pred *1024;
        	};
        	
        	if (rb4.isChecked()==true)
        	{
        		wiel = wiel *1024;
        	};
        	
        	pred = pred / 8;
        	predb = 1024 / pred ;
        	czas = wiel * predb;
        	min = czas / 60;
        	godz = czas / 3600;
        	
        	String godzs = new String(godz.toString());
        	String[] cg;
        	cg=godzs.split("\\.");
        	Integer cgi = Integer.valueOf(cg[0]);
        	
        	min = min - (cgi * 60);
        	
        	String mins = new String(min.toString());
        	String[] cm;
        	cm=mins.split("\\.");
        	Integer cmi = Integer.valueOf(cm[0]);
        	
        	gs = getString(R.string.godzina);
        	ms = getString(R.string.minuta);
        	
        	wynik.setText(cgi.toString() + " " + gs + " " + cmi + " " + ms);
        	}
        }
	};
	
	private class InputValidator implements TextWatcher {
	        private EditText et;

	        private InputValidator(EditText editText) {
	            this.et = editText;
	        }

	        @Override
	        public void afterTextChanged(Editable s) {

	        }

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,int after) {

	        }

	        @Override
	        public void onTextChanged(CharSequence s, int start, int before,int count) 
	        {
	            if (s.length() != 0) 
	            {
	                switch (et.getId()) 
	                {
	                case R.id.EditText01: 
	                {
	                    if (!Pattern.matches("^\\d{1,16}$", s)) 
	                    {
	                        et.setError(getString(R.string.blad));
	                    }
	                }
	                    break;

	                case R.id.EditText02: 
	                {
	                    if (!Pattern.matches("^\\d{1,16}$", s)) 
	                    {
	                        et.setError(getString(R.string.blad));
	                    }
	                }
	                    break;
	                }
	            }
	        }
	    }

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        przelicz  = (Button)findViewById(R.id.Button01);
        predkosc  = (EditText)findViewById(R.id.EditText01);
        wielkosc  = (EditText)findViewById(R.id.EditText02);
        wynik  = (EditText)findViewById(R.id.EditText03);
        rb2  = (RadioButton)findViewById(R.id.RadioButton02);
        rb4  = (RadioButton)findViewById(R.id.RadioButton04);
        przelicz.setOnClickListener(przeliczlistener);
        
        predkosc.addTextChangedListener(new InputValidator(predkosc));
        wielkosc.addTextChangedListener(new InputValidator(wielkosc));
        
    }
}