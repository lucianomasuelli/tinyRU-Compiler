package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.AttrNotFoundError;

public class VariableExprNode extends PrimarioNode{
    private String metodo = null;
    private String struct = null;
    private PrimarioNode encadenado;
    private ExpresionNode arrayAccess = null;

    public VariableExprNode(Token token){
        super(token);
    }
    public VariableExprNode(Token token, String struct, String metodo){
        super(token);
        this.struct = struct;
        this.metodo = metodo;
    }

    public void setEncadenado(PrimarioNode encadenado){
        this.encadenado = encadenado;
    }

    public void setArrayAccess(ExpresionNode arrayAccess){
        this.arrayAccess = arrayAccess;
    }

    @Override
    public void print(){
        System.out.print(token.getLexeme());
    }


    public String check(String structType, SymbolTable st){
        String type = null;
        if(structType == null) {
            if(encadenado != null){
                if(st.getStructTable().get(this.struct).fetchAttribute(token.getLexeme())) {
                    // envía como parámetro el tipo de la variable a la que se le está haciendo el acceso.
                    type = encadenado.check(st.getStructTable().get(this.struct).getAttribute(token.getLexeme()).getType(), st);
                }
                else {
                    throw new AttrNotFoundError(token.getLexeme(), this.struct, token.getLine(), token.getColumn());
                }
            }
            else {

                if (st.getStructTable().get(this.struct).fetchAttribute(token.getLexeme())) {
                    type = st.getStructTable().get(this.struct).getAttribute(token.getLexeme()).getType();
                }
                else {
                    throw new AttrNotFoundError(token.getLexeme(), this.struct, token.getLine(), token.getColumn());
                }
            }
        } else {
            if(encadenado != null){
                if (st.getStructTable().get(structType).fetchAttribute(token.getLexeme())) {
                    type = encadenado.check(st.getStructTable().get(structType).getAttribute(token.getLexeme()).getType(), st);
                }
                else {
                    throw new AttrNotFoundError(token.getLexeme(), structType, token.getLine(), token.getColumn());
                }
            }
            else {
                if(st.getStructTable().get(structType).fetchAttribute(token.getLexeme())) {
                    type = st.getStructTable().get(structType).getAttribute(token.getLexeme()).getType();
                }
                else {
                    throw new AttrNotFoundError(token.getLexeme(), structType, token.getLine(), token.getColumn());
                }

            }
        }
        return type;
    }

    @Override
    public String check(SymbolTable st) {
        return "";
    }
}
