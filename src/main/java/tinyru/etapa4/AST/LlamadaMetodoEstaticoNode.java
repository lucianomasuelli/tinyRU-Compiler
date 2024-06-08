package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.MethodNotFoundError;
import tinyru.etapa4.Exceptions.StaticCallError;
import tinyru.etapa4.Exceptions.StructNotFoundError;
import tinyru.etapa5.CodeGenerator;

public class LlamadaMetodoEstaticoNode extends PrimarioNode{
    private String idStruct;
    private LlamadaMetodo llamadaMetodo;
    private VarMetEncNode encadenado;

    public LlamadaMetodoEstaticoNode(Token token, String struct, String metodo, String idStruct, LlamadaMetodo llamadaMetodo, VarMetEncNode encadenado) {
        super(token, metodo, struct);
        this.llamadaMetodo = llamadaMetodo;
        this.encadenado = encadenado;
        this.idStruct = idStruct;
    }

    public LlamadaMetodoEstaticoNode(Token token, String idStruct, LlamadaMetodo llamadaMetodo, VarMetEncNode encadenado) {
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

        if(!st.fetchStruct(idStruct)){
            throw new StructNotFoundError(idStruct, token.getLine(), token.getColumn());
        }
        if(!st.getStruct(idStruct).fetchMethod(llamadaMetodo.getName())){
            throw new MethodNotFoundError(idStruct, llamadaMetodo.getName(), token.getLine(), token.getColumn());
        }
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

    @Override
    public void generateCode(CodeGenerator cg) {
        if(encadenado == null)
            llamadaMetodo.generateCode(cg);
        else {
            llamadaMetodo.generateCode(cg);
            encadenado.generateCode(cg);
        }
    }

    public VarMetEncNode getEncadenado() {
        return encadenado;
    }

    public LlamadaMetodo getMetodo() {
        return llamadaMetodo;
    }

}
