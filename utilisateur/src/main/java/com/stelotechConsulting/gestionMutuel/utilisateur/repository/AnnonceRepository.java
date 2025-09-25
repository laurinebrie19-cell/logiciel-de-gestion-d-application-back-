package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findByCategorieAnnonce_Id(Long categorieAnnonceId);
    List<Annonce> findByTypeAnnonce_Id(Long typeAnnonceId);
    List<Annonce> findByIsActiveTrue();
}
