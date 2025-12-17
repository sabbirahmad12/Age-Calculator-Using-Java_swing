import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class AgeCalculator extends JFrame {
    // Components
    private JTextField dayField, monthField, yearField;
    private JLabel resultLabel, totalDaysLabel, dayOfWeekLabel, nextBirthdayLabel;
    private JPanel inputPanel, resultPanel;
    private JButton calculateButton, clearButton;
    
    public AgeCalculator() {
        // Setup window
        setTitle("Age Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Initialize UI
        initComponents();
        
        // Layout setup
        setupLayout();
    }
    
    private void initComponents() {
        // Create custom font
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font titleFont = new Font("Arial", Font.BOLD, 16);
        Font resultFont = new Font("Arial", Font.BOLD, 24);
        Font smallFont = new Font("Arial", Font.PLAIN, 12);
        
        // Input panel with titled border
        inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 215), 2),
            "Enter Your Birth Date",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            titleFont,
            new Color(0, 120, 215)
        ));
        inputPanel.setBackground(Color.WHITE);
        
        // Input fields
        dayField = new JTextField(15);
        dayField.setFont(labelFont);
        dayField.setHorizontalAlignment(JTextField.CENTER);
        
        monthField = new JTextField(15);
        monthField.setFont(labelFont);
        monthField.setHorizontalAlignment(JTextField.CENTER);
        
        yearField = new JTextField(15);
        yearField.setFont(labelFont);
        yearField.setHorizontalAlignment(JTextField.CENTER);
        
        // Calculate button
        calculateButton = new JButton("Calculate Age");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(0, 120, 215));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 100, 190), 2),
            BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        calculateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Clear button
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(new Color(108, 117, 125));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 100, 110), 2),
            BorderFactory.createEmptyBorder(8, 35, 8, 35)
        ));
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Result panel with titled border
        resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(40, 167, 69), 2),
            "Age Calculation Result",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            titleFont,
            new Color(40, 167, 69)
        ));
        resultPanel.setBackground(Color.WHITE);
        
        // Result labels
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(resultFont);
        resultLabel.setForeground(new Color(220, 53, 69));
        
        totalDaysLabel = new JLabel("", SwingConstants.CENTER);
        totalDaysLabel.setFont(smallFont);
        totalDaysLabel.setForeground(new Color(108, 117, 125));
        
        dayOfWeekLabel = new JLabel("", SwingConstants.CENTER);
        dayOfWeekLabel.setFont(smallFont);
        dayOfWeekLabel.setForeground(new Color(108, 117, 125));
        
        nextBirthdayLabel = new JLabel("", SwingConstants.CENTER);
        nextBirthdayLabel.setFont(smallFont);
        nextBirthdayLabel.setForeground(new Color(108, 117, 125));
        
        // Add action listeners
        calculateButton.addActionListener(e -> calculateAge());
        clearButton.addActionListener(e -> clearFields());
        
        // Add key listener for Enter key
        yearField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateAge();
                }
            }
        });
    }
    
    private void setupLayout() {
        // Main container
        Container container = getContentPane();
        container.setLayout(new BorderLayout(10, 10));
        container.setBackground(new Color(240, 242, 245));
        
        // Header
        JLabel headerLabel = new JLabel("Age Calculator", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(new Color(33, 37, 41));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        
        // Input panel layout
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Day input
        JLabel dayLabel = new JLabel("Day :");
        dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(dayLabel, gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(dayField, gbc);
        
        // Month input
        JLabel monthLabel = new JLabel("Month :");
        monthLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(monthLabel, gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(monthField, gbc);
        
        // Year input
        JLabel yearLabel = new JLabel("Year :");
        yearLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(yearLabel, gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(yearField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(new Color(240, 242, 245));
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        
        // Result panel layout - Now with GridBagLayout for better alignment
        resultPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcResult = new GridBagConstraints();
        gbcResult.insets = new Insets(10, 10, 10, 10);
        gbcResult.gridwidth = GridBagConstraints.REMAINDER;
        gbcResult.fill = GridBagConstraints.HORIZONTAL;
        
        gbcResult.gridy = 0;
        resultPanel.add(resultLabel, gbcResult);
        
        gbcResult.gridy = 1;
        resultPanel.add(totalDaysLabel, gbcResult);
        
        gbcResult.gridy = 2;
        resultPanel.add(dayOfWeekLabel, gbcResult);
        
        gbcResult.gridy = 3;
        resultPanel.add(nextBirthdayLabel, gbcResult);
        
        // Create main content panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 20));
        mainPanel.setBackground(new Color(240, 242, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Add input panel to center
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        
        // Add button panel below input panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add components to container
        container.add(headerLabel, BorderLayout.NORTH);
        container.add(mainPanel, BorderLayout.CENTER);
        container.add(resultPanel, BorderLayout.SOUTH);
    }
    
    private void calculateAge() {
        try {
            // Get input values
            String dayStr = dayField.getText().trim();
            String monthStr = monthField.getText().trim();
            String yearStr = yearField.getText().trim();
            
            // Check if fields are empty
            if (dayStr.isEmpty() || monthStr.isEmpty() || yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all fields", 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Parse input values
            int day = Integer.parseInt(dayStr);
            int month = Integer.parseInt(monthStr);
            int year = Integer.parseInt(yearStr);
            
            // Basic validation
            int currentYear = LocalDate.now().getYear();
            if (year < 1900 || year > currentYear) {
                JOptionPane.showMessageDialog(this, 
                    String.format("Year must be between 1900 and %d", currentYear), 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (month < 1 || month > 12) {
                JOptionPane.showMessageDialog(this, 
                    "Month must be between 1 and 12", 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Check day based on month
            if (day < 1 || day > 31) {
                JOptionPane.showMessageDialog(this, 
                    "Day must be between 1 and 31", 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validate date
            try {
                LocalDate birthDate = LocalDate.of(year, month, day);
                LocalDate currentDate = LocalDate.now();
                
                // Check if date is in future
                if (birthDate.isAfter(currentDate)) {
                    JOptionPane.showMessageDialog(this, 
                        "Birth date cannot be in the future", 
                        "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Calculate age
                Period age = Period.between(birthDate, currentDate);
                
                // Calculate total days
                long totalDays = ChronoUnit.DAYS.between(birthDate, currentDate);
                
                // Get day of week
                String dayOfWeek = birthDate.getDayOfWeek().getDisplayName(
                    TextStyle.FULL, Locale.ENGLISH);
                
                // Calculate next birthday
                LocalDate nextBirthday = birthDate.withYear(currentDate.getYear());
                if (nextBirthday.isBefore(currentDate) || nextBirthday.isEqual(currentDate)) {
                    nextBirthday = nextBirthday.plusYears(1);
                }
                long daysToNextBirthday = ChronoUnit.DAYS.between(currentDate, nextBirthday);
                
                // Display results
                resultLabel.setText(String.format("%d Years, %02d Month, %02d Days", 
                    age.getYears(), age.getMonths(), age.getDays()));
                
                totalDaysLabel.setText(String.format("Total Days: %d Day", totalDays));
                dayOfWeekLabel.setText(String.format("Born on a %s", dayOfWeek));
                nextBirthdayLabel.setText(String.format("Next Birthday in: %d Days", daysToNextBirthday));
                
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid date. Please check day, month, and year.", 
                    "Invalid Date", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers in all fields", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "An error occurred: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        dayField.setText("");
        monthField.setText("");
        yearField.setText("");
        resultLabel.setText("");
        totalDaysLabel.setText("");
        dayOfWeekLabel.setText("");
        nextBirthdayLabel.setText("");
        dayField.requestFocus();
    }
    
    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(() -> {
            AgeCalculator calculator = new AgeCalculator();
            calculator.setVisible(true);
        });
    }
}