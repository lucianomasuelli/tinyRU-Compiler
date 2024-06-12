package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.MethodInput;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.AttrNotFoundError;
import tinyru.etapa5.CodeGenerator;

public class VariableExprNode extends VarMetEncNode{
    private VarMetEncNode encadenado;
    private ExpresionNode arrayAccess = null;

    public VariableExprNode(Token token, String struct, String metodo){
        super(token, metodo, struct);
    }

    public VariableExprNode(Token token){
        super(token);
    }

    public void setEncadenado(VarMetEncNode encadenado){
        this.encadenado = encadenado;
    }

    public void setArrayAccess(ExpresionNode arrayAccess){
        this.arrayAccess = arrayAccess;
    }

    @Override
    public void print(){
        System.out.print(token.getLexeme());
    }


    public String check(String structType, SymbolTable st) {
        String type = null;
        if(structType == null) {
            if (encadenado != null) { //Tiene encadenado
                // Busca la variable en los atributos del struct
                if(this.struct == null){ // Está en el start
                    if(st.getStart().fetchAttribute(token.getLexeme())) {
                        type = encadenado.check(st.getStart().getAttribute(token.getLexeme()).getType(), st);
                    } else {
                        throw new AttrNotFoundError(token.getLexeme(), null, token.getLine(), token.getColumn());
                    }
                } else {
                    if(st.getStruct(this.struct).fetchAttribute(token.getLexeme())) {
                        type = encadenado.check(st.getStruct(this.struct).getAttribute(token.getLexeme()).getType(), st);
                    } else {
                        if(this.metodo == "Constructor") {
                            if(st.getStruct(this.struct).getConstructor().fetchLocalVar(token.getLexeme())) {
                                type = encadenado.check(st.getStruct(this.struct).getConstructor().getLocalVar(token.getLexeme()).getType(), st);
                            } else {
                                if(st.getStruct(this.struct).getConstructor().fetchParameter(token.getLexeme())) {
                                    type = encadenado.check(st.getStruct(this.struct).getConstructor().getParameter(token.getLexeme()).getType(), st);
                                } else {
                                    throw new AttrNotFoundError(token.getLexeme(), this.struct, token.getLine(), token.getColumn());
                                }
                            }
                        }
                        else {
                            if(st.getStruct(this.struct).getMethod(this.metodo).fetchLocalVar(token.getLexeme())) {
                                type = encadenado.check(st.getStruct(this.struct).getMethod(this.metodo).getLocalVar(token.getLexeme()).getType(), st);
                            } else {
                                if(st.getStruct(this.struct).getMethod(this.metodo).fetchParameter(token.getLexeme())) {
                                    type = encadenado.check(st.getStruct(this.struct).getMethod(this.metodo).getParameter(token.getLexeme()).getType(), st);
                                } else {
                                    throw new AttrNotFoundError(token.getLexeme(), this.struct, token.getLine(), token.getColumn());
                                }
                            }
                        }
                    }
                }

            } else { // NO tiene encadenado
                if (this.metodo == null ){ // Está en el start
                    if (!st.getStart().fetchAttribute(token.getLexeme())) {
                        throw new AttrNotFoundError(token.getLexeme(), "Start", token.getLine(), token.getColumn());
                    } else {
                        type = st.getStart().getAttribute(token.getLexeme()).getType();
                    }
                }
                else {
                    if(this.metodo == "Constructor") { // Está en el constructor
                        //Reviso primero en las variables del constructor, luego los parametros y finalmente en el struct
                        if (st.getStruct(this.struct).getConstructor().fetchLocalVar(token.getLexeme())) {
                            type = st.getStruct(this.struct).getConstructor().getLocalVar(token.getLexeme()).getType();
                        } else {
                            if (st.getStruct(this.struct).getConstructor().fetchParameter(token.getLexeme())) {
                                type = st.getStruct(this.struct).getConstructor().getParameter(token.getLexeme()).getType();
                            } else {
                                if (!st.getStructTable().get(this.struct).fetchAttribute(token.getLexeme())) {
                                    throw new AttrNotFoundError(token.getLexeme(), this.struct, token.getLine(), token.getColumn());
                                }
                                type = st.getStructTable().get(this.struct).getAttribute(token.getLexeme()).getType();
                            }
                        }

                    }
                    else {
                        type = getVarType(st);
                    }
                }
            }
        }else {
            if(!st.getStructTable().get(structType).fetchAttribute(token.getLexeme())) {
                throw new AttrNotFoundError(token.getLexeme(), structType, token.getLine(), token.getColumn());
            }
            if(encadenado != null) {

                type = encadenado.check(st.getStructTable().get(structType).getAttribute(token.getLexeme()).getType(), st);
            }else {
                type = st.getStructTable().get(structType).getAttribute(token.getLexeme()).getType();
            }
        }
        return type;
    }

    public String getVarType(SymbolTable st) {
        String type = null;
        //Reviso primero en las variables del metodo, luego los parametros y finalmente en el struct
        if (st.getStruct(this.struct).getMethod(this.metodo).fetchLocalVar(token.getLexeme())) {
            type = st.getStruct(this.struct).getMethod(this.metodo).getLocalVar(token.getLexeme()).getType();
        } else {
            if (st.getStruct(this.struct).getMethod(this.metodo).fetchParameter(token.getLexeme())) {
                type = st.getStruct(this.struct).getMethod(this.metodo).getParameter(token.getLexeme()).getType();
            } else {
                if (!st.getStructTable().get(this.struct).fetchAttribute(token.getLexeme())) {
                    throw new AttrNotFoundError(token.getLexeme(), this.struct, token.getLine(), token.getColumn());
                }
                type = st.getStructTable().get(this.struct).getAttribute(token.getLexeme()).getType();
            }
        }
        return type;
    }

    @Override
    public String check(SymbolTable st) {
        return check(null, st);
    }

    @Override
    public void generateCode(CodeGenerator cg){
        generateCode(cg, null);
    }

    @Override
    public void generateCode(CodeGenerator cg, String caller) {
        String type = getVarType(cg, caller);
        if(encadenado != null){
            varAccess(cg, caller);
            encadenado.generateCode(cg, type);
        }
        else {
            varAccess(cg, caller);
        }
    }

    public void varAccess(CodeGenerator cg, String caller) {
        // Como estas variables son expresiones queremos cargar en $a0 el valor de la variable, no la dirección
        if(caller == null) { //No viene de un encadenado
            if(struct == null || struct.equals("start")){ // Está en el start
                if(cg.getSt().getStart().fetchAttribute(token.getLexeme())){
                    int offset = cg.getSt().getStart().getAttribute(token.getLexeme()).getOffset();
                    cg.getTextSection().append("lw $a0, -").append(offset).append("($fp)\n");

                    //TODO Checkear que funcione
                    if (arrayAccess == null){

                        cg.getTextSection().append("lw $a0, -").append(offset).append("($fp)\n");
                    } else {
                        arrayAccess.generateCode(cg);
                        cg.getTextSection().append("mul $a0, $a0, 4\n");
                        cg.getTextSection().append("add $a0, $a0, $sp\n");
                        cg.getTextSection().append("lw $a0, 0($a0)\n");

                        cg.getTextSection().append("lw $a0, -").append(offset).append("($fp)\n");

                    }

                }
            }
            else { // Está en un struct
                if(metodo == "Constructor"){  // Está en el constructor
                    // Reviso primero en las variables del constructor, luego los parametros y finalmente en el struct
                    if(cg.getSt().getStruct(struct).getConstructor().fetchLocalVar(token.getLexeme())){
                        cg.getTextSection().append("lw $a0, -").append(cg.getSt().getStruct(struct).getConstructor().getLocalVar(token.getLexeme()).getOffset()).append("($fp)\n");
                    }
                    else {
                        if(cg.getSt().getStruct(struct).getConstructor().fetchParameter(token.getLexeme())){
                            cg.getTextSection().append("lw $a0, ").append(cg.getSt().getStruct(struct).getConstructor().getParameter(token.getLexeme()).getOffset()).append("($fp)\n");
                        }
                        else {
                            if(cg.getSt().getStruct(struct).fetchAttribute(token.getLexeme())){
                                // v0 contiene la dirección del objeto
                                int offset = cg.getSt().getStruct(struct).getAttribute(token.getLexeme()).getOffset();
                                cg.getTextSection().append("lw $a0, ").append(offset).append("($v0)\n");  // Carga el contenido del atributo en $a0
                            }
                        }
                    }
                }
                else {  // Está en un método
                    MethodInput actualMethod = cg.getSt().getStruct(struct).getMethod(metodo);
                    if(actualMethod.fetchLocalVar(token.getLexeme())){  // Está en las variables locales
                        cg.getTextSection().append("lw $a0, -").append(actualMethod.getLocalVar(token.getLexeme()).getOffset()).append("($fp)\n");
                    }
                    else {
                        if(actualMethod.fetchParameter(token.getLexeme())){  // Está en los parámetros
                            cg.getTextSection().append("lw $a0, ").append(actualMethod.getParameter(token.getLexeme()).getOffset() + 4).append("($fp)\n"); // Se suma 4 por el self
                        }
                        else {  // Está en los atributos del struct
                            //Accedo al CIR de self
                            cg.getTextSection().append("lw $t0, 4($fp)\n"); // Carga el CIR de self en $t0
                            if(cg.getSt().getStruct(struct).fetchAttribute(token.getLexeme())) {
                                int offset = cg.getSt().getStruct(struct).getAttribute(token.getLexeme()).getOffset();
                                cg.getTextSection().append("lw $a0, ").append(offset).append("($t0)\n");  // Carga la dirección del atributo en $a0
                            }
                        }
                    }
                }
            }
        }
        else {
            if(cg.getSt().getStruct(caller).fetchAttribute(token.getLexeme())){
                int offset = cg.getSt().getStruct(caller).getAttribute(token.getLexeme()).getOffset();
                cg.getTextSection().append("lw $a0, ").append(offset).append("($a0)\n");
            }
        }
    }

    public String getVarType(CodeGenerator cg, String caller){
        String type;
        if(caller == null) {
            if(struct == null || struct == "start"){
                struct = "start";
                type = cg.getSt().getStart().getAttribute(token.getLexeme()).getType();
            }
            else {
                if(metodo == "Constructor"){
                    //Busca el tipo en las variables locales del constructor, luego los parametros y finalmente en el struct
                    if(cg.getSt().getStruct(struct).getConstructor().fetchLocalVar(token.getLexeme())){
                        type = cg.getSt().getStruct(struct).getConstructor().getLocalVar(token.getLexeme()).getType();
                    }
                    else {
                        if(cg.getSt().getStruct(struct).getConstructor().fetchParameter(token.getLexeme())){
                            type = cg.getSt().getStruct(struct).getConstructor().getParameter(token.getLexeme()).getType();
                        }
                        else {
                            type = cg.getSt().getStruct(struct).getAttribute(token.getLexeme()).getType();
                        }
                    }
                }
                else {
                    //Busca el tipo en las variables locales del método, luego los parametros y finalmente en el struct
                    if(cg.getSt().getStruct(struct).getMethod(metodo).fetchLocalVar(token.getLexeme())){
                        type = cg.getSt().getStruct(struct).getMethod(metodo).getLocalVar(token.getLexeme()).getType();
                    }
                    else {
                        if(cg.getSt().getStruct(struct).getMethod(metodo).fetchParameter(token.getLexeme())){
                            type = cg.getSt().getStruct(struct).getMethod(metodo).getParameter(token.getLexeme()).getType();
                        }
                        else {
                            type = cg.getSt().getStruct(struct).getAttribute(token.getLexeme()).getType();
                        }
                    }
                }

            }
        }
        else{
            if(cg.getSt().getStruct(caller).fetchAttribute(token.getLexeme())){
                type = cg.getSt().getStruct(caller).getAttribute(token.getLexeme()).getType();
            }
            else {
                type = cg.getSt().getStruct(caller).getMethod(metodo).getLocalVar(token.getLexeme()).getType();
            }
        }

        return type;
    }
}
