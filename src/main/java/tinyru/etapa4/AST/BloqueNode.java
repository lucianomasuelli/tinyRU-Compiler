package tinyru.etapa4.AST;

import java.util.List;

public abstract class BloqueNode {
    protected List<SentenciaNode> sentencias;

    public BloqueNode(List<SentenciaNode> sentencias) {
        this.sentencias = sentencias;
    }
}
