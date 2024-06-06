package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.TypesMismatchError;
import tinyru.etapa5.CodeGenerator;

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
    public String check(String structType, SymbolTable st) {
        return check(st);
    }


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

    @Override
    public void generateCode(CodeGenerator cg) {
        expLeft.generateCode(cg);
        cg.getTextSection().append("sw $a0, 0($sp)\n");
        cg.getTextSection().append("addiu $sp, $sp, -4\n");
        expRight.generateCode(cg);
        cg.getTextSection().append("lw $t1, 4($sp)\n");
        switch(op.getType()) {
            case SUM -> cg.getTextSection().append("add $a0, $a0, $t1\n");
            case RESTA -> cg.getTextSection().append("sub $a0, $a0, $t1\n");
            case IGUAL -> cg.getTextSection().append("seq $a0, $a0, $t1\n");
        }
    }
}
