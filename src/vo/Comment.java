package cooking.vo;

public class Comment {
	private String comment_cd_no;
	private String profile_cd_pk;
	private String comment_detail;
	private String comment_reg_dt;
	private int contents_cd_no;
	private String recipe_cd_no;
	private String profile_nk;
	private String profile_img;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(String profile_cd_pk, String comment_detail, String recipe_cd_no) {
		super();
		this.profile_cd_pk = profile_cd_pk;
		this.comment_detail = comment_detail;
		this.recipe_cd_no = recipe_cd_no;
	}

	public Comment(String comment_cd_no, String profile_cd_pk, String comment_detail, String comment_reg_dt,
			int contents_cd_no, String recipe_cd_no, String profile_nk, String profile_img) {
		super();
		this.comment_cd_no = comment_cd_no;
		this.profile_cd_pk = profile_cd_pk;
		this.comment_detail = comment_detail;
		this.comment_reg_dt = comment_reg_dt;
		this.contents_cd_no = contents_cd_no;
		this.recipe_cd_no = recipe_cd_no;
		this.profile_nk = profile_nk;
		this.profile_img = profile_img;
	}
	public String getComment_cd_no() {
		return comment_cd_no;
	}
	public void setComment_cd_no(String comment_cd_no) {
		this.comment_cd_no = comment_cd_no;
	}
	public String getProfile_cd_pk() {
		return profile_cd_pk;
	}
	public void setProfile_cd_pk(String profile_cd_pk) {
		this.profile_cd_pk = profile_cd_pk;
	}
	public String getComment_detail() {
		return comment_detail;
	}
	public void setComment_detail(String comment_detail) {
		this.comment_detail = comment_detail;
	}
	public String getComment_reg_dt() {
		return comment_reg_dt;
	}
	public void setComment_reg_dt(String comment_reg_dt) {
		this.comment_reg_dt = comment_reg_dt;
	}
	public int getContents_cd_no() {
		return contents_cd_no;
	}
	public void setContents_cd_no(int contents_cd_no) {
		this.contents_cd_no = contents_cd_no;
	}
	public String getRecipe_cd_no() {
		return recipe_cd_no;
	}
	public void setRecipe_cd_no(String recipe_cd_no) {
		this.recipe_cd_no = recipe_cd_no;
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
	
}
