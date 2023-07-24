package model.rec;

public class AsnowVO {
	String ascsdate,ascsconfirm, ascsname, ascstel;
	int ascscode, afsccode, asapcode;
	
	public AsnowVO(){
		
	}
	public AsnowVO(int afsccode) {
		this.ascscode = ascscode;
		this.afsccode = afsccode;
		this.asapcode = asapcode;
		this.ascsdate = ascsdate;
		this.ascsdate = ascsdate;
		this.ascsconfirm = ascsconfirm;
		this.ascsname = ascsname;
		this.ascstel = ascstel;
		
	}
	public String getAscsdate() {
		return ascsdate;
	}
	public void setAscsdate(String ascsdate) {
		this.ascsdate = ascsdate;
	}
	public String getAscsconfirm() {
		return ascsconfirm;
	}
	public void setAscsconfirm(String ascsconfirm) {
		this.ascsconfirm = ascsconfirm;
	}
	public String getAscsname() {
		return ascsname;
	}
	public void setAscsname(String ascsname) {
		this.ascsname = ascsname;
	}
	public String getAscstel() {
		return ascstel;
	}
	public void setAscstel(String ascstel) {
		this.ascstel = ascstel;
	}
	public int getAscscode() {
		return ascscode;
	}
	public void setAscscode(int ascscode) {
		this.ascscode = ascscode;
	}
	public int getAfsccode() {
		return afsccode;
	}
	public void setAfsccode(int afsccode) {
		this.afsccode = afsccode;
	}
	public int getAsapcode() {
		return asapcode;
	}
	public void setAsapcode(int asapcode) {
		this.asapcode = asapcode;
	}
}
