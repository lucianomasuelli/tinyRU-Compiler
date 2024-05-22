package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.ReturnTypeError;

public class ReturnNode extends SentenciaNode{

    Token token;
    ExpresionNode exp;
    String type;
    String metodo;
    String struct;

    public ReturnNode(Token token, ExpresionNode exp, String struct, String metodo){
        this.exp = exp;
        if (exp == null){ this.type = "void";}
        this.metodo = metodo;
        this.struct = struct;
        this.token = token;
    }

    public void print() {
        System.out.print("ret ");
        exp.print();
        System.out.println(";");
    }

    @Override
    public String check(SymbolTable st){
        String type = exp.check(st);

        String methodReturn = st.getStruct(struct).getMethod(metodo).getReturnType();
        if(!methodReturn.equals(type)){
            if(methodReturn.equals("Int") || methodReturn.equals("Char") || methodReturn.equals("Bool") || methodReturn.equals("Str")){
                if(type.equals("Nil")){
                    throw new ReturnTypeError(type, methodReturn, token.getLine(), token.getColumn());
                }
            }
        }
        this.type = type;
        return type;
    }
}
