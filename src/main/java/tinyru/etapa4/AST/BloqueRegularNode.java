package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;
import tinyru.etapa5.CodeGenerator;

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
    public String check(SymbolTable st) {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.check(st);
        }
        return "Sentencia";
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"BloqueRegularNode\": {\n";
        json += "\"sentencias\": [";
        for (SentenciaNode sentencia : sentencias) {
            json += sentencia.jsonify() + ",\n";
        }
        json += "]\n";
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.generateCode(cg);
        }
    }
}
