package model.rec;

public class CareVO {
	String csstartdate, cslastdate, csdate, cgname, cgcom, cgtel, care_loc;
	int cscode, memcode, cgcode, sfcode;

	public CareVO() {

	}

	public String getCgname() {
		return cgname;
	}

	public void setCgname(String cgname) {
		this.cgname = cgname;
	}

	public String getCgcom() {
		return cgcom;
	}

	public void setCgcom(String cgcom) {
		this.cgcom = cgcom;
	}

	public String getCgtel() {
		return cgtel;
	}

	public void setCgtel(String cgtel) {
		this.cgtel = cgtel;
	}

	public String getCare_loc() {
		return care_loc;
	}

	public void setCare_loc(String care_loc) {
		this.care_loc = care_loc;
	}

	public int getSfcode() {
		return sfcode;
	}

	public void setSfcode(int sfcode) {
		this.sfcode = sfcode;
	}

	// 돌봄서비스
	public CareVO(String csstartdate, String cslastdate, int memcode, int cgcode) {
		this.csstartdate = csstartdate;
		this.cslastdate = cslastdate;
		this.memcode = memcode;
		this.cgcode = cgcode;
		this.csdate = csdate;
	}

	// 간병인
	public CareVO(int cgcode, String cgname, String cgcom, String cgtel, String care_loc, int sfcode) {
		this.cgcode = cgcode;
		this.sfcode = sfcode;
		this.cgname = cgname;
		this.cgtel = cgtel;
		this.care_loc = care_loc;
		this.cgcom = cgcom;
	}

	public int getCscode() {
		return cscode;
	}

	public void setCscode(int cscode) {
		this.cscode = cscode;
	}

	public String getCsstartdate() {
		return csstartdate;
	}

	public void setCsstartdate(String csstartdate) {
		this.csstartdate = csstartdate;
	}

	public String getCslastdate() {
		return cslastdate;
	}

	public void setCslastdate(String cslastdate) {
		this.cslastdate = cslastdate;
	}

	public String getCsdate() {
		return csdate;
	}

	public void setCsdate(String csdate) {
		this.csdate = csdate;
	}

	public int getMemcode() {
		return memcode;
	}

	public void setMemcode(int memcode) {
		this.memcode = memcode;
	}

	public int getCgcode() {
		return cgcode;
	}

	public void setCgcode(int cgcode) {
		this.cgcode = cgcode;
	}

}
