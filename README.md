Developer mode launch:

    copy repository to your pc;
    run mysql server and create an empty database;
    open "application.properties" file and insert your port and database name instead of those present;
    also insert your database credentials in the next two lines;
    execute "mvn spring-boot:run" in command line;
    move to the "frontend" directory
    execute "npm start"
    open localhost:3000/bid?cat1={yourcategory}
  
  
 Theres is no categories and banners yet, but you can add it through postman(json format). Basic Authorization credentials: username=user, password=password
