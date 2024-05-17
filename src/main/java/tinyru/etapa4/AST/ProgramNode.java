package tinyru.etapa4.AST;

import java.util.List;

public class ProgramNode {
    private List<BloqueNode> children;

    public ProgramNode(List<BloqueNode> children) {
        this.children = children;
    }

    public List<BloqueNode> getChildren() {
        return children;
    }

    public void setChildren(List<BloqueNode> children) {
        this.children = children;
    }
}
