package tinyru.etapa2;
import tinyru.etapa1.Lexer;
import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa2.Exceptions.ParserError;
import tinyru.etapa2.Exceptions.UnexpectedTokenError;
import tinyru.etapa2.Exceptions.WrongTokenError;
import tinyru.etapa3.*;
import tinyru.etapa3.Exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    Lexer lexer;
    Token actualToken;
    SymbolTable symbolTable = new SymbolTable();

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public SymbolTable getSymbolTable(){
        return symbolTable;
    }

    public void analyze() {
        try {
            actualToken = lexer.nextToken();
            program();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SemanticError | ParserError e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private Token match(TokenType expected) {
        Token prevToken;
        if (actualToken.getType() == expected) {
            prevToken = actualToken;
            try {
                actualToken = lexer.nextToken();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new WrongTokenError(actualToken, expected, actualToken.getLine(), actualToken.getColumn());
        }
        return prevToken;
    }

    private Set<TokenType> first(String noTerminal) {
        Set<TokenType> firstSet = new HashSet<>();
        switch (noTerminal) {
            case "program" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTRUCT, TokenType.PIMPL, TokenType.PSTART));
            }
            case "start" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTART));
            }
            case "lista_definiciones" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTRUCT, TokenType.PIMPL));
            }
            case "struct" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTRUCT));
            }
            case "struct'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.COLON, TokenType.LBRACE));
            }
            case "struct''", "bloque_metodo" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LBRACE));
            }
            case "struct'''" -> {
                firstSet = new HashSet<>(Set.of(TokenType.RBRACE, TokenType.PPRI, TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY));
            }
            case "N2", "atributo" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PPRI, TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY));
            }
            case "N2'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.PPRI, TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY));
            }
            case "impl" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PIMPL));
            }
            case "impl'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.RBRACE, TokenType.PST, TokenType.PFN, TokenType.CONSTRUCT));
            }
            case "N3", "miembro" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PST, TokenType.PFN, TokenType.CONSTRUCT));
            }
            case "N3'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.PST, TokenType.PFN, TokenType.CONSTRUCT));
            }
            case "herencia" -> {
                firstSet = new HashSet<>(Set.of(TokenType.COLON));
            }
            case "constructor" -> {
                firstSet = new HashSet<>(Set.of(TokenType.CONSTRUCT));
            }
            case "metodo" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PST, TokenType.PFN));
            }
            case "metodo'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.RETURN_TYPE, TokenType.LPAREN));
            }
            case "bloque_metodo'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.RBRACE, TokenType.SEMICOLON, TokenType.PIF, TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY, TokenType.ID, TokenType.PSELF));
            }
            case "bloque_metodo''" -> {
                firstSet = new HashSet<>(Set.of(TokenType.RBRACE, TokenType.SEMICOLON, TokenType.PIF, TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.ID, TokenType.PSELF));
            }
            case "N6", "decl_var_locales" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY));
            }
            case "N6'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY));
            }
            case "N7", "sentencia" -> {
                firstSet = new HashSet<>(Set.of(TokenType.SEMICOLON, TokenType.PIF, TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.ID, TokenType.PSELF));
            }
            case "N7'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.SEMICOLON, TokenType.PIF, TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.ID, TokenType.PSELF));
            }
            case "lista_declaracion_variables" -> {
                firstSet = new HashSet<>(Set.of(TokenType.ID));
            }
            case "lista_declaracion_variables'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.COMMA, TokenType.LAMBDA));
            }
            case "argumentos_formales" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LPAREN));
            }
            case "argumentos_formales'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY, TokenType.RPAREN));
            }
            case "lista_argumentos_formales" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY));
            }
            case "lista_argumentos_formales'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.COMMA, TokenType.LAMBDA));
            }
            case "argumento_formal" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY));
            }
            case "tipo_metodo" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY, TokenType.PVOID));
            }
            case "tipo" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR, TokenType.STRUCTID, TokenType.PARRAY));
            }
            case "tipo_primitivo" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR));
            }
            case "tipo_referencia" -> {
                firstSet = new HashSet<>(Set.of(TokenType.STRUCTID));
            }
            case "tipo_arreglo" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PARRAY));
            }
            case "sentencia'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PELSE, TokenType.LAMBDA));
            }
            case "sentencia''" -> {
                firstSet = new HashSet<>(Set.of(TokenType.SEMICOLON, TokenType.SUM, TokenType.RESTA, TokenType.NOT, TokenType.INC, TokenType.DEC, TokenType.PNIL, TokenType.PTRUE, TokenType.PFALSE, TokenType.NUM, TokenType.STRING, TokenType.CHAR, TokenType.LPAREN, TokenType.PSELF, TokenType.ID, TokenType.STRUCTID, TokenType.PNEW));
            }
            case "bloque" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LBRACE));
            }
            case "bloque'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.RBRACE, TokenType.SEMICOLON, TokenType.PIF, TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.ID, TokenType.PSELF));
            }
            case "asignacion" -> {
                firstSet = new HashSet<>(Set.of(TokenType.ID, TokenType.PSELF));
            }
            case "N9" -> {
                firstSet = new HashSet<>(Set.of(TokenType.SEMICOLON, TokenType.PIF, TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.ID, TokenType.PSELF));
            }
            case "N9'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.SEMICOLON, TokenType.PIF, TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.ID, TokenType.PSELF));
            }
            case "acceso_var_simple" -> {
                firstSet = new HashSet<>(Set.of(TokenType.ID));
            }
            case "N10" -> {
                firstSet = new HashSet<>(Set.of(TokenType.DOT));
            }
            case "N10'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.DOT));
            }
            case "acceso_self_simple" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSELF));
            }
            case "N11" -> {
                firstSet = new HashSet<>(Set.of(TokenType.DOT));
            }
            case "N11'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.DOT));
            }
            case "encadenado_simple" -> {
                firstSet = new HashSet<>(Set.of(TokenType.DOT));
            }
            case "sentencia_simple" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LPAREN));
            }
            case "expresion", "exp_or", "exp_and", "exp_igual", "exp_compuesta", "exp_ad", "exp_mul", "exp_un" -> {
                firstSet = new HashSet<>(Set.of(TokenType.SUM, TokenType.RESTA, TokenType.NOT, TokenType.INC, TokenType.DEC, TokenType.PNIL, TokenType.PTRUE, TokenType.PFALSE, TokenType.NUM, TokenType.STRING, TokenType.CHAR, TokenType.LPAREN, TokenType.PSELF, TokenType.ID, TokenType.STRUCTID, TokenType.PNEW));
            }
            case "exp_or'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.OR, TokenType.LAMBDA));
            }
            case "exp_and'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.AND, TokenType.LAMBDA));
            }
            case "exp_igual'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.IGUAL, TokenType.DIF, TokenType.LAMBDA));
            }
            case "exp_compuesta'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.MENOR, TokenType.MAYOR, TokenType.MENORIGUAL, TokenType.MAYORIGUAL, TokenType.LAMBDA));
            }
            case "exp_ad'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.SUM, TokenType.RESTA, TokenType.LAMBDA));
            }
            case "exp_mul'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PROD, TokenType.DIV, TokenType.MOD, TokenType.LAMBDA));
            }
            case "op_igual" -> {
                firstSet = new HashSet<>(Set.of(TokenType.IGUAL, TokenType.DIF));
            }
            case "op_compuesto" -> {
                firstSet = new HashSet<>(Set.of(TokenType.MENOR, TokenType.MAYOR, TokenType.MENORIGUAL, TokenType.MAYORIGUAL));
            }
            case "op_ad" -> {
                firstSet = new HashSet<>(Set.of(TokenType.SUM, TokenType.RESTA));
            }
            case "op_unario" -> {
                firstSet = new HashSet<>(Set.of(TokenType.SUM, TokenType.RESTA, TokenType.NOT, TokenType.INC, TokenType.DEC));
            }
            case "op_mul" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PROD, TokenType.DIV, TokenType.MOD));
            }
            case "operando" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PNIL, TokenType.PTRUE, TokenType.PFALSE, TokenType.NUM, TokenType.STRING, TokenType.CHAR, TokenType.LPAREN, TokenType.PSELF, TokenType.ID, TokenType.PNEW, TokenType.STRUCTID));
            }
            case "N12" -> {
                firstSet = new HashSet<>(Set.of(TokenType.DOT));
            }
            case "llamada_metodo_enc_acceso_var_enc" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LPAREN, TokenType.LBRACKET, TokenType.DOT, TokenType.LAMBDA));
            }
            case "N12'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.DOT));
            }
            case "literal" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PNIL, TokenType.PTRUE, TokenType.PFALSE, TokenType.NUM, TokenType.STRING, TokenType.CHAR));
            }
            case "primario" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LPAREN, TokenType.PSELF, TokenType.ID, TokenType.STRUCTID, TokenType.PNEW));
            }
            case "primario'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.ID));
            }
            case "primario''" -> {
                firstSet = new HashSet<>(Set.of(TokenType.DOT, TokenType.LBRACKET, TokenType.LAMBDA, TokenType.LPAREN));
            }
            case "expresion_parentizada" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LPAREN));
            }
            case "acceso_self" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PSELF));
            }
            case "acceso_var" -> {
                firstSet = new HashSet<>(Set.of(TokenType.ID));
            }
            case "acceso_var'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LAMBDA, TokenType.LBRACKET, TokenType.DOT));
            }
            case "llamada_metodo" -> {
                firstSet = new HashSet<>(Set.of(TokenType.ID));
            }
            case "llamada_metodo'", "argumentos_actuales" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LPAREN));
            }
            case "llamada_metodo_estatico" -> {
                firstSet = new HashSet<>(Set.of(TokenType.STRUCTID));
            }
            case "llamada_constructor" -> {
                firstSet = new HashSet<>(Set.of(TokenType.PNEW));
            }
            case "llamada_constructor'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.STRUCTID, TokenType.PSTR, TokenType.PBOOL, TokenType.PINT, TokenType.PCHAR));
            }
            case "argumentos_actuales'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.RPAREN, TokenType.SUM, TokenType.RESTA, TokenType.NOT, TokenType.INC, TokenType.DEC, TokenType.PNIL, TokenType.PTRUE, TokenType.PFALSE, TokenType.NUM, TokenType.STRING, TokenType.CHAR, TokenType.LPAREN, TokenType.PSELF, TokenType.ID, TokenType.PNEW));
            }
            case "lista_expresiones" -> {
                firstSet = new HashSet<>(Set.of(TokenType.SUM, TokenType.RESTA, TokenType.NOT, TokenType.INC, TokenType.DEC, TokenType.PNIL, TokenType.PTRUE, TokenType.PFALSE, TokenType.NUM, TokenType.STRING, TokenType.CHAR, TokenType.LPAREN, TokenType.PSELF, TokenType.ID, TokenType.PNEW,TokenType.STRUCTID));
            }
            case "lista_expresiones'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.COMMA, TokenType.LAMBDA));
            }
            case "llamada_metodo_encadenado", "acceso_variable_encadenado" -> {
                firstSet = new HashSet<>(Set.of(TokenType.ID));
            }
            case "llamada_metodo_encadenado'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LPAREN));
            }
            case "acceso_variable_encadenado'" -> {
                firstSet = new HashSet<>(Set.of(TokenType.LBRACKET, TokenType.DOT, TokenType.LAMBDA));
            }
        }
        return firstSet;
    }


    private boolean onFirst(Token token, Set<TokenType> firstSet) {
        return firstSet.contains(token.getType());
    }


    //⟨program⟩ ::= ⟨Lista-Definiciones⟩ ⟨Start⟩
    private void program(){
        listaDefiniciones();
        start();

        DeclarationCheck declarationCheck = new DeclarationCheck(symbolTable);
        declarationCheck.declarationCheck();
    }


    //⟨Start⟩ ::= start ⟨Bloque-Método⟩
    private void start() {
        match(TokenType.PSTART);
        StartInput start = new StartInput();
        symbolTable.setStart(start);
        bloqueMetodo();
        if (actualToken.getType() != TokenType.EOF) {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }


    // ⟨Lista-Definiciones⟩ ::= ⟨Struct⟩ ⟨Lista-Definiciones⟩ | ⟨Impl⟩ ⟨Lista-Definiciones⟩ | lambda
    private void listaDefiniciones() {
        Set<TokenType> followListaDefiniciones = new HashSet<>(Set.of(TokenType.PSTART));
        if (onFirst(actualToken, first("struct"))) {
            struct();
            listaDefiniciones();
        } else if (onFirst(actualToken, first("impl"))) {
            impl();
            listaDefiniciones();
        } else if (followListaDefiniciones.contains(actualToken.getType())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Struct⟩ ::= struct idStruct ⟨Struct⟩’
    private void struct() {
        match(TokenType.PSTRUCT);
        String name = actualToken.getLexeme();
        Token sructToken = actualToken;
        match(TokenType.STRUCTID);
        StructInput structInput;
        if(!symbolTable.fetchStruct(name)){
            structInput = new StructInput();
            structInput.setIsDeclared(true);

            symbolTable.actualStruct = structInput;
            structInput.setName(name);
            structPrima();
            structInput.setLine(sructToken.getLine());
            structInput.setColumn(sructToken.getColumn());

            structInput.setIsDeclared(true);

            symbolTable.addStruct(name, structInput);
        } else {
            structInput = symbolTable.getStruct(name);
            if(!structInput.getIsDeclared()){
                symbolTable.actualStruct = structInput;
                structPrima();
                structInput.setName(name);
                structInput.setLine(sructToken.getLine());
                structInput.setColumn(sructToken.getColumn());
                structInput.setIsDeclared(true);
            } else {
                throw new StructAlreadyDeclaredError(name, sructToken.getLine(), sructToken.getColumn());
            }
        }


    }

    // ⟨Struct⟩’ ::= ⟨Herencia⟩ ⟨Struct⟩’’ | ⟨Struct⟩’’
    public void structPrima() {
        if (onFirst(actualToken, first("herencia"))) {
            String ancestor = herencia();
            symbolTable.actualStruct.setInheritanceName(ancestor);
            structPrimaPrima();
        } else if (onFirst(actualToken, first("struct''"))) {
            symbolTable.actualStruct.setInheritanceName("Object");
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
        } else if (actualToken.getLexeme().equals("}")) {
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

    //⟨Impl⟩ ::= impl idStruct { N3 }
    private void impl() {
        match(TokenType.PIMPL);

        if(symbolTable.fetchStruct(actualToken.getLexeme())) { // if struct already exists, check if it has impl
            symbolTable.actualStruct = symbolTable.getStruct(actualToken.getLexeme());
            if(symbolTable.actualStruct.getHasImpl()) {
                throw new ImplAlreadyDeclared(symbolTable.actualStruct.getName(), actualToken.getLine(), actualToken.getColumn());
            }
            symbolTable.actualStruct.setHasImpl(true);
        } else { // if struct does not exist, create it
            StructInput struct = new StructInput();
            struct.setName(actualToken.getLexeme());
            struct.setHasImpl(true);
            struct.setIsDeclared(false);

            symbolTable.actualStruct = struct;

            symbolTable.addStruct(struct.getName(), struct);
        }

        match(TokenType.STRUCTID);
        match(TokenType.LBRACE);
        int methodPos = 0;
        N3(methodPos);
        match(TokenType.RBRACE);

        if(!symbolTable.actualStruct.getHasConstructor()) {
            throw new ConstructorNotDeclared(symbolTable.actualStruct.getName(), actualToken.getLine(), actualToken.getColumn());
        }
    }


    //N3 ::= ⟨Miembro⟩N3’
    private void N3(int pos) {
        pos = miembro(pos);
        N3Prima(pos);
    }

    //N3’ ::= N3 | λ
    private void N3Prima(int pos) {
        Set<TokenType> followN3Prima = new HashSet<>(Set.of(TokenType.RBRACE));
        if (onFirst(actualToken, first("N3"))) {
            N3(pos);
        } else if (followN3Prima.contains(actualToken.getType())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    //⟨Herencia⟩ ::= : ⟨Tipo⟩
    private String herencia() {
        HashSet<String> types = new HashSet<>(Set.of("Str", "Bool", "Int", "Char","Array Str", "Array Bool","Array Int", "Array Char"));
        match(TokenType.COLON);
        int line = actualToken.getLine();
        int column = actualToken.getColumn();
        String type = tipo();
        if (types.contains(type)) {
            throw new PrimitiveTypeInheritanceError(actualToken.getLexeme(),type, line, column);
        }
        return type;
    }

    // ⟨Miembro⟩ ::= ⟨Método⟩ | ⟨Constructor ⟩
    private int miembro(int pos) {
        if (onFirst(actualToken, first("metodo"))) {
            metodo(pos);
            pos += 1;
        } else if (onFirst(actualToken, first("constructor"))) {
            constructor();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
        return pos;
    }

    // ⟨Constructor ⟩ ::= . ⟨Argumentos-Formales⟩ ⟨Bloque-Método⟩
    private void constructor() {
        match(TokenType.CONSTRUCT);

        if (symbolTable.actualStruct.getHasConstructor()) {
            throw new ConstructorAlreadyDeclared(symbolTable.actualStruct.getName(), actualToken.getLine(), actualToken.getColumn());
        }

        symbolTable.actualConstructor = new ConstructorInput();
        ArrayList<ParamInput> args = argumentosFormales();
        int pos = 0;
        for(ParamInput arg : args) {
            if(symbolTable.actualConstructor.fetchParameter(arg.getName())){
                throw new ParamAlreadyDeclared(arg.getName(), arg.getLine(), arg.getColumn());
            }

            symbolTable.actualConstructor.addConstructorParam(arg.getName(), arg);
            arg.setPosition(pos);

            pos++;
        }

        bloqueMetodo();
        symbolTable.actualStruct.setHasConstructor(true);
        symbolTable.actualStruct.setConstructor(symbolTable.actualConstructor); // add constructor to struct
    }

    // ⟨Atributo⟩ ::= pri ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ; | ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ;
    private void atributo() {
        ArrayList<Token> atributosDeclarados = new ArrayList<>();
        VarInput v;
        Boolean pri;
        String type;
        if (actualToken.getLexeme().equals("pri")) {
            match(TokenType.PPRI);
            pri = true;
            type = tipo();
            listaDeclaracionVariables(atributosDeclarados);
            match(TokenType.SEMICOLON);

        } else if (onFirst(actualToken, first("tipo"))) {
            type = tipo();
            pri = false;
            listaDeclaracionVariables(atributosDeclarados);
            match(TokenType.SEMICOLON);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
        int pos = symbolTable.actualStruct.getAttributeTable().size();
        for(Token t: atributosDeclarados){
            v = new VarInput(t.getLexeme(),type,pri);
            v.setLine(t.getLine());
            v.setColumn(t.getColumn());
            v.setPosition(pos);
            DeclarationCheck declarationCheck = new DeclarationCheck(symbolTable);
            declarationCheck.varCheck(symbolTable.actualStruct, v);

            symbolTable.actualStruct.addAttribute(t.getLexeme(),v);
            pos = pos + 1;
        }

    }
    // ⟨Método⟩ ::= st fn idMetAt ⟨Argumentos-Formales⟩ -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩
    // | fn idMetAt ⟨Argumentos-Formales⟩ -> ⟨Tipo-Método⟩ ⟨Bloque-Método⟩
    private void metodo(int pos) {
        boolean isStatic = false;
        if (actualToken.getLexeme().equals("st")) {
            match(TokenType.PST);
            isStatic = true;
            metodoPrima(isStatic, pos);
        } else if (actualToken.getLexeme().equals("fn")) {
            metodoPrima(isStatic, pos);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }

    }

    private void metodoPrima(boolean isStatic, int pos) {
        match(TokenType.PFN);
        String name = actualToken.getLexeme();
        Token methodToken = actualToken;
        match(TokenType.ID);
        if(!symbolTable.actualStruct.fetchMethod(name)){
            symbolTable.actualMethod = new MethodInput(name, isStatic);
        } else {
            symbolTable.actualMethod = symbolTable.actualStruct.getMethod(name);
        }
        ArrayList<ParamInput> args = argumentosFormales();
        int paramPos = 0;
        for(ParamInput arg : args) {
            if(symbolTable.actualMethod.fetchParameter(arg.getName())){
                throw new ParamAlreadyDeclared(arg.getName(), arg.getLine(), arg.getColumn());
            }

            symbolTable.actualMethod.addParameter(arg.getName(), arg);
            arg.setPosition(paramPos);

            paramPos++;
        }

        match(TokenType.RETURN_TYPE);
        String type = tipoMetodo();
        symbolTable.actualMethod.setReturnType(type);
        bloqueMetodo();
        symbolTable.actualMethod.setPosition(pos);
        symbolTable.actualMethod.setLine(methodToken.getLine());
        symbolTable.actualMethod.setColumn(methodToken.getColumn());

        DeclarationCheck declarationCheck = new DeclarationCheck(symbolTable);
        declarationCheck.methodCheck(symbolTable.actualStruct, symbolTable.actualMethod);

        symbolTable.actualStruct.addMethod(name, symbolTable.actualMethod);
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
    // follow = { },;,if,while,ret,(,{,id,self }
    private void N6Prima() {
        Set<TokenType> followN6Prima = new HashSet<>(Set.of(TokenType.RBRACE,TokenType.SEMICOLON, TokenType.PIF, TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.ID, TokenType.PSELF));
        if (onFirst(actualToken, first("N6"))) {
            N6();
        } else if (followN6Prima.contains(actualToken.getType())) {
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
        Set<TokenType> followN7Prima = new HashSet<>(Set.of(TokenType.RBRACE));
        if (onFirst(actualToken, first("N7"))) {
            N7();
        } else if (followN7Prima.contains(actualToken.getType())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Decl-Var-Locales⟩ ::= ⟨Tipo⟩ ⟨Lista-Declaración-Variables⟩ ;
    private void declVarLocales() {
        ArrayList<Token> declaredAttributes = new ArrayList<>();
        String type = tipo();
        listaDeclaracionVariables(declaredAttributes);
        match(TokenType.SEMICOLON);
        for(Token t: declaredAttributes){
            VarInput v = new VarInput(t.getLexeme(), type,false);
            symbolTable.actualMethod.addLocalVar(t.getLexeme(),v);
            if(symbolTable.getStart() != null) {
                if (symbolTable.getStart().getAttributeTable().containsKey(t.getLexeme())) {
                    throw new VarAlreadyDeclaredError(t.getLexeme(), t.getLine(), t.getColumn());
                }

                symbolTable.getStart().addAttribute(t.getLexeme(), v);
            }
        }
    }
    // ⟨Lista-Declaración-Variables⟩::= idMetAt ⟨Lista-Declaración-Variables⟩’
    private void listaDeclaracionVariables(ArrayList<Token> atributosDeclarados) {
        Token token = match(TokenType.ID);
        atributosDeclarados.add(token);
        listaDeclaracionVariablesPrima(atributosDeclarados);
    }

    // ⟨Lista-Declaración-Variables⟩’ ::= , ⟨Lista-Declaración-Variables⟩ | λ
    private void listaDeclaracionVariablesPrima(ArrayList<Token> atributosDeclarados) {
        Set<String> followListaDeclaracionVariablesPrima = new HashSet<>(Set.of(";"));
        if (actualToken.getLexeme().equals(",")) {
            match(TokenType.COMMA);
            listaDeclaracionVariables(atributosDeclarados);
        } else if (followListaDeclaracionVariablesPrima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Argumentos-Formales⟩ ::= ( ⟨Argumentos-Formales⟩’
    private ArrayList<ParamInput> argumentosFormales() {
        match(TokenType.LPAREN);
        return argumentosFormalesPrima();
    }

    // ⟨Argumentos-Formales⟩’ ::= ⟨Lista-Argumentos-Formales⟩ ) |  )
    private ArrayList<ParamInput> argumentosFormalesPrima() {
        ArrayList<ParamInput> args = new ArrayList<>();
        if (onFirst(actualToken, first("lista_argumentos_formales"))) {
            listaArgumentosFormales(args);
            match(TokenType.RPAREN);
        } else if (actualToken.getLexeme().equals(")")) {
            match(TokenType.RPAREN);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }

        return args;
    }

    // ⟨Lista-Argumentos-Formales⟩ ::= ⟨Argumento-Formal ⟩ ⟨Lista-Argumentos-Formales⟩’
    private void listaArgumentosFormales(ArrayList<ParamInput> args) {
        argumentoFormal(args);
        listaArgumentosFormalesPrima(args);
    }

    // ⟨Lista-Argumentos-Formales⟩’ ::= , ⟨Lista-Argumentos-Formales⟩ | λ
    private void listaArgumentosFormalesPrima(ArrayList<ParamInput> args) {
        Set<String> followListaArgumentosFormalesPrima = new HashSet<>(Set.of(")"));
        if (actualToken.getLexeme().equals(",")) {
            match(TokenType.COMMA);
            listaArgumentosFormales(args);
        } else if (followListaArgumentosFormalesPrima.contains(actualToken.getLexeme())) {
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }

    // ⟨Argumento-Formal ⟩ ::= ⟨Tipo⟩ idMetAt
    private void argumentoFormal(ArrayList<ParamInput> args) {
        String type = tipo();
        String name = actualToken.getLexeme();
        Token argToken = actualToken;
        match(TokenType.ID);
        ParamInput p = new ParamInput(name, type);
        p.setLine(argToken.getLine());
        p.setColumn(argToken.getColumn());
        args.add(p);
    }

    // ⟨Tipo-Método⟩ ::= ⟨Tipo⟩ | void
    private String tipoMetodo() {
        String type;
        if (onFirst(actualToken, first("tipo"))) {
            type = tipo();
        } else if (actualToken.getLexeme().equals("void")) {
            match(TokenType.PVOID);
            type = "void";
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
        return type;
    }

    // ⟨Tipo⟩ ::= ⟨Tipo-Primitivo⟩ | ⟨Tipo-Referencia⟩ | ⟨Tipo-Arreglo⟩
    private String tipo() {
        String type;
        if (onFirst(actualToken, first("tipo_primitivo"))) {
            type = tipoPrimitivo();
        } else if (onFirst(actualToken, first("tipo_referencia"))) {
            type = tipoReferencia();
        } else if (onFirst(actualToken, first("tipo_arreglo"))) {
            type = tipoArreglo();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
        return type;
    }

    // ⟨Tipo-Primitivo⟩ ::= Str | Bool |Int | Char
    private String tipoPrimitivo() {
        String type = actualToken.getLexeme();
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
        return type;
    }

    // ⟨Tipo-Referencia⟩ ::= idStruct
    private String tipoReferencia() {
        String type = actualToken.getLexeme();
        match(TokenType.STRUCTID);
        return type;
    }

    // ⟨Tipo-Arreglo⟩ ::= Array ⟨Tipo-Primitivo⟩
    private String tipoArreglo() {
        match(TokenType.PARRAY);
        return "Array " + tipoPrimitivo();
    }

    // ⟨Sentencia⟩ ::= ; | ⟨Asignación⟩ ; | ⟨Sentencia-Simple⟩ ; | if (⟨Expresión⟩) ⟨Sentencia⟩ ⟨Sentencia⟩’ | while ( ⟨Expresión⟩ ) ⟨Sentencia⟩ | ⟨Bloque⟩ | ret ⟨Sentencia⟩’’
    private void sentencia() {
        if (actualToken.getLexeme().equals(";")) {
            match(TokenType.SEMICOLON);
        } else if (onFirst(actualToken, first("asignacion"))){
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
    // follow = "}",";","if","while","ret","(","{","id","self","else"
     private void sentenciaPrima() {
        Set<TokenType> followSentenciaPrima = new HashSet<>(Set.of(TokenType.RBRACE,TokenType.SEMICOLON, TokenType.PIF,
                TokenType.PWHILE, TokenType.PRET, TokenType.LPAREN, TokenType.LBRACE, TokenType.ID, TokenType.PSELF, TokenType.PELSE));
        if (actualToken.getLexeme().equals("else")) {
            match(TokenType.PELSE);
            sentencia();
        } else if(followSentenciaPrima.contains(actualToken.getType())) {
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
        if (onFirst(actualToken, first("acceso_var_simple"))){
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
        match(TokenType.PSELF);
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
        if (onFirst(actualToken, first("N11"))) {
            N11();
        } else if (followN11Prima.contains(actualToken.getLexeme())) {
            // lambda
            return;
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Encadenado-Simple⟩ ::= . id
    private void encadenadoSimple(){
        match(TokenType.DOT);
        match(TokenType.ID);
    }
    // ⟨Sentencia-Simple⟩ ::= (⟨Expresión⟩)
    private void sentenciaSimple(){
        match(TokenType.LPAREN);
        expresion();
        match(TokenType.RPAREN);
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
        if (actualToken.getLexeme().equals("||")){
            match(TokenType.OR);
            expAnd();
            expOrPrima();
        } else {
            if (followExpOrPrima.contains(actualToken.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
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
        if (actualToken.getLexeme().equals("&&")){
            match(TokenType.AND);
            expIgual();
            expAndPrima();
        } else {
            if (followExpAndPrima.contains(actualToken.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
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
        if ( onFirst(actualToken, first("op_igual"))){
            opIgual();
            expCompuesta();
            expIgualPrima();
        } else {
            if (followExpIgualPrima.contains(actualToken.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
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
        if (onFirst(actualToken, first("op_compuesto"))){
            opCompuesto();
            expAd();
        } else {
            if (followExpCompuestaPrima.contains(actualToken.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
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
        if (onFirst(actualToken, first("op_ad"))){
            opAd();
            expMul();
            expAdPrima();
        } else {
            if (followExpAdPrima.contains(actualToken.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
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
        if (onFirst(actualToken, first("op_mul"))){
            opMul();
            expUn();
            expMulPrima();
        } else {
            if (followExpMulPrima.contains(actualToken.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
            }
        }
    }
    // ⟨ExpUn⟩ ::= ⟨OpUnario⟩ ⟨ExpUn⟩ | ⟨Operando⟩
    private void expUn(){
        if (onFirst(actualToken, first("op_unario"))){
            opUnario();
            expUn();
        } else if (onFirst(actualToken, first("operando"))){
            operando();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨OpIgual ⟩ ::= == | !=
    private void opIgual(){
        if (actualToken.getLexeme().equals("==")){
            match(TokenType.IGUAL);
        } else if (actualToken.getLexeme().equals("!=")){
            match(TokenType.DIF);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨OpCompuesto⟩ ::= < | > | <= | >=
    private void opCompuesto(){
        if (actualToken.getLexeme().equals("<")){
            match(TokenType.MENOR);
        } else if (actualToken.getLexeme().equals(">")){
            match(TokenType.MAYOR);
        } else if (actualToken.getLexeme().equals("<=")){
            match(TokenType.MENORIGUAL);
        } else if (actualToken.getLexeme().equals(">=")){
            match(TokenType.MAYORIGUAL);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨OpAd ⟩ ::= + | -
    private void opAd(){
        if (actualToken.getLexeme().equals("+")){
            match(TokenType.SUM);
        } else if (actualToken.getLexeme().equals("-")){
            match(TokenType.RESTA);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨OpUnario⟩ ::= + | - | ! | ++ |--
    private void opUnario(){
        if (actualToken.getLexeme().equals("+")){
            match(TokenType.SUM);
        } else if (actualToken.getLexeme().equals("-")){
            match(TokenType.RESTA);
        } else if (actualToken.getLexeme().equals("!")){
            match(TokenType.NOT);
        } else if (actualToken.getLexeme().equals("++")){
            match(TokenType.INC);
        } else if (actualToken.getLexeme().equals("--")){
            match(TokenType.DEC);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨OpMul ⟩ ::= * | / | %
    private void opMul(){
        if (actualToken.getLexeme().equals("*")){
            match(TokenType.PROD);
        } else if (actualToken.getLexeme().equals("/")){
            match(TokenType.DIV);
        } else if (actualToken.getLexeme().equals("%")){
            match(TokenType.MOD);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Operando⟩ ::= ⟨Literal⟩ | ⟨Primario⟩ N12’
    private void operando(){
        if (onFirst(actualToken, first("literal"))){
            literal();
        } else if (onFirst(actualToken, first("primario"))){
            primario();
            N12Prima();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // N12 ::= . id ⟨Llamada-Método-Enc-Acceso-Var-Enc⟩
    private void N12(){
        match(TokenType.DOT);
        match(TokenType.ID);
        llamadaMetodoEncadenado_accVarEnc();
    }
    //⟨Llamada-Método-Enc-Acceso-Var-Enc⟩ ::= ⟨Llamada-Método-Encadenado⟩’ | ⟨Acceso-Variable-Encadenado⟩’
    //{&&,||,),;,],==,!=,<,>,<=,>=,+,-,*,/,%,.,,}
    private void llamadaMetodoEncadenado_accVarEnc(){
        Set<TokenType> followLlamadaMetodoEncadenado_accVarEnc = new HashSet<>(Set.of(TokenType.AND, TokenType.OR, TokenType.RPAREN, TokenType.SEMICOLON, TokenType.RBRACKET,
                TokenType.IGUAL, TokenType.DIF, TokenType.MENOR, TokenType.MAYOR, TokenType.MENORIGUAL, TokenType.MAYORIGUAL, TokenType.SUM, TokenType.RESTA, TokenType.PROD,
                TokenType.DIV, TokenType.MOD, TokenType.DOT, TokenType.COMMA));
        if (onFirst(actualToken, first("llamada_metodo_encadenado'"))){
            llamadaMetodoEncadenadoPrima();
        } else if (onFirst(actualToken, first("acceso_variable_encadenado'"))){
            accesoVariableEncadenadoPrima();
        } else if(followLlamadaMetodoEncadenado_accVarEnc.contains(actualToken.getType())){
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // N12’ ::= N12 | λ
    // follow = {&&,||,),;,],==,!=,<,>,<=,>=,+,-,*,/,%,.,,}
    private void N12Prima(){
        Set<String> followN12Prima = new HashSet<>(Set.of("&&", "||", ")", ";", "]", "==", "!=", "<", ">", "<=", ">=", "+", "-", "*", "/", "%", ".", ","));
        if (onFirst(actualToken, first("N12"))){
            N12();
        } else if (followN12Prima.contains(actualToken.getLexeme())){
            // lambda
            return;
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Literal ⟩ ::= nil | true | false | intLiteral | StrLiteral | charLiteral
    private void literal(){
        if (actualToken.getType() == TokenType.PNIL){
            match(TokenType.PNIL);
        } else if (actualToken.getType() == TokenType.PTRUE){
            match(TokenType.PTRUE);
        } else if (actualToken.getType() == TokenType.PFALSE){
            match(TokenType.PFALSE);
        } else if (actualToken.getType() == TokenType.NUM){
            match(TokenType.NUM);
        } else if (actualToken.getType() == TokenType.STRING){
            match(TokenType.STRING);
        } else if (actualToken.getType() == TokenType.CHAR){
            match(TokenType.CHAR);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    //⟨Primario⟩ ::= ⟨ExpresionParentizada⟩ | ⟨AccesoSelf ⟩ | ⟨Primario⟩’ | ⟨Llamada-Método-Estático⟩ | ⟨Llamada-Constructor ⟩
    private void primario(){
        if (onFirst(actualToken, first("expresion_parentizada"))){
            expresionParentizada();
        } else if (onFirst(actualToken, first("acceso_self"))){
            accesoSelf();
        } else if (onFirst(actualToken, first("primario'"))){
            primarioPrima();
        } else if (onFirst(actualToken, first("llamada_metodo_estatico"))){
            llamadaMetodoEstatico();
        } else if (onFirst(actualToken, first("llamada_constructor"))){
            llamadaConstructor();
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    //⟨Primario⟩’ ::= id ⟨Primario⟩’’
    // {.,&&,||,),;,],==,!=,<,>,<=,>=,+,-,*,/,%,,}
    private void primarioPrima(){
        match(TokenType.ID);
        primarioPrimaPrima();
    }

    //⟨Primario⟩’’ ::= ⟨AccesoVar⟩’ | ⟨Llamada-Método⟩’ | lambda
    private void primarioPrimaPrima(){
        Set<TokenType> followPrimarioPrimaPrima = new HashSet<>(Set.of(TokenType.DOT, TokenType.AND, TokenType.OR, TokenType.RPAREN, TokenType.SEMICOLON, TokenType.RBRACKET,
                TokenType.IGUAL, TokenType.DIF, TokenType.MENOR, TokenType.MAYOR, TokenType.MENORIGUAL, TokenType.MAYORIGUAL, TokenType.SUM, TokenType.RESTA, TokenType.PROD,
                TokenType.DIV, TokenType.MOD, TokenType.COMMA));
        if (onFirst(actualToken, first("acceso_var'"))){
            accesoVarPrima();
        } else if (onFirst(actualToken, first("llamada_metodo'"))){
            llamadaMetodoPrima();
        } else if (followPrimarioPrimaPrima.contains(actualToken.getType())){
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨ExpresionParentizada⟩ ::= ( ⟨Expresion⟩ ) N12’
    private void expresionParentizada(){
        match(TokenType.LPAREN);
        expresion();
        match(TokenType.RPAREN);
        N12Prima();
    }
    // ⟨AccesoSelf ⟩ ::= self N12’
    private void accesoSelf(){
        match(TokenType.PSELF);
        N12Prima();
    }
    // ⟨AccesoVar ⟩ ::= id ⟨AccesoVar⟩’
    private void accesoVar(){
        match(TokenType.ID);
        accesoVarPrima();
    }
    // ⟨AccesoVar ⟩’ ::= N12 | [ ⟨Expresión⟩ ] N12 | λ | [ ⟨Expresión⟩ ]
    // follow  = {.,&&,||,),;,],==,!=,<,>,<=,>=,+,-,*,/,%,,}
    private void accesoVarPrima(){
        Set<String> followAccesoVarPrima = new HashSet<>(Set.of("&&", "||", ")", ";", "]", "==", "!=", "<", ">", "<=", ">=", "+", "-", "*", "/", "%", ",", "."));
        if (actualToken.getLexeme().equals("[")){
            match(TokenType.LBRACKET);
            expresion();
            match(TokenType.RBRACKET);
            if (onFirst(actualToken, first("N12"))){
                N12();
            } else if (followAccesoVarPrima.contains(actualToken.getLexeme())){
                // lambda
                return;
            } else {
                throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
            }
        } else if (onFirst(actualToken, first("N12"))){
            N12();
        } else if (followAccesoVarPrima.contains(actualToken.getLexeme())){
            // lambda
            return;
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Llamada-Método⟩ ::= id ⟨Llamada-Método⟩’
    private void llamadaMetodo(){
        match(TokenType.ID);
        llamadaMetodoPrima();
    }
    // ⟨Llamada-Método⟩’ ::= ⟨Argumentos-Actuales⟩ N12’
    private void llamadaMetodoPrima(){
        argumentosActuales();
        N12Prima();
    }
    // ⟨Llamada-Método-Estático⟩ ::= idStruct . ⟨Llamada-Método⟩N12’
    private void llamadaMetodoEstatico(){
        match(TokenType.STRUCTID);
        match(TokenType.DOT);
        llamadaMetodo();
        N12Prima();
    }
    // ⟨Llamada-Constructor ⟩ ::= new ⟨Llamada-Constructor ⟩’
    private void llamadaConstructor(){
        match(TokenType.PNEW);
        llamadaConstructorPrima();
    }
    // ⟨Llamada-Constructor ⟩’ ::= idStruct ⟨Argumentos-Actuales⟩ N12’| ⟨Tipo-Primitivo⟩ [ ⟨Expresion⟩ ]
    private void llamadaConstructorPrima(){
        if (actualToken.getType() == TokenType.STRUCTID){
            match(TokenType.STRUCTID);
            argumentosActuales();
            N12Prima();
        } else if (onFirst(actualToken, first("tipo_primitivo"))){
            tipoPrimitivo();
            match(TokenType.LBRACKET);
            expresion();
            match(TokenType.RBRACKET);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Argumentos-Actuales⟩ ::= ( ⟨Argumentos-Actuales⟩’
    private void argumentosActuales(){
        match(TokenType.LPAREN);
        argumentosActualesPrima();
    }
    // ⟨Argumentos-Actuales⟩’ ::= ⟨Lista-Expresiones⟩ ) | )
    private void argumentosActualesPrima(){
        if (onFirst(actualToken, first("lista_expresiones"))){
            listaExpresiones();
            match(TokenType.RPAREN);
        } else if (actualToken.getLexeme().equals(")")){
            match(TokenType.RPAREN);
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
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
        if (actualToken.getLexeme().equals(",")){
            match(TokenType.COMMA);
            listaExpresiones();
        } else if (followListaExpresionesPrima.contains(actualToken.getLexeme())){
            // lambda
            return;
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Llamada-Método-Encadenado⟩ ::= id ⟨Llamada-Método-Encadenado⟩’
    private void llamadaMetodoEncadenado(){
        match(TokenType.ID);
        llamadaMetodoEncadenadoPrima();
    }
    // ⟨Llamada-Método-Encadenado⟩’ ::= ⟨Argumentos-Actuales⟩ N12 | ⟨Argumentos-Actuales⟩
    private void llamadaMetodoEncadenadoPrima(){
        if (onFirst(actualToken, first("argumentos_actuales"))){
            argumentosActuales();
            if(onFirst(actualToken, first("N12"))){
                N12();
            } else {
                return;
            }
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
    // ⟨Acceso-Variable-Encadenado⟩ ::= id ⟨Acceso-Variable-Encadenado⟩’
    private void accesoVariableEncadenado(){
        match(TokenType.ID);
        accesoVariableEncadenadoPrima();
    }
    // ⟨Acceso-Variable-Encadenado⟩’ ::=  [⟨Expresion⟩] N12’ | N12’
    //{&&,||,),;,],==,!=,<,>,<=,>=,+,-,*,/,%,.,,}
    private void accesoVariableEncadenadoPrima(){
        Set<TokenType> followAccesoVariableEncadenadoPrima = new HashSet<>(Set.of(TokenType.AND, TokenType.OR, TokenType.RPAREN, TokenType.SEMICOLON, TokenType.RBRACKET,
                TokenType.IGUAL, TokenType.DIF, TokenType.MENOR, TokenType.MAYOR, TokenType.MENORIGUAL, TokenType.MAYORIGUAL, TokenType.SUM, TokenType.RESTA, TokenType.PROD,
                TokenType.DIV, TokenType.MOD, TokenType.DOT, TokenType.COMMA));
        if (actualToken.getLexeme().equals("[")){
            match(TokenType.LBRACKET);
            expresion();
            match(TokenType.RBRACKET);
            N12Prima();
        } else if (onFirst(actualToken, first("N12'"))){
            N12Prima();
        } else if (followAccesoVariableEncadenadoPrima.contains(actualToken.getType())){
            // lambda
        } else {
            throw new UnexpectedTokenError(actualToken.getLexeme(), actualToken.getLine(), actualToken.getColumn());
        }
    }
}
