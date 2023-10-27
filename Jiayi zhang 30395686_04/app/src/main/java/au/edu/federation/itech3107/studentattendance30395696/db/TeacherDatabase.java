package au.edu.federation.itech3107.studentattendance30395696.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import au.edu.federation.itech3107.studentattendance30395696.App;
import au.edu.federation.itech3107.studentattendance30395696.entity.TeacherEntity;

@Database(entities = {TeacherEntity.class}, version = 1, exportSchema = false)
public abstract class TeacherDatabase extends RoomDatabase {
    private static TeacherDatabase INSTANCE;
    public abstract TeacherDao teacherDao();

    public static TeacherDatabase getAppDatabase() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(App.context,
                    TeacherDatabase.class,"TeacherDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
