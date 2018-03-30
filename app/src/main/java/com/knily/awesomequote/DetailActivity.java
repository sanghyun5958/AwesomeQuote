package com.knily.awesomequote;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.knily.awesomequote.helper.Constants;
import com.knily.awesomequote.helper.DateTimeFormat;
import com.knily.awesomequote.helper.Utils;
import com.knily.awesomequote.models.AuthorInfo;
import com.knily.awesomequote.models.PostJSONData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.sephiroth.android.library.tooltip.Tooltip;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    private ImageView currentImage;
    private Button translator_btn,save_btn,crop_btn,share_btn;
    private TextView title,date,img_sign;
    private String content,subject,link,content_text="";
    private Button viewResponse;
    private int authorId;
    private View  image_text;
    private String[] tmp;
    private AdView mAdView;
    private TextView copyrighttxt;
    InterstitialAd main_inter;

    AdRequest adRequest;

    private Tooltip.ClosePolicy mClosePolicy = Tooltip.ClosePolicy.TOUCH_ANYWHERE_CONSUME;

    private PostJSONData postJSONData, notificationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        double random = Math.random();



        if(random > 0.80) {
            //Create the interstitial.
            main_inter = new InterstitialAd(this);
            main_inter.setAdUnitId("ca-app-pub-5388865560722868/2635764293");

            // Create ad request.
            adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("ca-app-pub-5388865560722868~4318302668")
                    .build();
            main_inter.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    main_inter.show();  // Load 된 광고를 출력
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {

                }

                @Override
                public void onAdClosed() {

                    // finish();

                }
            });
            AdRequest adRequest1 = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("ca-app-pub-5388865560722868~4318302668")
                    .build();
            main_inter.loadAd(adRequest1);
        }


        copyrighttxt = (TextView)findViewById(R.id.copyright_txt);
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        postJSONData = (PostJSONData) intent.getSerializableExtra(Constants.REFERENCE.KNILY_DATA);

        notificationData= (PostJSONData) intent.getSerializableExtra(Constants.REFERENCE.KNILY_DATA);


        // title on collapsing toolbar
        /*collapsing_container = (CollapsingToolbarLayout) findViewById(R.id.collapsing_container);
        collapsing_container.setTitle(postJSONData.getTitle().getRendered());
*/      configView();







        for(String key: getIntent().getExtras().keySet()){ //only null
            if (key.equals("title")){
                title.setText(getIntent().getExtras().getString(key));
            }



        }


        if (postJSONData != null) {
            String newDate= DateTimeFormat.DateTimeFormat(postJSONData.getDate());
            subject = postJSONData.getTitle().getRendered().replaceAll("(&#8230;)|(&#8216;)|(&#038;)|(<i>)|(</i>)|(&#8217;)|(&#8221;)|(&#8220;)|(&#8211;)", "\'");
            // 특수 문자를 -> "\'"로 변경
            link = postJSONData.getLink();
            authorId = postJSONData.getAuthor();

            try {
                Glide.with(getApplicationContext()).load(postJSONData.getFeaturedImgURL()).into(currentImage);

            } catch (Exception e) {
                e.printStackTrace();
            }





            //Request for Author images
        /*try {
            Glide.with(this).load("https://github.com/pramod-locio/Images/blob/master/2015-08-07_14.52.214-200x200.jpg").into(authorImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
            content=postJSONData.getContent().getRendered();

            content_text = Html.fromHtml(Html.fromHtml(content).toString()).toString();

          //  Log.d(TAG, "content_text1 = "+content);
          //  Log.d(TAG, "content_text2 = "+content_text);

            tmp = content_text.split(":::");

            title.setText(subject);

            Log.d(TAG, "content_text2 = "+tmp[0]+"+"+tmp[1]+"+"+tmp[2]);
            StringBuilder html = new StringBuilder();

            copyrighttxt.setText(tmp[2]);

        }
        else {

        }

        viewResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
            }
        });



        translator_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        translator_btn.setBackgroundResource(R.drawable.translator_round_clicked);
                        if (!v.isPressed()) {
                            v.setPressed(true);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                            translate(v);

                        translator_btn.setBackgroundResource(R.drawable.translator_round);
                        // perform action ON CLICK
                        break;
                }

                v.setPressed(false);
                return false;
            }
        });

        save_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        save_btn.setBackgroundResource(R.drawable.save_round_clicked);
                        if (!v.isPressed()) {
                            v.setPressed(true);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        try {
                            saveImage();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        save_btn.setBackgroundResource(R.drawable.save_round);
                        // perform action ON CLICK
                        break;
                }

                v.setPressed(false);
                return false;
            }
        });

        crop_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        crop_btn.setBackgroundResource(R.drawable.crop_round_clicked);
                        if (!v.isPressed()) {
                            v.setPressed(true);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                            crop();
                        crop_btn.setBackgroundResource(R.drawable.crop_round);
                        // perform action ON CLICK
                        break;
                }

                v.setPressed(false);
                return false;
            }
        });

        share_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        share_btn.setBackgroundResource(R.drawable.share_round_clicked);
                        if (!v.isPressed()) {
                            v.setPressed(true);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        shareImage();
                        share_btn.setBackgroundResource(R.drawable.share_round);
                        // perform action ON CLICK
                        break;
                }

                v.setPressed(false);
                return false;
            }
        });
        //Log.d("pppppppppp", String.valueOf(html));

        onNewIntent(getIntent());
    } // onCreate 끝










    protected boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    protected void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do your work
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }


    @Override
    public void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("NotificationMessage")) {
                setContentView(R.layout.activity_detail);
                // extract the extra-data in the Notification
                String msg = extras.getString("NotificationMessage");
                title.setText(msg);
            }
        }
    }


        @Override
    public void onResume() {
        super.onResume();
    }

    private void configView()
    {


        img_sign = (TextView)findViewById(R.id.img_sign);

        image_text = findViewById(R.id.image_text);
        translator_btn=(Button)findViewById(R.id.translator_btn);
        save_btn=(Button)findViewById(R.id.save_btn);
        crop_btn=(Button)findViewById(R.id.crop_button);
        share_btn=(Button)findViewById(R.id.share_btn);
        currentImage=(ImageView)findViewById(R.id.detail_imgView);
        title=(TextView)findViewById(R.id.detail_title);
      //  date=(TextView)findViewById(R.id.detail_date);
        viewResponse=(Button)findViewById(R.id.view_response);

    }

    //Go back most recent parent
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch(id)
        {
            case R.id.detail_add_comment:
                addComment();
                break;
            /*case R.id.detail_star:
                Toast.makeText(this, "favourite is Working.....", Toast.LENGTH_SHORT).show();
                break;*/
        }
        return super.onOptionsItemSelected(menuItem);
    }



    private void shareImage(){
        new SweetAlertDialog(this, SweetAlertDialog.EDIT_TEXT_TYPE)
                .setTitleText(getString(R.string.share_title))
                .setContentText(getString(R.string.share_sub))
                .showCancelButton(true)
                .setCancelText("Cancel")
                .setConfirmText("OK")
                .setEditTextConfirmClickListener(new SweetAlertDialog.OnEditTextSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog, String inputText) {


                            img_sign.setText(inputText);

                        sDialog.cancel();


                        View v = image_text;
                        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);

                        v.draw(canvas);


                        if (bitmap != null) {

                            try {
                                File bitmapFile = null;
                                File file = new File(DetailActivity.this.getExternalCacheDir(),"image_crop_sample");
                                if (!file.exists()) {
                                    file.mkdir();
                                }
                                bitmapFile = new File(file, "IMG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime()) + ".jpg");

                                BitmapUtils.writeBitmapToFile(bitmap, bitmapFile, 90);

                                Uri uriToImage = Uri.fromFile(bitmapFile);

                                Intent shareIntent = new Intent();
                                shareIntent.setAction(Intent.ACTION_SEND);
                                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                                shareIntent.setType("image/jpeg");
                                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.app_name)));


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }




                    }
                })
                .show();






    }

    private void translate(View v){

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Tooltip.make(
                DetailActivity.this,
                new Tooltip.Builder()
                        .anchor(v, Tooltip.Gravity.BOTTOM)
                        .closePolicy(mClosePolicy, 5000)
                        .text(tmp[1])
                        .withStyleId(R.style.ToolTipLayoutDefaultStyle_CustomFont)
                        .fitToScreen(true)
                        .activateDelay(2000)
                        .maxWidth(metrics.widthPixels / 2)
                        .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                        .build()
        ).show();
    }

    private void saveImage() throws IOException {

            if (checkPermission()) {
                GlideBitmapDrawable draw = (GlideBitmapDrawable) currentImage.getDrawable();
                Bitmap bitmap = draw.getBitmap();


                if (bitmap != null) {
                    try {
                        File bitmapFile = null;
                        File file = new File(Environment.getExternalStoragePublicDirectory("quote_image"), "");
                        if (!file.exists()) {
                            file.mkdir();
                        }

                        bitmapFile = new File(file, "IMG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime()) + ".jpg");

                        BitmapUtils.writeBitmapToFile(bitmap, bitmapFile, 90);

                        MediaScannerConnection.scanFile(DetailActivity.this, new String[]{bitmapFile.getAbsolutePath()}, null, new MediaScannerConnection.MediaScannerConnectionClient() {
                            @Override
                            public void onMediaScannerConnected() {

                            }

                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        new SweetAlertDialog(DetailActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText(getString(R.string.save_title))
                                                .setContentText(getString(R.string.save_sub))

                                                .show();
                                    }
                                });
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            } else {
                requestPermission();
            }




    }

    private void crop(){

        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.crop_title))
                .setCancelText(getString(R.string.crop_btn1))
                .setConfirmText(getString(R.string.crop_btn2))
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance, keep widget user state, reset them if you need


                        sDialog.cancel();



                        if (checkPermission()) {

                            Intent intent = new Intent(DetailActivity.this, CuttingActivity.class);
                            intent.putExtra(Constants.REFERENCE.KNILY_DATA, postJSONData);
                            intent.putExtra("position", 2);
                            Log.d("DATA", postJSONData + "");
                            startActivity(intent);
                        }else{
                            requestPermission();

                            Intent intent = new Intent(DetailActivity.this, CuttingActivity.class);
                            intent.putExtra(Constants.REFERENCE.KNILY_DATA, postJSONData);
                            intent.putExtra("position", 2);
                            Log.d("DATA", postJSONData + "");
                            startActivity(intent);
                        }

                        // or you can new a SweetAlertDialog to show
                               /* sDialog.dismiss();
                                new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .show();*/
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();

                        if (checkPermission()) {
                            Intent intent = new Intent(DetailActivity.this, CuttingActivity.class);
                            intent.putExtra(Constants.REFERENCE.KNILY_DATA, postJSONData);
                            intent.putExtra("position", 1);
                            Log.d("DATA", postJSONData + "");
                            startActivity(intent);
                        }else{
                            requestPermission();

                            Intent intent = new Intent(DetailActivity.this, CuttingActivity.class);
                            intent.putExtra(Constants.REFERENCE.KNILY_DATA, postJSONData);
                            intent.putExtra("position", 1);
                            Log.d("DATA", postJSONData + "");
                        }
                    }
                })
                .show();


    }



    //Add Comment implementation here
    private void addComment(){
        Intent intent=new Intent(DetailActivity.this, AddComment.class);
        intent.putExtra("url","https://www.knily.com/quote-images/"+postJSONData.getSlug());
        startActivity(intent);
    }

    //FloatingActionButton Action here


    //When back press video will be stopped in background of webView
    @Override
    public void onDestroy(){
        super.onDestroy();
     //   webView.destroy();
    }
}