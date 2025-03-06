package com.example.museumus106;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private GridLayout imageContainer;
    private Map<String, Integer> imageResources = new HashMap<>();
    private Map<String, Integer> textResources = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageContainer = findViewById(R.id.imageContainer);

        // Загрузка ресурсов
        loadResources();

        // Создание ImageView и добавление их в контейнер
        createImageViews();
    }

    private void loadResources() {
        // Загрузка изображений из res/drawable
        Field[] drawableFields = R.drawable.class.getFields();
        for (Field field : drawableFields) {
            String name = field.getName();
            if (name.startsWith("img")) {
                try {
                    int resId = field.getInt(null);
                    String key = name.split("_")[1];
                    imageResources.put(key, resId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Загрузка текстовых файлов из res/raw
        Field[] rawFields = R.raw.class.getFields();
        for (Field field : rawFields) {
            String name = field.getName();
            if (name.startsWith("txt")) {
                try {
                    int resId = field.getInt(null);
                    String key = name.split("_")[1];
                    textResources.put(key, resId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createImageViews() {
        int screenWidth = getScreenWidth();
        int imageSize = (screenWidth - dpToPx(16)) / 2; // Размер ImageView с учётом отступов

        int totalImages = imageResources.size();
        int rows = (int) Math.ceil(totalImages / 2.0); // Рассчитываем количество строк

        // Устанавливаем количество строк в GridLayout
        imageContainer.setRowCount(rows);

        int row = 0;
        int col = 0;

        for (Map.Entry<String, Integer> entry : imageResources.entrySet()) {
            String key = entry.getKey();
            int imageResId = entry.getValue();

            ImageView imageView = new ImageView(this);
            GridLayout.Spec rowSpec = GridLayout.spec(row);
            GridLayout.Spec colSpec = GridLayout.spec(col);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
            params.width = imageSize;
            params.height = imageSize;
            params.setMargins(dpToPx(2), dpToPx(2), dpToPx(2), dpToPx(2)); // Отступы между ImageView
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(imageResId);
            imageView.setOnClickListener(v -> openDetailActivity(imageResId, textResources.get(key)));

            imageContainer.addView(imageView);

            // Переход на следующую строку после 2 столбцов
            col++;
            if (col >= 2) {
                col = 0;
                row++;
            }
        }
    }

    private void openDetailActivity(int imageResId, int textResId) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("imageResId", imageResId);
        intent.putExtra("textResId", textResId);
        startActivity(intent);
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics()
        );
    }
}