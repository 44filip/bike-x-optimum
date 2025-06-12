# Database
*Prerequisites are: \
MySQL Server (latest) \
MySQL Workbench (latest)*

Import the data found in **/db_dump** to your relational database of choice (project
choice is MySQL). 
# Frontend
*Prerequisites are: \
Node.js version 20.x or below (min 8.x) \
Python 3.x (latest)* 

Through a terminal, navigate to **.\\frontend\\** by typing “cd .\frontend” then execute the
following commands **in order**:
```
npm install
npm run serve
```
# Backend
*Prerequisites are: \
JDK version 17.x (latest) \
IntelliJ IDEA (latest)*

Run **BackendApplication.java** found in
“.\backend\src\main\java\rs\ac\singidunum\backend\BackendApplication.java” either
through a Java environment or through the terminal (the execution will vary from
system to system).

# Browser
Navigate to **https://localhost:8080** in the address bar. \
Using **https** may trigger a security warning due to the certificate in place. \
If you're having login issues, navigate to https://localhost:8443/login and accept the security risk. \
For testing purposes, JWT token expiry will automatically log the user out after thirty seconds. \
You may change REFRESH_TOKEN_VALIDITY in “.\backend\src\main\java\rs\ac\singidunum\backend\util\JwtUtil.java”
# Success
![bikes](https://github.com/user-attachments/assets/e7723d77-5b82-4664-aff8-56939c7916c1)

