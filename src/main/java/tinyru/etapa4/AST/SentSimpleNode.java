package tinyru.etapa4.AST;

public class SentSimpleNode extends SentenciaNode {
    private ExpresionNode expresion;

    public SentSimpleNode(ExpresionNode expresion) {
        this.expresion = expresion;
    }

}
