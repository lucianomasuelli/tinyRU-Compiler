struct A{
    Int a,b;
}

impl A {
    .(){}
    fn met1(Int a) -> Int {
        a = 2/3+4;
        /? var = "holanda";
    }

    fn met2(Bool h) -> Int {
        h = true;
    }
}

struct B {
}

impl B {
    .(){}
    fn met1(Int a) -> Int {
        Int b;
        a = ++ b;
    }
}


start {
    Str var;
    Int a,b;
    var = "holanda";
    if (var == "holanda") {
        a = 2;
        b = 3;
       } else {
       a = 1;
       b = 2;}
}