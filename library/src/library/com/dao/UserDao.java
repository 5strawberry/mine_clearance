package library.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import library.com.model.UserModel;
import library.com.util.DbUtil;

public class UserDao {

	//��
	public int insertUser(Connection con,UserModel user) throws Exception {

		
		DbUtil dbUtil = new DbUtil();
		Connection con1 = dbUtil.getCon();
		String sql="insert into user values(?,?,?)";
		PreparedStatement pstmt=(PreparedStatement) con1.prepareStatement(sql);
		
		//���������û���Ϣ
		pstmt.setString(1, user.getUser_id());
        pstmt.setString(2, user.getUser_password());
        pstmt.setString(3, user.getUser_name());
   
        return pstmt.executeUpdate();	
	}
	
	//��
	public ResultSet selectUser(Connection con,UserModel user) throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection conn = dbUtil.getCon();
		String sql = "select * from user";
		PreparedStatement psta = con.prepareStatement(sql);
		ResultSet rs = psta.executeQuery(sql);//���ؽ����ResultSet
		while(rs.next()){
			String  user_id= rs.getString(1);// �û�id
			String user_password = rs.getString(2);//�û�����
			String user_name = rs.getString(3);// �û���			
			System.out.println("user_id:"+user_id+" user_password="+user_password+" user_name:"
			+user_name); 
			/*conn.close();*/
		}		
		
		return psta.executeQuery(sql);
		
	}
	//����
	public int updateUser(Connection con,UserModel user)throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection con3 = dbUtil.getCon();
        String sql="update user set user_id=?,user_password=?,user_name=?";
        PreparedStatement pstmt=(PreparedStatement) con3.prepareStatement(sql);

        pstmt.setString(1, user.getUser_id());
        pstmt.setString(2, user.getUser_password());
        pstmt.setString(3, user.getUser_name());
        return pstmt.executeUpdate();
    }
	//ɾ��
	public int deleteUser(Connection con,UserModel user,int user_id)throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection con4= dbUtil.getCon();
        String sql="delete from user where user_id=?";
        PreparedStatement pstmt=(PreparedStatement) con4.prepareStatement(sql);
       
		pstmt.setString(1,user.getUser_id());
        return pstmt.executeUpdate();
    }

}
