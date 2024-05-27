package model;

import java.util.*;



public class Hospital {
	/**
	 * attributs for the hospital class.
	 */
	private Director director;
	private PhysicianAdministrator physician_administrator;
	public static int Employee_ID = 100;
	public static int Patient_ID = 1000;
	private List<Physician> physicians = new ArrayList<Physician>();
	private List<Physician> Immunologists = new ArrayList<Physician>();
	private List<Physician> Dermatologists = new ArrayList<Physician>();
	private List<Physician> Neurologists = new ArrayList<Physician>();
	private List<Patient> patients = new ArrayList<Patient>();
	private List<PhysicianAdministrator> physicianadministrators = new ArrayList<PhysicianAdministrator>();
	private List<Volunteer> volunteers = new ArrayList<Volunteer>();

	/**
	 * 
	 * @param director taking the director as the input parameter and assigning it
	 */
	public Hospital(Director director) {
		this.director = director;

	}
	/**
	 * 
	 * @return the directior of the hospital.
	 */
	public Director getHospDirector() {
		return this.director;
	}
	/**
	 * 
	 * @param administrator, this method taking the PhysicianAdministrator as the input parameter and add up to the 3 PhysicianAdministrator
	 * @return, true if the adding an PhysicianAdministrator is done perfectly or else return false.
	 */
	public boolean addAdministrator(PhysicianAdministrator administrator) {
		boolean result = false;
		if(this.physicianadministrators.size()<3) {
			this.physicianadministrators.add(administrator);
			result = true;
		}
		return result;
	}
	/**
	 * 
	 * @param physician,this method is taking the physician as the input parameter and do the hiring process of the physician.
	 * in the hiring process first it will check wheather it is in the hospital record or not.
	 * @return, and it return the true if the hiring process of the physician doen perfrctly or else return the false.
	 */
	public boolean hirePhysician(Physician physician) {
		boolean result = true;

		for(int i=0;i<this.physicians.size();i++) {
			if(physician.FirstName.equals(this.physicians.get(i).FirstName) && physician.LastName.equals(this.physicians.get(i).LastName)) {
				result = false;
				break;
			}
		}
		if(result == true) {
			if(this.physicians.size()<75) {
				if(physician.getSpecialty().equals("Immunology")&&this.Immunologists.size()<25) {
					this.physicians.add(physician);
					this.Immunologists.add(physician);
					for(int i = 0;i<this.physicianadministrators.size();i++) {
						if(this.physicianadministrators.get(i).getAdminSpecialtyType().equals("Immunology")) {
							physicianadministrators.get(i).addPhisician(physician);
							break;
						}
					}

				}
				else if(physician.getSpecialty().equals("Dermatology")&&this.Dermatologists.size()<25){
					this.physicians.add(physician);
					this.Dermatologists.add(physician);
					for(int i = 0;i<this.physicianadministrators.size();i++) {
						if(this.physicianadministrators.get(i).getAdminSpecialtyType().equals("Dermatology")) {
							physicianadministrators.get(i).addPhisician(physician);
							break;
						}
					}

				}
				else if(physician.getSpecialty().equals("Neurology")&&this.Neurologists.size()<25){
					this.physicians.add(physician);
					this.Neurologists.add(physician);
					for(int i = 0;i<this.physicianadministrators.size();i++) {
						if(this.physicianadministrators.get(i).getAdminSpecialtyType().equals("Neurology")) {
							physicianadministrators.get(i).addPhisician(physician);
							break;
						}
					}


				}
				else {
					result = false;
				}

			}

		}
		return result;
	}
	/**
	 * 
	 * @param physician, this method is taking the physician as the input parameter and do the resign process of the physician.
	 * in the resign process first it will check wheather it is in the hospital record or not.
	 * then if it is perfect then handle the exception
	 * @return, if the process of the resign pyhisician got successful then return true or else return the false
	 * @throws NoSpecialtyException, handle the exception for the special cases.
	 */
	public boolean resignPhysician(Physician physician) throws NoSpecialtyException{

		switch (physician.getSpecialty()) {
		case "Immunology": {
			if(this.Immunologists.size() == 1)
			{
				throw new NoSpecialtyException("resignVolunteer fail");
			}
			break;
		}
		case "Dermatology":{
			if(this.Dermatologists.size() == 1)
			{
				throw new NoSpecialtyException("resignVolunteer fail");
			}
			break;
		}
		case "Neurology":{
			if(this.Neurologists.size() == 1)
			{
				throw new NoSpecialtyException("resignVolunteer fail");
			}
			break;
		}
		}

		boolean result = false;

		for(int i=0;i<this.physicians.size();i++) {
			if(this.physicians.get(i).FirstName.equals(physician.FirstName)&&this.physicians.get(i).LastName.equals(physician.LastName)) {
				result = true;
				this.physicians.remove(i);
				
				this.arrange(physician);
				this.arrangev(physician);
				break;

			}
		}
		if(result == true) {
			if(physician.getSpecialty().equals("Immunology")&&result == true) {
				for(int i=0;i<this.Immunologists.size();i++) {
					if(this.Immunologists.get(i).FirstName.equals(physician.FirstName)&&this.Immunologists.get(i).LastName.equals(physician.LastName)){
						this.Immunologists.remove(i);
					}
				}
			}
			else if(physician.getSpecialty().equals("Dermatology")&&result == true) {
				for(int i=0;i<this.Dermatologists.size();i++) {
					if(this.Dermatologists.get(i).FirstName.equals(physician.FirstName)&&this.Dermatologists.get(i).LastName.equals(physician.LastName)){
						this.Dermatologists.remove(i);
					}
				}
			}
			else if(physician.getSpecialty().equals("Neurology")&&result == true) {
				for(int i=0;i<this.Neurologists.size();i++) {
					if(this.Neurologists.get(i).FirstName.equals(physician.FirstName)&&this.Neurologists.get(i).LastName.equals(physician.LastName)){
						this.Neurologists.remove(i);
					}
				}
			}
			
		}

		if(result == false) {
			throw new NoSpecialtyException("resignVolunteer fail");
		}

		return result;

	}

/**
 * 
 * @return the arraylist of the physician details of the hospital.
 */

	public List<Physician> extractAllPhysicianDetails(){
		List<Physician> lists= new ArrayList<Physician>();

		lists = this.physicians;

		for(int i=0;i<lists.size();i++) {
			String Name = String.format("%s,%s",lists.get(i).FirstName,lists.get(i).LastName);
			lists.get(i).FirstName = Name;

		}
		Physician Temp;
		for(int i=0;i<lists.size();i++) {
			for(int j=0;j<lists.size();j++) {
				if(lists.get(i).FirstName.compareTo(lists.get(j).FirstName)>0) {
					Temp = lists.get(i);
					lists.set(i,lists.get(j));
					lists.set(j, Temp);

				}
			}
		}
		for(int i=0;i<lists.size();i++) {
			for(int j=0;j<lists.size();j++) {
				if(lists.get(i).LastName.equals(this.physicians.get(j).LastName)) {
					lists.set(i,this.physicians.get(j));
				}
			}
		}


		return lists;
	}
	/**
	 * 
	 * @param patient, this method is taking the patient as the parameter and add it into the patientlist of the hospital
	 * @return, ture if the admit process is done perfectly for the patient and flase if it is not done  perfectly.
	 * @throws NoSpaceException
	 */
	public boolean admitPatient(Patient patient) throws NoSpaceException{
		boolean result = true;

		for(int i=0;i<this.patients.size();i++) {
			if(patient.FirstName.equals(this.patients.get(i).FirstName) && patient.LastName.equals(this.patients.get(i).LastName)) {
				throw new NoSpaceException("Error.....");
			}
		}
		int no=0;
		for(int i=0;i<this.physicians.size();i++) {
			if(this.physicians.get(i).patient.size()!=0) {
				no+= (8 - this.physicians.get(i).patient.size());
			}
			else {
				no+=8;
			}
		}

		if(result == true&&no>0){

			this.patients.add(patient);

			for(int i=0;i<this.physicians.size();i++) {
				if(this.physicians.get(i).extractPatientDetail().size()<8) {
					this.physicians.get(i).assignPatient(patient);
					break;
				}
			}


		}
		else {
			result = false;
			throw new NoSpaceException("Error.....");
		}

		return result;
	}
	/**
	 * 
	 * @return the physician details of the hospital as the array list
	 */
	public List<Patient> extractAllPatientDetails(){
		List<Patient> lists= new ArrayList<Patient>();

		lists = this.patients;

		for(int i=0;i<lists.size();i++) {
			String Name = String.format("%s,%s",lists.get(i).FirstName,lists.get(i).LastName);
			lists.get(i).FirstName = Name;

		}
		Patient Temp;
		for(int i=0;i<lists.size();i++) {
			for(int j=0;j<lists.size();j++) {
				if(lists.get(i).FirstName.compareTo(lists.get(j).FirstName)>0) {
					Temp = lists.get(i);
					lists.set(i,lists.get(j));
					lists.set(j, Temp);

				}
			}
		}
		for(int i=0;i<lists.size();i++) {
			for(int j=0;j<lists.size();j++) {
				if(lists.get(i).LastName.equals(this.patients.get(j).LastName)) {
					lists.set(i,this.patients.get(j));
				}
			}
		}


		return lists;


	}
	/**
	 * 
	 * @param patient, this method is taking the Patient as the input parameter, to discharge the patient from the hospital
	 * @return, true if the discharge process is doen perfectly or return false if it is not done perfectly.
	 */
	public boolean dischargePatient(Patient patient) {
		boolean result = false;

		for(int i=0;i<this.patients.size();i++) {
			if(this.patients.get(i).FirstName.equals(patient.FirstName)&&this.patients.get(i).LastName.equals(patient.LastName)) {
				this.patients.remove(i);
				
				boolean r = false;
				for(int j=0;j<this.physicians.size();j++) {
					for(int k=0;k<this.physicians.get(j).patient.size();k++) {
						if(this.physicians.get(j).patient.get(k).FirstName.equals(patient.FirstName)&&this.physicians.get(j).patient.get(k).LastName.equals(patient.LastName)) {
							this.physicians.get(j).patient.remove(k);
							r = true;
							break;
						}

					}
					if(r == true) {
						break;
					}


				}

				result = true;
				break;

			}
		}


		return result;

	}
	/**
	 * 
	 * @param volunteer, this ,method taking the volunteer as the parameter and check if it is already i  the hospital record or not.
	 * and if not then it will add to the volunteer list means hire the volunteer 
	 * @return, true if the hiring is successfully done of the volunteer and return false if it is not doen successfully.
	 */
	public boolean hireVolunteer(Volunteer volunteer){
		boolean result = true;
		for(int i=0;i<this.volunteers.size();i++) {
			if(volunteer.FirstName.equals(this.volunteers.get(i).FirstName) && volunteer.LastName.equals(this.volunteers.get(i).LastName)) {
				result = false;
				break;
			}
		}
		if(result == true){
			this.volunteers.add(volunteer);


		}
		int no=0;
		for(int i=0;i<this.physicians.size();i++) {
			if(this.physicians.get(i).volunteer.size()!=0) {
				no+= (5 - this.physicians.get(i).volunteer.size());
			}
			else {
				no+=8;
			}
		}

		if(result == true&&no>0){


			for(int i=0;i<this.physicians.size();i++) {
				if(this.physicians.get(i).extractValunterDetail().size()<5) {
					this.physicians.get(i).volunteer.add(volunteer);
					break;
				}
			}


		}



		return result;
	}
	/**
	 * 
	 * @param volunteer, this method taking the Voluteer as the parameter and remove it from the hospital record 
	 * @throws NoVolunteersException, and if the volunteer is not in the hospital record then thro the exception.
	 */
	public void resignVolunteer(Volunteer volunteer) throws NoVolunteersException{
		boolean result = false;


		for(int i=0;i<this.physicians.size();i++)
		{
			for(int j=0;j<this.physicians.get(i).volunteer.size();j++)
			{
				if((Volunteer)this.physicians.get(i).volunteer.get(j) == volunteer)
				{
					if(this.physicians.get(i).volunteer.size()<=1) throw new NoVolunteersException(null);
				}
			}
		}


		for(int i=0;i<this.volunteers.size();i++) {
			if(this.volunteers.get(i).FirstName.equals(volunteer.FirstName)&&this.volunteers.get(i).LastName.equals(volunteer.LastName)) {
				this.volunteers.remove(i);
				result = true;
				break;

			}
		}
		if(result==true) {
			for(int i=0;i<this.physicians.size();i++) {
				for(int j=0;j<this.physicians.get(i).volunteer.size();j++) {
					if(this.physicians.get(i).volunteer.get(j).FirstName.equals(volunteer.FirstName)&&this.physicians.get(i).volunteer.get(j).LastName.equals(volunteer.LastName)) {
						this.physicians.get(i).volunteer.remove(volunteer);
						break;
					}

				}

			}
		}

		if(result == false) {
			throw new NoVolunteersException("resignVolunteer fail");
		}

	}
	/**
	 * 
	 * @return, the list of the volunteerdetails.
	 */
	public List<Volunteer> extractAllVolunteerDetails(){

		return this.volunteers;

	}
	/**
	 * 
	 * @param physician, physician, this method is taking the physician as the parameter and remove the patient of the recently resigned physician and add/assign that patient to the available physician.
	 */
	public void arrange(Physician physician) {

		for(int j=0;j<physician.patient.size();j++) {
			Patient patient = physician.patient.get(j);
			this.patients.remove(patient);
			try
			{
				this.admitPatient(patient);	
			}catch (Exception e) {

			}
		}
	}
/**
 * 
 * @param physician, this method is taking the physician as the parameter and remove the volunteer of the recently resigned physician and add/assign that volunteer to the available physician.
 */
	public void arrangev(Physician physician) {
		for(int j=0;j<physician.volunteer.size();j++) {
			Volunteer volunteer =(Volunteer)physician.volunteer.get(j);
			this.volunteers.remove(volunteer);
			try
			{
				this.hireVolunteer(volunteer);	
			}catch (Exception e) {

			}
		}
	}




}
class Person{
	/**
	 * attributes for the person class
	 */
	protected String FirstName;
	protected String LastName;
	protected int Age;
	protected String Gender;
	protected String Address;

	/**
	 * default constructor
	 */
	public Person() {

	}
	/**
	 * 
	 * @param FirstName, set up a FirstName of the person by overloaded Constructor.
	 * @param LastName, set up a LastName of the person by overloaded Constructor.
	 * @param Age, set up a Age of the person by overloaded Constructor.
	 * @param Gender, set up a Gender of the person by overloaded Constructor.
	 * @param Address, set up a Address of the person by overloaded Constructor.
	 */
	public Person(String FirstName,String LastName,int Age,String Gender,String Address) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Age = Age;
		this.Gender = Gender;
		this.Address = Address;

	}
	/**
	 * 
	 * @return the name of the person
	 */
	public String getName() {
		String result;

		result = String.format("%s, %s", this.FirstName,this.LastName);

		return result;
	}
	/**
	 * 
	 * @return the gender of the person
	 */
	public String getGender() {
		String result;

		result = String.format("%s", this.Gender);

		return result;

	}
	/**
	 * 
	 * @return the Age of the person
	 */
	public int getAge() {
		int result;

		result = this.Age;

		return result;


	}
	/**
	 * 
	 * @return  the address of the person.
	 */
	public String getAddress() {
		String result;

		result = String.format("%s", this.Address);

		return result;


	}
}

class Director extends Administrator{
	/**
	 * PhysicianAdministrator list attribute for the director of the hospital
	 */
	private List <PhysicianAdministrator> PhyAdministrators = new ArrayList<PhysicianAdministrator>();

	/**
	 * default constructor
	 */

	public Director(){

	}
	/**
	 * 
	 * @param FirstName, set a FirstName of the Director.
	 * @param LastName, set a LastName of the Director.
	 * @param Age, set a Age of the Director.
	 * @param Gender, set a Gender of the Director.
	 * @param Address, set a Address of the Director.
	 */
	public Director(String FirstName,String LastName,int Age,String Gender,String Address) {
		super(FirstName,LastName,Age,Gender,Address);
	}
	/**
	 * 
	 * @param PhyAdministrator, assugning the physician administrator.
	 * @return
	 */
	public boolean assignAdministrator(PhysicianAdministrator PhyAdministrator) {

		boolean result = false;
		if(this.PhyAdministrators.size()<3) {
			this.PhyAdministrators.add(PhyAdministrator);
			result = true;
		}
		return result;


	}
	/**
	 * 
	 * @return the PhysicianAdministrator list for the Admins of the hospital
	 */
	public List <PhysicianAdministrator> extractPhysicianAdmins() {

		return this.PhyAdministrators;
	}
}

class Employee extends Person{
	/**
	 * EmployeeID attribute of the Employee.
	 */
	protected int EmployeeID;

	/**
	 * default constructor
	 */
	public Employee() {

	}
	/**
	 * 
	 * @param FirstName, set up a FirstName of the Employee.
	 * @param LastName, set up a LastName of the Employee.
	 * @param Age, set up a Age of the Employee.
	 * @param Gender, set up a Gender of the Employee.
	 * @param Address, set up a Address of the Employee.
	 */
	public Employee(String FirstName,String LastName,int Age,String Gender,String Address) {
		super(FirstName,LastName,Age,Gender,Address);
		this.EmployeeID = Hospital.Employee_ID ;
		Hospital.Employee_ID++;

	}
	/**
	 * 
	 * @return the employee ID of the employee.
	 */
	public int getEmployeeID(){
		return this.EmployeeID;
	}
	
}
class PhysicianAdministrator extends Administrator{
	/**
	 * attributes for the PhysicianAdministrator
	 */
	private String SpecialtyType;
	private List<Physician> Immunologists = new ArrayList<Physician>();
	private List<Physician> Dermatologists = new ArrayList<Physician>();
	private List<Physician> Neurologists = new ArrayList<Physician>();

	/**
	 * default constructor
	 */

	public PhysicianAdministrator() {

	}
	/**
	 * 
	 * @param FirstName, set up FirstName of the physicianAdministrator.
	 * @param LastName, set up LastName of the physicianAdministrator.
	 * @param Age, set up Age of the physicianAdministrator.
	 * @param Gender, set up Gender of the physicianAdministrator.
	 * @param Address, set up Address of the physicianAdministrator.
	 */
	public PhysicianAdministrator(String FirstName,String LastName,int Age,String Gender,String Address) {
		super(FirstName,LastName,Age,Gender,Address);
	}
	/**
	 * 
	 * @param SpecialtyType, set up a speciality of the physicianAdministratior 
	 * @throws IllegalArgumentException, if type is not perfect then handle an exception.
	 */
	public void setAdminSpecialtyType(String SpecialtyType) throws IllegalArgumentException {
		if(SpecialtyType.equals("Immunology")||SpecialtyType.equals("Dermatology")||SpecialtyType.equals("Neurology")) {
			this.SpecialtyType = SpecialtyType;
		}
		else {
			throw new IllegalArgumentException("Error.....");
		}
	}
	public String getAdminSpecialtyType() {
		return this.SpecialtyType;
	}
	/**
	 *  method that return the String of the data of PhysicianAdministrator, generated by concatenation.
	 * and overridden the toString method of object class.
	 */
	public String toString() {
		String result;

		result = String.format("PysicianAdministrator [[[%d,[%s, %s, %d, %s, %s]], %.1f], %s]", 
				this.EmployeeID,
				this.FirstName,
				this.LastName,
				this.Age,
				this.Gender,
				this.Address,
				this.getSalary(),
				this.SpecialtyType);

		return result;
	}
/**
 * 
 * @param physician, add the physician of the specific type to the specific list of physician
 */
	public void addPhisician(Physician physician) {
		if(physician.getSpecialty().equals("Immunology")) {
			this.Immunologists.add(physician);
		}
		else if(physician.getSpecialty().equals("Dermatology")){
			this.Dermatologists.add(physician);
		}
		else if(physician.getSpecialty().equals("Neurology")){
			this.Neurologists.add(physician);
		}

	}
	
	/**
	 * 
	 * @return, the list of the physician of the specific type
	 */
	public List<Physician> extractPhysician(){
		List<Physician> litsOfPhysician = new ArrayList<Physician>();
		if(this.SpecialtyType.equals("Immunology")) {
			litsOfPhysician = this.Immunologists; 
		}
		else if(this.SpecialtyType.equals("Dermatology")) {
			litsOfPhysician = this.Dermatologists; 
		}
		else if(this.SpecialtyType.equals("Neurology")) {
			litsOfPhysician = this.Neurologists; 
		}
		return litsOfPhysician;

	}

}
class SalariedEmployee extends Employee{
	/**
	 * salary attrivute of the SalariedEmployee
	 */
	private double Salary;

	/**
	 * default constructor
	 */
	public SalariedEmployee() {

	}
	/**
	 * 
	 * @param FirstName, set up a FirstName of the SalariedEmployee
	 * @param LastName, set up a LastName of the SalariedEmployee
	 * @param Age, set up a Age of the SalariedEmployee
	 * @param Gender, set up a Gender of the SalariedEmployee
	 * @param Address, set up a Address of the SalariedEmployee
	 */
	public SalariedEmployee(String FirstName,String LastName,int Age,String Gender,String Address) {
		super(FirstName,LastName,Age,Gender,Address);
	}
	/**
	 * 
	 * @param Salary take the salary paramenter as the input and set the salary of the SalariedEmployee
	 */
	public void setSalary(double Salary) {
		this.Salary = Salary;
	}
	/**
	 * 
	 * @return  the salary of the SalariedRmployee
	 */
	public double getSalary() {
		return this.Salary;
	}

}
class Administrator extends SalariedEmployee{
	/**
	 * default constructor
	 */
	public Administrator(){

	}
	/**
	 * 
	 * @param FirstName, set up the FirstName of the Administrator.
	 * @param LastName, set up the LastName of the Administrator.
	 * @param Age, set up the Age of the Administrator.
	 * @param Gender, set up the Gender of the Administrator.
	 * @param Address, set up the Address of the Administrator.
	 */
	public Administrator(String FirstName,String LastName,int Age,String Gender,String Address) {
		super(FirstName,LastName,Age,Gender,Address);
	}


}

class Physician extends SalariedEmployee{
	/**
	 * attributes for the Physician class
	 */
	private String Specialty;
	protected List<Patient> patient= new ArrayList<Patient>();
	protected List<Employee> volunteer = new ArrayList<Employee>();
/**
 * default constructor
 */
	public Physician() {

	}
	/**
	 * 
	 * @param FirstName, set up a FirstName of the Physician by overloaded constructor
	 * @param LastName, set up a LastName of the Physician by overloaded constructor
	 * @param Age, set up a Age of the Physician by overloaded constructor
	 * @param Gender, set up a Gender of the Physician by overloaded constructor
	 * @param Address, set up a Address of the Physician by overloaded constructor
	 */
	public Physician(String FirstName,String LastName,int Age,String Gender,String Address) {
		super(FirstName,LastName,Age,Gender,Address);
	}
	/**
	 * method that return the String of the data of Physician, generated by concatenation.
	 * and overridden the toString method of object class.
	 */
	public String toString() {
		String result;


		result = String.format("Physician [[[%d,[%s, %s, %d, %s, %s]], %.1f]]", 
				this.EmployeeID,
				this.FirstName,
				this.LastName,
				this.Age,
				this.Gender,
				this.Address,
				this.getSalary());

		return result;
	}
	/**
	 * 
	 * @param FirstName, , set up a FirstName of the physician.
	 * @throws IllegalArgumentException, it throw the Exception for no duplication.
	 */
	public void setFirstName(String FirstName) throws IllegalArgumentException {
		this.FirstName = FirstName;
	}
	/**
	 * 
	 * @param Specialty, set up a Speciality of the physician.
	 */
	public void setSpecialty(String Specialty) {

		if(Specialty.equals("Immunology")||Specialty.equals("Dermatology")||Specialty.equals("Neurology")) {
			this.Specialty = Specialty;
		}
		else {
			throw new IllegalArgumentException("Error.....");
		}
	}
	/**
	 * 
	 * @return, return the Speciality of the physician
	 */
	public String getSpecialty() {
		return this.Specialty;
	}
	/**
	 * 
	 * @param LastName, set up a LastName of the physician.
	 */
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	/**
	 * 
	 * @param Address, set up a Address of the physician.
	 */
	public void setAddress(String Address) {
		this.Address = Address;
	}
	/**
	 * 
	 * @param Age, set up a Age of the physician.
	 */
	public void setAge(int Age) {
		this.Age = Age;
	}
	/**
	 * 
	 * @param Gender, set up a Gender of the physician.
	 */
	public void setGender(String Gender) {
		this.Gender = Gender;
	}
	/**
	 * 
	 * @param patient, assigning the patient to the physician
	 */
	public void assignPatient(Patient patient){
		if(this.patient.size()<8) {
			this.patient.add(patient);
		}
		
	}
	/**
	 * 
	 * @param patient, taking the patient as the paramenter of the method and remove it from the patient list
	 * 
	 * @return ture if removinhg is successfully or else return the false
	 */
	
	public boolean removepatient(Patient patient) {
		boolean result = true;
		for(int i=0;i<this.patient.size();i++) {
			if(this.patient.get(i).FirstName.equals(patient.FirstName)&&this.patient.get(i).LastName.equals(patient.LastName)) {
				result = false;
				this.patient.remove(i);
				break;
			}
		}
		return result;
	}
	/**
	 * in this method we assign the volunteer to the physician
	 * @param volunteer, we taking the voulteer object as the parameter
	 * @return
	 */
	public boolean assignVolunteer(Employee volunteer) {
		boolean result = false;
		if(this.volunteer.size()<5) {
			this.volunteer.add(volunteer);
			result=true;
		}
		return result;
	}
	/**
	 * 
	 * @return the list of voluteer which is with current physician.
	 */
	public List<Employee> extractValunterDetail(){
		return this.volunteer;
	}
	/**
	 * checks that wheather or not the it has the maximum number of Volunteer or not 
	 * and return true or false accordingly
	 * @return
	 */
	public boolean hasMaxVolunteers() {
		boolean result = false;

		if(this.volunteer.size()==5) {
			result = true;
		}
		return result;
	}
	/**
	 * checks that wheather or not the it has the maximum number of patient or not 
	 * and return true or false accordingly
	 * @return
	 */
	public boolean hasMaximumpatient() {
		boolean result = false;

		if(this.patient.size()==8) {
			result = true;
		}
		return result;
	}
	/**
	 * 
	 * @return, the list of all patient
	 */
	
	public List<Patient> extractPatientDetail(){
		return this.patient;
	}



}
/**
 * 
 * @author Yugal
 *
 */

class Patient extends Person{
/**
 * attributes for the patient.
 */
	private int PatientID;
	private Physician physician;

	public Patient() {

	}
	/**
	 * 
	 * @param FirstName, set up the FirstName of the physician by overloaded constructor
	 * @param LastName, set up the LastName of the physician by overloaded constructor
	 * @param Age, set up the Age of the physician by overloaded constructor
	 * @param Gender, set up the Gender of the physician by overloaded constructor
	 * @param Address, set up the Address of the physician by overloaded constructor
	 */
	public Patient(String FirstName,String LastName,int Age,String Gender,String Address) {
		super(FirstName,LastName,Age,Gender,Address);
		this.PatientID = Hospital.Patient_ID;
		Hospital.Patient_ID++;

	}
	/**
	 * method that return the String of the data of Patient, generated by concatenation.
	 * and overridden the toString method of object class.
	 */
	public String toString() {
		String result;

		result = String.format("Patient [%d, [%s, %s, %d, %s, %s]]",
				this.PatientID,
				this.FirstName,
				this.LastName,
				this.Age,
				this.Gender,
				this.Address);

		return result;
	}
	/**
	 * 
	 * @param FirstName, set an FirstName of the patient.
	 */
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	/**
	 * 
	 * @param LastName, set an LastName of the patient.
	 */
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	/**
	 * 
	 * @param Age, set an Age of the patient.
	 */

	public void setAge(int Age) {
		this.Age = Age;
	}
	/**
	 * 
	 * @param Gender, set an Gender of the patient.
	 */
	public void setGender(String Gender) {
		this.Gender = Gender;
	}
	/**
	 * 
	 * @param Address, set an address of the patient.
	 */
	public void setAddress(String Address) {
		this.Address = Address;
	}
	/**
	 * 
	 * @return patient ID.
	 */
	public int getPatientID() {
		return this.PatientID;
	}
	/**
	 * 
	 * @param physician, assigned the physician to the patient.
	 */
	public void setAssignedPhysician(Physician physician) {
		this.physician = physician;
		physician.assignPatient(this);


	}
	/**
	 * 
	 * @return, the physician object which is assigned to the patient.
	 */
	public Physician getAssignedPhysician() {
		return this.physician;
	}
	/**
	 * 
	 * @return, true if the patient record is cleared from the hospital successfully.
	 * @return, false if the patient record is not cleared from the hospital successfully.
	 */
	public boolean clearPatientRecord() {
		boolean result;
		if(this.physician==null) {
			result = true;
		}
		else {
			result = this.physician.removepatient(this);
		}

		return result;

	}

}
class Volunteer extends Employee{
/**
 * a default constructor.
 */
	public Volunteer() {

	}
	/**
	 * 
	 * @param FirstName, set up an FirstName to the Volunteer object, by constructor of parent class.
	 * @param LastName, set up an LastName to the Volunteer object, by constructor of parent class.
	 * @param Age, set up an Age to the Volunteer object, by constructor of parent class.
	 * @param Gender, set up an Gender to the Volunteer object, by constructor of parent class.
	 * @param Address, set up an Address to the Volunteer object, by constructor of parent class.
	 */
	public Volunteer(String FirstName,String LastName,int Age,String Gender,String Address) {
		super(FirstName,LastName,Age,Gender,Address); 


	}
	/**
	 * 
	 * @param FirstName, set up an FirstName to the Volunteer object.
	 */
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	/**
	 * 
	 * @param LastName, set up an LastName to the Volunteer object.
	 */
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	/**
	 * 
	 * @param Age, set up an Age to the Volunteer object.
	 */

	public void setAge(int Age) {
		this.Age = Age;
	}
	/**
	 * 
	 * @param Gender, set up an Gender to the Volunteer object.
	 */
	public void setGender(String Gender) {
		this.Gender = Gender;
	}
	/**
	 * 
	 * @param Address, set up an Address to the Volunteer object.
	 */
	public void setAddress(String Address) {
		this.Address = Address;
	}
	/**
	 * method that return the String of the data of Volunteer, generated by concatenation.
	 * and overridden the toString method of object class.
	 */
	public String toString() {
		String result;

		result = String.format("Volunteer [[%d,[%s, %s, %d, %s, %s]]]", 
				this.EmployeeID,
				this.FirstName,
				this.LastName,
				this.Age,
				this.Gender,
				this.Address);

		return result;
	}

}

/**
 * 
 * @author Yugal
 *
 */
class NoSpaceException extends Exception {
	public NoSpaceException(String s) {
		super(s);

	}

}
/**
 * 
 * @author Yugal
 *
 */
class NoVolunteersException extends Exception{
	public NoVolunteersException(String s) {
		super(s);
	}

}

/**
 * 
 * @author Yugal
 *
 */
class NoSpecialtyException extends Exception {
	public NoSpecialtyException(String s) {
		super(s);
	}

}
