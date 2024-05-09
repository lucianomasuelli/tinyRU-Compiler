package tinyru.etapa3;

public class ParamInput {

        private String name;
        private String type;
        private Integer position;
        private Integer line;
        private Integer column;

        public ParamInput(String name, String type) {
            this.name = name;
            this.type = type;
            this.position = 0;
        }

        public ParamInput(String name, String type, Integer position) {
            this.name = name;
            this.type = type;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Integer getPosition() {
            return position;
        }

        public void setLine(Integer line) {
            this.line = line;
        }

        public Integer getLine() {
            return line;
        }

        public void setColumn(Integer column) {
            this.column = column;
        }

        public Integer getColumn() {
            return column;
        }
}
