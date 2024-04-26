package tinyru.etapa3;

import java.util.Hashtable;

public class ConstructorInput {
    Hashtable<String, ParamInput> constructorParams = new Hashtable<>();

    public void addConstructorParam(String name, ParamInput constructorParam) {
        constructorParams.put(name, constructorParam);
    }

    public ParamInput getConstructorParam(String name) {
        return constructorParams.get(name);
    }

    public boolean fetchParameter(String name) {
        return constructorParams.containsKey(name);
    }

    public Hashtable<String, ParamInput> getConstructorParams() {
        return constructorParams;
    }
}
