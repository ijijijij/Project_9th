package cooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cooking.vo.Comment;
import cooking.vo.Recipe;

public class RecipeDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	// 레시피 개수 확인
	// 개수
	public int getRecipeCount(String profile_cd_pk) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int cnt = -1;
		try {
			String sql = "SELECT count(recipe_cd_no) as count \r\n"
					+ "FROM TEAM9TH.SYS_RECIPE_TB srt, TEAM9TH.SYS_PROFILE_TB spt\r\n"
					+ "WHERE srt.PROFILE_CD_PK =spt.PROFILE_CD_PK \r\n"
					+ "AND CONTENTS_CD_NO =1 AND srt.PROFILE_CD_PK =?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, profile_cd_pk);
			rs = pst.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("총개수:"+cnt);
		return cnt;
	}

	// 최신 레시피 조회
	public Recipe getDefaultRecipe(String profile_cd_pk) {
		Recipe recipe = null;
		String sql = "SELECT *\r\n"
				+ "FROM \r\n"
				+ "	(select recipe_cd_no, PROFILE_CD_PK, recipe_nm, \r\n"
				+ "	recipe_img, recipe_detail, recipe_link,\r\n"
				+ "	to_char(recipe_reg_dt,'yyyy-mm-dd') as recipe_reg_dt, recipe_tip, category_cd_no, \r\n"
				+ "	recipe_tag, recipe_cnt, recipe_reco_cnt,\r\n"
				+ "	recipe_dc_no, contents_cd_no \r\n"
				+ "	FROM TEAM9TH.SYS_RECIPE_TB\r\n"
				+ "	WHERE contents_cd_no = 1 AND profile_cd_pk=?\r\n"
				+ "	ORDER BY RECIPE_REG_DT desc\r\n"
				+ "	)\r\n"
				+ "WHERE rownum=1";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, profile_cd_pk);
			rs = pst.executeQuery();
			if (rs.next()) {
				recipe = new Recipe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14));
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
		return recipe;
	}

	// 레시피 조회
	public Recipe getRecipe(String recipe_cd_no) {
		Recipe recipe = null;
		String sql = "SELECT recipe_cd_no, PROFILE_CD_PK, recipe_nm, \r\n"
				+ "	recipe_img, recipe_detail, recipe_link,\r\n"
				+ "	to_char(recipe_reg_dt,'yyyy-mm-dd') as recipe_reg_dt, recipe_tip, category_cd_no, \r\n"
				+ "	recipe_tag, recipe_cnt, recipe_reco_cnt,\r\n"
				+ "	recipe_dc_no, contents_cd_no \r\n"
				+ "FROM TEAM9TH.SYS_RECIPE_TB\r\n"
				+ "WHERE recipe_cd_no=?";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, recipe_cd_no);
			rs = pst.executeQuery();
			if (rs.next()) {
				recipe = new Recipe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14));
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
		return recipe;
	}

	// 레시피 목록 조회
	public ArrayList<Recipe> getRecipeList(int numByPage, int pageNum, String profile_cd_pk) {
		int count = getRecipeCount(profile_cd_pk);
		System.out.println("여기서 countL"+count);
		ArrayList<Recipe> rlist = new ArrayList<Recipe>();
		String sql = "SELECT * FROM \r\n"
				+ "	(SELECT row_number() OVER (ORDER BY recipe_reg_Dt) AS serialnum,\r\n"
				+ "	recipe_cd_no, spt.PROFILE_CD_PK, recipe_nm, \r\n"
				+ "	recipe_img, recipe_detail, recipe_link,\r\n"
				+ "	to_char(recipe_reg_dt,'yyyy-mm-dd') AS recipe_reg_dt, recipe_tip, category_cd_no, \r\n"
				+ "	recipe_tag, recipe_cnt, recipe_reco_cnt,\r\n"
				+ "	recipe_dc_no, contents_cd_no\r\n"
				+ "	FROM TEAM9TH.SYS_RECIPE_TB srt, TEAM9TH.SYS_PROFILE_TB spt \r\n"
				+ "	WHERE srt.PROFILE_CD_PK =spt.PROFILE_CD_PK AND srt.PROFILE_CD_PK=?\r\n"
				+ "	)\r\n"
				+ "WHERE serialnum BETWEEN ? AND ? AND CONTENTS_CD_NO =1\r\n"
				+ "ORDER BY serialnum DESC";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, profile_cd_pk);
			//pst.setInt(2, (pageNum -1)* numByPage + 1);
			//pst.setInt(3, pageNum* numByPage);
			pst.setInt(2, count-(pageNum*numByPage)+1);
			pst.setInt(3, count - (pageNum - 1) * numByPage);
			rs = pst.executeQuery();
			while (rs.next()) {
				rlist.add(new Recipe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
						rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15)));
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
		System.out.println("리스트 확인:"+rlist.size());
		return rlist;
	}
	
	//레시피 삭제(게시상태 번호 2로 바꿈)
	public int deleteRecipe(String recipe_cd_no) {
		int cnt = -1;
		String sql = "UPDATE TEAM9TH.sys_recipe_tb "
				+ "SET contents_cd_no = 2 "
				+ "WHERE recipe_cd_no = ?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, recipe_cd_no);
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

	//조회수 증가
	public int updateViewCnt(String recipe_cd_no) {
		int cnt = -1;
		String sql = "UPDATE TEAM9TH.SYS_RECIPE_TB SET "
				+ "recipe_cnt = recipe_cnt+1 "
				+ "WHERE RECIPE_CD_NO =?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, recipe_cd_no);
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
// 댓글  조회
	public ArrayList<Comment> getCommentList(String recipe_cd_no) {
		ArrayList<Comment> clist = new ArrayList<Comment>();
		String sql = "SELECT comment_cd_no, p.profile_cd_pk, comment_detail, \r\n"
				+ "to_char(comment_reg_dt,'yyyy-mm-dd') AS comment_reg_dt, contents_cd_no, recipe_cd_no,"
				+ "profile_nk, profile_img \r\n"
				+ "FROM TEAM9TH.sys_comment_tb c, TEAM9TH.sys_profile_tb p\r\n"
				+ "WHERE c.profile_cd_pk=p.profile_cd_pk and contents_cd_no=1 AND recipe_cd_no = ?\r\n"
				+ "ORDER BY comment_reg_dt";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, recipe_cd_no);
			rs = pst.executeQuery();
			while (rs.next()) {
				clist.add(new Comment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
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
		System.out.println("댓글 개수 :"+clist.size());
		return clist;
	}

	//댓글 입력
	public int inputComment(Comment comment) {
		int cnt = -1;
		String sql = "INSERT INTO TEAM9TH.sys_comment_tb(profile_cd_pk, comment_detail, recipe_cd_no) \r\n"
				+ "values(?,?,?)";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, comment.getProfile_cd_pk());
			pst.setString(2, comment.getComment_detail());
			pst.setString(3, comment.getRecipe_cd_no());
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

	//댓글 삭제
	public int deleteComment(String comment_cd_no) {
		int cnt = -1;
		String sql = "UPDATE TEAM9TH.sys_comment_Tb "
				+ "SET contents_cd_no = 2 "
				+ "WHERE comment_cd_no = ?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, comment_cd_no);
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
