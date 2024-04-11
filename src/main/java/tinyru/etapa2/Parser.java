package tinyru.etapa2;
import tinyru.etapa1.Lexer;
import tinyru.etapa1.Token;
import java.io.IOException;

public class Parser {
    Lexer lexer;
    Token actual_token;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void analize() {

    }

    private void match(String expected) {
        if (actual_token.getLexeme().equals(expected)) {
            try {
                actual_token = lexer.nextToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Syntax error: expected " + expected + " but found " + actual_token.getLexeme());
            System.exit(1);
        }
    }

    //⟨program⟩ ::= ⟨Lista-Definiciones⟩ ⟨Start⟩ | ⟨Start⟩
    private void program(){
        if (onFirst(actual_token, first(lista_definiciones))){
            lista_definiciones();
            start();
        } else {
            start();
        }
    }


    //⟨Start⟩ ::= start ⟨Bloque-Método⟩
    private void start() {
        match("start");
        bloqueMetodo();
    }

}
