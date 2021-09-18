package cooking.vo;

public class Chat {
	private String chtr_cd_pk;
	private String cbang_cd_pk;
	private String profile_nm;
	public Chat() {
		super();
	}
	public Chat(String chtr_cd_pk, String cbang_cd_pk, String profile_nm) {
		super();
		this.chtr_cd_pk = chtr_cd_pk;
		this.cbang_cd_pk = cbang_cd_pk;
		this.profile_nm = profile_nm;
	}
	public String getChtr_cd_pk() {
		return chtr_cd_pk;
	}
	public void setChtr_cd_pk(String chtr_cd_pk) {
		this.chtr_cd_pk = chtr_cd_pk;
	}
	public String getCbang_cd_pk() {
		return cbang_cd_pk;
	}
	public void setCbang_cd_pk(String cbang_cd_pk) {
		this.cbang_cd_pk = cbang_cd_pk;
	}
	public String getProfile_nm() {
		return profile_nm;
	}
	public void setProfile_nm(String profile_nm) {
		this.profile_nm = profile_nm;
	}
	
}
