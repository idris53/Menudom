package com.menudom.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Idris Bohra on 12/17/2016.
 */
public class MenuActivity extends Activity {

    ImageView background, product, logo_image, item_image, icon1, icon2, trans_product_image;
    ListView categoryListView, itemlist;
    TextView itemsLabel, label2, itemsLabel2;
    Button goBack, done, load_more;
    float widthDiff, heightDiff;
    View view00;
    ArrayList<HashMap<String, String>> categories = new ArrayList<>();
    HashMap<String, ArrayList<HashMap<String, String>>> categoriesItems = new HashMap<String, ArrayList<HashMap<String, String>>>();
    private final float DEFAULT_SCREEN_WIDTH = 2048;
    private final float DEFAULT_SCREEN_HEIGHT = 1536;
    private final float DEFAULT_LOGO_X = 20;
    private final float DEFAULT_LOGO_Y = 40;
    ScrollView scrollView;
    RelativeLayout relativeLayout;
    CustomListAdapter adapter01;
    CustomListAdapter02 adapter02;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener imageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        background = (ImageView)findViewById(R.id.imageView6);
        logo_image = (ImageView)findViewById(R.id.imageView7);
        product = (ImageView)findViewById(R.id.imageView9);
        item_image = (ImageView)findViewById(R.id.imageView8);
        icon1 = (ImageView)findViewById(R.id.imageView10);
        icon2 = (ImageView)findViewById(R.id.imageView11);
        trans_product_image = (ImageView)findViewById(R.id.imageView19);
        categoryListView = (ListView)findViewById(R.id.listing);
        itemlist = (ListView)findViewById(R.id.listView);
        itemsLabel = (TextView)findViewById(R.id.textView5);
        itemsLabel2 = (TextView)findViewById(R.id.textView7);
        label2 = (TextView)findViewById(R.id.textView6);
        goBack = (Button)findViewById(R.id.button8);
        done = (Button)findViewById(R.id.button7);
        load_more = (Button)findViewById(R.id.button9);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        relativeLayout = (RelativeLayout)findViewById(R.id.relative01);
        view00 = (View)findViewById(R.id.view);

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

        for(int i=0;i<4;i++){
            if(i==0){
            HashMap<String, String> map = new HashMap<>();
            map.put("categoryIcon",String.valueOf(R.mipmap.smart_object01));
            map.put("categoryLabel","Desserts");
                map.put("categoryLabelShort","Desserts");
                map.put("itemCount","02");
            categories.add(map);
        } else if(i==1){
                HashMap<String, String> map = new HashMap<>();
                map.put("categoryIcon",String.valueOf(R.mipmap.smart_object02));
                map.put("categoryLabel","Organic Teas (Med Pot, Lrg Pot)");
                map.put("categoryLabelShort","Organic Teas");
                map.put("itemCount","10");
                categories.add(map);
            } else if(i==2){
                HashMap<String, String> map = new HashMap<>();
                map.put("categoryIcon",String.valueOf(R.mipmap.smart_object03));
                map.put("categoryLabel","Sandwiches");
                map.put("categoryLabelShort","Sandwiches");
                map.put("itemCount","05");
                categories.add(map);
            } else if(i==3){
                HashMap<String, String> map = new HashMap<>();
                map.put("categoryIcon",String.valueOf(R.mipmap.smart_object04));
                map.put("categoryLabel","Fresh Juices");
                map.put("categoryLabelShort","Fresh Juices");
                map.put("itemCount","05");
                categories.add(map);
            }
        }



        for(int i=0; i<categories.size(); i++){

            if(i==2){

            ArrayList<HashMap<String, String>> list = new ArrayList<>();
            for(int j=0; j<5; j++) {
                if(j==0){
                HashMap<String, String> map = new HashMap<>();
                map.put("itemLabel", "Halloumi Sandwich");
                map.put("itemPrice", "BD 2.000");
                map.put("itemViggieIcon", "true");
                map.put("itemStarIcon", "false");
                map.put("itemImage", "drawable://"+ R.mipmap.temp_halloumi_sandwich);
                map.put("itemDescription", "Panini with grilled halloumi, spicy hummus & sundried tomato, topped with jarjeer\n\n" +
                        "(Served with your choice of potato wedges, or salad)");
                list.add(map);
            } else if(j==1){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("itemLabel", "Halloumi Quesadilla");
                    map.put("itemPrice", "BD 2.000");
                    map.put("itemViggieIcon", "true");
                    map.put("itemStarIcon", "false");
                    map.put("itemImage", "drawable://"+ R.mipmap.temp_halloumi_quesadilla);
                    map.put("itemDescription", "Halloumi quesadilla with a mixture of bell pepper, olive oil and zaatar, served in tortillas.\n\n" +
                            "(Served with your choice of potato wedges, or salad)");
                    list.add(map);
                }else if(j==2){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("itemLabel", "Egg & Mushroom Sandwich");
                    map.put("itemPrice", "BD 1.800");
                    map.put("itemViggieIcon", "true");
                    map.put("itemStarIcon", "false");
                    map.put("itemImage", "drawable://"+ R.mipmap.temp_egg_and_mushroom);
                    map.put("itemDescription", "Grilled sandwich with melted cheese, egg & mushroom, cooked with onion\n\n" +
                            "(Served with your choice of potato wedges, or salad)");
                    list.add(map);
                }else if(j==3){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("itemLabel", "Cheese & Mushroom Sandwich");
                    map.put("itemPrice", "BD 1.900");
                    map.put("itemViggieIcon", "true");
                    map.put("itemStarIcon", "false");
                    map.put("itemImage", "drawable://"+ R.mipmap.temp_cheese_and_mushroom);
                    map.put("itemDescription", "Grilled sandwich with cheddar cheese & mozzarella cheese, topped with fresh mushroom.\n\n" +
                            "(Served with your choice of potato wedges, or salad)");
                    list.add(map);
                }else if(j==4){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("itemLabel", "Chicken Quesadilla");
                    map.put("itemPrice", "BD 2.100");
                    map.put("itemViggieIcon", "false");
                    map.put("itemStarIcon", "false");
                    map.put("itemImage", "drawable://"+ R.mipmap.temp_chicken_quesadilla);
                    map.put("itemDescription", "Chicken quesadilla with mozzarella cheese, green pepper, mushroom & onion, served in tortillas\n" +
                            "\n(Served with your choice of potato wedges, or salad)");
                    list.add(map);
                }
            }
                categoriesItems.put(categories.get(i).get("categoryLabel"),list);

            } else if(i==0){

                ArrayList<HashMap<String, String>> list = new ArrayList<>();
                for(int j=0; j<2; j++) {
                    if(j==0){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Date Cake");
                        map.put("itemPrice", "BD 1.200");
                        map.put("itemViggieIcon", "true");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_dates_cake);
                        map.put("itemDescription", "Home baked with real dates.");
                        list.add(map);
                    }else if(j==1){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Carrot Cake");
                        map.put("itemPrice", "BD 1.200");
                        map.put("itemViggieIcon", "true");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_carrot_cake);
                        map.put("itemDescription", "Home baked with fresh carrot and walnut.");
                        list.add(map);
                    }
                }

                categoriesItems.put(categories.get(i).get("categoryLabel"),list);
            } else if(i==3){

                ArrayList<HashMap<String, String>> list = new ArrayList<>();

                for(int j=0; j<5; j++) {
                    if(j==0){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Avocado");
                        map.put("itemPrice", "BD 1.200");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_avocado_juice);
                        map.put("itemDescription", "Fresh ripe avocado juice with your choice of either honey or sugar free sweetener.");
                        list.add(map);
                    }else if(j==1){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Orange Juice");
                        map.put("itemPrice", "BD 1.000");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_orange_juice);
                        map.put("itemDescription", "");
                        list.add(map);
                    }else if(j==2){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Lemon Mint Juice");
                        map.put("itemPrice", "BD 1.200");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_lemon_mint_juice);
                        map.put("itemDescription", "");
                        list.add(map);
                    }else if(j==3){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Honeydew melon juice");
                        map.put("itemPrice", "BD 1.200");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "true");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_honeydew_melon_juice);
                        map.put("itemDescription", "");
                        list.add(map);
                    }else if(j==4){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Watermelon Juice");
                        map.put("itemPrice", "BD 1.200");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_watermelon_juice);
                        map.put("itemDescription", "");
                        list.add(map);
                    }
                }
                categoriesItems.put(categories.get(i).get("categoryLabel"),list);

            } else if(i==1){

                ArrayList<HashMap<String, String>> list = new ArrayList<>();

                for(int j=0; j<10; j++) {
                    if(j==0){
                HashMap<String, String> map = new HashMap<>();
                map.put("itemLabel", "Green Tea with Rose and Cassis");
                map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_green_tea_with_rose_and_cassis);
                map.put("itemDescription", "Ingredients: green tea, rose, and natural flavoring\n\nProduct of Japan");
                list.add(map);

            } else if(j==1){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Green Tea with Pearl Grape");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_green_tea_with_pearl_grape);
                        map.put("itemDescription", "Ingredients: green tea, corn flower, and natural flavoring\n\nProduct of Japan");
                        list.add(map);

                    } else if(j==2){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Green Tea with White Peach");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_green_tea_with_white_peach);
                        map.put("itemDescription", "Ingredients: green tea, orange flower, and natural flavoring\n\nProduct of Japan");
                        list.add(map);

                    }else if(j==3){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "1001 Nights Green Tea");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_nights_green_tea);
                        map.put("itemDescription", "Ingredients: sunflower petals, rose hip, flavoring\n\nProduct of China");
                        list.add(map);

                    }else if(j==4){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Jasmine Silver White Needle");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_jasmine_silver_white_needle);
                        map.put("itemDescription", "Ingredients: pure white tea, jasmine flavor\n\nProduct of China");
                        list.add(map);

                    }else if(j==5){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Milk Oolong Tea");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_milk_oolong_tea);
                        map.put("itemDescription", "Ingredients: Pure oolong tea\n\nProduct of China");
                        list.add(map);

                    }else if(j==6){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "South Sea Pearl - Passion Fruit, Mango, Pineapple Tea");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_south_sea_pearl);
                        map.put("itemDescription", "Ingredients: green tea, pineapple, mango bits, orange petals, and rose petals");
                        list.add(map);

                    }else if(j==7){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Rooibos Original Organic Tea");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_rooibos_original_organic_tea);
                        map.put("itemDescription", "Ingredients: rooibos organic tea\n\nProduct of South Africa");
                        list.add(map);

                    }else if(j==8){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Herbal Chai");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_herbal_tea);
                        map.put("itemDescription", "Ingredients: apple bits, chamomile flower, stinging " +
                                "nettle leaves, cinnamon, nutmeg, ginger, cloves, cardamom, star anise, and flavoring\n\nProduct of Germany");
                        list.add(map);

                    }else if(j==9){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Oriental Eve");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_oriental_eve);
                        map.put("itemDescription", "Ingredients: black tea, green tea, lemon, strawberry, cornflower, blossoms, marigold flower, and rose petals\n\nProduct of Germany");
                        list.add(map);

                    }else if(j==10){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("itemLabel", "Strawberry Kiwi");
                        map.put("itemPrice", "BD 2.000 / 2.500");
                        map.put("itemViggieIcon", "false");
                        map.put("itemStarIcon", "false");
                        map.put("itemImage", "drawable://"+ R.mipmap.temp_strawberry_kiwi);
                        map.put("itemDescription", "Ingredients: Apple bits, rosehip peels, hibiscus, flavour, kiwi bits, strawberry bits");
                        list.add(map);

                    }
                }

                categoriesItems.put(categories.get(i).get("categoryLabel"),list);
            }

        }

       /* itemlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

            }
        });*/

        float backgroundWidth = DEFAULT_SCREEN_WIDTH;
        float backgroundHeight = DEFAULT_SCREEN_HEIGHT;
        backgroundWidth = backgroundWidth * widthDiff;
        backgroundHeight = backgroundHeight * heightDiff;
        background.setImageResource(R.mipmap.temp_splash03);
        background.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams((int)backgroundWidth, (int)backgroundHeight);
        background.setLayoutParams(lp0);

        float logoWidth = 237;
        float logoHeight = 85;
        //ImageView logo_image = new ImageView(getApplicationContext());
        //logo_image.setId(View.generateViewId());
        logo_image.setScaleType(ImageView.ScaleType.FIT_XY);
        logo_image.setImageResource(R.mipmap.temp_logo01);
        logoWidth = logoWidth * widthDiff;
        logoHeight = logoHeight * heightDiff;
        //int NEW_LOGO_X = (int) ((width * (DEFAULT_LOGO_X/DEFAULT_SCREEN_WIDTH)));
        //int NEW_LOGO_Y = (int) ((height * (DEFAULT_LOGO_Y/DEFAULT_SCREEN_HEIGHT)));
        int NEW_LOGO_X = (int)(DEFAULT_LOGO_X * widthDiff);
        int NEW_LOGO_Y = (int)(DEFAULT_LOGO_Y * widthDiff);
        RelativeLayout.LayoutParams lp11 = new RelativeLayout.LayoutParams((int)logoWidth, (int)logoHeight);
        lp11.setMargins(NEW_LOGO_X, NEW_LOGO_Y, NEW_LOGO_X, 0);
        lp11.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp11.addRule(RelativeLayout.ALIGN_RIGHT, categoryListView.getId());
        //lp11.setMargins(0,0,10,0);
        logo_image.setLayoutParams(lp11);

        categoryListView.setDivider(null);
        categoryListView.setBackgroundColor(Color.parseColor("#298A79"));
        categoryListView.setPadding(0,(int)(300 *heightDiff),0,0);
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams((int)(250 * widthDiff), RelativeLayout.LayoutParams.MATCH_PARENT);
        lp1.addRule(RelativeLayout.ALIGN_LEFT);
        lp1.setMargins(0,0,0,0);
        categoryListView.setLayoutParams(lp1);
        adapter01 = new CustomListAdapter(getApplicationContext(), categories);
        categoryListView.setAdapter(adapter01);

        view00.setBackgroundColor(Color.parseColor("#02CCA9"));
        RelativeLayout.LayoutParams lp00 = new RelativeLayout.LayoutParams((int)(20 * widthDiff), RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp00.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp00.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp00.addRule(RelativeLayout.RIGHT_OF, categoryListView.getId());
        view00.setLayoutParams(lp00);

        itemsLabel.setTextSize((int) (getResources().getDimension(R.dimen.menupage_label6) / getResources().getDisplayMetrics().density));
        itemsLabel.setText("Small Text");
        itemsLabel.setTextColor(Color.parseColor("#ffffff"));
        itemsLabel.setBackgroundColor(Color.parseColor("#02CCA9"));
        itemsLabel.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));
        itemsLabel.setPadding((int)(60 *widthDiff),(int)(60 *heightDiff),(int)(60 *widthDiff),(int)(60 *heightDiff));
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp2.addRule(RelativeLayout.RIGHT_OF, view00.getId());
        lp2.addRule(RelativeLayout.ALIGN_RIGHT, itemlist.getId());
        itemsLabel.setLayoutParams(lp2);

        item_image.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams lp8 = new RelativeLayout.LayoutParams((int)(100 *widthDiff), (int)(100 *heightDiff));
        lp8.addRule(RelativeLayout.ALIGN_TOP, itemsLabel.getId());
        lp8.addRule(RelativeLayout.ALIGN_RIGHT, itemsLabel.getId());
        lp8.setMargins((int)(30 *widthDiff),(int)(50 *heightDiff),(int)(30 *widthDiff),(int)(30 *heightDiff));
        item_image.setLayoutParams(lp8);

        //itemlist.setDividerHeight(10);
        //itemlist.setDivider(getResources().getDrawable(R.drawable.list_divider));
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams((int)(1050 * widthDiff), RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.BELOW, itemsLabel.getId());
        lp3.addRule(RelativeLayout.RIGHT_OF, view00.getId());
        lp3.addRule(RelativeLayout.ABOVE, load_more.getId());
        lp3.setMargins(0,(int)(30 *heightDiff),0,(int)(40 *heightDiff));
        itemlist.setLayoutParams(lp3);

        //product.setImageResource(R.mipmap.temp_product04);
        product.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(500 * heightDiff));
        lp4.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp4.addRule(RelativeLayout.RIGHT_OF, itemlist.getId());
        lp4.setMargins((int)(20 *widthDiff),0,0,0);
        product.setLayoutParams(lp4);

        trans_product_image.setImageResource(R.mipmap.shadow_copy);
        trans_product_image.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams lp20 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(200 * heightDiff));
        lp20.addRule(RelativeLayout.ALIGN_LEFT, product.getId());
        lp20.addRule(RelativeLayout.ALIGN_BOTTOM, product.getId());
        lp20.addRule(RelativeLayout.ALIGN_RIGHT, product.getId());
        trans_product_image.setLayoutParams(lp20);

        itemsLabel2.setTextSize((int) (getResources().getDimension(R.dimen.menupage_label5) / getResources().getDisplayMetrics().density));
        itemsLabel2.setText("Small Text");
        itemsLabel2.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        itemsLabel2.setTextColor(Color.parseColor("#ffffff"));
        itemsLabel2.setPadding((int)(40 *widthDiff),(int)(40 *heightDiff),(int)(40 *widthDiff),(int)(30 *heightDiff));
        RelativeLayout.LayoutParams lp9 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp9.addRule(RelativeLayout.ALIGN_LEFT, product.getId());
        lp9.addRule(RelativeLayout.ALIGN_BOTTOM, product.getId());
        lp9.addRule(RelativeLayout.ALIGN_RIGHT, product.getId());
        itemsLabel2.setLayoutParams(lp9);

        icon1.setImageResource(R.mipmap.temp_star_gold);
        icon1.setVisibility(View.INVISIBLE);
        RelativeLayout.LayoutParams lp13 = new RelativeLayout.LayoutParams((int)(80 *widthDiff), (int)(80 *heightDiff));
        lp13.setMargins((int)(310 *widthDiff),0,(int)(40 *widthDiff),(int)(30 *heightDiff));
        lp13.addRule(RelativeLayout.ALIGN_LEFT, product.getId());
        lp13.addRule(RelativeLayout.ALIGN_BOTTOM, product.getId());
        icon1.setLayoutParams(lp13);

        icon2.setImageResource(R.mipmap.temp_chily_gold);
        icon2.setVisibility(View.INVISIBLE);
        RelativeLayout.LayoutParams lp14 = new RelativeLayout.LayoutParams((int)(80 *widthDiff), (int)(80 *heightDiff));
        lp14.setMargins((int)(20 *heightDiff),0,(int)(310 *widthDiff),(int)(30 *heightDiff));
        lp14.addRule(RelativeLayout.ALIGN_BOTTOM, product.getId());
        lp14.addRule(RelativeLayout.ALIGN_RIGHT, product.getId());
        icon2.setLayoutParams(lp14);

        done.setText("RATE US!");
        done.setTextColor(Color.parseColor("#ffffff"));
        done.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        done.setBackgroundResource(R.drawable.curve_button02);
        done.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        //done.setPadding(80, 0, 80, 0);
        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams((int)(292 * widthDiff), (int)(150 * heightDiff));
        lp5.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp5.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp5.setMargins(0, 0, (int)(40 *widthDiff),(int)(40 *heightDiff));
        done.setLayoutParams(lp5);

        goBack.setText("BACK");
        goBack.setTextColor(Color.parseColor("#ffffff"));
        goBack.setBackgroundResource(R.drawable.curve_button02);
        goBack.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        goBack.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        Drawable img = getApplicationContext().getResources().getDrawable( R.mipmap.leftback );
        img.setBounds( 0, 0, (int) (65 * widthDiff), (int) (50 * heightDiff) );
        goBack.setCompoundDrawables(img, null, null, null);
        goBack.setPadding((int) (40 * widthDiff), 0, (int) (40 * widthDiff), 0);
        RelativeLayout.LayoutParams lp6 = new RelativeLayout.LayoutParams((int)(332 * widthDiff), (int)(150 * heightDiff));
        lp6.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp6.addRule(RelativeLayout.LEFT_OF, done.getId());
        lp6.addRule(RelativeLayout.RIGHT_OF, itemlist.getId());
        lp6.setMargins((int)(40 *widthDiff), 0, (int)(40 *widthDiff),(int)(40 *heightDiff));
        goBack.setLayoutParams(lp6);

        load_more.setText("SCROLL TO LOAD MORE");
        load_more.setVisibility(View.INVISIBLE);
        load_more.setTextColor(Color.parseColor("#02CCA9"));
        load_more.setBackgroundResource(R.drawable.curve_button03);
        load_more.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        load_more.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        //Drawable img = getApplicationContext().getResources().getDrawable( R.mipmap.leftback );
        //img.setBounds( 0, 0, (int) (65 * widthDiff), (int) (50 * heightDiff) );
        //goBack.setCompoundDrawables(img, null, null, null);
        load_more.setPadding((int) (40 * widthDiff), 0, (int) (40 * widthDiff), 0);
        RelativeLayout.LayoutParams lp16 = new RelativeLayout.LayoutParams((int)(332 * widthDiff), (int)(150 * heightDiff));
        lp16.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp16.addRule(RelativeLayout.LEFT_OF, goBack.getId());
        lp16.addRule(RelativeLayout.RIGHT_OF, view00.getId());
        lp16.setMargins((int)(20 *widthDiff), 0, (int)(20 *widthDiff),(int)(40 *heightDiff));
        load_more.setLayoutParams(lp16);

        //label2 scrollview
        RelativeLayout.LayoutParams lp7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp7.addRule(RelativeLayout.BELOW, product.getId());
        lp7.addRule(RelativeLayout.ABOVE, done.getId());
        lp7.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp7.addRule(RelativeLayout.ALIGN_LEFT, product.getId());
        lp7.setMargins(0, (int)(20 *heightDiff), (int)(20 *widthDiff),(int)(40 *heightDiff));
        scrollView.setLayoutParams(lp7);

        //label2 Relative layout
        //relativeLayout1.setId(View.generateViewId());
        //scrollView.addView(relativeLayout1);

        //label2 textview
        //label2.setId(View.generateViewId());
        label2.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        label2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et " +
                "dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea " +
                "commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla" +
                " pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est" +
                " laborum.");
        label2.setTextSize((int) (getResources().getDimension(R.dimen.storypage_buttonTextSize) / getResources().getDisplayMetrics().density));
        label2.setTextColor(Color.parseColor("#ffffff"));


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SpecializationActivity.class);
                startActivity(i);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ThankyouActivity.class);
                startActivity(i);
            }
        });

    }

    public class CustomListAdapter extends BaseAdapter {

        Context context;
        private LayoutInflater inflator = null;
        ArrayList<HashMap<String, String>> List;
        private SparseBooleanArray selectedItems = new SparseBooleanArray();
        int clickPosition;
        int size = 0;

        public CustomListAdapter(Context context, ArrayList<HashMap<String, String>> List) {

            //activity = a;
            this.context = context;
            this.List = List;
            inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            size = List.size();
        }



        public class ViewHolder{
            ImageView categoryIcon;
            TextView count, categoryName;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return List.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return List.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return List.indexOf(getItem(position));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ViewHolder holder = new ViewHolder();
            try{
                convertView = inflator.inflate(R.layout.menu_category_tab, null);

                holder.categoryIcon = (ImageView)convertView.findViewById(R.id.imageView8);
                holder.count = (TextView)convertView.findViewById(R.id.counter);
                holder.categoryName = (TextView)convertView.findViewById(R.id.textView9);

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lp.setMargins(0,0,0,0);
                holder.categoryIcon.setLayoutParams(lp);

                holder.count.setText(List.get(position).get("itemCount"));
                holder.count.setBackgroundResource(R.drawable.circular_counter);
                holder.count.setPadding((int)(10 *widthDiff),(int)(10 *heightDiff),(int)(10 *widthDiff),(int)(10 *heightDiff));
                RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp0.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                lp0.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                lp0.setMargins(0,(int)(120 *heightDiff),(int)(40 *widthDiff),0);
                holder.count.setLayoutParams(lp0);

                holder.categoryName.setText(List.get(position).get("categoryLabelShort"));
                holder.categoryName.setTextSize((int) (getResources().getDimension(R.dimen.menupage_label5) / getResources().getDisplayMetrics().density));
                holder.categoryName.setTextColor(Color.parseColor("#ffffff"));
                holder.categoryName.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
                RelativeLayout.LayoutParams lp01 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                lp01.addRule(RelativeLayout.ALIGN_LEFT, holder.categoryIcon.getId());
                lp01.addRule(RelativeLayout.ALIGN_RIGHT, holder.categoryIcon.getId());
                lp01.addRule(RelativeLayout.ALIGN_BOTTOM, holder.categoryIcon.getId());
                lp01.setMargins(0,0,0,(int)(10 *heightDiff));
                holder.categoryName.setLayoutParams(lp01);

                if(clickPosition==position){
                    holder.categoryIcon.setBackgroundColor(Color.parseColor("#02CCA9"));
                    holder.categoryName.setBackgroundColor(Color.parseColor("#02CCA9"));
                }else{
                    holder.categoryIcon.setBackgroundColor(Color.parseColor("#298A79"));
                    holder.categoryName.setBackgroundColor(Color.parseColor("#298A79"));
                }

                if(clickPosition==0){
                    itemsLabel.setText(List.get(0).get("categoryLabel"));
                    item_image.setImageResource(Integer.parseInt(List.get(0).get("categoryIcon")));
                    //holder.count.setText(Integer.toString(categoriesItems.get(List.get(0).get("categoryLabel")).size()));
                    adapter02 = new CustomListAdapter02(getApplicationContext(), categoriesItems.get(List.get(0).get("categoryLabel")));
                    itemlist.setAdapter(adapter02);
                }

                holder.categoryIcon.setImageResource(Integer.parseInt(List.get(position).get("categoryIcon")));
                holder.categoryIcon.setPadding((int)(70 *widthDiff),(int)(40 *heightDiff),(int)(70 *widthDiff),(int)(40 *heightDiff));
                holder.categoryIcon.setTag(position);
                holder.categoryIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int post = (int) v.getTag();

                        //holder.categoryIcon.setBackgroundColor(Color.parseColor("#02CCA9"));
                        //Drawable t = holder.categoryIcon.getBackground();
                        if(post==1 || post==2){
                            load_more.setVisibility(View.VISIBLE);
                        }else{
                            load_more.setVisibility(View.INVISIBLE);
                        }
                        itemsLabel.setText(List.get(post).get("categoryLabel"));
                        item_image.setImageResource(Integer.parseInt(List.get(post).get("categoryIcon")));
                        //holder.count.setText(Integer.toString(categoriesItems.get(List.get(post).get("categoryLabel")).size()));
                        adapter02 = new CustomListAdapter02(getApplicationContext(), categoriesItems.get(List.get(post).get("categoryLabel")));
                        itemlist.setAdapter(adapter02);
                        clickPosition = post;
                        adapter01.notifyDataSetChanged();


                    }
                });

            }

            catch (Exception e) {
                e.printStackTrace();
            }

			/*Animation animation = AnimationUtils.loadAnimation(convertView.getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
			convertView.startAnimation(animation);
			lastPosition = position;*/

            return convertView;
        }
    }

    public class CustomListAdapter02 extends BaseAdapter {

        Context context;
        private LayoutInflater inflator = null;
        ArrayList<HashMap<String, String>> List;
        int clickPosition = 0;
        int lastPosition=0;


        public CustomListAdapter02(Context context, ArrayList<HashMap<String, String>> List) {

            //activity = a;
            this.context = context;
            this.List = List;
            inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        public class ViewHolder{
            TextView itemLabel, itemPrice;
            ImageView star, chily;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return List.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return List.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return List.indexOf(getItem(position));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ViewHolder holder = new ViewHolder();
            try{
                convertView = inflator.inflate(R.layout.category_item_tab, null);

                holder.itemLabel = (TextView)convertView.findViewById(R.id.textView7);
                holder.itemPrice = (TextView)convertView.findViewById(R.id.textView8);
                holder.star = (ImageView)convertView.findViewById(R.id.imageView11);
                holder.chily = (ImageView)convertView.findViewById(R.id.imageView10);

                RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams((int)(80 *widthDiff), (int)(80 *heightDiff));
                lp3.setMargins(0,(int)(60 *heightDiff),(int)(40 *widthDiff),0);
                lp3.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                lp3.addRule(RelativeLayout.ALIGN_RIGHT, holder.itemLabel.getId());
                holder.star.setLayoutParams(lp3);

               /* RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams((int)(80 *widthDiff), (int)(80 *heightDiff));
                lp4.setMargins((int)(20 *heightDiff),(int)(50 *heightDiff),(int)(20 *widthDiff),0);
                lp4.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                lp4.addRule(RelativeLayout.LEFT_OF, holder.star.getId());
                holder.chily.setLayoutParams(lp4);*/

                if(List.get(position).get("itemViggieIcon").equals("true")){
                    holder.chily.setVisibility(View.VISIBLE);
                }else{
                    holder.chily.setVisibility(View.INVISIBLE);
                }

                if(List.get(position).get("itemStarIcon").equals("true")){
                    holder.star.setVisibility(View.VISIBLE);
                    RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams((int)(80 *widthDiff), (int)(80 *heightDiff));
                    lp4.setMargins((int)(20 *heightDiff),(int)(60 *heightDiff),(int)(20 *widthDiff),0);
                    lp4.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    lp4.addRule(RelativeLayout.LEFT_OF, holder.star.getId());
                    holder.chily.setLayoutParams(lp4);

                }else  if(List.get(position).get("itemStarIcon").equals("false")){
                    holder.star.setVisibility(View.INVISIBLE);
                    RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams((int)(80 *widthDiff), (int)(80 *heightDiff));
                    lp4.setMargins((int)(20 *heightDiff),(int)(50 *heightDiff),(int)(20 *widthDiff),0);
                    lp4.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    lp4.addRule(RelativeLayout.ALIGN_RIGHT, holder.itemLabel.getId());
                    holder.chily.setLayoutParams(lp4);
                }

                holder.itemLabel.setTextSize((int) (getResources().getDimension(R.dimen.menupage_label5) / getResources().getDisplayMetrics().density));
                holder.itemLabel.setText(List.get(position).get("itemLabel"));
                holder.itemLabel.setTextColor(Color.parseColor("#ffffff"));
                holder.itemLabel.setBackgroundColor(Color.parseColor("#BCAA0D"));
                holder.itemLabel.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
                holder.itemLabel.setPadding((int)(60 *widthDiff),0,(int)(170 *widthDiff),0);
                RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)(200 *heightDiff));
                lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                //lp2.addRule(RelativeLayout.RIGHT_OF, categoryListView.getId());
                lp1.addRule(RelativeLayout.LEFT_OF, holder.itemPrice.getId());
                holder.itemLabel.setLayoutParams(lp1);

                holder.itemPrice.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
                holder.itemPrice.setTextSize((int) (getResources().getDimension(R.dimen.menupage_label5) / getResources().getDisplayMetrics().density));
                holder.itemPrice.setText(List.get(position).get("itemPrice"));
                holder.itemPrice.setTextColor(Color.parseColor("#ffffff"));
                holder.itemPrice.setBackgroundColor(Color.parseColor("#9B8613"));
                holder.itemPrice.setPadding((int)(80 *widthDiff),(int)(60 *heightDiff),(int)(80 *widthDiff),(int)(60 *heightDiff));
                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp2.addRule(RelativeLayout.ALIGN_BOTTOM, holder.itemLabel.getId());
                lp2.addRule(RelativeLayout.ALIGN_TOP, holder.itemLabel.getId());
                holder.itemPrice.setLayoutParams(lp2);

                if(clickPosition==position){
                    holder.itemLabel.setBackgroundColor(Color.parseColor("#9B8613"));
                }else{
                    holder.itemLabel.setBackgroundColor(Color.parseColor("#BCAA0D"));
                }

                if(clickPosition==0){

                    itemsLabel2.setText(List.get(0).get("itemLabel"));
                    imageLoader.displayImage(
                            (List.get(0).get("itemImage")), product,
                            options, imageListener);
                    //product.setImageResource(Integer.parseInt(List.get(0).get("itemImage")));
                    /*if(List.get(position).get("itemViggieIcon").equals("true")){
                        icon2.setVisibility(View.VISIBLE);
                    }else{
                        icon2.setVisibility(View.INVISIBLE);
                    }

                    if(List.get(position).get("itemStarIcon").equals("true")){
                        icon1.setVisibility(View.VISIBLE);
                    }else{
                        icon1.setVisibility(View.INVISIBLE);
                    }*/

                    if(!List.get(0).get("itemDescription").equals("")) {

                        label2.setText(List.get(0).get("itemDescription"));
                    }else{
                        label2.setText("");
                    }

                }

                holder.itemLabel.setTag(position);
                holder.itemLabel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int post = (int) v.getTag();
                        itemsLabel2.setText(List.get(post).get("itemLabel"));
                        imageLoader.displayImage(
                                (List.get(post).get("itemImage")), product,
                                options, imageListener);
                        //product.setImageResource(Integer.parseInt(List.get(post).get("itemImage")));
                        if(!List.get(post).get("itemDescription").equals("")) {
                            label2.setText(List.get(post).get("itemDescription"));
                        }else{

                            label2.setText("");
                        }//Toast.makeText(context, List.get(post).get("itemDescription"), Toast.LENGTH_SHORT).show();
                        clickPosition = post;
                        adapter02.notifyDataSetChanged();
                    }
                });

            }

            catch (Exception e) {
                e.printStackTrace();
            }

			Animation animation = AnimationUtils.loadAnimation(convertView.getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
			convertView.startAnimation(animation);
			lastPosition = position;

            return convertView;
        }
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

}
