package model.rec;

public class ReturnVO {
	String colinfo, coltel, colloc, URL, opentime, colprice;
	int colcode;
	public ReturnVO() {
		
	}
	public ReturnVO(String colinfo, String colloc, String coltel, String URL, String opentime, String colprice, int colcode) {
		this.colcode = colcode;
		this.colinfo = colinfo;
		this.coltel = coltel;
		this.colloc = colloc;
		this.URL = URL;
		this.opentime = opentime;
		this.colprice = colprice;
		
	}
	public String getColinfo() {
		return colinfo;
	}
	public void setColinfo(String colinfo) {
		this.colinfo = colinfo;
	}
	public String getColtel() {
		return coltel;
	}
	public void setColtel(String coltel) {
		this.coltel = coltel;
	}
	public String getColloc() {
		return colloc;
	}
	public void setColloc(String colloc) {
		this.colloc = colloc;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getColprice() {
		return colprice;
	}
	public void setColprice(String colprice) {
		this.colprice = colprice;
	}
	public int getColcode() {
		return colcode;
	}
	public void setColcode(int colcode) {
		this.colcode = colcode;
	}
	
}
