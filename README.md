# desafio-votacao

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução para dispositivos móveis para gerenciar e participar dessas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

Cadastrar uma nova pauta
Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
Contabilizar os votos e dar o resultado da votação na pauta
Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A solução deve ser construída em java, usando Spring-boot, mas os frameworks e bibliotecas são de livre escolha (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

# Resolução

### Para a execução é necessario:

- Maven (utilizei a versão 3.92)
- Java (utilizei a versão 19)
- Spring boot (utilizei a versão 3.1.2)
- Mysql (Os scripts estão na pasta resource)

Com isso em mãos, será necessario somente dar um git clone no objeto, abrir o terminal shell na pasta raiz e executar os camandos:
```bash
cd target
java -jar .\com.github.KelvimSaidel-1.0-SNAPSHOT.jar
```
Com isso a aplicação estará rodando e será possível acessa lá pelo postman.

### Sobre a execução:

Existem 4 classes, sendo elas, usuário, pauta, sessao e registro_votos_Usuarios (que por enquanto só possui o método put).

Para consultar um usuário, pauta ou sessão especifica, usa-se:

http://localhost:8080/sessao-votacao/Usuario/1 (id usuario)

http://localhost:8080/sessao-votacao/Pauta/1 (id pauta)

http://localhost:8080/sessao-votacao/Sessao/1 (id sessao)

Para consultar todos usuarios, pautas ou sessoes, usa-se:

http://localhost:8080/sessao-votacao/Usuarios

http://localhost:8080/sessao-votacao/Pautas

http://localhost:8080/sessao-votacao/Sessoes

Para deletar algum usuario, pauta, ou sessao especifica, usa-se:

http://localhost:8080/sessao-votacao/Usuario/1 (id usuario)

http://localhost:8080/sessao-votacao/Pauta/1 (id pauta)

http://localhost:8080/sessao-votacao/Sessao/1 (id sessao)

Para inserir um usuário, pauta e sessão usa-se:
```JSON
{   
   
    "nome_usuario": "Kelvim",
    "cpf": 123456715
}
```

```JSON
{
     "nome_pauta": "Pauta5",
     "id_usuario": 1
} 
```


Sessao com tempo de vigencia default
```JSON
{
     "id_pauta": 1,
     "nome_pauta": "Pauta1"
}
```

Sessao com tempo de vigencia dinamico
```JSON
{
     "id_pauta": 1,
     "nome_pauta": "Pauta1"
     "tempoVigenciaEmMinutos": "5"
}
```
para atualizar utiliza-se:

```JSON
{
     "id_usuario": 3,
     "nome_usuario": "Kelvim Silva",
     "cpf": 123456720
}
```

```JSON
{
    "id_pauta": 1,
    "nome_pauta": "Pauta5",
    "id_usuario": 1
}
```

```JSON
{
     "id_pauta": 1,
     "nome_pauta": "Pauta1"
}
```

E por fim para votar utiliza-se:

```
http://localhost:8080/sessao-votacao/Sessao/Votar/Usuario/{id_usuario}/Pauta/{id_pauta}/voto/{SIM ou NAO}

```
