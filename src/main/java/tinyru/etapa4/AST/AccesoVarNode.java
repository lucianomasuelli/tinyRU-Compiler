package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.AttrNotFoundError;

import java.util.List;
import java.util.Stack;

public abstract class AccesoVarNode extends EncadenadoNode {
    private Token token;
    private EncadenadoNode encadenado;
    private String struct;

    public AccesoVarNode(Token token, String struct){
        this.token = token;
        this.struct = struct;
    }

    public void setStruct(String struct){
        this.struct = struct;
    }

    public String getStruct(){
        return struct;
    }

    public void print() {
        System.out.print(token.getLexeme());
    }

    public void setEncadenado(EncadenadoNode enc){
        encadenado = enc;
    }


    public Token getToken() {
        return token;
    }

    public String getType() {
        String type = null;
        if(encadenado != null){
            type = encadenado.getType();
        }else {
            type = token.getType().toString();
        }
        return type;
    }

    public String getID() {
        return token.getLexeme();
    }


    @Override
    public String check(String structType, SymbolTable st) {
        String type = null;
        if(structType == null) {
            if (encadenado != null) {
                type = encadenado.check(st.getStructTable().get(token.getLexeme()).getName(), st);
            } else {
                type = st.getStructTable().get(token.getLexeme()).getName();
            }
        }else {
            if(!st.getStructTable().get(structType).fetchAttribute(token.getLexeme())) {
                throw new AttrNotFoundError(token.getLexeme(), structType, token.getLine(), token.getColumn());
            }
            if(encadenado != null) {

                type = encadenado.check(st.getStructTable().get(structType).getAttribute(token.getLexeme()).getType(), st);
            }else {
                type = st.getStructTable().get(structType).getAttribute(token.getLexeme()).getType();
            }
        }
        return type;
    }
}
