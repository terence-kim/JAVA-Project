package model.rec;

public class ReturnnowVO {
	String ccsdate, ccsconfirm, ccsname, ccstel;
	int ccscode, recode, colcode;
	
	public ReturnnowVO() {
		
	}
	public ReturnnowVO(int colcode) {
		this.ccscode = ccscode;
		this.recode = recode;
		this.colcode = colcode;
		this.ccsdate = ccsdate;
		this.ccsconfirm = ccsconfirm;
		this.ccsname = ccsname;
		this.ccstel = ccstel;
	}
	public String getCcsdate() {
		return ccsdate;
	}
	public void setCcsdate(String ccsdate) {
		this.ccsdate = ccsdate;
	}
	public String getCcsconfirm() {
		return ccsconfirm;
	}
	public void setCcsconfirm(String ccsconfirm) {
		this.ccsconfirm = ccsconfirm;
	}
	public String getCcsname() {
		return ccsname;
	}
	public void setCcsname(String ccsname) {
		this.ccsname = ccsname;
	}
	public String getCcstel() {
		return ccstel;
	}
	public void setCcstel(String ccstel) {
		this.ccstel = ccstel;
	}
	public int getCcscode() {
		return ccscode;
	}
	public void setCcscode(int ccscode) {
		this.ccscode = ccscode;
	}
	public int getRecode() {
		return recode;
	}
	public void setRecode(int recode) {
		this.recode = recode;
	}
	public int getColcode() {
		return colcode;
	}
	public void setColcode(int colcode) {
		this.colcode = colcode;
	}
		
}
