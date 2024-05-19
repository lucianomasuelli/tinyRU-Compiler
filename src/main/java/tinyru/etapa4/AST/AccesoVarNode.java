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
    private String metodo;

    public AccesoVarNode(Token token, String struct){
        this.token = token;
        this.struct = struct;
    }

    // Cuando está en el start struct es nulo
    public AccesoVarNode(Token token){
        this.token = token;
    }

    public void setStruct(String struct){
        this.struct = struct;
    }

    public String getStruct(){
        return struct;
    }

    public void setMetodo(String metodo){ this.metodo = metodo; }

    public String getMetodo(){ return metodo; }

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
            //TODO: El tipo se extrae de la tabla de simbolos
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
                type = encadenado.check(st.getStructTable().get(this.struct).getAttribute(token.getLexeme()).getType(), st);
            } else {
                if (this.metodo == null ){ // Está en el start
                    if (!st.getStart().fetchAttribute(token.getLexeme())) {
                        throw new AttrNotFoundError(token.getLexeme(), null, token.getLine(), token.getColumn());
                    } else {
                        type = st.getStart().getAttribute(token.getLexeme()).getType();
                    }
                } else {
                    //Reviso primero en las variables del metodo, luego los parametros y finalmente en el struct
                    if (st.getStruct(this.struct).getMethod(this.metodo).fetchLocalVar(token.getLexeme())) {
                        type = st.getStruct(this.struct).getMethod(this.metodo).getLocalVar(token.getLexeme()).getType();
                    } else {
                        if (st.getStruct(this.struct).getMethod(this.metodo).fetchParameter(token.getLexeme())) {
                            type = st.getStruct(this.struct).getMethod(this.metodo).getParameter(token.getLexeme()).getType();
                        } else {
                            if (!st.getStructTable().get(this.struct).fetchAttribute(token.getLexeme())) {
                                throw new AttrNotFoundError(token.getLexeme(), this.struct, token.getLine(), token.getColumn());
                            }
                            type = st.getStructTable().get(this.struct).getAttribute(token.getLexeme()).getType();
                        }
                    }
                }
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
