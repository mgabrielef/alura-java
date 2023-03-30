import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
                //"https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        var parser = new JsonParser();
        List<Map<String, String>> movie_list = parser.parse(body);

        for (Map<String, String> movie : movie_list){
            System.out.println("\u001b[1m Título: \u001b[0m" + movie.get("title"));
            System.out.println("\u001b[1m Imagem: \u001b[0m" + movie.get("image"));
            System.out.println("\u001b[38;2;255;255;255m\u001b[48;2;42;122;228m Nota: " + movie.get("imDbRating") + "\u001b[m");
            double rating = Double.parseDouble(movie.get("imDbRating"))/2;
            for (int i = 0; i <= rating; i++) {
                System.out.print("\u2B50");
            };

            System.out.println();
        }
    }
}