# Guide d'utilisation du Système de Gestion de Fichiers S3

## Vue d'ensemble

Ce système offre une solution complète et intelligente pour la gestion des fichiers avec AWS S3, conçue pour être réutilisable à travers différentes entités de votre application.

## Architecture

### Composants principaux

1. **FileEntity** - Entité JPA pour stocker les métadonnées des fichiers
2. **FileStorageService** - Interface de service générique
3. **S3FileStorageService** - Implémentation AWS S3
4. **FileManagerHelper** - Utilitaire pour faciliter l'intégration
5. **FileConstants** - Constantes pour types d'entités et catégories

## Utilisation pour nouvelles entités

### 1. Étapes de base

```java
// Dans votre service
@Autowired
private FileManagerHelper fileManagerHelper;

// Upload d'un fichier
String fileRef = fileManagerHelper.uploadEntityDocument(
    file,                           // MultipartFile
    "COPROPRIETE",                 // Type d'entité
    coproprieteId,                 // ID de l'entité
    "DOCUMENT_SYNDICAL",           // Catégorie
    "Document important"           // Description
);

// Récupération des fichiers
List<FileResponseDto> files = fileManagerHelper.getEntityFiles("COPROPRIETE", coproprieteId);
```

### 2. Pour une entité spécifique (ex: Copropriété)

```java
@Entity
public class Copropriete {
    // Vos champs existants...
    
    @Column(length = 255)
    private String documentsRef; // Format: FILE_ID:123
    
    @Column(length = 255)  
    private String planRef;      // Format: FILE_ID:123
}

// Service dédié
@Service
public class CoproprieteFileService {
    
    @Autowired
    private FileManagerHelper fileManagerHelper;
    
    public String uploadDocument(Long coproprieteId, MultipartFile file) {
        return fileManagerHelper.uploadEntityDocument(
            file,
            FileConstants.EntityTypes.COPROPRIETE,
            coproprieteId,
            FileConstants.CommonCategories.DOCUMENT,
            "Document de copropriété"
        );
    }
}
```

## API Endpoints disponibles

### Upload de fichiers
- `POST /api/files/upload` - Upload générique
- `POST /api/syndics/{id}/files/carte-professionnelle` - Upload spécifique Syndic

### Récupération de fichiers
- `GET /api/files/entity/{entityType}/{entityId}` - Tous les fichiers d'une entité
- `GET /api/files/entity/{entityType}/{entityId}/category/{category}` - Par catégorie
- `GET /api/files/{fileId}` - Métadonnées d'un fichier

### Téléchargement
- `GET /api/files/{fileId}/download` - Téléchargement direct
- `GET /api/files/{fileId}/presigned-url` - URL présignée

## Catégories prédéfinies

### Pour Syndic
- `CARTE_PROFESSIONNELLE`
- `ASSURANCE_RC`
- `GARANTIE_FINANCIERE` 
- `TAMPON_SIGNATURE`
- `LOGO_COORDONNEES`
- `LOGO_SIMPLE`

### Catégories communes
- `DOCUMENT`
- `IMAGE`
- `RAPPORT`
- `FACTURE`
- `CONTRAT`

## Configuration

Assurez-vous d'avoir ces propriétés dans `application.properties` :

```properties
# Configuration S3
cloud.aws.credentials.access-key=YOUR_ACCESS_KEY
cloud.aws.credentials.secret-key=YOUR_SECRET_KEY
cloud.aws.region.static=eu-north-1
aws.bucket.name=your-bucket-name

# Taille max des fichiers
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

## Exemple d'utilisation complète

```java
// 1. Ajouter les champs de référence dans votre entité
@Entity
public class Mandat {
    @Column(length = 255)
    private String contratRef;
    
    @Column(length = 255)
    private String avenantRef;
    
    // getters/setters...
}

// 2. Créer un service dédié
@Service
public class MandatFileService {
    
    @Autowired
    private FileManagerHelper fileManagerHelper;
    
    @Autowired
    private MandatRepository mandatRepository;
    
    public String uploadContrat(Long mandatId, MultipartFile file) {
        String fileRef = fileManagerHelper.uploadEntityDocument(
            file,
            "MANDAT",
            mandatId,
            "CONTRAT",
            "Contrat de mandat"
        );
        
        // Mettre à jour la référence dans l'entité
        Optional<Mandat> mandatOpt = mandatRepository.findById(mandatId);
        if (mandatOpt.isPresent()) {
            Mandat mandat = mandatOpt.get();
            mandat.setContratRef(fileRef);
            mandatRepository.save(mandat);
        }
        
        return fileRef;
    }
    
    public FileResponseDto getContratFile(Long mandatId) {
        Optional<Mandat> mandatOpt = mandatRepository.findById(mandatId);
        if (mandatOpt.isPresent()) {
            String fileRef = mandatOpt.get().getContratRef();
            return fileManagerHelper.parseFileReference(fileRef);
        }
        return null;
    }
}

// 3. Ajouter les endpoints dans votre contrôleur
@RestController
@RequestMapping("/api/mandats")
public class MandatController {
    
    @Autowired
    private MandatFileService mandatFileService;
    
    @PostMapping("/{id}/files/contrat")
    public ResponseEntity<String> uploadContrat(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String fileRef = mandatFileService.uploadContrat(id, file);
        return ResponseEntity.ok(fileRef);
    }
    
    @GetMapping("/{id}/files/contrat")
    public ResponseEntity<FileResponseDto> getContratFile(@PathVariable Long id) {
        FileResponseDto file = mandatFileService.getContratFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}
```

## Avantages

1. **Isolation** - Système complètement indépendant et réutilisable
2. **Flexibilité** - Supporte n'importe quelle entité avec n'importe quelle catégorie
3. **Sécurité** - URLs présignées pour l'accès sécurisé aux fichiers
4. **Traçabilité** - Métadonnées complètes avec dates d'upload
5. **Performance** - Stockage S3 optimisé avec clés hiérarchiques
6. **Maintenance** - Gestion centralisée des fichiers avec soft delete

Ce système vous permettra d'ajouter facilement la gestion de fichiers à n'importe quelle nouvelle entité sans dupliquer le code.