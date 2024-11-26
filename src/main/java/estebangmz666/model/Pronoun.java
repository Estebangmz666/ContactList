package estebangmz666.model;

public enum Pronoun {
    EL("Él"),
    ELLA("Ella"),
    ELLE("Elle"),
    NOSOTROS("Nosotros"),
    NOSOTRAS("Nosotras"),
    ELLOS("Ellos"),
    ELLAS("Ellas"),
    USTED("Usted"),
    USTEDES("Ustedes"),
    EL_X("El/Ellx"),
    ELLX("Ellx"),
    THEY("They"),
    ZE("Ze"),
    HIR("Hir"),
    ZIR("Zir"),
    XE("Xe"),
    VE("Ve"),
    PER("Per"),
    IT("It"),
    OTHER("Otro");

    private final String displayName;

    Pronoun(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Pronoun fromString(String name) {
        for (Pronoun pronoun : Pronoun.values()) {
            if (pronoun.name().equalsIgnoreCase(name)) {
                return pronoun;
            }
        }
        throw new IllegalArgumentException("Pronombre no válido: " + name);
    }
    
    public static Pronoun fromDisplayName(String displayName) {
        for (Pronoun pronoun : Pronoun.values()) {
            if (pronoun.displayName.equalsIgnoreCase(displayName)) {
                return pronoun;
            }
        }
        throw new IllegalArgumentException("DisplayName no válido: " + displayName);
    }
    
}