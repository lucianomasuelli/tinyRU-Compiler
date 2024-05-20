package tinyru.etapa4.AST;

import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;

public abstract class ExpresionNode{
    private String type;

    //Aca deberia inferir el tipo o reconocerlo durante la ejecución?

    public void print() {
    }

    public abstract String check(SymbolTable st);

}
