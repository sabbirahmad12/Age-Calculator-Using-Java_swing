import java.time.LocalDate;

public class DateValidator {
    
    public static String validateDate(String dayStr, String monthStr, String yearStr) {
        // Check if fields are empty
        if (dayStr.isEmpty() || monthStr.isEmpty() || yearStr.isEmpty()) {
            return "Please fill in all fields";
        }
        
        try {
            int day = Integer.parseInt(dayStr);
            int month = Integer.parseInt(monthStr);
            int year = Integer.parseInt(yearStr);
            
            // Check year range
            int currentYear = LocalDate.now().getYear();
            if (year < 1900 || year > currentYear) {
                return String.format("Year must be between 1900 and %d", currentYear);
            }
            
            // Check month
            if (month < 1 || month > 12) {
                return "Month must be between 1 and 12";
            }
            
            // Check day
            if (day < 1 || day > 31) {
                return "Day must be between 1 and 31";
            }
            
            // Try to create date (will throw exception if invalid)
            LocalDate date = LocalDate.of(year, month, day);
            
            // Check if date is in future
            if (date.isAfter(LocalDate.now())) {
                return "Birth date cannot be in the future";
            }
            
            return "Valid";
            
        } catch (NumberFormatException e) {
            return "Please enter valid numbers";
        } catch (Exception e) {
            return "Invalid date. Please check day, month, and year.";
        }
    }
}