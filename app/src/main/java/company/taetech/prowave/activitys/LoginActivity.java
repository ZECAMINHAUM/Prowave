package company.taetech.prowave.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import company.taetech.prowave.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String email;
    private String senha;
    private EditText txtemail;
    private EditText txtsenha;
    private Button btentrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Init();

    }

    private void Init() {

        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("pt");
        onStart();

        txtemail = findViewById(R.id.edt_email);
        txtsenha = findViewById(R.id.edt_pass);
        btentrar = findViewById(R.id.bt_entrar);

        email = txtemail.getText().toString();
        senha = txtsenha.getText().toString();

        btentrar.setOnClickListener(this);

    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(txtemail.getText().toString(), txtsenha.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent Logado = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(Logado);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    txtemail.setText("");
                    txtsenha.setText("");
                }

                // [START_EXCLUDE]
                if (!task.isSuccessful()) {

                }
                // [END_EXCLUDE]
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = txtemail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            txtemail.setError("Campo Vazio.");
            valid = false;
        } else {
            txtemail.setError(null);
        }

        String password = txtsenha.getText().toString();
        if (TextUtils.isEmpty(password)) {
            txtsenha.setError("Campo Vazio.");
            valid = false;
        } else {
            txtsenha.setError(null);
        }

        return valid;
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent AuthLogin = new Intent(this, MainActivity.class);
            startActivity(AuthLogin);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }
    }



    @Override
    public void onClick(View v) {

        if (v == btentrar){
            signIn(email,senha);

        }

    }
}
