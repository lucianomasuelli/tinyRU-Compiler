package tinyru.etapa2;
import tinyru.etapa1.Lexer;
import tinyru.etapa1.Token;
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

    private void first(String noTerminal) {
        Set<String> firstSet;
        switch (noTerminal) {
            case "program" -> {
                firstSet = new HashSet<>(Set.of("struct", "impl", "start"));
            }
            case "start" -> {
                firstSet = new HashSet<>(Set.of("start"));
            }
            case "lista_definiciones" -> {
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
            case "N2", "atributo" -> {
                firstSet = new HashSet<>(Set.of("pri","Str","Bool","Int","Char","idStruct","Array"));
            }
            case "N2'" -> {
                firstSet = new HashSet<>(Set.of("lambda","pri","Str","Bool","Int","Char","idStruct","Array"));
            }
            case "impl" -> {
                firstSet = new HashSet<>(Set.of("impl"));
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

    }

    //⟨program⟩ ::= ⟨Lista-Definiciones⟩ ⟨Start⟩ | ⟨Start⟩
    private void program(){
        if (onFirst(actual_token, first("lista_definiciones"))){
            lista_definiciones();
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


    // ⟨Lista-Definiciones⟩ ::= ⟨Struct⟩ ⟨Lista-Definiciones⟩ | ⟨Impl⟩ ⟨Lista-Definiciones⟩ | ⟨Lista-Definiciones⟩
    // ⟨Struct⟩ ::= struct idStruct ⟨Struct⟩’
    // ⟨Struct⟩’ ::= ⟨Herencia⟩ ⟨Struct⟩’’ | {N2} | { }
    //⟨Struct⟩’’ ::= {N2} | { }
    //N2 ::= ⟨Atributo⟩ N2’
    //N2’ ::=  N2 | λ
    //⟨Impl⟩ ::= impl idStruct {N3} | impl idStruct { }
    //N3 ::= ⟨Miembro⟩N3’
    //N3’ ::= N3 | λ
    //⟨Herencia⟩ ::= : ⟨Tipo⟩
    // ⟨Miembro⟩ ::= ⟨Método⟩ | ⟨Constructor ⟩
    // ⟨Constructor ⟩ ::= . ⟨Argumentos-Formales⟩ ⟨Bloque-Método⟩
    // ⟨Atributo⟩ ::= pri ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ; | ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ;
    // ⟨Método⟩ ::= st fn idMetAt ⟨Argumentos-Formales⟩ -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩ | fn idMetAt ⟨Argumentos-Formales⟩ -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩ | st fn idMetAt -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩ | fn idMetAt ->	 ⟨Tipo-Método⟩ ⟨Bloque-Método⟩
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
