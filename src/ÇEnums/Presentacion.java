package ÇEnums;

public enum Presentacion{
    C("Comprimidos"),G("Gotas"),S("Suspension");


    private String extension;

    Presentacion(String extension) {
        this.extension = extension;
    }
}

