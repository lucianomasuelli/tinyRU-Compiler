package tinyru.etapa3;

import java.util.Hashtable;

public class StartInput {
    private String name = "start";
    private int line;
    private int column;
    private int position;

    Hashtable<String, VarInput> attributeTable = new Hashtable<>();

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }
}
