import java.util.ArrayList;

/**
 * This class is designed to test each method in the StopAndFrisk file
 * interactively
 * 
 * @author Vidushi Jindal
 * @author Tanvi Yamarthy
 */

public class Driver {

    private static StopAndFrisk year = new StopAndFrisk();

    public static void main(String[] args) {
        String[] methods = { "readFile", "populationStopped", "friskedVSArrested",
                "genderBias", "crimeIncrease", "mostCommonBorough" };
        String[] options = { "Test a new CSV file year", "Test another method on the same file", "Quit" };
        int controlChoice = 1;
        do {
            do {
                StdOut.println("\nWhat method would you like to test?");

                for (int i = 0; i < methods.length; i++)
                    StdOut.printf("%d. %s\n", i + 1, methods[i]);

                StdOut.print("Enter a number => ");
                int choice = Integer.parseInt(StdIn.readLine());

                switch (choice) {
                    case 1:
                        // readFile
                        StdOut.print("Enter a CSV input file => ");
                        String inputFile = StdIn.readLine();
                        testReadFile(inputFile);
                        break;
                    case 2:
                        // populationStopped
                        StdOut.print("Enter year => ");
                        int yearInput = Integer.parseInt(StdIn.readLine());
                        StdOut.print("Enter race (B = Black, W = White, A = Asian) => ");
                        String raceInput = StdIn.readLine();
                        testPopulationStopped(yearInput, raceInput);
                        break;
                    case 3:
                        // friskedVSArrested
                        StdOut.print("Enter year => ");
                        int yearFriskedvArrested = Integer.parseInt(StdIn.readLine());
                        testFriskedVSArrested(yearFriskedvArrested);
                        break;
                    case 4:
                        // genderBias
                        StdOut.print("Enter year => ");
                        int yearBias = Integer.parseInt(StdIn.readLine());
                        testGenderBias(yearBias);
                        break;
                    case 5:
                        // crimeIncrease
                        StdOut.print("Enter first year => ");
                        int yearInput1 = Integer.parseInt(StdIn.readLine());
                        StdOut.print("Enter second year => ");
                        int yearInput2 = Integer.parseInt(StdIn.readLine());
                        StdOut.print("Enter crime => ");
                        String crimeInput = StdIn.readLine();
                        testCrimeIncrease(yearInput1, yearInput2, crimeInput);
                        break;
                    case 6:
                        // mostCommonBorough
                        StdOut.print("Enter year => ");
                        int yearCommonBorough = Integer.parseInt(StdIn.readLine());
                        testMostCommonBorough(yearCommonBorough);
                        break;
                    default:
                        StdOut.println("Not a valid option!");
                }

                StdIn.resetFile();
                StdOut.println("\nWhat would you like to do now?");

                for (int i = 0; i < 3; i++)
                    StdOut.printf("%d. %s\n", i + 1, options[i]);

                StdOut.print("Enter a number => ");
                controlChoice = Integer.parseInt(StdIn.readLine());
            } while (controlChoice == 2);
        } while (controlChoice == 1);
    }

    private static StopAndFrisk testReadFile(String inputFile) {
        year.readFile(inputFile);
        return year;
    }

    private static void testPopulationStopped(int yearInput, String inputFile) {
        ArrayList<SFRecord> popStopped = year.populationStopped(yearInput, inputFile);
        System.out.println(popStopped.size());
    }

    private static void testFriskedVSArrested(int yearFriskedvArrested) {
        double[] answerPercent = year.friskedVSArrested(yearFriskedvArrested);

        StdOut.println("In the year " + yearFriskedvArrested + ", " + answerPercent[0]
                + "% were frisked and " + answerPercent[1] + "% were arrested.");
    }

    private static void testGenderBias(int yearBias) {
        double[][] answer = year.genderBias(yearBias);

        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                System.out.print(answer[i][j] + "%" + " ");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void testCrimeIncrease(int yearInput1, int yearInput2, String crimeInput) {
        double output = year.crimeIncrease(crimeInput, yearInput1, yearInput2);
        System.out.println(output + "%");
    }

    private static void testMostCommonBorough(int yearCommonBorough) {
        String commonBorough = year.mostCommonBorough(yearCommonBorough);
        System.out.println(commonBorough);
    }
}
