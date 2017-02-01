package com.menudom.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
 * Created by Idris Bohra on 12/11/2016.
 */
public class WelcomeActivity extends Activity {

    float widthDiff, heightDiff;
    private final float DEFAULT_SCREEN_WIDTH = 2048;
    private final float DEFAULT_SCREEN_HEIGHT = 1536;
    private final float DEFAULT_LABEL_X = 40;
    private final float DEFAULT_LABEL_Y = 80;
    RelativeLayout relativeLayout;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener imageListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //relativelayout to contain product_image1, product_image2, logo, label1, view, label2, GO BACK button, THE PLACE button, JUMP TO MENU button
        relativeLayout = (RelativeLayout)findViewById(R.id.rl3);

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

        //relativelayout start
        //for product image 01
        float productImage01Width = DEFAULT_SCREEN_WIDTH/2;
        float productImage01Height = 718;
        ImageView product_image01 = new ImageView(getApplicationContext());
        product_image01.setId(View.generateViewId());
        //product_image01.setImageResource(R.mipmap.temp_welcome_top);
        imageLoader.displayImage(
                ("drawable://"+R.mipmap.temp_welcome_top), product_image01,
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
        float productImage02Height = 818;
        ImageView product_image02 = new ImageView(getApplicationContext());
        product_image02.setId(View.generateViewId());
        //product_image02.setImageResource(R.mipmap.temp_welcome_bottom);
        imageLoader.displayImage(
                ("drawable://"+R.mipmap.temp_welcome_bottom), product_image02,
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
        background_image.setImageResource(R.mipmap.temp_splash02);
        background_image.setScaleType(ImageView.ScaleType.FIT_XY);
        backgroundWidth = backgroundWidth * widthDiff;
        backgroundHeight = backgroundHeight * heightDiff;
        //background_image.getLayoutParams().width = (int)backgroundWidth;
        //background_image.getLayoutParams().height = (int)backgroundHeight;
        RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams((int)backgroundWidth, (int)backgroundHeight);
        lp0.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        background_image.setLayoutParams(lp0);
        relativeLayout.addView(background_image);

        //Our story label
        TextView label1 = new TextView(getApplicationContext());
        label1.setId(View.generateViewId());
        label1.setText("Welcome\nto Organika");
        label1.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));
        label1.setTextSize((int) (getResources().getDimension(R.dimen.storypage_label1) / getResources().getDisplayMetrics().density));
        label1.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        int NEW_LOGO_X = (int) ((width * (DEFAULT_LABEL_X/DEFAULT_SCREEN_WIDTH)));
        int NEW_LOGO_Y = (int) ((height * (DEFAULT_LABEL_Y/DEFAULT_SCREEN_HEIGHT)));
        lp2.addRule(RelativeLayout.ALIGN_LEFT, background_image.getId());
        lp2.setMargins(NEW_LOGO_X, NEW_LOGO_Y, 0, 0);
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
        lp3.setMargins(0, (int)(40 * heightDiff), (int)(150 * widthDiff), 0);
        view1.setLayoutParams(lp3);
        relativeLayout.addView(view1);

        //JUMP TO MENU button
        Button buttonMenu = new Button(getApplicationContext());
        buttonMenu.setId(View.generateViewId());
        buttonMenu.setText("JUMP TO MENU");
        buttonMenu.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonMenu.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        buttonMenu.setBackgroundResource(R.drawable.curve_button);
        GradientDrawable drawable = (GradientDrawable) buttonMenu.getBackground();
        drawable.setColor(Color.parseColor("#ddc108"));
        //buttonMenu.setPadding((int)(40* widthDiff), 0,20,0);
        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams((int)(412 * widthDiff), (int)(150 * heightDiff));
        lp5.addRule(RelativeLayout.ALIGN_BOTTOM, background_image.getId());
        lp5.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp5.setMargins(0,0,0,(int)(40* heightDiff));
        buttonMenu.setLayoutParams(lp5);
        relativeLayout.addView(buttonMenu);

        //OUR STORY button
        Button buttonStory = new Button(getApplicationContext());
        buttonStory.setId(View.generateViewId());
        buttonStory.setText("OUR STORY");
        buttonStory.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        buttonStory.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        //buttonBack.setPadding(40, 15, 40, 15);
        buttonStory.setBackgroundResource(R.drawable.curve_button02);
        RelativeLayout.LayoutParams lp6 = new RelativeLayout.LayoutParams((int)(312 * widthDiff), (int)(150 * heightDiff));
        lp6.addRule(RelativeLayout.ALIGN_BOTTOM, background_image.getId());
        lp6.addRule(RelativeLayout.RIGHT_OF, buttonMenu.getId());
        lp6.addRule(RelativeLayout.ALIGN_RIGHT, background_image.getId());
        lp6.setMargins((int)(40* widthDiff),0,(int)(40* widthDiff),(int)(40* heightDiff));
        buttonStory.setLayoutParams(lp6);
        relativeLayout.addView(buttonStory);

        //label2 scrollview
        ScrollView scrollView = new ScrollView(getApplicationContext());
        scrollView.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp4.addRule(RelativeLayout.BELOW, view1.getId());
        lp4.addRule(RelativeLayout.ABOVE, buttonMenu.getId());
        lp4.addRule(RelativeLayout.ALIGN_RIGHT, background_image.getId());
        lp4.addRule(RelativeLayout.ALIGN_LEFT, label1.getId());
        lp4.setMargins(0, (int)(40* heightDiff), (int)(150* widthDiff), (int)(40* heightDiff));
        scrollView.setLayoutParams(lp4);
        relativeLayout.addView(scrollView);

        //label2 Relative layout
        RelativeLayout relativeLayout1 = new RelativeLayout(getApplicationContext());
        relativeLayout1.setId(View.generateViewId());
        scrollView.addView(relativeLayout1);

        //label2 textview
        TextView label2 = new TextView(getApplicationContext());
        label2.setId(View.generateViewId());
        label2.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        label2.setText("Welcome to Organika, your sanctuary where you find the healthy teas, good " +
                "food, and relaxing environment. So if you love to follow a healthy lifestyle, you are " +
                "at the right place!\n\nThrough this app, we are taking you to a journey to discover our" +
                " teahouse and what we have to offer you the best experience. All you have to do is to relax! " +
                "And go through our app.");
        label2.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        label2.setTextColor(Color.parseColor("#ffffff"));
        relativeLayout1.addView(label2);
        //relativelayout end

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });

        buttonStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StoryActivity02.class);
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
