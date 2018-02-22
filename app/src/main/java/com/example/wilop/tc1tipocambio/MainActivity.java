package com.example.wilop.tc1tipocambio;

import android.opengl.ETC1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private RadioButton Rb1 ;
    private RadioButton Rb2;
    private EditText EtC;
    private TextView RText;
    private Button Conv;
    private String Monto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Rb1 = findViewById(R.id.radioButton1);
         Rb2 = findViewById(R.id.radioButton2);
         EtC = findViewById(R.id.editText1);
         RText = findViewById(R.id.textView);
         Conv =  findViewById(R.id.button);



    }

    public void onRadioButton1Clicked(View view){
        Rb2.setChecked(false);

    }
    public void onRadioButton2Clicked(View view){
        Rb1.setChecked(false);

    }

    public void onButtonClicked(View view){
        Monto=EtC.getText().toString();
        if(Monto.isEmpty()){
            Toast.makeText (MainActivity.this,"Inserte un monto para convertir", Toast.LENGTH_SHORT).show();
            RText.setVisibility(View.INVISIBLE);
        }else{
            if(Rb1.isChecked()){
                //Do Colones to Dolars
                int iMonto =  Integer.parseInt(Monto.toString());
                double Resultado = iMonto / 572.61;
                RText.setText( String.format( " %.2f", Resultado )+" Dolares");
                RText.setVisibility(View.VISIBLE);
            }else {
                //Do Dolares to Colones
                int iMonto =  Integer.parseInt(Monto.toString());
                double Resultado = iMonto * 572.61;
                RText.setText(String.format( "%.2f", Resultado )+" Colones");
                RText.setVisibility(View.VISIBLE);
            }
        }



    }

    public void ColToDol(int Colones){
        Log.d("INFO", "ColToDol: ");
    }

    public void DolToCol(int Dolares){
        Log.d("INFO", "DolToCol: ");

    }
}
