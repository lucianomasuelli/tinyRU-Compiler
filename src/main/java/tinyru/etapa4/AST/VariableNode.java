package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public class VariableNode extends PrimarioNode{
    Token token;

    public VariableNode(Token token){
        this.token = token;
    }
}
