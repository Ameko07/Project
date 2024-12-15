public class ThemeManager {
    private static String backgroundPath = "src/defaultBackground.png";
    private static String blockImagePath = "src/defaultBlock.png";

    // Change le thème
    public static void setTheme(String background, String block) {
        backgroundPath = background;
        blockImagePath = block;
    }

    // Getters pour récupérer les chemins
    public static String getBackgroundPath() {
        return backgroundPath;
    }

    public static String getBlockImagePath() {
        return blockImagePath;
    }
}
