package com.app.copro.util;

public final class FileConstants {
    
    private FileConstants() {}

    public static final class EntityTypes {
        public static final String SYNDIC = "SYNDIC";
        public static final String COPROPRIETE = "COPROPRIETE";
        public static final String MANDAT = "MANDAT";
        public static final String CARNET = "CARNET";
        public static final String PROJET = "PROJET";
        public static final String CONTRAT_ASSURANCE = "CONTRAT_ASSURANCE";
        public static final String DIAGNOSTIC_COLLECTIF = "DIAGNOSTIC_COLLECTIF";
        public static final String TRAVAIL_IMPORTANT = "TRAVAIL_IMPORTANT";
        public static final String PROCEDURE_ADMINISTRATIVE = "PROCEDURE_ADMINISTRATIVE";
        public static final String PPT = "PPT";
    }

    public static final class SyndicCategories {
        public static final String CARTE_PROFESSIONNELLE = "CARTE_PROFESSIONNELLE";
        public static final String ASSURANCE_RC = "ASSURANCE_RC";
        public static final String GARANTIE_FINANCIERE = "GARANTIE_FINANCIERE";
        public static final String TAMPON_SIGNATURE = "TAMPON_SIGNATURE";
        public static final String LOGO_COORDONNEES = "LOGO_COORDONNEES";
        public static final String LOGO_SIMPLE = "LOGO_SIMPLE";
    }

    public static final class CommonCategories {
        public static final String DOCUMENT = "DOCUMENT";
        public static final String IMAGE = "IMAGE";
        public static final String ARCHIVE = "ARCHIVE";
        public static final String RAPPORT = "RAPPORT";
        public static final String FACTURE = "FACTURE";
        public static final String CONTRAT = "CONTRAT";
    }

    public static final class MimeTypes {
        public static final String PDF = "application/pdf";
        public static final String JPEG = "image/jpeg";
        public static final String PNG = "image/png";
        public static final String DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        public static final String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    public static final int DEFAULT_PRESIGNED_URL_DURATION_MINUTES = 60;
    public static final long MAX_FILE_SIZE_BYTES = 10 * 1024 * 1024; // 10MB
}