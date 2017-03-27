package gm.mysql.dh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class MySqlDataHandler {

	MySqlDatabase db;
	Connection connection;

	// instantiate
	public MySqlDataHandler(MySqlDatabase db) {
		this.db = db;
		connection = db.getConnection();
	}

	// SQL HANDLERS (see RESULTSET HANDLERS section for resultset handling like
	// printing etc)

	// update
	public void runSqlUpdate(String updateSql) {

		Statement statement = null;

		try {
			statement = connection.createStatement();
			statement.executeUpdate(updateSql);
		} catch (SQLException e1) {
			System.out.println(e1.getLocalizedMessage());
		}

	}

	// select
	public ResultSet runSqlQuery(String querySql) {

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(querySql);
		} catch (SQLException e1) {
			System.out.println(e1.getLocalizedMessage());
		}

		return resultSet;
	}

	// generic sql
	public ResultSet runSql(String sql) {

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e1) {
			System.out.println(e1.getLocalizedMessage());
		}

		return resultSet;
	}

	// RESULTSET HANDLERS
	public void printResultSetRows(ResultSet rs) throws Exception {

		ResultSetMetaData rsMetadata = rs.getMetaData();
		int colCount = rsMetadata.getColumnCount();
		System.out.println("Column Count - " + colCount);

		// For non-queries (like insert/update column-count of result set will
		// be zero
		if (colCount > 0) {
			rs.beforeFirst();
			while (rs.next()) {
				// System.out.println ("outer loop");
				for (int i = 1; i <= colCount; i++) {
					// System.out.println ("inner loop");
					System.out.println(rsMetadata.getColumnLabel(i) + " : "
							+ rs.getString(i));
				}
			}
		}
	}

	public void printResultSetRowsTabular(ResultSet rs) throws Exception {

		ResultSetMetaData rsMetadata = rs.getMetaData();
		int colCount = rsMetadata.getColumnCount();
		System.out.println("Column Count - " + colCount);

		// For non-queries (like insert/update column-count of result set will
		// be zero
		if (colCount > 0) {
			rs.beforeFirst();
			
			StringBuffer rowHeaderString = new StringBuffer();
			StringBuffer rowString = new StringBuffer();
			
			for (int i = 1; i <= colCount; i++) {  // for every column
				rowHeaderString.append(rsMetadata.getColumnLabel(i) + " ");
			}			
			
		    System.out.println(rowHeaderString.toString());
		    
			while (rs.next()) {  // for every row
				for (int i = 1; i <= colCount; i++) {  // for every column
				rowString.append(rs.getString(i) + " ");
				}
			    System.out.println(rowString.toString());
			    rowString.delete(0, rowString.length());
				}
		}
	}
}
