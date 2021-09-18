package cooking.service;

import java.util.ArrayList;

import com.google.gson.Gson;

import cooking.dao.AvatarDAO;
import cooking.vo.Avatar;
import cooking.vo.MyAvatar;

public class AvatarService {
	private AvatarDAO dao;
	private Gson gson;

	public AvatarService() {
		dao = new AvatarDAO();
		gson = new Gson();
	}
	//내 아바타 조회
	public ArrayList<MyAvatar> getMyAvatar(String user_cd_pk) {
		return dao.getMyAvatar(user_cd_pk);
	}
	//아바타 선택 조회
	public MyAvatar getThisAvatar(String mavt_cd_pk) {
		return dao.getThisAvatar(mavt_cd_pk);
	}
	//아바타 선택 조회(제이슨)
	public String getJsonAvatar(String mavt_cd_pk) {
		return gson.toJson(dao.getThisAvatar(mavt_cd_pk));
	}
	// 내 포인트 확인
	public int getPoint(String user_cd_pk) {
		return dao.getPoint(user_cd_pk);
	}
	//아바타 선택 > 포인트 차감
	public int updatePoint(String user_cd_pk, int point) {
		return dao.updatePoint(user_cd_pk, point);
	}
	//아바타 선택 > 옵션 주기
	public int updateOptionStp(String option, String mavt_cd_pk) {
		return dao.updateOptionStp(option, mavt_cd_pk);
	}
	//아바타 선택 > 아바타 업그레이드
	public int updateAvtStp(String mavt_cd_pk) {
		return dao.updateAvtStp(mavt_cd_pk);
	}
	//아바타 선택 > 아바타 업그레이드 > 옵션 초기화
	public int initializeStp(String mavt_cd_pk) {
		return dao.initializeStp(mavt_cd_pk);
	}
	//아바타 상점
	public ArrayList<Avatar> getAvatar(){
		return dao.getAvatar();
	}
	//아바타 상점 > 구입
	public int buyAvatar(MyAvatar myAvt) {
		return dao.buyAvatar(myAvt);
	}
	
	
}
