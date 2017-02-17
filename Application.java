import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lori on 2/16/2017.
 */
public class Application {

    /**
     * This method instantiates two other methods.
     * One that inserts an amount of numbers into a linked list and the other that queries that list of numbers.
     * The query consists of inserting or deleting into or from the list.
     * This method prints the modified list.
     */
    private void problem2() {
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        LinkedList<Integer> queries = new LinkedList<Integer>();
        numbers = insertingLines();
        queries = query(numbers);
        System.out.println(queries);
    }

    /**
     * This method is used to insert an amount of numbers from the standard input into a linked list.
     *
     * @return a linked list of numbers.
     */
    private LinkedList<Integer> insertingLines() {
        LinkedList<Integer> numbersFromInput = new LinkedList<Integer>();
        Scanner input = new Scanner(System.in);
        double numberConvertedFromString = 0;
        int amountOfNumbers = 0;
        while (true) {
            amountOfNumbers = Integer.parseInt(getInput(input, "Enter the amount of numbers that you want to add: "));
            if (amountOfNumbers < 0) {
                System.out.println("Please enter only a positive number.");
            } else
                break;
        }

        while (true) {
            List<Integer> line = new ArrayList<Integer>();
            System.out.format("Enter the numbers separated by space: ");
            String[] numbersAsStrings = input.nextLine().split("\\s");

            for (int index = 0; index < numbersAsStrings.length; index++) {
                if (numbersAsStrings[index].matches("[0-9]+")) {
                    numberConvertedFromString = Double.parseDouble(numbersAsStrings[index]);
                    if (numberConvertedFromString > Integer.MAX_VALUE) {
                        System.out.println("Please enter a smaller number then " + numberConvertedFromString);
                        numbersFromInput.clear();
                        break;
                    }
                    if (numbersAsStrings.length > amountOfNumbers || numbersAsStrings.length < amountOfNumbers) {
                        System.out.format("Please enter no more or less than %d numbers.\n", amountOfNumbers);
                        numbersFromInput.clear();
                        break;
                    }
                    numbersFromInput.add(Integer.parseInt(numbersAsStrings[index]));
                } else {
                    System.out.println("You have entered a character or a string of characters. " +
                            "Please enter the numbers again.");
                    numbersFromInput.clear();
                    break;
                }
            }
            if (!numbersFromInput.isEmpty()) {
                break;
            }
        }
        return numbersFromInput;
    }

    /**
     * This method is used to query the list of numbers.
     * Uses two types of queries: inserting and deleting.
     * After the user performs the desired queries it returns a linked list with the new values.
     *
     * @param numbers a list of numbers.
     * @return the same list that it was provided with, but now containing the modifications.
     */
    private LinkedList<Integer> query(LinkedList<Integer> numbers) {
        Scanner input = new Scanner(System.in);
        int amountOfQueries = 0;
        int counterOfQueries = 0;
        String insertOrDelete = "";
        double numbersForQuery = 0;
        boolean appropriateQueryValues = true;


        while (true) {
            amountOfQueries = Integer.parseInt(getInput(input, "Enter the amount of queries that you want to execute: "));
            if (amountOfQueries < 0) {
                System.out.println("Please enter only a positive number.");
            } else
                break;
        }

        while (counterOfQueries < amountOfQueries) {
                if (appropriateQueryValues == true) {
                    while (true) {
                        System.out.print("Do you want to insert or delete? ");
                        insertOrDelete = input.nextLine().toLowerCase().trim();
                        if (!insertOrDelete.equals("insert") && !insertOrDelete.equals("delete")) {
                            System.out.println("Please enter a 'insert' or 'delete'.");
                        } else
                            break;
                    }
                }
                if (insertOrDelete.equals("insert")) {
                    System.out.print("Enter the position and number separated by space: ");
                    String[] numberOfQueries = input.nextLine().split("\\s");

                    for (int index = 0; index < numberOfQueries.length; index++) {
                        if (numberOfQueries[index].matches("[0-9]+")) {
                            numbersForQuery = Double.parseDouble(numberOfQueries[index]);

                            if (numbersForQuery > Integer.MAX_VALUE) {
                                System.out.println("Please enter a smaller number.");
                                appropriateQueryValues = false;
                                counterOfQueries--;
                                break;
                            }
                            if (numberOfQueries.length > 2) {
                                System.out.println("Please enter no more then 2 numbers.");
                                counterOfQueries--;
                                appropriateQueryValues = false;
                                break;
                            }
                            if (numberOfQueries.length < 2) {
                                System.out.println("Please enter 2 numbers.");
                                counterOfQueries--;
                                appropriateQueryValues = false;
                                break;
                            }
                            if (numbersForQuery < 0) {
                                System.out.println("Please enter only positive numbers.");
                                counterOfQueries--;
                                appropriateQueryValues = false;
                                break;
                            }
                            if (Integer.parseInt(numberOfQueries[0]) > numbers.size()) {
                                System.out.println("Index does not exist.");
                                appropriateQueryValues = false;
                                counterOfQueries--;
                                break;
                            }

                            appropriateQueryValues = true;

                        } else {
                            System.out.println("You have entered a string of characters. " +
                                    "Please enter the numbers again.");
                            counterOfQueries--;
                            appropriateQueryValues = false;
                            break;
                        }
                    }
                    if (appropriateQueryValues == true) {
                        numbers.add(Integer.parseInt(numberOfQueries[0]), Integer.parseInt(numberOfQueries[1]));
                    }
                } else if (insertOrDelete.equals("delete")) {
                    int position = 0;
                    while (true) {
                        position = Integer.parseInt(getInput(input, "Enter the position: "));
                        if (position < 0) {
                            System.out.println("Please enter only a positive number.");
                        }
                        if (position <= numbers.size()) {
                            numbers.remove(position);
                            break;
                        } else {
                            System.out.println("Index does not exist.");
                        }
                    }
                }

                counterOfQueries++;
        }
        return numbers;
    }
    

    /**
     * This method is used to get the input from the user and use the isInteger method which verifies if the input
     * is a number or not.
     *
     * @param input         the user input.
     * @param promptMessage a message that will prompt before the user inputs a value.
     * @return the input of the user in a string format.
     */
    private String getInput(Scanner input, String promptMessage) {
        String text = "";
        double numberConvertedFromString = 0;

        while (true) {
            System.out.print(promptMessage);
            text = input.nextLine();
            if (isInteger(text)) {
                numberConvertedFromString = Double.parseDouble(text);
                if (numberConvertedFromString > Integer.MAX_VALUE) {
                    System.out.println("Please enter a smaller number.");
                } else
                    break;
            } else
                System.out.println("Entered value is not a number, or you have entered more then one number.");
        }
        return text;
    }

    /**
     * This method verifies if a string is a number.
     *
     * @param string a string which contains a number or an array of characters.
     * @return true if it is a number and false otherwise.
     */

    private boolean isInteger(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.problem2();
    }
}
