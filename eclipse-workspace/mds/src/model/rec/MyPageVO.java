package model.rec;

public class MyPageVO {

		int memcode;
		String PW, memtel, memaddr, memname, memjuban;
		public MyPageVO() {
			
		}
		
		public MyPageVO(int memcode, String PW, String memtel, String memaddr, String memname, String memjuban) {
			this.memcode=memcode;
			this.PW=PW;
			this.memtel=memtel;
			this.memaddr=memaddr;
			this.memname=memname;
			this.memjuban=memjuban;
			
			
			
		}

		public int getMemcode() {
			return memcode;
		}

		public void setMemcode(int memcode) {
			this.memcode = memcode;
		}


		public String getMemtel() {
			return memtel;
		}

		public void setMemtel(String memtel) {
			this.memtel = memtel;
		}

		public String getMemaddr() {
			return memaddr;
		}

		public void setMemaddr(String memaddr) {
			this.memaddr = memaddr;
		}


		public String getMemname() {
			return memname;
		}

		public void setMemname(String memname) {
			this.memname = memname;
		}

		public String getMemjuban() {
			return memjuban;
		}

		public void setMemjuban(String memjuban) {
			this.memjuban = memjuban;
		}

		public String getPW() {
			return PW;
		}

		public void setPW(String pW) {
			PW = pW;
		}

		
	}
