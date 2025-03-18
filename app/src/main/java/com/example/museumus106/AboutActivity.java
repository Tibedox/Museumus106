package com.example.museumus106;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.util.Scanner;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Находим TextView для отображения текста
        TextView contentTextView = findViewById(R.id.contentTextView);

        // Загружаем текст из файла raw/o_museume.txt
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.o_museume);
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String content = scanner.hasNext() ? scanner.next() : "";
            contentTextView.setText(content);
            scanner.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            contentTextView.setText("Ошибка загрузки текста.");
        }
    }
    // Обработчик нажатия на текстовую ссылку "← Назад"
    public void onBackClick(View view) {
        // Переход на MenuActivity
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish(); // Закрываем текущую активность (опционально)
    }
}