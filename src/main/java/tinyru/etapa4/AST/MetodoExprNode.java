package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.MethodInput;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.ArgsMismatchError;
import tinyru.etapa4.Exceptions.WrongArgTypeError;
import tinyru.etapa5.CodeGenerator;

import java.util.List;

public class MetodoExprNode extends VarMetEncNode{
    private VarMetEncNode encadenado;
    private List<ExpresionNode> argActuales;

    public MetodoExprNode(Token token, String struct, String metodo) {
        super(token,metodo, struct);
    }

    public MetodoExprNode(Token token, String struct) {
        super(token,struct);
    }

    public MetodoExprNode(Token token) {
        super(token, null);
    }

    public void setEncadenado(VarMetEncNode encadenado) {
        this.encadenado = encadenado;
    }

    public void setArgActuales(List<ExpresionNode> argActuales) {
        this.argActuales = argActuales;
    }

    public Token getToken() {
        return token;
    }
    public String getName() {
        return token.getLexeme();
    }

    public List<ExpresionNode> getArgActuales() {
        return argActuales;
    }



    public void addArgActual(ExpresionNode argActual) {
        this.argActuales.add(argActual);
    }

    @Override
    public String check(String structType, SymbolTable st) {
        String metodo = token.getLexeme();
        String type = null;
        if (encadenado != null) {
            structType = st.getStruct(structType).getMethod(metodo).getReturnType();
            type = encadenado.check(structType, st); //TODO
        }else{
            if(structType == null){
                structType = struct;
            }
            // verifica los argumentos
            MethodInput method = st.getStruct(structType).getMethod(token.getLexeme());
            if (method.getParameterTable().size() != argActuales.size()) {
                throw new ArgsMismatchError(argActuales.size(), method.getParameterTable().size(), token.getLine(), token.getColumn());
            }
            for (int i = 0; i < argActuales.size(); i++) {
                if (!argActuales.get(i).check(st).equals(method.getParamByPosition(i).getType())) {
                    throw new WrongArgTypeError(method.getParamByPosition(i).getType(),
                            argActuales.get(i).check(st), token.getLine(), token.getColumn());
                }
            }
            // el tipo es el valor de retorno del método
            type = st.getStructTable().get(structType).getMethod(metodo).getReturnType();
        }
        return type;
    }

    @Override
    public String check(SymbolTable st) {
        return check(null, st);
    }

    @Override
    public String jsonify(){
        String json;
        if(encadenado == null){
            json = "{\"MetodoExpr\": {\"nombre\": " + token.getLexeme() + ", \"args\": [";
            for (ExpresionNode arg : argActuales) {
                json += arg.jsonify() + ",";
            }
            json = json.substring(0, json.length() - 1);
            json += "]}}";
        }else{
            json = "{\"MetodoExpr\": {\"nombre\": " + token.getLexeme() + ", \"args\": [";
            for (ExpresionNode arg : argActuales) {
                json += arg.jsonify() + ",";
            }
            json = json.substring(0, json.length() - 1);
            json += "], \"encadenado\": " + encadenado.jsonify() + "}}";
        }
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        generateCode(cg, struct);
    }

    @Override
    public void generateCode(CodeGenerator cg, String structCaller) {
        // Llamado a un método
        cg.getTextSection().append("# Llamado a ").append(token.getLexeme()).append("()\n");
        int numArgs = argActuales.size();
        // Pushea el frame pointer a la pila.
        cg.getTextSection().append("sw $fp 0($sp)\n"); // Guardamos el valor del fp actual en stack

        cg.getTextSection().append("addiu $sp $sp -4\n"); // Decrementamos el puntero de pila

        // Guardamos argumentos en orden inverso
        cg.getTextSection().append("# Guarda argumentos\n");
        for (int i = argActuales.size() - 1; i >= 0; i--) {
            argActuales.get(i).generateCode(cg); //TODO: ver que se genere bien este código
            //Se espera que el resultado de la expresión esté en $a0
            cg.getTextSection().append("sw $a0 0($sp)\n"); // Guardamos el valor de la expresión en la pila
            cg.getTextSection().append("addiu $sp $sp -4\n"); // Decrementamos el puntero de pila
        }

        //TODO: acá creo que se debería guardar el CIR de self

        // Llamamos a la función
        cg.getTextSection().append("# Jump a la definición del método\n");

        cg.getTextSection().append("jal ").append(structCaller).append("_").append(token.getLexeme()).append("\n");

        //cg.getTextSection().append("addiu $sp, $sp, ").append(4 * numArgs + 4).append("\n"); // Incrementamos el puntero de pila


        /*# Llamado a aObj.sum()
	# Guardamos el valor del fp actual en stack
	sw $fp 0($sp)
	addiu $sp $sp -4

	# Guardamos argumentos
	# No tiene xd

	# Guardamos CIR de self (aObj)
	la $t0, start_locvar_aObj # Cargamos el CIR de aObj
	sw $t0 0($sp)
	addiu $sp $sp -4

	# Hacemos jump a la definición del método
	la $t0, start_locvar_aObj # Cargamos el CIR de aObj
	la $t0, 0($t0) # Cargamos VTable de A
	la $t0, 0($t0) # Cargamos sum() según su índice dentro de A
	jalr $t0*/
    }

}
