package model.rec;

public class ReviewVO {
	int num, code;
	String content, avg;
	
	public ReviewVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ReviewVO(String avg, String content, int code) {
		this.avg = avg;
		this.content = content;
		this.code = code;
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}
	
}
