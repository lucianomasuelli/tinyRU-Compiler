package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;

public class ReturnNode extends SentenciaNode{

    ExpresionNode exp;
    String type;

    public ReturnNode(ExpresionNode exp){
        this.exp = exp;
        if (exp == null){ this.type = "void";}
    }

    public void print() {
        System.out.print("ret ");
        exp.print();
        System.out.println(";");
    }

    @Override
    public String check(SymbolTable st){
        String type = exp.check(st);
        this.type = type;
        return type;
    }
}
