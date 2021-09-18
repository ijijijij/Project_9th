
package cooking.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import cooking.service.AccountService;
import cooking.service.AvatarService;
import cooking.service.CookBangService;
import cooking.service.RecipeService;
import cooking.vo.Avatar;
import cooking.vo.Comment;
import cooking.vo.CookBang;
import cooking.vo.MyAvatar;
import cooking.vo.Page;
import cooking.vo.PrivateInfo;
import cooking.vo.Profile;
import cooking.vo.Recipe;
import teamP_09.Member;

/**
 * Servlet implementation class MyRoom
 */
@MultipartConfig(
		// location = "C:\\javaexp\\workspace2\\jspexp\\src\\main\\webapp\\fileStorage",
		maxFileSize = -1, maxRequestSize = -1, fileSizeThreshold = 1024)

@WebServlet(name = "myroom", urlPatterns = { "/myroom", "/deleteRecipe", "/inputComment", "/deleteComment",
		"/myProfile", "/myProfile_update", "/myPrivateInfo",
		"/myPrivateInfo_update", "/logOut", "/signOut", "/avatar", "/raise", "/offer",
		"/upgrade", "/store", "/takeAvatar", "/recipe", "/cookBang", "/cb"})
public class MyRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "fileStorage";
	private AccountService service;
	private AvatarService avtService;
	private RecipeService rcpService;
	private CookBangService cbService;
	private Page bbsPage;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public MyRoom() {
		super();
		service = new AccountService();
		avtService = new AvatarService();
		rcpService = new RecipeService();
		cbService = new CookBangService();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		// 샘플 방문대상 정보 설정
		//String visited_cd_pk = request.getParameter("visited_cd_pk");
		//PriavateInfo visited = ((PrivateInfo)request.setAttribute("visited", service.getPrivateInfo(visited_cd_pk));
		String visitor = request.getParameter("visitor");
		PrivateInfo visited = service.getPrivateInfo(visitor);
		request.setAttribute("visited", visited);
		String visited_cd_pk = null;
		if(visited!=null) {
			visited_cd_pk = visited.getUser_cd_pk();
		}
		// 세션 설정
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		String memNum = null;
		if(member!=null) {
			memNum = member.getMemnum();
		}
		//String memNum = member.getMemnum();
		PrivateInfo privateInfo = null;
		if(memNum!=null) {
			privateInfo = service.getPrivateInfo(memNum);
		}
		session.setAttribute("user", privateInfo);
		//PrivateInfo privateInfo = (PrivateInfo)session.getAttribute("user");
		Profile profile = null;
		String user_cd_pk = null;
		// 세션 null 체크
		if(privateInfo!=null) {
			 user_cd_pk = ((PrivateInfo) session.getAttribute("user")).getUser_cd_pk();
			// 기본 객체 설정
			profile = service.getProfile(user_cd_pk);
			request.setAttribute("profile", profile);
			request.setAttribute("privateInfo", privateInfo);
		}
		//기본 객체 설정
		PrivateInfo visited_privateInfo = service.getPrivateInfo(visited_cd_pk);
		request.setAttribute("visited_privateInfo", visited_privateInfo);
		Profile visited_profile = service.getProfile(visited_cd_pk);
		System.out.println("visited_profile:"+visited_profile);
		request.setAttribute("visited_profile", visited_profile);
		// command 설정
		String command = request.getServletPath();
		String page = "";
		boolean redirect = false;
		boolean forward = true;
		// command 확인
		LocalTime now = LocalTime.now();
		System.out.println(now + " 커맨드 페이지:" + command);
		// command에 따른 페이지 설정
		// 마이룸 기본(레시피)
		if (command.equals("/myroom")) { 
			visited_cd_pk = request.getParameter("visitor");
			String prf_cd_pk = visited_profile.getProfile_cd_pk();
			if(user_cd_pk!=null && visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", service.getProfile(user_cd_pk));
				prf_cd_pk = profile.getProfile_cd_pk();
			}
			//레시피 기본
			int numByPage = 5;
			int pageNum = 1;
			int shownPageNum = 3;
			if(request.getParameter("page")!=null) {
				pageNum = Integer.parseInt(request.getParameter("page"));
			}
			String recipe_cd_no = request.getParameter("recipe_cd_no");
			System.out.println("prf_cd_pk:"+prf_cd_pk);
			if(recipe_cd_no==null) {
				Recipe recipe = rcpService.getDefaultRecipe(prf_cd_pk);
				if(recipe!=null) {
					recipe_cd_no = recipe.getRecipe_cd_no();
					request.setAttribute("recipe", recipe);
				}
			} else {
				request.setAttribute("recipe", rcpService.getRecipe(recipe_cd_no));
				rcpService.updateViewCnt(recipe_cd_no);
			}
			// @@@@@@@@@아래 user_cd_pk 수정 필요 @@@@@@
			ArrayList<Recipe> rcpList = rcpService.getRecipeList(numByPage, pageNum, prf_cd_pk);
			ArrayList<Comment> cmtList = rcpService.getCommentList(recipe_cd_no);
			int realLastPage = rcpService.getRealLastPage(numByPage, prf_cd_pk);
			int firstPage = rcpService.getFirstPage(shownPageNum, pageNum);
			int lastPage = rcpService.getLastPage(shownPageNum, realLastPage, firstPage);
			boolean prevPage = rcpService.prevPage(firstPage, shownPageNum);
			boolean nextPage = rcpService.nextPage(lastPage, realLastPage);
			bbsPage = new Page(pageNum, numByPage, shownPageNum, firstPage, lastPage, realLastPage, prevPage, nextPage);
			System.out.println(user_cd_pk);
			System.out.println(visited_cd_pk);
			//Profile profile = service.getProfile(user_cd_pk);
			//request.setAttribute("profile", profile);
			request.setAttribute("proc", "recipe");
			request.setAttribute("profile", profile);
			request.setAttribute("bbsPage", bbsPage);
			request.setAttribute("rcpList", rcpList);
			request.setAttribute("cmtList", cmtList);
			page = "myRoom\\recipes\\myRecipes2.jsp";
			//댓글 등록
		} else if (command.equals("/inputComment")) {
			String comment_detail = request.getParameter("comment_w");
			String recipe_cd_no = request.getParameter("recipe_cd_no");
			visited_cd_pk = request.getParameter("visitor");
			rcpService.inputComment(new Comment(profile.getProfile_cd_pk(),comment_detail, recipe_cd_no));
			redirect = true;
			page = "myroom?visitor="+visited_cd_pk+"&recipe_cd_no="+recipe_cd_no;
			//댓글 삭제
		} else if (command.equals("/deleteComment")) {
			String comment_cd_no = request.getParameter("comment_cd_no");
			String recipe_cd_no = request.getParameter("recipe_cd_no");
			visited_cd_pk = request.getParameter("visitor");
			System.out.println("댓글번호:"+comment_cd_no);
			rcpService.deleteComment(comment_cd_no);
			redirect = true;
			page = "myroom?visitor="+visited_cd_pk+"&recipe_cd_no="+recipe_cd_no;	
			//레시피 삭제
		} else if(command.equals("/deleteRecipe")) {
			String recipe_cd_no = request.getParameter("recipe_cd_no");
			visited_cd_pk = request.getParameter("visitor");
			redirect = true;
			rcpService.deleteRecipe(recipe_cd_no);
			page = "myroom?visitor="+visited_cd_pk;
			// 마이룸 > 내 정보(>프로필)
		} else if (command.equals("/myProfile")) {
			//Profile profile = service.getProfile(user_cd_pk);
			request.setAttribute("profile", profile);
			page = "myRoom\\account\\profile.jsp";
			// 마이룸 > 내 정보 > 개인정보
		} else if (command.equals("/myPrivateInfo")) {
			//Profile profile = service.getProfile(user_cd_pk);
			request.setAttribute("profile", profile);
			page = "myRoom\\account\\privateInfo.jsp";
			// 프로필 정보 수정
		} else if (command.equals("/myProfile_update")) {
			// 경로 설정
			String filePath = request.getServletContext().getRealPath("");
			String uploadPath = filePath + UPLOAD_DIR;
			// form 가져오기
			Collection<Part> parts = request.getParts();
			ArrayList<String> slist = new ArrayList<String>();
			ArrayList<String> flist = new ArrayList<String>();
			// 디렉토리 없는 경우 생성
			File fileDir = new File(uploadPath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			// form에서 file타입과 그 외 타입 분리해서 저장
			for (Part part : parts) {
				if (part.getHeader("Content-Disposition").contains("filename=")) {
					String fileName = extractFileName(part.getHeader("Content-Disposition"));
					if (part.getSize() > 0) {
						part.write(uploadPath + File.separator + fileName);
						part.delete();
					}
					flist.add(UPLOAD_DIR + File.separator + fileName);
				} else {
					slist.add(request.getParameter(part.getName()));
				}
			}
			// 매개변수 설정
			String profile_img = flist.get(0);
			if (profile_img.substring(UPLOAD_DIR.length() + 1) == "") {
				profile_img = service.getProfile(user_cd_pk).getProfile_img();
			}
			String profile_bimg = flist.get(1);
			if (profile_bimg.substring(UPLOAD_DIR.length() + 1) == "") {
				profile_bimg = service.getProfile(user_cd_pk).getProfile_bimg();
			}
			String profile_nk = slist.get(0);
			String profile_intro = slist.get(1);
			// service 호출 및 이동 페이지 지정
			int cnt = service
					.updateProfile(new Profile(profile_img, profile_bimg, profile_nk, profile_intro, user_cd_pk));
			if (cnt > 0) {
				redirect = true;
				page = "myProfile";
			} else {
				page = "myroom";
			}
			// 개인정보 수정
		} else if (command.equals("/myPrivateInfo_update")) {
			String pw = request.getParameter("pw");
			String newPw = request.getParameter("newPw");
			String mail = request.getParameter("mail");
			String tel = request.getParameter("tel");
			int cnt = -1;
			if (newPw.length() != 0) {
				cnt = service.updatePrivateInfo(new PrivateInfo(user_cd_pk, newPw, tel.replace("-", ""), mail));
			} else {
				cnt = service.updatePrivateInfo(new PrivateInfo(user_cd_pk, pw, tel.replace("-", ""), mail));
			}
			if (cnt > 0) {
				redirect = true;
				page = "myPrivateInfo";
			}
			// 로그아웃
		} else if (command.equals("/logOut")) {
			session.invalidate();
			redirect = true;
			page = "main.do";
			// 회원탈퇴
		} else if (command.equals("/signOut")) {
			int cnt = service.signOut(user_cd_pk);
			if (cnt > 0) {
				redirect = true;
				page = "#";
			}

			////////////// 아바타////////////////
		} else if (command.equals("/avatar")) {
			ArrayList<MyAvatar> myAvtList = null;
			visited_cd_pk = request.getParameter("visitor");
			if(visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", service.getProfile(user_cd_pk));
			}
			if(visited_cd_pk.equals(user_cd_pk)) {
				myAvtList = avtService.getMyAvatar(user_cd_pk);
			} else {
				myAvtList = avtService.getMyAvatar(visited_cd_pk);
			}
			//Profile profile = service.getProfile(user_cd_pk);
			request.setAttribute("myAvtList", myAvtList);
			//request.setAttribute("visitedAvtList", visitedAvtList);
			//request.setAttribute("profile", profile);
			request.setAttribute("proc", "avatar");
			page = "myRoom\\avatar\\avatar.jsp";
			// 아바타 키우기 > 선택
		} else if (command.equals("/raise")) {
			visited_cd_pk = request.getParameter("visitor");
			if(visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", service.getProfile(user_cd_pk));
			}
			String mavt_cd_pk = request.getParameter("mavt");
			MyAvatar myAvt = avtService.getThisAvatar(mavt_cd_pk);
			//Profile profile = service.getProfile(user_cd_pk);
			request.setAttribute("myAvt", myAvt);
			//request.setAttribute("profile", profile);
			request.setAttribute("proc", "raise");
			page = "myRoom\\avatar\\avatar.jsp";
			// 아바타 키우기 > 선택 > 옵션 주기
		} else if (command.equals("/offer")) {
			visited_cd_pk = request.getParameter("visitor");
			if(visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", service.getProfile(user_cd_pk));
			}
			String option = request.getParameter("option");
			String mavt_cd_pk = request.getParameter("mavt_cd_pk"); 
			MyAvatar myAvt = avtService.getThisAvatar(mavt_cd_pk);
			//Profile profile = service.getProfile(user_cd_pk);
			request.setAttribute("myAvt", myAvt);
			//request.setAttribute("profile", profile);
			request.setAttribute("proc", "raise");
			int usePoint = 20;
			if(profile.getProfile_point()>=usePoint) {
				avtService.updateOptionStp(option, mavt_cd_pk);
				avtService.updatePoint(user_cd_pk, usePoint);
				forward = false;
				response.setContentType("text/html;charset=utf-8"); 
				response.getWriter().print(avtService.getJsonAvatar(mavt_cd_pk));
			}
			// 아바타 업그레이드
		} else if (command.equals("/upgrade")) {
			visited_cd_pk = request.getParameter("visitor");
			if(visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", service.getProfile(user_cd_pk));
			}
			//Profile profile = service.getProfile(user_cd_pk);
			String mavt_cd_pk = request.getParameter("mavt_cd_pk");
			MyAvatar myAvt = avtService.getThisAvatar(mavt_cd_pk);
			request.setAttribute("myAvt", myAvt);
			//request.setAttribute("profile", profile);
			request.setAttribute("proc", "raise");
			avtService.updateAvtStp(mavt_cd_pk);
			avtService.initializeStp(mavt_cd_pk);
			page = "raise?mavt="+mavt_cd_pk+"&visitor="+visited_cd_pk;
			//forward = false;
			//response.setContentType("text/html;charset=utf-8"); 
			//response.getWriter().print(avtService.getJsonAvatar(mavt_cd_pk));
			// 아바타 상점
		} else if (command.equals("/store")) {
			//Profile profile = service.getProfile(user_cd_pk);
			visited_cd_pk = request.getParameter("visitor");
			if(visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", service.getProfile(user_cd_pk));
			}
			ArrayList<Avatar> avtList = avtService.getAvatar();
			//request.setAttribute("profile", profile);
			request.setAttribute("avtList", avtList);
			request.setAttribute("proc", "store");
			page = "myRoom\\avatar\\avatar.jsp";
			//아바타 구매
		} else if (command.equals("/takeAvatar")) {
			visited_cd_pk = request.getParameter("visitor");
			if(visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", service.getProfile(user_cd_pk));
			}
			//Profile profile = service.getProfile(user_cd_pk);
			String avt_cd_pk = request.getParameter("avt_cd_pk");
			String mavt_nm = request.getParameter("mavt_nm");
			int usePoint = 200;
			avtService.updatePoint(user_cd_pk, usePoint);
			avtService.buyAvatar(new MyAvatar(user_cd_pk, avt_cd_pk, mavt_nm));
			redirect = true;
			page = "avatar?visitor="+visited_cd_pk;
			//쿡방
		} else if (command.equals("/cookBang")) {
			visited_cd_pk = request.getParameter("visitor");
			String profile_cd_pk = visited_profile.getProfile_cd_pk();
			if(visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", profile);
				profile_cd_pk = profile.getProfile_cd_pk();
			}
			ArrayList<CookBang> cookBangList = cbService.getCookBangList(profile_cd_pk);
			System.out.println("쿡방리스트 개수:"+cookBangList.size());
			request.setAttribute("cookBangList", cookBangList);
			request.setAttribute("proc", "archive");
			page = "myRoom\\cookbang\\cookBang.jsp";
			//쿡방 > 선택
		} else if (command.equals("/cb")) {
			request.setAttribute("profile", profile);
			visited_cd_pk = request.getParameter("visitor");
			if(visited_cd_pk.equals(user_cd_pk)) {
				request.setAttribute("visited_profile", service.getProfile(user_cd_pk));
			}
			String cbang_cd_pk = request.getParameter("cbNum");
			CookBang cbang = cbService.getCookBang(cbang_cd_pk);
			System.out.println("cbang 이름:"+cbang.getCbang_nm());
			request.setAttribute("cbang", cbang);
			request.setAttribute("proc", "room");
			page = "myRoom\\cookbang\\cookBang.jsp";
		}

		// 페이지 호출
		if (!redirect && forward) {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(redirect) {
			response.sendRedirect(page);
		}

	}

	// 프로필 수정 > 이미지 변경 시 사용 메서드
	private String extractFileName(String partHeader) {
		for (String cd : partHeader.split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
				int index = fileName.lastIndexOf(File.separator);
				return fileName.substring(index + 1);
			}
		}
		return null;
	}

}
