package com.example.weatherappp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "9a49d58923a8ad1d7d669189f283a559"; 

    TextView temp, weather, city, humidity, windSpeed, day_date, tomorrowTemp;
    ImageView imageView;
    TextInputLayout cityInputLayout;
    ImageButton enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind views
        temp = findViewById(R.id.temp);
        weather = findViewById(R.id.weather);
        city = findViewById(R.id.city);
        humidity = findViewById(R.id.humidity);
        windSpeed = findViewById(R.id.windSpeed);
        day_date = findViewById(R.id.day_date);
        tomorrowTemp = findViewById(R.id.textView11);
        imageView = findViewById(R.id.imageView);
        cityInputLayout = findViewById(R.id.cityName);
        enterBtn = findViewById(R.id.enter);

        // Set current date
        String date = new SimpleDateFormat("EEEE, MMM dd", Locale.getDefault()).format(new Date());
        day_date.setText(date);

        // Fetch weather on button click
        enterBtn.setOnClickListener(v -> {
            String cityInput = cityInputLayout.getEditText().getText().toString().trim();
            if (!cityInput.isEmpty()) {
                fetchWeather(cityInput);
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchWeather(String cityName) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" +
                cityName + "&appid=" + API_KEY + "&units=standard";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) return;

                try {
                    JSONObject json = new JSONObject(response.body().string());
                    JSONObject main = json.getJSONObject("main");
                    JSONObject wind = json.getJSONObject("wind");
                    JSONArray weatherArray = json.getJSONArray("weather");
                    JSONObject weatherObj = weatherArray.getJSONObject(0);


                    double tempC = main.getDouble("temp") - 273.15;
                    double humidityy = main.getDouble("humidity");
                    double windSpeedMps = wind.getDouble("speed");
                    double windSpeedKmph = windSpeedMps * 3.6;
                    //double pressureHpa = main.getDouble("pressure");

                    String tempVal = String.format(Locale.getDefault(), "%.1f°C", tempC);
                    String humidityVal = String.format(Locale.getDefault(), "%.0f%%", humidityy);
                    String windVal = String.format(Locale.getDefault(), "%.1f km/h", windSpeedKmph);

                    String condition = weatherObj.getString("main");
                    String cityText = json.getString("name");
                    String tomorrow = (int)(main.getDouble("temp") - 273.15 + 1.5) + "°C";

                    new Handler(Looper.getMainLooper()).post(() -> {
                        temp.setText(tempVal);
                        weather.setText(condition);
                        city.setText(cityText);
                        humidity.setText(humidityVal);
                        windSpeed.setText(windVal);
                        tomorrowTemp.setText(tomorrow);
                        updateWeatherIcon(condition);
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateWeatherIcon(String condition) {
        condition = condition.toLowerCase();
        if (condition.contains("cloud")) {
            imageView.setImageResource(R.drawable.cloudy);
        } else if (condition.contains("clear")) {
            imageView.setImageResource(R.drawable.sunny);
        } else if (condition.contains("few clods")){
            imageView.setImageResource(R.drawable.cloudy_sunny); // fallback
        } else if (condition.contains("rain")) {
            imageView.setImageResource(R.drawable.rainy);
        } else if (condition.contains("snow")) {
            imageView.setImageResource(R.drawable.snowy);
        } else if (condition.contains("mist")){
            imageView.setImageResource(R.drawable.windy); // fallback
        } else if (condition.contains("thunderstorm")){
            imageView.setImageResource(R.drawable.storm); // fallback
        } else if (condition.contains("shower rain")){
            imageView.setImageResource(R.drawable.rain); // fallback
        } else {
            imageView.setImageResource(R.drawable.sunny); // fallback
        }
    }
}
