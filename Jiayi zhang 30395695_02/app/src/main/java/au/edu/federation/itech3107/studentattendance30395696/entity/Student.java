package au.edu.federation.itech3107.studentattendance30395696.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_student_entity")
public class Student {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "m_id",typeAffinity = ColumnInfo.INTEGER)
    public long m_id;
    public String id;
    public String name;
    public Boolean flag;//true 已签到  false未签到

    public Student(String id, String name, Boolean flag) {
        this.id = id;
        this.name = name;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Student{" +
                "m_id=" + m_id +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                '}';
    }
}
