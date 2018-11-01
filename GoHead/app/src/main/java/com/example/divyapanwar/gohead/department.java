package com.example.divyapanwar.gohead;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class department extends AppCompatActivity {
Button upload,select;
FirebaseStorage storage;
FirebaseDatabase database;
Uri pdfUri;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        upload=(Button)findViewById(R.id.upload);
        select=(Button)findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(department.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectPdf();
                }
                else{
                    ActivityCompat.requestPermissions(department.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null){
                    Uploadfile(pdfUri);
                }
                else{
                    Toast.makeText(getApplicationContext(),"select a file",Toast.LENGTH_SHORT).show();
                }
            }
        });
       
    }

    private void Uploadfile(Uri pdfUri) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String filename=System.currentTimeMillis()+"";
        final StorageReference storageReference=storage.getReference();
        storageReference.child("Uploads").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url=storageReference.getDownloadUrl().toString();
                DatabaseReference databaseReference=database.getReference();
                databaseReference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"file uploaded successfully",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"file not successfully uploaded",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"file not successfully uploaded yeah",Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currenprogress=(int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currenprogress);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
        else{
            Toast.makeText(getApplicationContext(),"please provide permission",Toast.LENGTH_LONG).show();
        }
    }

    private void selectPdf() {
        Intent in=new Intent();
        in.setType("application/pdf");
        in.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(in,86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
        pdfUri=data.getData();
        }
        else{
            Toast.makeText(getApplicationContext(),"please select a file",Toast.LENGTH_SHORT).show();
        }
    }
}
