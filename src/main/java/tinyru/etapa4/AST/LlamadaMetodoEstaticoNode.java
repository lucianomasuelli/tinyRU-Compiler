package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.StaticCallError;

public class LlamadaMetodoEstaticoNode extends PrimarioNode{
    private String idStruct;
    private MetodoExprNode llamadaMetodo;
    private VarMetEncNode encadenado;

    public LlamadaMetodoEstaticoNode(Token token, String struct, String metodo,String idStruct, MetodoExprNode llamadaMetodo, VarMetEncNode encadenado) {
        super(token, metodo, struct);
        this.llamadaMetodo = llamadaMetodo;
        this.encadenado = encadenado;
        this.idStruct = idStruct;
    }

    @Override
    public String check(String structType, SymbolTable st) {
        return check(st);
    }

    @Override
    public String check(SymbolTable st) {
        if(!st.getStruct(idStruct).getMethod(llamadaMetodo.getName()).getIsStatic()){
            throw new StaticCallError(idStruct, llamadaMetodo.getName(), token.getLine(), token.getColumn());
        }
        if(encadenado == null)
            return llamadaMetodo.check(idStruct,st);
        else
            return encadenado.check(llamadaMetodo.check(idStruct,st), st);
    }
}
