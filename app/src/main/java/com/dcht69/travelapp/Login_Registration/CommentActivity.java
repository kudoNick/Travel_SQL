package com.dcht69.travelapp.Login_Registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dcht69.travelapp.R;

import java.util.HashMap;
import java.util.Map;


public class CommentActivity extends AppCompatActivity {
    EditText edtGopY, edtSDT, edtEmail, edtSex;
    Button btnSend, btnCancel;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mapping();
    }

    private void mapping() {
        edtGopY = findViewById(R.id.edtGopY);
        edtSDT = findViewById(R.id.edtSDT);
        edtEmail = findViewById(R.id.edtEmail);
        edtSex = findViewById(R.id.edtSex);
        btnSend = findViewById(R.id.btnSend);
        btnCancel = findViewById(R.id.btnCancel);
    }

    public void Send(View view) {
        String gopY = edtGopY.getText().toString().trim();
        String sDT = edtSDT.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String sex = edtSex.getText().toString().trim();
        if (gopY.equals("") || sDT.matches("") || email.isEmpty() || sex.equals("")) {
            if (gopY.equals("")) {
                Toast.makeText(this, "Bạn phải nhập góp ý", Toast.LENGTH_SHORT).show();
                edtGopY.requestFocus();
            } else if (sDT.equals("")) {
                Toast.makeText(this, "Bạn phải nhập số điện thoại", Toast.LENGTH_SHORT).show();
                edtSDT.requestFocus();
            } else if (email.equals("")) {
                Toast.makeText(this, "Bạn phải nhập địa chỉ Email", Toast.LENGTH_SHORT).show();
                edtEmail.requestFocus();
            } else if (sex.equals("")) {
                Toast.makeText(this, "Bạn phải nhập giới tính", Toast.LENGTH_SHORT).show();
                edtSex.requestFocus();
            }
        } else {
            //viết hàm ở đây
            insertStudent("http://192.168.32.102:4430/69dcht22/account/insertComment.php");
        }
    }

    private void insertStudent(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(CommentActivity.this, response + ", Cảm ơn bạn đã góp ý với chúng tôi!!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CommentActivity.this, response + ", Lỗi , Bạn hay kiểm tra lại !!", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CommentActivity.this, error.toString() + ",Có lỗi xảy ra trong quá trình thêm thông tin", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("gopY", edtGopY.getText().toString());
                map.put("sDT", edtSDT.getText().toString());
                map.put("email", edtEmail.getText().toString());
                map.put("sex", edtSex.getText().toString());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void Cancel(View view) {
        finish();
    }
}