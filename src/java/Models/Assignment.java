package Models;

import java.util.Date;

/**
 * Assignment model for ClassMate system
 */
public class Assignment {
    // TODO: Add properties and methods
    private int assignmentId;
    private int classId;
    private String title;
    private String des;
    private int createdBy;
    private Date createdAt;
    private Date deadLine;

    public Assignment() {
    }

    public Assignment(int assignmentId, int classId, String title, String des, int createdBy, Date createdAt, Date deadLine) {
        this.assignmentId = assignmentId;
        this.classId = classId;
        this.title = title;
        this.des = des;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.deadLine = deadLine;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "Assignment{" + "assignmentId=" + assignmentId + ", classId=" + classId + ", title=" + title + ", des=" + des + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", deadLine=" + deadLine + '}';
    }

    
} 