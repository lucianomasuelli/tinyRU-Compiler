
struct A{
    Int x;
}

impl A {
    .(Int y){
        x = y;
    }

    fn met1() -> Int {
        Int c;
        c = 5;
        ret c;
    }
}

start {
    A a;
    Int b;
    a = new A(10);
    b = a.met1();
}