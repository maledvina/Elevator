
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File; 
import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
*  Author: Maya Ledvina
*  Date: 11/8/18
 * Description: An elevator simulator, get commands from a file then acts accordingly. Uses Stacks,Queues and SLLists.
 * Creates scanner,elevator, floor lists and floor queues
 */
public class ElevatorAction {
		private static Scanner sc;
		
		private static StackList<Person> elevator=new StackList<Person>();
		
		private static SLList<Person> floor0=new SLList<Person>();
		private static SLList<Person> floor1=new SLList<Person>();
		private static SLList<Person> floor2=new SLList<Person>();
		private static SLList<Person> floor3=new SLList<Person>();
		
		private static QueueList<Person> waiting0=new QueueList<Person>();
		private static QueueList<Person> waiting1=new QueueList<Person>();
		private static QueueList<Person> waiting2=new QueueList<Person>();
		private static QueueList<Person> waiting3=new QueueList<Person>();
		
	
	
	
    /**
     * Description: Main, gets both files and makes sure there is something in them
     * @param text file with input data
     */
    public static void main(String[] args) throws FileNotFoundException {
       

		Scanner input = new Scanner(args[0]);
        String fileName=input.nextLine();
        SLList<Person> people=getListOfPassenger(fileName);
		  
       
		File file = new File(args[1]);
		 sc = new Scanner(file);
	
		if (sc.hasNextLine() && people!=null ){
			elevator(people);
		} else {
			System.out.println("error");
        

    }
}
    /**
     *Description: Reads a text file line by line, and stores the information in an SLList.
     * Everytime a new entry is read from a text file it goes through the Passenger
     * class, and then stored in the SLList.
     *
     * @param textfile with passenger data
     * @return  an SLList containing the passenger name, entry not used for anything really
     */
    public static SLList<Person> getListOfPassenger(String fileName)
    {
        SLList<Person> nothing=new SLList<>();
        try 
        {
            BufferedReader input=new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //probably not necessary but i dont want to mess w/ it 
            while(input.ready())
            {
                String data=input.readLine(); //get line
                 if(data.length()>1){ //while theres stuff in it
                    Scanner scr=new Scanner(data);
                    String name=scr.next(); // name is the next string
                    int entry=scr.nextInt(); // entry is the next int
					addPerson(name,entry); // go to addPerson and add them to SLList's
                    nothing.add(new Person(name, entry)); // add to nothing
                 }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return nothing;
    }
	/**
	* Description: This method adds people to their corresponding list to be checked in the wait command
	* @param name is the name and entry in the starting floor
	*/
	public static void addPerson(String name, int entry){
		Person person = new Person(name, entry); //make new person
		
		if(entry == 0){ //if original floor is 0
			floor0.add(person); // add to floor 0 list
		}
		if(entry == 1){
			floor1.add(person);
		}
		if(entry == 2){
			floor2.add(person);
		}
		if(entry == 3){
			floor3.add(person);
		}
	}
	
	/**
	*Description: checks each line to get what command it is and act accordingly. 
	*Specifics on what each command does are down below.
	@param: list is the original list given however it isn't used
	*/
    public static void elevator( SLList<Person> list){
		String command; // Wait,PickUp etc
		
		while(sc.hasNext()){ //while file has next word
		command =sc.next();
		
		
		try{	
		
	
	
	/**
	*Description: When command equal wait then get floor and name and check to see if name matches the list 
	* then add then to waiting queue and remove from list
	*/


	if ( command.equals("WAIT")){
		int	floor = sc.nextInt();
		String name= sc.next();
			
			if(floor == 0){
				boolean onFloor0 = false;
				for (int i = 0; i < floor0.length(); i++) { //for the length of the SLList
					if (name.equals(floor0.getValue(i).getName())) { //check to see if names match
							onFloor0= true;
							Person name0 = floor0.getValue(i);
							floor0.remove(i); //remove from list
							waiting0.enqueue(name0); //add name to queue

					}
					}
					if(!onFloor0){
						System.out.println(name + " is not on floor 0.");
				}
			}
			if(floor == 1){
				boolean onFloor1 = false;
				for (int i = 0; i < floor1.length(); i++) {
					if (name.equals(floor1.getValue(i).getName())) {		
							onFloor1= true;
							Person name1 = floor1.getValue(i);
							floor1.remove(i);
							waiting1.enqueue(name1);
					}
					}
					if(!onFloor1){
						System.out.println(name + " is not on floor 1.");
				}
			}
			if(floor == 2){
				boolean onFloor2 = false;
				for (int i = 0; i < floor2.length(); i++) {
					if (name.equals(floor2.getValue(i).getName())) {
							onFloor2= true;
							Person name2 = floor2.getValue(i);
							floor2.remove(i);
							waiting2.enqueue(name2);
	
					}
					}
					if(!onFloor2){
						System.out.println(name + " is not on floor 2.");
				}
			}
			if(floor == 3){
				boolean onFloor3 = false;
				for (int i = 0; i < floor3.length(); i++) {
					if (name.equals(floor3.getValue(i).getName())) {
							onFloor3= true;
							Person name3 = floor3.getValue(i);
							floor3.remove(i);
							waiting3.enqueue(name3);	
					}
					}
					if(!onFloor3){
						System.out.println(name + " is not on floor 3.");
					}
			}
			
	}
	
				
	/**
	* Description: when command equals Pick Up, then take the next int as the Floor the elevator is on.
	* When on a specific floor take a person off the queue and put it on the elevator 
	*/
			
		if( command.equals("PICK_UP")){
				int onFloor = sc.nextInt();
				if(onFloor == 0){ //floor 0
					if (elevator.size() != 7){	// max capacity
						while(elevator.size() != 7 && !waiting0.isEmpty()){
						Person w = waiting0.dequeue();	//take person off queue
						elevator.push(w); // put on elevator
				}					
				}
				}
				if(onFloor == 1){
					if (elevator.size() != 7){ // max capacity
						while(elevator.size() != 7 && !waiting1.isEmpty()){						
						Person f = waiting1.dequeue();
						elevator.push(f);
				}
				}
				}
				if(onFloor == 2){
					if (elevator.size() != 7){ // max capacity
						while(elevator.size() != 7 && !waiting2.isEmpty()){
						Person s = waiting2.dequeue();
						elevator.push(s);
						}
					}
				}
				if(onFloor == 3){
					if (elevator.size() != 7){ // max capacity
						while(elevator.size() != 7 && !waiting3.isEmpty()){
						Person t = waiting3.dequeue();
						elevator.push(t);
						
					}
					}
				}
				
		}
		
		
		/**
		* Description: When commands equal inspection then print
		•	Is the elevator empty? How many empty spots are there?
		•	Who will be the next to get off the elevator?
		•	Who will be the next to get on the elevator for each floor?
		*/

		
		if( command.equals("INSPECTION")){
			System.out.println("Elevator Status");
			if(elevator.size() == 0){
				System.out.println("Elevator is empty. There are 7 empty spots left.");
				System.out.println("No one is in the Elevator.");
			}else{
				int left = 7 - elevator.size(); //get empty spots
				System.out.println("Elevator is not empty. There are " + left + " empty spots.");
				Person name =elevator.peek(); // get first name on elevator stack
				System.out.println(name.getName()+ " will be the next person to leave the elevator.");
			}
			
			
			
			if(waiting0.isEmpty()){ //if queue is empty
				System.out.println("There are no people waiting to get to the elevator from floor 0");
			}else{
				Person z = waiting0.front(); // get first name on queue
				System.out.println(z.getName() + " will be the next person to get on the elevator from floor 0");
			}
			if(waiting1.isEmpty()){
				System.out.println("There are no people waiting to get to the elevator from floor 1");
			}else{
				Person fir = waiting1.front();
				System.out.println(fir.getName() + " will be the next person to get on the elevator from floor 1");
			}
			if(waiting2.isEmpty()){
				System.out.println("There are no people waiting to get to the elevator from floor 2");
			}else{
				Person sec = waiting2.front();	
				System.out.println(sec.getName() + " will be the next person to get on the elevator from floor 2");
			}
			if(waiting3.isEmpty()){
				System.out.println("There are no people waiting to get to the elevator from floor 3");
			}else{
				Person thir = waiting3.front();
				System.out.println(thir.getName() + " will be the next person to get on the elevator from floor 3");
			}
		}
			
			/**
			* Description: whe comman equals Drop Off get the floor and number of people getting off.
			* When elevator is at specific floor let the # of people off and add the people on the floor
			*/
		
		if(command.equals("DROP_OFF")){
			int flr = sc.nextInt(); //floor	
			int ppl = sc.nextInt(); // people
			
			if(flr == 0){
				if(elevator.size()<= ppl){ //if the people in elevator is less than or equal to the amount getting off
					while(elevator.size() != 0){ // while elevator doesn't equal 0
						floor0.add(elevator.pop()); //let everyone out and put back on list
						
					}
				}else{ // number is less than people in elevator
					for(int i = 0; i< ppl; i++){ 
						floor0.add(elevator.pop()); //let the number of people out
					}
				}
				while(!waiting0.isEmpty()&& elevator.size() != 7){ //while waiting queue isn't empty and the elevator size isn't 7
					Person atZer = waiting0.dequeue(); // take off queue
					elevator.push(atZer); //put on elevator
					
			}
			}
				
			if(flr == 1){
				if(elevator.size()< ppl){
					while(elevator.size() != 0){
					floor1.add(elevator.pop());
				}
				}else{
					for(int i = 0; i< ppl; i++){
						floor1.add(elevator.pop());
				}
				}
				while(!waiting1.isEmpty()&& elevator.size() != 7){
					Person atOne = waiting1.dequeue();
					elevator.push(atOne);
					
				}	
			}
			if(flr == 2){
				if(elevator.size()< ppl){
					while(elevator.size() != 0){
					floor2.add(elevator.pop());
				}
				}else{
					for(int i = 0; i< ppl; i++){
						floor2.add(elevator.pop());
					}
				}
				while(!waiting2.isEmpty() && elevator.size() != 7){
					Person atTwo = waiting2.dequeue();
					elevator.push(atTwo);
					}
			}
			if(flr == 3){

				if(elevator.size()< ppl){
					while(elevator.size() != 0){
					floor3.add(elevator.pop());
				}
				}else{
				for(int i = 0; i< ppl; i++){
					floor3.add(elevator.pop());
				}
				}
				while(!waiting3.isEmpty()&& elevator.size() != 7){
					Person atThr = waiting3.dequeue();
					elevator.push(atThr);
					}
			}
		
		}
		
	

		
	} catch (InputMismatchException exception){ // just in case the instructions weren't formatted right

			System.out.println("error, check instructions formatting");
		}

		}
	}
	
	}





    

