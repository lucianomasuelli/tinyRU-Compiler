package tinyru.etapa3;

import java.util.Hashtable;

public class MethodInput {
    String name;
    String returnType;
    Boolean isStatic;
    int position;
    int line;
    int column;

    Hashtable<String, ParamInput> parameterTable = new Hashtable<>();
    Hashtable<String, VarInput> localVarTable = new Hashtable<>();

    public MethodInput(String name, Boolean isStatic) {
        this.name = name;
        this.isStatic = isStatic;
        this.returnType = "void"; //TODO agregar que tome el tipo de retorno
        this.position = 0; //TODO agregar que calcule la posición relativa
    }

    public MethodInput(String name, Boolean isStatic, int position, String returnType) {
        this.name = name;
        this.isStatic = isStatic;
        this.returnType = returnType;
        this.position = position;
    }

    public void addParameter(String name, ParamInput parameter) {
        parameterTable.put(name, parameter);
    }

    public ParamInput getParameter(String name) {
        return parameterTable.get(name);
    }

    public ParamInput getParameterByPos(int pos) {
        ParamInput p,param = null;
        for (String key : parameterTable.keySet()) {
            p = parameterTable.get(key);
            if (p.getPosition() == pos) {
                param=p;
            }
        }
        return param;
    }

    public boolean fetchParameter(String name) {
        return parameterTable.containsKey(name);
    }

    public boolean fetchLocalVar(String name) { return localVarTable.containsKey(name); }

    public Hashtable<String, ParamInput> getParameterTable() {
        return parameterTable;
    }

    public void addLocalVar(String name, VarInput localVar) {
        localVarTable.put(name, localVar);
    }

    public VarInput getLocalVar(String name) { return localVarTable.get(name); }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getName() {
        return name;
    }

    public boolean getIsStatic() {
        return isStatic;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public Hashtable<String, VarInput> getLocalVarTable() {
        return localVarTable;
    }
}
