package edu.oscail.cs.databasedemo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class CommentsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_COMMENT };

	private static final String TAG = "DBDEMO";

	CommentsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	void close() {
		dbHelper.close();
	}

	Comment createComment(String comment) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
		long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Comment newComment = cursorToComment(cursor);

		// Log the comment stored
		Log.d(TAG, "comment = " + cursorToComment(cursor).toString()
				+ " insert ID = " + insertId);

		cursor.close();
		return newComment;
	}

	void deleteComment(Comment comment) {
		long id = comment.getId();
		Log.d(TAG, "delete comment = " + id);
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	void deleteAllComments() {
		System.out.println("Comment deleted all");
		Log.d(TAG, "delete all = ");
		database.delete(MySQLiteHelper.TABLE_COMMENTS, null, null);
	}
	
	List<Comment> getAllComments() {
		List<Comment> comments = new ArrayList<Comment>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Comment comment = cursorToComment(cursor);
			Log.d(TAG, "get comment = " + cursorToComment(cursor).toString());
			comments.add(comment);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return comments;
	}

	private Comment cursorToComment(Cursor cursor) {
		Comment comment = new Comment();
		comment.setId(cursor.getLong(0));
		comment.setComment(cursor.getString(1));
		return comment;
	}
}