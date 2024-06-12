start{
    Int i;
    i = 0;
    while (i<10){
        if(i==5){
            (IO.out_int(i));
        }
        else{
            (IO.out_int(i*2));
        }
        i=i+1;
    }
}