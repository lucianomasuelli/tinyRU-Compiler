/? Valida el acceso a un atributo heredado con visibilidad privada

struct A{
    pri Int x;
}

impl A {
    .(Int x){

    }
}

struct B:A {
    pri Int y;
}

impl B {
    .(){}
    fn met2() -> Int {
        B b;
        x = 3;
    }
}


start {
}