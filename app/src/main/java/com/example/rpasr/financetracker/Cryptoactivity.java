package com.example.rpasr.financetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import  cz.msebera.android.httpclient.Header;

public class Cryptoactivity extends AppCompatActivity {


    // UI References

    Button mbackbutton;



    // Crypto Currency
    //




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitcoin_layout);


        //Todo set up onclick listner for back button

        mbackbutton = findViewById(R.id.backButton);
        mbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tracker","the backButton has been pressed");

                Intent intent = new Intent(Cryptoactivity.this,MainActivity.class);
                finish();
                startActivity(intent);


            }



        });
        // calling the Api call


        // setting up text views



        String [] currencies = {"CNY","SEK","BRL","NZD","MXN","SGD","HKD","NOK","KRW","TRY","RUB","INR"};





        Map  <String,TextView> currencyData = new HashMap<String , TextView>();


        currencyData.put ( "EUR",(TextView)findViewById(R.id.eur));
        currencyData.put ( "GBP",(TextView)findViewById(R.id.GBP));

        currencyData.put("CHF",(TextView)findViewById(R.id.CHF));
        currencyData.put("CNY",(TextView)findViewById(R.id.CNY));
        currencyData.put("SEK",(TextView)findViewById(R.id.SEK));
        currencyData.put("BRL",(TextView)findViewById(R.id.BRL));
        currencyData.put("NZD",(TextView)findViewById(R.id.NZD));
        currencyData.put("MXN",(TextView)findViewById(R.id.MXN));
        currencyData.put("SGD",(TextView)findViewById(R.id.SGD));
        currencyData.put("HKD",(TextView)findViewById(R.id.HKD));
        currencyData.put("NOK",(TextView)findViewById(R.id.NOK));
        currencyData.put("KRW",(TextView)findViewById(R.id.KRW));
        currencyData.put("TRY",(TextView)findViewById(R.id.TRY));
        currencyData.put("RUB",(TextView)findViewById(R.id.RUB));
        currencyData.put("INR",(TextView)findViewById(R.id.INR));


        //

        //  TODO PLEASE MAKE INPUTING DATA INTO MAPS NICER  EX : ARRAY  ,












        getData(currencyData);



    }

    private void getData (Map <String, TextView> map){

        for ( String key : map.keySet()){
            netWorking(key,map.get(key));
        }

    }

    private void netWorking(String urlExtention, final TextView currency ){
        Log.d("Tracker","networking method called");
        TextView textView;
        String newApikey = "mCcC9O3ATCmrgMjq6aKanxu7";

        String temp_url = "https://web-services.oanda.com/rates/api/v2/rates/spot.json?api_key=mCcC9O3ATCmrgMjq6aKanxu7&base=USD&quote=";

        temp_url += urlExtention;


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(temp_url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response ){
                super.onSuccess(statusCode, headers, response);
                Log.d("Tracker","succses on calling Api call");
                JsonExtract info = new JsonExtract(response);
                currency.setText(info.getBid());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("Tracker","Failure to get infor");



            }
        });




    }




    }
