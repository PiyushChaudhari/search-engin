# search-engin
This is a spring boot application.
We require below software prerequisites.

1) JDK Install (1.8.0_91)
2) STS (Spring Tool Suite)
3) Mongodb installed version 3.0.6
4) Import project on Spring Tool Suite
5) Gradle Version 4.6
6) Go to https://github.com/PiyushChaudhari/search-engin/blob/master/src/main/resources/application.properties and change below details 

  `'spring.data.mongodb.host'` mongodb running host name
  
  `'spring.data.mongodb.port'` mongodb running port
  
  `'klevu.search.data.path'` location of your data file
  
  `'klevu.search.product.top'` how many top production you want as integer.

7) Run mongodb.

8) Run your application.

9) For 3rd point of assignment `Given this product ID, find out products that the other customers are buying along with the one provided.`

You need to hit url `http://localhost:8080/select-data/product/{id}` on your browser and replace `{id}` wiht product ID

10) For 4th point of assignment `Order them based on their frequency (descending) and return the top 5 products (id and name) as recommendations (JSON format)`

You need to hit url `http://localhost:8080/select-data/top-product` on your browser



