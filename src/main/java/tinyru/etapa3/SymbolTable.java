package tinyru.etapa3;

import tinyru.etapa3.StructInput;

import java.util.Hashtable;

public class SymbolTable {
    public StructInput actualStruct;
    public MethodInput actualMethod = new MethodInput(null, null);
    public ConstructorInput actualConstructor = new ConstructorInput();

    private Hashtable<String, StructInput> structTable = new Hashtable<>();
    private StartInput start;

    private Boolean creatingConstructor = false;

    public SymbolTable() {
        addStructIO();
        addObject();
        addArray();
        addInt();
        addString();
        addBool();
        addChar();
    }

    public Boolean getCreatingConstructor() {
        return creatingConstructor;
    }

    public void setCreatingConstructor(Boolean creatingConstructor) {
        this.creatingConstructor = creatingConstructor;
    }

    public void setStart(StartInput start) {
        this.start = start;
    }

    public StartInput getStart() {
        return start;
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
        structIO.setHasImpl(true);
        structIO.setIsDeclared(true);

        structIO.setInheritanceName("Object");

        MethodInput methodInput1 = new MethodInput("out_str",true);
        methodInput1.addParameter("s", new ParamInput("s", "Str"));
        methodInput1.setReturnType("void");

        MethodInput methodInput2 = new MethodInput("out_int",true);
        methodInput2.addParameter("i", new ParamInput("i", "Int"));
        methodInput2.setPosition(1);
        methodInput2.setReturnType("void");

        MethodInput methodInput3 = new MethodInput("out_bool",true);
        methodInput3.addParameter("b", new ParamInput("b", "Bool"));
        methodInput3.setPosition(2);
        methodInput3.setReturnType("void");

        MethodInput methodInput4 = new MethodInput("out_char",true);
        methodInput4.addParameter("c", new ParamInput("c", "Char"));
        methodInput4.setPosition(3);
        methodInput4.setReturnType("void");

        MethodInput methodInput5 = new MethodInput("out_array_int",true);
        methodInput5.addParameter("a", new ParamInput("a", "Array"));
        methodInput5.setPosition(4);
        methodInput5.setReturnType("void");

        MethodInput methodInput6 = new MethodInput("out_array_str",true);
        methodInput6.addParameter("a", new ParamInput("a", "Array"));
        methodInput6.setPosition(5);
        methodInput6.setReturnType("void");

        MethodInput methodInput7 = new MethodInput("out_array_bool",true);
        methodInput7.addParameter("a", new ParamInput("a", "Array"));
        methodInput7.setPosition(6);
        methodInput7.setReturnType("void");

        MethodInput methodInput8 = new MethodInput("out_array_char",true);
        methodInput8.addParameter("a", new ParamInput("a", "Array"));
        methodInput8.setPosition(7);
        methodInput8.setReturnType("void");

        MethodInput methodInput9 = new MethodInput("in_str",true);
        methodInput9.setPosition(8);
        methodInput9.setReturnType("Str");

        MethodInput methodInput10 = new MethodInput("in_int",true);
        methodInput10.setPosition(9);
        methodInput10.setReturnType("Int");

        MethodInput methodInput11 = new MethodInput("in_bool",true);
        methodInput11.setPosition(10);
        methodInput11.setReturnType("Bool");

        MethodInput methodInput12 = new MethodInput("in_char",true);
        methodInput12.setPosition(11);
        methodInput12.setReturnType("Char");

        structIO.addMethod("out_str", methodInput1);
        structIO.addMethod("out_int", methodInput2);
        structIO.addMethod("out_bool", methodInput3);
        structIO.addMethod("out_char", methodInput4);
        structIO.addMethod("out_array_int", methodInput5);
        structIO.addMethod("out_array_str", methodInput6);
        structIO.addMethod("out_array_bool", methodInput7);
        structIO.addMethod("out_array_char", methodInput8);
        structIO.addMethod("in_str", methodInput9);
        structIO.addMethod("in_int", methodInput10);
        structIO.addMethod("in_bool", methodInput11);
        structIO.addMethod("in_char", methodInput12);

        structTable.put("IO", structIO);
    }

    public void addObject(){
        StructInput object = new StructInput();
        object.setName("Object");
        object.setHasImpl(true);
        object.setIsDeclared(true);
        structTable.put("Object", object);
    }

    public void addArray(){
        StructInput array = new StructInput();
        array.setName("Array");
        array.setHasImpl(true);
        array.setIsDeclared(true);

        MethodInput methodInput1 = new MethodInput("length",false);
        methodInput1.setReturnType("Int");
        array.addMethod("length", methodInput1);

        structTable.put("Array", array);
    }

    public void addInt(){
        StructInput intType = new StructInput();
        intType.setHasImpl(true);
        intType.setIsDeclared(true);
        intType.setName("Int");
        structTable.put("Int", intType);

    }

    public void addString() {
        StructInput stringType = new StructInput();
        stringType.setHasImpl(true);
        stringType.setIsDeclared(true);
        stringType.setName("Str");

        MethodInput methodInput1 = new MethodInput("length",false);
        methodInput1.setPosition(0);
        methodInput1.setReturnType("Int");
        stringType.addMethod("length", methodInput1);

        MethodInput methodInput2 = new MethodInput("concat",false);
        methodInput2.addParameter("s", new ParamInput("s", "Str"));
        methodInput2.setPosition(1);
        methodInput2.setReturnType("Str");
        stringType.addMethod("concat", methodInput2);

        structTable.put("Str", stringType);

    }

    public void addBool(){
        StructInput boolType = new StructInput();
        boolType.setHasImpl(true);
        boolType.setIsDeclared(true);
        boolType.setName("Bool");
        structTable.put("Bool", boolType);
    }

    public void addChar(){
        StructInput charType = new StructInput();
        charType.setHasImpl(true);
        charType.setIsDeclared(true);
        charType.setName("Char");
        structTable.put("Char", charType);
    }
}


