final fork = {
    final \str = {return "A fork of {len(this.sensitive)} sensitive attributes"}
    final \call = {
        ret = list();
        while(sens as next(!of this.sensitive|iter)) {
            value = measure(sensitive=sens;labels=labels;preds=preds);
            push(ret, value);
        }
        return reduce(ret);
    }
}
