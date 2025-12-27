package ma.projet.grpc.services;

import ma.projet.grpc.entities.Compte;
import ma.projet.grpc.repositories.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompteService {
    private final CompteRepository compteRepository;
    
    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }
    
    public List<Compte> findAllComptes() {
        return compteRepository.findAll();
    }
    
    public Compte findCompteById(String id) {
        return compteRepository.findById(id).orElse(null);
    }
    
    public Compte saveCompte(Compte compte) {
        // Générer l'ID si non défini
        if (compte.getId() == null || compte.getId().isEmpty()) {
            compte.setId(UUID.randomUUID().toString());
        }
        return compteRepository.save(compte);
    }
    
    public long countComptes() {
        return compteRepository.count();
    }
    
    public double calculateTotalSolde() {
        return compteRepository.findAll().stream()
                .mapToDouble(Compte::getSolde)
                .sum();
    }
}

