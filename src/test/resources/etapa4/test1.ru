
struct A{}

impl A {
    .(){}
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

}