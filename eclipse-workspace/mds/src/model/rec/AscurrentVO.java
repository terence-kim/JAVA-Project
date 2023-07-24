package model.rec;

public class AscurrentVO {
	String asdate,assch,machinename;
	int asapcode, memcode;
	public AscurrentVO() {
		
	}
	public AscurrentVO( String assch, String machinename ,int memcode) {
		this.asapcode = asapcode;
		this.asdate = asdate;
		this.assch = assch;
		this.memcode = memcode;
		this.machinename = machinename;
		
	}
	public String getAsdate() {
		return asdate;
	}
	public void setAsdate(String asdate) {
		this.asdate = asdate;
	}
	public String getAssch() {
		return assch;
	}
	public void setAssch(String assch) {
		this.assch = assch;
	}
	public int getAsapcode() {
		return asapcode;
	}
	public void setAsapcode(int asapcode) {
		this.asapcode = asapcode;
	}
	public int getMemcode() {
		return memcode;
	}
	public void setMemcode(int memcode) {
		this.memcode = memcode;
	}
	public String getMachinename() {
		return machinename;
	}
	public void setManame(String machinename) {
		this.machinename = machinename;
	}
	
	
}
