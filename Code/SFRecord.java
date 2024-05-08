/**
 * SFRecord class represents one stop and frisk occurrence where a person
 * was stopped by an officer.
 * 
 * @author Vidushi Jindal
 * @author Tanvi Yamarthy
 * 
 * ****** DO NOT EDIT! *******
 */

public class SFRecord {

    // instance variables
    private String  description; // description of committed crime
    private boolean arrested;    // true if file contains Y (Yes), false if file contains N (No)
    private boolean frisked;     // true if file contains Y (Yes), false if file contains N (No)
    private String  gender;      // person's gender
    private String  race;        // person's rate
    private String  location;    // location where stop occurred

    /* 
     * Note: A person who is stopped has not necessarily committed a crime; 
     * people are presumed innocent until proven guilty by the courts.
     */ 

    /*
     * Constructor
     * 
     * @param caseDescription
     * @param arrested
     * @param frisked
     * @param gender
     * @param race
     * @param location
     */ 
    public SFRecord ( String caseDescription, boolean arrested, boolean frisked, 
                    String gender, String race, String location ) {
        this.description = caseDescription;
        this.arrested        = arrested;
        this.frisked         = frisked;
        this.gender          = gender;
        this.race            = race;
        this.location        = location;
    }

    // accessor methods
    public String getDescription () {
        return description;
    }

    public boolean getArrested () {
        return arrested;
    }

    public boolean getFrisked () {
        return frisked;
    }

    public String getGender () {
        return gender;
    }

    public String getRace () {
        return race;
    }

    public String getLocation () {
        return location;
    }

    /*
     * 
     * @return the string representation of SFRecord object
     */ 
    public String toString () {
        String str = "\n Record";
        str += "\n\tDescription: " + description;
        str += "\n\tArrested: " + arrested;
        str += "\n\tFrisked: " + frisked;
        str += "\n\tGender: " + gender;
        str += "\n\tRace: " + race;
        str += "\n\tLocation: " + location;
        return str;
    }

    /*
     * Compares this object with other
     * 
     * @param other the object to be compared with.
     * @return true if this object equals other, false otherwise.
     */
    public boolean equals ( Object other ) {

        if ( other == null || !(other instanceof SFRecord) ) {
            return false;
        }
        SFRecord o = (SFRecord) other;
        return description.equals(o.getDescription()) &&
                arrested == o.getArrested() &&
                frisked == o.getFrisked() &&
                gender.equals(o.getGender()) &&
                race.equals(o.getRace()) &&
                location.equals(o.getLocation());
    }
}