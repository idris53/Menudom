package com.menudom.activity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menudom.R;

/**
 * Created by User on 11/28/2016.
 */
public class StoryActivity extends Activity {

    ImageView background_image, promo_image01, promo_image02, logo;
    TextView label1;
    float widthDiff, heightDiff;
    private final float DEFAULT_SCREEN_WIDTH = 1024;
    private final float DEFAULT_SCREEN_HEIGHT = 768;
    private final float DEFAULT_LOGO_X = 100;
    private final float DEFAULT_LOGO_Y = 100;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        background_image = (ImageView) findViewById(R.id.imageView4);
        /*promo_image01 = (ImageView) findViewById(R.id.imageView5);
        promo_image02 = (ImageView) findViewById(R.id.imageView6);*/
        //logo = (ImageView) findViewById(R.id.imageView5);
        //label1 = (TextView) findViewById(R.id.textView);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;

            widthDiff = width/DEFAULT_SCREEN_WIDTH;

            heightDiff = height/DEFAULT_SCREEN_HEIGHT;


        background_image.setImageResource(R.mipmap.background_512x768);
        float backgroundWidth = 512;
        float backgroundHeight = 768;

        backgroundWidth = backgroundWidth * widthDiff;
        backgroundHeight = backgroundHeight * heightDiff;

        /*RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)backgroundWidth, (int)backgroundHeight);
        lp.setMargins(0, 0, 0, 0);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        background_image.setLayoutParams(lp);*/

        relativeLayout = (RelativeLayout)findViewById(R.id.rl1);
        relativeLayout.removeAllViews();
        relativeLayout.addView(background_image);

        logo.setImageResource(R.mipmap.temp);
        float logoWidth = 200;
        float logoHeight = 100;

        logoWidth = logoWidth * widthDiff;
        logoHeight = logoHeight * heightDiff;

        int NEW_LOGO_X = (int) ((width * (DEFAULT_LOGO_X/DEFAULT_SCREEN_WIDTH)) - (logoWidth/(float)2));
        int NEW_LOGO_Y = (int) ((height * (DEFAULT_LOGO_Y/DEFAULT_SCREEN_HEIGHT)) - (logoHeight/(float)2));

        /*RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams((int)logoWidth, (int)logoHeight);
        lp1.setMargins(NEW_LOGO_X, NEW_LOGO_Y, 0, 0);
        logo.setLayoutParams(lp1);*/

        logo.setMaxWidth((int)logoWidth);
        logo.setMaxHeight((int)logoHeight);

        label1.setPadding(NEW_LOGO_X, NEW_LOGO_Y,0,0);

        /*promo_image01.setImageResource(R.mipmap.temp);
        promo_image02.setImageResource(R.mipmap.temp);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float densityDpi = metrics.density;



        float imageWidth = background_image.getDrawable().getIntrinsicWidth();
        //float imageHeight = 768;

        System.out.println("imageWidth - "+imageWidth);

        if(width!=DEFAULT_SCREEN_WIDTH){

            float widthDiff = width/DEFAULT_SCREEN_WIDTH;

            imageWidth = imageWidth * widthDiff;
            imageWidth = imageWidth/densityDpi;

            imageWidth = width * (imageWidth/DEFAULT_SCREEN_WIDTH);

        }*/
/*
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)imageWidth, RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, 0, 0, 0);
        background_image.setLayoutParams(lp);*/
    }
}
