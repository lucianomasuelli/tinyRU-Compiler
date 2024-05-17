package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public class LiteralNode extends OperandoNode {
    Token token;

    public LiteralNode(Token token){
        this.token = token;
    }

    public void print() {
        System.out.print(token.getLexeme());
    }
}
