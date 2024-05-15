package tinyru.etapa4.AST;
import tinyru.etapa1.Token;

public class StrLiteralNode extends LiteralNode{
    Token token;

    public StrLiteralNode(Token token){
        this.token = token;
    }
}
