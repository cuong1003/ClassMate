<%-- 
    Document   : Login.jsp
    Created on : Jun 25, 2025, 10:43:48 PM
    Author     : fakey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập - ClassMate</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            
            .login-container {
                background: white;
                padding: 40px;
                border-radius: 10px;
                box-shadow: 0 10px 30px rgba(0,0,0,0.3);
                min-width: 400px;
                text-align: center;
            }
            
            .back-btn {
                position: absolute;
                top: 20px;
                left: 20px;
                background: rgba(255,255,255,0.9);
                border: none;
                padding: 10px 15px;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
                color: #333;
            }
            
            .back-btn:hover {
                background: white;
            }
            
            .login-title {
                font-size: 2rem;
                color: #667eea;
                margin-bottom: 10px;
            }
            
            .user-type {
                background: #f8f9fa;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
                font-weight: bold;
            }
            
            .teacher-type {
                color: #28a745;
            }
            
            .student-type {
                color: #007bff;
            }
            
            .form-group {
                margin-bottom: 20px;
                text-align: left;
            }
            
            .form-group label {
                display: block;
                margin-bottom: 5px;
                color: #333;
                font-weight: bold;
            }
            
            .form-group input {
                width: 100%;
                padding: 12px;
                border: 2px solid #e9ecef;
                border-radius: 5px;
                font-size: 1rem;
                transition: border-color 0.3s;
            }
            
            .form-group input:focus {
                outline: none;
                border-color: #667eea;
            }
            
            .login-btn {
                width: 100%;
                padding: 12px;
                background: #667eea;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 1.1rem;
                cursor: pointer;
                transition: background 0.3s;
            }
            
            .login-btn:hover {
                background: #5a6fd8;
            }
            
            .error-message {
                background: #f8d7da;
                color: #721c24;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
                border: 1px solid #f5c6cb;
            }
            
            .register-link {
                margin-top: 20px;
                color: #666;
            }
            
            .register-link a {
                color: #667eea;
                text-decoration: none;
            }
            
            .register-link a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/" class="back-btn">← Quay lại</a>
        
        <div class="login-container">
            <div class="login-title">🔑 Đăng Nhập</div>
            
            <%
                String userType = request.getParameter("type");
                if (userType != null) {
                    if ("0".equals(userType)) {
            %>
                        <div class="user-type teacher-type">
                            👨‍🏫 Đăng nhập dành cho Giáo viên
                        </div>
            <%
                    } else if ("1".equals(userType)) {
            %>
                        <div class="user-type student-type">
                            👨‍🎓 Đăng nhập dành cho Học sinh
                        </div>
            <%
                    }
                }
            %>
            
            <%
                String errorMessage = (String) request.getAttribute("fail");
                if (errorMessage != null) {
            %>
                    <div class="error-message">
                        ❌ <%= errorMessage %>
                    </div>
            <%
                }
            %>
            
            <form action="login" method="post">
                <div class="form-group">
                    <label for="username">Tên đăng nhập:</label>
                    <input type="text" id="username" name="username" required 
                           value="<%= request.getAttribute("uu") != null ? request.getAttribute("uu") : "" %>">
                </div>
                
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <% if (userType != null) { %>
                    <input type="hidden" name="type" value="<%= userType %>">
                <% } %>
                
                <button type="submit" class="login-btn">Đăng Nhập</button>
            </form>
            
            <div class="register-link">
                Chưa có tài khoản? 
                <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
            </div>
        </div>
    </body>
</html>
