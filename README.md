Projeto Jogo em Java – Sistema Completo com Batalha e Progressão

Este projeto foi desenvolvido como parte da avaliação prática da disciplina de Programação Orientada a Objetos (POO).
O jogo demonstra a aplicação real dos conceitos estudados ao longo do semestre, incluindo herança, polimorfismo, classes abstratas, exceções personalizadas, organização por pacotes e muito mais.

👥 Integrantes do Grupo

Ruan Miguel Correia dos Santos – 01839500

Gabriel Garcia da Fonseca – 01775171

Lara Ellen Nogueira De Arruda – 01808725

Pedro Guilherme Santana Ferreira – 01821748

Ronildo Lourenço da Silva – 01862063

🎮 Descrição do Jogo

Este projeto implementa um RPG de terminal, onde o jogador cria um personagem, explora áreas e enfrenta vilões aleatórios em batalhas por turnos.
O sistema inclui:

Criação de personagens

Vilões gerados aleatoriamente

Sistema de batalha por turnos

Sistema de vitória e derrota

Registro de progresso

Menu interativo

Persistência em arquivo JSON

A estrutura foi desenvolvida utilizando os principais pilares da Programação Orientada a Objetos.

🧱 Arquitetura do Projeto

O projeto está organizado em pacotes profissionais, mantendo separação clara de responsabilidades:

src/
 └── jogo/
      ├── Main.java
      ├── modelo/
      │     ├── Personagem.java
      │     ├── Heroi.java
      │     ├── Vilao.java
      │     └── Status.java
      ├── sistema/
      │     ├── GerenciadorDeJogo.java
      │     └── Batalha.java
      ├── database/
      │     ├── json/
      │     │     └── SaveManager.java
      └── ui/
            └── Menu.java

✔️ Benefícios desta arquitetura

Fácil manutenção

Pacotes com responsabilidades específicas

Separação clara entre lógica, modelos e interface de usuário

Permite expansão futura (loja, inventário, magias, multiplayer, etc.)

🧠 Conceitos de POO aplicados
✔️ Classes e Objetos

Personagem, Heroi, Vilao, Batalha, Menu…

✔️ Encapsulamento

Atributos privados + getters/setters

✔️ Construtores e Sobrecarga

Heroi e Vilao utilizam construtores bem definidos

✔️ Herança

Heroi e Vilao herdam de Personagem

✔️ Polimorfismo

Métodos sobrescritos como:

realizarAtaque()

defender()

toString()

✔️ Classes Abstratas

Personagem é abstrata e define a interface mínima de um personagem no jogo.

✔️ Tratamento de Exceções

try/catch para erros no jogo

exceções personalizadas no SaveManager

✔️ Manipulação de Arquivos

O progresso é salvo em JSON no diretório:

/database/json/

⚔️ Como funciona o Sistema de Batalha:

Batalha por turnos

Ataques com dano aleatório baseado no personagem

Sistema de defesa

Vítima perde vida gradualmente

Logs completos no terminal

Vilões gerados aleatoriamente com atributos variáveis

A vitória concede:

XP (caso exista no seu código)

Registro no JSON

Retorno ao menu inicial

A derrota finaliza o jogo.

💾 Sistema de Salvamento (JSON)

O arquivo JSON armazena:

Nome do herói

Vida

Ataque

Derrotas

Vitórias

Histórico

Ele é gerenciado automaticamente pelo SaveManager.

Conclusão:

Este projeto demonstra de forma clara e completa o domínio dos conceitos fundamentais e avançados de POO.
A arquitetura modular, o uso de herança e polimorfismo, e o sistema de batalha tornam o código robusto e extensível.
