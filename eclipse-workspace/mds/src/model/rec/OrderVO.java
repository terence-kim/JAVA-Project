package model.rec;

public class OrderVO {
	int mocode, memcode, paynum, shopcode, amscode, amsquantity;
	String name, modate,mototalprice, modeliver,mototal, shopquantity, amsnation, amscom, amssignificant, amsprice;
	
	public OrderVO() {
		// TODO Auto-generated constructor stub
	}
	//am_seller 생성자
	public OrderVO(int amscode, String amsnation, String amscom, String amsprice, int amsquantity, String amssignificant) {
		this.amscode = amscode;
		this.amsnation = amsnation;
		this.amscom = amscom;
		this.amsprice = amsprice;
		this.amsquantity = amsquantity;
		this.amssignificant = amssignificant;
	}
	
	//machineorder 생성자
	public OrderVO(int mocode, String modate, String mototalprice, String modeliver, int memcode, int paynum, String mototal) {
		this.mocode = mocode;//주문 코드
		this.modate = modate;//주문일자
		this.mototalprice = mototalprice;//총가격
		this.modeliver = modeliver;//배송상태
		this.memcode = memcode;//회원코드
		this.paynum = paynum;//결제코드
		this.mototal = mototal;//총수량
	}
	//shoppinglist 생성자
	public OrderVO(String shopquantity, int amscode, int mocode) {
		this.shopquantity = shopquantity;
		this.amscode = amscode;
		this.mocode = mocode;
	}

	public int getMocode() {
		return mocode;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMocode(int mocode) {
		this.mocode = mocode;
	}

	public int getMemcode() {
		return memcode;
	}

	public void setMemcode(int memcode) {
		this.memcode = memcode;
	}

	public int getPaynum() {
		return paynum;
	}

	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}

	public int getShopcode() {
		return shopcode;
	}

	public void setShopcode(int shopcode) {
		this.shopcode = shopcode;
	}

	public int getAmscode() {
		return amscode;
	}

	public void setAmscode(int amscode) {
		this.amscode = amscode;
	}

	public String getModate() {
		return modate;
	}

	public void setModate(String modate) {
		this.modate = modate;
	}

	public String getMototalprice() {
		return mototalprice;
	}

	public void setMototalprice(String mototalprice) {
		this.mototalprice = mototalprice;
	}

	public String getModeliver() {
		return modeliver;
	}

	public void setModeliver(String modeliver) {
		this.modeliver = modeliver;
	}

	public String getMototal() {
		return mototal;
	}

	public void setMototal(String mototal) {
		this.mototal = mototal;
	}

	public String getShopquantity() {
		return shopquantity;
	}

	public void setShopquantity(String shopquantity) {
		this.shopquantity = shopquantity;
	}
	public int getAmsquantity() {
		return amsquantity;
	}
	public void setAmsquantity(int amsquantity) {
		this.amsquantity = amsquantity;
	}
	public String getAmsnation() {
		return amsnation;
	}
	public void setAmsnation(String amsnation) {
		this.amsnation = amsnation;
	}
	public String getAmscom() {
		return amscom;
	}
	public void setAmscom(String amscom) {
		this.amscom = amscom;
	}
	public String getAmssignificant() {
		return amssignificant;
	}
	public void setAmssignificant(String amssignificant) {
		this.amssignificant = amssignificant;
	}
	public String getAmsprice() {
		return amsprice;
	}
	public void setAmsprice(String amsprice) {
		this.amsprice = amsprice;
	}
	
}
