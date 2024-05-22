package tinyru.etapa3;

import java.util.Hashtable;

public class ConstructorInput {
    Hashtable<String, ParamInput> constructorParams = new Hashtable<>();
    Hashtable<String, VarInput> locaVars = new Hashtable<>();

    public void addConstructorParam(String name, ParamInput constructorParam) {
        constructorParams.put(name, constructorParam);
    }

    public ParamInput getParameter(String name) {
        return constructorParams.get(name);
    }

    public void addLocalVar(String name, VarInput localVar) {
        locaVars.put(name, localVar);
    }

    public VarInput getLocalVar(String name) {
        return locaVars.get(name);
    }

    public boolean fetchLocalVar(String name) {
        return locaVars.containsKey(name);
    }

    public boolean fetchParameter(String name) {
        return constructorParams.containsKey(name);
    }

    public ParamInput getParamByPosition(int position) {
        for (ParamInput param : constructorParams.values()) {
            if (param.getPosition() == position) {
                return param;
            }
        }
        return null;
    }

    public Hashtable<String, ParamInput> getConstructorParams() {
        return constructorParams;
    }
}
