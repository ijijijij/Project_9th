package cooking.vo;

public class CookBang {
	private String cbang_cd_pk;
	private String profile_cd_pk;
	private String cbang_nm;
	private String cbang_vid;
	private String cbang_img;
	public CookBang() {
		super();
	}
	
	public CookBang(String cbang_cd_pk, String profile_cd_pk, String cbang_nm, String cbang_vid, String cbang_img) {
		super();
		this.cbang_cd_pk = cbang_cd_pk;
		this.profile_cd_pk = profile_cd_pk;
		this.cbang_nm = cbang_nm;
		this.cbang_vid = cbang_vid;
		this.cbang_img = cbang_img;
	}

	public String getCbang_cd_pk() {
		return cbang_cd_pk;
	}
	public void setCbang_cd_pk(String cbang_cd_pk) {
		this.cbang_cd_pk = cbang_cd_pk;
	}
	public String getProfile_cd_pk() {
		return profile_cd_pk;
	}
	public void setProfile_cd_pk(String profile_cd_pk) {
		this.profile_cd_pk = profile_cd_pk;
	}
	public String getCbang_nm() {
		return cbang_nm;
	}
	public void setCbang_nm(String cbang_nm) {
		this.cbang_nm = cbang_nm;
	}

	public String getCbang_vid() {
		return cbang_vid;
	}

	public void setCbang_vid(String cbang_vid) {
		this.cbang_vid = cbang_vid;
	}

	public String getCbang_img() {
		return cbang_img;
	}

	public void setCbang_img(String cbang_img) {
		this.cbang_img = cbang_img;
	}
	
	
}
