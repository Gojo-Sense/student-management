package com.example.student_management.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Hidden  // Hide from Swagger documentation
public class HomeController {

    /**
     * Root endpoint - redirects to Swagger UI
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/swagger-ui.html";
    }

    /**
     * Welcome endpoint - shows API information
     */
    @GetMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return """
                <html>
                <head>
                    <title>Student Management API</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            max-width: 800px;
                            margin: 50px auto;
                            padding: 20px;
                            background-color: #f5f5f5;
                        }
                        .container {
                            background: white;
                            padding: 30px;
                            border-radius: 10px;
                            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                        }
                        h1 {
                            color: #2c3e50;
                        }
                        .link-box {
                            background: #ecf0f1;
                            padding: 15px;
                            margin: 10px 0;
                            border-radius: 5px;
                        }
                        a {
                            color: #3498db;
                            text-decoration: none;
                            font-weight: bold;
                        }
                        a:hover {
                            text-decoration: underline;
                        }
                        .endpoint {
                            background: #e8f4f8;
                            padding: 10px;
                            margin: 5px 0;
                            border-left: 4px solid #3498db;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>🎓 Student Management API</h1>
                        <p><strong>Version:</strong> 1.0.0</p>
                        <p><strong>Framework:</strong> Spring Boot 3.5.7</p>
                        
                        <h2>📚 Available Resources</h2>
                        
                        <div class="link-box">
                            <h3>API Documentation</h3>
                            <p>📖 <a href="/swagger-ui.html">Swagger UI</a> - Interactive API Documentation</p>
                            <p>📄 <a href="/v3/api-docs">OpenAPI Spec (JSON)</a> - Raw API Specification</p>
                        </div>
                        
                        <div class="link-box">
                            <h3>Database Console</h3>
                            <p>🗄️ <a href="/h2-console">H2 Database Console</a> - Database Management</p>
                        </div>
                        
                        <h2>🔗 API Endpoints</h2>
                        <div class="endpoint">
                            <strong>POST</strong> /students/save - Create/Update Student
                        </div>
                        <div class="endpoint">
                            <strong>GET</strong> /students/all - Get All Students
                        </div>
                        <div class="endpoint">
                            <strong>GET</strong> /students/count - Count Students
                        </div>
                        <div class="endpoint">
                            <strong>GET</strong> /students/byYear - Students by Year
                        </div>
                        <div class="endpoint">
                            <strong>DELETE</strong> /students/delete/{id} - Delete Student
                        </div>
                        
                        <hr style="margin: 30px 0;">
                        <p style="text-align: center; color: #7f8c8d;">
                            Made with Spring Boot | <a href="/swagger-ui.html">Try API Now →</a>
                        </p>
                    </div>
                </body>
                </html>
                """;
    }
}
