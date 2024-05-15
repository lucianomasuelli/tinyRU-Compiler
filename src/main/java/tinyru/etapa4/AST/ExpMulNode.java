package tinyru.etapa4.AST;

public class ExpMulNode extends ExpAdNode{
    ExpMulNode expUnLeft;
    OpMulNode opMul;
    ExpUnNode expUnRight;

    public ExpMulNode(){
    }

    public ExpMulNode(ExpUnNode expUnLeft){
        this.expUnLeft = expUnLeft;
    }

    public ExpMulNode(ExpMulNode expUnLeft, OpMulNode opMul, ExpUnNode expUnRight){
        this.expUnLeft = expUnLeft;
        this.opMul = opMul;
        this.expUnRight = expUnRight;
    }
}
