package cooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import cooking.vo.CookBang;

public class CookBangDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	//쿡방 조회
	public ArrayList<CookBang> getCookBangList(String profile_cd_pk) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<CookBang> cookBangList= new ArrayList<CookBang>();
		String sql = "SELECT cbang_cd_pk, profile_cd_pk, cbang_nm, cbang_vid, cbang_img\r\n"
				+ "FROM TEAM9TH.sys_cb_tb\r\n"
				+ "WHERE profile_cd_pk=?";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, profile_cd_pk);
			rs = pst.executeQuery();
			while (rs.next()) {
				cookBangList.add(new CookBang(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5))); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return cookBangList;
	}

	//쿡방 조회
	public CookBang getCookBang(String cbang_cd_pk) {
		CookBang cookBang= new CookBang();
		String sql = "SELECT cbang_cd_pk, profile_cd_pk, cbang_nm, cbang_vid, cbang_img\r\n"
				+ "FROM TEAM9TH.sys_cb_tb\r\n"
				+ "WHERE cbang_cd_pk=?";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cbang_cd_pk);
			rs = pst.executeQuery();
			if (rs.next()) {
				cookBang = new CookBang(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5)); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return cookBang;
	}
}
