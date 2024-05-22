package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.MethodInput;
import tinyru.etapa3.ParamInput;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.ArgsMismatchError;
import tinyru.etapa4.Exceptions.WrongArgTypeError;

import java.util.Hashtable;
import java.util.List;

public class MetodoExprNode extends VarMetEncNode{
    private VarMetEncNode encadenado;
    private List<ExpresionNode> argActuales;

    public MetodoExprNode(Token token, String struct, String metodo) {
        super(token,metodo, struct);
    }

    public MetodoExprNode(Token token, String metodo) {
        super(token,metodo);
    }

    public MetodoExprNode(Token token) {
        super(token, null);
    }

    public void setEncadenado(VarMetEncNode encadenado) {
        this.encadenado = encadenado;
    }

    public void setArgActuales(List<ExpresionNode> argActuales) {
        this.argActuales = argActuales;
    }

    public Token getToken() {
        return token;
    }
    public String getName() {
        return token.getLexeme();
    }


    public void addArgActual(ExpresionNode argActual) {
        this.argActuales.add(argActual);
    }

    @Override
    public String check(String structType, SymbolTable st) {
        String metodo = token.getLexeme();
        String type = null;
        if (encadenado != null) {
            structType = st.getStruct(structType).getMethod(metodo).getReturnType();
            type = encadenado.check(structType, st); //TODO
        }else{
            // verifica los argumentos

            MethodInput method = st.getStruct(structType).getMethod(token.getLexeme());
            if (method.getParameterTable().size() != argActuales.size()) {
                throw new ArgsMismatchError(argActuales.size(), method.getParameterTable().size(), token.getLine(), token.getColumn());
            }
            for (int i = 0; i < argActuales.size(); i++) {
                if (!argActuales.get(i).check(st).equals(method.getParamByPosition(i).getType())) {
                    throw new WrongArgTypeError(method.getParamByPosition(i).getType(),
                            argActuales.get(i).check(st), token.getLine(), token.getColumn());
                }
            }
            // el tipo es el valor de retorno del método
            type = st.getStructTable().get(structType).getMethod(metodo).getReturnType();
        }
        return type;
    }

    @Override
    public String check(SymbolTable st) {
        return check(null, st);
    }
}
