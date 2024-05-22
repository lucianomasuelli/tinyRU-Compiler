package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.ArraySizeError;
import tinyru.etapa4.Exceptions.TypeDefinitionError;

public class ArrayNode extends LlamadaConstructorNode{
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
}
