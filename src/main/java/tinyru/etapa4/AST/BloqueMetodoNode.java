package tinyru.etapa4.AST;

import tinyru.etapa3.MethodInput;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.MissingReturnError;

import java.util.List;
import java.util.Objects;

public class BloqueMetodoNode extends  BloqueNode{


    public BloqueMetodoNode(List<SentenciaNode> sentencias, String structName, String methodName) {
        super(sentencias, structName,methodName);
    }

    @Override
    public void check(SymbolTable st) {
        String returnType = st.getStruct(structName).getMethod(methodName).getReturnType();
        boolean ret = false;
        for (SentenciaNode sentencia : sentencias) {
            if(!returnType.equals("void")){
                //verifica si es de tipo ReturnNode
                if(sentencia instanceof ReturnNode) {
                    ret = true;
                }
            }
            sentencia.check(st);
        }
        if(ret == false && !returnType.equals("void")){
            int methodLine = st.getStruct(structName).getMethod(methodName).getLine();
            int methodColumn = st.getStruct(structName).getMethod(methodName).getColumn();
            throw new MissingReturnError(methodName, methodLine, methodColumn);
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
