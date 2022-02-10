import java.util.*;

public class Functions {

    // Ask user for an int in a defined range, and don't return that input until it is deemed valid
    public static int getIntInRange(int lower, int upper, Scanner scan) {
        String input = scan.nextLine();
        // While the number is not an int in a defined range, keep asking for a new int
        while (!validInt(lower, upper, input)) {
            System.out.printf("Choose number between %d and %d\n", lower, upper);
            input = scan.nextLine();
        }
        // Return the input parsed into an int
        return Integer.parseInt(input);
    }

    // Checks if an input is both an int and within the correct range, making it valid for this use
    public static boolean validInt(int lower, int upper, String input) {
        // If the input is an int
        if (isInt(input)) {
            // Returns whether the int is in the correct range
            return inRange(lower, upper, Integer.parseInt(input));
        }
        return false;
    }

    // Returns whether an input was an integer or not
    public static boolean isInt(String input) {
        boolean isInt;
        // This attempts to parse the string into an int. If is fails, then the input was not a valid int
        try {
            Integer.parseInt(input);
            isInt = true;
        } catch (NumberFormatException  e) {
            System.out.println(input + " is not a number\n");
            System.out.print("Try again: ");
            isInt = false;
        }
        return isInt;
    }

    // Returns whether an input is within a defined range
    public static boolean inRange(int lower, int upper, int input) {
        // As long as there is a properly defined range
        if (upper > lower) {
            // If the input is within the range, return true
            if (input >= lower && input <= upper) return true;

            // Else tell the user that it is not within range
            else {
                System.out.println("Number is not in range\n");
                System.out.print("Try again: ");
                return false;
            }
        }
        // This is just in case I defined the range incorrectly
        else {
            System.out.println("\n\nRange is not set up properly\n");
            return false;
        }
    }

    // Returns a random boolean, simulating a coin flip
    public static boolean flipCoin() {
        Random rand = new Random();
        return rand.nextBoolean();
    }
}
