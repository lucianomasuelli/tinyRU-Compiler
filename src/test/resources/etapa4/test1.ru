struct A{
    Int x;
}

impl A {
    .(Int j){
        x = j;
    }

    fn met1(Int w) -> Int {
        ret w*2;
    }
}

start {
    A a;
    Int c;
    a = new A(10);
    c = a.met1(6);
}