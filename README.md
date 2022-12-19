# API Lista de Compras
  Esse projeto tem como objetivo medir os níveis de domínio sobre as tecnológias do ecossistema spring. Para construção desse API, foram utilizados os seguintes recursos:
 * Java 11
 * Spring Boot
 * Spring Data JPA
 * Flyway
 * Lombok
 * Banco de dados Mysql
 * Maven
 
 ## O que foi implementado
 Os seguintes requisitos foram atendidos:
  * criar API REST utilizando spring boot com os mesmos recursos do app-exemplo 
  * criar validações (Não nulo, Não vazio etc)  
  * criar categorias de lista de compras "concluídas" e "não-concluídas" 
   
 ## Como usar a API
  No caminho \src\main\resources deve ser criado um arquivo denominado como application.properties. Este arquivo é responsável por conter configurações, dentre elas estão as de conexão com banco de dados. Abaixo estão listadas as configurações que devem ser inseridas no arquivo criado em que [host:porta] deve ser substituido pelo host que se encontra o seu banco de dados e a porta, [username] deve ser substituído pelo usário do banco e [password] pela sua senha do banco.
 
 * spring.datasource.url= jdbc:mysql://[host:porta]/listaDeComprasDB?createDatabaseIfNotExist=true&serverTimezone=UTC
 * spring.datasource.username=[username]
 * spring.datasource.password=[password]
 * spring.jpa.show-sql=true
 
  Após isso, rode a aplicação e o banco listaDeComprasDB irá ser criado automáticamente. A API possuí dois endpoints o primeiro deles refere-se as listas de compras que o usário pode criar e manipular. Para verificar seu funcionamento deve-se utilizar um API Client como o Postman, onde os path's das requisições do tipo GET apresentadas abaixo irão retornar todas as listas salvas no banco e uma única lista, respectivamente.
 ```
 http://localhost:8080/listas
 http://localhost:8080/listas/{idListaDeCompras}
 ```
 As requisições do tipo POST devem ser feitas passando no body um JSON no seguinte formato:
 ```
  {
    "nome":"exemplo"
  }
```
Já as requisições do tipo PUT devem ter especificadas em seu path o id da lista desejada para editar, além disso em seu body o JSON deve possuir seguinte formato:
 ```
  {
    "nome":"exemplo"
    "concluida":[false/true]
  }
```
Por fim, as requisções do tipo DELETE devem ter especificadas em seu path o id da lista que deve ser excluída, caso o usuário deseje.
 
  O segundo endpoint criado corresponde ao de criação e manipulação dos produtos de uma lista de compras. Nele, os path's das requisições do tipo GET, possuem o formato ilustrado abaixo, em que estes retornam todos os produtos associados a uma lista e um produto específico. 
  ```
    http://localhost:8080/produtos?idListaDeCompras=2
    http://localhost:8080/produtos/{idProduto}
  ```
 As requisições do tipo POST e PUT devem ser feitas passando no body um JSON no formato apresentado abaixo, em que na requesição do tipo PUT deve ser informado em seu path o id do produto que irá ser alterado.
  ```
    {
    "nome":"exemplo",
    "quantidade":2,
    "listaDeCompras": {
        "idListaDeCompras":2 
      }
    }
  ```
  Por fim, as requisções do tipo DELETE devem ter especificadas em seu path o id do produto que deve ser excluído, caso o usuário deseje.
  
  
  
  
