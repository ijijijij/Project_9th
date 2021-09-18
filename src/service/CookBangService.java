package cooking.service;

import java.util.ArrayList;

import cooking.dao.CookBangDAO;
import cooking.vo.CookBang;

public class CookBangService {
	private CookBangDAO dao;
	public CookBangService() {
		dao = new CookBangDAO();
	}

	// 쿡방 리스트
	public ArrayList<CookBang> getCookBangList(String profile_cd_pk) {
		return dao.getCookBangList(profile_cd_pk);
	}
	// 쿡방 조회
	public CookBang getCookBang(String cbang_cd_no) {
		return dao.getCookBang(cbang_cd_no);
	}


}
