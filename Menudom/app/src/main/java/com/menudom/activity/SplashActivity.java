package com.menudom.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.menudom.R;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SplashActivity extends ActionBarActivity {

    ImageView background_image, logo_image;
    JSONParser01 jsonParser01;
    int left, top;
    private final float DEFAULT_SCREEN_WIDTH = 2048;
    private final float DEFAULT_SCREEN_HEIGHT = 1536;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        background_image = (ImageView) findViewById(R.id.imageView);
        logo_image = (ImageView) findViewById(R.id.imageView2);
        jsonParser01 = new JSONParser01();


        background_image.setScaleType(ImageView.ScaleType.FIT_XY);
        background_image.setImageResource(R.mipmap.temp_splash);
        logo_image.setImageResource(R.mipmap.temp_logo);

       /* DisplayMetrics metrics = getResources().getDisplayMetrics();
        float densityDpi = metrics.density;*/

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;

        float x = 1024;
        float y = 768;

        float backgroundImageWidth = 2048;
        float backgroundImageHeight = 1536;

        float logoWidth = 596;
        float logoHeight = 596;

        float widthDiff = width/DEFAULT_SCREEN_WIDTH;

        backgroundImageWidth = backgroundImageWidth * widthDiff;

            logoWidth = logoWidth * widthDiff;

            x = width * (x/DEFAULT_SCREEN_WIDTH);

            left = (int) (x - (logoWidth/(float)2));


            float heightDiff = height/DEFAULT_SCREEN_HEIGHT;

            backgroundImageHeight = backgroundImageHeight * heightDiff;

            logoHeight = logoHeight * heightDiff;

            y = height * (y/DEFAULT_SCREEN_HEIGHT);

            top = (int) (y - (logoHeight/(float)2));

        background_image.getLayoutParams().width = (int)backgroundImageWidth;
        background_image.getLayoutParams().height = (int)backgroundImageHeight;

        logo_image.getLayoutParams().width = (int) logoWidth;
        logo_image.getLayoutParams().height = (int) logoHeight;

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)logoWidth, (int)logoHeight);
        lp.setMargins(left, top, 0, 0);
        logo_image.setLayoutParams(lp);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 4000);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class NetworkThread extends AsyncTask<String, String, String> {

        JSONObject JsonResponse;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... arg0) {

            String client_secret = "sdfdsf987213hsd";
            String device_id = "324u329047";

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

            String link = "http://api.menudom.com/api?grant_type=generate_access_token&device_id=" +
                    "324u329047&client_signature="+hexStrBuilder.toString();

            String link2="http://api.menudom.com/api?grant_type=refresh_access_token&device_id=24324324234&access_token=1480087093";

            JsonResponse = jsonParser01.getJSONFromUrl(link2);

            return null;
        }

        @Override
        protected void onPostExecute(String file_url){

            System.out.println("output - "+JsonResponse);
        }
    }
}

