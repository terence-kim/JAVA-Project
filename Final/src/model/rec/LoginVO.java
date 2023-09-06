package model.rec;

public class LoginVO {

		Number memcode;
		String ID, PW;
		public LoginVO(Number memcode2, String iD2, String pW2, String memtel, String memaddr, String mememr, String memname, String memjuban, Object memobstacle) {
			
		}
		public LoginVO(Number memcode, String ID, String PW) {
			this.memcode=memcode;
			this.ID=ID;
			this.PW=PW;
			
		}

		public Number getMemcode() {
			return memcode;
		}
		public void setMemcode(Number memcode) {
			this.memcode = memcode;
		}
		public String getID() {
			return ID;
		}

		public void setID(String ID) {
			this.ID = ID;
		}

		public String getPW() {
			return PW;
		}

		public void setPW(String PW) {
			this.PW = PW;
		}
	    
	}
		
	
