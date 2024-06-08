package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.ReturnTypeError;
import tinyru.etapa5.CodeGenerator;

public class ReturnNode extends SentenciaNode{

    Token token;
    ExpresionNode exp;
    String type;
    String metodo;
    String struct;

    public ReturnNode(Token token, ExpresionNode exp, String struct, String metodo){
        this.exp = exp;
        if (exp == null){ this.type = "void";}
        this.metodo = metodo;
        this.struct = struct;
        this.token = token;
    }

    public void print() {
        System.out.print("ret ");
        exp.print();
        System.out.println(";");
    }

    @Override
    public String check(SymbolTable st){
        String type;
        if(exp != null){
            type = exp.check(st);
        }
        else{
            type = "Nil";
        }

        String methodReturn = st.getStruct(struct).getMethod(metodo).getReturnType();
        if(!methodReturn.equals(type)){
            if(methodReturn.equals("Int") || methodReturn.equals("Char") || methodReturn.equals("Bool") || methodReturn.equals("Str")){
                if(type.equals("Nil")){
                    throw new ReturnTypeError(type, methodReturn, token.getLine(), token.getColumn());
                }
            }
            else{
                if(methodReturn.equals("void")){
                    if(type != "Nil"){
                        throw new ReturnTypeError(type, methodReturn, token.getLine(), token.getColumn());
                    }
                }
            }
        }
        this.type = type;
        return type;
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"ReturnNode\": {\n";
        json += "\"exp\": " + exp.jsonify() + "\n";
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        if (exp != null) {
            // Generar el código para la expresión de retorno
            exp.generateCode(cg);

            // Mover el resultado de la expresión al registro $v0
            //cg.getTextSection().append("move $v0, $a0\n");
        }

        // Saltar al epílogo de la función
        cg.getTextSection().append("j _end_").append(struct).append("_").append(metodo).append("\n");
    }
}
