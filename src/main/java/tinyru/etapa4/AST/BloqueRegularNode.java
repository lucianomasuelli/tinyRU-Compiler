package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;

import java.util.List;

public class BloqueRegularNode extends BloqueNode{
    public BloqueRegularNode(List<SentenciaNode> sentencias, String structName){
        super(sentencias,structName);
    }

    @Override
    public void check(SymbolTable st) {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.check(st);
        }
    }
}
