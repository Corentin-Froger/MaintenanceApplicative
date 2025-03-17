package utilisateur;

import java.util.Arrays;
import java.util.List;

public record Utilisateur(String nom) {
    private static final List<String> adminNames = Arrays.asList("Roger", "Pierre");
    // TODO mots de passe des admins en clair...
    private static final List<String> adminPasswords = Arrays.asList("Chat", "KiRouhl");

    public boolean isAdmin() {
        return adminNames.contains(this.nom());
    }

    public static boolean isAdminPasswordValid(Utilisateur utilisateur, String password) {
        int i = adminNames.indexOf(utilisateur.nom());
        int j = adminPasswords.indexOf(password);

        if (i == -1 || j == -1) { // Inexistant
            return false;
        }
        return i == j;
    }
}
