package tinyru.etapa4;

public class NodoWhile extends NodoSentencia{
    NodoExpresion condicional;
    NodoSentencia cuerpo;

    public NodoWhile(NodoExpresion condicional, NodoSentencia cuerpo) {
        this.condicional = condicional;
        this.cuerpo = cuerpo;
    }
}
