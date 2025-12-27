# 🏦 Service gRPC Banking avec Spring Boot

![Java](https://img.shields.io/badge/Java-20-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen)
![gRPC](https://img.shields.io/badge/gRPC-1.53.0-blue)
![Maven](https://img.shields.io/badge/Maven-3.6+-red)

Un service bancaire moderne développé avec Spring Boot et gRPC permettant la gestion complète des comptes bancaires avec une communication haute performance basée sur Protocol Buffers.

## 📋 Table des matières

- [Fonctionnalités](#-fonctionnalités)
- [Technologies utilisées](#-technologies-utilisées)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Utilisation](#-utilisation)
- [Schéma Protobuf](#-schéma-protobuf)
- [Exemples de requêtes](#-exemples-de-requêtes)
- [Structure du projet](#-structure-du-projet)
- [Tests](#-tests)
- [Auteur](#-auteur)

## ✨ Fonctionnalités

### Gestion des Comptes
- ✅ Création de comptes bancaires (COURANT, EPARGNE)
- ✅ Consultation de tous les comptes
- ✅ Recherche de compte par identifiant
- ✅ Calcul automatique des statistiques (nombre, somme, moyenne)

### API gRPC
- ✅ Service gRPC complet avec méthodes RPC
- ✅ Support de BloomRPC pour les tests interactifs
- ✅ Génération automatique des stubs Java
- ✅ Communication haute performance avec Protocol Buffers
- ✅ Support des appels unaires (Unary RPC)

### Base de données
- ✅ Stockage en mémoire avec ConcurrentHashMap
- ✅ Gestion thread-safe des données
- ✅ Génération automatique des IDs

## 🛠 Technologies utilisées

- **Java 20** - Langage de programmation
- **Spring Boot 3.1.0** - Framework d'application
- **Spring Data JPA** - Persistance des données (préparé pour extension)
- **gRPC 1.53.0** - Framework RPC haute performance
- **Protocol Buffers 3.21.12** - Sérialisation binaire
- **H2 Database** - Base de données en mémoire (optionnelle)
- **Lombok** - Réduction du code boilerplate
- **Maven** - Gestion des dépendances

## 📦 Prérequis

- Java 20 ou supérieur
- Maven 3.6+
- BloomRPC (optionnel, pour les tests)
- Git (optionnel)

## 🚀 Installation

### 1. Cloner le repository
```bash
git clone https://github.com/HaytamNajam26/TP18.git
cd TP18
```

### 2. Compiler le projet
```bash
mvn clean compile
```

### 3. Lancer l'application
```bash
mvn spring-boot:run
```

Le serveur gRPC sera disponible sur **localhost:9090**

## ⚙️ Configuration

Le fichier `src/main/resources/application.properties` contient la configuration de l'application :

```properties
# Configuration du serveur gRPC
grpc.server.port=9090
```

## 💻 Utilisation

### Accès aux interfaces

| Interface | URL | Description |
|-----------|-----|-------------|
| Serveur gRPC | `localhost:9090` | Point d'accès gRPC pour les appels RPC |
| BloomRPC | Application locale | Client gRPC graphique pour les tests |

### Connexion avec BloomRPC

1. Ouvrir BloomRPC
2. Importer le fichier `src/main/proto/CompteService.proto`
3. Configurer l'adresse : `localhost:9090`
4. Tester les méthodes RPC

## 📊 Schéma Protobuf

### Types principaux

```protobuf
enum TypeCompte {
    COURANT = 0;
    EPARGNE = 1;
}

message Compte {
    string id = 1;
    float solde = 2;
    string dateCreation = 3;
    TypeCompte type = 4;
}

message CompteRequest {
    float solde = 1;
    string dateCreation = 2;
    TypeCompte type = 3;
}

message SoldeStats {
    int32 count = 1;
    float sum = 2;
    float average = 3;
}
```

### Service gRPC

```protobuf
service CompteService {
    rpc AllComptes(GetAllComptesRequest) returns (GetAllComptesResponse);
    rpc CompteById(GetCompteByIdRequest) returns (GetCompteByIdResponse);
    rpc TotalSolde(GetTotalSoldeRequest) returns (GetTotalSoldeResponse);
    rpc SaveCompte(SaveCompteRequest) returns (SaveCompteResponse);
}
```

## 📝 Exemples de requêtes

### 1. Récupérer tous les comptes

**Méthode RPC :** `AllComptes`

**Requête (BloomRPC) :**
```json
{}
```

**Réponse exemple :**
```json
{
  "comptes": [
    {
      "id": "efca0b08-ace6-487a-bd90-eb2827f2d6a9",
      "solde": 5000.75,
      "dateCreation": "2025-12-27",
      "type": "COURANT"
    },
    {
      "id": "ee368aab-3710-4188-a55a-0c2ecf8d4f8e",
      "solde": 10000.5,
      "dateCreation": "2025-12-27",
      "type": "EPARGNE"
    }
  ]
}
```

<img width="1955" height="1019" alt="image" src="https://github.com/user-attachments/assets/e067ca00-f538-4c6c-9afc-71b038ae24ec" />


### 2. Récupérer un compte par ID

**Méthode RPC :** `CompteById`

**Requête (BloomRPC) :**
```json
{
  "id": "efca0b08-ace6-487a-bd90-eb2827f2d6a9"
}
```

**Réponse exemple :**
```json
{
  "compte": {
    "id": "efca0b08-ace6-487a-bd90-eb2827f2d6a9",
    "solde": 5000.75,
    "dateCreation": "2025-12-27",
    "type": "COURANT"
  }
}
```

<img width="1962" height="750" alt="image" src="https://github.com/user-attachments/assets/f6556873-0880-40dd-b3da-8dae84c804b5" />


### 3. Obtenir les statistiques des soldes

**Méthode RPC :** `TotalSolde`

**Requête (BloomRPC) :**
```json
{}
```

**Réponse exemple :**
```json
{
  "stats": {
    "count": 2,
    "sum": 15001.25,
    "average": 7500.625
  }
}
```

<img width="1960" height="772" alt="image" src="https://github.com/user-attachments/assets/05e0477a-874f-42d6-992d-8471b67e90d6" />


### 4. Créer un nouveau compte COURANT

**Méthode RPC :** `SaveCompte`

**Requête (BloomRPC) :**
```json
{
  "compte": {
    "solde": 1500.0,
    "dateCreation": "2025-12-27",
    "type": "COURANT"
  }
}
```

**Réponse exemple :**
```json
{
  "compte": {
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "solde": 1500.0,
    "dateCreation": "2025-12-27",
    "type": "COURANT"
  }
}
```

<img width="1965" height="767" alt="image" src="https://github.com/user-attachments/assets/995998e9-5dec-4a63-a52f-ee84f7dff7b3" />


### 5. Créer un nouveau compte EPARGNE

**Méthode RPC :** `SaveCompte`

**Requête (BloomRPC) :**
```json
{
  "compte": {
    "solde": 3000.0,
    "dateCreation": "2025-12-27",
    "type": "EPARGNE"
  }
}
```

**Réponse exemple :**
```json
{
  "compte": {
    "id": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
    "solde": 3000.0,
    "dateCreation": "2025-12-27",
    "type": "EPARGNE"
  }
}
```

<img width="1965" height="767" alt="image" src="https://github.com/user-attachments/assets/c43a7333-8515-49fb-97d8-02f486b95243" />


## 📁 Structure du projet

```
TP18/
├── src/
│   ├── main/
│   │   ├── java/ma/projet/grpc/
│   │   │   ├── client/
│   │   │   │   └── CompteClient.java
│   │   │   ├── controllers/
│   │   │   │   └── CompteServiceImpl.java
│   │   │   └── Grpc2Application.java
│   │   ├── proto/
│   │   │   └── CompteService.proto
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── target/
│   └── generated-sources/
│       └── protobuf/
│           └── grpc-java/
│               └── ma/projet/grpc/stubs/
│                   └── (Fichiers générés)
├── pom.xml
└── README.md
```

## 🧪 Tests

### Tests avec BloomRPC

1. **Installer BloomRPC**
   - Télécharger depuis : https://github.com/uw-labs/bloomrpc/releases
   - Installer l'application

2. **Configurer BloomRPC**
   - Ouvrir BloomRPC
   - Cliquer sur "Import Proto File"
   - Sélectionner `src/main/proto/CompteService.proto`
   - Configurer l'adresse : `localhost:9090`

3. **Tester les méthodes**
   - Sélectionner une méthode RPC
   - Entrer les données JSON
   - Cliquer sur "Send Request"

### Tests avec le client Java

```bash
# Compiler et exécuter le client
mvn compile exec:java
```

Le client créera automatiquement deux comptes et affichera les résultats.

### Ordre recommandé pour les tests

1. **Créer des comptes** : Utilisez `SaveCompte` pour créer 2-3 comptes
2. **Vérifier les comptes** : Utilisez `AllComptes` pour voir tous les comptes
3. **Rechercher un compte** : Utilisez `CompteById` avec un ID valide
4. **Tester les statistiques** : Utilisez `TotalSolde` pour voir les stats
5. **Tester les erreurs** : Testez avec des IDs inexistants

## 🎯 Fonctionnalités avancées

### Génération automatique des stubs

Les stubs Java sont générés automatiquement lors de la compilation :
```bash
mvn clean compile
```

Les fichiers générés se trouvent dans :
```
target/generated-sources/protobuf/grpc-java/ma/projet/grpc/stubs/
```

### Communication haute performance

- **Protocol Buffers** : Sérialisation binaire efficace
- **HTTP/2** : Support natif par gRPC
- **Streaming** : Prêt pour l'extension avec streaming RPC

### Gestion thread-safe

Le service utilise `ConcurrentHashMap` pour garantir la sécurité des threads lors des accès concurrents.

## 🔧 Développement

### Compiler le projet
```bash
mvn clean compile
```

### Exécuter l'application
```bash
mvn spring-boot:run
```

### Exécuter le client de test
```bash
mvn exec:java
```

### Générer le JAR exécutable
```bash
mvn clean package
java -jar target/grpc2-0.0.1-SNAPSHOT.jar
```

### Nettoyer le projet
```bash
mvn clean
```

## 📚 Ressources

- [Documentation gRPC Java](https://grpc.io/docs/languages/java/)
- [Documentation Protocol Buffers](https://developers.google.com/protocol-buffers)
- [Documentation Spring Boot gRPC](https://github.com/yidongnan/grpc-spring-boot-starter)
- [Documentation Spring Boot](https://spring.io/projects/spring-boot)
- [BloomRPC GitHub](https://github.com/uw-labs/bloomrpc)

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :

1. Fork le projet
2. Créer une branche (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est sous licence MIT.

## 👤 Auteur

Développé dans le cadre d'un TP sur gRPC avec Spring Boot.

---

**Note :** Ce projet utilise une base de données en mémoire. Les données sont perdues à chaque redémarrage de l'application. Pour une utilisation en production, intégrez une base de données persistante (PostgreSQL, MySQL, etc.) avec Spring Data JPA.

