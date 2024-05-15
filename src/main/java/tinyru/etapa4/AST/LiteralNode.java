package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public abstract class LiteralNode extends OperandoNode {
    Token token;

    public LiteralNode(Token token){
        this.token = token;
    }
}
