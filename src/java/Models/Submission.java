package Models;


import java.util.Date;

/**
 * Submission model for ClassMate system
 */
public class Submission {
    // TODO: Add properties and methods
    
    private int id;
    private int assignment_id;
    private int user_id;
    private String submit_text;
    private String fileUrl;
    private Date submmitted_at;
    private int grade;
    private String feedback;
    private String user_name;

    public Submission() {
    }

    public Submission(int id, int assignment_id, int user_id, String submit_text, String fileUrl, Date summitted_at, int grade, String feedback) {
        this.id = id;
        this.assignment_id = assignment_id;
        this.user_id = user_id;
        this.submit_text = submit_text;
        this.fileUrl = fileUrl;
        this.submmitted_at = submmitted_at;
        this.grade = grade;
        this.feedback = feedback;
    }
    
    public Submission(int id, int assignment_id, int user_id,String user_name, String submit_text, String fileUrl, Date summitted_at, int grade, String feedback) {
        this.id = id;
        this.assignment_id = assignment_id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.submit_text = submit_text;
        this.fileUrl = fileUrl;
        this.submmitted_at = submmitted_at;
        this.grade = grade;
        this.feedback = feedback;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSubmit_text() {
        return submit_text;
    }

    public void setSubmit_text(String submit_text) {
        this.submit_text = submit_text;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getSubmmitted_at() {
        return submmitted_at;
    }

    public void setSubmmitted_at(Date submmitted_at) {
        this.submmitted_at = submmitted_at;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Submission{" + "id=" + id + ", assignment_id=" + assignment_id + ", user_id=" + user_id + ", submit_text=" + submit_text + ", fileUrl=" + fileUrl + ", submmitted_at=" + submmitted_at + ", grade=" + grade + ", feedback=" + feedback + ", user_name=" + user_name + '}';
    }
    
    
    
} 