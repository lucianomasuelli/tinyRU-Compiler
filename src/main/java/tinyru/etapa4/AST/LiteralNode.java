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

    @Override
    public String check(String structType, SymbolTable st) {
        return check(st);
    }

    public TokenType getType() {
        return token.getType();
    }

    @Override
    public String check(SymbolTable st){
        String type = null;
        switch (token.getType()){
            case NUM -> type = "Int";
            case STRING-> type =  "Str";
            case PTRUE, PFALSE -> type =  "Bool";
            case CHAR-> type =  "Char";
        }
        return type;
    }
}
