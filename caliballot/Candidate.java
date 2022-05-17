/**
 * creates a Candidate object to hold a first name, last name and age of a candidate.  Allows for setting of occupation
 * and city if wanted.
 * Candidate constructor - creates a Candidate object
 * setOccupation
 * setCity
 * toString - returns each part of the Candidate object
 * equals - allows to find if specified part of two objects are equal
 * compareTo - compares ages
 * @author Jonathon Zempel
 * @version 01-29-2022
 */
public class Candidate implements Comparable<Candidate>{
    public String lastName;
    public String firstName;
    public int age;
    public String party;
    private String occupation;
    private String city;

    /**
     * class constructor
     * @param nameLast last name of candidate
     * @param nameFirst first name of candidate
     * @param candAge age of candidate
     * @param affiliation party affiliation of candidate
     */
    public Candidate(String nameFirst, String nameLast, String candAge, String affiliation) {
        firstName = nameFirst;
        lastName = nameLast;
        age = Integer.parseInt(candAge);
        party = affiliation;
    }

    /**
     * sets candidates occupation
     * @param candOccupation occupation of candidate, if wanted to specify
     */
    public void setOccupation(String candOccupation) {
        occupation = candOccupation;
    }

    /**
     * sets candidates city
     * @param location city where candidate is from, if wanted
     */
    public void setCity(String location) {
        city = location;
    }

    /**
     *@return string consisting of first_name last_name  age  party
     */
    @Override
    public String toString() {
        return firstName + "  " + lastName + " - "  + age + "  " + party;
    }

    /**
     *
     * @param o candidate object
     * @return true if this.o is equal to specified that.o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Candidate that = (Candidate) o;
        if (age == that.age)
            return false;
        if (lastName.equals(that.lastName))
            return false;
        if (firstName.equals(that.firstName))
            return false;
        return true;

    }

    /**
     * compares candidate objects by age
     * @param o candidate object
     * @return which candidate has the youngest age
     */
    @Override
    public int compareTo(Candidate o) {
        return Integer.compare(this.age, o.age);
    }


}
