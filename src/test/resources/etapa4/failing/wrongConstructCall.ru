/? hace una llamada a constructor con argumentos de tipo incorrecto.

struct A{
    pri Int x;
}

impl A {
    .(Int x){

    }
    fn met1(Int a) -> Int {
        A d;
        Array Int c;
        a = 1;
        c = new Int [6 + 7 * a];
        d = new B(c);
    }
}

struct B:A {
    pri Int y;
}

impl B {
    .(Int g){}
}


start {
}