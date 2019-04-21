package main.webapp;

public class Employee {

	private String empName;
	private double empNumber;
	private String empState;
	private double empZip;
	private double empAge;

	public Employee(){
		
	}
	public Employee(String empname, double empnum, String empstate, double empzip, double empage) {
		this.empName = empname;
		this.empNumber  = empnum;
		this.empState = empstate;
		this.empZip = empzip;
		this.empAge = empage;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [name=" + empName + ", Employee number=" + empNumber
				+ ", state=" + empState + ", zip=" + empZip + ", age="
				+ empAge + "]";
	}
	
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empNumber
	 */
	public double getEmpNumber() {
		return empNumber;
	}

	/**
	 * @param empNumber the empNumber to set
	 */
	public void setEmpNumber(double empNumber) {
		this.empNumber = empNumber;
	}

	/**
	 * @return the empState
	 */
	public String getEmpState() {
		return empState;
	}

	/**
	 * @param empState the empState to set
	 */
	public void setEmpState(String empState) {
		this.empState = empState;
	}

	/**
	 * @return the empZip
	 */
	public double getEmpZip() {
		return empZip;
	}

	/**
	 * @param empZip the empZip to set
	 */
	public void setEmpZip(double empZip) {
		this.empZip = empZip;
	}

	/**
	 * @return the empAge
	 */
	public double getEmpAge() {
		return empAge;
	}

	/**
	 * @param empAge the empAge to set
	 */
	public void setEmpAge(double empAge) {
		this.empAge = empAge;
	}

}
