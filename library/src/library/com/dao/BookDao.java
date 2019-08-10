package library.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import library.com.model.BookModel;
import library.com.util.DbUtil;
//import com.mysql.jdbc.Connection;

public class BookDao {
	
	//��
	public int insertBook(Connection con,BookModel book) throws Exception {
		
		DbUtil dbUtil = new DbUtil();
		Connection con1 = dbUtil.getCon();
		String sql="insert into book values(?,?,?,?,?,?)";
		PreparedStatement pstmt=(PreparedStatement) con1.prepareStatement(sql);
		
		//��������ͼ����Ϣ
		pstmt.setString(1, book.getBook_author());
        pstmt.setString(2, book.getBook_name());
        pstmt.setString(3, book.getBook_number());
        pstmt.setString(4, book.getBook_prince());
        pstmt.setString(5, book.getPublishment());
        pstmt.setString(6, book.getBorrow());
       
        return pstmt.executeUpdate();
       
	}
	//��
	public ResultSet selectBook() throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection con = dbUtil.getCon();
		String sql = "select * from book";
		PreparedStatement psta = con.prepareStatement(sql);
		ResultSet rs = psta.executeQuery(sql);//���ؽ����ResultSet
		
		while(rs.next()){
			String  book_author= rs.getString(1);// ��ȡ��һ���е�ֵ����
			String book_name = rs.getString(2);//  ͼ������
			String book_number = rs.getString(3);// ͼ����
			String book_price = rs.getString(4);// ͼ��۸�
			String publishment=rs.getString(5);//������	
			String borrow=rs.getString(6);//�Ƿ񱻽���
			System.out.println("book_author:"+book_author+" book_name="+book_name+"book_number:"
			+book_number +"book_price:"+book_price+	"publishment:"+publishment+"borrow:"+borrow);
		}
		return psta.executeQuery(sql);
	}
	//����
	public int updatebook(Connection con,BookModel book)throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection con3 = dbUtil.getCon();
        String sql="update book set borrow=? where book_number=?";
        PreparedStatement pstmt=(PreparedStatement) con3.prepareStatement(sql);

        pstmt.setString(2, book.getBook_number());       
        pstmt.setString(1, book.getBorrow());
        
        return pstmt.executeUpdate();
    }
	//ɾ��
	public int deletebook(Connection con,BookModel book)throws Exception{
		DbUtil dbUtil = new DbUtil();
		Connection con4= dbUtil.getCon();
        String sql="delete from book where book_number=?";
        PreparedStatement pstmt=(PreparedStatement) con4.prepareStatement(sql);
        
		pstmt.setString(1,book.getBook_number());		
        return pstmt.executeUpdate();
    }

}
