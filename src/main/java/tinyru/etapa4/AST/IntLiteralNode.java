package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public class IntLiteralNode extends LiteralNode {
    Token token;

    public IntLiteralNode(Token token){
        super(token);
    }
}
