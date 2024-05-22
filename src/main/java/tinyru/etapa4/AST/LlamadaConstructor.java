package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.ArgsMismatchError;
import tinyru.etapa4.Exceptions.StructNotFoundError;
import tinyru.etapa4.Exceptions.WrongArgTypeError;

import java.util.List;

public class LlamadaConstructor extends LlamadaConstructorNode{
    private String idStruct;
    private List<ExpresionNode> args;
    private VarMetEncNode encadenado = null;

    public LlamadaConstructor(Token token, String struct, String metodo, String idStruct, List<ExpresionNode> args, VarMetEncNode encadenado){
        super(token, metodo, struct);
        this.args = args;
        this.encadenado = encadenado;
        this.idStruct = idStruct;
    }


    @Override
    public String check(SymbolTable st) {
        String type = idStruct;
        if(encadenado != null){
            type = encadenado.check(type, st);
        }
        else {
            if (st.fetchStruct(idStruct)) {
                if (st.getStruct(idStruct).getConstructor().getConstructorParams().size() == args.size()) {
                    for (int i = 0; i < args.size(); i++) {
                        if (!args.get(i).check(st).equals(st.getStruct(idStruct).getConstructor().getParamByPosition(i).getType())) {
                            throw new WrongArgTypeError(st.getStruct(idStruct).getConstructor().getParamByPosition(i).getType(),
                                    args.get(i).check(st), token.getLine(), token.getColumn());
                        }
                    }
                } else {
                    throw new ArgsMismatchError(args.size(), st.getStruct(idStruct).getConstructor().getConstructorParams().size(),
                            token.getLine(), token.getColumn());
                }
            } else {
                throw new StructNotFoundError(idStruct, token.getLine(), token.getColumn());
            }

        }
        return type;
    }

    @Override
    public String check(String type, SymbolTable st) {
        return check(st);
    }
}
