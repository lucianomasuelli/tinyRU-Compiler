package tinyru.etapa4.AST;

public class IfNode extends SentenciaNode {
    ExpresionNode condicional;
    SentenciaNode cuerpo;
    SentenciaNode sentElse;

    public IfNode(ExpresionNode condicional, SentenciaNode cuerpo, SentenciaNode sino) {
        this.condicional = condicional;
        this.cuerpo = cuerpo;
        this.sentElse = sino;
    }

    public void print() {
        System.out.print("if (");
        condicional.print();
        System.out.println(") {");
        cuerpo.print();
        System.out.println("}");
        if (sentElse != null) {
            System.out.println("else {");
            sentElse.print();
            System.out.println("}");
        }
    }
}
