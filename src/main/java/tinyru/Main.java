package tinyru;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            //System.out.println("Uso: java -jar etapa1.jar archivo.ru");

            TokenType tkType = TokenType.PSTRUCT;
            Token token = new Token(tkType, "struct", 1, 1);
            System.out.println(token.toString());
            return;
        }

        String filePath = args[0];
        Lexer lexer = null;
        try {
            lexer = new Lexer(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            int i = 0;
            ArrayList<Token> tokens = new ArrayList<Token>();
            int currChar = lexer.getCurrentChar();
            Token token;
            while (currChar != -1) {
                token = lexer.nextToken();
                if (token != null) {
                    tokens.add(token);
                }
                currChar = lexer.getCurrentChar();
            }
            System.out.println(tokens);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
