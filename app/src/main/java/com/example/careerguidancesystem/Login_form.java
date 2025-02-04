package com.example.careerguidancesystem;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_form extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        EditText email=findViewById(R.id.editTextTextEmailAddress);
        EditText password=findViewById(R.id.editTextTextPassword);
        Button login=findViewById(R.id.login_form_button);
        login.setOnClickListener(v -> {
            String mail=email.getText().toString();
            String pass=password.getText().toString();
            Intent intent=new Intent(Login_form.this,Homescreen.class);
            startActivity(intent);
        });


    }
}
