package model.rec;

import java.awt.Component;

public class MemlogVO {

		int memcode;
		String ID, PW, memtel, memaddr, mememr, memname, memjuban;
		Object memobstacle;
		public MemlogVO() {
			
		}
		public MemlogVO(int memcode, String ID, String PW, String memtel, String memaddr, String mememr, String memname, String memjuban, Object memobstacle) {
			this.memcode=memcode;
			this.ID=ID;
			this.PW=PW;
			this.memtel=memtel;
			this.memaddr=memaddr;
			this.mememr=mememr;
			this.memname=memname;
			this.memjuban=memjuban;
			this.memobstacle=memobstacle;


			
		}

		public Number getMemcode() {
			return memcode;
		}
		public void setMemcode(int memcode) {
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
			this.PW =PW;
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

		public String getMememr() {
			return mememr;
		}

		public void setMememr(String mememr) {
			this.mememr = mememr;
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

		public Object getMemobstacle() {
			return memobstacle;
		}
		
		public void setMemobstacle(Object memobstacle) {
			this.memobstacle = memobstacle;
		}

	    
	}
		
	
