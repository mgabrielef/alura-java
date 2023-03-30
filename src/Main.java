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
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        var parser = new JsonParser();
        List<Map<String, String>> movie_list = parser.parse(body);

        for (Map<String, String> movie : movie_list){
            System.out.println("\u001b[1m Título: " + movie.get("title"));
            System.out.println("\u001b[1m Imagem: " + movie.get("image"));
            System.out.println("\u001b[1m Nota: " + movie.get("imDbRating"));
            System.out.println();
        }
    }
}