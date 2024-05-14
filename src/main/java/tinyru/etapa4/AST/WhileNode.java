package tinyru.etapa4.AST;

public class WhileNode extends SentenciaNode {
    ExpresionNode condicional;
    SentenciaNode cuerpo;

    public WhileNode(ExpresionNode condicional, SentenciaNode cuerpo) {
        this.condicional = condicional;
        this.cuerpo = cuerpo;
    }
}
