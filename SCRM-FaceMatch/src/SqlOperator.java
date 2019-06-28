

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import sun.misc.BASE64Decoder;

public class SqlOperator {
	 public static Connection getConnection()
	    {
	        String DBIP="192.168.153.182"; //数据库IP 即用即改
	        final String DB_DRIVER ="com.mysql.jdbc.Driver";
			final String DB_Name="facerecgnewretail";//数据库名称
			
	        final String DB_URL=
	                "jdbc:mysql://"+DBIP+":3306/"+DB_Name+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
					
	        final String DB_USER="root";
	        final String DB_PASSWORD="123456";

	        Connection conn=null;

	        try {
	            //加载驱动程序类
	            Class.forName(DB_DRIVER);
	            //建立数据库连接
	            conn=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
	            //调整时区
	            Statement statement=conn.createStatement();
	            statement.execute("set global time_zone='+8:00';");
	        }
	        catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        finally
	        {
	        }
	        return conn;
	    }
	public static long matchFace(String img,String path){
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String searchFace = "select * from customer";
			ResultSet face = stmt.executeQuery(searchFace);
			FaceMatch a = new FaceMatch();
			while(face.next()) {
				String aFace = face.getString("Mugshot");
				if(a.match(img,aFace)) {
					int id = face.getInt("id");
					stmt.close();
					con.close();
					return id;
				}
			}
			face.close();
			int id = (int) (1+Math.random()*1000000);
			String fileName = System.currentTimeMillis() + ".png";
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(img);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] = (byte) (bytes[i]+256);
				}
			}
			OutputStream out = new FileOutputStream(path + fileName);
			out.write(bytes);
			out.flush();
			out.close();
			String insert = "Insert into customer (id,mugshot) values (?,?)";
			java.sql.PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1,id);
			ps.setString(2,path+fileName);
			ps.executeUpdate();
			ps.close();
			stmt.close();
			con.close();
			return id;
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			}
		return -1;
	}
	//搜索信息,season,special,nomal
	public static void searchComidity(String kind){
		switch(kind) {
		case "season":
			break;
		case "special":
			break;
		case "nomal":
			break;
		}
    }
	//选出一个商品的信息
	public static void searchone(long id) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String searchCommodity = "select * from commodity where id = "+id;
			ResultSet commodity = stmt.executeQuery(searchCommodity);
			while(commodity.next()) {
				String name = commodity.getString("name");
				int Quantity = commodity.getInt("quantity");
				double price = commodity.getDouble("price");
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			}	
	}
}
