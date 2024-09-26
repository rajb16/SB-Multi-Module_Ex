<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Error Page</title>
    <link rel="icon" type="image/x-icon" href="<%= request.getContextPath() %>/favicon.ico" />

    <style>
      body,
      html {
        height: 100%;
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
      }

      .container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        padding: 20px;
        box-sizing: border-box;
      }

      .error-content {
        background-color: white;
        padding: 40px;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        text-align: center;
        max-width: 500px;
        width: 100%;
      }

      h1 {
        color: #e74c3c;
        margin-top: 0;
      }

      p {
        color: #333;
        line-height: 1.6;
      }

      .error-details {
        margin-top: 20px;
        font-size: 0.9em;
        color: #666;
      }
    </style>
  </head>

  <body>
    <div class="container">
      <div class="error-content">
        <h1>Oops! Something went wrong</h1>
        <p>We're sorry, but we couldn't find the page you are looking for.</p>
        <div class="error-details">
          <p>Error code: <%= request.getParameter("status") != null ? request.getParameter("status") : "Unknown status" %></p>
          <p>Error message: <%= request.getParameter("error") != null ? request.getParameter("error") : "Unknown error" %></p>
        </div>
      </div>
    </div>
  </body>
</html>
