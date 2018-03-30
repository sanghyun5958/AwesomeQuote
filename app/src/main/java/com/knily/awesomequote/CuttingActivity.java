package com.knily.awesomequote;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fenchtose.nocropper.CropperCallback;
import com.fenchtose.nocropper.CropperView;
import com.knily.awesomequote.helper.Constants;
import com.knily.awesomequote.helper.DateTimeFormat;
import com.knily.awesomequote.models.PostJSONData;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CuttingActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_PERMISSION = 22;
    private static final int REQUEST_GALLERY = 21;
    private static final String TAG = "CuttingActivity";

    private int position;

    @BindView(R.id.imageview)
    CropperView mImageView;

    private Bitmap mBitmap;
    private boolean isSnappedToCenter = false;
    private PostJSONData postJSONData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        postJSONData = (PostJSONData) intent.getSerializableExtra(Constants.REFERENCE.KNILY_DATA);
        position = (int) intent.getSerializableExtra("position");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
           // w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if(position == 1) {
                Log.d("position info :","position inside 1 :"+ position);
                setContentView(R.layout.activity_cutting_portrait);
            }else{
                Log.d("position info :","position inside 2 :"+ position);
                setContentView(R.layout.activity_cutting_portrait_2);
            }
        } else {
            Log.i(TAG, "Set landscape mode");
            setContentView(R.layout.activity_cutting_landscape);
        }
        ButterKnife.bind(this);
        mImageView.setDebug(true);



        Log.d("position info :","position info :"+ position);



        if (postJSONData != null) {

            Bitmap bit1 = null;



                Glide.with(this).load(postJSONData.getFeaturedImgURL()).asBitmap().
                        into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                mImageView.setImageBitmap(resource);
                            }
                        });




        }
    }

    public CuttingActivity(){

    }

    public CuttingActivity(int position){

        this.position = position;


    }

    @OnClick(R.id.image_button)
    public void onImageButtonClicked() {
        startGalleryIntent();
    }

    @OnClick(R.id.crop_button)
    public void onImageCropClicked() {
        cropImageAsync();
    }

    @OnClick(R.id.rotate_button)
    public void onImageRotateClicked() {
        rotateImage();
    }

    @OnClick(R.id.snap_button)
    public void onImageSnapClicked() {
        snapImage();
    }

    @OnClick(R.id.gesture_button)
    public void toggleGestures() {
        boolean enabled = mImageView.isGestureEnabled();
        enabled = !enabled;
        mImageView.setGestureEnabled(enabled);
        Toast.makeText(this, "Gesture " + (enabled ? "Enabled" : "Disabled"), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.replace_button)
    public void replaceImage() {
        if (mBitmap != null) {
            mBitmap = BitmapUtils.rotateBitmap(mBitmap, 180);
            mImageView.replaceBitmap(mBitmap);
        }
    }

    private void loadNewImage(String filePath) {
        Log.i(TAG, "load image: " + filePath);
        mBitmap = BitmapFactory.decodeFile(filePath);
        Log.i(TAG, "bitmap: " + mBitmap.getWidth() + " " + mBitmap.getHeight());

        int maxP = Math.max(mBitmap.getWidth(), mBitmap.getHeight());
        float scale1280 = (float)maxP / 1280;

        if (mImageView.getWidth() != 0) {
            mImageView.setMaxZoom(mImageView.getWidth() * 2 / 1280f);
        } else {

            ViewTreeObserver vto = mImageView.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                    mImageView.setMaxZoom(mImageView.getWidth() * 2 / 1280f);
                    return true;
                }
            });

        }

        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)(mBitmap.getWidth()/scale1280),
                (int)(mBitmap.getHeight()/scale1280), true);

        mImageView.setImageBitmap(mBitmap);

    }

    private void startGalleryIntent() {

        if (!hasGalleryPermission()) {
            askForGalleryPermission();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    private boolean hasGalleryPermission() {
        return ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void askForGalleryPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_READ_PERMISSION);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent) {
        super.onActivityResult(requestCode, responseCode, resultIntent);

        if (responseCode == RESULT_OK) {
            String absPath = BitmapUtils.getFilePathFromUri(this, resultIntent.getData());
            loadNewImage(absPath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_READ_PERMISSION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startGalleryIntent();
                return;
            }
        }

        Toast.makeText(this, "Gallery permission not granted", Toast.LENGTH_SHORT).show();
    }

    private void cropImageAsync() {
        mImageView.getCroppedBitmapAsync(new CropperCallback() {
            @Override
            public void onCropped(Bitmap bitmap) {
                if (bitmap != null) {

                    try {
                        File bitmapFile = null;
                        File file = new File(Environment.getExternalStoragePublicDirectory("image_crop_sample"), "");
                        if (!file.exists()) {
                            file.mkdir();
                        }
                        bitmapFile = new File(file, "IMG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime()) + ".jpg");

                        BitmapUtils.writeBitmapToFile(bitmap, bitmapFile, 90);

                        MediaScannerConnection.scanFile(CuttingActivity.this, new String[]{bitmapFile.getAbsolutePath()}, null, new MediaScannerConnection.MediaScannerConnectionClient() {
                            @Override
                            public void onMediaScannerConnected() {

                            }

                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CuttingActivity.this, "file saved", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                        Log.d("CuttingActivity","writeBitmaptoFile ="+bitmapFile.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onOutOfMemoryError() {

            }
        });
    }

    private void cropImage() {

        Bitmap bitmap = mImageView.getCroppedBitmap();

        if (bitmap != null) {

            try {
                BitmapUtils.writeBitmapToFile(bitmap, new File(Environment.getExternalStorageDirectory() + "/crop_test.jpg"), 90);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void rotateImage() {
        if (mBitmap == null) {
            Log.e(TAG, "bitmap is not loaded yet");
            return;
        }

        mBitmap = BitmapUtils.rotateBitmap(mBitmap, 90);
        mImageView.setImageBitmap(mBitmap);
    }

    private void snapImage() {
        if (isSnappedToCenter) {
            mImageView.cropToCenter();
        } else {
            mImageView.fitToCenter();
        }

        isSnappedToCenter = !isSnappedToCenter;
    }
}
