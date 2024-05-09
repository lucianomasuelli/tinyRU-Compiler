package tinyru.etapa3;

import java.util.Hashtable;
import tinyru.etapa3.VarInput;


public class StructInput {

    private String name;
    private String inheritanceName;
    private ConstructorInput constructor;
    private int line;
    private int column;
    private boolean hasImpl = false;
    private boolean isDeclared = false;
    private boolean hasConstructor = false;
    private boolean isChecked = false;

    private Hashtable<String, VarInput> attributeTable = new Hashtable<>();
    private Hashtable<String, ConstInput> constantTable = new Hashtable<>();
    private Hashtable<String, MethodInput> methodTable = new Hashtable<>();


    public void addAttribute(String name, VarInput attribute) {
        attributeTable.put(name, attribute);
    }

    public VarInput getAttribute(String name) {
        return attributeTable.get(name);
    }

    public Hashtable<String, VarInput> getAttributeTable() {
        return attributeTable;
    }

    public boolean fetchAttribute(String name) {
        return attributeTable.containsKey(name);
    }

    public void addConstant(String name, ConstInput constant) {
        constantTable.put(name, constant);
    }

    public ConstInput getConstant(String name) {
        return constantTable.get(name);
    }

    public boolean fetchConstant(String name) {
        return constantTable.containsKey(name);
    }

    public Hashtable<String, ConstInput> getConstantTable() {
        return constantTable;
    }

    public void addMethod(String name, MethodInput method) {
        methodTable.put(name, method);
    }

    public MethodInput getMethod(String name) {
        return methodTable.get(name);
    }

    public boolean fetchMethod(String name) {
        return methodTable.containsKey(name);
    }

    public Hashtable<String, MethodInput> getMethodTable() {
        return methodTable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setInheritanceName(String inheritanceName) {
        this.inheritanceName = inheritanceName;
    }

    public String getInheritanceName() {
        return inheritanceName;
    }

    public String getParent() {
        return inheritanceName;
    }

    public void setParent(String parent) {
        this.inheritanceName = parent;
    }

    public void setConstructor(ConstructorInput constructor) {
        this.constructor = constructor;
    }

    public ConstructorInput getConstructor() {
        return constructor;
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

    public void setHasImpl(boolean hasImpl) {
        this.hasImpl = hasImpl;
    }

    public boolean getHasImpl() {
        return hasImpl;
    }

    public void setIsDeclared(boolean isDeclared) {
        this.isDeclared = isDeclared;
    }

    public boolean getIsDeclared() {
        return isDeclared;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setHasConstructor(boolean hasConstructor) {
        this.hasConstructor = hasConstructor;
    }

    public boolean getHasConstructor() {
        return hasConstructor;
    }

    public MethodInput getMethodByPos(int pos) {
        MethodInput methodPos = null;
        for(String key : methodTable.keySet()) {
            MethodInput method = methodTable.get(key);
            if(method.getPosition() == pos) {
                methodPos = method;
                break;
            }
        }
        return methodPos;
    }
}
