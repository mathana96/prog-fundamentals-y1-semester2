import java.util.ArrayList;
import java.util.List;

/**
 * This class stores information specific to employees 
 * of type Manager which includes a manager bonus and
 * an ArrayList which acts as a department.
 * 
 * @author Mathana Nair Sreedaran
 *
 */
public class Manager extends Employee
{
	private double managerBonus; //Stores the manager bonus
	private List<Employee> department; //Initialise the ArrayList

	/**
	 * Constructor for objects of class Manager
	 * 
	 * @param firstName		First name of a manager
	 * @param secondName	Second name of a manager
	 * @param hourlyRate  Hourly rate of a manager
	 * @param managerBonus Manager bonus 
	 */
	public Manager(String firstName, String secondName, double hourlyRate, double managerBonus)
	{
		super(firstName, secondName, hourlyRate);
		if (managerBonus >= 0) //validation for bonus
		{
			this.managerBonus = managerBonus;
		}
		else
		{
			this.managerBonus = 0;
		}
		this.department = new ArrayList<Employee>();
	}

	

	/**
	 * Returns the number of employees in this manager's department
	 * @return		Size of department
	 */
	public int numberInDept()
	{
		return this.department.size();
	}
	
	/**
	 * Returns a String of all the employees in this manager's department
	 * @return
	 */
	public String employeesInDept()
	{
		String str = "";

		for (int i=0; i<department.size(); i++)
		{
			str += "\n		- " + department.get(i).getFirstName() + " " + department.get(i).getSecondName();		
		}
		return str;
	}
	
	/**
	 * Calculate salary of manager with bonus
	 */
  public double calculateSalary(double numHours)
	{
  	return super.calculateSalary(numHours) + this.managerBonus;
	}
  
  /**
   * Returns a String of the manager's details and position
   */
  public String toString() 
  {
		return "\nPostion: Manager\n" + 
					 super.toString() + 
					 "\nManager bonus: " + 
					 managerBonus +
					 "\nEmployees in department: " +
					 department.size() +
					 employeesInDept(); //Lists the employees in this manager's department
	
  }
  
  /**
   * Add an employee to this manager's department
   * @param employee		The employee to be added
   */
  void addDeptEmployee(Employee employee)
	{
 
  	department.add(employee);
	}

  /**
	 * Setters and getters
	 */
	public double getBonus()
	{
		return managerBonus;
	}

	public void setBonus(double newBonus)
	{
		if (newBonus >= 0)
		{
			this.managerBonus = newBonus;
		}

	}
	
	public void setDept(ArrayList<Employee> newDepartment)
	{
		this.department = newDepartment;
	}
	
	public List<Employee> getDept()
  {
	  return department;
	}

}
