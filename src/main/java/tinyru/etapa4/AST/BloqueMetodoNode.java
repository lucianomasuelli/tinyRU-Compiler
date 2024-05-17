package tinyru.etapa4.AST;

import tinyru.etapa3.MethodInput;

import java.util.List;

public class BloqueMetodoNode extends  BloqueNode{
    private String methodName;

    public BloqueMetodoNode(List<SentenciaNode> sentencias, String structName, String methodName) {
        super(sentencias, structName);
        this.methodName = methodName;
    }

}
