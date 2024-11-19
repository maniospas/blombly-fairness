#include "fairbench/utils"
#include "ops/sort"


final reduce = new {final explainable = explainable; final sort = sort;}


reduce.min(args) = {
    best = args[0];
    while(i as loop::range(1, len(args)))
        if(args[i] < best)
            best = args[i];
    return new {
        explainable:
        final name = "min " + best.name;
        final desc = "minimum of " + best.desc;
        final value = best | float;
        final explain = args;
    }
}

reduce.max(args) = {
    best = args[0];
    while(i as loop::range(1, len(args)))
        if(args[i] > best)
            best = args[i];
    return new {
        explainable:
        final name = "max " + best.name;
        final desc = "maximum of " + best.desc;
        final value = best | float;
        final explain = args;
    }
}

reduce.std(args) = {
    mean = sum(args) / len(args);
    squares = 0;
    sums = 0;
    while(x as loop::next(args)) {
        squares += (x - mean)^2;
        sums += x;
    }
    variance = squares / len(args);
    std_dev = sqrt(variance);
    return new {
        explainable:
        final name = x.name+" std";
        final desc = "standard deviation of "+args[0].desc;
        final value = std_dev;
        final explain = args;
    }
}

reduce.gini(args) = {
    // Sort args in ascending order
    sorted_args = args|sort;
    n = len(sorted_args);
    cumulative_sum = 0;
    total_sum = sum(sorted_args);
    while(i as loop::range(n)) {
        cumulative_sum += (i + 1) * sorted_args[i];
    }
    gini = (2 * cumulative_sum) / (n * total_sum) - (n + 1) / n;
    return new {
        explainable:
        final name = args[0].name+" gini";
        final desc = "gini coefficient of "+args[0].desc;
        final value = gini;
        final explain = args;
    }
}

reduce.minratio(args) = {
    sorted_args = args|sort;
    min_ratio = sorted_args[0]|float / sorted_args[len(args)-1]|float;
    return new {
        explainable:
        final name = args[0].name+" minratio";
        final desc = "minimum ratio between "+args[0].desc;
        final value = min_ratio;
        final explain = args;
    }
}

reduce.maxdiff(args) = {
    sorted_args = args|sort;
    max_difference = sorted_args[len(args)-1]|float - sorted_args[0]|float;
    return new {
        explainable:
        final name = args[0].name+" maxdiff";
        final desc = "maximum difference between "+args[0].desc;
        final value = max_difference;
        final explain = args;
    }
}
