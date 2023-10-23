import socket
import time

# Paramètres du serveur
host = '127.0.0.1'  # Adresse IP du serveur (dans cet exemple, le serveur s'exécute en local)
port = 12345  # Port sur lequel le serveur écoutera les connexions

# Créez un socket serveur
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Liez le socket au couple (host, port)
server_socket.bind((host, port))

# Écoutez les connexions entrantes
server_socket.listen(5)  # Le paramètre spécifie le nombre maximum de connexions en attente

print(f"Le serveur écoute sur {host}:{port}")

while True:
    # Attendez qu'un client se connecte
    client_socket, client_address = server_socket.accept()

    print(f"Connexion entrante de {client_address}")

    # Traitez les données reçues du client
    data = client_socket.recv(1024)  # Recevez jusqu'à 1024 octets de données à la fois
    data = data.decode('utf-8')  # Décodez les données en texte

    # Faites quelque chose avec les données (dans cet exemple, nous les affichons)
    print(f"Données reçues du client : {data}")

    # Réponse au client
    response = "Message reçu par le serveur"
    client_socket.send(response.encode('utf-8'))

    # Fermez la connexion avec le client
    client_socket.close()
    clients = {}

    # Définissez le timeout en secondes pour vérifier la déconnexion
    timeout = 60  # Par exemple, 60 secondes

    while True:
        client_socket, client_address = server_socket.accept()

        # Stockez l'heure de la dernière communication du client
        clients[client_socket] = time.time()

        print(f"Connexion entrante de {client_address}")

        # ... (votre code pour traiter les données du client)

        # Vérifiez les clients pour déconnexion
        disconnected_clients = []
        for client, last_activity_time in clients.items():
            if time.time() - last_activity_time > timeout:
                print(f"Client {client.getpeername()} déconnecté.")
                disconnected_clients.append(client)
                client.close()

        # Supprimez les clients déconnectés du dictionnaire
        for client in disconnected_clients:
            del clients[client]
