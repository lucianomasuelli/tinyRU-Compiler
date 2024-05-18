package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;

public class LiteralNode extends OperandoNode {
    Token token;

    public LiteralNode(Token token){
        this.token = token;
    }

    public void print() {
        System.out.print(token.getLexeme());
    }

    public TokenType getType() {
        return token.getType();
    }
}
