# assignment_kishor
# Project Title: Favourite Food Recipe Management
* Motivation: For demonstrating use of Micorservice REST APIs
* Build Status: RELEASE-01
* Code Style: Standard
* Tech/Frameworks: Coding Language used-Java, Framework-Spring Boot
* Features: Allows user to Add, Update, Remove, Fetch and Filter their Favourite Recipes
* Softwares needed: Java Runtime Environment version 8 or higher, Postman or any other API Client, MySql database server 8 or higher

* Instructions: 
  1. Create and replace schema name in application.yml before running the service
  2. Replace or ensure port number 7004 is free to run recipe-service on
  3. import the Postman collection to test the APIs 
  4. for more information about APIs use swagger link below
  


* Swagger link: http://localhost:7004/recipe-service/swagger-ui/index.html#/recipe-controller

* APIs developed/available till RELEASE-01: 
  1. http://localhost:7004/recipe-service/save/Recipe
  2. http://localhost:7004/recipe-service/get/Recipe/ByRecipeId?recipeId=5
  3. http://localhost:7004/recipe-service/changeStatus/ByRecipeId?recipeId=5&status=true
  4. http://localhost:7004/recipe-service/listing/Recipe
   * @apiNote This API takes ListingDTO as argument where details 
     of fields are as follows:
   * start: zero indexed Integer value which denotes the page number 
   * length: non-zero number denoting total number of items per page 
   * defaultSort: default sort column and direction (if not provided records
     will be sorted in ascending order by primary key value) 
   * status: boolean value denoting active(true) or inactive(false) records 
   * search: list of column name and respective values to be searched on along with searchType 
   * ascendingSortFields: ascending sorting of records based on column names 
   * descendingSortFields : descending sorting of records based on column names
	 
 

* Postman Collection link: https://www.getpostman.com/collections/c8757d7132bac3cfd862 
