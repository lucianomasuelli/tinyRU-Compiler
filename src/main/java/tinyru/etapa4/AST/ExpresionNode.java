package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;

public abstract class ExpresionNode{
    private String type;
    private Token token;

    //Aca deberia inferir el tipo o reconocerlo durante la ejecución?

    public void print() {
    }

    public abstract String check(String structType, SymbolTable st);
    public abstract String check(SymbolTable st);

}
