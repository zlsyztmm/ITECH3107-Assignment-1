package au.edu.federation.itech3107.studentattendance30395696.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_course_entity")
public class Course implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "m_id",typeAffinity = ColumnInfo.INTEGER)
    public long m_id;
    public long classID;
    public String num;
    public String name;
    public String startTime;
    public String endTime;

    public Course(long classID, String num, String name, String startTime, String endTime) {
        this.classID = classID;
        this.num = num;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Course{" +
                "m_id=" + m_id +
                ", classID=" + classID +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
