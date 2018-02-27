package com.example.wilop.tc1tipocambio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.URL;
import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import android.content.res.XmlResourceParser;



public class MainActivity extends AppCompatActivity {

    private RadioButton Rb1 ;
    private RadioButton Rb2;
    private EditText EtC;
    private TextView RText;
    private Button Conv;
    private String Monto;
    public static final String dURL = "http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx/ObtenerIndicadoresEconomicosXML?tcIndicador=317&tcFechaInicio=23/02/2018&tcFechaFinal=23/02/2018&tcNombre=Wilson&tnSubNiveles=N";



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
                double Resultado = iMonto / 572.84;
                RText.setText( String.format( " %.2f", Resultado )+" Dolares");
                RText.setVisibility(View.VISIBLE);
            }else {
                //Do Dolares to Colones
                int iMonto =  Integer.parseInt(Monto.toString());
                double Resultado = iMonto * 566.69;
                RText.setText(String.format( "%.2f", Resultado )+" Colones");
                RText.setVisibility(View.VISIBLE);
            }
        }


    }
    private class AsyncDowload extends AsyncTask<Integer, String, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            XmlPullParser RecivedData = tryDowloadData();
            int RecordsFound = tryParsingXMLData(RecivedData);
            return RecordsFound;
        }
        private XmlPullParser tryDowloadData(){
            try {
                URL xmlURL = new URL(dURL);
                XmlPullParser RecivedData = XmlPullParserFactory.newInstance().newPullParser();
                RecivedData.setInput(xmlURL.openStream(),null);
                return RecivedData;
            }catch (XmlPullParserException e){
                Log.e("TAG","XmlPullParserFail",e);
            } catch (IOException e) {
                Log.e("TAG","XmlPullParserFail",e);
                e.printStackTrace();
            }
            return null;
        }
        private int tryParsingXMLData(XmlPullParser receivedData){
            if(receivedData != null){
                try {
                    return processRecivedData(receivedData);
                }catch (XmlPullParserException e){
                    Log.e("TAG","PullParserFail",e);
                }catch (IOException e){
                    Log.e("TAG","IOException",e);
                }
            }
            return 0;
        }
        private int processRecivedData(XmlPullParser xmlData) throws XmlPullParserException, IOException {
            int recordsFound = 0;
            int eventType = -1;
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                String tagName = xmlData.getName();
                try {
                    if (xmlData.getEventType() == XmlResourceParser.START_TAG) {
                        String s = xmlData.getName();
                        String strMessage = xmlData.getAttributeValue(null, "string");
                        Log.d("TAG", strMessage);
                    }
                }catch (Exception e){

                }
            }

            // Handle no data available: Publish an empty event.
            if (recordsFound == 0) {
                publishProgress();
                Log.i("TAG", "Finished processing "+recordsFound+" records.");
            }
            Log.i("TAG", "Finished processing "+recordsFound+" records.");
            return recordsFound;
        }
    }





}
