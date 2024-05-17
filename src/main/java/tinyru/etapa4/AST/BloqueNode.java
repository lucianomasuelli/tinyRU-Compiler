package tinyru.etapa4.AST;

import java.util.List;

public abstract class BloqueNode {
    protected String structName;
    protected List<SentenciaNode> sentencias;

    public BloqueNode(List<SentenciaNode> sentencias, String structName) {
        this.sentencias = sentencias;
        this.structName = structName;
    }

    public void print() {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.print();
        }
    }
}
