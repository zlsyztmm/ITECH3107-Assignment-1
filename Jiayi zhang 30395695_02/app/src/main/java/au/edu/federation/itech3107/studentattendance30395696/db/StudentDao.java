package au.edu.federation.itech3107.studentattendance30395696.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import au.edu.federation.itech3107.studentattendance30395696.entity.Student;

@Dao
public interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

    @Query("update tb_student_entity set flag=:flag1 where m_id=:id")
    void changeStudentFlag(Long id, boolean flag1);

    @Query("select * from tb_student_entity")
    List<Student> queryAllStudent();
}