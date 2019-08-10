package library.com.dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import library.com.model.UserBorrowModel;
import library.com.util.DbUtil;

public class UserBorrowDao {

public int insertUserBorrow(Connection con,UserBorrowModel userborrow) throws Exception {		
		DbUtil dbUtil = new DbUtil();
		Connection con1 = dbUtil.getCon();
		String sql="insert into userborrow values(?,?,?,?)";
		PreparedStatement pstmt=(PreparedStatement) con1.prepareStatement(sql);
		
		//���������û���Ϣ
		pstmt.setInt(1, userborrow.getUser_id());
        pstmt.setInt(2, userborrow.getBook_number());
        pstmt.setDate(3, userborrow.getBorrow_date());
        pstmt.setInt(4, userborrow.getReturnbook());
   
        return pstmt.executeUpdate();	
	}
	
	//��
	private ResultSet selectUserBorrow() throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection con = dbUtil.getCon();
		String sql = "select * from user where user_id=? and book_number=?";
		PreparedStatement psta = con.prepareStatement(sql);
		ResultSet rs = psta.executeQuery(sql);//���ؽ����ResultSet
		
		while(rs.next()){
			int  user_id= rs.getInt(1);// �û�id
			int book_number = rs.getInt(2);//����
			Date  borrow_date = rs.getDate(3);// ����
			int returnbook=rs.getInt(4);//�Ƿ�黹
			
			System.out.println("user_id:"+user_id+" book_number��"+book_number+"borrow_date:"
			+borrow_date+"returnbook:"+returnbook);
		}
		con.close();
		return rs;
	}
	//����
	public int updateuserBorrow(Connection con,UserBorrowModel userborrow)throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection con3 = dbUtil.getCon();
        String sql="update userborrow set user_id=?,book_number=?,borrow_date=?,returnbook=?";
        PreparedStatement pstmt=(PreparedStatement) con3.prepareStatement(sql);

        pstmt.setInt(1, userborrow.getUser_id());
        pstmt.setInt(2, userborrow.getBook_number());
        pstmt.setDate(3, userborrow.getBorrow_date());
        pstmt.setInt(4, userborrow.getReturnbook());
        
        return pstmt.executeUpdate();
    }
	//ɾ��
	public int deleteUseBorrow(Connection con,UserBorrowModel userborrow,int user_id,int book_number)throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection con4= dbUtil.getCon();
        String sql="delete from userborrow where book_number=? and user_id=?";
        PreparedStatement pstmt=(PreparedStatement) con4.prepareStatement(sql);
       
		pstmt.setInt(1,userborrow.getUser_id());
		pstmt.setInt(2,userborrow.getBook_number());
        return pstmt.executeUpdate();
    }

}
