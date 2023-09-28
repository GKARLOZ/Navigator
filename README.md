# 	:rocket: Navigator 
The app provides users with the ability to select a location and destination, utilizing the Dijkstra Algorithm to determine and display the shortest path. This application is built upon OOP, SOLID principles and design patterns. These include the implementation of essential patterns such as abstract factories, singletons, and builders. These design choices have been made to facilitate ease of maintenance.<br><br>

## Project Overview
This project is built and managed using Maven, a popular build automation and dependency management tool for Java projects.

### Database Configuration
Import the navigatorSchema.sql file to your database. <br> 
For example: Import the file to your MySQL workbench <br>
(https://dev.mysql.com/doc/workbench/en/wb-admin-export-import-management.html)

Inside the resources folder add your own db.properties file with the following: 

    db.url=jdbc:mysql://localhost:3306/databaseName
    db.user=YourUserName
    db.password=YourPassword
    
#### :warning:	 Note: :warning:	
The program will display an error if db.properties file is not setup properly. For example: <br>
Error executing query: SELECT * FROM Locationserror cause: nullException in thread "main" java.lang.NullPointerException: Cannot invoke "java.sql.ResultSet.close()" because "resultSet" is null.

## Fact about the Navigator:
* Relational Database: It efficiently retrieves data from a relational database, employing the MyBatis framework or JDBC API to ensure seamless connectivity.
* Parsers: The app transforms retrieved data into JSON and XML format, streamlining information exchange, with the assistance of the Jackson library.
* Collaborative Development: This application was a collaborative group project, where version control was managed through Github, ensuring organized and effective teamwork.
* Project Management: The project was organized and tracked using Jira, facilitating efficient task management and issue tracking.



## Screenshots
![Screenshot 2023-09-28 113230](https://github.com/GKARLOZ/Navigator/assets/20764455/d4817bce-4162-45b6-b9da-b8ef3745d117)
![Screenshot 2023-09-28 125151](https://github.com/GKARLOZ/Navigator/assets/20764455/16e915c2-0a2c-4701-aace-de47fe409445)
