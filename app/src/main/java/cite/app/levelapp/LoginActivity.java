package cite.app.levelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email_et);
        password = findViewById(R.id.login_password_et);
        fAuth = FirebaseAuth.getInstance();


    }

    //login user
    public void ClickSignInBtn(View view) {

        //content login here
        String emailLogin = email.getText().toString();
        String passLogin = password.getText().toString();

        if (TextUtils.isEmpty(emailLogin)) {
            email.setError("Email is Required");
            return;
        } else if (TextUtils.isEmpty(passLogin)) {
            password.setError("Password is Required");
            return;
        } else {

            fAuth.signInWithEmailAndPassword(emailLogin, passLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //if login is success then go to mainactivity
                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {

                        Toast.makeText(LoginActivity.this, "Error Occurred" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

//proceed to sign up activity
    public void ClickSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

//forgotten password
    public void ClickForgot(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);

    }
}