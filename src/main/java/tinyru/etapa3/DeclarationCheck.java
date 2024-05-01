package tinyru.etapa3;

import tinyru.etapa3.Exceptions.*;
import tinyru.etapa1.Token;

import java.util.HashSet;
import java.util.Set;

public class DeclarationCheck {
    private SymbolTable symbolTable;

    private HashSet<String> arrayTypes = new HashSet<>(Set.of("Array Str", "Array Bool","Array Int", "Array Char"));

    public DeclarationCheck(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void declarationCheck() {
        for (String key : symbolTable.getStructTable().keySet()) {
            StructInput struct = symbolTable.getStruct(key);
            inheritanceCheck(struct);
            consolidation(struct);
            implCheck(struct);
            noStructCheck(struct);
            typesCheck(struct);
            checkConstructorParams(struct);
        }

        //check if the type of the attributes of start are correct
        for (String key : symbolTable.getStart().getAttributeTable().keySet()) {
            VarInput var = symbolTable.getStart().getAttributeTable().get(key);
            checkVarType(var);
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
                if (!actualStruct.getIsChecked()){
                    StructInput parentStruct = symbolTable.getStruct(actualStruct.getInheritanceName());
                    int numOfMethods = parentStruct.getMethodTable().size();
                    for (String key : struct.getMethodTable().keySet()) {
                        MethodInput met = struct.getMethodTable().get(key);
                        met.setPosition(met.getPosition() + numOfMethods);
                    }
                    for (String key : parentStruct.getMethodTable().keySet()) {
                        MethodInput method = parentStruct.getMethodTable().get(key);
                        consolidationMethodCheck(struct,method);
                        struct.addMethod(method.getName(), method);
                    }
                    int numOfAttributes = parentStruct.getAttributeTable().size();
                    for (String key : struct.getAttributeTable().keySet()) {
                        VarInput var = struct.getAttributeTable().get(key);
                        var.setPosition(var.getPosition() + numOfAttributes);
                    }
                    for (String key : parentStruct.getAttributeTable().keySet()) {
                        VarInput var = parentStruct.getAttributeTable().get(key);
                        consolidationVarCheck(struct,var);
                        struct.addAttribute(var.getName(), var);
                    }
                } else {
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
                actualStruct.setIsChecked(true);
            }
            actualStruct = symbolTable.getStruct(actualStruct.getInheritanceName());
        }
        struct.setIsChecked(true);
    }

    // Check if the method is already declared
    public void methodCheck(StructInput actualStruct, MethodInput method) {
        if (actualStruct.fetchMethod(method.getName())) {
            throw new MethodAlreadyDeclaredError(actualStruct, method.getName(), method.getLine(), method.getColumn());
        }
    }
    public void consolidationMethodCheck(StructInput actualStruct, MethodInput method) {
        if (actualStruct.fetchMethod(method.getName())) {
            //Mismo tipo de atributo, en la mismo orden y mismo retorno
            if (method.getParameterTable().size() != actualStruct.getMethod(method.getName()).getParameterTable().size()){
                MethodInput m = actualStruct.getMethod(method.getName());
                throw new MethodOverloadError(m.getName(), m.getLine(), m.getColumn());
            }
            for (String key : method.getParameterTable().keySet()){
                try {
                    ParamInput p = method.getParameterTable().get(key);
                    ParamInput p2 = actualStruct.getMethod(method.getName()).getParameterByPos(p.getPosition());
                    if (!p.getType().equals(p2.getType()) || !p.getPosition().equals(p2.getPosition())){
                        MethodInput m = actualStruct.getMethod(method.getName());
                        throw new MethodOverloadError(m.getName(), m.getLine(), m.getColumn());
                    }
                } catch (NullPointerException e) {
                    MethodInput m = actualStruct.getMethod(method.getName());
                    throw new MethodOverloadError(m.getName(), m.getLine(), m.getColumn());
                }

            }
            if (!method.getReturnType().equals(actualStruct.getMethod(method.getName()).getReturnType())){
                MethodInput m = actualStruct.getMethod(method.getName());
                throw new MethodOverloadReturnError(m.getName(), m.getLine(), m.getColumn());
            }

        }
    }

    //Check if the variable is already declared
    public void varCheck(StructInput actualStruct, VarInput var) {
        if (actualStruct.fetchAttribute(var.getName())) {
            throw new VarAlreadyDeclaredError(actualStruct, var.getName(), var.getLine(), var.getColumn());
        }
    }

    public void consolidationVarCheck(StructInput actualStruct, VarInput var) {
        if (actualStruct.fetchAttribute(var.getName())) {
            VarInput v = actualStruct.getAttribute(var.getName());
            throw new VarAlreadyDeclaredError(actualStruct, v.getName(), v.getLine(), v.getColumn());
        }
    }

    public void implCheck(StructInput struct) {
        if (!struct.getHasImpl()) {
            throw new NoImplError(struct.getName(), struct.getLine(), struct.getColumn());
        }
    }

    public void noStructCheck(StructInput struct) {
        if (!struct.getIsDeclared()){
            throw new NoStructError(struct.getName(), struct.getLine(), struct.getColumn());
        }
    }

    public void checkConstructor(StructInput struct) {
        if (!struct.getHasConstructor()) {
            throw new NoConstructorError(struct.getName(), struct.getLine(), struct.getColumn());
        }
    }

    public void typesCheck(StructInput struct) {

        for (String key : struct.getAttributeTable().keySet()) {
            VarInput var = struct.getAttributeTable().get(key);
            checkVarType(var);
        }
        for (String key : struct.getMethodTable().keySet()) {
            MethodInput method = struct.getMethodTable().get(key);
            checkMethodTypeReturn(method);
            for (String key2 : method.getParameterTable().keySet()) {
                ParamInput param = method.getParameterTable().get(key2);
                checkParamType(method, param);
            }
        }

    }

    public void checkVarType(VarInput var) {
        if (!symbolTable.fetchStruct(var.getType()) && !arrayTypes.contains(var.getType())){
            throw new VarTypeError(var.getName(), var.getType(), var.getLine(), var.getColumn());
        }
    }

    public void checkMethodTypeReturn(MethodInput method) {
        if (!symbolTable.fetchStruct(method.getReturnType()) && !arrayTypes.contains(method.getReturnType()) && !method.getReturnType().equals("void")) {
            throw new MethodTypeReturnError(method.getName(), method.getReturnType(), method.getLine(), method.getColumn());
        }
    }

    public void checkParamType(MethodInput method, ParamInput param) {
        if (!symbolTable.fetchStruct(param.getType()) && !arrayTypes.contains(param.getType())) {
            throw new ParamTypeError(method.getName(), param.getName(), param.getType(), param.getLine(), param.getColumn());
        }
    }


    //check if the constructor parameters type are correct
    public void checkConstructorParams(StructInput struct) {
        if(struct.getConstructor() != null) {
            for (String key : struct.getConstructor().getConstructorParams().keySet()) {
                ParamInput param = struct.getConstructor().getConstructorParams().get(key);
                if (!symbolTable.fetchStruct(param.getType())) {
                    throw new ParamTypeError("Constructor", param.getName(), param.getType(), param.getLine(), param.getColumn());
                }
            }
        }
    }
}
