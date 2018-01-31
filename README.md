# Search
Buscador de arquivos por sentenças.

## Requisitos
Java 7+
- Download : https://www.java.com/pt_BR/download/

MongoDB
- Download: https://www.mongodb.com/download-center/enterprise/releases/development
- Instalação:
	- Windows : https://docs.mongodb.org/manual/tutorial/install-mongodb-on-windows/
	- Mac : https://docs.mongodb.org/manual/tutorial/install-mongodb-on-os-x/
	- Linux(ubuntu) : https://docs.mongodb.org/manual/tutorial/install-mongodb-on-ubuntu/
	
	#### OBS.: No Linux e no Mac quando iniciamos o mongo, por padrão, ele tenta acessar o diretório ./data/db, se o mesmo não 		existir, deverá ser criado e ter as permissões de acesso. Vide mais na documentação de instalação do mongo.
	

## Execução
    
1. Realize o download do projeto no git : https://github.com/jrfjuni/search.git
2. Antes de iniciar o mongo, copie a pasta 'dump' para dentro do diretório ./bin do mongoDB
	1. Para iniciar o mongo, dentro do diretório ./bin onde o mesmo foi descompactado execute o seguinte comando: mongod
	2. Com o mongo iniciado, abra um novo terminal e navegue até o diretório ./bin, e execute o seguinte comando: mongorestore dump
3. No diretório onde foi descompactado o projeto acesse a pasta ./target e execute o comando:
    #### java -jar search.jar "palavras para busca"
