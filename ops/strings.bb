final bar(value) = {
    value |= float;
    value = int(value*10);
    ret = "";
    while(i as loop::range(value))
        ret = ret + "■";
    return ret;
}

final left(value) = {
    default size = 20;
    ret = value|str;
    while(i as loop::range(len(ret), size))
        ret = ret + " ";
    return ret;
}
