package cite.app.levelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DatabaseBtn extends AppCompatActivity implements View.OnClickListener {

    public CardView db_easy, db_medium, db_hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_btn);

        db_easy = (CardView) findViewById(R.id.database_easy);
        db_medium = (CardView) findViewById(R.id.database_med);
        db_hard = (CardView) findViewById(R.id.database_hard);

        db_easy.setOnClickListener(this);
        db_medium.setOnClickListener(this);
        db_hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.database_easy:
                i = new Intent(this, databaseEasy.class);
                startActivity(i);
                break;

            case R.id.database_med:
                i = new Intent(this, databaseMed.class);
                startActivity(i);
                break;

            case R.id.database_hard:
                i = new Intent(this, databaseHard.class);
                startActivity(i);
                break;


        }
    }
}