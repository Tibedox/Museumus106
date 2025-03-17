package com.example.museumus106;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    // Обработчик нажатия на текстовую ссылку "← Назад"
    public void onBackClick(View view) {
        // Переход на MenuActivity
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish(); // Закрываем текущую активность (опционально)
    }
}