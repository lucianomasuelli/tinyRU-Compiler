package tinyru.etapa4.AST;

public class SentenciaNode {
    AsigNode asig;
    SentSimpleNode sentSimple;
    IfNode sentIf;
    WhileNode sentWhile;
    BloqueNode bloque;
    ReturnNode ret;

    public SentenciaNode(){
        this.asig = null;
        this.sentSimple = null;
        this.sentIf = null;
        this.sentWhile = null;
        this.bloque = null;
        this.ret = null;
    }

    public SentenciaNode(AsigNode asig){
        this.asig = asig;
    }

    public SentenciaNode(SentSimpleNode sentSimple){
        this.sentSimple = sentSimple;
    }

    public SentenciaNode(IfNode sentIf){
        this.sentIf = sentIf;
    }

    public SentenciaNode(WhileNode sentWhile){
        this.sentWhile = sentWhile;
    }

    public SentenciaNode(BloqueNode bloque){
        this.bloque = bloque;
    }

    public SentenciaNode(ReturnNode ret){
        this.ret = ret;
    }
}
