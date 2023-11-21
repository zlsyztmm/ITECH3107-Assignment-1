package au.edu.federation.itech3107.studentattendance30395696.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import au.edu.federation.itech3107.studentattendance30395696.App;
import au.edu.federation.itech3107.studentattendance30395696.entity.Course;

@Database(entities = {Course.class}, version = 1, exportSchema = false)
public abstract class ClassDatabase extends RoomDatabase {
    private static ClassDatabase INSTANCE;
    public abstract ClassDao classDao();

    public static ClassDatabase getAppDatabase() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(App.context,
                    ClassDatabase.class,"ClassDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
