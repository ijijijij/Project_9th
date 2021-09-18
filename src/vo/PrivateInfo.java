package cooking.vo;

import java.sql.Timestamp;
import java.util.Date;

public class PrivateInfo {
	private String user_cd_pk;
	private int user_gb;
	private String user_id;
	private String user_pw;
	private String user_nm;
	private String user_birth;
	private String user_tel;
	private String user_mail;
	private String user_join_dt;
	private String user_edit_dt;
	public PrivateInfo() {
		super();
	}
	
	public PrivateInfo(String user_cd_pk, String user_pw, String user_tel, String user_mail) {
		super();
		this.user_cd_pk = user_cd_pk;
		this.user_pw = user_pw;
		this.user_tel = user_tel;
		this.user_mail = user_mail;
	}

	public PrivateInfo(String user_cd_pk, int user_gb, String user_id, String user_pw, String user_nm, String user_birth,
			String user_tel, String user_mail, String user_join_dt, String user_edit_dt) {
		super();
		this.user_cd_pk = user_cd_pk;
		this.user_gb = user_gb;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_nm = user_nm;
		this.user_birth = user_birth;
		this.user_tel = user_tel;
		this.user_mail = user_mail;
		this.user_join_dt = user_join_dt;
		this.user_edit_dt = user_edit_dt;
	}
	public String getUser_cd_pk() {
		return user_cd_pk;
	}
	public void setUser_cd_pk(String user_cd_pk) {
		this.user_cd_pk = user_cd_pk;
	}
	public int getUser_gb() {
		return user_gb;
	}
	public void setUser_gb(int user_gb) {
		this.user_gb = user_gb;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getUser_join_dt() {
		return user_join_dt;
	}
	public void setUser_join_dt(String user_join_dt) {
		this.user_join_dt = user_join_dt;
	}
	public String getUser_edit_dt() {
		return user_edit_dt;
	}
	public void setUser_edit_dt(String user_edit_dt) {
		this.user_edit_dt = user_edit_dt;
	}
	
	
}
