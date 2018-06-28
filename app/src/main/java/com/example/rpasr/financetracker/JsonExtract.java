package com.example.rpasr.financetracker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class JsonExtract {
    private JSONObject data;
    private String price;

    private String bid;
    private JSONObject mJSONObject;


    public JsonExtract(JSONObject response){

        data = response;
        try {

        JSONArray    quotes = response.getJSONArray("quotes");

        mJSONObject =   quotes.getJSONObject(0);

        bid = mJSONObject.getString("bid");







        }

        catch ( JSONException e){
            e.printStackTrace();


        }

    }

    public String getBid() {
        return bid;
    }


}
