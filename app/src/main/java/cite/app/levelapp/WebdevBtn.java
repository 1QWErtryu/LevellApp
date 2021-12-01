package cite.app.levelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WebdevBtn extends AppCompatActivity implements View.OnClickListener {
    public CardView db_easy, db_medium, db_hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webdev_btn);
        db_easy = (CardView) findViewById(R.id.web_easy);
        db_medium = (CardView) findViewById(R.id.web_med);
        db_hard = (CardView) findViewById(R.id.web_hard);

        db_easy.setOnClickListener(this);
        db_medium.setOnClickListener(this);
        db_hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.web_easy:
                i = new Intent(this, EasyWebDev.class);
                startActivity(i);
                break;

            case R.id.web_med:
                i = new Intent(this, MedWebDev.class);
                startActivity(i);
                break;

            case R.id.web_hard:
                i = new Intent(this, HardWebDev.class);
                startActivity(i);
                break;


        }
    }
}