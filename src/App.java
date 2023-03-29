import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexaão HTTP e buscar os top 250 filmes
        String imdbkey = System.getenv("IMDB_API_KEY");
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/" + imdbkey;
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        

        // pegar só os dados que interessam (Título, poste, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        

        // exibir e manipular os dados da forma necessária

        for (int i = 0; i < 3; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[1m\u001b[38;2;184;184;184m\u001b[32;42;13;188;121mTítulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[1m\u001b[38;2;184;184;184m\u001b[32;42;13;188;121mCapa do Filme:\u001b[m " + filme.get("image"));
            System.out.println("Classificação: \u001b[3m" + filme.get("imDbRating") + "\u001b[m");
            
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;
            
            for (int n = 1; n <= numeroEstrelinhas; n++) {
                System.out.print("\u001b[32;42;13;188;121m\u001b[32;42;13;188;121m⭐ \u001b[m");
            }
            System.out.println("\n");
            
        }
    }
}
