package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;

import java.util.List;

public abstract class BloqueNode{
    protected String structName;
    protected String methodName;
    protected List<SentenciaNode> sentencias;

    public BloqueNode(List<SentenciaNode> sentencias, String structName,String methodName) {
        this.sentencias = sentencias;
        this.structName = structName;
        this.methodName = methodName;
    }

    public void print() {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.print();
        }
    }

    public String getMethodName() {
        return methodName;
    }
    public String getStructName(){
        return structName;
    }

    public abstract String jsonify();

    public abstract void check(SymbolTable st);
}
