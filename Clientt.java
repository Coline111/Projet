/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Clientt {

    public static void main(String[] args) {
        try {
            // Spécifiez l'adresse IP et le port du serveur auquel vous souhaitez vous connecter
            String serverAddress = "127.0.0.1"; // Adresse IP du serveur
            int serverPort = 12345; // Port du serveur

            // Créez une socket client pour se connecter au serveur
            Socket socket = new Socket(serverAddress, serverPort);

            // Créez des flux d'entrée et de sortie pour communiquer avec le serveur
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Envoyez un message au serveur
            String message = "Bonjour, serveur!";
            out.println(message);

            // Lisez la réponse du serveur
            String response = in.readLine();
            System.out.println("Réponse du serveur : " + response);

            // Fermez la connexion avec le serveur
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Clientt {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            // Spécifiez l'adresse IP et le port du serveur auquel vous souhaitez vous connecter
            String serverAddress = "127.0.0.1"; // Adresse IP du serveur
            int serverPort = 12345; // Port du serveur

            // Créez une socket client pour se connecter au serveur
            socket = new Socket(serverAddress, serverPort);

            // Créez des flux d'entrée et de sortie pour communiquer avec le serveur
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Créez un lecteur pour lire depuis le terminal
            BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

            // Envoyez un message au serveur
            String message = "Bonjour, serveur!";
            out.println(message);

            // Lisez la réponse du serveur
            String response = in.readLine();
            System.out.println("Réponse du serveur : " + response);

            // Boucle pour attendre que l'utilisateur tape "aurevoir" pour se déconnecter
            String userInput;
            while ((userInput = terminalReader.readLine()) != null) {
                if (userInput.equalsIgnoreCase("aurevoir")) {
                    System.out.println("Déconnexion en cours...");
                    break; // Sortez de la boucle pour fermer la connexion
                }
                // Envoyez l'entrée de l'utilisateur au serveur
                out.println(userInput);
            }

            // Fermez la connexion avec le serveur
            socket.close();
            System.out.println("Déconnexion réussie.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
    
