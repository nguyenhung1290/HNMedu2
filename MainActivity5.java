package com.nguyenhung1290.hnmedu2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nguyenhung1290.hnmedu2.databinding.ActivityMain5Binding;

import java.io.File;
import java.io.IOException;


public class MainActivity5 extends AppCompatActivity {

    ActivityMain5Binding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    EditText getText;

    private AdView mAdView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7601848265461189/2612222211");
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest);

        binding.btnxemhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity5.this);
                progressDialog.setMessage("Đang lấy dữ liệu");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String imageID =binding.edtxemhinh.getText().toString();
                storageReference= FirebaseStorage.getInstance().getReference("5C/" + imageID + ".jpg");

                try {
                    File localfile =File.createTempFile("tempfile",".jpg");
                    storageReference.getFile(localfile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();

                                    Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    binding.imageView2.setImageBitmap(bitmap);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Toast.makeText(MainActivity5.this,"Lỗi lấy dữ liệu",Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });




    }




}
