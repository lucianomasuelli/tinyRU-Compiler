/? Uso del self en un objeto

struct A {
    Int a;
    Int b;
}

impl A {
    .(Int a, Int b) {
        self.a = a;
        self.b = b;
    }
    fn compare() -> Bool {
    ret self.b == self.a;
    }
}

start {
    A a;
    a = new A(1,2);
    (a.compare());
    }