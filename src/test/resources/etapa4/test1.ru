
struct A{
    Int x;
}

impl A {
    .(Int y){
        x = y;
    }
    fn sum(Int x) -> Int {
        if(x == 0) {
            ret 0;
        }
        else {
            ret x + sum(x - 1);
        }
        ret 2;
    }
}

start {
    A a;
    Int b;
    a = new A(10);
    b = 2;
}