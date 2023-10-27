package au.edu.federation.itech3107.studentattendance30395696.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import au.edu.federation.itech3107.studentattendance30395696.entity.Course;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ClassDao_Impl implements ClassDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Course> __insertionAdapterOfCourse;

  private final EntityDeletionOrUpdateAdapter<Course> __deletionAdapterOfCourse;

  public ClassDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourse = new EntityInsertionAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tb_course_entity` (`m_id`,`classID`,`num`,`name`,`startTime`,`endTime`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        stmt.bindLong(1, value.m_id);
        stmt.bindLong(2, value.classID);
        if (value.num == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.num);
        }
        if (value.name == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.name);
        }
        if (value.startTime == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.startTime);
        }
        if (value.endTime == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.endTime);
        }
      }
    };
    this.__deletionAdapterOfCourse = new EntityDeletionOrUpdateAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tb_course_entity` WHERE `m_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        stmt.bindLong(1, value.m_id);
      }
    };
  }

  @Override
  public void insertClass(final Course course) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourse.insert(course);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Course course) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfCourse.handle(course);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Course> queryAll() {
    final String _sql = "select * from tb_course_entity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMId = CursorUtil.getColumnIndexOrThrow(_cursor, "m_id");
      final int _cursorIndexOfClassID = CursorUtil.getColumnIndexOrThrow(_cursor, "classID");
      final int _cursorIndexOfNum = CursorUtil.getColumnIndexOrThrow(_cursor, "num");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        final long _tmpClassID;
        _tmpClassID = _cursor.getLong(_cursorIndexOfClassID);
        final String _tmpNum;
        if (_cursor.isNull(_cursorIndexOfNum)) {
          _tmpNum = null;
        } else {
          _tmpNum = _cursor.getString(_cursorIndexOfNum);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpStartTime;
        if (_cursor.isNull(_cursorIndexOfStartTime)) {
          _tmpStartTime = null;
        } else {
          _tmpStartTime = _cursor.getString(_cursorIndexOfStartTime);
        }
        final String _tmpEndTime;
        if (_cursor.isNull(_cursorIndexOfEndTime)) {
          _tmpEndTime = null;
        } else {
          _tmpEndTime = _cursor.getString(_cursorIndexOfEndTime);
        }
        _item = new Course(_tmpClassID,_tmpNum,_tmpName,_tmpStartTime,_tmpEndTime);
        _item.m_id = _cursor.getLong(_cursorIndexOfMId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
