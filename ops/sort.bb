final sort(args) = {
    sorted_args = args;
    while(i as next(!of range(sorted_args|len-1))) {
        while(j as next(!of range(i + 1, sorted_args|len)))
            if(sorted_args[i] > sorted_args[j]) {
                temp = sorted_args[i];
                sorted_args[i] = sorted_args[j];
                sorted_args[j] = temp;
            }
    }
    return sorted_args;
}
