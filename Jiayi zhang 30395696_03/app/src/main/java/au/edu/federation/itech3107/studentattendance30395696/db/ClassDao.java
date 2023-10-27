package au.edu.federation.itech3107.studentattendance30395696.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import au.edu.federation.itech3107.studentattendance30395696.entity.Course;

@Dao
public interface ClassDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClass(Course course);


    @Query("select * from tb_course_entity")
    List<Course> queryAll();

    @Delete
    int delete(Course course);
}