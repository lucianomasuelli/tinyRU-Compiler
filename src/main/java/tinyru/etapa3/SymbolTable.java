package tinyru.etapa3;

import tinyru.etapa3.StructInput;

import java.util.Hashtable;

public class SymbolTable {
    public StructInput actualStruct;

    public  MethodInput actualMethod;

    private Hashtable<String, StructInput> structTable = new Hashtable<>();

    public SymbolTable() {
        addStructIO();
        addObject();
        addArray();
        addInt();
        addString();
        addBool();
        addChar();
    }

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

    public void addStructIO() {
        StructInput structIO = new StructInput();
        structIO.setName("IO");
        structTable.put("IO", structIO);

        MethodInput methodInput1 = new MethodInput("out_str",true);
        methodInput1.addParameter("str", new ParamInput("str", "String"));
        methodInput1.setReturnType("void");

    }

    public void addObject(){
        StructInput object = new StructInput();
        object.setName("Object");
        structTable.put("Object", object);
    }

    public void addArray(){
        StructInput array = new StructInput();
        array.setName("Array");

        MethodInput methodInput1 = new MethodInput("length",false);
        methodInput1.setReturnType("Int");
        array.addMethod("length", methodInput1);

        structTable.put("Array", array);
    }

    public void addInt(){
        StructInput intType = new StructInput();
        intType.setName("Int");
        structTable.put("Int", intType);

    }

    public void addString() {
        StructInput stringType = new StructInput();
        stringType.setName("String");

        MethodInput methodInput1 = new MethodInput("length",false);
        methodInput1.setReturnType("Int");
        stringType.addMethod("length", methodInput1);

        MethodInput methodInput2 = new MethodInput("concat",false);
        methodInput2.addParameter("str", new ParamInput("str", "String"));
        methodInput2.setReturnType("String");
        stringType.addMethod("concat", methodInput2);

        structTable.put("String", stringType);

    }

    public void addBool(){
        StructInput boolType = new StructInput();
        boolType.setName("Bool");
        structTable.put("Bool", boolType);
    }

    public void addChar(){
        StructInput charType = new StructInput();
        charType.setName("Char");
        structTable.put("Char", charType);
    }
}


