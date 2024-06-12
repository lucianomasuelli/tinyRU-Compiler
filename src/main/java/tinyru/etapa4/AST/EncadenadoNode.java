package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;
import tinyru.etapa5.CodeGenerator;

public abstract class EncadenadoNode{

    public abstract String check(String type, SymbolTable st);

    public abstract String getType();

    public abstract String jsonify();

    public abstract void generateCode(CodeGenerator cg);

    public abstract void generateCode(CodeGenerator cg, String caller);

}
