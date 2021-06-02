package com.nguyenhung1290.hnmedu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {

    Button btnDangKi2;
    EditText edtTenDanhNhap2, edtMatKhau2;

    FirebaseAuth mAuth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mAuth2 = FirebaseAuth.getInstance();

        Anhxa();
        btnDangKi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKi();
            }
        });
    }
    private void DangKi(){
        String email=edtTenDanhNhap2.getText().toString();
        String pass=edtMatKhau2.getText().toString();
        mAuth2.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity3.this,"Ðăng kí thành công",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity3.this,"Lỗi đăng kí",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    private void Anhxa(){
        btnDangKi2=(Button) findViewById(R.id.btndangki2);
        edtTenDanhNhap2=(EditText) findViewById(R.id.edttxttendangnhapdangki);
        edtMatKhau2=(EditText) findViewById(R.id.edttxtmatkhaudangki);
    }
}