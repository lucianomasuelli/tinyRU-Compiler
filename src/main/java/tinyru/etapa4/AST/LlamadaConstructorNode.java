package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;

public abstract class LlamadaConstructorNode extends PrimarioNode{

    public LlamadaConstructorNode(Token token, String metodo, String struct) {
        super(token, metodo, struct);
    }

    public LlamadaConstructorNode(Token token) {
        super(token);
    }
}
