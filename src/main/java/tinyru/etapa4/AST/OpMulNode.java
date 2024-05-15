package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public class OpMulNode extends ExpMulNode {
    Token token;

    public OpMulNode(Token token){
        this.token = token;
    }
}
