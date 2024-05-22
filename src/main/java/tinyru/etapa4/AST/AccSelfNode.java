package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;

public class AccSelfNode extends AccesoVarNode {

    public AccSelfNode(Token token, String struct, String metodo){
        super(token, struct);
        this.metodo = metodo;
    }

    public String check(String structId, SymbolTable st){
        String type;
        if(encadenado != null){
            type = encadenado.check(struct, st);
        } else {
            type = struct;
        }
        return type;
    }
}
