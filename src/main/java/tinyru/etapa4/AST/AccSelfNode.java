package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;

public class AccSelfNode extends AccesoVarNode {

    public AccSelfNode(Token token, String struct){
        super(token, struct);
    }

}