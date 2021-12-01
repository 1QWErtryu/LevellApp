package cite.app.levelapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
            public CardView webdev,im,database,networking,appdev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webdev = (CardView) findViewById(R.id.Webdev);
        im = (CardView) findViewById(R.id.Im);
        database = (CardView) findViewById(R.id.Database);
        networking = (CardView) findViewById(R.id.Networking);
        appdev = (CardView) findViewById(R.id.Mobdev);

        webdev.setOnClickListener(this);
        im.setOnClickListener(this);
        database.setOnClickListener(this);
        networking.setOnClickListener(this);
        appdev .setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){

            case R.id.Webdev:
                i = new Intent(this,WebdevBtn.class);
                startActivity(i);
                break;

            case R.id.Im:
                i = new Intent(this,imBtn.class);
                startActivity(i);
                break;

            case R.id.Database:
                i = new Intent(this,DatabaseBtn.class);
                startActivity(i);
                break;

            case R.id.Networking:
                i = new Intent(this,Networking.class);
                startActivity(i);
                break;

            case R.id.Mobdev:
                i = new Intent(this,MobileDevBtn.class);
                startActivity(i);
                break;

        }
    }
}