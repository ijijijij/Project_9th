package cooking.vo;

public class Avatar {
	private String avt_cd_pk;
	private String avt_nm;
	private int avt_prc;
	private String stp_1st_img;
	private String stp_2nd_img;
	private String stp_3rd_img;
	private String avt_intro;
	public Avatar() {
		super();
	}
	
	public Avatar(String avt_cd_pk, String avt_nm, int avt_prc, String stp_1st_img, String stp_2nd_img,
			String stp_3rd_img, String avt_intro) {
		super();
		this.avt_cd_pk = avt_cd_pk;
		this.avt_nm = avt_nm;
		this.avt_prc = avt_prc;
		this.stp_1st_img = stp_1st_img;
		this.stp_2nd_img = stp_2nd_img;
		this.stp_3rd_img = stp_3rd_img;
		this.avt_intro = avt_intro;
	}

	public String getAvt_cd_pk() {
		return avt_cd_pk;
	}
	public void setAvt_cd_pk(String avt_cd_pk) {
		this.avt_cd_pk = avt_cd_pk;
	}
	public String getAvt_nm() {
		return avt_nm;
	}
	public void setAvt_nm(String avt_nm) {
		this.avt_nm = avt_nm;
	}
	public int getAvt_prc() {
		return avt_prc;
	}
	public void setAvt_prc(int avt_prc) {
		this.avt_prc = avt_prc;
	}

	public String getStp_1st_img() {
		return stp_1st_img;
	}

	public void setStp_1st_img(String stp_1st_img) {
		this.stp_1st_img = stp_1st_img;
	}

	public String getStp_2nd_img() {
		return stp_2nd_img;
	}

	public void setStp_2nd_img(String stp_2nd_img) {
		this.stp_2nd_img = stp_2nd_img;
	}

	public String getStp_3rd_img() {
		return stp_3rd_img;
	}

	public void setStp_3rd_img(String stp_3rd_img) {
		this.stp_3rd_img = stp_3rd_img;
	}

	public String getAvt_intro() {
		return avt_intro;
	}

	public void setAvt_intro(String avt_intro) {
		this.avt_intro = avt_intro;
	}
	
	
}
