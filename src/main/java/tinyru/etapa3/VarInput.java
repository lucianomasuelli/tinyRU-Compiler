package tinyru.etapa3;

import java.util.HashSet;

public class VarInput {

    private String name;
    private String type;
    private int line;
    private int column;

    private Boolean visibility;

    private int position;

    public VarInput(String name, String type, Boolean visibility) {
        this.name = name;
        this.type = type;
        this.visibility = visibility;
        this.position = 0; //TODO agregar que calcule la posición relativa
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVisibility() {
        return visibility ? "public" : "private"; // No se como lo estabamos poniendo
    }

    public int getPosition() {
        return position;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

}
