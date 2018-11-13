package com.example.amir.recyclerviewitemclose;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Bitmap> imageList=new ArrayList<>();

    private   CustomAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.acivity_main);

        recyclerView=findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapter=new CustomAdapter(imageList);

        recyclerView.setAdapter(adapter);

        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isPermissionGranted()){

                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if(isAnyCameraAppInstalled(intent)){

                        startActivityForResult(intent,2);
                    }
                }else {
                    if(isUserCheckedNeverAskAgain()){

                        askPermission();

                    }else {

                        askPermission();
                    }

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode==RESULT_OK){

            Bitmap image=getTumbNail(data);

            adapter.update(image);
        }
    }

    private void askPermission() {

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

    }

    public boolean isPermissionGranted() {

        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED;
    }

    public boolean isUserCheckedNeverAskAgain() {

        return !ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public boolean isAnyCameraAppInstalled(Intent intent) {

        return intent.resolveActivity(getPackageManager())!=null;
    }

    public Bitmap getTumbNail(Intent data) {

        Bundle bundle=data.getExtras();

        Bitmap bitmap= (Bitmap) bundle.get("data");

        return bitmap;
    }
}
