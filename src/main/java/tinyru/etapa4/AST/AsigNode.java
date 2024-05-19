package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;

public class AsigNode extends SentenciaNode {
    AccesoVarNode variable;
    ExpresionNode expr;

    public AsigNode(AccesoVarNode variable, ExpresionNode expr){
        this.variable = variable;
        this.expr = expr;
    }

    public void print() {
        System.out.print("Asignacion: ");
        variable.print();
        System.out.print(" = ");
        expr.print();
        System.out.println(";");
    }

    public void check(SymbolTable st) {
        String varType = variable.check(null, st);
        String expType = expr.check(st);
        System.out.println("Asignacion: " + varType + " = " + expType);
    }
}
