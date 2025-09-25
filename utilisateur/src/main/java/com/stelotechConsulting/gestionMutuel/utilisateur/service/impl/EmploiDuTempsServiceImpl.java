package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EmploiDuTempsRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.SeanceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EmploiDuTempsResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SeanceResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.*;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.*;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IEmploiDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmploiDuTempsServiceImpl implements IEmploiDuTempsService {
    @Autowired private EmploiDuTempsRepository emploiDuTempsRepository;
    @Autowired private SeanceRepository seanceRepository;
    @Autowired private NiveauRepository niveauRepository;
    @Autowired private MatiereRepository matiereRepository;
    @Autowired private SalleRepository salleRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public EmploiDuTempsResponseDTO createEmploiDuTemps(EmploiDuTempsRequestDTO dto) {
        Niveau niveau = niveauRepository.findById(dto.getNiveauId())
            .orElseThrow(() -> new IllegalArgumentException("Niveau non trouvé"));
        EmploiDuTemps emploi = EmploiDuTemps.builder()
            .titre(dto.getTitre())
            .dateDebut(dto.getDateDebut())
            .dateFin(dto.getDateFin())
            .niveau(niveau)
            .build();
        List<Seance> seances = new ArrayList<>();
        if (dto.getSeances() != null) {
            for (SeanceRequestDTO sDto : dto.getSeances()) {
                // Vérification chevauchement enseignant
                List<Seance> enseignantSeances = seanceRepository.findByEnseignantIdAndJour(sDto.getEnseignantId(), sDto.getJour());
                for (Seance s : enseignantSeances) {
                    if (chevauche(s.getHeureDebut(), s.getHeureFin(), sDto.getHeureDebut(), sDto.getHeureFin())) {
                        throw new IllegalArgumentException("L'enseignant est déjà occupé à cette heure");
                    }
                }
                // Vérification chevauchement salle
                List<Seance> salleSeances = seanceRepository.findBySalleIdAndJour(sDto.getSalleId(), sDto.getJour());
                for (Seance s : salleSeances) {
                    if (chevauche(s.getHeureDebut(), s.getHeureFin(), sDto.getHeureDebut(), sDto.getHeureFin())) {
                        throw new IllegalArgumentException("La salle est déjà occupée à cette heure");
                    }
                }
                Matiere matiere = matiereRepository.findById(sDto.getMatiereId())
                    .orElseThrow(() -> new IllegalArgumentException("Matière non trouvée"));
                Salle salle = salleRepository.findById(sDto.getSalleId())
                    .orElseThrow(() -> new IllegalArgumentException("Salle non trouvée"));
                Utilisateur enseignant = userRepository.findById(sDto.getEnseignantId())
                    .orElseThrow(() -> new IllegalArgumentException("Enseignant non trouvé"));
                Seance seance = Seance.builder()
                    .jour(sDto.getJour())
                    .heureDebut(sDto.getHeureDebut())
                    .heureFin(sDto.getHeureFin())
                    .matiere(matiere)
                    .salle(salle)
                    .enseignant(enseignant)
                    .emploiDuTemps(emploi)
                    .build();
                seances.add(seance);
            }
        }
        emploi.setSeances(seances);
        emploi = emploiDuTempsRepository.save(emploi);
        return toResponseDTO(emploi);
    }

    @Override
    public EmploiDuTempsResponseDTO getEmploiDuTempsById(Long id) {
        EmploiDuTemps emploi = emploiDuTempsRepository.findById(id).orElse(null);
        return emploi != null ? toResponseDTO(emploi) : null;
    }

    @Override
    public List<EmploiDuTempsResponseDTO> getEmploisDuTempsByNiveau(Long niveauId) {
        return emploiDuTempsRepository.findByNiveauId(niveauId).stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public EmploiDuTempsResponseDTO updateEmploiDuTemps(Long id, EmploiDuTempsRequestDTO dto) {
        EmploiDuTemps emploi = emploiDuTempsRepository.findById(id).orElse(null);
        if (emploi == null) return null;
        emploi.setTitre(dto.getTitre());
        emploi.setDateDebut(dto.getDateDebut());
        emploi.setDateFin(dto.getDateFin());
        // Pour la mise à jour des séances, on peut remplacer la liste
        List<Seance> seances = new ArrayList<>();
        if (dto.getSeances() != null) {
            for (SeanceRequestDTO sDto : dto.getSeances()) {
                // Vérification chevauchement enseignant
                List<Seance> enseignantSeances = seanceRepository.findByEnseignantIdAndJour(sDto.getEnseignantId(), sDto.getJour());
                for (Seance s : enseignantSeances) {
                    if (chevauche(s.getHeureDebut(), s.getHeureFin(), sDto.getHeureDebut(), sDto.getHeureFin())) {
                        throw new IllegalArgumentException("L'enseignant est déjà occupé à cette heure");
                    }
                }
                // Vérification chevauchement salle
                List<Seance> salleSeances = seanceRepository.findBySalleIdAndJour(sDto.getSalleId(), sDto.getJour());
                for (Seance s : salleSeances) {
                    if (chevauche(s.getHeureDebut(), s.getHeureFin(), sDto.getHeureDebut(), sDto.getHeureFin())) {
                        throw new IllegalArgumentException("La salle est déjà occupée à cette heure");
                    }
                }
                Matiere matiere = matiereRepository.findById(sDto.getMatiereId())
                    .orElseThrow(() -> new IllegalArgumentException("Matière non trouvée"));
                Salle salle = salleRepository.findById(sDto.getSalleId())
                    .orElseThrow(() -> new IllegalArgumentException("Salle non trouvée"));
                Utilisateur enseignant = userRepository.findById(sDto.getEnseignantId())
                    .orElseThrow(() -> new IllegalArgumentException("Enseignant non trouvé"));
                Seance seance = Seance.builder()
                    .jour(sDto.getJour())
                    .heureDebut(sDto.getHeureDebut())
                    .heureFin(sDto.getHeureFin())
                    .matiere(matiere)
                    .salle(salle)
                    .enseignant(enseignant)
                    .emploiDuTemps(emploi)
                    .build();
                seances.add(seance);
            }
        }
        emploi.setSeances(seances);
        emploi = emploiDuTempsRepository.save(emploi);
        return toResponseDTO(emploi);
    }

    @Override
    public void deleteEmploiDuTemps(Long id) {
        emploiDuTempsRepository.deleteById(id);
    }

    @Override
    public List<EmploiDuTempsResponseDTO> getAllEmploisDuTemps() {
        return emploiDuTempsRepository.findAll().stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    private boolean chevauche(java.time.LocalTime debut1, java.time.LocalTime fin1, java.time.LocalTime debut2, java.time.LocalTime fin2) {
        return !debut1.isAfter(fin2) && !debut2.isAfter(fin1);
    }

    private EmploiDuTempsResponseDTO toResponseDTO(EmploiDuTemps emploi) {
        EmploiDuTempsResponseDTO dto = new EmploiDuTempsResponseDTO();
        dto.setId(emploi.getId());
        dto.setTitre(emploi.getTitre());
        dto.setDateDebut(emploi.getDateDebut());
        dto.setDateFin(emploi.getDateFin());
        dto.setNiveauId(emploi.getNiveau().getId());
        if (emploi.getSeances() != null) {
            List<SeanceResponseDTO> seanceDtos = emploi.getSeances().stream().map(s -> {
                SeanceResponseDTO sdto = new SeanceResponseDTO();
                sdto.setId(s.getId());
                sdto.setJour(s.getJour());
                sdto.setHeureDebut(s.getHeureDebut());
                sdto.setHeureFin(s.getHeureFin());
                sdto.setMatiereId(s.getMatiere().getId());
                sdto.setMatiereNom(s.getMatiere().getNom());
                sdto.setEnseignantId(s.getEnseignant().getId());
                sdto.setEnseignantNom(s.getEnseignant().getLastName());
                sdto.setSalleId(s.getSalle().getId());
                sdto.setSalleNom(s.getSalle().getNom());
                return sdto;
            }).collect(Collectors.toList());
            dto.setSeances(seanceDtos);
        }
        return dto;
    }
}
