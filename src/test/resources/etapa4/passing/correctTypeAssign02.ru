/? chequea el tipo de una asignación donde la expresión a asignar accede a una variable definida dentro del método.

struct A{
}

impl A {
    .(){}
    fn met1(Int a) -> Int {
        Int b;
        b = 0;
        a = 1 + b;
    }
}

start {
}