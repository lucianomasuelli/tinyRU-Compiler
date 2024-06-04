package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;
import tinyru.etapa5.CodeGenerator;

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

    public List<SentenciaNode> getSentencias() {
        return sentencias;
    }

    public String getMethodName() {
        return methodName;
    }
    public String getStructName(){
        return structName;
    }

    public abstract String jsonify();

    public abstract void check(SymbolTable st);

    public String generateCode(CodeGenerator cg) {
        String code = "";
        for (SentenciaNode sentencia : sentencias) {
            code = sentencia.generateCode(cg);
        }
        return code;
    }
}
