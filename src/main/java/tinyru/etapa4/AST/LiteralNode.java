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
    public void generateCode(CodeGenerator cg) {
        if(token.getType() == TokenType.NUM) {
            cg.getTextSection().append("li $a0, ").append(token.getLexeme()).append("\n");
        } else if (token.getType()==TokenType.PTRUE || token.getType() == TokenType.PFALSE) {
            cg.getTextSection().append("li $a0, ").append(token.getLexeme().equals("true") ? 1 : 0).append("\n");
        } else if (token.getType()==TokenType.CHAR) {
            cg.getTextSection().append("li $a0, ").append((int)token.getLexeme().charAt(1)).append("\n");
        } else if (token.getType()==TokenType.STRING) {
            //TODO Revisar que funcione
            String lexeme = token.getLexeme();
            int length = lexeme.length();

            cg.getTextSection().append("li $v0, 9\n");
            cg.getTextSection().append("li $a0, ").append(length + 1).append("\n"); //Conseguir la longitud del coso
            cg.getTextSection().append("syscall\n");
            cg.getTextSection().append("move $a0, $v0\n"); // Store the address of the allocated memory in $t0

            // Copy the string to the allocated memory
            for (int i = 0; i < length; i++) { // Start from 1 to skip the opening quote and end at length to skip the closing quote
                char c = lexeme.charAt(i);
                cg.getTextSection().append("li $t0, ").append((int) c).append("\n");
                cg.getTextSection().append("sb $t0, ").append(i).append("($a0)\n");
            }

            // Add the null terminator
            cg.getTextSection().append("li $t0, 0\n");
            cg.getTextSection().append("sb $t0, ").append(length).append("($a0)\n");


        }
    }
}
