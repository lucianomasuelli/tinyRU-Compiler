package tinyru.etapa4.AST;

import tinyru.etapa3.MethodInput;
import tinyru.etapa3.SymbolTable;

import java.util.List;

public class BloqueMetodoNode extends  BloqueNode{
    private String methodName;

    public BloqueMetodoNode(List<SentenciaNode> sentencias, String structName, String methodName) {
        super(sentencias, structName,methodName);
    }

    @Override
    public void check(SymbolTable st) {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.check(st);
        }
    }

    @Override
    public String jsonify(){
        String json = "";
        json += "{\n";
        json += "\"nombre\": \"" + methodName + "\",\n";
        json += "\"sentencias\": [\n";
        for (SentenciaNode sentencia : sentencias) {
            json += sentencia.jsonify();
            if(sentencias.indexOf(sentencia) != sentencias.size() - 1){
                json += ",\n";
            }
        }
        json += "]\n";
        json += "}\n";
        return json;
    }
}
