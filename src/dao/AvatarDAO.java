package cooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cooking.vo.Avatar;
import cooking.vo.MyAvatar;

public class AvatarDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	//마이아바타 조회
	public ArrayList<MyAvatar> getMyAvatar(String user_cd_pk) {
		ArrayList<MyAvatar> mavt = new ArrayList<MyAvatar>();
		String sql = "SELECT mavt_cd_pk, user_cd_pk, a.avt_cd_pk, mavt_nm, \r\n"
				+ "avt_stp, sun_stp, water_stp, nut_stp, "
				+ "decode(avt_stp, 1, a.stp_1st_img, 2, a.stp_2nd_img, 3, a.stp_3rd_img) AS avt_img\r\n"
				+ "FROM TEAM9TH.SYS_MAVT_TB m, TEAM9TH.sys_avt_tb a\r\n"
				+ "WHERE user_cd_pk = ? AND a.avt_cd_pk=m.avt_cd_pk";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_cd_pk);
			rs = pst.executeQuery();
			while (rs.next()) {
				mavt.add(new MyAvatar(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getInt(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getString(9)));
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
		return mavt;
	}
	// 마이아바타 조회
	public MyAvatar getThisAvatar(String mavt_cd_pk) {
		MyAvatar mavt = null;
		String sql = "SELECT mavt_cd_pk, m.user_cd_pk, a.avt_cd_pk, mavt_nm, \r\n"
				+ "avt_stp, sun_stp, water_stp, nut_stp, "
				+ "decode(avt_stp, 1, a.stp_1st_img, 2, a.stp_2nd_img, 3, a.stp_3rd_img) AS avt_img,\r\n"
				+ "p.profile_point\r\n"
				+ "FROM TEAM9TH.SYS_MAVT_TB m, sys_avt_tb a, TEAM9TH.SYS_PROFILE_TB p\r\n"
				+ "WHERE mavt_cd_pk=? AND a.avt_cd_pk=m.avt_cd_pk "
				+ "AND m.USER_CD_PK =p.USER_CD_PK";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, mavt_cd_pk);
			rs = pst.executeQuery();
			while (rs.next()) {
				mavt = new MyAvatar(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getInt(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10));
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
		return mavt;
	}
	
	// 포인트 조회
	public int getPoint(String user_cd_pk) {
		int point = 0;
		String sql = "SELECT profile_point "
				+ "FROM TEAM9TH.SYS_PROFILE_TB WHERE user_cd_pk=?";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_cd_pk);
			rs = pst.executeQuery();
			if (rs.next()) {
				point = rs.getInt("profile_point");
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
		return point;
	}
	//포인트 감소
	public int updatePoint(String user_cd_pk, int point) {
		int cnt = -1;
		String sql = "UPDATE TEAM9TH.sys_profile_tb "
				+ "SET profile_point = profile_point-"+point
				+ "WHERE user_cd_pk=?";
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

	// 옵션주기
	public int updateOptionStp(String option, String mavt_cd_pk) {
		int cnt = -1;
		String sql = null;
		if(option.equals("sun")) {
			sql = "UPDATE TEAM9TH.SYS_MAVT_TB SET sun_STP = sun_stp+1 "
					+ "WHERE mavt_cd_pk = ?";
		} else if(option.equals("water")) {
			sql = "UPDATE TEAM9TH.SYS_MAVT_TB SET WATER_STP = WATER_stp+1 "
					+ "WHERE mavt_cd_pk = ?";
		} else {
			sql = "UPDATE TEAM9TH.SYS_MAVT_TB SET nut_STP = nut_stp+1 "
					+ "WHERE mavt_cd_pk = ?";
		}
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, mavt_cd_pk);
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
	// 옵션주기
	public int updateAvtStp(String mavt_cd_pk) {
		int cnt = -1;
		String sql = "UPDATE TEAM9TH.sys_mavt_Tb SET avt_stp = avt_stp+1 "
				+ "WHERE mavt_cd_pk= ?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, mavt_cd_pk);
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
	// 옵션주기
	public int initializeStp(String mavt_cd_pk) {
		int cnt = -1;
		String sql = "UPDATE TEAM9TH.SYS_MAVT_TB "
				+ "SET sun_stp = 0, WATER_STP =0, NUT_STP =0 "
				+ "WHERE mavt_cd_pk=?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, mavt_cd_pk);
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
	// 아바타 구입
	public int buyAvatar(MyAvatar myAvt) {
		int cnt = -1;
		String sql = "INSERT INTO TEAM9TH.SYS_MAVT_TB(mavt_cd_pk, user_cd_pk, avt_cd_pk, mavt_nm) "
				+ "values(\r\n"
				+ "'mavt-'||trim(to_char(mavt_sq.nextval,'0000')), ?,?,?)";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, myAvt.getUser_cd_pk());
			pst.setString(2, myAvt.getAvt_cd_pk());
			pst.setString(3, myAvt.getMavt_nm());
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
	// 마이아바타 조회
	public ArrayList<Avatar> getAvatar() {
		ArrayList<Avatar> avtList = new ArrayList<Avatar>();
		String sql = "SELECT avt_cd_pk, avt_nm, avt_prc, "
				+ "stp_1st_img, stp_2nd_img, stp_3rd_img, avt_intro\r\n"
				+ "FROM TEAM9TH.sys_avt_tb";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				avtList.add(new Avatar(rs.getString(1), rs.getString(2), rs.getInt(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
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
		return avtList;
	}


}
