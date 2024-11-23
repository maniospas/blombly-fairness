final bar(value) = {
    value |= float;
    value = int(value*10);
    ret = "";
    while(i in value|range)
        ret = ret + "■";
    return ret;
}

final left(value) = {
    default size = 20;
    ret = value|str;
    while(i in range(ret|len, size))
        ret = ret + " ";
    return ret;
}
