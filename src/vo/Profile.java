package cooking.vo;

public class Profile {
	private String profile_cd_pk;
	private String user_cd_pk;
	private String profile_nk;
	private String profile_img;
	private int profile_point;
	private String profile_bimg;
	private String profile_intro;
	
	public Profile() {
		super();
	}

	public Profile(String profile_img, String profile_bimg, String profile_nk, String profile_intro, String user_cd_pk) {
		super();
		this.user_cd_pk = user_cd_pk;
		this.profile_nk = profile_nk;
		this.profile_img = profile_img;
		this.profile_bimg = profile_bimg;
		this.profile_intro = profile_intro;
	}



	public Profile(String profile_cd_pk, String user_cd_pk, String profile_nk, String profile_img, int profile_point,
			String profile_bimg, String profile_intro) {
		super();
		this.profile_cd_pk = profile_cd_pk;
		this.user_cd_pk = user_cd_pk;
		this.profile_nk = profile_nk;
		this.profile_img = profile_img;
		this.profile_point = profile_point;
		this.profile_bimg = profile_bimg;
		this.profile_intro = profile_intro;
	}

	public String getProfile_cd_pk() {
		return profile_cd_pk;
	}

	public void setProfile_cd_pk(String profile_cd_pk) {
		this.profile_cd_pk = profile_cd_pk;
	}

	public String getUser_cd_pk() {
		return user_cd_pk;
	}

	public void setUser_cd_pk(String user_cd_pk) {
		this.user_cd_pk = user_cd_pk;
	}

	public String getProfile_nk() {
		return profile_nk;
	}

	public void setProfile_nk(String profile_nk) {
		this.profile_nk = profile_nk;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public int getProfile_point() {
		return profile_point;
	}

	public void setProfile_point(int profile_point) {
		this.profile_point = profile_point;
	}

	public String getProfile_bimg() {
		return profile_bimg;
	}

	public void setProfile_bimg(String profile_bimg) {
		this.profile_bimg = profile_bimg;
	}

	public String getProfile_intro() {
		return profile_intro;
	}

	public void setProfile_intro(String profile_intro) {
		this.profile_intro = profile_intro;
	}

	
	
}
