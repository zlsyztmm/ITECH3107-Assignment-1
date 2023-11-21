package au.edu.federation.itech3107.studentattendance30395696.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ClassDatabase_Impl extends ClassDatabase {
  private volatile ClassDao _classDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tb_course_entity` (`m_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `classID` INTEGER NOT NULL, `num` TEXT, `name` TEXT, `startTime` TEXT, `endTime` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e6e9b6f68a6a5daca7de0b81b31c3573')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `tb_course_entity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTbCourseEntity = new HashMap<String, TableInfo.Column>(6);
        _columnsTbCourseEntity.put("m_id", new TableInfo.Column("m_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTbCourseEntity.put("classID", new TableInfo.Column("classID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTbCourseEntity.put("num", new TableInfo.Column("num", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTbCourseEntity.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTbCourseEntity.put("startTime", new TableInfo.Column("startTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTbCourseEntity.put("endTime", new TableInfo.Column("endTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTbCourseEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTbCourseEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTbCourseEntity = new TableInfo("tb_course_entity", _columnsTbCourseEntity, _foreignKeysTbCourseEntity, _indicesTbCourseEntity);
        final TableInfo _existingTbCourseEntity = TableInfo.read(_db, "tb_course_entity");
        if (! _infoTbCourseEntity.equals(_existingTbCourseEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "tb_course_entity(au.edu.federation.itech3107.studentattendance30395696.entity.Course).\n"
                  + " Expected:\n" + _infoTbCourseEntity + "\n"
                  + " Found:\n" + _existingTbCourseEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e6e9b6f68a6a5daca7de0b81b31c3573", "e913db3f16b3e52896aed638d9a50a05");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "tb_course_entity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `tb_course_entity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ClassDao.class, ClassDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public ClassDao classDao() {
    if (_classDao != null) {
      return _classDao;
    } else {
      synchronized(this) {
        if(_classDao == null) {
          _classDao = new ClassDao_Impl(this);
        }
        return _classDao;
      }
    }
  }
}
