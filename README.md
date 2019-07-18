# Aplicação simples em Spring Boot para testes
A aplicação utiliza Spring Boot, Java 8+ e Hibernate no backend, enquanto no frontend possui Vue.js, Riot.js e Materialize.

A aplicação está configurada para criar as tabelas no banco automaticamente, mas é necessário criar uma base PostgreSQL com os dados de conexão presentes no application.properties.

## TODO
## 0. configuração 
- modificar a aplicação para subir na porta HTTP 8080

## 1. server - GET /skills
- modificar GET /skills para retornar ordenado por nome
	
## 2. server - GET /stats
- modificar GET /stats para retornar ordenado por total (decrescente) e avg (decrescente)

## 3. server - GET /skills/like
- corrigir GET /skills/like para filtrar por nome

## 4. CRUD REST para a classe JobBean
- mapear para /jobs
- suporte para GET, POST, DELETE

### 4.1. Criar card no index.html para visualização ou inclusão de Jobs
- seguindo o padrão dos outros cards já existentes

### 4.2. CRUD View para o JobBean 
- com página de listagem
- inserção/edição/remoção
- acessível pela página inicial

### 4.3. Criar constraint para impedir inclusão de mais um job com o mesmo nome

## 5. Corrigir a classe RestAspect 
- para que seus métodos sejam chamados a cada requisição http

### 5.1. Corrigir a tela /stats/list.htm 
- para exibir o tempo médio de execução de cada evento logado

### 5.1.1. Modificar para que o valor médio seja exibido com no máximo duas casas decimais

## 6. REST - modificar serviço /skills
- implementar método GET com o mapeamento /skills/name/{name} ou /skills?name={name} para trazer um item com um nome específico

### 6.1. REST - modificar serviço /skills
- implementar método GET com o mapeamento /skills/exists/name/{name} ou /skills/exists?name={name} para trazer um booleano indicando se um item com um nome específico existe na base de dados
