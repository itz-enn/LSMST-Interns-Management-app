/*
 * @author Kayode Ojo
 */
package smaApi;

import java.sql.SQLException;

public class test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		studentModel student = new dbHelper().getStudent("Toyo", "c++");
System.out.println(student.toString());

	}

}
