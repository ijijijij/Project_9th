package cooking.vo;

public class MyAvatar {
	String mavt_cd_pk;
	String user_cd_pk;
	String avt_cd_pk;
	String mavt_nm;
	int avt_stp;
	int sun_stp;
	int water_stp;
	int nut_stp;
	String avt_img;
	int profile_point;
	
	
	public MyAvatar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MyAvatar(String user_cd_pk, String avt_cd_pk, String mavt_nm) {
		super();
		this.user_cd_pk = user_cd_pk;
		this.avt_cd_pk = avt_cd_pk;
		this.mavt_nm = mavt_nm;
	}

	public MyAvatar(String mavt_cd_pk, String user_cd_pk, String avt_cd_pk, String mavt_nm, int avt_stp, int sun_stp,
			int water_stp, int nut_stp, String avt_img) {
		super();
		this.mavt_cd_pk = mavt_cd_pk;
		this.user_cd_pk = user_cd_pk;
		this.avt_cd_pk = avt_cd_pk;
		this.mavt_nm = mavt_nm;
		this.avt_stp = avt_stp;
		this.sun_stp = sun_stp;
		this.water_stp = water_stp;
		this.nut_stp = nut_stp;
		this.avt_img = avt_img;
	}

	public MyAvatar(String mavt_cd_pk, String user_cd_pk, String avt_cd_pk, String mavt_nm, int avt_stp, int sun_stp,
			int water_stp, int nut_stp, String avt_img, int profile_point) {
		super();
		this.mavt_cd_pk = mavt_cd_pk;
		this.user_cd_pk = user_cd_pk;
		this.avt_cd_pk = avt_cd_pk;
		this.mavt_nm = mavt_nm;
		this.avt_stp = avt_stp;
		this.sun_stp = sun_stp;
		this.water_stp = water_stp;
		this.nut_stp = nut_stp;
		this.avt_img = avt_img;
		this.profile_point = profile_point;
	}

	public String getMavt_cd_pk() {
		return mavt_cd_pk;
	}
	public void setMavt_cd_pk(String mavt_cd_pk) {
		this.mavt_cd_pk = mavt_cd_pk;
	}
	public String getUser_cd_pk() {
		return user_cd_pk;
	}
	public void setUser_cd_pk(String user_cd_pk) {
		this.user_cd_pk = user_cd_pk;
	}
	public String getAvt_cd_pk() {
		return avt_cd_pk;
	}
	public void setAvt_cd_pk(String avt_cd_pk) {
		this.avt_cd_pk = avt_cd_pk;
	}
	public String getMavt_nm() {
		return mavt_nm;
	}
	public void setMavt_nm(String mavt_nm) {
		this.mavt_nm = mavt_nm;
	}
	public int getAvt_stp() {
		return avt_stp;
	}
	public void setAvt_stp(int avt_stp) {
		this.avt_stp = avt_stp;
	}
	public int getSun_stp() {
		return sun_stp;
	}
	public void setSun_stp(int sun_stp) {
		this.sun_stp = sun_stp;
	}
	public int getWater_stp() {
		return water_stp;
	}
	public void setWater_stp(int water_stp) {
		this.water_stp = water_stp;
	}
	public int getNut_stp() {
		return nut_stp;
	}
	public void setNut_stp(int nut_stp) {
		this.nut_stp = nut_stp;
	}

	public String getAvt_img() {
		return avt_img;
	}

	public void setAvt_img(String avt_img) {
		this.avt_img = avt_img;
	}

	public int getProfile_point() {
		return profile_point;
	}

	public void setProfile_point(int profile_point) {
		this.profile_point = profile_point;
	}
	
}
