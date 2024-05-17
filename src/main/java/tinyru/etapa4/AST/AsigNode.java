package tinyru.etapa4.AST;

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
}
