package tinyru.etapa4;

public class NodoIf extends NodoSentencia {
    NodoExpresion condicional;
    NodoSentencia cuerpo;
    NodoSentencia sentElse;

    public NodoIf(NodoExpresion condicional, NodoSentencia cuerpo, NodoSentencia sino) {
        this.condicional = condicional;
        this.cuerpo = cuerpo;
        this.sentElse = sino;
    }
}
