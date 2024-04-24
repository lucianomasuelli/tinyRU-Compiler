package tinyru.etapa3;

import tinyru.etapa3.StructInput;

import java.util.Hashtable;

public class SymbolTable {
    public StructInput actualStruct;

    public  MethodInput actualMethod;

    private Hashtable<String, StructInput> structTable = new Hashtable<>();

    public void addStruct(String name, StructInput struct) {
        structTable.put(name, struct);
    }

    public StructInput getStruct(String name) {
        return structTable.get(name);
    }

    public boolean fetchStruct(String name) {
        return structTable.containsKey(name);
    }

    public Hashtable<String, StructInput> getStructTable() {
        return structTable;
    }

}


