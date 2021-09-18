package cooking.vo;

import java.util.Date;

public class Recipe {
	
	private int serial_num;
	private String recipe_cd_no;
	private String profile_cd_pk;
	private String recipe_nm;
	private String recipe_img;
	private String recipe_detail;
	private String recipe_link;
	private String recipe_reg_dt;
	private String recipe_tip;
	private int category_cd_no;
	private String recipe_tag;
	private int recipe_cnt;
	private int recipe_reco_cnt;
	private int recipe_dc_no;
	private int contents_cd_no;
	
	public Recipe() {
		super();
	}

	public Recipe(int serial_num, String recipe_cd_no, String profile_cd_pk, String recipe_nm, String recipe_img,
			String recipe_detail, String recipe_link, String recipe_reg_dt, String recipe_tip, int category_cd_no,
			String recipe_tag, int recipe_cnt, int recipe_reco_cnt, int recipe_dc_no, int contents_cd_no) {
		super();
		this.serial_num = serial_num;
		this.recipe_cd_no = recipe_cd_no;
		this.profile_cd_pk = profile_cd_pk;
		this.recipe_nm = recipe_nm;
		this.recipe_img = recipe_img;
		this.recipe_detail = recipe_detail;
		this.recipe_link = recipe_link;
		this.recipe_reg_dt = recipe_reg_dt;
		this.recipe_tip = recipe_tip;
		this.category_cd_no = category_cd_no;
		this.recipe_tag = recipe_tag;
		this.recipe_cnt = recipe_cnt;
		this.recipe_reco_cnt = recipe_reco_cnt;
		this.recipe_dc_no = recipe_dc_no;
		this.contents_cd_no = contents_cd_no;
	}

	public Recipe(String recipe_cd_no, String profile_cd_pk, String recipe_nm, String recipe_img, String recipe_detail,
			String recipe_link, String recipe_reg_dt, String recipe_tip, int category_cd_no, String recipe_tag,
			int recipe_cnt, int recipe_reco_cnt, int recipe_dc_no, int contents_cd_no) {
		super();
		this.recipe_cd_no = recipe_cd_no;
		this.profile_cd_pk = profile_cd_pk;
		this.recipe_nm = recipe_nm;
		this.recipe_img = recipe_img;
		this.recipe_detail = recipe_detail;
		this.recipe_link = recipe_link;
		this.recipe_reg_dt = recipe_reg_dt;
		this.recipe_tip = recipe_tip;
		this.category_cd_no = category_cd_no;
		this.recipe_tag = recipe_tag;
		this.recipe_cnt = recipe_cnt;
		this.recipe_reco_cnt = recipe_reco_cnt;
		this.recipe_dc_no = recipe_dc_no;
		this.contents_cd_no = contents_cd_no;
	}

	public String getRecipe_cd_no() {
		return recipe_cd_no;
	}

	public void setRecipe_cd_no(String recipe_cd_no) {
		this.recipe_cd_no = recipe_cd_no;
	}

	public String getProfile_cd_pk() {
		return profile_cd_pk;
	}

	public void setProfile_cd_pk(String profile_cd_pk) {
		this.profile_cd_pk = profile_cd_pk;
	}

	public String getRecipe_nm() {
		return recipe_nm;
	}

	public void setRecipe_nm(String recipe_nm) {
		this.recipe_nm = recipe_nm;
	}

	public String getRecipe_img() {
		return recipe_img;
	}

	public void setRecipe_img(String recipe_img) {
		this.recipe_img = recipe_img;
	}

	public String getRecipe_detail() {
		return recipe_detail;
	}

	public void setRecipe_detail(String recipe_detail) {
		this.recipe_detail = recipe_detail;
	}

	public String getRecipe_link() {
		return recipe_link;
	}

	public void setRecipe_link(String recipe_link) {
		this.recipe_link = recipe_link;
	}

	public String getRecipe_reg_dt() {
		return recipe_reg_dt;
	}

	public void setRecipe_reg_dt(String recipe_reg_dt) {
		this.recipe_reg_dt = recipe_reg_dt;
	}

	public String getRecipe_tip() {
		return recipe_tip;
	}

	public void setRecipe_tip(String recipe_tip) {
		this.recipe_tip = recipe_tip;
	}

	public int getCategory_cd_no() {
		return category_cd_no;
	}

	public void setCategory_cd_no(int category_cd_no) {
		this.category_cd_no = category_cd_no;
	}

	public String getRecipe_tag() {
		return recipe_tag;
	}

	public void setRecipe_tag(String recipe_tag) {
		this.recipe_tag = recipe_tag;
	}

	public int getRecipe_cnt() {
		return recipe_cnt;
	}

	public void setRecipe_cnt(int recipe_cnt) {
		this.recipe_cnt = recipe_cnt;
	}

	public int getRecipe_reco_cnt() {
		return recipe_reco_cnt;
	}

	public void setRecipe_reco_cnt(int recipe_reco_cnt) {
		this.recipe_reco_cnt = recipe_reco_cnt;
	}

	public int getRecipe_dc_no() {
		return recipe_dc_no;
	}

	public void setRecipe_dc_no(int recipe_dc_no) {
		this.recipe_dc_no = recipe_dc_no;
	}

	public int getContents_cd_no() {
		return contents_cd_no;
	}

	public void setContents_cd_no(int contents_cd_no) {
		this.contents_cd_no = contents_cd_no;
	}

	public int getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(int serial_num) {
		this.serial_num = serial_num;
	}
	
	
}
