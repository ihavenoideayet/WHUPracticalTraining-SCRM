

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class PersonMatchServlet
 */
@WebServlet("/PersonMatchServlet")
public class PersonMatchServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String 	img	= request.getParameter("img");	//Í¼ÏñÊý¾Ý
		FaceDetect detect = new FaceDetect();
		boolean isP = detect.detect(img);
		SqlOperator operator = new SqlOperator();
		if(isP) {
			String basePath = request.getSession().getServletContext()
					.getRealPath("picture/");
			long a = operator.matchFace(img,basePath);
			System.out.println(a);
		}
	}
}