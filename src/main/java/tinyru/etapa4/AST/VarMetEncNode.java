package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa5.CodeGenerator;

public abstract class VarMetEncNode extends PrimarioNode{

    public VarMetEncNode(Token token, String metodo, String struct) {
        super(token, metodo, struct);
    }

    public VarMetEncNode(Token token, String metodo) {
        super(token, metodo);
    }

    public VarMetEncNode(Token token) {
        super(token);
    }

    public abstract void generateCode(CodeGenerator cg, String structCaller);

    //public abstract void generateCode(CodeGenerator cg, String varType, String varName);
}
