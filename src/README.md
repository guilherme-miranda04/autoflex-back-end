Documentação da API, tendo os métodos responsáveis dentro de cada grupo abaixo:

> [!IMPORTANT]
> Eu separei a etapa do ProductWithRawMaterial em outra categoria em vez de produto, para não ficar confuso!
> Os dois possuem os mesmos endpoints, o que muda é a forma que lidamos com isso.


<details>
<summary> Products </summary>

  ## ![POST](https://img.shields.io/badge/POST-0052CC?style=&logoColor=whites) Criar Produto: 
  ```console
  http://localhost:8080/product
  ```
  
  Body Params   | Types            | Required? 
  ------------- | ---------------- | :--------:
  code          | String           | ✅ Yes    
  name          | String           | ⬜️ No
  price         | BigDecimal       | ⬜️ No

O `code` é o único requisito da criação do produto que é único, O ID do produto é gerado via back-end e é utilizado o UUID por questões de segurança.

  ## ![GET](https://img.shields.io/badge/GET-43853D?style=&logoColor=whites) Listar Produtos: 
  ```console
  http://localhost:8080/product
  ```
  
  Callback Params   | Types             
  ----------------- | ----------------  
  id                | UUID (String) 
  code              | String              
  name              | String           
  price             | BigDecimal        
  materials         | Array de Objeto

  ## ![GET](https://img.shields.io/badge/GET-43853D?style=&logoColor=whites) Obter Produto via ID: (Need fix)
  ```console
  http://localhost:8080/product/${productID}
  ```
  
  Callback Params   | Types             
  ----------------- | ----------------  
  code              | String              
  name              | String           
  price             | BigDecimal        
  materials         | Array de Objeto

  ## ![PUT](https://img.shields.io/badge/PUT-FFA116?style=&logoColor=whites) Atualizar Produto
  ```console
  http://localhost:8080/product/${productID}
  ```

  Body Params   | Types            | Required? 
  ------------- | ---------------- | :--------: 
  code          | String           | ⬜️ No
  name          | String           | ⬜️ No
  price         | BigDecimal       | ⬜️ No

  ## ![DELETE](https://img.shields.io/badge/DELETE-A81D33?style=&logoColor=whites) Deletar Produto
  ```console
  http://localhost:8080/product/${productID}
  ```

  
</details>

<details>
<summary> RawMaterials </summary>

  ## ![POST](https://img.shields.io/badge/POST-0052CC?style=&logoColor=whites) Criar Material Bruto: 
  ```console
  http://localhost:8080/raw-material
  ```
  
  Body Params   | Types            | Required? 
  ------------- | ---------------- | :--------:
  code          | String           | ✅ Yes    
  name          | String           | ⬜️ No
  stockQuantity | BigDecimal       | ⬜️ No

  ## ![GET](https://img.shields.io/badge/GET-43853D?style=&logoColor=whites) Listar Material Bruto: 
  ```console
  http://localhost:8080/raw-material
  ```
  
  Callback Params   | Types             
  ----------------- | ----------------  
  id                | UUID (String) 
  code              | String              
  name              | String           
  stockQuantity     | BigDecimal        
  usedInProducts    | Array de Objeto

  ## ![PUT](https://img.shields.io/badge/PUT-FFA116?style=&logoColor=whites) Atualizar Material Bruto:
  ```console
  http://localhost:8080/raw-material/${rawMaterialID}
  ```

  Body Params   | Types            | Required? 
  ------------- | ---------------- | :--------: 
  code          | String           | ⬜️ No
  name          | String           | ⬜️ No
  stockQuantity | BigDecimal       | ⬜️ No

  ## ![DELETE](https://img.shields.io/badge/DELETE-A81D33?style=&logoColor=whites) Deletar Material Bruto:
  ```console
  http://localhost:8080/raw-material/${rawMaterialID}
  ```
</details>

<details>
<summary> ProductWithRawMaterials </summary>
  
  ## ![POST](https://img.shields.io/badge/POST-0052CC?style=&logoColor=whites) Criar Material Bruto necessário para Produto: 
  ```console
  http://localhost:8080/product/{productID}/raw-material
  ```
  
  Body Params   | Types            | Required? 
  ------------- | ---------------- | :--------:
  id            | UUID (String)    | ✅ Yes    
  quantityNeeded| BigDecimal       | ✅ Yes 

  Este `id` do body seria o ID do Material Bruto no qual quer similar ao Produto.

  ## ![PUT](https://img.shields.io/badge/PUT-FFA116?style=&logoColor=whites) Atualizar Material Bruto necessário para Produto:
  ```console
  http://localhost:8080/product/{productID}/raw-material/${rawMaterialID}
  ```

  Body Params   | Types            | Required? 
  ------------- | ---------------- | :--------: 
  quantityNeeded| BigDecimal       | ✅ Yes 

  ## ![DELETE](https://img.shields.io/badge/DELETE-A81D33?style=&logoColor=whites) Deletar Material Bruto necessário para Produto:
  ```console
  http://localhost:8080/product/{productID}/raw-material/${rawMaterialID}
  ```

</details>

<details>
<summary> Production Suggestion </summary>

  ## ![GET](https://img.shields.io/badge/GET-43853D?style=&logoColor=whites) Lista de sugestão de Produção: 
  ```console
  http://localhost:8080/production-suggestion
  ```
  
  Callback Params   | Types             
  ----------------- | ----------------  
  productID         | UUID (String) 
  productName       | String              
  unitPrice         | BigDecimal           
  quantityPossible  | BigDecimal        
  totalValue        | BigDecimal
</details>
