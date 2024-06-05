
struct A{}

impl A {
    .(){}
    fn met1(Int a, Int b) -> Int {
        ret a + b;
    }
}

start {
    A a;
    Int res;
    a = new A();
    (a.met1(1, 2));
}