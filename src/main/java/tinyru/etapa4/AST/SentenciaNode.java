package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;

public abstract class SentenciaNode{

    String type;
    public String check(SymbolTable st) {return this.type;}

    public void print() {}

}
