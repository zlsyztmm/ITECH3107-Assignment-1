package au.edu.federation.itech3107.studentattendance30395696.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import au.edu.federation.itech3107.studentattendance30395696.App;
import au.edu.federation.itech3107.studentattendance30395696.entity.Student;

@Database(entities = {Student.class}, version = 1,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase INSTANCE;
    public abstract StudentDao studentDao();

    public static StudentDatabase getAppDatabase(String tbName) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(App.context,
                    StudentDatabase.class, tbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
