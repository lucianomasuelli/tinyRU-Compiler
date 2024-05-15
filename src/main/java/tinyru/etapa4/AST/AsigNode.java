package tinyru.etapa4.AST;

public class AsigNode extends SentenciaNode {
    AccesoVarNode variable;
    ExpresionNode expr;

    public AsigNode(AccesoVarNode variable, ExpresionNode expr){
        this.variable = variable;
        this.expr = expr;
    }
}
