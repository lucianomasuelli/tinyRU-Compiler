package tinyru.etapa4.AST;

import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;

import java.util.Stack;

public abstract class EncadenadoNode{

    public abstract String check(String type, SymbolTable st);

    public abstract String getType();

}
