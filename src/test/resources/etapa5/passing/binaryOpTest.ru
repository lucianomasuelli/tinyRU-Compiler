start {
    Int a,b;
    Bool t,f;
    a = 1;
    b = 5;
    t = true;
    f = false;
    (IO.out_int(a+b));
    (IO.out_int(a-b));
    (IO.out_int(a*b));
    (IO.out_int(a/b));
    (IO.out_int(a % b));
    (IO.out_bool(a==b));
    (IO.out_bool(a != b));
    (IO.out_bool(a<b));
    (IO.out_bool(a<=b));
    (IO.out_bool(a>b));
    (IO.out_bool(a>=b));
    (IO.out_bool(t&&f));
    (IO.out_bool(t||f));
}