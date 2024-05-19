package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;

import java.util.List;

public class BloqueRegularNode extends SentenciaNode{
    protected String structName;
    protected List<SentenciaNode> sentencias;

    public BloqueRegularNode(List<SentenciaNode> sentencias, String structName) {
        this.sentencias = sentencias;
        this.structName = structName;
    }

    public void print() {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.print();
        }
    }
    @Override
    public void check(SymbolTable st) {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.check(st);
        }
    }
}
