package com.example.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GlobalErrorHandler implements ErrorController {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletRequest request, String customStatus, String customMessage) throws IOException {
        String status = customStatus != null ? customStatus : 
        (request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) != null ?
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString() : "Unknown Status");
                
        String error = customMessage != null ? customMessage : 
        (request.getAttribute(RequestDispatcher.ERROR_MESSAGE) != null ?
                request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString() : "Unknown Error");

        /* // Check for 404 error and redirect to the application's root page
        if ("404".equals(status)) {
            String rootUrl = contextPath.isEmpty() ? "/" : contextPath;
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", rootUrl)
                    .build();
        } */

        ClassPathResource resource = new ClassPathResource("static/error.html");
        String content;
        try {
            content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            // Replace placeholders in the HTML with actual error details
            content = content.replace("{{STATUS}}", status);
            content = content.replace("{{ERROR}}", error);

            if ("404".equals(status)) {
                String rootUrl = contextPath.isEmpty() ? "/" : contextPath;
                String countdownHtml = createCountdownHtml(rootUrl);
                content = content.replace("</div>\n    </div>", countdownHtml + "\n    </div>\n    </div>");
            }
        } catch (IOException e) {
            content = "<html><body><h1>An IO exception occurred while loading the error page.</h1></body></html>";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(content);
    }

    private String createCountdownHtml(String rootUrl) {
        return "<div id='countdown' style='margin-top: 20px;'>Redirecting in 30 seconds...</div>" +
               "<div style='margin-top: 10px;'>" +
               "<a href='" + rootUrl + "' style='color: #3498db; text-decoration: none; margin-right: 20px;'>Click here if not redirected</a>" +
               "<button id='cancelRedirect' style='background-color: #e74c3c; color: white; border: none; padding: 5px 10px; cursor: pointer;'>Cancel Redirect</button>" +
               "</div>" +
               "<script>" +
               "var seconds = 30;" +
               "var countdownInterval;" +
               "function countdown() {" +
               "    document.getElementById('countdown').innerHTML = 'Redirecting in ' + seconds + ' seconds...';" +
               "    if (seconds > 0) {" +
               "        seconds--;" +
               "        countdownInterval = setTimeout(countdown, 1000);" +
               "    } else {" +
               "        window.location.href = '" + rootUrl + "';" +
               "    }" +
               "}" +
               "countdown();" +
               "document.getElementById('cancelRedirect').addEventListener('click', function() {" +
               "    clearTimeout(countdownInterval);" +
               "    document.getElementById('countdown').innerHTML = 'Redirect cancelled.';" +
               "    this.style.display = 'none';" +
               "});" +
               "</script>";
    }
}

/* @Controller
public class GlobalErrorHandler implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity<Void> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object error = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        String redirectUrl = "/error.html?status=" + 
                              (status != null ? status : "Unknown status") +
                              "&error=" + 
                              (error != null ? URLEncoder.encode(error.toString(), StandardCharsets.UTF_8) : "Unknown error");
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
    }
} */