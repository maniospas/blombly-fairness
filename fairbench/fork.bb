#include "libs/loop"

final fork = {
    final \str = {return "A fork of {len(this.sensitive)} sensitive attributes"}
    final \call = {
        ret = list();
        while(sens as loop::next(this.sensitive)) {
            value = measure(sensitive=sens;labels=labels;preds=preds);
            push(ret, value);
        }
        return reduce(ret);
    }
}
