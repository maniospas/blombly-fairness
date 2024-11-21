final bar(value) = {
    value |= float;
    value = int(value*10);
    ret = "";
    while(i as next(!of value|range|iter))
        ret = ret + "■";
    return ret;
}

final left(value) = {
    default size = 20;
    ret = value|str;
    while(i as next(!of range(ret|len, size)))
        ret = ret + " ";
    return ret;
}
