package au.edu.federation.itech3107.studentattendance30395696.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_teacher_entity")
public class TeacherEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "m_id",typeAffinity = ColumnInfo.INTEGER)
    public long m_id;

    public String user_name;
    public String user_pass;
    public Boolean flag;

    public TeacherEntity(String user_name, String user_pass, Boolean flag) {
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "m_id=" + m_id +
                ", user_name='" + user_name + '\'' +
                ", user_pass='" + user_pass + '\'' +
                ", flag=" + flag +
                '}';
    }
}
