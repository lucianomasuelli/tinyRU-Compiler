package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.ArraySizeError;
import tinyru.etapa4.Exceptions.TypeDefinitionError;
import tinyru.etapa5.CodeGenerator;

public class ArrayNode extends LlamadaConstructorNode{
    private String variable;
    private String primitiveType;
    private ExpresionNode size;

    public ArrayNode(Token token, String struct, String metodo, String primitiveType, ExpresionNode size){
        super(token, metodo, struct);
        this.primitiveType = primitiveType;
        this.size = size;
    }

    @Override
    public String check(String structType, SymbolTable st) {
        return check(st);
    }

    @Override
    public String check(SymbolTable st) {
        String type = null;
        if((primitiveType.equals("Int") || primitiveType.equals("Str") || primitiveType.equals("Bool") || primitiveType.equals("Char"))) {
            if(!size.check(st).equals("Int")) {
                throw new ArraySizeError(size.getType(), token.getLine(), token.getColumn());
            }
        }
        else {
            throw new TypeDefinitionError(primitiveType, token.getLine(), token.getColumn());
        }

        type = switch (primitiveType) {
            case "Int" -> "Array Int";
            case "Str" -> "Array Str";
            case "Bool" -> "Array Bool";
            case "Char" -> "Array Char";
            default -> type;
        };
        return type;
    }

    @Override
    public String jsonify(){
        return "{\"Array\": {\"nombre\": " + token.getLexeme() + ", \"metodo\": \"" + metodo + "\", \"struct\": \"" + struct + "\", \"primitiveType\": \"" + primitiveType + "\", \"size\": " + size.jsonify() + "}}";
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        //Calculo del tamaño del arreglo
        size.generateCode(cg);
        cg.getTextSection().append("lw $t1, $a0\n");
        //Multiplicación del tamaño por el tamaño de cada elemento

        switch (primitiveType) {
            case "Int" -> {cg.getTextSection().append("mul $a0, $t1, 4\n");}
            case "Str" -> {cg.getTextSection().append("mul $a0, $t1 1025\n");}
            case "Bool","Char" -> {cg.getTextSection().append("mul $a0, $t1, 1\n");}
        };

        cg.getTextSection().append("li $v0, 9\n");
        cg.getTextSection().append("syscall\n");

        //Guardar el array en la stack
        cg.getTextSection().append("sw $v0, 0($sp)\n");

    }
}
