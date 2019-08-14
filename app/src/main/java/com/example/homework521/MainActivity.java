package com.example.homework521;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin;
    private Button btnRegistration;
    private FileHelper filehelper;

    public static final String FILE_LOGIN = "reg_login.txt";
    public static final String FILE_PASS = "reg_pass.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        filehelper = new FileHelper(MainActivity.this);
        filehelper.createFile(FILE_LOGIN);
        filehelper.createFile(FILE_PASS);
    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegistration = findViewById(R.id.btn_registration);
        btnLogin.setOnClickListener(this);
        btnRegistration.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText etLogin = findViewById(R.id.et_login);
        EditText etPass = findViewById(R.id.et_pass);
        String login = etLogin.getText().toString();
        String pass = etPass.getText().toString();

        if (!login.equals("") && !pass.equals("")) {
            switch (view.getId()) {
                case R.id.btn_login:
                    String curlogin = filehelper.getValue(FILE_LOGIN);
                    String curpass = filehelper.getValue(FILE_PASS);
                    if (login.equals(curlogin) && pass.equals(curpass)) {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_signin), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_error_signin), Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btn_registration:
                    filehelper.updateValue(FILE_LOGIN, login);
                    filehelper.updateValue(FILE_PASS, pass);
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_registration), Toast.LENGTH_LONG).show();
                    break;
            }
            etLogin.setText("");
            etPass.setText("");
        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_error_edittext), Toast.LENGTH_LONG).show();
        }
    }
}
