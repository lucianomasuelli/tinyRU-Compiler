package tinyru.etapa4.AST;

import tinyru.etapa3.MethodInput;

public class MetodoNode {
    private MethodInput methodInput;
    private BloqueNode bloque;

    public MetodoNode(MethodInput methodInput, BloqueNode bloque) {
        this.methodInput = methodInput;
        this.bloque = bloque;
    }

    public MethodInput getMethodInput() {
        return methodInput;
    }

    public void setMethodInput(MethodInput methodInput) {
        this.methodInput = methodInput;
    }

    public BloqueNode getBloque() {
        return bloque;
    }

    public void setBloque(BloqueNode bloque) {
        this.bloque = bloque;
    }
}
