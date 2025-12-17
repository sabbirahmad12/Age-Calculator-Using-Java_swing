import java.awt.*;
import javax.swing.*;

public class ThemeManager {
    // Color scheme
    public static final Color PRIMARY_COLOR = new Color(41, 128, 185);    // Blue
    public static final Color SECONDARY_COLOR = new Color(142, 68, 173);  // Purple
    public static final Color ACCENT_COLOR = new Color(39, 174, 96);      // Green
    public static final Color BACKGROUND_COLOR = new Color(245, 245, 245); // Light gray
    public static final Color TEXT_COLOR = new Color(51, 51, 51);         // Dark gray
    
    // Font settings
    public static final String FONT_FAMILY = "Segoe UI";
    
    public static void applyTheme() {
        // Set global UI defaults
        UIManager.put("Button.font", new Font(FONT_FAMILY, Font.PLAIN, 13));
        UIManager.put("Label.font", new Font(FONT_FAMILY, Font.PLAIN, 13));
        UIManager.put("TextField.font", new Font(FONT_FAMILY, Font.PLAIN, 13));
        UIManager.put("ComboBox.font", new Font(FONT_FAMILY, Font.PLAIN, 13));
        UIManager.put("CheckBox.font", new Font(FONT_FAMILY, Font.PLAIN, 13));
        
        // Button colors
        UIManager.put("Button.background", PRIMARY_COLOR);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.select", PRIMARY_COLOR.darker());
        
        // Text field colors
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.foreground", TEXT_COLOR);
        UIManager.put("TextField.caretForeground", PRIMARY_COLOR);
        
        // ComboBox colors
        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("ComboBox.foreground", TEXT_COLOR);
        
        // Panel colors
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        
        // Frame colors
        UIManager.put("Frame.background", BACKGROUND_COLOR);
    }
    
    public static GradientPaint createGradient(int width, int height) {
        return new GradientPaint(
            0, 0, PRIMARY_COLOR,
            width, height, PRIMARY_COLOR.brighter()
        );
    }
}