package au.edu.federation.itech3107.studentattendance30395696.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import au.edu.federation.itech3107.studentattendance30395696.entity.TeacherEntity;

@Dao
public interface TeacherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeacher(TeacherEntity teacherEntity);

    @Query("select * from tb_teacher_entity where user_name= :us_name")
    TeacherEntity queryTeacher(String us_name);

    @Query("select * from tb_teacher_entity where user_name= :us_name and user_pass= :us_pass")
    TeacherEntity queryTeacher(String us_name,String us_pass);

    @Query("select * from tb_teacher_entity")
    List<TeacherEntity> queryAll();
}