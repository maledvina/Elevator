
/**
	* Description: Person class just sets up the person and gets name. floor and put to string
	* Author: Maya Ledvina
	* Date: 11/8/18
	*/

	public class Person {
    
		String name;
		int enteryFloor; // i know it's spelled wrong 
   


    /**
     * Description: gets name and beginning floor from file and sets them up
	 *@param name is the person's name, entryFloor is the beginning floor
     */
    public Person(String name, int entryFloor) {
        this.name = name;
        this.enteryFloor =entryFloor;
       
    }

    /**
     * Description: Methods getName, GetEnteryFloor and toString which are pretty much describe themself
     */
    public String getName() { //get person's name
        return name;
    }

    public int getEnteryFloor() { //get beginning floor
        return enteryFloor;
    }
	public String toString(){ //make into a string
		return name + " " + enteryFloor;
	}

   

    
}