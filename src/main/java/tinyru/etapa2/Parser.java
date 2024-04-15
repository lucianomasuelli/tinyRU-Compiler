package tinyru.etapa2;
import tinyru.etapa1.Lexer;
import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa2.Exceptions.UnexpectedTokenError;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    Lexer lexer;
    Token actualToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void analyze() {
        try {
            actualToken = lexer.nextToken();
            program();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void match(TokenType expected) {
        if (actualToken.getType() == expected) {
            try {
                actualToken = lexer.nextToken();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Syntax error: expected " + expected + " but found " + actualToken.getLexeme());
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
            case "bloque_metodo'" -> {
                firstSet = new HashSet<>(Set.of("}",";","if","while","ret","(","{","Str","Bool","Int","Char","idStruct","Array","id","self"));
            }
            case "bloque_metodo''" -> {
                firstSet = new HashSet<>(Set.of("}",";","if","while","ret","(","{","id","self"));
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
            case "sentencia'" -> {
                firstSet = new HashSet<>(Set.of("else","lambda"));
            }
            case "sentencia''" -> {
                firstSet = new HashSet<>(Set.of(";","+","-","!","++","--","nil","true","false","intLiteral","StrLiteral","charLiteral","(","self","id","idStruct","new"));
            }
            case "bloque" -> {
                firstSet = new HashSet<>(Set.of("{"));
            }
            case "bloque'" -> {
                firstSet = new HashSet<>(Set.of("}",";","if","while","ret","(","{","id","self"));
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
        if (onFirst(actualToken, first("lista_definiciones"))){
            listaDefiniciones();
            start();
        } else if(onFirst(actualToken, first("start"))) {
            start();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
        //Hacer que matchee con EOF
    }


    //⟨Start⟩ ::= start ⟨Bloque-Método⟩
    private void start() {
        match(TokenType.PSTART);
        bloqueMetodo();
    }


    // ⟨Lista-Definiciones⟩ ::= ⟨Struct⟩ ⟨Lista-Definiciones⟩ | ⟨Impl⟩ ⟨Lista-Definiciones⟩ | ⟨Lista-Definiciones⟩'
    private void listaDefiniciones() {
        if (onFirst(actualToken, first("struct"))) {
            struct();
            listaDefiniciones();
        } else if (onFirst(actualToken, first("impl"))) {
            impl();
            listaDefiniciones();
        } else if (onFirst(actualToken, first("lista_definiciones'"))) {
            listaDefinicionesPrima();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Lista-Definiciones⟩' ::= ⟨Lista-Definiciones⟩
    private void listaDefinicionesPrima() {
        listaDefiniciones();
    }

    // ⟨Struct⟩ ::= struct idStruct ⟨Struct⟩’
    private void struct() {
        match(TokenType.PSTRUCT);
        match(TokenType.STRUCTID);
        structPrima();
    }

    // ⟨Struct⟩’ ::= ⟨Herencia⟩ ⟨Struct⟩’’ | ⟨Struct⟩’’
    public void structPrima() {
        if (onFirst(actualToken, first("herencia"))) {
            herencia();
            structPrimaPrima();
        } else if (onFirst(actualToken, first("struct''"))) {
            structPrimaPrima();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    //⟨Struct⟩’’ ::= { ⟨Struct⟩’’’
    private void structPrimaPrima() {
        match(TokenType.LBRACE);
        structPrimaPrimaPrima();
    }

    //⟨Struct⟩’’’ ::= N2 } | }
    private void structPrimaPrimaPrima() {
        if (onFirst(actualToken, first("N2"))) {
            N2();
            match(TokenType.RBRACE);
        } else if (onFirst(actualToken, first("}"))) {
            match(TokenType.RBRACE);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
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
        if (onFirst(actualToken, first("N2"))) {
            N2();
        } else if (followN2Prima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    //⟨Impl⟩ ::= impl idStruct { ⟨Impl⟩’
    private void impl() {
        match(TokenType.PIMPL);
        match(TokenType.STRUCTID);
        match(TokenType.LBRACE);
        implPrima();
    }

    //⟨Impl⟩’ ::= N3 } | }
    private void implPrima() {
        if (onFirst(actualToken, first("N3"))) {
            N3();
            match(TokenType.RBRACE);
        } else if (onFirst(actualToken, first("}"))) {
            match(TokenType.RBRACE);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
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
        if (onFirst(actualToken, first("N3"))) {
            N3();
        } else if (followN3Prima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    //⟨Herencia⟩ ::= : ⟨Tipo⟩
    private void herencia() {
        match(TokenType.COLON);
        tipo();
    }

    // ⟨Miembro⟩ ::= ⟨Método⟩ | ⟨Constructor ⟩
    private void miembro() {
        if (onFirst(actualToken, first("metodo"))) {
            metodo();
        } else if (onFirst(actualToken, first("constructor"))) {
            constructor();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Constructor ⟩ ::= . ⟨Argumentos-Formales⟩ ⟨Bloque-Método⟩
    private void constructor() {
        match(TokenType.CONSTRUCT);
        argumentosFormales();
        bloqueMetodo();
    }

    // ⟨Atributo⟩ ::= pri ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ; | ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ;
    private void atributo() {
        if (actualToken.getLexeme().equals("pri")) {
            match(TokenType.PPRI);
            tipo();
            listaDeclaracionVariables();
            match(TokenType.SEMICOLON);
        } else if (onFirst(actualToken, first("tipo"))) {
            tipo();
            listaDeclaracionVariables();
            match(TokenType.SEMICOLON);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Método⟩ ::= st fn idMetAt ⟨Método⟩’ | fn idMetAt ⟨Método⟩’
    private void metodo() {
        if (actualToken.getLexeme().equals("st")) {
            match(TokenType.PST);
            match(TokenType.PFN);
            match(TokenType.ID);
            metodoPrima();
        } else if (actualToken.getLexeme().equals("fn")) {
            match(TokenType.PFN);
            match(TokenType.ID);
            metodoPrima();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }

    }

    // ⟨Método⟩’ ::= ⟨Argumentos-Formales⟩ -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩ | -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩
    private void metodoPrima() {
        if (onFirst(actualToken, first("argumentos_formales"))) {
            argumentosFormales();
            match(TokenType.RETURN_TYPE);
            tipoMetodo();
            bloqueMetodo();
        } else if (actualToken.getLexeme().equals("->")) {
            match(TokenType.RETURN_TYPE);
            tipoMetodo();
            bloqueMetodo();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    //⟨Bloque-Método⟩ ::= { ⟨Bloque-Método⟩’
    private void bloqueMetodo() {
        match(TokenType.LBRACE);
        bloqueMetodoPrima();
    }

    //⟨Bloque-Método⟩’ ::= N6 ⟨Bloque-Método⟩’’ | N7 } | }
    private void bloqueMetodoPrima() {
        if (onFirst(actualToken, first("N6"))) {
            N6();
            bloqueMetodoPrimaPrima();
        } else if (onFirst(actualToken, first("N7"))) {
            N7();
            match(TokenType.RBRACE);
        } else if (actualToken.getLexeme().equals("}")) {
            match(TokenType.RBRACE);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    //⟨Bloque-Método⟩’’ ::= N7 } | }
    private void bloqueMetodoPrimaPrima() {
        if (onFirst(actualToken, first("N7"))) {
            N7();
            match(TokenType.RBRACE);
        } else if (actualToken.getLexeme().equals("}")) {
            match(TokenType.RBRACE);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // N6 ::= ⟨Decl-Var-Locales⟩ N6’
    private void N6() {
        declVarLocales();
        N6Prima();
    }

    // N6’ ::=  N6 | λ
    private void N6Prima() {
        Set<String> followN6Prima = new HashSet<>(Set.of("}",";","if","while","ret","(","{","id","self"));
        if (onFirst(actualToken, first("N6"))) {
            N6();
        } else if (followN6Prima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // N7 ::= ⟨Sentencia⟩N7’
    private void N7() {
        sentencia();
        N7Prima();
    }

    // N7’ ::= N7 | λ
    private void N7Prima() {
        Set<String> followN7Prima = new HashSet<>(Set.of("}"));
        if (onFirst(actualToken, first("N7"))) {
            N7();
        } else if (followN7Prima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Decl-Var-Locales⟩ ::= ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ;
    private void declVarLocales() {
        tipo();
        listaDeclaracionVariables();
        match(TokenType.SEMICOLON);
    }
    // ⟨Lista-Declaración-Variables⟩::= idMetAt ⟨Lista-Declaración-Variables⟩’
    private void listaDeclaracionVariables() {
        match(TokenType.ID);
        listaDeclaracionVariablesPrima();
    }

    // ⟨Lista-Declaración-Variables⟩’ ::= , ⟨Lista-Declaración-Variables⟩ | λ
    private void listaDeclaracionVariablesPrima() {
        Set<String> followListaDeclaracionVariablesPrima = new HashSet<>(Set.of(";"));
        if (actualToken.getLexeme().equals(",")) {
            match(TokenType.COMMA);
            listaDeclaracionVariables();
        } else if (followListaDeclaracionVariablesPrima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Argumentos-Formales⟩ ::= ( ⟨Argumentos-Formales⟩’
    private void argumentosFormales() {
        match(TokenType.LPAREN);
        argumentosFormalesPrima();
    }

    // ⟨Argumentos-Formales⟩’ ::= ⟨Lista-Argumentos-Formales⟩ ) |  )
    private void argumentosFormalesPrima() {
        if (onFirst(actualToken, first("lista_argumentos_formales"))) {
            listaArgumentosFormales();
            match(TokenType.RPAREN);
        } else if (actualToken.getLexeme().equals(")")) {
            match(TokenType.RPAREN);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Lista-Argumentos-Formales⟩ ::= ⟨Argumento-Formal ⟩ ⟨Lista-Argumentos-Formales⟩’
    private void listaArgumentosFormales() {
        argumentoFormal();
        listaArgumentosFormalesPrima();
    }

    // ⟨Lista-Argumentos-Formales⟩’ ::= , ⟨Lista-Argumentos-Formales⟩ | λ
    private void listaArgumentosFormalesPrima() {
        Set<String> followListaArgumentosFormalesPrima = new HashSet<>(Set.of(")"));
        if (actualToken.getLexeme().equals(",")) {
            match(TokenType.COMMA);
            listaArgumentosFormales();
        } else if (followListaArgumentosFormalesPrima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Argumento-Formal ⟩ ::= ⟨Tipo⟩ idMetAt
    private void argumentoFormal() {
        tipo();
        match(TokenType.ID);
    }

    // ⟨Tipo-Método⟩ ::= ⟨Tipo⟩ | void
    private void tipoMetodo() {
        if (onFirst(actualToken, first("tipo"))) {
            tipo();
        } else if (actualToken.getLexeme().equals("void")) {
            match(TokenType.PVOID);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Tipo⟩ ::= ⟨Tipo-Primitivo⟩ | ⟨Tipo-Referencia⟩ | ⟨Tipo-Arreglo⟩
    private void tipo() {
        if (onFirst(actualToken, first("tipo_primitivo"))) {
            tipoPrimitivo();
        } else if (onFirst(actualToken, first("tipo_referencia"))) {
            tipoReferencia();
        } else if (onFirst(actualToken, first("tipo_arreglo"))) {
            tipoArreglo();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Tipo-Primitivo⟩ ::= Str | Bool |Int | Char
    private void tipoPrimitivo() {
        if (actualToken.getLexeme().equals("Str")) {
            match(TokenType.PSTR);
        } else if (actualToken.getLexeme().equals("Bool")) {
            match(TokenType.PBOOL);
        } else if (actualToken.getLexeme().equals("Int")) {
            match(TokenType.PINT);
        } else if (actualToken.getLexeme().equals("Char")) {
            match(TokenType.PCHAR);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Tipo-Referencia⟩ ::= idStruct
    private void tipoReferencia() {
        match(TokenType.STRUCTID);
    }

    // ⟨Tipo-Arreglo⟩ ::= Array ⟨Tipo-Primitivo⟩
    private void tipoArreglo() {
        match(TokenType.PARRAY);
        tipoPrimitivo();
    }

    // ⟨Sentencia⟩ ::= ; | ⟨Asignación⟩ ; | ⟨Sentencia-Simple⟩ ; | if (⟨Expresión⟩) ⟨Sentencia⟩ ⟨Sentencia⟩’ | while ( ⟨Expresión⟩ ) ⟨Sentencia⟩ | ⟨Bloque⟩ | ret ⟨Sentencia⟩’’
private void sentencia() {
        if (actualToken.getLexeme().equals(";")) {
            match(TokenType.SEMICOLON);
        } else if (onFirst(actualToken, first("asignacion"))) {
            asignacion();
            match(TokenType.SEMICOLON);
        } else if (onFirst(actualToken, first("sentencia_simple"))) {
            sentenciaSimple();
            match(TokenType.SEMICOLON);
        } else if (actualToken.getLexeme().equals("if")) {
            match(TokenType.PIF);
            match(TokenType.LPAREN);
            expresion();
            match(TokenType.RPAREN);
            sentencia();
            sentenciaPrima();
        } else if (actualToken.getLexeme().equals("while")) {
            match(TokenType.PWHILE);
            match(TokenType.LPAREN);
            expresion();
            match(TokenType.RPAREN);
            sentencia();
        } else if (onFirst(actualToken, first("bloque"))) {
            bloque();
        } else if (actualToken.getLexeme().equals("ret")) {
            match(TokenType.PRET);
            sentenciaPrimaPrima();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Sentencia⟩’ ::= else ⟨Sentencia⟩ | λ
     private void sentenciaPrima() {
        Set<String> followSentenciaPrima = new HashSet<>(Set.of("}",";","if","while","ret","(","{","id","self","else"));
        if (actualToken.getLexeme().equals("else")) {
            match(TokenType.PELSE);
            sentencia();
        } else if(followSentenciaPrima.contains(actualToken.getLexeme())) {
            // lambda
        }
        else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
     }

    // ⟨Sentencia⟩’’ ::= ⟨Expresión⟩ ; | ;
    private void sentenciaPrimaPrima() {
        if (onFirst(actualToken, first("expresion"))) {
            expresion();
            match(TokenType.SEMICOLON);
        } else if (actualToken.getLexeme().equals(";")) {
            match(TokenType.SEMICOLON);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Bloque⟩ ::= { ⟨Bloque⟩’
    private void bloque() {
        match(TokenType.LBRACE);
        bloquePrima();
    }

    // ⟨Bloque⟩’ ::= N9 } | }
    private void bloquePrima() {
        if (onFirst(actualToken, first("N9"))) {
            N9();
            match(TokenType.RBRACE);
        } else if (actualToken.getLexeme().equals("}")) {
            match(TokenType.RBRACE);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Asignación⟩ ::= ⟨AccesoVar-Simple⟩ = ⟨Expresión⟩ | ⟨AccesoSelf-Simple⟩ = ⟨Expresión⟩
    private void asignacion() {
        if (onFirst(actualToken, first("acceso_var_simple"))) {
            accesoVarSimple();
            match(TokenType.ASSIGN);
            expresion();
        } else if (onFirst(actualToken, first("acceso_self_simple"))) {
            accesoSelfSimple();
            match(TokenType.ASSIGN);
            expresion();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // N9 ::= ⟨Sentencia⟩N9’
    private void N9() {
        sentencia();
        N9Prima();
    }

    // N9’ ::=  N9 | λ
    private void N9Prima() {
        Set<String> followN9Prima = new HashSet<>(Set.of("}"));
        if (onFirst(actualToken, first("N9"))) {
            N9();
        } else if (followN9Prima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨AccesoVar-Simple⟩ ::= id ⟨AccesoVar-Simple⟩’
    private void accesoVarSimple() {
        match(TokenType.ID);
        accesoVarSimplePrima();
    }

    // ⟨AccesoVar-Simple⟩’ ::= N10 | [ ⟨Expresión⟩ ] | λ
    private void accesoVarSimplePrima() {
        Set<String> followAccesoVarSimplePrima = new HashSet<>(Set.of("="));
        if (onFirst(actualToken, first("N10"))) {
            N10();
        } else if (actualToken.getLexeme().equals("[")) {
            match(TokenType.LBRACKET);
            expresion();
            match(TokenType.RBRACKET);
        } else if(followAccesoVarSimplePrima.contains(actualToken.getLexeme())) {
            // lambda
        }
        else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // N10 ::= ⟨Encadenado-Simple⟩ N10’
    private void N10() {
        encadenadoSimple();
        N10Prima();
    }

    // N10’ ::= N10 | λ
    private void N10Prima() {
        Set<String> followN10Prima = new HashSet<>(Set.of("="));
        if (onFirst(actualToken, first("N10"))) {
            N10();
        } else if (followN10Prima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }





    // ⟨AccesoSelf-Simple⟩ ::= self N11’
    private void accesoSelfSimple() {
        match("self");
        N11Prima();
    }

    // N11 ::= ⟨Encadenado-Simple⟩ N11’
    private void N11(){
        encadenadoSimple();
        N11Prima();
    }
    // N11’ ::= N11 | λ
    private void N11Prima(){
        Set<String> followN11Prima = new HashSet<>(Set.of("="));
        if (onFirst(actual_token, first("N11"))) {
            N11();
        } else if (followN11Prima.contains(actual_token.getLexeme())) {
            // lambda
            return;
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Encadenado-Simple⟩ ::= . id
    private void encadenadoSimple(){
        match(".");
        match("id");
    }
    // ⟨Sentencia-Simple⟩ ::= (⟨Expresión⟩)
    private void sentenciaSimple(){
        match("(");
        expresion();
        match(")");
    }
    // ⟨Expresión⟩ ::= ⟨ExpOr ⟩
    private void expresion(){
        expOr();
    }

    // ⟨ExpOr⟩ ::= ⟨ExpAnd⟩⟨ExpOr⟩’
    private void expOr(){
        expAnd();
        expOrPrima();
    }

    // ⟨ExpOr⟩’ ::= || ⟨ExpAnd⟩⟨ExpOr⟩’ | λ
    private void expOrPrima(){
        Set<String> followExpOrPrima = new HashSet<>(Set.of(")", ";", "]", ","));
        if (actual_token.getLexeme().equals("||")){
            match("||");
            expAnd();
            expOrPrima();
        } else {
            if (followExpOrPrima.contains(actual_token.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
            }
        }
    }
    // ⟨ExpAnd⟩ ::= ⟨ExpIgual⟩⟨ExpAnd⟩’
    private void expAnd(){
        expIgual();
        expAndPrima();
    }
    // ⟨ExpAnd⟩’ ::= && ⟨ExpIgual⟩⟨ExpAnd⟩’ | λ
    private void expAndPrima() {
        Set<String> followExpAndPrima = new HashSet<>(Set.of("||", ")", ";", "]", ","));
        if (actual_token.getLexeme().equals("&&")){
            match("&&");
            expIgual();
            expAndPrima();
        } else {
            if (followExpAndPrima.contains(actual_token.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
            }
        }
    }
    // ⟨ExpIgual⟩ ::= ⟨ExpCompuesta⟩⟨ExpIgual⟩’
    private void expIgual(){
        expCompuesta();
        expIgualPrima();
    }
    // ⟨ExpIgual⟩’::= ⟨OpIgual⟩⟨ExpCompuesta⟩⟨ExpIgual⟩’ | λ
    private void expIgualPrima() {
        Set<String> followExpIgualPrima = new HashSet<>(Set.of("&&", "||", ")", ";", "]", ","));
        if ( onFirst(actual_token, first("op_igual"))){
            opIgual();
            expCompuesta();
            expIgualPrima();
        } else {
            if (followExpIgualPrima.contains(actual_token.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
            }
        }
    }
    // ⟨ExpCompuesta⟩ ::= ⟨ExpAd ⟩ ⟨ExpCompuesta⟩’
    private void expCompuesta(){
        expAd();
        expCompuestaPrima();
    }
    // ⟨ExpCompuesta⟩’::= ⟨OpCompuesto⟩ ⟨ExpAd ⟩ | λ
    private void expCompuestaPrima() {
        Set<String> followExpCompuestaPrima = new HashSet<>(Set.of("&&", "||", ")", ";", "]", "==", "!=", ","));
        if (onFirst(actual_token, first("op_compuesto"))){
            opCompuesto();
            expAd();
        } else {
            if (followExpCompuestaPrima.contains(actual_token.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
            }
        }

    }
    // ⟨ExpAd⟩ ::= ⟨ExpMul⟩⟨ExpAd⟩’
    private void expAd(){
        expMul();
        expAdPrima();
    }
    // ⟨ExpAd⟩’ ::= ⟨OpAd⟩ ⟨ExpMul⟩⟨ExpAd⟩’ | λ
    // follow = {&&,||,),;,],==,!=,<,>,<=,>=,,}
    private void expAdPrima() {
        Set<String> followExpAdPrima = new HashSet<>(Set.of("&&", "||", ")", ";", "]", "==", "!=", "<", ">", "<=", ">=", ","));
        if (onFirst(actual_token, first("op_ad"))){
            opAd();
            expMul();
            expAdPrima();
        } else {
            if (followExpAdPrima.contains(actual_token.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
            }
        }
    }
    // ⟨ExpMul⟩ ::= ⟨ExpUn⟩⟨ExpMul⟩’
    private void expMul(){
        expUn();
        expMulPrima();
    }
    // ⟨ExpMul⟩’ ::= ⟨OpMul⟩⟨ExpUn⟩⟨ExpMul⟩’ | λ
    private void expMulPrima() {
        Set<String> followExpMulPrima = new HashSet<>(Set.of("&&", "||", ")", ";", "]", "==", "!=", "<", ">", "<=", ">=", "+", "-", ","));
        if (onFirst(actual_token, first("op_mul"))){
            opMul();
            expUn();
            expMulPrima();
        } else {
            if (followExpMulPrima.contains(actual_token.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
            }
        }
    }
    // ⟨ExpUn⟩ ::= ⟨OpUnario⟩ ⟨ExpUn⟩ | ⟨Operando⟩
    private void expUn(){
        if (onFirst(actual_token, first("opUnario"))){
            opUnario();
            expUn();
        } else if (onFirst(actual_token, first("operando"))){
            operando();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨OpIgual ⟩ ::= == | !=
    private void opIgual(){
        if (actual_token.getLexeme().equals("==")){
            match("==");
        } else if (actual_token.getLexeme().equals("!=")){
            match("!=");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨OpCompuesto⟩ ::= < | > | <= | >=
    private void opCompuesto(){
        if (actual_token.getLexeme().equals("<")){
            match("<")
        } else if (actual_token.getLexeme().equals(">")){
            match(">");
        } else if (actual_token.getLexeme().equals("<=")){
            match("<=");
        } else if (actual_token.getLexeme().equals(">=")){
            match(">=");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨OpAd ⟩ ::= + | -
    private void opAd(){
        if (actual_token.getLexeme().equals("+")){
            match("+");
        } else if (actual_token.getLexeme().equals("-")){
            match("-");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨OpUnario⟩ ::= + | - | ! | ++ |--
    private void opUnario(){
        if (actual_token.getLexeme().equals("+")){
            match("+");
        } else if (actual_token.getLexeme().equals("-")){
            match("-");
        } else if (actual_token.getLexeme().equals("!")){
            match("!");
        } else if (actual_token.getLexeme().equals("++")){
            match("++");
        } else if (actual_token.getLexeme().equals("--")){
            match("--");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨OpMul ⟩ ::= * | / | %
    private void opMul(){
        if (actual_token.getLexeme().equals("*")){
            match("*");
        } else if (actual_token.getLexeme().equals("/")){
            match("/");
        } else if (actual_token.getLexeme().equals("%")){
            match("%");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Operando⟩ ::= ⟨Literal⟩ | ⟨Primario⟩ N12’
    private void operando(){
        if (onFirst(actual_token, first("literal"))){
            literal();
        } else if (onFirst(actual_token, first("primario"))){
            primario();
            N12Prima();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // N12 ::= . ⟨Llamada-Método-Encadenado⟩ | . ⟨Acceso-Variable-Encadenado⟩
    private void N12(){
        if (actual_token.getLexeme().equals(".")){
            match(".");
            if (onFirst(actual_token, first("llamada_metodo_encadenado"))){
                llamadaMetodoEncadenado();
            } else if (onFirst(actual_token, first("acceso_variable_encadenado"))){
                accesoVariableEncadenado();
            } else {
                throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
            }
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // N12’ ::= N12 | λ
    // follow = {&&,||,),;,],==,!=,<,>,<=,>=,+,-,*,/,%,.,,}
    private void N12Prima(){
        Set<String> followN12Prima = new HashSet<>(Set.of("&&", "||", ")", ";", "]", "==", "!=", "<", ">", "<=", ">=", "+", "-", "*", "/", "%", ".", ","));
        if (onFirst(actual_token, first("N12"))){
            N12();
        } else if (followN12Prima.contains(actual_token.getLexeme())){
            // lambda
            return;
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Literal ⟩ ::= nil | true | false | intLiteral | StrLiteral | charLiteral
    private void literal(){
        if (actual_token.getLexeme().equals("nil")){
            match("nil");
        } else if (actual_token.getLexeme().equals("true")){
            match("true");
        } else if (actual_token.getLexeme().equals("false")){
            match("false");
        } else if (actual_token.getType().equals("NUM")){
            match("intLiteral");
        } else if (actual_token.getType().equals("STRING")){
            match("StrLiteral");
        } else if (actual_token.getType().equals("CHAR")){
            match("charLiteral");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Primario⟩ ::= ⟨ExpresionParentizada⟩ | ⟨AccesoSelf ⟩ | ⟨AccesoVar ⟩ | ⟨Llamada-Método⟩ | ⟨Llamada-Método-Estático⟩ | ⟨Llamada-Constructor ⟩
    private void primario(){
        if (onFirst(actual_token, first("expresion_parentizada"))){
            expresionParentizada();
        } else if (onFirst(actual_token, first("acceso_self"))){
            accesoSelf();
        } else if (onFirst(actual_token, first("acceso_var"))){
            accesoVar();
        } else if (onFirst(actual_token, first("llamada_metodo"))){
            llamadaMetodo();
        } else if (onFirst(actual_token, first("llamada_metodo_estatico"))){
            llamadaMetodoEstatico();
        } else if (onFirst(actual_token, first("llamada_constructor"))){
            llamadaConstructor();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨ExpresionParentizada⟩ ::= ( ⟨Expresion⟩ ) N12’
    private void expresionParentizada(){
        match("(");
        expresion();
        match(")");
        N12Prima();
    }
    // ⟨AccesoSelf ⟩ ::= self N12’
    private void accesoSelf(){
        match("self");
        N12Prima();
    }
    // ⟨AccesoVar ⟩ ::= id ⟨AccesoVar⟩’
    private void accesoVar(){
        match("id");
        accesoVarPrima();
    }
    // ⟨AccesoVar ⟩’ ::= N12 | [ ⟨Expresión⟩ ] N12 | λ | [ ⟨Expresión⟩ ]
    // follow  = {.,&&,||,),;,],==,!=,<,>,<=,>=,+,-,*,/,%,,}
    private void accesoVarPrima(){
        Set<String> followAccesoVarPrima = new HashSet<>(Set.of("&&", "||", ")", ";", "]", "==", "!=", "<", ">", "<=", ">=", "+", "-", "*", "/", "%", ",", "."));
        if (actual_token.getLexeme().equals("[")){
            match("[");
            expresion();
            match("]");
            if (onFirst(actual_token, first("N12"))){
                N12();
            } else if (followAccesoVarPrima.contains(actual_token.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
            }
        } else if (onFirst(actual_token, first("N12"))){
            N12();
        } else if (followAccesoVarPrima.contains(actual_token.getLexeme())){
            // lambda
            return;
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Llamada-Método⟩ ::= id ⟨Llamada-Método⟩’
    private void llamadaMetodo(){
        match("id");
        llamadaMetodoPrima();
    }
    // ⟨Llamada-Método⟩’ ::= ⟨Argumentos-Actuales⟩ N12’
    private void llamadaMetodoPrima(){
        argumentosActuales();
        N12Prima();
    }
    // ⟨Llamada-Método-Estático⟩ ::= idStruct . ⟨Llamada-Método⟩N12’
    private void llamadaMetodoEstatico(){
        match("idStruct");
        match(".");
        llamadaMetodo();
        N12Prima();
    }
    // ⟨Llamada-Constructor ⟩ ::= new ⟨Llamada-Constructor ⟩’
    private void llamadaConstructor(){
        match("new");
        llamadaConstructorPrima();
    }
    // ⟨Llamada-Constructor ⟩’ ::= idStruct ⟨Argumentos-Actuales⟩ N12’| ⟨Tipo-Primitivo⟩ [ ⟨Expresion⟩ ]
    private void llamadaConstructorPrima(){
        if (actual_token.getLexeme().equals("idStruct")){
            match("idStruct");
            argumentosActuales();
            N12Prima();
        } else if (onFirst(actual_token, first("tipoPrimitivo"))){
            tipoPrimitivo();
            match("[");
            expresion();
            match("]");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Argumentos-Actuales⟩ ::= ( ⟨Argumentos-Actuales⟩’
    private void argumentosActuales(){
        match("(");
        argumentosActualesPrima();
    }
    // ⟨Argumentos-Actuales⟩’ ::= ⟨Lista-Expresiones⟩ ) | )
    private void argumentosActualesPrimaa(){
        if (onFirst(actual_token, first("lista_expresiones"))){
            listaExpresiones();
            match(")");
        } else if (actual_token.getLexeme().equals(")")){
            match(")");
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Lista-Expresiones⟩ ::= ⟨Expresión⟩ ⟨Lista-Expresiones⟩’
    private void listaExpresiones(){
        expresion();
        listaExpresionesPrima();
    }
    // ⟨Lista-Expresiones⟩’ ::= , ⟨Lista-Expresiones⟩ | λ
    // follow = {)}
    private void listaExpresionesPrima(){
        Set<String> followListaExpresionesPrima = new HashSet<>(Set.of(")"));
        if (actual_token.getLexeme().equals(",")){
            match(",");
            listaExpresiones();
        } else if (followListaExpresionesPrima.contains(actual_token.getLexeme())){
            // lambda
            return;
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }
    // ⟨Llamada-Método-Encadenado⟩ ::= id ⟨Llamada-Método-Encadenado⟩’
    private void llamadaMetodoEncadenado(){
        match("id");
        llamadaMetodoEncadenadoPrima();
    }
    // ⟨Llamada-Método-Encadenado⟩’ ::= ⟨Argumentos-Actuales⟩ N12 | ⟨Argumentos-Actuales⟩
    private void llamadaMetodoEncadenadoPrima(){
        argumentosActuales();
        N12();
    }
    // ⟨Acceso-Variable-Encadenado⟩ ::= id ⟨Acceso-Variable-Encadenado⟩’
    private void accesoVariableEncadenado(){
        match("id");
        accesoVariableEncadenadoPrima();
    }
    // ⟨Acceso-Variable-Encadenado⟩’ ::=  [⟨Expresion⟩] N12’ | N12’
    private void accesoVariableEncadenadoPrima(){
        if (actual_token.getLexeme().equals("[")){
            match("[");
            expresion();
            match("]");
            N12Prima();
        } else if (onFirst(actual_token, first("N12Prima"))){
            N12Prima();
        } else {
            throw new UnexpectedTokenError(actual_token.getLexeme(), actual_token.getLine(), actual_token.getColumn());
        }
    }


}
