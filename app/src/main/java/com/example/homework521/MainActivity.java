package com.example.homework521;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnRegistration;
    private FileHelper fileHelper;
    private EditText etLogin;
    private EditText etPass;

    public static final String FILE_LOGIN = "reg_login.txt";
    public static final String FILE_PASS = "reg_pass.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fileHelper = new FileHelper(MainActivity.this);

        //авторизация
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString();
                String pass = etPass.getText().toString();

                if (!login.equals("") && !pass.equals("")) {
                    String curLogin = fileHelper.getValue(FILE_LOGIN);
                    String curPass = fileHelper.getValue(FILE_PASS);
                    if (login.equals(curLogin) && pass.equals(curPass)) {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_signin), Toast.LENGTH_LONG).show();
                        etLogin.setText("");
                        etPass.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_error_signin), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_error_edittext), Toast.LENGTH_LONG).show();
                }

            }
        });

        //регистрация
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString();
                String pass = etPass.getText().toString();

                if (!login.equals("") && !pass.equals("")) {
                    fileHelper.updateValue(FILE_LOGIN, login);
                    fileHelper.updateValue(FILE_PASS, pass);
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_registration), Toast.LENGTH_LONG).show();
                    etLogin.setText("");
                    etPass.setText("");
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_error_edittext), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegistration = findViewById(R.id.btn_registration);
        etLogin = findViewById(R.id.et_login);
        etPass = findViewById(R.id.et_pass);
    }
}
