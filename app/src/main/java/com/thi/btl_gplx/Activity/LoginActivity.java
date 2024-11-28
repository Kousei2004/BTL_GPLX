package com.thi.btl_gplx.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.textfield.TextInputEditText;
import com.thi.btl_gplx.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        // Khởi tạo Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        usernameEditText = findViewById(R.id.username_log);
        passwordEditText = findViewById(R.id.pass_log);

        // Thiết lập sự kiện click cho nút Đăng Nhập
        findViewById(R.id.btn_dangnhap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Thiết lập sự kiện click cho nút Đăng Ký
        findViewById(R.id.btn_dangkyngay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển tới hoạt động Đăng Ký
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // Thiết lập sự kiện click cho nút Quên Mật Khẩu
        findViewById(R.id.btn_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgotPasswordDialog();
            }
        });
    }

    private void loginUser() {
        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy tham chiếu đến nút đăng nhập và vô hiệu hóa nó
        Button loginButton = findViewById(R.id.btn_dangnhap);
        loginButton.setEnabled(false);  // Vô hiệu hóa nút đăng nhập

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Kích hoạt lại nút đăng nhập khi quá trình hoàn tất
                        loginButton.setEnabled(true);

                        if (task.isSuccessful()) {
                            // Đăng nhập thành công
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            // Xử lý lỗi
                            Exception exception = task.getException();
                            if (exception != null) {
                                Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Xác thực thất bại.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    private void showForgotPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_forgot_password, null);

        // Tạo dialog từ builder
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent_background); // Đặt nền trong suốt

        // Khai báo các thành phần trong dialog
        EditText emailBox = dialogView.findViewById(R.id.emailBox);
        Button btnReset = dialogView.findViewById(R.id.btnReset);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        dialog.setView(dialogView); // Đặt view cho dialog

        // Thiết lập nút hủy
        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

        // Thiết lập nút xác nhận
        btnReset.setOnClickListener(v -> {
            String email = emailBox.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            } else {
                resetPassword(email);
                dialog.dismiss(); // Đóng dialog sau khi gửi yêu cầu khôi phục
            }
        });

        // Hiển thị dialog
        dialog.show();
    }


    private void resetPassword(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Email khôi phục mật khẩu đã được gửi", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Có lỗi xảy ra: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
