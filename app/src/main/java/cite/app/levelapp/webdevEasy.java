package cite.app.levelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class webdevEasy extends AppCompatActivity {
    public static ArrayList<Modelclass> list;

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
        setContentView(R.layout.webdev_easy);
        list=new ArrayList<>();
        list.add(new Modelclass("Ano ang english ng kalabaw","Duck","Goat","Cow","Carabao","Carabao"));
        list.add(new Modelclass("Ano ang english ng Aso","Duck","Goat","Dog","Carabao","Dog"));
        list.add(new Modelclass("Ano ang english ng Pusa","Duck","Cat","Cow","Carabao","Cat"));
        list.add(new Modelclass("Ano ang english ng Kalapati","Duck","Goat","Cow","Dove","Dove"));
        list.add(new Modelclass("Ano ang english ng Baka","Duck","Goat","Cow","Carabao","Cow"));
        list.add(new Modelclass("Ano ang english ng Usa","Deer","Goat","Cow","Carabao","Deer"));
        list.add(new Modelclass("Ano ang english ng Kutsilyo","Duck","Goat","Knife","Carabao","Knife"));
        list.add(new Modelclass("Ano ang english ng Manok","Duck","Goat","Cow","Carabao","Chicken"));
        list.add(new Modelclass("Ano ang english ng Kambing","Duck","Goat","Cow","Carabao","Goat"));
        list.add(new Modelclass("Ano ang english ng Upuan","Duck","Goat","Chair","Carabao","Chair"));
        list.add(new Modelclass("Ano ang english ng Mesa","Duck","Goat","Table","Carabao","Table"));
        list.add(new Modelclass("Ano ang english ng Lapis","Pencil","Goat","Cow","Carabao","Pencil"));
        list.add(new Modelclass("Ano ang english ng Halaman","Duck","Plant","Cow","Carabao","Plant"));
        list.add(new Modelclass("Ano ang english ng Guro","Duck","Teacher","Cow","Carabao","Teacher"));
        list.add(new Modelclass("Ano ang english ng Selpon","Duck","Goat","Cellphone","Carabao","Cellphone"));
        list.add(new Modelclass("Ano ang english ng Damit","Duck","Shirt","Cow","Carabao","Shirt"));
        list.add(new Modelclass("Ano ang english ng Unan","Duck","Goat","Pillow","Carabao","Pillow"));
        list.add(new Modelclass("Ano ang english ng Pabango","Perfume","Goat","Cow","Carabao","Perfume"));
        list.add(new Modelclass("Ano ang english ng Kisame","Duck","Ceiling","Cow","Carabao","Ceiling"));
        list.add(new Modelclass("Ano ang english ng Sahig","Duck","Goat","Floor","Carabao","Floor"));

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
        Dialog dialog = new Dialog(webdevEasy.this);
        dialog.setContentView(R.layout.time_out_dialog);

        dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent = new Intent(webdevEasy.this,DatabaseBtn.class);
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

        Intent intent = new Intent(webdevEasy.this,WonActivity.class);
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