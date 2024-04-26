package tinyru.etapa3;

import java.util.Hashtable;

public class MethodInput {
    String name;
    String returnType;
    Boolean isStatic;
    int position;

    Hashtable<String, ParamInput> parameterTable = new Hashtable<>();
    Hashtable<String, VarInput> localVarTable = new Hashtable<>();

    public MethodInput(String name, Boolean isStatic) {
        this.name = name;
        this.isStatic = isStatic;
        this.returnType = "void"; //TODO agregar que tome el tipo de retorno
        this.position = 0; //TODO agregar que calcule la posición relativa
    }

    public void addParameter(String name, ParamInput parameter) {
        parameterTable.put(name, parameter);
    }

    public ParamInput getParameter(String name) {
        return parameterTable.get(name);
    }

    public boolean fetchParameter(String name) {
        return parameterTable.containsKey(name);
    }

    public Hashtable<String, ParamInput> getParameterTable() {
        return parameterTable;
    }

    public void addLocalVar(String name, VarInput localVar) {
        localVarTable.put(name, localVar);
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getName() {
        return name;
    }

    public String getIsStatic() {
        return isStatic.toString();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
