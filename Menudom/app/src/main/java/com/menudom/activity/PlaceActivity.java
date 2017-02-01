package com.menudom.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.menudom.R;
import com.menudom.adapter.ImageSlideAdapter;

import java.util.ArrayList;

/**
 * Created by Idris Bohra on 12/11/2016.
 */
public class PlaceActivity extends Activity {

    float widthDiff, heightDiff;
    private final float DEFAULT_SCREEN_WIDTH = 2048;
    private final float DEFAULT_SCREEN_HEIGHT = 1536;
    private final float DEFAULT_LOGO_X = 40;
    private final float DEFAULT_LOGO_Y = 50;
    RelativeLayout relativeLayout;

    private ViewPager mViewPager;
    //TextView imgNameTxt;
    //PageIndicator mIndicator;
    ArrayList<String> products;
    private Runnable animateViewPager;
    private Handler handler;
    private static final long ANIM_VIEWPAGER_DELAY = 5000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        //relativelayout to contain product_image1, product_image2, logo, label1, view, label2, GO BACK button, THE PLACE button, JUMP TO MENU button
        relativeLayout = (RelativeLayout)findViewById(R.id.rl4);

        //to calculate screen width and height
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;

        //screen difference calculation logic
        widthDiff = width/DEFAULT_SCREEN_WIDTH;
        heightDiff = height/DEFAULT_SCREEN_HEIGHT;

        //for background image
        float backgroundWidth = DEFAULT_SCREEN_WIDTH;
        float backgroundHeight = DEFAULT_SCREEN_HEIGHT;
        ImageView background_image = new ImageView(getApplicationContext());
        background_image.setId(View.generateViewId());
        background_image.setImageResource(R.mipmap.temp_splash03);
        background_image.setScaleType(ImageView.ScaleType.FIT_XY);
        backgroundWidth = backgroundWidth * widthDiff;
        backgroundHeight = backgroundHeight * heightDiff;
        //background_image.getLayoutParams().width = (int)backgroundWidth;
        //background_image.getLayoutParams().height = (int)backgroundHeight;
        RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams((int)backgroundWidth, (int)backgroundHeight);
        //lp0.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        background_image.setLayoutParams(lp0);
        relativeLayout.addView(background_image);

		//for product image
        float productImageWidth = 1124;
        float productImageHeight = 645;
        productImageWidth = productImageWidth * widthDiff;
        productImageHeight = productImageHeight * heightDiff;
        products = new ArrayList<String>();
        products.add("drawable://"+ R.mipmap.temp_one_image);
        //products.add("drawable://"+ R.mipmap.temp_product02);
        //products.add("drawable://"+ R.mipmap.temp_product03);
        mViewPager = new ViewPager(getApplicationContext());
        mViewPager.setId(View.generateViewId());
        mViewPager.setAdapter(new ImageSlideAdapter(getApplicationContext(), products));
        RelativeLayout.LayoutParams lp9 = new RelativeLayout.LayoutParams((int)productImageWidth, (int)productImageHeight);
        lp9.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp9.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mViewPager.setLayoutParams(lp9);
        relativeLayout.addView(mViewPager);

        float logoWidth = 473;
        float logoHeight = 200;
        ImageView logo_image = new ImageView(getApplicationContext());
        logo_image.setId(View.generateViewId());
        logo_image.setScaleType(ImageView.ScaleType.FIT_XY);
        logo_image.setImageResource(R.mipmap.temp_logo01);
        logoWidth = logoWidth * widthDiff;
        logoHeight = logoHeight * heightDiff;
        int NEW_LOGO_X = (int) ((width * (DEFAULT_LOGO_X/DEFAULT_SCREEN_WIDTH)));
        int NEW_LOGO_Y = (int) ((height * (DEFAULT_LOGO_Y/DEFAULT_SCREEN_HEIGHT)));
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams((int)logoWidth, (int)logoHeight);
        lp1.setMargins(NEW_LOGO_X, NEW_LOGO_Y, 0, 0);
        lp1.addRule(RelativeLayout.ALIGN_LEFT, background_image.getId());
        //lp1.addRule(RelativeLayout.ALIGN_RIGHT, mViewPager.getId());
        logo_image.setLayoutParams(lp1);
        relativeLayout.addView(logo_image);

        //Our story label
        TextView label1 = new TextView(getApplicationContext());
        label1.setId(View.generateViewId());
        label1.setText("The Place");
        label1.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));
        label1.setTextSize((int) (getResources().getDimension(R.dimen.storypage_label1) / getResources().getDisplayMetrics().density));
        label1.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_LEFT, logo_image.getId());
        lp2.addRule(RelativeLayout.ALIGN_RIGHT, mViewPager.getId());
        lp2.addRule(RelativeLayout.BELOW, logo_image.getId());
        lp2.setMargins(0,(int)(40 * heightDiff),0,0);
        label1.setLayoutParams(lp2);
        relativeLayout.addView(label1);

        //view
        View view1 = new View(getApplicationContext());
        view1.setId(View.generateViewId());
        view1.setBackgroundColor(Color.parseColor("#ddc108"));
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(4 * heightDiff));
        lp3.addRule(RelativeLayout.BELOW, label1.getId());
        lp3.addRule(RelativeLayout.LEFT_OF, mViewPager.getId());
        lp3.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp3.setMargins(0, (int)(20 * heightDiff), (int)(50 * heightDiff), 0);
        view1.setLayoutParams(lp3);
        relativeLayout.addView(view1);

        //label3 textview
        TextView label3 = new TextView(getApplicationContext());
        label3.setId(View.generateViewId());
        label3.setText("Your Nearby Haven");
        label3.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        label3.setTextSize((int) (getResources().getDimension(R.dimen.placepage_label2) / getResources().getDisplayMetrics().density));
        label3.setTypeface(null, Typeface.BOLD);
        label3.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams lp8 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp8.addRule(RelativeLayout.ALIGN_LEFT, logo_image.getId());
        lp8.addRule(RelativeLayout.ALIGN_RIGHT, mViewPager.getId());

        lp8.addRule(RelativeLayout.BELOW, view1.getId());
        lp8.setMargins(0, (int)(40* heightDiff),0,0);
        label3.setLayoutParams(lp8);
        relativeLayout.addView(label3);

        Button buttonBack = new Button(getApplicationContext());
        buttonBack.setId(View.generateViewId());
        buttonBack.setText("BACK");
        buttonBack.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonBack.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        buttonBack.setBackgroundResource(R.drawable.curve_button02);
        Drawable img = getApplicationContext().getResources().getDrawable( R.mipmap.leftback );
        img.setBounds( 0, 0, (int) (65 * widthDiff), (int) (50 * heightDiff) );
        buttonBack.setCompoundDrawables(img, null, null, null);
        buttonBack.setPadding((int) (40 * widthDiff), 0,(int) (40 * widthDiff),0);
        //buttonBack.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.leftback, 0, 0, 0);
        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams((int)(332 * widthDiff), (int)(150 * heightDiff));
        lp5.addRule(RelativeLayout.ALIGN_BOTTOM, background_image.getId());
        lp5.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp5.setMargins(0, 0, 0, (int) (40 * heightDiff));
        buttonBack.setLayoutParams(lp5);
        relativeLayout.addView(buttonBack);

        //JUMP TO MENU button
        Button buttonMenu = new Button(getApplicationContext());
        buttonMenu.setId(View.generateViewId());
        buttonMenu.setText("JUMP TO MENU");
        buttonMenu.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonMenu.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        buttonMenu.setBackgroundResource(R.drawable.curve_button);
        GradientDrawable drawable = (GradientDrawable) buttonMenu.getBackground();
        drawable.setColor(Color.parseColor("#ddc108"));
        RelativeLayout.LayoutParams lp6 = new RelativeLayout.LayoutParams((int)(712 * widthDiff), (int)(150 * heightDiff));
        lp6.addRule(RelativeLayout.ALIGN_BOTTOM, background_image.getId());
        lp6.addRule(RelativeLayout.RIGHT_OF, buttonBack.getId());
        //buttonMenu.setPadding(100, 0,100,0);
        lp6.setMargins((int)(40* widthDiff),0,0,(int)(40* heightDiff));
        buttonMenu.setLayoutParams(lp6);
        relativeLayout.addView(buttonMenu);

        //OUR SPECIALIZATION button
        Button buttonSpecialization = new Button(getApplicationContext());
        buttonSpecialization.setId(View.generateViewId());
        buttonSpecialization.setText("OUR SPECIALIZATION");
        buttonSpecialization.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonSpecialization.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        //buttonBack.setPadding(40, 15, 40, 15);
        buttonSpecialization.setBackgroundResource(R.drawable.curve_button02);
        RelativeLayout.LayoutParams lp7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(150 * heightDiff));
        lp7.addRule(RelativeLayout.ALIGN_BOTTOM, background_image.getId());
        lp7.addRule(RelativeLayout.RIGHT_OF, buttonMenu.getId());
        lp7.addRule(RelativeLayout.ALIGN_RIGHT, background_image.getId());
        //buttonSpecialization.setPadding(50, 0,50,0);
        lp7.setMargins((int)(40* widthDiff),0,(int)(40* widthDiff),(int)(40* heightDiff));
        buttonSpecialization.setLayoutParams(lp7);
        relativeLayout.addView(buttonSpecialization);

        //label2 scrollview
        ScrollView scrollView = new ScrollView(getApplicationContext());
        scrollView.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp4.addRule(RelativeLayout.BELOW, mViewPager.getId());
        lp4.addRule(RelativeLayout.ABOVE, buttonMenu.getId());
        lp4.addRule(RelativeLayout.ALIGN_RIGHT, background_image.getId());
        lp4.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp4.setMargins(0, (int)(40* heightDiff), (int)(70* widthDiff), (int)(20* heightDiff));
        scrollView.setLayoutParams(lp4);
        relativeLayout.addView(scrollView);

        //label2 Relative layout
        RelativeLayout relativeLayout1 = new RelativeLayout(getApplicationContext());
        relativeLayout1.setId(View.generateViewId());
        scrollView.addView(relativeLayout1);

        //label2 textview
        TextView label2 = new TextView(getApplicationContext());
        label2.setId(View.generateViewId());
        label2.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        label2.setText("Located in the beautiful Seef Area, Organika is your nearby haven to indulge into " +
                "complete calmness and serenity.\n\nSurrounded by our original teapots collections, fresh teas " +
                "and listening peaceful music tracks. You will enjoy a peaceful time reading your favorite book," +
                " relaxing from a long day or talking to a friend.\n" +
                "\nAnd if you have a gathering you would like to arrange," +
                " Organika would love to offer a meeting room for your guests with complimentary tea. The room is provided " +
                "with all necessary facilities such as TVs, HDMI Connectors, and a Washroom. You may ask our waiter for " +
                "further details on the booking details and price. ");
        label2.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        label2.setTextColor(Color.parseColor("#ffffff"));
        relativeLayout1.addView(label2);

        buttonSpecialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(), SpecializationActivity.class);
                startActivity(i);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(), StoryActivity02.class);
                startActivity(i);
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });
    }
}
