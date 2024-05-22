package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.TypesMismatchError;

import java.util.HashSet;
import java.util.Set;

public class ExpBinNode extends ExpresionNode {

    ExpresionNode expLeft;
    Token op;
    ExpresionNode expRight;

    HashSet<TokenType> booleanTypes = new HashSet<TokenType>(Set.of(TokenType.AND,TokenType.DIF,TokenType.IGUAL,TokenType.MAYOR,TokenType.MAYORIGUAL,TokenType.MENOR,TokenType.MENORIGUAL,TokenType.OR));
    String type;

    public ExpBinNode(){
    }

    public ExpBinNode(ExpresionNode expLeft, Token op, ExpresionNode expRight){
        this.expLeft = expLeft;
        this.op = op;
        this.expRight = expRight;
        if (booleanTypes.contains(op.getType())) {
            type = "Bool";
        } else {
            type = "Int";
        }
    }

    public void print() {
        System.out.print("(");
        expLeft.print();
        System.out.print(" " + op.getLexeme() + " ");
        expRight.print();
        System.out.print(")");
    }

    @Override
    public String check(SymbolTable st){

        String typeLeft = expLeft.check(st);
        String typeRigth = expRight.check(st);
        if (typeLeft.equals(typeRigth)){
            return this.type;
        }else {
            throw new TypesMismatchError(typeLeft, typeRigth, op.getLine(), op.getColumn());
        }
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"ExpBinNode\": {\n";
        json += "\"expLeft\": " + expLeft.jsonify() + ",\n";
        json += "\"op\": " + op.getLexeme()   + ",\n";
        json += "\"expRight\": " + expRight.jsonify() + "\n";
        json += "}\n";
        json += "}";
        return json;
    }
}
