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

    public VarInput(String name, String type, Boolean visibility, int position) {
        this.name = name;
        this.type = type;
        this.visibility = visibility;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean getVisibility() {
        return visibility;
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

    public void setPosition(int position) {
        this.position = position;
    }
}
