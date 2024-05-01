package tinyru.etapa3;

import tinyru.etapa3.SymbolTable;

import java.io.FileWriter;
import java.io.IOException;

public class JSONGenerator {
    private String filename;

    public JSONGenerator(String filename){
        this.filename = filename;
    }

    //Crea el json
    public String jasonify(SymbolTable st){
        String json;
        json = "{\n";
        json += "\"nombre\": \"" + this.filename + "\",\n";
        json += "\"structs\": [\n";

        for (String key : st.getStructTable().keySet()) {
            StructInput struct = st.getStructTable().get(key);
            json += "\t{\n";
            json += "\t\"nombre\": \"" + key + "\",\n";
            json += "\t\"herencia\": \"" + struct.getInheritanceName() + "\",\n";

            json += "\t\"atributos\": [";
            for (String key2 : struct.getAttributeTable().keySet()) {
                VarInput attribute = struct.getAttributeTable().get(key2);
                json += "\n\t\t{\n";
                json += "\t\t\"nombre\": \"" + key2 + "\",\n";
                json += "\t\t\"tipo\": \"" + attribute.getType() + "\",\n";
                json += "\t\t\"visibilidad\": \"" + attribute.getVisibility() + "\"\n";
                json += "\t\t\"posicion\": " + attribute.getPosition() + "\n";
                if(struct.getAttributeTable().size() > 1)
                    json += "\t\t},\n";
                else
                    json += "\t\t}\n";
            }
            if(struct.getAttributeTable().size() > 1)
                json += "\t],\n";
            else {
                json += "\t]\n";
            }

            json += "\t\"metodos\": [\n";
            for (String key2 : struct.getMethodTable().keySet()) {
                MethodInput method = struct.getMethodTable().get(key2);
                json += " \t\t{\n";
                json += " \t\t\"nombre\": \"" + key2 + "\",\n";
                json += " \t\t\"retorno\": \"" + method.getReturnType() + "\",\n";
                json += " \t\t\"posicion\": " + method.getPosition() + "\n";
                json += " \t\t\"parametros\": [";
                for (String key3 : method.getParameterTable().keySet()) {
                    ParamInput parameter = method.getParameterTable().get(key3);
                    json += " \n\t\t\t{\n";
                    json += " \t\t\t\"nombre\": \"" + key3 + "\",\n";
                    json += " \t\t\t\"tipo\": \"" + parameter.getType() + "\"\n";
                    json += " \t\t\t\"posicion\": " + parameter.getPosition() + "\n";
                    json += " \t\t\t},\n";
                }
                if(method.getParameterTable().size() > 1)
                    json += "\t\t\t],\n";
                else
                    json += "\t\t\t]\n";
                json += "\t\t},\n";
            }
            if(struct.getMethodTable().size() > 1)
                json += "\t],\n";
            else
                json += "\t]\n";

            json += "\t\"constructor\": {\n";

            if(struct.getConstructor() != null){
                json += "\t\t\"parametros\": [";
                for (String key4 : struct.getConstructor().getConstructorParams().keySet()) {
                    ParamInput parameter = struct.getConstructor().getConstructorParams().get(key4);
                    json += "\n\t\t\t{\n";
                    json += "\t\t\t\"nombre\": \"" + key4 + "\",\n";
                    json += "\t\t\t\"tipo\": \"" + parameter.getType() + "\"\n";
                    json += "\t\t\t\"posicion\": " + parameter.getPosition() + "\n";
                    if(struct.getConstructor().getConstructorParams().size() > 1)
                        json += "\t\t\t},\n";
                    else
                        json += "\t\t\t}\n";
                }
                if(struct.getConstructor().getConstructorParams().size() > 1)
                    json += "\t\t],\n";
                else
                    json += "\t\t]\n";
            }
            json += "\t}\n";
        }
        json += "\"start\": {\n";
        json += "\t\"nombre\": " + st.getStart().getName() + "\n";
        json += "\t\"atributos\": [";
        for (String key : st.getStart().getAttributeTable().keySet()) {
            VarInput attribute = st.getStart().getAttributeTable().get(key);
            json += "\n\t\t{\n";
            json += "\t\t\"nombre\": \"" + key + "\",\n";
            json += "\t\t\"tipo\": \"" + attribute.getType() + "\",\n";
            json += "\t\t\"posicion\": " + attribute.getPosition() + "\n";
            if(st.getStart().getAttributeTable().size() > 1)
                json += "\t\t},\n";
            else
                json += "\t\t}\n";
        }

        json += "]\n";
        json += "}\n";
        //añade el start
        createJSON(json);
        return json;
    }

    public void createJSON(String json){
        try {
            FileWriter myWriter = new FileWriter(filename + ".json");
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
