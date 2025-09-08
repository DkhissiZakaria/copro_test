package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.model.Syndic;
import com.app.copro.repository.SyndicRepository;
import com.app.copro.service.FileStorageService;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class SyndicFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private SyndicRepository syndicRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public String uploadCarteProfessionnelle(Long syndicId, MultipartFile file) {
        String fileRef = fileManagerHelper.uploadSyndicDocument(
            file, 
            syndicId, 
            FileConstants.SyndicCategories.CARTE_PROFESSIONNELLE,
            "Carte professionnelle du syndic"
        );
        
        if (fileRef != null) {
            updateSyndicFileReference(syndicId, "docCarteProfessionnelleRef", fileRef);
        }
        
        return fileRef;
    }

    public String uploadAssuranceRc(Long syndicId, MultipartFile file) {
        String fileRef = fileManagerHelper.uploadSyndicDocument(
            file, 
            syndicId, 
            FileConstants.SyndicCategories.ASSURANCE_RC,
            "Attestation d'assurance RC professionnelle"
        );
        
        if (fileRef != null) {
            updateSyndicFileReference(syndicId, "docAssuranceRcRef", fileRef);
        }
        
        return fileRef;
    }

    public String uploadGarantieFinanciere(Long syndicId, MultipartFile file) {
        String fileRef = fileManagerHelper.uploadSyndicDocument(
            file, 
            syndicId, 
            FileConstants.SyndicCategories.GARANTIE_FINANCIERE,
            "Attestation de garantie financière"
        );
        
        if (fileRef != null) {
            updateSyndicFileReference(syndicId, "docGarantieFinanciereRef", fileRef);
        }
        
        return fileRef;
    }

    public String uploadTamponSignature(Long syndicId, MultipartFile file) {
        String fileRef = fileManagerHelper.uploadSyndicDocument(
            file, 
            syndicId, 
            FileConstants.SyndicCategories.TAMPON_SIGNATURE,
            "Tampon et signature du syndic"
        );
        
        if (fileRef != null) {
            updateSyndicFileReference(syndicId, "docTamponSignatureRef", fileRef);
        }
        
        return fileRef;
    }

    public String uploadLogoCoordonnees(Long syndicId, MultipartFile file) {
        String fileRef = fileManagerHelper.uploadSyndicDocument(
            file, 
            syndicId, 
            FileConstants.SyndicCategories.LOGO_COORDONNEES,
            "Logo avec coordonnées (510x260)"
        );
        
        if (fileRef != null) {
            updateSyndicFileReference(syndicId, "logoCoordonneesRef", fileRef);
        }
        
        return fileRef;
    }

    public String uploadLogoSimple(Long syndicId, MultipartFile file) {
        String fileRef = fileManagerHelper.uploadSyndicDocument(
            file, 
            syndicId, 
            FileConstants.SyndicCategories.LOGO_SIMPLE,
            "Logo simple pour signature mail"
        );
        
        if (fileRef != null) {
            updateSyndicFileReference(syndicId, "logoSimpleRef", fileRef);
        }
        
        return fileRef;
    }

    public List<FileResponseDto> getAllSyndicFiles(Long syndicId) {
        return fileManagerHelper.getAllSyndicFiles(syndicId);
    }

    public FileResponseDto getCarteProfessionnelleFile(Long syndicId) {
        return getSyndicFile(syndicId, "docCarteProfessionnelleRef");
    }

    public FileResponseDto getAssuranceRcFile(Long syndicId) {
        return getSyndicFile(syndicId, "docAssuranceRcRef");
    }

    public FileResponseDto getGarantieFinanciereFile(Long syndicId) {
        return getSyndicFile(syndicId, "docGarantieFinanciereRef");
    }

    public FileResponseDto getTamponSignatureFile(Long syndicId) {
        return getSyndicFile(syndicId, "docTamponSignatureRef");
    }

    public FileResponseDto getLogoCoordonneesFile(Long syndicId) {
        return getSyndicFile(syndicId, "logoCoordonneesRef");
    }

    public FileResponseDto getLogoSimpleFile(Long syndicId) {
        return getSyndicFile(syndicId, "logoSimpleRef");
    }

//    public String generatePresignedUrl(Long syndicId, String category) {
//        FileResponseDto file = fileStorageService.getLatestFileByEntityAndCategory(
//            FileConstants.EntityTypes.SYNDIC,
//            syndicId,
//            category
//        );
//
//        return file != null ? fileManagerHelper.generatePresignedUrl(file.getId()) : null;
//    }

    public boolean deleteAllSyndicFiles(Long syndicId) {
        boolean deleted = fileManagerHelper.deleteAllSyndicFiles(syndicId);
        if (deleted) {
            clearAllSyndicFileReferences(syndicId);
        }
        return deleted;
    }

    private void updateSyndicFileReference(Long syndicId, String fieldName, String fileRef) {
        Optional<Syndic> syndicOpt = syndicRepository.findById(syndicId);
        if (syndicOpt.isPresent()) {
            Syndic syndic = syndicOpt.get();
            
            switch (fieldName) {
                case "docCarteProfessionnelleRef":
                    syndic.setDocCarteProfessionnelleRef(fileRef);
                    break;
                case "docAssuranceRcRef":
                    syndic.setDocAssuranceRcRef(fileRef);
                    break;
                case "docGarantieFinanciereRef":
                    syndic.setDocGarantieFinanciereRef(fileRef);
                    break;
                case "docTamponSignatureRef":
                    syndic.setDocTamponSignatureRef(fileRef);
                    break;
                case "logoCoordonneesRef":
                    syndic.setLogoCoordonneesRef(fileRef);
                    break;
                case "logoSimpleRef":
                    syndic.setLogoSimpleRef(fileRef);
                    break;
            }
            
            syndicRepository.save(syndic);
        }
    }

    private FileResponseDto getSyndicFile(Long syndicId, String fieldName) {
        Optional<Syndic> syndicOpt = syndicRepository.findById(syndicId);
        if (syndicOpt.isPresent()) {
            Syndic syndic = syndicOpt.get();
            String fileRef = null;
            
            switch (fieldName) {
                case "docCarteProfessionnelleRef":
                    fileRef = syndic.getDocCarteProfessionnelleRef();
                    break;
                case "docAssuranceRcRef":
                    fileRef = syndic.getDocAssuranceRcRef();
                    break;
                case "docGarantieFinanciereRef":
                    fileRef = syndic.getDocGarantieFinanciereRef();
                    break;
                case "docTamponSignatureRef":
                    fileRef = syndic.getDocTamponSignatureRef();
                    break;
                case "logoCoordonneesRef":
                    fileRef = syndic.getLogoCoordonneesRef();
                    break;
                case "logoSimpleRef":
                    fileRef = syndic.getLogoSimpleRef();
                    break;
            }
            
            return fileManagerHelper.parseFileReference(fileRef);
        }
        
        return null;
    }

    private void clearAllSyndicFileReferences(Long syndicId) {
        Optional<Syndic> syndicOpt = syndicRepository.findById(syndicId);
        if (syndicOpt.isPresent()) {
            Syndic syndic = syndicOpt.get();
            syndic.setDocCarteProfessionnelleRef(null);
            syndic.setDocAssuranceRcRef(null);
            syndic.setDocGarantieFinanciereRef(null);
            syndic.setDocTamponSignatureRef(null);
            syndic.setLogoCoordonneesRef(null);
            syndic.setLogoSimpleRef(null);
            syndicRepository.save(syndic);
        }
    }
}