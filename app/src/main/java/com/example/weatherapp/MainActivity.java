package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText txtCity;
    TextView dt,city1,des,tem,humid,feels;
    String url;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.btnSubmit);
        txtCity = findViewById(R.id.txtCity);
        feels = findViewById(R.id.feels);
        humid = findViewById(R.id.humid);
        dt = findViewById(R.id.dt);
        city1 = findViewById(R.id.city1);
        des = findViewById(R.id.des);
        tem = findViewById(R.id.temp);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = txtCity.getText().toString();
                url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=9a49d58923a8ad1d7d669189f283a559";

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    // Get current date and time
                                    LocalDateTime now = LocalDateTime.now();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE HH:mm");
                                    String formattedDateTime = now.format(formatter);

                                    // Parse the JSON data
                                    JSONObject main = response.getJSONObject("main");
                                    double temp = main.getDouble("temp") - 273.15; // Convert from Kelvin to Celsius
                                    double feelsLike = main.getDouble("feels_like") - 273.15;
                                    int humidity = main.getInt("humidity");

                                    JSONObject wind = response.getJSONObject("wind");
                                    double windSpeed = wind.getDouble("speed");

                                    JSONArray weatherArray = response.getJSONArray("weather");
                                    JSONObject weather = weatherArray.getJSONObject(0);
                                    String description = weather.getString("description");

                                    String cityName = response.getString("name");

                                    // Display the data
                                    dt.setText(String.format(formattedDateTime));
                                    city1.setText(String.format(cityName));
                                    tem.setText(String.format("%.2f°C",temp));
                                    des.setText(String.format(description));
                                    humid.setText(String.format("Humidity: %d%%",humidity));
                                    feels.setText(String.format("Wind Speed: %.2f m/s",windSpeed));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(jsonObjectRequest);
            }
        });
    }
}
