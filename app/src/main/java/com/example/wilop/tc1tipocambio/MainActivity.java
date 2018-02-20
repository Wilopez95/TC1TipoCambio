package com.example.wilop.tc1tipocambio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;




public class MainActivity extends AppCompatActivity {

    private RadioButton Rb1 ;
    private RadioButton Rb2;
    private EditText EtC;
    private EditText EtD ;
    private Button Conv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Rb1 = findViewById(R.id.radioButton1);
         Rb2 = findViewById(R.id.radioButton2);
         EtC = findViewById(R.id.editText1);
         EtD = findViewById(R.id.editText2);
         Conv =  findViewById(R.id.button);

    }



    public void onButtonClicked(View view){


    }

    public void ColToDol(int Colones){
        Log.d("INFO", "ColToDol: ");
    }

    public void DolToCol(int Dolares){
        Log.d("INFO", "DolToCol: ");

    }
}
