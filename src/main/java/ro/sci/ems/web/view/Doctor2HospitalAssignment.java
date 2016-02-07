package ro.sci.ems.web.view;

public class Doctor2HospitalAssignment {

	private Long doctorID;
	private Long hospitalID;
	public Long getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(Long doctorID) {
		this.doctorID = doctorID;
	}
	public Long getHospitalID() {
		return hospitalID;
	}
	public void setHospitalID(Long hospitalID) {
		this.hospitalID = hospitalID;
	}
	@Override
	public String toString() {
		return "Doctor2HospitalAssignment [doctorID=" + doctorID + ", hospitalID=" + hospitalID + "]";
	}
	
	
}
