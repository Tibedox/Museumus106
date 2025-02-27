package com.example.museumus106;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int imageResId = getIntent().getIntExtra("imageResId", 0);
        int textResId = getIntent().getIntExtra("textResId", 0);

        ImageView imageView = findViewById(R.id.detailImageView);
        TextView textView = findViewById(R.id.detailTextView);
        Button backButton = findViewById(R.id.backButton);

        imageView.setImageResource(imageResId);

        // Чтение содержимого текстового файла
        String fileContent = readTextFileFromRawResource(textResId);
        textView.setText(fileContent);

        backButton.setOnClickListener(v -> finish());
    }

    // Метод для чтения текстового файла из res/raw
    private String readTextFileFromRawResource(int resourceId) {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = getResources().openRawResource(resourceId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}