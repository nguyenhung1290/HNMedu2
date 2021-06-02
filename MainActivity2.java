package com.nguyenhung1290.hnmedu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

public class MainActivity2 extends AppCompatActivity {


    Button btnDangNhap, btnDangKi;
    EditText edtTenDangNhap, edtMatKhauDangNhap;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        StartAppAd.disableSplash();

        mAuth = FirebaseAuth.getInstance();

        Xaanh();

        btnDangKi=(Button)findViewById(R.id.btndangki);
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act3= new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(act3);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();

            }
        });

    }
    private void DangNhap(){
        String taikhoan=edtTenDangNhap.getText().toString();
        String Matkhau=edtMatKhauDangNhap.getText().toString();
        mAuth.signInWithEmailAndPassword(taikhoan, Matkhau)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity2.this,"Ðăng nhập thành công",Toast.LENGTH_SHORT).show();
                            Intent movetomh4= new Intent(MainActivity2.this,MainActivity4.class);
                            startActivity(movetomh4);
                        } else {
                            Toast.makeText(MainActivity2.this,"Lỗi đăng nhập",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void Xaanh(){
        btnDangNhap=(Button) findViewById(R.id.btndangnhap);
        edtTenDangNhap=(EditText) findViewById(R.id.edttendangnhap);
        edtMatKhauDangNhap=(EditText) findViewById(R.id.edtmatkhaudangnhap);
    }
}