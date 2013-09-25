package com.smaple.libs.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public final class SqliteUtils {

	public final static String DATABASE_TABLES = "SELECT name FROM sqlite_master WHERE type = 'table'";

	public final static String SQLITE_DATABASE_VERSION = "SELECT sqlite_version() AS sqlite_version";

	public final static String  CHECK_DATABASE_TABLE_INFO="PRAGMA table_info(\"%1$s\")";
	
	public static List<String> getDBTables(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery(DATABASE_TABLES, null);
		List<String> tables = null;
		if (cursor != null) {
			tables = new ArrayList<String>(cursor.getCount());

			try {
				if (cursor.moveToFirst()) {
					do {
						tables.add(cursor.getString(0));
					} while (cursor.moveToNext());
				}
			} finally {
				cursor.close();
			}
		}
		return tables;
	}

	public static String getSQLiteVersion(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery(SQLITE_DATABASE_VERSION, null);
		StringBuilder sqlite_version = new StringBuilder();
		try {
			if (cursor != null && cursor.moveToFirst()) {
				sqlite_version.append(cursor.getString(0));
				while (cursor.moveToNext()) {
					sqlite_version.append(cursor.getString(0));
				}
			}
		} finally {
			cursor.close();
		}
		return sqlite_version.toString();
	}
}
