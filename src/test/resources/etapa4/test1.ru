
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
    Int b;
    a = new A(10);
    b = 2;
}