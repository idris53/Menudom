package com.menudom.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menudom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Idris Bohra on 12/11/2016.
 */
public class SpecializationActivity extends Activity {

    ImageView background, logo_image;
    TextView label1;
    View view, view2;
    RecyclerView recyclerView;
    Button specializationButton, buttonMenu;
    float widthDiff, heightDiff;
    ArrayList<HashMap<String, String>> categories = new ArrayList<>();
    private final float DEFAULT_SCREEN_WIDTH = 2048;
    private final float DEFAULT_SCREEN_HEIGHT = 1536;
    private final float DEFAULT_LOGO_X = 1024;
    private final float DEFAULT_LOGO_Y = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization);

        background = (ImageView) findViewById(R.id.imageView6);
        logo_image = (ImageView) findViewById(R.id.imageView7);
        label1 = (TextView) findViewById(R.id.textView3);
        view = (View) findViewById(R.id.view2);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        specializationButton = (Button) findViewById(R.id.button5);
        buttonMenu = (Button) findViewById(R.id.button6);

        //to calculate screen width and height
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;

        //screen difference calculation logic
        widthDiff = width/DEFAULT_SCREEN_WIDTH;
        heightDiff = height/DEFAULT_SCREEN_HEIGHT;

        for(int i=0;i<4;i++){
            if(i==0){
            HashMap<String, String> map = new HashMap<>();
            map.put("icon",String.valueOf(R.mipmap.smart_object01));
            map.put("label1","Homemade\nCakes");
            categories.add(map);
        } else if(i==1){
                HashMap<String, String> map = new HashMap<>();
                map.put("icon",String.valueOf(R.mipmap.smart_object02));
                map.put("label1","Organic Healthy\nTeas");
                categories.add(map);
            }else if(i==2){
                HashMap<String, String> map = new HashMap<>();
                map.put("icon",String.valueOf(R.mipmap.smart_object03));
                map.put("label1","Delicious\nSandwiches");
                categories.add(map);
            }else if(i==3){
                HashMap<String, String> map = new HashMap<>();
                map.put("icon",String.valueOf(R.mipmap.smart_object04));
                map.put("label1","Fresh\nJuices");
                categories.add(map);
            }
        }

        float backgroundWidth = DEFAULT_SCREEN_WIDTH;
        float backgroundHeight = DEFAULT_SCREEN_HEIGHT;
        backgroundWidth = backgroundWidth * widthDiff;
        backgroundHeight = backgroundHeight * heightDiff;
        background.setImageResource(R.mipmap.temp_ourstory);
        background.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams((int)backgroundWidth, (int)backgroundHeight);
        background.setLayoutParams(lp0);

        float logoWidth = 565;
        float logoHeight = 200;
        logoWidth = logoWidth * widthDiff;
        logoHeight = logoHeight * heightDiff;
        logo_image.setImageResource(R.mipmap.temp_logo02);
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams((int)logoWidth, (int)logoHeight);
        int NEW_LOGO_X = (int) ((width * (DEFAULT_LOGO_X/DEFAULT_SCREEN_WIDTH)) - (logoWidth/(float)2));
        int NEW_LOGO_Y = (int) ((height * (DEFAULT_LOGO_Y/DEFAULT_SCREEN_HEIGHT)) - (logoHeight/(float)2) );
        lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp1.setMargins(NEW_LOGO_X, NEW_LOGO_Y, 0, 0);
        logo_image.setLayoutParams(lp1);

        label1.setText("We are Specialized in");
        label1.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));
        label1.setTextSize((int) (getResources().getDimension(R.dimen.specializationpage_label3) / getResources().getDisplayMetrics().density));
        label1.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.BELOW, logo_image.getId());
        lp2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //lp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp2.setMargins(NEW_LOGO_X, (int)(40 *heightDiff), 0, 0);
        label1.setLayoutParams(lp2);

        view.setBackgroundColor(Color.parseColor("#ddc108"));
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(4 * heightDiff));
        lp3.addRule(RelativeLayout.BELOW, label1.getId());
        lp3.addRule(RelativeLayout.ALIGN_RIGHT, label1.getId());
        lp3.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp3.setMargins(0,(int)(20 *heightDiff),0,0);
        view.setLayoutParams(lp3);

        //RecyclerView recyclerView = new RecyclerView(getApplicationContext());
        //recyclerView.setBackgroundColor(Color.parseColor("#000000"));
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp4.addRule(RelativeLayout.BELOW, view.getId());
        lp4.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        //lp4.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //System.out.println("categories - "+categories);
        RecyclerAdapter adapter =  new RecyclerAdapter(getApplicationContext(), categories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutParams(lp4);

        specializationButton.setText("BACK");
        specializationButton.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        specializationButton.setTextColor(Color.parseColor("#ffffff"));
        specializationButton.setBackgroundResource(R.drawable.curve_button02);
        Drawable img = getApplicationContext().getResources().getDrawable( R.mipmap.leftback );
        img.setBounds( 0, 0, (int) (65 * widthDiff), (int) (50 * heightDiff) );
        specializationButton.setCompoundDrawables(img, null, null, null);
        specializationButton.setPadding((int) (40 * widthDiff), 0, (int) (40 * widthDiff), 0);
        specializationButton.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams((int)(400 * widthDiff), (int)(150 * heightDiff));
        lp5.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp5.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp5.setMargins((int)(40 *widthDiff), 0, 0, (int)(40 *heightDiff));
        specializationButton.setLayoutParams(lp5);

        buttonMenu.setText("MENU");
        buttonMenu.setTextColor(Color.parseColor("#ffffff"));
        buttonMenu.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonMenu.setBackgroundResource(R.drawable.curve_button);
        buttonMenu.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        GradientDrawable drawable = (GradientDrawable) buttonMenu.getBackground();
        drawable.setColor(Color.parseColor("#ddc108"));
        //buttonMenu.setPadding(100,0,100,0);
        RelativeLayout.LayoutParams lp7 = new RelativeLayout.LayoutParams((int)(900 * widthDiff), (int)(150 * heightDiff));
        lp7.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp7.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp7.setMargins(0,0,(int)(40 *widthDiff),(int)(40 *heightDiff));
        buttonMenu.setLayoutParams(lp7);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i= new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });

        specializationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i= new Intent(getApplicationContext(), PlaceActivity.class);
                startActivity(i);
            }
        });

    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

        private ArrayList<HashMap<String, String>> List;
        Context activity;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView icon;
            TextView label1;

            public MyViewHolder(View view) {
                super(view);
                icon = (ImageView) view.findViewById(R.id.imageView8);
                label1 = (TextView) view.findViewById(R.id.textView4);
            }
        }


        public RecyclerAdapter(Context activity, ArrayList<HashMap<String, String>> List) {
            this.activity = activity;
            this.List = List;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.specialization_list_tab, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //Movie movie = moviesList.get(position);
            int lastPosition = -1;


            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)(330 * widthDiff), (int)(250 * heightDiff));
            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            lp.setMargins((int)(50* widthDiff),(int)(80* heightDiff),(int)(50* widthDiff),0);
            /*Picasso.with(activity)
                    .load(List.get(position).get("icon"))
                    .placeholder(R.mipmap.ic_launcher) // optional
                    .error(R.mipmap.ic_launcher)         // optional
                    .into(holder.icon);*/
            holder.icon.setPadding((int)(40* widthDiff), (int)(40* heightDiff), (int)(40* widthDiff), (int)(40* heightDiff));
            holder.icon.setImageResource(Integer.parseInt(List.get(position).get("icon")));
            holder.icon.setLayoutParams(lp);


            holder.label1.setText(List.get(position).get("label1"));
            holder.label1.setTextColor(Color.parseColor("#ffffff"));
            holder.label1.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
            holder.label1.setTextSize((int) (getResources().getDimension(R.dimen.specializationpage_label4) / getResources().getDisplayMetrics().density));
            RelativeLayout.LayoutParams lp01 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp01.addRule(RelativeLayout.BELOW, holder.icon.getId());
            lp01.addRule(RelativeLayout.ALIGN_LEFT, holder.icon.getId());
            lp01.addRule(RelativeLayout.ALIGN_RIGHT, holder.icon.getId());
            //lp01.setMargins((int)(-50* widthDiff),0,(int)(-50* widthDiff),0);
            lp01.setMargins(0,0,0,0);
            holder.label1.setLayoutParams(lp01);


    	/*Animation animation = AnimationUtils.loadAnimation(activity,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;*/
        }

        @Override
        public int getItemCount() {
            return List.size();
        }
    }

}
