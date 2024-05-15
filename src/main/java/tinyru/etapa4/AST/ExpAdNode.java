package tinyru.etapa4.AST;

public class ExpAdNode extends ExpCompNode{
    ExpAdNode expAdLeft;
    OpAdNode opAd;
    ExpMulNode expMulRight;

    public ExpAdNode(){
    }

    public ExpAdNode(ExpAdNode expAdLeft, OpAdNode opAd, ExpMulNode expMulRight){
        this.expAdLeft = expAdLeft;
        this.opAd = opAd;
        this.expMulRight = expMulRight;
    }
}
