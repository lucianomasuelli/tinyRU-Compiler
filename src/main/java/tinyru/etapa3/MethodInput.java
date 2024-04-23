package tinyru.etapa3;

import java.util.Hashtable;

public class MethodInput {
    String name;
    String returnType;
    Boolean isStatic;

    Hashtable<String, ParamInput> parameterTable = new Hashtable<>();
    Hashtable<String, VarInput> localVarTable = new Hashtable<>();

    public MethodInput(String name, Boolean isStatic) {
        this.name = name;
        this.isStatic = isStatic;
    }

    public void addParameter(String name, ParamInput parameter) {
        parameterTable.put(name, parameter);
    }

    public ParamInput getParameter(String name) {
        return parameterTable.get(name);
    }

    public void addLocalVar(String name, VarInput localVar) {
        localVarTable.put(name, localVar);
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
