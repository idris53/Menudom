package com.menudom.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.menudom.R;
import com.menudom.database.DatabaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Idris Bohra on 12/21/2016.
 */
public class LoginActivity extends Activity {

    EditText username, password;
    Button login;
    JSONParser01 jsonParser01;
    DatabaseActivity db;
    private Timer myTimer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.usernameEditText);
        password = (EditText)findViewById(R.id.passwordEditText);
        login = (Button)findViewById(R.id.loginButton);
        jsonParser01 = new JSONParser01();
        db = new DatabaseActivity(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.length()!=0 && password.length()!=0){

                    ConnectivityManager mgr =(ConnectivityManager)getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);

                    NetworkInfo info = mgr.getActiveNetworkInfo();

                    if(info!=null && info.isConnected()){

                        new GenerateTokenNetworkThread().execute();
                    }else{

                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public class GenerateTokenNetworkThread extends AsyncTask<String, String, String> {

        JSONObject JsonResponse;
        String device_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... arg0) {

            String client_secret = "sdfdsf987213hsd";
            device_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);



            String new1  = client_secret+device_id;

            MessageDigest mDigest = null;
            try {

                mDigest = MessageDigest.getInstance("SHA-256");

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            byte[] shaByteArr = mDigest.digest(new1.getBytes(Charset.forName("UTF-8")));
            StringBuilder hexStrBuilder = new StringBuilder();
            for (int i = 0; i < shaByteArr.length; i++) {
                hexStrBuilder.append(String.format("%02x", shaByteArr[i]));
            }

            String link = AppConfig.API_URL+"api?grant_type=generate_access_token&device_id="+device_id+
                    "&client_signature="+hexStrBuilder.toString();

            //System.out.println("signature - "+hexStrBuilder.toString());

            //String link2="http://api.menudom.com/api?grant_type=refresh_access_token&device_id=24324324234&access_token=1480087093";

            JsonResponse = jsonParser01.getJSONFromUrl(link);

            return null;
        }

        @Override
        protected void onPostExecute(String file_url){

            //System.out.println("output - "+JsonResponse);

            try {

                if(JsonResponse.get("status").toString().equals("200")){

                    Date date = new Date();
                    long timestamp = date.getTime();
                    db.resetOauthTables();
                    db.insertCredentials(device_id, JsonResponse.get("accessToken").toString(), timestamp);

                    myTimer = new Timer();
                    myTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            try {

                                if(db.getToken().size()!=0) {

                                    //new RefreshTokenNetworkThread().execute(db.getToken());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }, 0, 1000000);

                    ArrayList<String> list = new ArrayList<>();
                    list.add(username.getText().toString());
                    list.add(password.getText().toString());
                    list.add(device_id);
                    list.add(JsonResponse.get("accessToken").toString());

                    new LoginNetworkThread().execute(list);

                }else{

                    Toast.makeText(getApplicationContext(),JsonResponse.get("status").toString(), Toast.LENGTH_SHORT).show();
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public class RefreshTokenNetworkThread extends AsyncTask<ArrayList<String>, String, String> {

        JSONObject JsonResponse;
        String device_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(ArrayList<String>... arg0) {

            device_id = arg0[0].get(0);
            String link=AppConfig.API_URL+"api?grant_type=refresh_access_token&device_id="+arg0[0].get(0)
                    +"&access_token="+arg0[0].get(1);

            //System.out.println("link - "+link);

            JsonResponse = jsonParser01.getJSONFromUrl(link);

            return null;
        }

        @Override
        protected void onPostExecute(String file_url){


            try {

                if(JsonResponse.get("status").toString().equals("200")) {

                    //System.out.println("refresh token - "+JsonResponse.get("accessToken").toString());
                    Date date = new Date();
                    long timestamp = date.getTime();
                    db.refreshAccessToken(device_id, JsonResponse.get("accessToken").toString(), timestamp);

                } else{

                    Toast.makeText(getApplicationContext(),JsonResponse.get("status").toString(), Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class LoginNetworkThread extends AsyncTask<ArrayList<String>, String, String> {

        JSONObject JsonResponse;
        ArrayList<String> list = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(ArrayList<String>... arg0) {


            list.add(arg0[0].get(2));
            list.add(arg0[0].get(3));
            String link=AppConfig.API_URL+"api/login?username="+arg0[0].get(0)+"&password="+arg0[0].get(1)+"&device_id="+arg0[0].get(2) +
                    "&device_type=android&access_token="+arg0[0].get(3);

            JsonResponse = jsonParser01.getJSONFromUrl(link);

            return null;
        }

        @Override
        protected void onPostExecute(String file_url){

        try{
            try {

                if(JsonResponse.get("status").toString().equals("200")) {

                    Toast.makeText(getApplicationContext(), JsonResponse.get("message").toString(), Toast.LENGTH_SHORT).show();

                    db.resetCustomerTables();

                    JSONObject JsonResponseData = JsonResponse.getJSONObject("data");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("customer_id", JsonResponseData.get("customer_id").toString());
                    map.put("customer_name", JsonResponseData.get("customer_name").toString());
                    map.put("customer_email", JsonResponseData.get("customer_email").toString());
                    map.put("restaurant_name", JsonResponseData.get("restaurant_name").toString());
                    map.put("status", "active");
                    db.insertCustomer(map);

                    list.add(JsonResponseData.get("customer_id").toString());

                    new LayoutsTemplatesNetworkThread().execute(list);

                    //Intent i = new Intent(getApplicationContext(), SplashActivity.class);
                    //startActivity(i);

                } else{

                    Toast.makeText(getApplicationContext(),JsonResponse.get("message").toString(), Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

    public class LayoutsTemplatesNetworkThread extends AsyncTask<ArrayList<String>, String, String> {

        JSONObject JsonResponse;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(ArrayList<String>... arg0) {


            String link =AppConfig.API_URL+"api/get_templates_and_layouts?access_token="+arg0[0].get(1)+
                    "&device_id="+arg0[0].get(0)+"&customer_id="+arg0[0].get(2);

            //System.out.println("link - "+link);

            JsonResponse = jsonParser01.getJSONFromUrl(link);

            return null;
        }

        @Override
        protected void onPostExecute(String file_url){

            try{
                try {

                    if(JsonResponse.get("status").toString().equals("200")) {

                        db.resetTemplateTables();
                        db.resetLayoutTables();
                        JSONArray JsonArr = JsonResponse.getJSONArray("data");

                        for (int k = 0; k < JsonArr.length(); k++) {

                            JSONObject objTemplate = (JSONObject) JsonArr.get(k);
                            HashMap<String, String> map = new HashMap<>();
                            map.put("Id",objTemplate.get("id").toString());
                            map.put("name",objTemplate.get("name").toString());
                            map.put("display_name",objTemplate.get("display_name").toString());
                            map.put("description",objTemplate.get("description").toString());
                            map.put("status",objTemplate.get("status").toString());
                            db.insertTemplate(map);

                            //if(objTemplate.get("status")){

                            JSONArray JsonArrLayout = objTemplate.getJSONArray("layout");

                            for (int m = 0; m < JsonArrLayout.length(); m++) {

                                JSONObject objLayout = (JSONObject) JsonArrLayout.get(m);
                                HashMap<String, String> map2 = new HashMap<>();
                                map2.put("Id",objLayout.get("id").toString());
                                map2.put("name",objLayout.get("name").toString());
                                map2.put("table_name",objLayout.get("table_name").toString());
                                map2.put("description",objLayout.get("description").toString());
                                map2.put("template_id",objLayout.get("template_id").toString());
                                map2.put("created_on",objLayout.get("created_on").toString());
                                map2.put("status",objLayout.get("status").toString());
                                db.insertLayout(map2);
                            }
                        //}

                        }


                    } else{

                        Toast.makeText(getApplicationContext(),JsonResponse.get("message").toString(), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
