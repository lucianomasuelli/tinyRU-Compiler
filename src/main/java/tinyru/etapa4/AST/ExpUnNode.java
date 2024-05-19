package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.TypesMismatchError;

public class ExpUnNode extends ExpresionNode{
    ExpresionNode expRight;
    Token op;

    public ExpUnNode(){
    }

    public ExpUnNode(ExpresionNode expRight, Token op){
        this.expRight = expRight;
        this.op = op;
    }

    public void print() {
        System.out.print(op.getLexeme());
        expRight.print();
    }

    @Override
    public String check(SymbolTable st){
        String type = expRight.check(st);
        if (op.getLexeme().equals('!')){
            if (!type.equals("Bool")) {
                throw new TypesMismatchError(type, "Bool", op.getLine(), op.getColumn());
            }
        } else {
            if (!type.equals("Int")){
                throw new TypesMismatchError(type, "Int", op.getLine(), op.getColumn());
            }
        }
        return type;
    }
}
