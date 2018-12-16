package td.fransiska.ti3a_17_19_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import td.fransiska.ti3a_17_19_tugasbesar.Helpers.DataHelper;
import td.fransiska.ti3a_17_19_tugasbesar.Helpers.SessionManagement;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin, btnRegister;
    SessionManagement sessionManagement;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        dataHelper = new DataHelper(this);
        sessionManagement = new SessionManagement(this);
        if (sessionManagement.isLoggedIn()) {
            goToActivity();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if(email.matches("") || email.trim().isEmpty() || password.matches("") || password.trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email dan Password tidak boleh kosong/space", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if(dataHelper.autentikasi(email, password)) {
                        sessionManagement.createLoginSession(email, password);
                        goToActivity();
                    }else{
                        Toast.makeText(LoginActivity.this, "Email tidak terdaftar", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void goToActivity(){
        Intent mIntent = new Intent(getApplicationContext(),
                HomeActivity.class);
        startActivity(mIntent);
    }
}
