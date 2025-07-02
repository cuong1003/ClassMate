package Models;

/**
 * Classroom model for ClassMate system
 */
public class Classroom {
    private int classId;
    private String className;
    private String classCode;
    private int ownerId;

    public Classroom() {
    }

    public Classroom(int classId, String className, String classCode, int ownerId) {
        this.classId = classId;
        this.className = className;
        this.classCode = classCode;
        this.ownerId = ownerId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    
    
} 