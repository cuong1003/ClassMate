
package Controllers.Utils;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jakarta.servlet.ServletContext;

public class FileManager {
    
    // method lấy đường dẫn đến thư mục của project 
    // (tránh dùng đường dẫn tuyệt đói.)
    public String getUploadBasePath(ServletContext context) {
        return context.getRealPath("/uploads/classes/");
    }
    
    public boolean createClassroomFolders(ServletContext context, int classroomId) {
        try {
            String basePath = getUploadBasePath(context);
            System.out.println("DEBUG:Base path = " + basePath);
            
            // *DuongDan*/web/uploads/classes/classroom_123/
            String classroomPath = basePath + "classroom_" + classroomId;
            
            Path classPath = Paths.get(classroomPath); // Đổi string thành Path
            Files.createDirectories(classPath); //Tạo folder lớp học
            
            // Tạo subfolder assignments
            Path assignmentsPath = Paths.get(classroomPath + "/assignments");
            Files.createDirectories(assignmentsPath);
            
            // Tạo subfolder students  
            Path studentsPath = Paths.get(classroomPath + "/students");
            Files.createDirectories(studentsPath);
            
            System.out.println("DEBUG:Đã tạo folder lớp: " + classroomId);
            return true;
            
        } catch (Exception e) {
            System.err.println("DEBUG:Lỗi tạo folder lớp: " + classroomId + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
