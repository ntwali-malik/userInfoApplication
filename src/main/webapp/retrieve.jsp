<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Retrieve Users</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f8ff;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
        color: #333;
    }
    h1 {
        color: #4682b4;
        margin-bottom: 30px;
    }
    .container {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 80%;
        max-width: 600px;
    }
    .user-info {
        border-bottom: 1px solid #ddd;
        padding: 10px 0;
    }
    .user-info:last-child {
        border-bottom: none;
    }
    .user-info p {
        margin: 5px 0;
    }
</style>
</head>
<body>
    <h1>User Information</h1>
    <div class="container">
        <jsp:include page="/retrieve" />
    </div>
</body>
</html>
