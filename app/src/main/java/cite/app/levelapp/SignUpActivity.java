package cite.app.levelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText emailSignUp,passSignUp,confirmPass;
    TextView loginLinkText;
    Button signUpBtn;
    private LinearLayout layout;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailSignUp = findViewById(R.id.signUp_email_et);
        passSignUp = findViewById(R.id.signUp_password_et);
        confirmPass = findViewById(R.id.signUp_confrim_password_et);
        fAuth = FirebaseAuth.getInstance();




    }

    public void HaveAnAccount(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    public void ClickSignUpBtn(View view) {
        String email = emailSignUp.getText().toString();
        String password = passSignUp.getText().toString();
        String confirmPassword = confirmPass.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailSignUp.setError("Email is Required");
            return;
        }else if (TextUtils.isEmpty(password)){
            passSignUp.setError("Password is Required");
            return;
        } else if (password.length() < 6){
            passSignUp.setError("Too short Password!");
            return;
        }else if (TextUtils.isEmpty(confirmPassword)){
            passSignUp.setError("Please Confirm Password");
            return;
        }else{


            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this,"Sign Up is Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    }else{
                        Toast.makeText(SignUpActivity.this,"Error Occurred"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }


    }
}