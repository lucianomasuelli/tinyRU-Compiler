package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;

public class ExpresionNode{
    private String type;
    private Token token;

    //Aca deberia inferir el tipo o reconocerlo durante la ejecución?

    public void print() {
    }

    //Retorna el tipo de la expresión
    public String check(SymbolTable st){ return this.type; }

}
