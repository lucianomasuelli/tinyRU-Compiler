package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public class OpAdNode extends ExpAdNode{
    Token token;

    public OpAdNode(Token token){
        this.token = token;
    }
}
