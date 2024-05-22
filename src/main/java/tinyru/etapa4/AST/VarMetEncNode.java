package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public abstract class VarMetEncNode extends PrimarioNode{

    public VarMetEncNode(Token token, String metodo, String struct) {
        super(token, metodo, struct);
    }

    public VarMetEncNode(Token token, String metodo) {
        super(token, metodo);
    }
}
