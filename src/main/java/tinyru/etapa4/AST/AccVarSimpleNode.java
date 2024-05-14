package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public class AccVarSimpleNode extends AccesoVarNode {
    Token token;

    public AccVarSimpleNode(Token token) {
        this.token = token;
    }
}
