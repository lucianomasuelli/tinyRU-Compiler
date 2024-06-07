package tinyru.etapa4.AST;

import tinyru.etapa3.MethodInput;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.MissingReturnError;
import tinyru.etapa5.CodeGenerator;

import java.util.List;
import java.util.Objects;

public class BloqueMetodoNode extends  BloqueNode{


    public BloqueMetodoNode(List<SentenciaNode> sentencias, String structName, String methodName) {
        super(sentencias, structName,methodName);
    }

    @Override
    public void check(SymbolTable st) {
        if (structName.equals("start")) {
            for (SentenciaNode sentencia : sentencias) {
                sentencia.check(st);
            }
        }
        else{
            String returnType;
            if(methodName.equals("constructor")){
                returnType = "void";
            }
            else{
                returnType = st.getStruct(structName).getMethod(methodName).getReturnType();
            }
            boolean ret = false;
            for (SentenciaNode sentencia : sentencias) {
                if(!returnType.equals("void")){
                    //verifica si es de tipo ReturnNode
                    if(sentencia instanceof ReturnNode) {
                        ret = true;
                    }
                }
                sentencia.check(st);
            }
            if(ret == false && !returnType.equals("void")){
                int methodLine = st.getStruct(structName).getMethod(methodName).getLine();
                int methodColumn = st.getStruct(structName).getMethod(methodName).getColumn();
                throw new MissingReturnError(methodName, methodLine, methodColumn);
            }
        }
    }

    public void generateCode(CodeGenerator cg) {
        if(structName.equals("start")) {
            cg.getTextSection().append("main:\n");
            cg.getTextSection().append("move $fp, $sp\n");  // Guarda el frame pointer
            cg.getTextSection().append("sw $ra, 0($sp)\n");  // Guarda el return address
            cg.getTextSection().append("addiu $sp, $sp, -4\n");  // Mueve el stack pointer
            // Genera el código de las sentencias
            for (SentenciaNode sentencia : sentencias) {
                sentencia.generateCode(cg);
            }
        }
        else{
            if(methodName.equals("constructor")){
                cg.getTextSection().append(structName).append("_constructor:\n");
                cg.getTextSection().append("# Prologo\n");
                cg.getTextSection().append("move $fp, $sp\n");  // Guarda el frame pointer
                cg.getTextSection().append("sw $ra, 0($sp)\n");  // Guarda el return address
                cg.getTextSection().append("addiu $sp, $sp, -4\n");  // Mueve el stack pointer

                // Genera el código de las sentencias
                // En este momento $v0 contiene la dirección del CIR
                cg.getTextSection().append("# Cuerpo del constructor\n");
                for (SentenciaNode sentencia : sentencias) {
                    sentencia.generateCode(cg);
                }

                cg.getTextSection().append("# Epilogo\n");
                cg.getTextSection().append("lw $ra, 4($sp)\n");  // Recupera el return address
                cg.getTextSection().append("addiu $sp, $sp, 4\n");  // Restaura el stack pointer
                cg.getTextSection().append("lw $fp, 0($sp)\n");  // Recupera el frame pointer


                cg.getTextSection().append("jr $ra\n");  // Salta a la dirección de retorno



            }
            else { // Genera el código del bloque de un método
                cg.getTextSection().append(structName).append("_").append(methodName).append(":\n");
                cg.getTextSection().append("# Prologo\n");
                cg.getTextSection().append("move $fp, $sp\n");  // Guarda el frame pointer
                cg.getTextSection().append("sw $ra, 0($sp)\n");  // Guarda el return address
                cg.getTextSection().append("addiu $sp, $sp, -4\n");  // Mueve el stack pointer

                // Genera el código de las sentencias
                cg.getTextSection().append("# Cuerpo del método\n");
                for (SentenciaNode sentencia : sentencias) {
                    sentencia.generateCode(cg);
                }

                int numArgs = cg.getSt().getStructTable().get(structName).getMethod(methodName).getParameterTable().size();
                cg.getTextSection().append("# Epilogo\n");
                cg.getTextSection().append("_end_").append(structName).append("_").append(methodName).append(":\n");
                cg.getTextSection().append("lw $ra, 4($sp)\n");  // Recupera el return address
                cg.getTextSection().append("addiu $sp, $sp, ").append(4 * (numArgs + 1)).append("\n");  // Restaura el stack pointer
                cg.getTextSection().append("lw $fp, 0($sp)\n");  // Recupera el frame pointer
                cg.getTextSection().append("jr $ra\n");  // Salta a la dirección de retorno
            }
        }
    }


    @Override
    public String jsonify(){
        String json = "";
        json += "{\n";
        json += "\"nombre\": \"" + methodName + "\",\n";
        json += "\"sentencias\": [\n";
        for (SentenciaNode sentencia : sentencias) {
            json += sentencia.jsonify();
            if(sentencias.indexOf(sentencia) != sentencias.size() - 1){
                json += ",\n";
            }
        }
        json += "]\n";
        json += "}\n";
        return json;
    }

}
