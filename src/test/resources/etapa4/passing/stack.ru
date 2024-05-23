struct S {
    N h;
    }

impl S {
    .() { self.h = new N(); }
    fn add(Int v) -> void {
        N node;
        node = new N();
        (node.setValue(v));
        (node.n.setNext(self.h));
        self.h = node;
    }
}

struct N { Int v;
    N n;
    }

impl N {
    .() { }
    fn setValue(Int v) -> void { self.v = v;}
    fn setNext(N n) -> void { self.n = n; }
}

start {
    S s;
    s = new S();
    (s.add(1));
}