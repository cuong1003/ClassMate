package Models;

import java.util.Date;

/**
 * Assignment model for ClassMate system
 * Updated to support both assignments and announcements
 */
public class Assignment {
    private int id;                    // id INT PRIMARY KEY
    private int classroomId;           // classroom_id INT NOT NULL
    private String title;              // title NVARCHAR(200) NOT NULL
    private String description;        // description NVARCHAR(MAX)
    private String type;               // type VARCHAR(20) NOT NULL
    private String fileUrl;            // file_url VARCHAR(500)
    private Date deadline;             // deadline DATETIME
    private int createdBy;             // created_by INT NOT NULL
    private Date createdAt;            // created_at DATETIME

    public Assignment() {
    }

    // Constructor cho Assignment (Bài tập)
    public Assignment(int id, int classroomId, String title, String description, 
                     String fileUrl, Date deadline, int createdBy, Date createdAt) {
        this.id = id;
        this.classroomId = classroomId;
        this.title = title;
        this.description = description;
        this.type = "assignment";
        this.fileUrl = fileUrl;
        this.deadline = deadline;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    
    // Constructor cho Announcement (Thông báo!)
    public Assignment(int id, int classroomId, String title, String description, 
                     int createdBy, Date createdAt) {
        this.id = id;
        this.classroomId = classroomId;
        this.title = title;
        this.description = description;
        this.type = "announcement";
        this.fileUrl = null;
        this.deadline = null;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Constructor để lấy thông tin cho ListAssignment JSP
    public Assignment(String title, String description, 
                     String fileUrl, Date createdAt, Date deadline) {
        this.title = title;
        this.description = description;
        this.type = "assignment";
        this.fileUrl = fileUrl;
        this.createdAt = createdAt;
        this.deadline = deadline;
    }

    // Constructor để lấy thông tin cho ListAnnouncement JSP
    public Assignment(String title, String description, Date createdAt) {
        this.title = title;
        this.description = description;
        this.type = "announcement";
        this.createdAt = createdAt;
    }
    
    // Constructor chính
    public Assignment(int id, int classroomId, String title, String description, 
                     String type, String fileUrl, Date deadline, int createdBy, Date createdAt) {
        this.id = id;
        this.classroomId = classroomId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.fileUrl = fileUrl;
        this.deadline = deadline;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    // Utility methods
    public boolean isAssignment() {
        return "assignment".equals(this.type);
    }
    
    public boolean isAnnouncement() {
        return "announcement".equals(this.type);
    }
    
    public boolean hasDeadline() {
        return this.deadline != null;
    }
    
    public boolean hasFileUrl() {
        return this.fileUrl != null && !this.fileUrl.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Assignment{" + 
               "id=" + id + 
               ", classroomId=" + classroomId + 
               ", title='" + title + '\'' + 
               ", description='" + description + '\'' + 
               ", type='" + type + '\'' + 
               ", fileUrl='" + fileUrl + '\'' + 
               ", deadline=" + deadline + 
               ", createdBy=" + createdBy + 
               ", createdAt=" + createdAt + 
               '}';
    }

    
} 