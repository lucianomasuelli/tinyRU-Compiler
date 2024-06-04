package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;
import tinyru.etapa5.CodeGenerator;

public abstract class SentenciaNode{

    String type;
    public abstract String check(SymbolTable st);

    public void print() {}

    public abstract String jsonify();

    public abstract void generateCode(CodeGenerator cg);
}
