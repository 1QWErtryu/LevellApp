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

public class HardNetworking extends AppCompatActivity {

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
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_hard_networking );

        list=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("NetMed");


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
   //             list.add(new Modelclass("Which of the following statements could be valid with respect to the ICMP (Internet Control Message Protocol)?","It reports all errors which occur during transmission.","A redirect message is used when a router notices that a packet seems to have been routed wrongly.","It informs routers when an incorrect path has been taken.","The destination unreachable type message is used when a router cannot locate the destination.","A redirect message is used when a router notices that a packet seems to have been routed wrongly."));
  //      list.add(new Modelclass("The IP network 192.168.50.0 is to be divided into 10 equal sized subnets. Which of the following subnet masks can be used for the above requirement?","255.243.240","255.255.0.0","255.255.0","255.255.255","255.255.0"));
 //       list.add(new Modelclass("When the mail server sends mail to other mail servers it becomes?","SMTP client","SMTP server","Peer","Master","SMTP client"));
 //       list.add(new Modelclass("The length of an IPv6 address is?","32 bits","64 bits","128 bits","256 bits","128 bits"));
//        list.add(new Modelclass("Which of the above is consider as (a) signal transmission medium is data communications except?","Twisted pair cables","Microwaves and Satellite Signals","Repeaters","Fiber optics","Repeaters"));
//        list.add(new Modelclass("Which of the following address belongs class A?","121.12.12.248","130.12.12.248","128.12.12.248","129.12.12.248","121.12.12.248"));
//        list.add(new Modelclass("Which of the following is correct IPv4 address?","124.201.3.1.52","128.64.0.0","300.142.210.64","01.200.128.123","128.64.0.0"));
//        list.add(new Modelclass("Which of the following IP addresses can be used as (a) loop-back addresses?","0.0.0.0","127.0.0.1","255.255.255.255","0.255.255.255","127.0.0.1"));
//        list.add(new Modelclass("The term WAN stands for?","Wide Area Net","Wide Access Network","Wide Area Network","Wide Access Net","Wide Area Network"));
//        list.add(new Modelclass("Which of the following cannot be used as a medium for 802.3 ethernet?","A thin coaxial cable","A twisted pair cable","A microwave link","A twisted pair cable","A microwave link"));

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
                Dialog dialog = new Dialog(HardNetworking.this);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HardNetworking.this,DatabaseBtn.class);
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

        Intent intent = new Intent(HardNetworking.this,WonActivity.class);
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