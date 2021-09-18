package cooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import cooking.vo.PrivateInfo;
import cooking.vo.Profile;

public class AccountDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	//개인정보 조회
	public PrivateInfo getPrivateInfo(String user_cd_pk) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PrivateInfo privateInfo = null;
		String sql = "SELECT user_cd_pk, user_gb, user_id, user_pw, \r\n"
				+ "user_nm, user_birth, user_tel, user_mail,\r\n"
				+ "user_join_dt, user_edit_dt \r\n"
				+ "FROM TEAM9TH.SYS_USER_TB\r\n"
				+ "WHERE user_cd_pk=?";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_cd_pk);
			rs = pst.executeQuery();
			if(rs.next()) {
				privateInfo = new PrivateInfo(
						rs.getString(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10)
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs !=null )
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return privateInfo;
	}

	//프로필 조회
	public Profile getProfile(String user_cd_pk) {
		Profile profile = null;
		String sql = "SELECT profile_cd_pk, user_cd_pk, profile_nk, profile_img, profile_point, profile_bimg, profile_intro\r\n"
				+ "FROM TEAM9TH.sys_profile_tb\r\n"
				+ "WHERE user_cd_pk = ?";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_cd_pk);
			rs = pst.executeQuery();
			if(rs.next()) {
				profile = new Profile(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7)
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs !=null )
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return profile;
	}
	
	//프로필 수정
	public int updateProfile(Profile newProfile) {
		int cnt = -1;
		String sql = "UPDATE TEAM9TH.sys_profile_tb SET \r\n"
				+ "profile_img=?,\r\n"
				+ "profile_bimg = ?,\r\n"
				+ "profile_nk = ?,\r\n"
				+ "profile_intro = ?\r\n"
				+ "WHERE user_cd_pk = ?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, newProfile.getProfile_img());
			pst.setString(2, newProfile.getProfile_bimg());
			pst.setString(3, newProfile.getProfile_nk());
			pst.setString(4, newProfile.getProfile_intro());
			pst.setString(5, newProfile.getUser_cd_pk());
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외 : "+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return cnt;
	}

	//개인정보 수정
	public int updatePrivateInfo(PrivateInfo privateInfo) {
		int cnt = -1;
		String sql = "UPDATE TEAM9TH.SYS_USER_TB \r\n"
				+ "SET USER_PW = ?, USER_mail=?, user_tel=?\r\n"
				+ "WHERE user_cd_pk = ?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, privateInfo.getUser_pw());
			pst.setString(2, privateInfo.getUser_mail());
			pst.setString(3, privateInfo.getUser_tel());
			pst.setString(4, privateInfo.getUser_cd_pk());
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외 : "+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return cnt;
	}

	//개인정보 수정
	
	//회원 탈퇴
	public int signOut(String user_cd_pk) {
		int cnt = -1;
		String sql = "DELETE FROM TEAM9TH.sys_user_tb WHERE user_cd_pk = ?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_cd_pk);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외 : "+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return cnt;
	}

		
}
