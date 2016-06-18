
public class Car {
	String licenceNumber;
	int parkingDuration;
	double[] parkingCharge={7.50,4.50,3.50,2.50};
	 Car() {
		
	}
	public Car(String licencePlate, String hoursParked) {
		// TODO Auto-generated constructor stub
		this.licenceNumber=licencePlate;
		this.parkingDuration=Integer.parseInt(hoursParked);
	}
	/**
	 * @return the licenceNumber
	 */
	public String getlicenceNumber() {
		return licenceNumber;
	}
	/**
	 * @param licenceNumber the licenceNumber to set
	 */
	public void setlicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}
	/**
	 * @return the parkingDuration
	 */
	public int getparkingDuration() {
		return parkingDuration;
	}
	/**
	 * @param parkingDuration the parkingDuration to set
	 */
	public void setHours(int parkingDuration) {
		this.parkingDuration = parkingDuration;
	}
	/**
	 * @return the parkingCharge
	 */
	public double getparkingCharge(int i) {
		return this.parkingCharge[i];
	}
	/**
	 * @param parkingCharge the parkingCharge to set
	 */
	public void setFee(double[] parkingCharge) {
		this.parkingCharge = parkingCharge;
	}
	 
	public double calculateParkingCharge(){
		int hours = this.getparkingDuration();
		double fee = 0;
		for (int i =1; i <= hours; i++) {
			if(i==1){
				fee=fee+this.getparkingCharge(0);
			}else if(i>=2&&i<=4){
				fee=fee+this.getparkingCharge(1);
			}else if(i>=5&&i<=8){
				fee=fee+this.getparkingCharge(2);
			}else{
				fee=fee+this.getparkingCharge(3);
			}
		}
		return fee;}
}




 