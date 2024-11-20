#include "libs/loop"


final sort(args) = {
    sorted_args = args;
    while(i as loop::range(len(sorted_args) - 1)) {
        while(j as loop::range(i + 1, len(sorted_args)))
            if(sorted_args[i] > sorted_args[j]) {
                temp = sorted_args[i];
                sorted_args[i] = sorted_args[j];
                sorted_args[j] = temp;
            }
    }
    return sorted_args;
}
