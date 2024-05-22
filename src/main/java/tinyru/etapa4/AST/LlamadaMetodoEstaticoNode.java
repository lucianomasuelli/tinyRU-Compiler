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

    public LlamadaMetodoEstaticoNode(Token token, String idStruct, MetodoExprNode llamadaMetodo, VarMetEncNode encadenado) {
        super(token);
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

    @Override
    public String jsonify(){
        String json;
        if(encadenado == null){
            json = "{\"LlamadaMetodoEstatico\": {\"nombre\": " + token.getLexeme() + ", \"metodo\": " + llamadaMetodo.jsonify() + ", \"struct\": \"" + idStruct + "\"}}";
        }else{
            json = "{\"LlamadaMetodoEstatico\": {\"nombre\": " + token.getLexeme() + ", \"metodo\": " + llamadaMetodo.jsonify() + ", \"struct\": \"" + idStruct + "\", \"encadenado\": " + encadenado.jsonify() + "}}";
        }
        return json;
    }
}
