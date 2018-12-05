package td.fransiska.ti3a_17_19_tugasbesar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import td.fransiska.ti3a_17_19_tugasbesar.Helpers.DataHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText edtNama, edtEmail, edtPassword;
    Button btnRegister;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtNama = findViewById(R.id.editNama);
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.buttonRegister);

        dataHelper = new DataHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = edtNama.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if(nama.isEmpty() || nama.trim().isEmpty() || email.isEmpty() || email.trim().isEmpty() || password.isEmpty() || password.trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else if(!email.contains("@")){
                    Toast.makeText(RegisterActivity.this, "Email tidak valid", Toast.LENGTH_SHORT).show();
                }else{
                    dataHelper.addUser(nama, email, password);
                    finish();
                }
            }
        });
    }
}

