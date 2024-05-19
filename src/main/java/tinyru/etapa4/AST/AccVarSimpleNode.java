package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;

public class AccVarSimpleNode extends AccesoVarNode {

    public AccVarSimpleNode(Token token, String struct){
        super(token, struct);
    }

    public AccVarSimpleNode(Token token) {
        super(token);
    }
}
