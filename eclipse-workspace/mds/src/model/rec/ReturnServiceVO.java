package model.rec;

public class ReturnServiceVO {
	String reitem, resch, redate;
	int recode, memcode;
	public ReturnServiceVO() {
		
	}
	public ReturnServiceVO(String reitem, String resch, int memcode) {
		this.recode = recode;
		this.redate = redate;
		this.reitem = reitem;
		this.resch = resch;
		this.memcode = memcode;
	
	}
	public String getReitem() {
		return reitem;
	}
	public void setReitem(String reitem) {
		this.reitem = reitem;
	}
	public String getResch() {
		return resch;
	}
	public void setResch(String resch) {
		this.resch = resch;
	}
	public String getRedate() {
		return redate;
	}
	public void setRedate(String redate) {
		this.redate = redate;
	}
	public int getRecode() {
		return recode;
	}
	public void setRecode(int recode) {
		this.recode = recode;
	}
	public int getMemcode() {
		return memcode;
	}
	public void setMemcode(int memcode) {
		this.memcode = memcode;
	}

}
