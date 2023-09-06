package model.rec;

public class AsVO {
	String afscname, afscloc, afsctel, URL, afsctime, afscprice;
	int afsccode;
	public AsVO() {
		
	}

	public AsVO(String afscname, String afscloc, String URL, String afsctime, String afscprice, String afsctel, int afsccode ) {
		this.afsccode = afsccode;
		this.afscname = afscname;
		this.afscloc = afscloc;
		this.URL = URL;
		this.afsctime = afsctime;
		this.afscprice = afscprice;
		
		
	}

	public String getAfscname() {
		return afscname;
	}

	public void setAfscname(String afscname) {
		this.afscname = afscname;
	}

	public String getAfscloc() {
		return afscloc;
	}

	public void setAfscloc(String afscloc) {
		this.afscloc = afscloc;
	}

	public String getAfsctel() {
		return afsctel;
	}

	public void setAfsctel(String afsctel) {
		this.afsctel = afsctel;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		URL = URL;
	}

	public String getAfsctime() {
		return afsctime;
	}

	public void setAfsctime(String afsctime) {
		this.afsctime = afsctime;
	}

	public String getAfscprice() {
		return afscprice;
	}

	public void setAfscprice(String afscprice) {
		this.afscprice = afscprice;
	}

	public int getAfsccode() {
		return afsccode;
	}

	public void setAfsccode(int afsccode) {
		this.afsccode = afsccode;
	}

}

