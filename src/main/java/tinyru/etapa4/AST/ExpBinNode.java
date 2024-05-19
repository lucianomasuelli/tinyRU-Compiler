package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.TypesMismatchError;

public class ExpBinNode extends ExpresionNode {

    ExpresionNode expLeft;
    Token op;
    ExpresionNode expRight;

    public ExpBinNode(){
    }

    public ExpBinNode(ExpresionNode expLeft, Token op, ExpresionNode expRight){
        this.expLeft = expLeft;
        this.op = op;
        this.expRight = expRight;
    }

    public void print() {
        System.out.print("(");
        expLeft.print();
        System.out.print(" " + op.getLexeme() + " ");
        expRight.print();
        System.out.print(")");
    }

    @Override
    public String check(SymbolTable st){

        String typeLeft = expLeft.check(st);
        String typeRigth = expRight.check(st);
        if (typeLeft.equals(typeRigth)){
            return typeLeft;
        }else {
            throw new TypesMismatchError(typeLeft, typeRigth, op.getLine(), op.getColumn());
        }
    }
}
