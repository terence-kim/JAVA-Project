package model.rec;

public class ConsultVO {
		String ivDate, ivcon, repname, repdept;
		int ivcode, memcode, repcode;
		public ConsultVO() {
			
		}
		
		public ConsultVO(String ivDate, String ivcon, int memcode, int repcode) {
			this.ivcode = ivcode;
			this.ivDate = ivDate;
			this.ivcon = ivcon;
			this.memcode = memcode;
			this.repcode = repcode;
			this.repname = repname;
			this.repdept = repdept;
		}

		public String getivDate() {
			return ivDate;
		}

		public String getRepname() {
			return repname;
		}

		public void setRepname(String repname) {
			this.repname = repname;
		}

		public String getRepdept() {
			return repdept;
		}

		public void setRepdept(String repdept) {
			this.repdept = repdept;
		}

		public void setIvtitle(String ivDate) {
			this.ivDate = ivDate;
		}

		public String getIvcon() {
			return ivcon;
		}

		public void setIvcon(String ivcon) {
			this.ivcon = ivcon;
		}

		public int getIvcode() {
			return ivcode;
		}

		public void setIvcode(int ivcode) {
			this.ivcode = ivcode;
		}

		public int getMemcode() {
			return memcode;
		}

		public void setMemcode(int memcode) {
			this.memcode = memcode;
		}

		public int getRepcode() {
			return repcode;
		}

		public void setRepcode(int repcode) {
			this.repcode = repcode;
		}
		
		
}

