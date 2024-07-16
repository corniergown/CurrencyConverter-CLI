import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CurrencyConverterApp {

    private static final String JSON_FILE_PATH = "exchange_rates.json";

    public static void main(String[] args) {
        String apiKey = "b8efcc01a05ab7f4f15af7ac";
        CurrencyService currencyService = new CurrencyService(apiKey);
        Scanner scanner = new Scanner(System.in);

        System.out.println("CONSULTOR DE CAMBIO DE DIVISAS\n");
        System.out.println("1. Consultar tasas de cambio desde exchange_rates.json");
        System.out.println("2. Actualizar y guardar nuevas tasas de cambio");
        System.out.println("3. Salir");

        boolean exit = false;

        while (!exit) {
            System.out.print("\nSeleccione una opción (1, 2, o 3): ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("\nConsultando tasas de cambio desde " + JSON_FILE_PATH);
                    try {
                        String jsonData = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
                        System.out.println("Datos de tasas de cambio:\n" + jsonData);
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo JSON: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("\nActualizando y guardando nuevas tasas de cambio...");
                    try {
                        currencyService.getExchangeRateAndUpdateJSON("USD");
                    } catch (IOException e) {
                        System.out.println("Error al actualizar las tasas de cambio: " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("\nSaliendo del programa.");
                    exit = true;
                    break;
                default:
                    System.out.println("\nOpción inválida. Seleccione 1, 2 o 3.");
                    break;
            }
        }

        scanner.close();
    }
}

