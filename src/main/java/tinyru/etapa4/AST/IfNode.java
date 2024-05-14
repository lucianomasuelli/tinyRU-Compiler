package tinyru.etapa4.AST;

public class IfNode extends SentenciaNode {
    ExpresionNode condicional;
    SentenciaNode cuerpo;
    SentenciaNode sentElse;

    public IfNode(ExpresionNode condicional, SentenciaNode cuerpo, SentenciaNode sino) {
        this.condicional = condicional;
        this.cuerpo = cuerpo;
        this.sentElse = sino;
    }
}
