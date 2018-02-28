package test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TestDB {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/tbcs";
			String user = "root";
			String pass = "";
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		Connection conn = getConnection();
		String sql = "select * from dict_item";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i <= data.getColumnCount(); i++) {
					// ��������е���Ŀ��ʵ������
					int columnCount = data.getColumnCount();
					// ���ָ���е�����
					String columnName = data.getColumnName(i);
					// ���ָ���е���ֵ
					String columnValue = rs.getString(i);
					// ���ָ���е���������
					int columnType = data.getColumnType(i);
					// ���ָ���е�����������
					String columnTypeName = data.getColumnTypeName(i);
					// ���ڵ�Catalog����
					String catalogName = data.getCatalogName(i);
					// ��Ӧ�������͵���
					String columnClassName = data.getColumnClassName(i);
					// �����ݿ������͵�����ַ�����
					int columnDisplaySize = data.getColumnDisplaySize(i);
					// Ĭ�ϵ��еı���
					String columnLabel = data.getColumnLabel(i);
					// ����е�ģʽ
					String schemaName = data.getSchemaName(i);
					// ĳ�����͵ľ�ȷ��(���͵ĳ���)
					int precision = data.getPrecision(i);
					// С������λ��
					int scale = data.getScale(i);
					// ��ȡĳ�ж�Ӧ�ı���
					String tableName = data.getTableName(i);
					// �Ƿ��Զ�����
					boolean isAutoInctement = data.isAutoIncrement(i);
					// �����ݿ����Ƿ�Ϊ������
					boolean isCurrency = data.isCurrency(i);
					// �Ƿ�Ϊ��
					int isNullable = data.isNullable(i);
					// �Ƿ�Ϊֻ��
					boolean isReadOnly = data.isReadOnly(i);
					// �ܷ������where��
					boolean isSearchable = data.isSearchable(i);
					System.out.println(columnCount);
					System.out.println("�����" + i + "���ֶ�����:" + columnName);
					System.out.println("�����" + i + "���ֶ�ֵ:" + columnValue);
					System.out.println("�����" + i + "������,����SqlType�еı��:"
							+ columnType);
					System.out.println("�����" + i + "������������:" + columnTypeName);
					System.out.println("�����" + i + "���ڵ�Catalog����:"
							+ catalogName);
					System.out.println("�����" + i + "��Ӧ�������͵���:"
							+ columnClassName);
					System.out.println("�����" + i + "�����ݿ������͵�����ַ�����:"
							+ columnDisplaySize);
					System.out.println("�����" + i + "��Ĭ�ϵ��еı���:" + columnLabel);
					System.out.println("�����" + i + "��ģʽ:" + schemaName);
					System.out.println("�����" + i + "���͵ľ�ȷ��(���͵ĳ���):" + precision);
					System.out.println("�����" + i + "С������λ��:" + scale);
					System.out.println("�����" + i + "��Ӧ�ı���:" + tableName);
					System.out.println("�����" + i + "�Ƿ��Զ�����:" + isAutoInctement);
					System.out.println("�����" + i + "�����ݿ����Ƿ�Ϊ������:" + isCurrency);
					System.out.println("�����" + i + "�Ƿ�Ϊ��:" + isNullable);
					System.out.println("�����" + i + "�Ƿ�Ϊֻ��:" + isReadOnly);
					System.out.println("�����" + i + "�ܷ������where��:"
							+ isSearchable);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
