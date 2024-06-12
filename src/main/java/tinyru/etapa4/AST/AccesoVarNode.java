package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.MethodInput;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa3.VarInput;
import tinyru.etapa4.Exceptions.AttrNotFoundError;
import tinyru.etapa5.CodeGenerator;

import java.util.List;
import java.util.Stack;

public abstract class AccesoVarNode extends EncadenadoNode {
    private Token token;
    protected EncadenadoNode encadenado;
    protected ExpresionNode index; //Caso de acceso array
    protected String struct;
    protected String metodo;
    private Boolean visible = true;

    public AccesoVarNode(Token token, String struct){
        this.token = token;
        this.struct = struct;
    }

    // Cuando está en el start struct es nulo
    public AccesoVarNode(Token token){
        this.token = token;
    }

    public void setStruct(String struct){
        this.struct = struct;
    }

    public String getStruct(){
        return struct;
    }

    public void setMetodo(String metodo){ this.metodo = metodo; }

    public String getMetodo(){ return metodo; }

    public void print() {
        System.out.print(token.getLexeme());
    }

    public void setEncadenado(EncadenadoNode enc){
        encadenado = enc;
    }

    public void setVisibility(Boolean visible){
        this.visible = visible;
    }

    public void setIndex(ExpresionNode index){this.index = index;}

    public ExpresionNode getIndex(){return this.index;}

    public Token getToken() {
        return token;
    }

    public String getType() {
        String type = null;
        if(encadenado != null){
            type = encadenado.getType();
        }else {
            type = token.getType().toString();
        }
        return type;
    }

    public String getID() {
        return token.getLexeme();
    }


    @Override
    public String check(String structType, SymbolTable st) {
        String type = null;
        if(structType == null ) {
            if (encadenado != null) { //Tiene encadenado
                // Busca la variable en los atributos del struct
                if(this.struct == null){ // Está en el start
                    if(st.getStart().fetchAttribute(token.getLexeme())) {
                        type = encadenado.check(st.getStart().getAttribute(token.getLexeme()).getType(), st);
                    } else {
                        throw new AttrNotFoundError(token.getLexeme(), null, token.getLine(), token.getColumn());
                    }
                }
                else{
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
                        throw new AttrNotFoundError(token.getLexeme(), null, token.getLine(), token.getColumn());
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
                                else {
                                    //TODO: Verifica si el atributo es heredado y visible.
                                    VarInput attribute = st.getStructTable().get(this.struct).getAttribute(token.getLexeme());
                                    if(attribute.getInherited()) {
                                        if(!attribute.getVisibility()) {
                                            throw new AttrNotFoundError(token.getLexeme(), this.struct, token.getLine(), token.getColumn());
                                        }
                                    }
                                }
                                type = st.getStructTable().get(this.struct).getAttribute(token.getLexeme()).getType();
                            }
                        }
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
        //tener en cuenta que el resultado siempre se guarda en $a0
        if (caller == null){
            if(struct == null || struct.equals("start")){ // Está en el start

                if(cg.getSt().getStart().fetchAttribute(token.getLexeme())){
                    int offset = cg.getSt().getStart().getAttribute(token.getLexeme()).getOffset();
                    if(encadenado == null){
                        cg.getTextSection().append("la $a0, -").append(offset).append("($fp)\n");
                    }
                    else {
                        cg.getTextSection().append("lw $a0, -").append(offset).append("($fp)\n");
                    }

                }
            }
            else { // Está en un struct
                if(metodo == "Constructor"){  // Está en el constructor
                    // Reviso primero en las variables del constructor, luego los parametros y finalmente en el struct
                    if(cg.getSt().getStruct(struct).getConstructor().fetchLocalVar(token.getLexeme())){
                        cg.getTextSection().append("la $a0, -").append(cg.getSt().getStruct(struct).getConstructor().getLocalVar(token.getLexeme()).getOffset()).append("($fp)\n");
                    }
                    else {
                        if(cg.getSt().getStruct(struct).getConstructor().fetchParameter(token.getLexeme())){
                            cg.getTextSection().append("la $a0, ").append(cg.getSt().getStruct(struct).getConstructor().getParameter(token.getLexeme()).getOffset()).append("($fp)\n");
                        }
                        else {
                            if(cg.getSt().getStruct(struct).fetchAttribute(token.getLexeme())){
                                // v0 contiene la dirección del objeto
                                int offset = cg.getSt().getStruct(struct).getAttribute(token.getLexeme()).getOffset();
                                cg.getTextSection().append("la $a0, ").append(offset).append("($v0)\n");  // Carga la dirección del atributo en $a0
                            }
                        }
                    }
                }
                else {  // Está en un método
                    MethodInput actualMethod = cg.getSt().getStruct(struct).getMethod(metodo);
                    if(actualMethod.fetchLocalVar(token.getLexeme())){
                        // Está en las variables locales
                        cg.getTextSection().append("la $a0, -").append(actualMethod.getLocalVar(token.getLexeme()).getOffset()).append("($fp)\n");
                    }
                    else {
                        if(actualMethod.fetchParameter(token.getLexeme())){  // Está en los parámetros
                            cg.getTextSection().append("la $a0, ").append(actualMethod.getParameter(token.getLexeme()).getOffset() +4).append("($fp)\n");
                        }
                        else {  // Está en los atributos del struct
                            //Accedo al CIR de self
                            cg.getTextSection().append("lw $t0, 4($fp)\n"); // Carga el CIR de self en $t0
                            if(cg.getSt().getStruct(struct).fetchAttribute(token.getLexeme())){
                                int offset = cg.getSt().getStruct(struct).getAttribute(token.getLexeme()).getOffset();
                                cg.getTextSection().append("la $a0, ").append(offset).append("($t0)\n");  // Carga la dirección del atributo en $a0
                            }
                        }
                    }
                }
            }
        }else {
            if(cg.getSt().getStruct(caller).fetchAttribute(token.getLexeme())){
                int offset = cg.getSt().getStruct(caller).getAttribute(token.getLexeme()).getOffset();
                cg.getTextSection().append("la $a0, ").append(offset).append("($a0)\n");
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

    @Override
    public String jsonify() {
        String json = "{\n";
        json += "\"AccesoVarNode\": {\n";
        json += "\"id\": " + token.getLexeme() + ",\n";
        json += "\"struct\": " + struct + ",\n";
        json += "\"metodo\": " + metodo + ",\n";
        json += "\"visibility\": " + visible + ",\n";
        if(encadenado != null) {
            json += "\"Encadenado\": " + encadenado.jsonify() + "\n";
        }
        json += "}\n";
        json += "}\n";
        return json;
    }
}
