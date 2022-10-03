# EP1 SO - Escalonador de Processos
![image](https://user-images.githubusercontent.com/37965946/193656169-0d1ecfed-905f-4c40-947b-f27e4d5e6321.png)


A especificação do EP está no arquivo pdf na raiz do projeto, e os arquivos citados no documento estão em `src/main/resources`

## Pré-requisitos

* JDK 11 ou maior (testado com a JDK11 OpenJDK)
* Make (opcional, mas recomendado para rodar os scripts do makefile)

## Getting Started
Abaixo as instruções com o makefile. Se não quiser usar make, abra o makefile e veja os comandos executados lá dentro

### Setup
* O conteúdo dos arquivos mencionados nos itens abaixo devem seguir o padrão especificado em `ep01.pdf`
* Coloque os programas que deseja executar em `src/main/resources/programas`. O programa lê todos os arquivos dessa pasta.
* Coloque o arquivo `quantum.txt` em `src/main/resources`.

### Rodando
Para recompilar tudo e rodar a aplicacao:
```sh
make
```


### Compilando a aplicação
```sh
make build
```


### Rodando a aplicação
```sh
make run
```

### Limpando o classpath
```sh
make clean
```
