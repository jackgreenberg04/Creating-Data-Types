import java.util.ArrayList;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 * 
 * @author Tanvi Yamarthy
 * @author Vidushi Jindal
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile ( String csvFile ) {

        // DO NOT remove these two lines
        StdIn.setFile(csvFile); // Opens the file
        StdIn.readLine();       // Reads and discards the header line

        // WRITE YOUR CODE HERE
        while (!StdIn.isEmpty()){
            String recLine = StdIn.readLine();
            String[] recordEntries = recLine.split(",");
        
            int year = Integer.parseInt(recordEntries[0]);
            String description = recordEntries[2];
            String gender = recordEntries[52];
            String race = recordEntries[66];
            String location = recordEntries[71];
            Boolean arrested = recordEntries[13].equals("Y");
            Boolean frisked = recordEntries[16].equals("Y");

            // DUPLICATE CASE
            SFRecord record = new SFRecord(description, arrested, frisked, gender, race, location);

            boolean found = false;
            for (SFYear sfYear : database){
                if (sfYear.getcurrentYear() == year){
                    sfYear.addRecord(record);
                    found = true;
                }
            }
            if (!found) {
                SFYear newYear = new SFYear(year);
                newYear.addRecord(record);
                database.add(newYear);
            }
            
        }
        // System.out.println(database.get(0).getRecordsForYear().size());
    }

    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

    public ArrayList<SFRecord> populationStopped ( int year, String race ) {

        // WRITE YOUR CODE HERE
        ArrayList<SFRecord> records = new ArrayList<>();
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year) {
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    if (record.getRace().equals(race)) {
                        records.add(record);
                    }
                }
                //break;
            }
        }
	    return records; // update the return value
    }

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested ( int year ) {
        
        // WRITE YOUR CODE HERE
        int friskedCount = 0;
        int arrestedCount = 0;
        int totalRecords = 0;
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year) {
                totalRecords = sfYear.getRecordsForYear().size();
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    if (record.getFrisked()){
                        friskedCount++;
                    } 
                        if (record.getArrested()){
                            arrestedCount++;
                        } 
                    }
                break;
                }
        }
        double[] results = new double[2];
        if (totalRecords > 0) {
            results[0] = ((double)friskedCount / totalRecords) * 100;
            results[1] = ((double)arrestedCount / totalRecords) * 100;
        }
        return results; // update the return value
        
    }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias ( int year ) {

        // WRITE YOUR CODE HERE
        int blackFemales = 0;
        int blackMales = 0;
        int whiteFemales = 0; 
        int whiteMales = 0;  
        int totalBlacks = 0;
        int totalWhites = 0;
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year) {
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    if (record.getRace().equals("B")) {
                        totalBlacks++;
                        if (record.getGender().equals("F")){
                            blackFemales++;
                        } 
                        else if (record.getGender().equals("M")){
                            blackMales++;
                        } 
                    } else if (record.getRace().equals("W")) {
                        totalWhites++;
                        if (record.getGender().equals("F")) whiteFemales++;
                        else if (record.getGender().equals("M")) whiteMales++;
                    }
                }
                break;
            }
        }
        double[][] results = new double[2][3];
        if (totalBlacks > 0) {
            results[0][0] = ((double) blackFemales / totalBlacks) * 0.5 * 100;
            results[1][0] = ((double) blackMales / totalBlacks) * 0.5 * 100;
        } 
        if (totalWhites > 0) {
            results[0][1] = ((double) whiteFemales / totalWhites) * 0.5 * 100;
            results[1][1] = ((double) whiteMales / totalWhites) * 0.5 * 100;
        }
        if (totalBlacks + totalWhites > 0){
            results[0][2] = ((double) (results[0][0] + results[0][1]));
            results[1][2] = ((double) (results[1][0] + results[1][1]));
        }

        return results; // update the return value
    }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

    public double crimeIncrease ( String crimeDescription, int year1, int year2 ) {
        
        // WRITE YOUR CODE HERE
        double count1 = 0;
        double count2 = 0;
        double total1 = 0;
        double total2 = 0;
        
        for (SFYear sfYear : database) {

            if (sfYear.getcurrentYear() == year1) {
                total1 = sfYear.getRecordsForYear().size();
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    if (record.getDescription().indexOf(crimeDescription) != -1){
                        count1++;
                    }
                }
            }
            if (sfYear.getcurrentYear() == year2) {
                total2 = sfYear.getRecordsForYear().size();
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    if (record.getDescription().indexOf(crimeDescription) != -1){
                        count2++;
                        }
                    }
                }
            
        }
        
        double percent1 = 0;
        double percent2 = 0;
        double percent = 0;
        if (total1 > 0){
            percent1 = ((double) count1 / total1) * 100;
            //System.out.println(percent1);
            System.out.println(count1);
            System.out.println(total1);
        }
        if (total2 > 0){
            percent2 = ((double) count2 / total2) * 100;
            //System.out.println(percent2);
            //System.out.println(count2);
            //System.out.println(total2);
        }
        percent = percent2 - percent1;

	    return percent; // update the return value
    }

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough ( int year ) {

        // WRITE YOUR CODE HERE
        int[] counts = new int[5];
        String[] boroughs = {"Brooklyn", "Manhattan", "Bronx", "Queens", "Staten Island"};
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year) {
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    for (int i = 0; i < boroughs.length; i++) {
                        if (record.getLocation().equalsIgnoreCase(boroughs[i])) {
                            counts[i]++;
                            break;
                        }
                    }
                }
                break;
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }

        return boroughs[maxIndex]; // update the return value
    }

}
