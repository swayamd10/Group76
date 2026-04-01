# Group76

For Part 1.2, to check if the default HR and Manager accounts have been initialized:

  Go to MySQLWorkbench and connect to the database instance, if you do not have one, create one with the name "safe_dispatch_db"
  
  Run the application in IntelliJ
  
  Type in the following SQL commands into MySQL Workbench:

    USE safe_dispatch_db;
    SELECT * FROM user;

  In the database, you should see something along the lines of:

    '1','$2a$10$D0C.YVSmTbWkM0g.ZwYmlONYyGMp638jdqxDdL5BFrhAtD6boXmnG','ROLE_HR','hr_user'
    '2','$2a$10$28rHZmomWhd3RLIquv.2LelgQa7BmgUaM8Jyi4Qy2D.MfuGOitew6','ROLE_MANAGER','manager_user'




