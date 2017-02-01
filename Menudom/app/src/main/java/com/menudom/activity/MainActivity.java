package com.menudom.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menudom.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ImageView background_image, logo_image;
    JSONParser01 jsonParser01;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    int logoLeft, logoTop, recyclerViewLeft, recyclerViewTop;
    private final float DEFAULT_SCREEN_WIDTH = 2048;
    private final float DEFAULT_SCREEN_HEIGHT = 1536;
    ArrayList<HashMap<String, String>> buttonDetails = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background_image = (ImageView) findViewById(R.id.imageView);
        logo_image = (ImageView) findViewById(R.id.imageView3);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        jsonParser01 = new JSONParser01();

        background_image.setScaleType(ImageView.ScaleType.FIT_XY);
        /*Picasso.with(getApplicationContext())
                .load("http://mrwallpaper.com/wallpapers/mickey-mouse-1024x768.jpg")
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher)
                .resize(1024, 768)
                .into(background_image);*/

        background_image.setImageResource(R.mipmap.temp_splash03);
        logo_image.setImageResource(R.mipmap.temp_logo02);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;

        float logoX = 1024;
        float logoY = 450;

        //DisplayMetrics metrics = getResources().getDisplayMetrics();
        //float densityDpi = metrics.density;

        float logoWidth = 568;
        float logoHeight = 200;

        float backgroundImageWidth = 2048;
        float backgroundImageHeight = 1536;

        float listX = 1024;
        float listY = logoY+200;

        for(int i=0; i<2;i++){

            if(i==0){

            HashMap<String, String> map = new HashMap<>();

            float widthDiff = width/DEFAULT_SCREEN_WIDTH;
            map.put("width",Float.toString((float)568 * widthDiff));
            float heightDiff = height/DEFAULT_SCREEN_HEIGHT;
            map.put("height",Float.toString((float)150 * heightDiff));
            map.put("backColor",Integer.toString(Color.parseColor("#1bbc9b")));
            map.put("textColor",Integer.toString(Color.parseColor("#ffffff")));
            map.put("text","START THE JOURNEY");
            map.put("classTag","StoryActivity");
            map.put("marginBottom",Float.toString((float)50 * heightDiff));
            map.put("textSize",Integer.toString((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density)));

            buttonDetails.add(map);
        }else if(i==1){

                HashMap<String, String> map = new HashMap<>();


                    float widthDiff = width/DEFAULT_SCREEN_WIDTH;
                    map.put("width",Float.toString((float)568 * widthDiff));

                    float heightDiff = height/DEFAULT_SCREEN_HEIGHT;
                    map.put("height",Float.toString((float)150 * heightDiff));


                map.put("backColor",Integer.toString(Color.parseColor("#ddc108")));
                map.put("textColor",Integer.toString(Color.parseColor("#ffffff")));
                map.put("text","JUMP TO MENU");
                map.put("classTag","MenuActivity");
                map.put("marginBottom",Float.toString((float)50 * heightDiff));
                map.put("textSize",Integer.toString((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density)));
                buttonDetails.add(map);

            }
        }


            float widthDiff = width/DEFAULT_SCREEN_WIDTH;

            logoWidth = logoWidth * widthDiff;
            //logoWidth = logoWidth/densityDpi;

            backgroundImageWidth = backgroundImageWidth * widthDiff;

            logoX = width * (logoX/DEFAULT_SCREEN_WIDTH);

            logoLeft = (int) (logoX - (logoWidth/(float)2));

            listX = width * (listX/DEFAULT_SCREEN_WIDTH);
            recyclerViewLeft = (int) (listX - (Float.parseFloat(buttonDetails.get(0).get("width"))/(float)2));



            float heightDiff = height/DEFAULT_SCREEN_HEIGHT;

            logoHeight = logoHeight * heightDiff;
            //logoHeight = logoHeight/densityDpi;

            backgroundImageHeight = backgroundImageHeight * heightDiff;

            logoY = height * (logoY/DEFAULT_SCREEN_HEIGHT);

            logoTop = (int) (logoY - (logoHeight/(float)2));

            listY = height * (listY/DEFAULT_SCREEN_HEIGHT);
            recyclerViewTop = (int) (listY);



        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)logoWidth, (int)logoHeight);
        lp.setMargins(logoLeft, logoTop, 0, 0);
        //logo_image.setScaleType(ImageView.ScaleType.FIT_XY);
        logo_image.setLayoutParams(lp);

        background_image.getLayoutParams().width = (int)backgroundImageWidth;
        background_image.getLayoutParams().height = (int)backgroundImageHeight;

        /*RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams((int)backgroundImageWidth, (int)backgroundImageHeight);
        lp1.setMargins(0, 0, 0, 0);
        background_image.setLayoutParams(lp1);*/

        if(buttonDetails.size()!=0){

            adapter =  new RecyclerAdapter(MainActivity.this, buttonDetails);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            RelativeLayout.LayoutParams listlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            listlp.setMargins(recyclerViewLeft, recyclerViewTop, 0, 0);
            recyclerView.setLayoutParams(listlp);
            recyclerView.setAdapter(adapter);
        }



        //new NetworkThread().execute();

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

    /*public class NetworkThread extends AsyncTask<String, String, String> {

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
    }*/

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

        private java.util.List<HashMap<String, String>> List;
        Activity activity;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public Button button;

            public MyViewHolder(View view) {
                super(view);
                button = (Button) view.findViewById(R.id.button);



            }
        }


        public RecyclerAdapter(Activity activity, java.util.List<HashMap<String, String>> List) {
            this.activity = activity;
            this.List = List;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_button_tab, parent, false);


            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.button.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
            holder.button.setWidth((int)Float.parseFloat(List.get(position).get("width")));
            holder.button.setHeight((int)Float.parseFloat(List.get(position).get("height")));
            holder.button.setBackgroundResource(R.drawable.curve_button);
            GradientDrawable drawable = (GradientDrawable) holder.button.getBackground();
            drawable.setColor((int)Float.parseFloat(List.get(position).get("backColor")));
            holder.button.setTextColor((int)Float.parseFloat(List.get(position).get("textColor")));
            holder.button.setTextSize(Float.parseFloat(List.get(position).get("textSize")));
            holder.button.setText(List.get(position).get("text"));

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)Float.parseFloat(List.get(position).get("width")),
                    (int)Float.parseFloat(List.get(position).get("height")));
            lp.setMargins(0, 0, 0, (int)Float.parseFloat(List.get(position).get("marginBottom")));
            holder.button.setLayoutParams(lp);

            holder.button.setTag(position);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int post = (int)v.getTag();
                    if(List.get(post).get("classTag").equals("StoryActivity")){

                        Intent i = new Intent(activity, WelcomeActivity.class);
                        startActivity(i);

                    }else if(List.get(post).get("classTag").equals("MenuActivity")){

                        Intent i = new Intent(activity, MenuActivity.class);
                        startActivity(i);

                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return List.size();
        }
    }
}

