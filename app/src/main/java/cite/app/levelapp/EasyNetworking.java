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

public class EasyNetworking extends AppCompatActivity {

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
        setContentView ( R.layout.activity_easy_networking );

        list=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("NetEasy");


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
//        list.add(new Modelclass("Which address is used in an internet employing the TCP/IP protocols?","physical address and logical address","port address","specific address","all of the mentioned","all of the mentioned"));
//        list.add(new Modelclass("Which address identifies a process on a host?","physical address","logical address","port address","specific address","port address"));
//        list.add(new Modelclass("Transmission data rate is decided by","network layer","physical layer","data link layer","transport layer","physical layer"));
//        list.add(new Modelclass("When collection of various computers seems a single coherent system to its client, then it is called?","computer network","distributed system","both (a) and (b)","none of the mentioned","distributed system"));
 //       list.add(new Modelclass("Two devices are in network is?","a process in one device is able to exchange information with a process in another device","a process is running on both devices","PIDs of the processes running of different devices are same","none of the mentioned","a process is running on both devices"));
//        list.add(new Modelclass("Which one of the following computer network is built on the top of another network?","prior network","chief network","prime network","overlay network","overlay network"));
//        list.add(new Modelclass("In computer network nodes are?","the computer that originates the data","the computer that routes the data","the computer that terminates the data","all of the mentioned","all of the mentioned"));
//        list.add(new Modelclass("Communication channel is shared by all the machines on the network in?","unicast network","multicast network","broadcast network","none of the mentioned","broadcast network"));
//        list.add(new Modelclass("Bluetooth is an example of?","personal area network","local area network","virtual private network","none of the mentioned","personal area network"));
//        list.add(new Modelclass("Which of these is a standard interface for serial data transmission?","ASCII","RS232C","2","Centronics","RS232C"));

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
                Dialog dialog = new Dialog(EasyNetworking.this);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EasyNetworking.this,DatabaseBtn.class);
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

        Intent intent = new Intent(EasyNetworking.this,WonActivity.class);
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