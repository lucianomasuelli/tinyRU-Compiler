package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;

public abstract class SentenciaNode{

    String type;
    public abstract String check(SymbolTable st);

    public void print() {}

    public abstract String jsonify();

}
