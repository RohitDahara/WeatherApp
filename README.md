<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Weather App - README</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      margin: 2rem auto;
      max-width: 800px;
      line-height: 1.6;
      background-color: #f9f9f9;
      color: #333;
      padding: 0 1rem;
    }

    h1, h2, h3 {
      color: #0066cc;
    }

    img {
      max-width: 100%;
      height: auto;
      border-radius: 10px;
      margin-bottom: 1rem;
    }

    code {
      background-color: #eee;
      padding: 2px 6px;
      border-radius: 4px;
      font-family: monospace;
    }

    pre {
      background: #f4f4f4;
      padding: 1rem;
      overflow-x: auto;
      border-radius: 8px;
    }

    ul {
      margin-left: 1.5rem;
    }

    .screenshot {
      border: 1px solid #ddd;
      padding: 8px;
      background-color: #fff;
      margin-bottom: 2rem;
    }
  </style>
</head>
<body>

  <h1>📱 Weather App (Android)</h1>

  <p>
    A simple Android weather application that fetches current weather and predicts tomorrow’s temperature using a trained regression model. Built with Java, Material Design, OkHttp, and the OpenWeatherMap API.
  </p>

  <h2>🚀 Features</h2>
  <ul>
    <li>Search weather by city name</li>
    <li>Get temperature, condition, humidity, wind speed</li>
    <li>Display date and weather icon based on condition</li>
    <li>Predict tomorrow’s temperature using regression formula</li>
    <li>Geolocation-based weather (optional)</li>
  </ul>

  <h2>🧠 Model Logic (for prediction)</h2>
  <pre><code>predicted_temp = -2.57 * temp + 0.82 * humidity - 2.76 * windSpeed - 1.83 * pressure + 1921.24</code></pre>

  <h2>🛠️ Tech Stack</h2>
  <ul>
    <li><strong>Language:</strong> Java</li>
    <li><strong>UI:</strong> ConstraintLayout, Material Components</li>
    <li><strong>API:</strong> OpenWeatherMap (or replaceable)</li>
    <li><strong>HTTP:</strong> OkHttp</li>
  </ul>

  <h2>🖼️ Screenshots</h2>

  <div class="screenshot">
    <h3>🌤️ Home Screen</h3>
    <img src="screenshots/home_screen.png" alt="Home Screen">
  </div>

  <div class="screenshot">
    <h3>📊 Weather Forecast</h3>
    <img src="screenshots/weather_screen.png" alt="Weather Forecast">
  </div>

  <h2>📦 Installation</h2>
  <ol>
    <li>Clone the repository</li>
    <pre><code>git clone https://github.com/RohitDahara/WeatherApp.git</code></pre>

    <li>Open in Android Studio</li>
    <li>Replace the API key inside <code>MainActivity.java</code></li>
    <li>Run on emulator or physical device</li>
  </ol>

  <h2>🧪 Testing</h2>
  <p>Test prediction accuracy by comparing tomorrow's prediction with real-time data the next day.</p>

  <h2>🌐 API Alternatives</h2>
  <ul>
    <li><a href="https://www.weatherapi.com/" target="_blank">WeatherAPI.com</a></li>
    <li><a href="https://www.visualcrossing.com/weather-api" target="_blank">Visual Crossing</a></li>
    <li><a href="https://developer.accuweather.com/" target="_blank">AccuWeather API</a></li>
  </ul>

  <h2>🙌 Author</h2>
  <p>Created by <strong>Rohit Dahara</strong> | GitHub: <a href="https://github.com/RohitDahara" target="_blank">RohitDahara</a></p>

</body>
</html>
