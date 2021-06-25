package com.dcht69.travelapp.thang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dcht69.travelapp.city.View.CityActivity;
import com.dcht69.travelapp.R;

public class LoginAct extends AppCompatActivity {
    EditText edtUserName, edtPassword;
    Button btnDangNhap, btnHuy;
    CheckBox chkLuuTT;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_m001_login);
        mapping();

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        edtUserName.setText(sharedPreferences.getString("userName", ""));
        edtPassword.setText(sharedPreferences.getString("password", ""));
        chkLuuTT.setChecked(sharedPreferences.getBoolean("isCheck", false));

    }

    public void login(View view) {
        String userName = edtUserName.getText().toString();
        String passWord = edtPassword.getText().toString();
        boolean isCheck = chkLuuTT.isChecked();
        if (userName.equals("admin") && passWord.equals("1234") || userName.equals("69dcht22") && passWord.equals("12345")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (isCheck) {
                editor.putString("userName", userName);
                editor.putString("password", passWord);
                editor.putBoolean("isCheck", isCheck);
                editor.commit();
            } else {
                editor.remove("userName");
                editor.remove("password");
                editor.remove("isCheck");
                editor.commit();

            }
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginAct.this, CityActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Mời bạn đăng kí!", Toast.LENGTH_LONG).show();
        }
    }

    public void signUp(View view) {
        startActivity(new Intent(LoginAct.this, SignUpAct.class));
    }

    private void mapping() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnHuy = findViewById(R.id.btnDangKi);
        chkLuuTT = findViewById(R.id.chkLuuTT);

    }

}