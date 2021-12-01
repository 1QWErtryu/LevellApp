package cite.app.levelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class databaseMed extends AppCompatActivity {

    public static ArrayList<Modelclass> list;
    DatabaseReference databaseReference;


    CountDownTimer countDownTimer;
    int timerValue=20;
    RoundedHorizontalProgressBar progressBar;

    List<Modelclass> allQuestionsList;
    Modelclass modelClass;
    int index=0;
    TextView card_question,optiona,optionb,optionc,optiond;
    CardView cardOA,cardOB,cardOC,cardOD;
    int correctCount= 0;
    int wrongCount= 0;
    LinearLayout nxtBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_med);


        list=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseMed");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Modelclass modelclass = dataSnapshot.getValue(Modelclass.class);
                    list.add(modelclass);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }
        });


   //     list.add(new Modelclass("Which of the following is known as a set of entities of the same type that share same properties, or attributes?","Relation set","Relationship set","Entity set","Entity Relation model","Entity set"));
     //   list.add(new Modelclass("What is information about data called?","Hyper Data","Tera Data","Meta Data","Relations","Meta data"));
       // list.add(new Modelclass("What does an RDBMS consist of?","Collection of Records","Collection of Keys","Collection of Tables","Collection of Fields","Collection of Tables"));
        //list.add(new Modelclass("Which of the following is a component of the DBMS?","Data","Data Management","Data Languages","All of the Above","All of the above"));
//        list.add(new Modelclass("Which of the following is a function of the DBMS?","Storing data","Providing multi-users access control","Data Integrity","All of the above","All of the above"));
 //       list.add(new Modelclass("Which of the following is not a function of the database?","Managing stored data","Manipulating data","Security for stored data","Analyzing code","Analyzing code"));
  //      list.add(new Modelclass("Which of the following is a feature of the database?","No-backup for the data stored","User-interface provided","Lack of Authentication","Store data in multiple locations","User-interface provided"));
 //       list.add(new Modelclass("A collection of interrelated records is called a","Database","Spreadsheet","Management information system","Text file","Database"));
  //      list.add(new Modelclass("External database is","Database created using DBMS package","Database created in MS-Word","Database created in EXCEL","All of the Above","Database created using DBMS package"));
 //       list.add(new Modelclass("A report generator is used to","Update files","Print files on paper","Data entry","None of the Above","None of the above"));

        Hooks();
        allQuestionsList = list;
        Collections.shuffle(allQuestionsList);
        modelClass = list.get(index);

        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));

        nxtBtn.setClickable(false);

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(databaseMed.this);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(databaseMed.this,DatabaseBtn.class);
                        startActivity(intent);
                    }
                });

                dialog.show();
            }
        }.start();

        setAllData();
    }

    private void setAllData() {
        card_question.setText(modelClass.getQuestion());
        optiona.setText(modelClass.getoA());
        optionb.setText(modelClass.getoB());
        optionc.setText(modelClass.getoC());
        optiond.setText(modelClass.getoD());
        timerValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void Hooks() {
        progressBar=findViewById(R.id.quiz_timer);
        card_question=findViewById(R.id.card_question);
        optiona=findViewById(R.id.card_optiona);
        optionb=findViewById(R.id.card_optionb);
        optionc=findViewById(R.id.card_optionc);
        optiond=findViewById(R.id.card_optiond);

        cardOA=findViewById(R.id.cardOA);
        cardOB=findViewById(R.id.cardOB);
        cardOC=findViewById(R.id.cardOC);
        cardOD=findViewById(R.id.cardOD);

        nxtBtn=findViewById(R.id.nxtBtn);

    }

    public void Correct(CardView cardView){

        cardView.setBackgroundColor(getResources().getColor(R.color.green));

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                modelClass=list.get(index);
                resetColor();
                setAllData();
                enableButton();
            }
        });


    }

    public void Wrong(CardView cardOA){

        cardOA.setBackgroundColor(getResources().getColor(R.color.red));

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if (index < list.size() - 1){
                    index++;
                    modelClass = list.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                }else {
                    GameWon();
                }
            }
        });


    }

    private void GameWon() {

        Intent intent = new Intent(databaseMed.this,WonActivity.class);
        intent.putExtra("Correct",correctCount);
        intent.putExtra("Wrong",wrongCount);
        startActivity(intent);
    }

    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }

    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }



    public void OptionAClick(View view){
        disableButton();
        nxtBtn.setClickable(true);
        if(modelClass.getoA().equals(modelClass.getAns())){
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size() - 1){
                Correct(cardOA);
            }else {
                GameWon();
            }
        }else {
            Wrong(cardOA);
        }
    }

    public void OptionBClick(View view){
        disableButton();
        nxtBtn.setClickable(true);
        if(modelClass.getoB().equals(modelClass.getAns())){
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size() - 1){
                Correct(cardOB);
            }else {
                GameWon();
            }
        }else {
            Wrong(cardOB);
        }
    }

    public void OptionCClick(View view){
        disableButton();
        nxtBtn.setClickable(true);
        if(modelClass.getoC().equals(modelClass.getAns())){
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size() - 1){
                Correct(cardOC);
            }else {
                GameWon();
            }
        }else {
            Wrong(cardOC);
        }
    }

    public void OptionDClick(View view){
        disableButton();
        nxtBtn.setClickable(true);
        if(modelClass.getoD().equals(modelClass.getAns())){
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size() - 1){
                Correct(cardOD);
            }else {
                GameWon();
            }
        }else {
            Wrong(cardOD);
        }
    }
}
