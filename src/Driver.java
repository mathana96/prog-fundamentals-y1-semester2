import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handles the input of the user with the system performing 
 * the necessary actions to produce the desired output using a menu system.
 * This class has an ArrayList of all the employees created.
 * 
 * @author Mathana Nair Sreedaran
 */

public class Driver
{
	private Scanner	input;
	private List<Employee> employees;

	/**
	 * Main method initialising the menu system.
	 */
	public static void main(String[] args)
	{
		Driver driver = new Driver();
		driver.run();
	}

	/**
	 * Constructor for Driver initialising the Scanner
	 * utility and ArrayList of employees which stores
	 * objects of type Employee.
	 */
	public Driver()
	{
		input = new Scanner(System.in);
		employees = new ArrayList<Employee>();
	}

	/**
	 * Presents the user with the main menu of the system
	 * and returns their selection.
	 * @return The user's menu selection
	 */
	public int mainMenu()
	{
		
		int option = 0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("\n============= MAIN MENU =============\n");
				System.out.println("1)	Add an employee");
				System.out.println("2)	Remove an employee");
				System.out.println("3)	List employees");
				System.out.println("4)	Assign employee to a department");
				System.out.println("5)	Remove employee from a department");
				System.out.println("6)	Calculate salary");
				System.out.println("7)	Edit employee details");
				System.out.println("\n0)	Exit system");
				option = input.nextInt();
				errorFree = true;
				
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
			
		} 
		
		System.out.println(option);
		return option;
	}	

	/**
	 * Handles the user's selection from the main menu 
	 * by calling methods corresponding to the user's
	 * selection.
	 */
	public void run()
	{
		int option = mainMenu();

		while (option != 0)
		{
			switch (option)
			{
			case 1:
				workerRun();
				break;
				
			case 2:
				workerRemoveRun();
				break;

			case 3:
				System.out.println(listAll());
				break;
				
			case 4:
				addToDept();
				break;
				
			case 5:
				removeDept();
				break;

			case 6:
				salaryRun();
				break;
				
			case 7:
				editDetails();
				break;

			default:
				System.out.println("Invalid option. Please try again.");
			}
			option = mainMenu();
		}
		System.out.println("Exiting system");
		System.exit(0);
	}
	
	/**
	 * Presents the user with a sub menu of the system called the worker menu
	 * and returns their selection.
	 * @return				The user's menu selection
	 */
	public int workerMenu()
	{
		int option = 0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("\n============= WORKER MENU =============\n");
				System.out.println("Please select which type of employee you would like to add");
				System.out.println("1)	Manager");
				System.out.println("2)	Sales worker");
				System.out.println("3)	Temporary worker");
				System.out.println("\n0)	Return to main menu");
				option = input.nextInt();
				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}
		return option;		
	}
	
	/**
	 * Handles the user's selection from the worker menu 
	 * by calling methods corresponding to the user's
	 * selection.
	 */
	public void workerRun() 
	{
		int option = workerMenu();

		while (option != 0)
		{
			switch (option)
			{
			case 1:
				addManager();
				option = 0;
				break;

			case 2:
				addSalesWorker();
				option = 0;
				break;	
				
			case 3:
				addTempWorker();
				option = 0;
				break;

			default:
				System.out.println("Invalid option. Please try again.\n");
				option = workerMenu();
			}		
		}
	}
	
	/**
	 * Removes an employee from the ArrayList employees and from any department they belong to
	 */
	public void workerRemoveRun()
	{
		if (employees.size() > 0)
		{
			//Lists all the employees 
			System.out.println(listAll());
			int selectedEmployee = 0;
			boolean errorFree = false;
			while (!errorFree)
			{
				try
				{
					System.out.println("Please select employee you want to remove (Employee ID): ");
					selectedEmployee = input.nextInt() - 1;
					
					if (selectedEmployee < employees.size() && selectedEmployee >= 0) //Avoid outofboundsexception
					{
						errorFree = true;		
					}	
					else
					{
						System.out.println("Invalid option. Please try again. ");
					}
				}	
				catch (Exception e)
				{
					input.nextLine();
					System.out.println("\nOnly numeric values accepted. Please try again.\n");
				}

			}		
			//Create an object of the selected employee from the ArrayList
		  
		  
		  Employee fired = employees.get(selectedEmployee);
		  
		  //If employee to be fired is in a department, remove the employee from department
		  for (Employee manager: employees)
		  {
		  	if (manager instanceof Manager)
		  	{
		  		int staffNum = 0;
		  		int toBeFired = 0;
		  		
		  		Manager man = (Manager) manager;
		  		for (Employee staff: man.getDept())
		  		{	  			
		  			if (staff.getFirstName() == fired.getFirstName() && staff.getSecondName() == fired.getSecondName())
		  			{
		  				toBeFired = staffNum;
		  			}	
	  				staffNum++;
		  		}
		  		//Only execute if there is anybody in any department
		  		if (staffNum > 0)
		  		{
		  			man.getDept().remove(toBeFired);
		  		}
		  		
		  	}
		  	
		  }
		  
		  employees.remove(selectedEmployee);
		  System.out.println("\nEmployee sucessfuly removed.\n");
			
		}
		else
		{
			System.out.println("\nNo employees found.\n");
		}
	}

	/**
	 * Adds an employee of type Manager to the ArrayList "employees".
	 */
	public void addManager()
	{
	
		input.nextLine();
		System.out.println("First name: ");
		String firstName = input.nextLine();
		
		System.out.println("Second name: ");
		String secondName = input.nextLine();

		double hourlyRate = 0.0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
    		System.out.println("Hourly rate: ");
    		hourlyRate = input.nextDouble();
    		errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}	
		
		double bonus = 0.0;
	  errorFree = false;
	  while (!errorFree)
		{
			try
			{
    		System.out.println("Manager bonus: ");
    		bonus = input.nextDouble();
    		errorFree = true;

			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}

		employees.add(new Manager(firstName, secondName, hourlyRate, bonus));

	}
	
	/**
	 * Adds an employee of type SalesWorker to the ArrayList "employees".
	 */
	public void addSalesWorker() 
	{
		input.nextLine();
		System.out.println("First name: ");
		String firstName = input.nextLine();

		System.out.println("Second name: ");
		String secondName = input.nextLine();

		double hourlyRate = 0.0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
    		System.out.println("Hourly rate: ");
    		hourlyRate = input.nextDouble();
    		errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}
		
		double bonus = 0.0;
	  errorFree = false;
	  while (!errorFree)
		{
			try
			{
    		System.out.println("Sales bonus percentage (only values between 0 and 20): ");
    		bonus = input.nextDouble();
    		errorFree = true;

			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		} 

		employees.add(new SalesWorker(firstName, secondName, hourlyRate, bonus));

	}
	
	/**
	 * Adds an employee of type TempWorker to the ArrayList "employees".
	 */
	public void addTempWorker() 
	{
		input.nextLine();
		System.out.println("First name: ");
		String firstName = input.nextLine();

		System.out.println("Second name: ");
		String secondName = input.nextLine();

		double hourlyRate = 0.0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
    		System.out.println("Hourly rate: ");
    		hourlyRate = input.nextDouble();
    		errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}	

		employees.add(new TempWorker(firstName, secondName, hourlyRate));

	}

	/**
	 * Adds an employee of either type SalesWorker or TempWorker
	 *  to the department of a particular Manager.
	 */
	public void addToDept()
	{
		int present = 0;
		//Check if employee of type Manager is present in the employees ArrayList
		for (Employee employee: employees)
		{
			if ((employee instanceof Manager))
			{
				present++;
			}
		}
		
		//Execute if the total number of employees are more than the number of Managers and there is at least 1 manager
		if ((employees.size() > present) && (present > 0))
		{
			//Lists all the employees 
			System.out.println(listAll());
			int selectedEmployee = 0;
			int selectedManager = 0;
			boolean errorFree = false;
			while (!errorFree)
			{
				try
				{
					System.out.println("Please select employee (Either Sales Worker or Temp Employee) to be assigned (Employee ID): ");
					selectedEmployee = input.nextInt() - 1;
					if (selectedEmployee < employees.size() && selectedEmployee >= 0) //Avoid outofbounds
					{

						if (employees.get(selectedEmployee).getClass().equals(Manager.class))
						{
							System.out.println("Cannot add Manager to department. Please try again.\n");
						}
						else
						{
							errorFree = true;
						}
					}	
					else
					{
						System.out.println("Invalid option. Please try again. ");
					}
				}	
				catch (Exception e)
				{
					input.nextLine();
					System.out.println("\nOnly numeric values accepted. Please try again.\n");
				}

			}		
			//Create an object of the selected employee from the ArrayList
			Employee employee = employees.get(selectedEmployee);
	
				errorFree = false;
				while (!errorFree)
				{
					//Lists all managers
					try
					{
						input.nextLine();
						System.out.println("\n============= MANAGER LIST =============");
						System.out.println(listManagers());
						System.out.println("Please select the manager whose department you wish to assign the employee to: ");
						selectedManager = input.nextInt() - 1;
						//Catches Outofboundsexception
						if (selectedManager < employees.size() && selectedEmployee >= 0)
						{
							if (employees.get(selectedManager).getClass().equals(Manager.class))
							{
								errorFree = true;
							}
							else
							{
								System.out.println("Invalid option. Please try again. ");
							}
							
						}	
						else
						{
							System.out.println("Invalid option. Please try again. ");
						}
					}
					catch (Exception e)
					{
						System.out.println("\nOnly numeric values accepted. Please try again.\n");
					}

				}
				
				Manager man = (Manager) employees.get(selectedManager);
					//Checks if the employee has already been assigned a department
					int added = 0;
					for (Employee aManager: employees)
					{
						if (aManager instanceof Manager)
						{
							Manager chosen = (Manager) aManager;
							
							for (Employee inChosenDepartment: chosen.getDept())
							{
								if ((employee.getFirstName() == inChosenDepartment.getFirstName()) && (employee.getSecondName() == inChosenDepartment.getSecondName()))
								{
									added++;
								}
							}
						}
					}
					//Execute if employee is not in any department
					if (added == 0)
					{
						man.addDeptEmployee(employee);
						System.out.println("\nEmployee successfully added to department\n");
					}
					else
					{
						input.nextLine();
						System.out.println("Employee already added to a department. Please try again. ");
					}

				}
				else
				{
					System.out.println("\nError\n-Existing employees not found.\n-Existing managers not found. At least 1 manager required to add employee to department\n-Cannot create department with only Manager(s)");
				}			    			
			}


	/**
	 * Removes an employee from a department
	 */
	public void removeDept()
	{
		int present = 0;
		//Check if there are any employees assigned to any department
		
		 for (Employee employee: employees)
		  {
		  	if (employee instanceof Manager)
		  	{
		  		Manager man = (Manager) employee;
		  		if (man.getDept().size() != 0)
		  		{
		  			present++;
		  		}	  		
		  	}  	
		  }


		//Execute if there is at least 1 employee in a department
		if (present > 0)
		{			
			System.out.println(listManagers());
			int selectedManager = 0;
			boolean errorFree = false;
			while (!errorFree)
			{
				try
				{
					System.out.println("Please select which department (Manager) the employee is in: ");
					selectedManager = input.nextInt() - 1;
					if (selectedManager < employees.size() && selectedManager >= 0) //Avoid outofbounds
					{
						if (employees.get(selectedManager).getClass().equals(Manager.class))
						{
							errorFree = true;		
						}
						else
						{
							System.out.println("Invalid option. Please try again. ");
						}
					}	
					else
					{
						System.out.println("Invalid option. Please try again. ");
					}
				}	
				catch (Exception e)
				{
					input.nextLine();
					System.out.println("\nOnly numeric values accepted. Please try again.\n");
				}

			}

			Manager man = (Manager) employees.get(selectedManager);
			int i = 0;
			for (Employee staff: man.getDept())
			{
				System.out.println("Department ID: " + "[" + (i+1) + "] " + staff.getFirstName() + " " + staff.getSecondName());
				i++;
			}

			int selectedEmployee = 0;
			errorFree = false;
			while (!errorFree)
			{
				try
				{
					System.out.println("Please select employee (Employee ID) to be removed: ");
					selectedEmployee = input.nextInt() - 1;
					if (selectedEmployee < man.numberInDept() && selectedEmployee >= 0) //Avoid outofbounds
					{
						errorFree = true;								
					}	
					else
					{
						System.out.println("Invalid option. Please try again. ");
					}
				}	
				catch (Exception e)
				{
					input.nextLine();
					System.out.println("\nOnly numeric values accepted. Please try again.\n");
				}

			}

			man.getDept().remove(selectedEmployee);
			System.out.println("\nEmployee successfully removed.\n");			

		}
		else
		{
			System.out.println("\nNo employees in a department found.\n");			

		}

	}
	/**
   * return a String listing all the employees
   * @return A string version of the employees ArrayList
   */
	public String listAll() 
	{
		String list = "";
		int i = 0;
		list += "\n============= EMPLOYEE LIST =============\n";
		if (!employees.isEmpty())
		{
			for (Employee employee : employees)
			{
				
				list += "\n===============================\nWorker ID: " + (i+1) + employee.toString() + "\n===============================\n"; 	
				i++;
			}
			
		}
		else
		{
			list += "\nNo employees found\n";
		}
		return list;
	}	
	
	/**
	 * return a String listing all the employees
	 * @return A String of employees of type Manager in employees ArrayList
	 */
	public String listManagers() 
		{
			String list = "";
			int i = 0;
			
			for (Employee employee : employees)
			{
				
				if (employee.getClass().equals(Manager.class))
				{
					
					list += "\n===============================\nWorker ID: " + (i+1) + employee.toString() + "\n===============================\n"; 
				}
				i++;
			}	
		return list;
		}

	/**
	 * Presents the user with the salary menu of the system
	 * and returns their selection.
	 * @return The user's menu selection
	 */
	public int salaryMenu()
	{
		int option = 0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("\n============= SALARY MENU =============\n");
				System.out.println("1)	Salary of all employees");
				System.out.println("2)	Salary of only Managers");
				System.out.println("3)	Salary of only Sales Workers");
				System.out.println("4)	Salary of only Temporary Workers");
				System.out.println("5)	Salary of individual employee");
				System.out.println("6)	Salary summary");
				System.out.println("\n0)	Back to main menu");
				option = input.nextInt();
				errorFree = true;
				
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
			
		}
		
		System.out.println(option);
		return option;	
	}
	
	/**
	 * Handles the user's selection from the salary menu 
	 * by calling methods corresponding to the user's
	 * selection.
	 */
	public void salaryRun()
	{
		if (!employees.isEmpty())
		{
			int option = salaryMenu();

			while (option != 0)
			{
				switch (option)
				{
				case 1:
					System.out.println(allSalary());
					option = 0;
					break;

				case 2:
					System.out.println(managerSalary());
					option = 0;
					break;	
					
				case 3:
					System.out.println(salesSalary());
					option = 0;
					break;
					
				case 4:
					System.out.println(tempSalary());
					option = 0;
					break;
					
				case 5:
					System.out.println(individualSalary());
					option = 0;
					break;
					
				case 6:
					System.out.println(summarySalary());
					option = 0;
					break;

				default:
					System.out.println("Invalid option. Please try again.\n");
					option = salaryMenu();
				}		
			}
		}
		else
		{
			System.out.println("\nNo employees found\n");
		}
		
	}
	
	/**
	 * return a String listing all the employees' details 
	 * including their individual salary. Total salary is listed 
	 * at the bottom
	 * @return A String of employees including their salary
	 */
	public String allSalary()
	{
		String salary = "";
		double totalSalary = 0.0;
		int i = 0;
		double numHours = 0.0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("Enter number of hours worked: \n");
				numHours = input.nextDouble();
				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}
		salary += "\n============= SALARY OF ALL EMPLOYEES =============\n";
		for(Employee employee: employees)
		{
			salary += "\n===============================\nWorker ID: " + (i+1) + employee.toString();
			totalSalary += employee.calculateSalary(numHours);
			if (employee instanceof Manager) //Add manager bonus if of type Manager
			{
				salary += "\nManager bonus: " + ((Manager) employee).getBonus();
			}
			else if (employee instanceof SalesWorker) //Add sales performance bonus if of type SalesWorker
			{
				salary += "\nSales bonus percentage: " + ((SalesWorker) employee).getBonus();
			}
			salary += "\n\nSalary: " + employee.calculateSalary(numHours);	
			salary += "\n===============================";
			i++;
		}
		salary += "\nTOTAL SALARY: " + totalSalary;
		return salary;
	}
	
	/**
	 * return a String listing only employees of type Manager's
	 * details, individual salary and total salary
	 * @return A String of employees of type Manager including their salary
	 */
	public String managerSalary()
	{
		String salary = "";
		double totalSalary = 0.0;
		int i = 0;
		double numHours = 0.0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("Enter number of hours worked: \n");
				numHours = input.nextDouble();
				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}
		salary += "\n============= SALARY OF ALL MANAGERS =============\n";
		for(Employee employee: employees)
		{
			if (employee instanceof Manager)
			{
				salary += "\n===============================\nWorker ID: " + (i+1) + employee.toString();
				salary += "\nManager bonus: " + ((Manager) employee).getBonus();
				salary += "\n\nSalary: " + employee.calculateSalary(numHours);	
				salary += "\n===============================";
				totalSalary += employee.calculateSalary(numHours);
			}
			
			i++;
		}
		salary += "\nTOTAL SALARY: " + totalSalary;
		return salary;
		
	}
	
	/**
	 * return a String listing only employees of type SalesWorker's
	 * details, individual salary and total salary
	 * @return A String of employees of type SalesWorker including their salary
	 */
	public String salesSalary()
	{
		String salary = "";
		double totalSalary = 0.0;
		int i = 0;
		double numHours = 0.0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("Enter number of hours worked: \n");
				numHours = input.nextDouble();
				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}
		salary += "\n============= SALARY OF ALL SALES WORKERS =============\n";
		for(Employee employee: employees)
		{
			if (employee instanceof SalesWorker)
			{
				salary += "\n===============================\nWorker ID: " + (i+1) + employee.toString();
				salary += "\nSales bonus percentage: " + ((SalesWorker) employee).getBonus();
				salary += "\n\nSalary: " + employee.calculateSalary(numHours);	
				salary += "\n===============================";
				totalSalary += employee.calculateSalary(numHours);
			}
			
			i++;
		}
		salary += "\nTOTAL SALARY: " + totalSalary;
		return salary;	
	}
	
	/**
	 * return a String listing only employees of type TempWorker's
	 * details, individual salary and total salary
	 * @return A String of employees of type TempWorker including their salary
	 */
	public String tempSalary()
	{
		String salary = "";
		double totalSalary = 0.0;
		int i = 0;
		double numHours = 0.0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("Enter number of hours worked: \n");
				numHours = input.nextDouble();
				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}
		salary += "\n============= SALARY OF ALL TEMPORARY WORKERS =============\n";
		for(Employee employee: employees)
		{
			if ((!(employee instanceof Manager)) && (!(employee instanceof SalesWorker)))
			{
				salary += "\n===============================\nWorker ID: " + (i+1) + employee.toString();
				salary += "\n\nSalary: " + employee.calculateSalary(numHours);	
				salary += "\n===============================";
				totalSalary += employee.calculateSalary(numHours);
			}
			
			i++;
		}
		salary += "\nTOTAL SALARY: " + totalSalary;
		return salary;
		
	}
	
	/**
	 * return a String listing an individual employee's
	 * details and salary
	 * @return A String of individual employee details including their salary
	 */
	public String individualSalary()
	{
		System.out.println(listAll());
		int selectedEmployee = 0;
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
    		System.out.println("Please select employee (Employee ID): ");
    		selectedEmployee = input.nextInt() - 1;
    		if (selectedEmployee < employees.size() && selectedEmployee >= 0)
    		{			
    			errorFree = true;
    		}	
    		else
    		{
    			System.out.println("Invalid option. Please try again. ");
    		}
			}	
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
			
		}
		
		Employee employee = employees.get(selectedEmployee);
		String salary = "";
		double numHours = 0.0;
		
		errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("Enter number of hours worked: \n");
				numHours = input.nextDouble();
				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}
		
		salary += "\n============= SALARY OF " + employee.getFirstName() + " =============\n";
		salary += "\n===============================\nWorker ID: " + (selectedEmployee+1) + employee.toString();
			
		if (employee instanceof Manager)
		{
			salary += "\nManager bonus: " + ((Manager) employee).getBonus();
		}
		else if (employee instanceof SalesWorker)
		{
			salary += "\nSales bonus percentage: " + ((SalesWorker) employee).getBonus();
		}
		salary += "\n\nSalary: " + employee.calculateSalary(numHours);	
		salary += "\n===============================";
		
		return salary;
	}
	
	/**
	 * return a String with a summary of total salaries of each Employee subclass
	 * @return A String of total salaries of employees and each subclass
	 */
	public String summarySalary()
	{
		String salary = "";
		double numHours = 0.0;
		double managerSalary = 0.0;
		double salesSalary = 0.0;
		double tempSalary = 0.0;
		double totalSalary = 0.0;
		
		boolean errorFree = false;
		while (!errorFree)
		{
			try
			{
				System.out.println("Enter number of hours worked: \n");
				numHours = input.nextDouble();
				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("\nOnly numeric values accepted. Please try again.\n");
			}
		}
		
		salary += "\n============= SALARY SUMMARY =============\n";
		for(Employee employee: employees)
		{
			totalSalary += employee.calculateSalary(numHours);	
			if (employee instanceof Manager)
			{
				managerSalary += employee.calculateSalary(numHours);
			}
			else if (employee instanceof SalesWorker)
			{
				salesSalary += employee.calculateSalary(numHours);
			}
			else
			{
				tempSalary += employee.calculateSalary(numHours);
			}
			
		}
		salary += "\nTOTAL MANAGER SALARY: " + managerSalary;
		salary += "\nTOTAL SALES WORKER SALARY: " + salesSalary;
		salary += "\nTOTAL TEMPORARY WORKER SALARY: " + tempSalary;
		salary += "\n\nTOTAL SALARY: " + totalSalary;

		return salary;
	}

	/**
	 * Allows user to select and edit an existing employee's details
	 */
	public void editDetails()
	{
		if (!employees.isEmpty()) //Checks if there are existing employees
		{
			System.out.println(listAll());
			int selectedEmployee = 0;
			boolean errorFree = false;
			while (!errorFree)
			{
				try
				{
					System.out.println("Please select employee (Employee ID): ");
					selectedEmployee = input.nextInt() - 1;
					if (selectedEmployee < employees.size() && selectedEmployee >= 0)
					{
						errorFree = true;
					}	
					else
					{
						System.out.println("Invalid option. Please try again. ");
					}
				}	
				catch (Exception e)
				{
					input.nextLine();
					System.out.println("\nOnly numeric values accepted. Please try again.\n");
				}

			}		
			Employee employee = employees.get(selectedEmployee);
			
			System.out.println("Update the following fields:\n");
			
			input.nextLine();
			System.out.println("First Name: ");
			String firstName = input.nextLine();
		  employee.setFirstName(firstName);

			System.out.println("Second Name: ");
			String secondName = input.nextLine();
		  employee.setSecondName(secondName);

			double hourlyRate = 0.0;
			errorFree = false;
			while (!errorFree)
			{
				try
				{
	    		System.out.println("Hourly rate: ");
	    		hourlyRate = input.nextDouble();
	  		  employee.setHourlyRate(hourlyRate);
	    		errorFree = true;
				}
				catch (Exception e)
				{
					input.nextLine();
					System.out.println("\nOnly numeric values accepted. Please try again.\n");
				}
			}
			
			if (employee.getClass().equals(Manager.class)) //Present field to edit manager bonus is of type Manager
			{
				double bonus = 0.0;
			  errorFree = false;
			  while (!errorFree)
				{
					try
					{
		    		System.out.println("Manager bonus: ");
		    		bonus = input.nextDouble();
		    		errorFree = true;

					}
					catch (Exception e)
					{
						input.nextLine();
						System.out.println("\nOnly numeric values accepted. Please try again.\n");
					}
				}
				Manager man = (Manager) employees.get(selectedEmployee);
				man.setBonus(bonus);
			}
			else if (employee.getClass().equals(SalesWorker.class)) //Present field to edit sales performance bonus is of type SalesWorker
			{
				double bonus = 0.0;
			  errorFree = false;
			  while (!errorFree)
				{
					try
					{
		    		System.out.println("Sales bonus percentage (only values between 0 and 20): ");
		    		bonus = input.nextDouble();
		    		errorFree = true;

					}
					catch (Exception e)
					{
						input.nextLine();
						System.out.println("\nOnly numeric values accepted. Please try again.\n");
					}
				} 
			  
			  SalesWorker sales = (SalesWorker) employees.get(selectedEmployee);
				sales.setBonus(bonus);
			}
			System.out.println("\nSystem updated");
		}
		else
		{
			System.out.println("\nNo employees found\n");
		}
		
	}	

}
