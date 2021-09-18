package cooking.service;

import cooking.dao.AccountDAO;
import cooking.vo.PrivateInfo;
import cooking.vo.Profile;

public class AccountService {
	private AccountDAO dao;

	public AccountService() {
		dao = new AccountDAO();
	}
	//개인정보 조회
		public PrivateInfo getPrivateInfo(String user_cd_pk) {
			return dao.getPrivateInfo(user_cd_pk);
		}
	//프로필 조회
	public Profile getProfile(String user_cd_pk) {
		return dao.getProfile(user_cd_pk);
	}
	//프로필 수정
	public int updateProfile(Profile newProfile) {
		return dao.updateProfile(newProfile);
	}
	//개인정보 수정
	public int updatePrivateInfo(PrivateInfo privateInfo) {
		return dao.updatePrivateInfo(privateInfo);
	}
	//회원 탈퇴
	public int signOut(String user_cd_pk) {
		return dao.signOut(user_cd_pk);
	}
	
}
