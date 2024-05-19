package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;

public class LiteralNode extends OperandoNode {
    Token token;

    public LiteralNode(Token token){
        this.token = token;
    }

    public void print() {
        System.out.print(token.getLexeme());
    }

    public TokenType getType() {
        return token.getType();
    }

    @Override
    public String check(SymbolTable st){
        String type = null;
        switch (token.getType()){
            case PINT, NUM -> type = "Int";
            case PSTR-> type =  "Str";
            case PTRUE, PFALSE -> type =  "Bool";
            case PCHAR-> type =  "Char";
        }
        return type;
    }
}
