# 🎨 JavaFX Paint 

Um editor gráfico vetorial desenvolvido em **Java** utilizando a biblioteca **JavaFX** e gerenciamento de dependências pelo **Maven**. Este projeto aplica conceitos sólidos de Programação Orientada a Objetos (POO) e Padrões de Projeto para criar uma aplicação robusta e extensível.

## ✨ Funcionalidades

- **Desenho de Formas:** Linhas, Retângulos, Círculos e Desenho à Mão Livre.
- **Seleção Dinâmica:** Clique em qualquer forma desenhada para selecioná-la. Formas selecionadas ganham uma *bounding box* destacada com quadrados nas quinas.
- **Movimentação:** Arraste livremente as figuras selecionadas pelo *canvas*.
- **Recolorir:** Altere a cor de preenchimento/contorno de qualquer figura selecionada usando o `ColorPicker`.
- **Gerenciamento de Camadas (Teclado):**
    - `<SETA PARA CIMA>`: Traz a figura selecionada para a frente.
    - `<SETA PARA BAIXO>`: Envia a figura selecionada para trás.
- **Remoção:** Pressione `<DEL>` ou `<BACKSPACE>` para apagar a figura selecionada.

## 🛠️ Arquitetura e Padrões de Projeto

O projeto foi construído focando em boas práticas de engenharia de software:

- **State Pattern:** As ferramentas de desenho (`LineTool`, `CircleTool`, `SelectionTool`, etc.) são implementadas usando o padrão State através da classe abstrata `Tool`. O `PaintController` delega os eventos de mouse para a ferramenta ativa, eliminando a necessidade de blocos condicionais (`if/switch`) complexos.
- **MVC (Model-View-Controller):** Separação clara entre a lógica de dados (`PaintModel`), a interface gráfica (`visaoPaint.fxml`) e o controle de ações (`PaintController`).
- **Herança e Polimorfismo:** A classe abstrata `Figure` unifica o comportamento comum (como desenhar a seleção e mover), enquanto o método `contains(Point2D)` utiliza polimorfismo com cálculos matemáticos específicos (fórmulas de elipse, cálculos de área de linha) para detecção de clique em cada forma geométrica.

## 🚀 Como executar o projeto

### Pré-requisitos
- [Java JDK 11+](https://adoptium.net/)
- [Apache Maven](https://maven.apache.org/)

### Passos
1. Clone este repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git](https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git)

2. Acesse a pasta do projeto:

Bash
cd NOME_DA_PASTA_DO_PROJETO

3. Compile e execute a aplicação usando o plugin do JavaFX no Maven:

Bash
mvn clean javafx:run

💻 Tecnologias Utilizadas

Java 11

JavaFX 21 (Controls e FXML)

Maven (Build e Dependências)

Scene Builder (Design da Interface FXML)

### Projeto desenvolvido como atividade final na disciplina de Programação Orientada a Objetos, ministrada pelo Professor Dr. Giovanny Lucero 


