package Models;
import java.time.LocalDateTime;

/**
 * ClassroomMember model for ClassMate system
 */
public class ClassroomMember {
    // TODO: Add properties and methods
    private int id;
    private int user_id;
    private int classroom_id;
    private LocalDateTime joined_time;

    public ClassroomMember() {
    }

    public ClassroomMember(int id, int user_id, int classroom_id, LocalDateTime joined_time) {
        this.id = id;
        this.user_id = user_id;
        this.classroom_id = classroom_id;
        this.joined_time = joined_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

    public LocalDateTime getJoined_time() {
        return joined_time;
    }

    public void setJoined_time(LocalDateTime joined_time) {
        this.joined_time = joined_time;
    }
    
    
} 