package tinyru.etapa3;

import java.util.HashSet;

public class VarInput {

    private String name;
    private String type;
    private int line;
    private int column;
    private boolean isInherited = false;

    private Boolean visibility;

    private int position;

    public VarInput(String name, String type, Boolean visibility) {
        this.name = name;
        this.type = type;
        this.visibility = visibility;
        this.position = 0;
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

    public void setInherited(boolean isInherited) {
        this.isInherited = isInherited;
    }

    public boolean getInherited() {
        return isInherited;
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

    public int getOffset() {
        return 4 * (position+1);
    }
}
