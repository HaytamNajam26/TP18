package ma.projet.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ma.projet.grpc.stubs.*;

public class CompteClient {
    
    public static void main(String[] args) {
        // Créer un canal gRPC vers le serveur
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        
        try {
            // Créer un stub bloquant pour les appels synchrones
            CompteServiceGrpc.CompteServiceBlockingStub stub = CompteServiceGrpc.newBlockingStub(channel);
            
            System.out.println("=== Création de deux comptes ===\n");
            
            // Créer le premier compte (COURANT)
            System.out.println("1. Création d'un compte COURANT...");
            CompteRequest compte1 = CompteRequest.newBuilder()
                    .setSolde(5000.75f)
                    .setDateCreation("2025-12-27")
                    .setType(TypeCompte.COURANT)
                    .build();
            
            SaveCompteRequest request1 = SaveCompteRequest.newBuilder()
                    .setCompte(compte1)
                    .build();
            
            SaveCompteResponse response1 = stub.saveCompte(request1);
            System.out.println("✓ Compte créé avec succès!");
            System.out.println("  ID: " + response1.getCompte().getId());
            System.out.println("  Solde: " + response1.getCompte().getSolde());
            System.out.println("  Type: " + response1.getCompte().getType());
            System.out.println("  Date de création: " + response1.getCompte().getDateCreation());
            System.out.println();
            
            // Créer le deuxième compte (EPARGNE)
            System.out.println("2. Création d'un compte EPARGNE...");
            CompteRequest compte2 = CompteRequest.newBuilder()
                    .setSolde(10000.50f)
                    .setDateCreation("2025-12-27")
                    .setType(TypeCompte.EPARGNE)
                    .build();
            
            SaveCompteRequest request2 = SaveCompteRequest.newBuilder()
                    .setCompte(compte2)
                    .build();
            
            SaveCompteResponse response2 = stub.saveCompte(request2);
            System.out.println("✓ Compte créé avec succès!");
            System.out.println("  ID: " + response2.getCompte().getId());
            System.out.println("  Solde: " + response2.getCompte().getSolde());
            System.out.println("  Type: " + response2.getCompte().getType());
            System.out.println("  Date de création: " + response2.getCompte().getDateCreation());
            System.out.println();
            
            // Afficher tous les comptes
            System.out.println("=== Liste de tous les comptes ===");
            GetAllComptesResponse allComptes = stub.allComptes(GetAllComptesRequest.newBuilder().build());
            System.out.println("Nombre total de comptes: " + allComptes.getComptesCount());
            for (Compte compte : allComptes.getComptesList()) {
                System.out.println("- " + compte.getType() + " | ID: " + compte.getId() + 
                                 " | Solde: " + compte.getSolde());
            }
            
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel gRPC: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermer le canal
            channel.shutdown();
        }
    }
}

