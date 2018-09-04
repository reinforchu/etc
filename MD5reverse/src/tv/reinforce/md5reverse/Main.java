import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    public static void main(String[] args) {
        Document doc = null;
        String hash = "";
        System.out.println("Expose plaintext from MD5 hash.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(hash.isEmpty()) {
            System.out.print("Hash> ");
            try {
                hash = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            doc = Jsoup.connect("http://md5.gromweb.com/?md5=" + hash).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String plainText = doc.select("#form_string_to_hash_string").attr("value"); // Get plaintext
        System.out.println("Text> " + plainText); // Empty data is returned if don't exist in the database.
    }
}
