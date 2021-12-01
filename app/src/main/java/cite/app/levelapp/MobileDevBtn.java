package cite.app.levelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MobileDevBtn extends AppCompatActivity implements View.OnClickListener{

    public CardView db_easy, db_medium, db_hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_dev_btn);

        db_easy = (CardView) findViewById(R.id.mobdev_easy);
        db_medium = (CardView) findViewById(R.id.mobdev_med);
        db_hard = (CardView) findViewById(R.id.mobdev_hard);

        db_easy.setOnClickListener(this);
        db_medium.setOnClickListener(this);
        db_hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.mobdev_easy:
                i = new Intent(this, EasyMobiledev.class);
                startActivity(i);
                break;

            case R.id.mobdev_med:
                i = new Intent(this, MedMobiledev.class);
                startActivity(i);
                break;

            case R.id.mobdev_hard:
                i = new Intent(this, HardMobiledev.class);
                startActivity(i);
                break;


        }
    }
}