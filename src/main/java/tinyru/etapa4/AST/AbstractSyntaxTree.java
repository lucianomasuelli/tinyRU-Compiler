package tinyru.etapa4.AST;

import java.util.List;

public class AbstractSyntaxTree {
    private List<BloqueNode> children;

    public AbstractSyntaxTree(List<BloqueNode> children) {
        this.children = children;
    }

    public List<BloqueNode> getRoot() {
        return children;
    }

    public void setRoot(List<BloqueNode> children) {
        this.children = children;
    }

    public void print() {
        for (BloqueNode node : children) {
            node.print();
        }
    }

}
