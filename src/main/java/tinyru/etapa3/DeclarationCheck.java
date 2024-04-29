package tinyru.etapa3;

import tinyru.etapa3.Exceptions.*;

public class DeclarationCheck {
    private SymbolTable symbolTable;

    public DeclarationCheck(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void declarationCheck() {
        for (String key : symbolTable.getStructTable().keySet()) {
            StructInput struct = symbolTable.getStruct(key);
            inheritanceCheck(struct);
            consolidation(struct);
        }
    }

    // Check if the inheritance is valid
    public void inheritanceCheck(StructInput struct) {
        if (struct.getInheritanceName() != null) {
            if (!symbolTable.fetchStruct(struct.getInheritanceName())) {
                throw new InheritanceError(struct.getName(), struct.getInheritanceName(),  struct.getLine(), struct.getColumn());
            }
        }
    }


    // Check if the struct is already declared
    public void structCheck(StructInput struct) {
        if (symbolTable.fetchStruct(struct.getName())) {
            throw new StructAlreadyDeclaredError(struct.getName(), struct.getLine(), struct.getColumn());
        }
    }

    // Consolidate and check circular inheritance
    public void consolidation(StructInput struct) {
        StructInput actualStruct = struct;
        while (actualStruct.getInheritanceName() != null) {
            if (actualStruct.getInheritanceName().equals(struct.getName())) {
                throw new CircularInheritanceError(struct.getName(), struct.getInheritanceName(), struct.getLine(), struct.getColumn());
            }
            else { //adds the attributes and methods of the parent struct to the child struct
                StructInput parentStruct = symbolTable.getStruct(actualStruct.getInheritanceName());
                for (String key : parentStruct.getMethodTable().keySet()) {
                    MethodInput method = parentStruct.getMethodTable().get(key);
                    consolidationMethodCheck(struct,method);
                    struct.addMethod(method.getName(), method);
                }
                for (String key : parentStruct.getAttributeTable().keySet()) {
                    VarInput var = parentStruct.getAttributeTable().get(key);
                    consolidationVarCheck(struct,var);
                    struct.addAttribute(var.getName(), var);
                }

            }
            actualStruct = symbolTable.getStruct(actualStruct.getInheritanceName());
        }
    }

    // Check if the method is already declared
    public void methodCheck(StructInput actualStruct, MethodInput method) {
        if (actualStruct.fetchMethod(method.getName())) {
            throw new MethodAlreadyDeclaredError(method.getName(), method.getLine(), method.getColumn());
        }
    }
    public void consolidationMethodCheck(StructInput actualStruct, MethodInput method) {
        if (actualStruct.fetchMethod(method.getName())) {

            //Mismo tipo de atributo, en la mismo orden y mismo retorno

            for (String key : method.getParameterTable().keySet()){
                ParamInput p = method.getParameterTable().get(key);
                ParamInput p2 = actualStruct.getMethod(method.getName()).getParameterByPos(p.getPosition());
                if (!p.getType().equals(p2.getType()) || !p.getPosition().equals(p2.getPosition())){
                    MethodInput m = actualStruct.getMethod(method.getName());
                    throw new MethodOverloadError(m.getName(), m.getLine(), m.getColumn());
                }
            }


        }
    }

    //Check if the variable is already declared
    public void varCheck(StructInput actualStruct, VarInput var) {
        if (actualStruct.fetchAttribute(var.getName())) {
            throw new VarAlreadyDeclaredError(var.getName(), var.getLine(), var.getColumn());
        }
    }

    public void consolidationVarCheck(StructInput actualStruct, VarInput var) {
        if (actualStruct.fetchAttribute(var.getName())) {
            VarInput v = actualStruct.getAttribute(var.getName());
            throw new VarAlreadyDeclaredError(v.getName(), v.getLine(), v.getColumn());
        }
    }

}
