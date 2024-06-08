package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.ArgsMismatchError;
import tinyru.etapa4.Exceptions.StructNotFoundError;
import tinyru.etapa4.Exceptions.WrongArgTypeError;
import tinyru.etapa5.CodeGenerator;

import java.util.List;

public class LlamadaConstructor extends LlamadaConstructorNode{
    private String idStruct;
    private List<ExpresionNode> args;
    private VarMetEncNode encadenado = null;

    public LlamadaConstructor(Token token, String struct, String metodo, String idStruct, List<ExpresionNode> args, VarMetEncNode encadenado){
        super(token, metodo, struct);
        this.args = args;
        this.encadenado = encadenado;
        this.idStruct = idStruct;
    }

    public LlamadaConstructor(Token token, String idStruct, List<ExpresionNode> args, VarMetEncNode encadenado){
        super(token);
        this.args = args;
        this.encadenado = encadenado;
        this.idStruct = idStruct;
    }


    @Override
    public String check(SymbolTable st) {
        String type = idStruct;
        if(encadenado != null){
            type = encadenado.check(type, st);
        }
        else {
            if (st.fetchStruct(idStruct)) {
                if (st.getStruct(idStruct).getConstructor().getConstructorParams().size() == args.size()) {
                    for (int i = 0; i < args.size(); i++) {
                        if (!args.get(i).check(st).equals(st.getStruct(idStruct).getConstructor().getParamByPosition(i).getType())) {
                            throw new WrongArgTypeError(st.getStruct(idStruct).getConstructor().getParamByPosition(i).getType(),
                                    args.get(i).check(st), token.getLine(), token.getColumn());
                        }
                    }
                } else {
                    throw new ArgsMismatchError(args.size(), st.getStruct(idStruct).getConstructor().getConstructorParams().size(),
                            token.getLine(), token.getColumn());
                }
            } else {
                throw new StructNotFoundError(idStruct, token.getLine(), token.getColumn());
            }

        }
        return type;
    }

    @Override
    public String check(String type, SymbolTable st) {
        return check(st);
    }

    @Override
    public String jsonify(){
        String json = "{";
        json += "\"node\": \"LlamadaConstructor\",";
        json += "\"idStruct\": \"" + idStruct + "\",";
        json += "\"metodo\": \"" + metodo + "\",";
        json += "\"args\": [";
        for (ExpresionNode arg : args) {
            json += arg.jsonify() + ",";
        }
        if (args.size() > 0) {
            json = json.substring(0, json.length() - 1);
        }
        json += "]";
        if (encadenado != null) {
            json += ",\"encadenado\": " + encadenado.jsonify();
        }
        json += "}";
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        // Pushea el frame pointer a la pila.
        cg.getTextSection().append("sw $fp 0($sp)\n"); // Guardamos el valor del fp actual en stack
        cg.getTextSection().append("addiu $sp $sp -4\n"); // Decrementamos el puntero de pila

        // Calcular el tamaño del objeto en bytes
        int objectSize = cg.getSt().getStruct(idStruct).getAttributeTable().size() * 4 + 4;

        // Asignar espacio en el heap para el objeto
        cg.getTextSection().append("li $a0, ").append(objectSize).append("\n"); // Cargar el tamaño del objeto en $a0
        cg.getTextSection().append("li $v0, 9\n"); // Código de servicio para asignar espacio en el heap
        cg.getTextSection().append("syscall\n"); // Llamar al sistema para asignar espacio, devuelve la dirección del heap en $v0

        //Creación del CIR
        //Crea un label que devuelve la dirección del objeto


        //Guardar la dirección de la vtable en el objeto
        cg.getTextSection().append("la $t0, ").append(idStruct).append("_vt\n"); // Cargar la dirección de la vtable en $t0
        cg.getTextSection().append("sw $t0, 0($v0)\n"); // Guardar la dirección de la vtable en el objeto

        // Guardar los argumentos del constructor en la pila y saltar a la definición del constructor
        for (int i = 0; i < args.size(); i++) {
            args.get(i).generateCode(cg);
            cg.getTextSection().append("sw $a0, ").append("0($sp)\n"); // Guardar el argumento en el stack
            cg.getTextSection().append("addiu $sp, $sp, -4\n"); // Mover el stack pointer
        }
        cg.getTextSection().append("jal ").append(idStruct).append("_constructor\n"); // Saltar a la definición del constructor

        //cg.getTextSection().append("addiu $sp, $sp, ").append(4 * args.size()).append("\n"); // Restaurar el stack pointer
        //cg.getTextSection().append("lw $fp, 0($sp)\n");//Recupera el frame pointer

        // Guarda en $a0 la dirección del objeto
        cg.getTextSection().append("move $a0, $v0\n");


    }
}
