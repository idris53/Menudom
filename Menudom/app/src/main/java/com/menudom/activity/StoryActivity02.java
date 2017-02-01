package com.menudom.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.menudom.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Idris Bohra on 12/8/2016.
 */
public class StoryActivity02 extends Activity {

    //ImageView background_image, promo_image01, promo_image02, logo;
    //TextView label1;
    float widthDiff, heightDiff;
    private final float DEFAULT_SCREEN_WIDTH = 2048;
    private final float DEFAULT_SCREEN_HEIGHT = 1536;
    private final float DEFAULT_LOGO_X = 40;
    private final float DEFAULT_LOGO_Y = 40;
    RelativeLayout relativeLayout, relativeLayout02;
    int NEW_LOGO_X, NEW_LOGO_Y;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener imageListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story02);

        //relativelayout to contain product_image1, product_image2, logo, label1, view, label2, GO BACK button, THE PLACE button, JUMP TO MENU button
        relativeLayout = (RelativeLayout)findViewById(R.id.rl1);

        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        options = new DisplayImageOptions.Builder()
                .cacheOnDisc().build();

        imageListener = new ImageDisplayListener();

        //to calculate screen width and height
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;

        //screen difference calculation logic
        widthDiff = width/DEFAULT_SCREEN_WIDTH;
        heightDiff = height/DEFAULT_SCREEN_HEIGHT;

        //status bar height
        int StatusBarHeight = getStatusBarHeight();
        System.out.println("StatusBarHeight - "+StatusBarHeight);
        //Toast.makeText(getApplicationContext(), Float.toString(heightDiff), Toast.LENGTH_SHORT).show();

        //relativelayout start
        //for product image 01
        float productImage01Width = DEFAULT_SCREEN_WIDTH/2;
        float productImage01Height = 713;
        ImageView product_image01 = new ImageView(getApplicationContext());
        product_image01.setId(View.generateViewId());
        //product_image01.setImageResource(R.mipmap.temp_story_top);
        imageLoader.displayImage(
                ("drawable://"+R.mipmap.temp_story_top), product_image01,
                options, imageListener);
        product_image01.setScaleType(ImageView.ScaleType.FIT_XY);
        productImage01Width = productImage01Width * widthDiff;
        productImage01Height = productImage01Height * heightDiff;
        RelativeLayout.LayoutParams lp10 = new RelativeLayout.LayoutParams((int)productImage01Width, (int)productImage01Height);
        lp10.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp10.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        product_image01.setLayoutParams(lp10);
        relativeLayout.addView(product_image01);

        //for product image 02
        float productImage02Width = DEFAULT_SCREEN_WIDTH/2;
        float productImage02Height = 825;
        ImageView product_image02 = new ImageView(getApplicationContext());
        product_image02.setId(View.generateViewId());
        //product_image02.setImageResource(R.mipmap.temp_story_bottom);
        imageLoader.displayImage(
                ("drawable://"+R.mipmap.temp_story_bottom), product_image02,
                options, imageListener);
        product_image02.setScaleType(ImageView.ScaleType.FIT_XY);
        productImage02Width = productImage02Width * widthDiff;
        productImage02Height = productImage02Height * heightDiff;
        RelativeLayout.LayoutParams lp11 = new RelativeLayout.LayoutParams((int)productImage02Width, (int)productImage02Height);
        lp11.addRule(RelativeLayout.BELOW, product_image01.getId());
        lp11.addRule(RelativeLayout.ALIGN_LEFT, product_image01.getId());

        product_image02.setLayoutParams(lp11);
        relativeLayout.addView(product_image02);

        //for background image
        float backgroundWidth = 1024;
        float backgroundHeight = 1536;
        ImageView background_image = new ImageView(getApplicationContext());
        background_image.setId(View.generateViewId());
        background_image.setImageResource(R.mipmap.temp_ourstory);
        background_image.setScaleType(ImageView.ScaleType.FIT_XY);
        backgroundWidth = backgroundWidth * widthDiff;
        backgroundHeight = backgroundHeight * heightDiff;
        //background_image.getLayoutParams().width = (int)backgroundWidth;
        //background_image.getLayoutParams().height = (int)backgroundHeight;
        RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams((int)backgroundWidth, (int)backgroundHeight);
        lp0.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        background_image.setLayoutParams(lp0);
        relativeLayout.addView(background_image);

        //for logo
        float logoWidth = 473;
        float logoHeight = 200;
        ImageView logo_image = new ImageView(getApplicationContext());
        logo_image.setId(View.generateViewId());
        logo_image.setScaleType(ImageView.ScaleType.FIT_XY);
        logo_image.setImageResource(R.mipmap.temp_logo01);
        logoWidth = logoWidth * widthDiff;
        logoHeight = logoHeight * heightDiff;
        NEW_LOGO_X = (int) ((width * (DEFAULT_LOGO_X/DEFAULT_SCREEN_WIDTH)));
        NEW_LOGO_Y = (int) ((height * (DEFAULT_LOGO_Y/DEFAULT_SCREEN_HEIGHT)));
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams((int)logoWidth, (int)logoHeight);
        lp1.setMargins(NEW_LOGO_X, NEW_LOGO_Y, 0, 0);
        lp1.addRule(RelativeLayout.ALIGN_LEFT, background_image.getId());
        logo_image.setLayoutParams(lp1);
        relativeLayout.addView(logo_image);

        //Our story label
        TextView label1 = new TextView(getApplicationContext());
        label1.setId(View.generateViewId());
        label1.setText("Our Story");
        label1.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));
        label1.setTextSize((int) (getResources().getDimension(R.dimen.storypage_label1) / getResources().getDisplayMetrics().density));
        label1.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_LEFT, logo_image.getId());
        lp2.addRule(RelativeLayout.BELOW, logo_image.getId());
        label1.setLayoutParams(lp2);
        relativeLayout.addView(label1);

        //view
        View view1 = new View(getApplicationContext());
        view1.setId(View.generateViewId());
        view1.setBackgroundColor(Color.parseColor("#ddc108"));
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(4 * heightDiff));
        lp3.addRule(RelativeLayout.BELOW, label1.getId());
        lp3.addRule(RelativeLayout.ALIGN_RIGHT, background_image.getId());
        lp3.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp3.setMargins(0, (int)(20* heightDiff), (int)(150* widthDiff), 0);
        view1.setLayoutParams(lp3);
        relativeLayout.addView(view1);

        //GO BACK button
        Button buttonBack = new Button(getApplicationContext());
        buttonBack.setId(View.generateViewId());
        buttonBack.setText("BACK");
        buttonBack.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonBack.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        buttonBack.setBackgroundResource(R.drawable.curve_button02);
        Drawable img = getApplicationContext().getResources().getDrawable( R.mipmap.leftback );
        img.setBounds(0, 0, (int) (65 * widthDiff), (int) (50 * heightDiff));
        buttonBack.setCompoundDrawables(img, null, null, null);
        buttonBack.setPadding((int) (40 * widthDiff), 0, (int) (40 * widthDiff), 0);
        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams((int)(312 * widthDiff), (int)(150 * heightDiff));
        lp5.addRule(RelativeLayout.ALIGN_BOTTOM, background_image.getId());
        lp5.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp5.setMargins(0,0,0,(int)(40 *heightDiff));
        //buttonBack.setPadding(50* (int)widthDiff, 0,50* (int)widthDiff,0);
        buttonBack.setLayoutParams(lp5);
        relativeLayout.addView(buttonBack);

        //THE PLACE button
        Button buttonPlace = new Button(getApplicationContext());
        buttonPlace.setId(View.generateViewId());
        buttonPlace.setText("THE PLACE");
        buttonPlace.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonPlace.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        //buttonBack.setPadding(40, 15, 40, 15);
        buttonPlace.setBackgroundResource(R.drawable.curve_button02);
        RelativeLayout.LayoutParams lp6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(150 * heightDiff));
        lp6.addRule(RelativeLayout.ALIGN_BOTTOM, background_image.getId());
        lp6.addRule(RelativeLayout.RIGHT_OF, buttonBack.getId());
        lp6.addRule(RelativeLayout.ALIGN_RIGHT, background_image.getId());
        lp6.setMargins((int)(40*widthDiff),0,(int)(40* widthDiff),(int)(40 *heightDiff));
        buttonPlace.setLayoutParams(lp6);
        relativeLayout.addView(buttonPlace);

        //JUMP TO MENU button
        Button buttonMenu = new Button(getApplicationContext());
        buttonMenu.setId(View.generateViewId());
        buttonMenu.setText("JUMP TO MENU");
        buttonMenu.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonMenu.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        buttonMenu.setBackgroundResource(R.drawable.curve_button);
        GradientDrawable drawable = (GradientDrawable) buttonMenu.getBackground();
        drawable.setColor(Color.parseColor("#ddc108"));
        RelativeLayout.LayoutParams lp7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(150 * heightDiff));
        lp7.addRule(RelativeLayout.ABOVE, buttonBack.getId());
        lp7.addRule(RelativeLayout.ALIGN_LEFT, buttonBack.getId());
        lp7.addRule(RelativeLayout.ALIGN_RIGHT, buttonPlace.getId());
        lp7.setMargins(0,0,0,(int)(40 *heightDiff));
        buttonMenu.setLayoutParams(lp7);
        relativeLayout.addView(buttonMenu);

        //label2 scrollview
        ScrollView scrollView = new ScrollView(getApplicationContext());
        scrollView.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp4.addRule(RelativeLayout.BELOW, view1.getId());
        lp4.addRule(RelativeLayout.ABOVE, buttonMenu.getId());
        lp4.addRule(RelativeLayout.ALIGN_RIGHT, background_image.getId());
        lp4.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp4.setMargins(0, (int)(20 *heightDiff), (int)(150* widthDiff), (int)(20 *heightDiff));
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
        label2.setText("Organika is not just a place where good tea is served. Organika is a hub for " +
                "organic & healthy lifestyle.\n\nAt Organika, we believe in the importance of giving a " +
                "harmonious experience to our customers. This comes through our organic & healthy food choices" +
                " we provide in addition to our relaxing space & environment. ");
        label2.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        label2.setTextColor(Color.parseColor("#ffffff"));
        relativeLayout1.addView(label2);
        //relativelayout end

        buttonPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PlaceActivity.class);
                startActivity(i);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(i);
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });

    }

    private static class ImageDisplayListener extends
            SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}