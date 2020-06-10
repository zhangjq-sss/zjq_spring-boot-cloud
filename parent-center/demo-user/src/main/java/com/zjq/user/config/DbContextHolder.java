package com.zjq.user.config;

public class DbContextHolder {
	//列举数据源的key

	public enum DbType {

	WRITE, READ1,READ2

	}



	// private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

	private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<DbType>() {

	@Override

	protected DbType initialValue() {

	return DbType.WRITE;

	}

	};



	public static void setDbType(DbType dbType) {

	if (dbType == null)

	throw new NullPointerException();

	contextHolder.set(dbType);

	}



	public static DbType getDbType() {

	return contextHolder.get() == null ? DbType.WRITE : contextHolder.get();

	}

	public static void resetDbType(){

	contextHolder.set(DbType.WRITE);

	}



	public static void clearDbType() {

	contextHolder.remove();

	}

}
