package library.com.model;

import java.security.Signature;
import java.sql.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class UserBorrowModel {
	private int user_id;
	private int book_number;
	private Date borrow_date;
	private int returnbook;
	
	public int getUser_id() {
		return getUser_id();
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBook_number() {
		return book_number;
	}
	public void setBook_number(int book_number) {
		this.book_number = book_number;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}
	public int getReturnbook() {
		return returnbook;
	}
	public void setReturnbook(int returnbook) {
		this.returnbook = returnbook;
	} 
	
}
