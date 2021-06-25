package com.dcht69.travelapp.thang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dcht69.travelapp.R;

public class SignUpAct extends AppCompatActivity {
    EditText edtUserName, edtPassword, edtRePassword;
    Button btnDangKi;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_m003_signup);
        mapping();

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        edtUserName.setText(sharedPreferences.getString("userName", ""));
        edtPassword.setText(sharedPreferences.getString("password", ""));
        edtRePassword.setText(sharedPreferences.getString("repassword", ""));
    }


    public void signUp(View view) {
        String userName = edtUserName.getText().toString();
        String passWord = edtPassword.getText().toString();
        String rePassWord = edtRePassword.getText().toString();
        if (userName.equals("69dcht22") && passWord.equals("12345") && rePassWord.equals("12345")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName", userName);
            editor.putString("password", passWord);
            editor.putString("repassword", passWord);
            editor.commit();
            Toast.makeText(this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUpAct.this,LoginAct.class));
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("userName");
            editor.remove("password");
            editor.remove("repassword");
            editor.commit();
            Toast.makeText(this, "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
        }

    }

    private void mapping() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);
        btnDangKi = findViewById(R.id.btnDangKi);

    }

}