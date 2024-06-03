package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa5.CodeGenerator;

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

    public String getType() {
        return check(null);
    }

    public Token getToken() {
        return token;
    }

    public String getLiteral() {
        return token.getLexeme();
    }

    @Override
    public String check(SymbolTable st){
        String type = null;
        switch (token.getType()){
            case NUM -> type = "Int";
            case STRING-> type =  "Str";
            case PTRUE, PFALSE -> type =  "Bool";
            case CHAR-> type =  "Char";
            case PNIL -> type =  "Nil";
        }
        return type;
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"LiteralNode\": {\n";
        json += "\"tipo\": ";
        String type = null;
        switch (token.getType()){
            case NUM -> type = "Int";
            case STRING-> type =  "Str";
            case PTRUE, PFALSE -> type =  "Bool";
            case CHAR-> type =  "Char";
        }
        json += type;
        json+= "\n";
        json += "\"valor\": \"" + token.getLexeme() + "\",\n";
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public String generateCode() {
        return getLiteral();
    }
}
