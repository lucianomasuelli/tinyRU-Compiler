package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;

public class VariableNode extends PrimarioNode{
    Token token;
    String metodo = null;
    String struct = null;

    public VariableNode(Token token){
        this.token = token;
    }
    public VariableNode(Token token, String struct, String metodo){
        this.token = token;
        this.struct = struct;
        this.metodo = metodo;
    }

    @Override
    public void print(){
        System.out.print(token.getLexeme());
    }

    @Override
    public String check(SymbolTable st){
        if (struct != null){
            return st.getStruct(struct).getMethod(metodo).getLocalVar(token.getLexeme()).getType();
        } else {
            return st.getStart().getAttribute(token.getLexeme()).getType();
        }
    }
}
