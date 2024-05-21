package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;

import java.util.List;

public class MetodoExprNode extends VarMetEncNode{
    private VarMetEncNode encadenado;
    private List<ExpresionNode> argActuales;

    public MetodoExprNode(Token token, String metodo, String struct) {
        super(token, metodo, struct);
    }

    public void setEncadenado(VarMetEncNode encadenado) {
        this.encadenado = encadenado;
    }

    public void setArgActuales(List<ExpresionNode> argActuales) {
        this.argActuales = argActuales;
    }


    public void addArgActual(ExpresionNode argActual) {
        this.argActuales.add(argActual);
    }

    @Override
    public String check(String structType, SymbolTable st) {
        String metodo = token.getLexeme();
        String type = null;
        if (encadenado != null) {
            type = encadenado.check(structType, st); //TODO
        }else{
            // el tipo es el valor de retorno del método
            type = st.getStructTable().get(structType).getMethod(metodo).getReturnType();
        }
        return type;
    }

    @Override
    public String check(SymbolTable st) {
        return "";
    }
}
