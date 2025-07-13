/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers.Utils;

/**
 *
 * @author fakey
 */
public class FileManager {
    // quản lí các phuowng thức về file/folder.
    private static final String UPLOAD_ROOT = "web/uploads/classes/";
    
    public static boolean createClassroomFolders(int classroomId) {
        // Logic tạo folder classroom_[id]/assignments/ và students/
        return true;
    }
    
    public static boolean createStudentFolder(int classroomId, int studentId) {
        // Logic tạo folder cho student khi join lớp
        return true;
    }
    
    public static String getClassroomPath(int classroomId) {
        return UPLOAD_ROOT + "classroom_" + classroomId + "/";
    }
    
    public static String getAssignmentPath(int classroomId) {
        return getClassroomPath(classroomId) + "assignments/";
    }
    
    public static String getStudentPath(int classroomId, int studentId) {
        return getClassroomPath(classroomId) + "students/student_" + studentId + "/";
    }
}
