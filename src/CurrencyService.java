import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyService {

    private String apiKey;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public CurrencyService(String apiKey) {
        this.apiKey = apiKey;
    }

    public void getExchangeRateAndUpdateJSON(String fromCurrency) throws IOException {
        String endpoint = String.format("%s/latest/%s", apiKey, fromCurrency);
        URL url = new URL(BASE_URL + endpoint);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        System.out.println("Sending 'GET' request to URL: " + url);
        System.out.println("Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parseamos la respuesta JSON y la imprimimos línea por línea
        String jsonResponse = response.toString();
        System.out.println("Exchange Rate Data:");
        System.out.println(jsonResponse);

        // Guardar la respuesta JSON en un archivo local
        saveJSONToFile(jsonResponse, "exchange_rates.json");
    }

    private void saveJSONToFile(String json, String fileName) {
    String filePath = fileName;  // Assuming you want to save in the current directory
    try (FileWriter fileWriter = new FileWriter(filePath)) {
        fileWriter.write(json);
        System.out.println("JSON data saved to " + filePath);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

