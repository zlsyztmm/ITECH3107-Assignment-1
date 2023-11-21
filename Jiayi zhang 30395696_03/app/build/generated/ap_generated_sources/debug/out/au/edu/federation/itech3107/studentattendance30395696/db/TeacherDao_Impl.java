package au.edu.federation.itech3107.studentattendance30395696.db;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import au.edu.federation.itech3107.studentattendance30395696.entity.TeacherEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TeacherDao_Impl implements TeacherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TeacherEntity> __insertionAdapterOfTeacherEntity;

  public TeacherDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTeacherEntity = new EntityInsertionAdapter<TeacherEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tb_teacher_entity` (`m_id`,`user_name`,`user_pass`,`flag`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TeacherEntity value) {
        stmt.bindLong(1, value.m_id);
        if (value.user_name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.user_name);
        }
        if (value.user_pass == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.user_pass);
        }
        final Integer _tmp;
        _tmp = value.flag == null ? null : (value.flag ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
      }
    };
  }

  @Override
  public void insertTeacher(final TeacherEntity teacherEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTeacherEntity.insert(teacherEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public TeacherEntity queryTeacher(final String us_name) {
    final String _sql = "select * from tb_teacher_entity where user_name= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (us_name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, us_name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMId = CursorUtil.getColumnIndexOrThrow(_cursor, "m_id");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfUserPass = CursorUtil.getColumnIndexOrThrow(_cursor, "user_pass");
      final int _cursorIndexOfFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "flag");
      final TeacherEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpUser_pass;
        if (_cursor.isNull(_cursorIndexOfUserPass)) {
          _tmpUser_pass = null;
        } else {
          _tmpUser_pass = _cursor.getString(_cursorIndexOfUserPass);
        }
        final Boolean _tmpFlag;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfFlag)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfFlag);
        }
        _tmpFlag = _tmp == null ? null : _tmp != 0;
        _result = new TeacherEntity(_tmpUser_name,_tmpUser_pass,_tmpFlag);
        _result.m_id = _cursor.getLong(_cursorIndexOfMId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public TeacherEntity queryTeacher(final String us_name, final String us_pass) {
    final String _sql = "select * from tb_teacher_entity where user_name= ? and user_pass= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (us_name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, us_name);
    }
    _argIndex = 2;
    if (us_pass == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, us_pass);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMId = CursorUtil.getColumnIndexOrThrow(_cursor, "m_id");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfUserPass = CursorUtil.getColumnIndexOrThrow(_cursor, "user_pass");
      final int _cursorIndexOfFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "flag");
      final TeacherEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpUser_pass;
        if (_cursor.isNull(_cursorIndexOfUserPass)) {
          _tmpUser_pass = null;
        } else {
          _tmpUser_pass = _cursor.getString(_cursorIndexOfUserPass);
        }
        final Boolean _tmpFlag;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfFlag)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfFlag);
        }
        _tmpFlag = _tmp == null ? null : _tmp != 0;
        _result = new TeacherEntity(_tmpUser_name,_tmpUser_pass,_tmpFlag);
        _result.m_id = _cursor.getLong(_cursorIndexOfMId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TeacherEntity> queryAll() {
    final String _sql = "select * from tb_teacher_entity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMId = CursorUtil.getColumnIndexOrThrow(_cursor, "m_id");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfUserPass = CursorUtil.getColumnIndexOrThrow(_cursor, "user_pass");
      final int _cursorIndexOfFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "flag");
      final List<TeacherEntity> _result = new ArrayList<TeacherEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TeacherEntity _item;
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpUser_pass;
        if (_cursor.isNull(_cursorIndexOfUserPass)) {
          _tmpUser_pass = null;
        } else {
          _tmpUser_pass = _cursor.getString(_cursorIndexOfUserPass);
        }
        final Boolean _tmpFlag;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfFlag)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfFlag);
        }
        _tmpFlag = _tmp == null ? null : _tmp != 0;
        _item = new TeacherEntity(_tmpUser_name,_tmpUser_pass,_tmpFlag);
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
