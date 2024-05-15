package tinyru.etapa4.AST;

public class ExpUnNode extends ExpMulNode{
    OperandoNode operando;
    OpUnitarioNode opUnitario;
    ExpUnNode expUn;

    public ExpUnNode(){
    }

    public ExpUnNode(OperandoNode operando){
        this.operando = operando;
    }

    public ExpUnNode(OpUnitarioNode opUnitario, ExpUnNode expUn){
        this.opUnitario = opUnitario;
        this.expUn = expUn;
    }
}
