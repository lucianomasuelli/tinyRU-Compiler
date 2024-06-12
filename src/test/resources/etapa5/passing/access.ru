struct A{
    Int x;
}

impl A {
    .(Int y){
        x = y;
    }
}


start {
    A a;
    a = new A(5);
    a.x = 10;
    (IO.out_int(a.x));
}