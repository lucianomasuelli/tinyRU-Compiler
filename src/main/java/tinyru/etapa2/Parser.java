package tinyru.etapa2;
import tinyru.etapa1.Lexer;
import tinyru.etapa1.Token;
import tinyru.etapa2.Exceptions.UnexpectedTokenError;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    Lexer lexer;
    Token actual_token;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void analize() {

    }

    private void match(String expected) {
        if (actual_token.getLexeme().equals(expected)) {
            try {
                actual_token = lexer.nextToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Syntax error: expected " + expected + " but found " + actual_token.getLexeme());
            System.exit(1);
        }
    }

    private Set<String> first(String noTerminal) {
        Set<String> firstSet = new HashSet<>();
        switch (noTerminal) {
            case "program" -> {
                firstSet = new HashSet<>(Set.of("struct", "impl", "start"));
            }
            case "start" -> {
                firstSet = new HashSet<>(Set.of("start"));
            }
            case "lista_definiciones", "lista_definiciones'" -> {
                firstSet = new HashSet<>(Set.of("struct", "impl"));
            }
            case "struct" -> {
                firstSet = new HashSet<>(Set.of("struct"));
            }
            case "struct'" -> {
                firstSet = new HashSet<>(Set.of(":", "{"));
            }
            case "struct''", "bloque_metodo" -> {
                firstSet = new HashSet<>(Set.of("{"));
            }
            case "struct'''" -> {
                firstSet = new HashSet<>(Set.of("}","pri","Str","Bool","Int","Char","idStruct","Array"));
            }
            case "N2", "atributo" -> {
                firstSet = new HashSet<>(Set.of("pri","Str","Bool","Int","Char","idStruct","Array"));
            }
            case "N2'" -> {
                firstSet = new HashSet<>(Set.of("lambda","pri","Str","Bool","Int","Char","idStruct","Array"));
            }
            case "impl" -> {
                firstSet = new HashSet<>(Set.of("impl"));
            }
            case "impl'" -> {
                firstSet = new HashSet<>(Set.of("}","st","fn","."));
            }
            case "N3", "miembro" -> {
                firstSet = new HashSet<>(Set.of("st","fn","."));
            }
            case "N3'" -> {
                firstSet = new HashSet<>(Set.of("lambda","st","fn","."));
            }
            case "herencia" -> {
                firstSet = new HashSet<>(Set.of(":"));
            }
            case "constructor" -> {
                firstSet = new HashSet<>(Set.of("."));
            }
            case "metodo" -> {
                firstSet = new HashSet<>(Set.of("st","fn"));
            }
            case "metodo'" -> {
                firstSet = new HashSet<>(Set.of("->","("));
            }
            case "N6", "decl_var_locales" -> {
                firstSet = new HashSet<>(Set.of("Str","Bool","Int","Char","idStruct","Array"));
            }
            case "N6'" -> {
                firstSet = new HashSet<>(Set.of("lambda","Str","Bool","Int","Char","idStruct","Array"));
            }
            case "N7", "sentencia" -> {
                firstSet = new HashSet<>(Set.of(";","if","while","ret","(","{","id","self"));
            }
            case "N7'" -> {
                firstSet = new HashSet<>(Set.of("lambda",";","if","while","ret","(","{","id","self"));
            }
            case "lista_declaracion_variables" -> {
                firstSet = new HashSet<>(Set.of("idMetAt"));
            }
            case "lista_declaracion_variables'" -> {
                firstSet = new HashSet<>(Set.of(",","lambda"));
            }
            case "argumentos_formales" -> {
                firstSet = new HashSet<>(Set.of("("));
            }
            case "argumentos_formales'" -> {
                firstSet = new HashSet<>(Set.of("Str","Bool","Int","Char","idStruct","Array",")"));
            }
            case "lista_argumentos_formales" -> {
                firstSet = new HashSet<>(Set.of("Str","Bool","Int","Char","idStruct","Array"));
            }
            case "lista_argumentos_formales'" -> {
                firstSet = new HashSet<>(Set.of(",","lambda"));
            }
            case "argumento_formal" -> {
                firstSet = new HashSet<>(Set.of("Str","Bool","Int","Char","idStruct","Array"));
            }
            case "tipo_metodo" -> {
                firstSet = new HashSet<>(Set.of("Str","Bool","Int","Char","idStruct","Array","void"));
            }
            case "tipo" -> {
                firstSet = new HashSet<>(Set.of("Str","Bool","Int","Char","idStruct","Array"));
            }
            case "tipo_primitivo" -> {
                firstSet = new HashSet<>(Set.of("Str","Bool","Int","Char"));
            }
            case "tipo_referencia" -> {
                firstSet = new HashSet<>(Set.of("idStruct"));
            }
            case "tipo_arreglo" -> {
                firstSet = new HashSet<>(Set.of("Array"));
            }
            case "bloque" -> {
                firstSet = new HashSet<>(Set.of("{"));
            }
            case "asignacion" -> {
                firstSet = new HashSet<>(Set.of("id","self"));
            }
            case "N9" -> {
                firstSet = new HashSet<>(Set.of(";","if","while","ret","(","{","id","self"));
            }
            case "N9'" -> {
                firstSet = new HashSet<>(Set.of("lambda",";","if","while","ret","(","{","id","self"));
            }
            case "acceso_var_simple" -> {
                firstSet = new HashSet<>(Set.of("id"));
            }
            case "N10" -> {
                firstSet = new HashSet<>(Set.of("."));
            }
            case "N10'" -> {
                firstSet = new HashSet<>(Set.of("lambda","."));
            }
            case "acceso_self_simple" -> {
                firstSet = new HashSet<>(Set.of("self"));
            }
            case "N11" -> {
                firstSet = new HashSet<>(Set.of("."));
            }
            case "N11'" -> {
                firstSet = new HashSet<>(Set.of("lambda","."));
            }
            case "encadenado_simple" -> {
                firstSet = new HashSet<>(Set.of("."));
            }
            case "sentencia_simple" -> {
                firstSet = new HashSet<>(Set.of("("));
            }
            case "expresion", "exp_or", "exp_and", "exp_igual", "exp_compuesta", "exp_ad", "exp_mul", "exp_un" -> {
                firstSet = new HashSet<>(Set.of("+","-","!","++","--","nil","true","false","intLiteral","StrLiteral","charLiteral","(","self","id","idStruct","new"));
            }
            case "exp_or'" -> {
                firstSet = new HashSet<>(Set.of("||","lambda"));
            }
            case "exp_and'" -> {
                firstSet = new HashSet<>(Set.of("&&","lambda"));
            }
            case "exp_igual'" -> {
                firstSet = new HashSet<>(Set.of("==","!=","lambda"));
            }
            case "exp_compuesta'" -> {
                firstSet = new HashSet<>(Set.of("<",">","<=",">=","lambda"));
            }
            case "exp_ad'" -> {
                firstSet = new HashSet<>(Set.of("+","-","lambda"));
            }
            case "exp_mul'" -> {
                firstSet = new HashSet<>(Set.of("*","/","%","lambda"));
            }
            case "op_igual" -> {
                firstSet = new HashSet<>(Set.of("==","!="));
            }
            case "op_compuesto" -> {
                firstSet = new HashSet<>(Set.of("<",">","<=",">="));
            }
            case "op_ad" -> {
                firstSet = new HashSet<>(Set.of("+","-"));
            }
            case "op_unario" -> {
                firstSet = new HashSet<>(Set.of("+","-","!","++","--"));
            }
            case "op_mul" -> {
                firstSet = new HashSet<>(Set.of("*","/","%"));
            }
            case "operando" -> {
                firstSet = new HashSet<>(Set.of("nil","true","false","intLiteral","StrLiteral","charLiteral","(","self","id","new"));
            }
            case "N12" -> {
                firstSet = new HashSet<>(Set.of("."));
            }
            case "N12'" -> {
                firstSet = new HashSet<>(Set.of("lambda","."));
            }
            case "literal" -> {
                firstSet = new HashSet<>(Set.of("nil","true","false","intLiteral","StrLiteral","charLiteral"));
            }
            case "primario" -> {
                firstSet = new HashSet<>(Set.of("(","self","id","idStruct","new"));
            }
            case "expresion_parentizada" -> {
                firstSet = new HashSet<>(Set.of("("));
            }
            case "acceso_self" -> {
                firstSet = new HashSet<>(Set.of("self"));
            }
            case "acceso_var" -> {
                firstSet = new HashSet<>(Set.of("id"));
            }
            case "acceso_var'" -> {
                firstSet = new HashSet<>(Set.of("lambda","[", "."));
            }
            case "llamada_metodo" -> {
                firstSet = new HashSet<>(Set.of("id"));
            }
            case "llamada_metodo'", "argumentos_actuales" -> {
                firstSet = new HashSet<>(Set.of("("));
            }
            case "llamada_metodo_estatico" -> {
                firstSet = new HashSet<>(Set.of("idStruct"));
            }
            case "llamada_constructor" -> {
                firstSet = new HashSet<>(Set.of("new"));
            }
            case "llamada_constructor'" -> {
                firstSet = new HashSet<>(Set.of("idStruct","Str","Bool","Int","Char"));
            }
            case "argumentos_actuales'" -> {
                firstSet = new HashSet<>(Set.of(")","+","-","!","++","--","nil","true","false","intLiteral","StrLiteral","charLiteral","(","self","id","idStruct","new"));
            }
            case "lista_expresiones" -> {
                firstSet = new HashSet<>(Set.of("+","-","!","++","--","nil","true","false","intLiteral","StrLiteral","charLiteral","(","self","id","idStruct","new"));
            }
            case "lista_expresiones'" -> {
                firstSet = new HashSet<>(Set.of(",","lambda"));
            }
            case "llamada_metodo_encadenado", "acceso_variable_encadenado" -> {
                firstSet = new HashSet<>(Set.of("id"));
            }
            case "llamada_metodo_encadenado'" -> {
                firstSet = new HashSet<>(Set.of("("));
            }
            case "acceso_variable_encadenado'" -> {
                firstSet = new HashSet<>(Set.of("[",".", "lambda"));
            }
        }
        return firstSet;
    }


    private boolean onFirst(Token token, Set<String> firstSet) {
        return firstSet.contains(token.getLexeme());
    }

    //⟨program⟩ ::= ⟨Lista-Definiciones⟩ ⟨Start⟩ | ⟨Start⟩
    private void program(){
        if (onFirst(actual_token, first("lista_definiciones"))){
            listaDefiniciones();
            start();
        } else {
            start();
        }
    }


    //⟨Start⟩ ::= start ⟨Bloque-Método⟩
    private void start() {
        match("start");
        bloqueMetodo();
    }


    // ⟨Lista-Definiciones⟩ ::= ⟨Struct⟩ ⟨Lista-Definiciones⟩ | ⟨Impl⟩ ⟨Lista-Definiciones⟩ | ⟨Lista-Definiciones⟩'
    private void listaDefiniciones() {
        if (onFirst(actual_token, first("struct"))) {
            struct();
            listaDefiniciones();
        } else if (onFirst(actual_token, first("impl"))) {
            impl();
            listaDefiniciones();
        } else if (onFirst(actual_token, first("lista_definiciones'"))) {
            listaDefinicionesPrima();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }

    // ⟨Lista-Definiciones⟩' ::= ⟨Lista-Definiciones⟩
    private void listaDefinicionesPrima() {
        listaDefiniciones();
    }

    // ⟨Struct⟩ ::= struct idStruct ⟨Struct⟩’
    private void struct() {
        match("struct");
        match("idStruct");
        structPrima();
    }

    // ⟨Struct⟩’ ::= ⟨Herencia⟩ ⟨Struct⟩’’ | ⟨Struct⟩’’
    public void structPrima() {
        if (onFirst(actual_token, first("herencia"))) {
            herencia();
            structPrimaPrima();
        } else if (onFirst(actual_token, first("struct''"))) {
            structPrimaPrima();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }

    //⟨Struct⟩’’ ::= { ⟨Struct⟩’’’
    private void structPrimaPrima() {
        match("{");
        structPrimaPrimaPrima();
    }

    //⟨Struct⟩’’’ ::= N2 } | }
    private void structPrimaPrimaPrima() {
        if (onFirst(actual_token, first("N2"))) {
            N2();
            match("}");
        } else if (onFirst(actual_token, first("}"))) {
            match("}");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }

    //N2 ::= ⟨Atributo⟩ N2’
    private void N2() {
        atributo();
        N2Prima();
    }

    //N2’ ::=  N2 | λ
    private void N2Prima() {
        Set<String> followN2Prima = new HashSet<>(Set.of("}"));
        if (onFirst(actual_token, first("N2"))) {
            N2();
        } else if (followN2Prima.contains(actual_token.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }

    //⟨Impl⟩ ::= impl idStruct { ⟨Impl⟩’
    private void impl() {
        match("impl");
        match("idStruct");
        match("{");
        implPrima();
    }

    //⟨Impl⟩’ ::= N3 } | }
    private void implPrima() {
        if (onFirst(actual_token, first("N3"))) {
            N3();
            match("}");
        } else if (onFirst(actual_token, first("}"))) {
            match("}");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }

    //N3 ::= ⟨Miembro⟩N3’
    private void N3() {
        miembro();
        N3Prima();
    }

    //N3’ ::= N3 | λ
    private void N3Prima() {
        Set<String> followN3Prima = new HashSet<>(Set.of("}"));
        if (onFirst(actual_token, first("N3"))) {
            N3();
        } else if (followN3Prima.contains(actual_token.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }

    //⟨Herencia⟩ ::= : ⟨Tipo⟩
    private void herencia() {
        match(":");
        tipo();
    }

    // ⟨Miembro⟩ ::= ⟨Método⟩ | ⟨Constructor ⟩
    private void miembro() {
        if (onFirst(actual_token, first("metodo"))) {
            metodo();
        } else if (onFirst(actual_token, first("constructor"))) {
            constructor();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }

    // ⟨Constructor ⟩ ::= . ⟨Argumentos-Formales⟩ ⟨Bloque-Método⟩
    private void constructor() {
        match(".");
        argumentosFormales();
        bloqueMetodo();
    }

    // ⟨Atributo⟩ ::= pri ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ; | ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ;
    private void atributo() {
        if (actual_token.getLexeme().equals("pri")) {
            match("pri");
            tipo();
            listaDeclaracionVariables();
            match(";");
        } else if (onFirst(actual_token, first("tipo"))) {
            tipo();
            listaDeclaracionVariables();
            match(";");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Método⟩ ::= st fn idMetAt ⟨Método⟩’ | fn idMetAt ⟨Método⟩’
    private void metodo() {
        if (actual_token.getLexeme().equals("st")) {
            match("st");
            match("fn");
            match("idMetAt");
            metodoPrima();
        } else if (actual_token.getLexeme().equals("fn")) {
            match("fn");
            match("idMetAt");
            metodoPrima();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }

    }

    // ⟨Método⟩’ ::= ⟨Argumentos-Formales⟩ -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩ | -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩
    private void metodoPrima() {
        if (onFirst(actual_token, first("argumentos_formales"))) {
            argumentosFormales();
            match("->");
            tipoMetodo();
            bloqueMetodo();
        } else if (actual_token.getLexeme().equals("->")) {
            match("->");
            tipoMetodo();
            bloqueMetodo();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }

    // ⟨Bloque-Método⟩ ::= {N6 N7} | { N7 } | { N6 } | { }
    // N6 ::= ⟨Decl-Var-Locales⟩N6’
    // N6’ ::=  N6 | λ
    // N7 ::= ⟨Sentencia⟩N7’
    // N7’ ::= N7 | λ
    // ⟨Decl-Var-Locales⟩ ::= ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ;
    // ⟨Lista-Declaración-Variables⟩::= idMetAt ⟨Lista-Declaración-Variables⟩’
    // ⟨Lista-Declaración-Variables⟩’ ::= , ⟨Lista-Declaración-Variables⟩ | λ
    // ⟨Argumentos-Formales⟩ ::= ( ⟨Argumentos-Formales⟩’
    // ⟨Argumentos-Formales⟩’ ::= ⟨Lista-Argumentos-Formales⟩ ) |  )
    // ⟨Lista-Argumentos-Formales⟩ ::= ⟨Argumento-Formal ⟩ ⟨Lista-Argumentos-Formales⟩’
    // ⟨Lista-Argumentos-Formales⟩’ ::= , ⟨Lista-Argumentos-Formales⟩ | λ
    // ⟨Argumento-Formal ⟩ ::= ⟨Tipo⟩ idMetAt
    // ⟨Tipo-Método⟩ ::= ⟨Tipo⟩ | void
    // ⟨Tipo⟩ ::= ⟨Tipo-Primitivo⟩ | ⟨Tipo-Referencia⟩ | ⟨Tipo-Arreglo⟩
    // ⟨Tipo-Primitivo⟩ ::= Str | Bool |Int | Char
    // ⟨Tipo-Referencia⟩ ::= idStruct
    // ⟨Tipo-Arreglo⟩ ::= Array ⟨Tipo-Primitivo⟩
    // ⟨Sentencia⟩ ::= ; | ⟨Asignación⟩; | ⟨Sentencia-Simple⟩; | if (⟨Expresión⟩) ⟨Sentencia⟩ | if (⟨Expresión⟩) ⟨Sentencia⟩ else ⟨Sentencia⟩ | while ( ⟨Expresión⟩ ) ⟨Sentencia⟩ | ⟨Bloque⟩ | ret ⟨Expresión⟩; | ret ;
    // ⟨Bloque⟩ ::= {N9} | { }
    // ⟨Asignación⟩ ::= ⟨AccesoVar-Simple⟩ = ⟨Expresión⟩ | ⟨AccesoSelf-Simple⟩ = ⟨Expresión⟩
    // N9 ::= ⟨Sentencia⟩N9’
    // N9’ ::=  N9 | λ
    // ⟨AccesoVar-Simple⟩ ::= id N10 | id [ ⟨Expresión⟩ ] | id
    // N10 ::= ⟨Encadenado-Simple⟩ N10’
    // N10’ ::= N10 | λ
    // ⟨AccesoSelf-Simple⟩ ::= self N11’
    // N11 ::= ⟨Encadenado-Simple⟩ N11’
    // N11’ ::= N11 | λ
    // ⟨Encadenado-Simple⟩ ::= . id
    // ⟨Sentencia-Simple⟩ ::= (⟨Expresión⟩)
    // ⟨Expresión⟩ ::= ⟨ExpOr ⟩
    // ⟨ExpOr⟩ ::= ⟨ExpAnd⟩⟨ExpOr⟩’
    // ⟨ExpOr⟩’ ::= || ⟨ExpAnd⟩⟨ExpOr⟩’ | λ
    // ⟨ExpAnd⟩ ::= ⟨ExpIgual⟩⟨ExpAnd⟩’
    // ⟨ExpAnd⟩’ ::= && ⟨ExpIgual⟩⟨ExpAnd⟩’ | λ
    // ⟨ExpIgual⟩ ::= ⟨ExpCompuesta⟩⟨ExpIgual⟩’
    // ⟨ExpIgual⟩’::= ⟨OpIgual⟩⟨ExpCompuesta⟩⟨ExpIgual⟩’ | λ
    // ⟨ExpCompuesta⟩ ::= ⟨ExpAd ⟩ ⟨ExpCompuesta⟩’
    // ⟨ExpCompuesta⟩’::= ⟨OpCompuesto⟩ ⟨ExpAd ⟩ | λ
    // ⟨ExpAd⟩ ::= ⟨ExpMul⟩⟨ExpAd⟩’
    // ⟨ExpAd⟩’ ::= ⟨OpAd⟩ ⟨ExpMul⟩⟨ExpAd⟩’ | λ
    // ⟨ExpMul⟩ ::= ⟨ExpUn⟩⟨ExpMul⟩’
    // ⟨ExpMul⟩’ ::= ⟨OpMul⟩⟨ExpUn⟩⟨ExpMul⟩’ | λ
    // ⟨ExpUn⟩ ::= ⟨OpUnario⟩ ⟨ExpUn⟩ | ⟨Operando⟩
    // ⟨OpIgual ⟩ ::= == | !=
    // ⟨OpCompuesto⟩ ::= < | > | <= | >=
    // ⟨OpAd ⟩ ::= + | -
    // ⟨OpUnario⟩ ::= + | - | ! | ++ |--
    // ⟨OpMul ⟩ ::= * | / | %
    // ⟨Operando⟩ ::= ⟨Literal⟩ | ⟨Primario⟩ N12’
    // N12 ::= . ⟨Llamada-Método-Encadenado⟩ | . ⟨Acceso-Variable-Encadenado⟩
    // N12’ ::= N12 | λ
    // ⟨Literal ⟩ ::= nil | true | false | intLiteral | StrLiteral | charLiteral
    // ⟨Primario⟩ ::= ⟨ExpresionParentizada⟩ | ⟨AccesoSelf ⟩ | ⟨AccesoVar ⟩ | ⟨Llamada-Método⟩ | ⟨Llamada-Método-Estático⟩ | ⟨Llamada-Constructor ⟩
    // ⟨ExpresionParentizada⟩ ::= ( ⟨Expresion⟩ ) N12’
    // ⟨AccesoSelf ⟩ ::= self N12’
    // ⟨AccesoVar ⟩ ::= id ⟨AccesoVar⟩’
    // ⟨AccesoVar ⟩’ ::= N12 | [ ⟨Expresión⟩ ] N12 | λ | [ ⟨Expresión⟩ ]
    // ⟨Llamada-Método⟩ ::= id ⟨Llamada-Método⟩’
    // ⟨Llamada-Método⟩’ ::= ⟨Argumentos-Actuales⟩ N12’
    // ⟨Llamada-Método-Estático⟩ ::= idStruct . ⟨Llamada-Método⟩N12’
    // ⟨Llamada-Constructor ⟩ ::= new ⟨Llamada-Constructor ⟩’
    // ⟨Llamada-Constructor ⟩’ ::= idStruct ⟨Argumentos-Actuales⟩ N12’| ⟨Tipo-Primitivo⟩ [ ⟨Expresion⟩ ]
    // ⟨Argumentos-Actuales⟩ ::= ( ⟨Argumentos-Actuales⟩’
    // ⟨Argumentos-Actuales⟩’ ::= ⟨Lista-Expresiones⟩ ) | )
    // ⟨Lista-Expresiones⟩ ::= ⟨Expresión⟩ ⟨Lista-Expresiones⟩’
    // ⟨Lista-Expresiones⟩’ ::= , ⟨Lista-Expresiones⟩ | λ
    // ⟨Llamada-Método-Encadenado⟩ ::= id ⟨Llamada-Método-Encadenado⟩’
    // ⟨Llamada-Método-Encadenado⟩’ ::= ⟨Argumentos-Actuales⟩ N12 | ⟨Argumentos-Actuales⟩
    // ⟨Acceso-Variable-Encadenado⟩ ::= id ⟨Acceso-Variable-Encadenado⟩’
    // ⟨Acceso-Variable-Encadenado⟩’ ::=  [⟨Expresion⟩] N12’ | N12’


}
