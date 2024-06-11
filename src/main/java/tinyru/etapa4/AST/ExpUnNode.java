package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.TypesMismatchError;
import tinyru.etapa5.CodeGenerator;

import java.util.HashSet;
import java.util.Set;

public class ExpUnNode extends ExpresionNode{
    ExpresionNode expRight;
    Token op;

    HashSet<TokenType> booleanTypes = new HashSet<TokenType>(Set.of(TokenType.NOT));
    String type;

    public ExpUnNode(){
    }

    public ExpUnNode(ExpresionNode expRight, Token op){
        this.expRight = expRight;
        this.op = op;
        if (booleanTypes.contains(op.getType())) {
            type = "Bool";
        } else {
            type = "Int";
        }
    }

    public void print() {
        System.out.print(op.getLexeme());
        expRight.print();
    }

    @Override
    public String check(String structType, SymbolTable st) {
        return check(st);
    }

    @Override
    public String check(SymbolTable st){
        String type = expRight.check(st);
        if (this.type.equals("Bool")){
            if (!type.equals("Bool")) {
                throw new TypesMismatchError(type, "Bool", op.getLine(), op.getColumn());
            }
        } else {
            if (!type.equals("Int")){
                throw new TypesMismatchError(type, "Int", op.getLine(), op.getColumn());
            }
        }
        return this.type;
    }
    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"ExpUnNode\": {\n";
        json += "\"op\": " + op.getLexeme() + ",\n";
        json += "\"expRight\": " + expRight.jsonify() + "\n";
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        expRight.generateCode(cg);
        cg.getTextSection().append("lw $t1, $a0\n");
        switch(op.getType()){
            case NOT -> cg.getTextSection().append("seq $a0, $t1, $zero\n");
            case INC -> cg.getTextSection().append("addi $a0, $t1, 1\n");
            case DEC -> cg.getTextSection().append("addi $a0, $t1, -1\n");
            case SUM -> cg.getTextSection().append("addi $a0, $t1, 0\n");
            case RESTA -> cg.getTextSection().append("sub $a0, $zero, $t1\n");
        }
    }
}
