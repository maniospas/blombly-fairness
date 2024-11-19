final explainable = {
    \str = {return "{name} {value}"}
    \float = {return value}
    \lt(other) = {return value<other.value}
    \gt(other) = {return value>other.value}
}

final prepare = {
    // convert to vectors
    preds     |= vector;
    labels    |= vector;
    sensitive |= vector;
    n_preds     = len(preds);
    n_labels    = len(labels);
    n_sensitive = len(sensitive);
    if(n_preds!=n_labels)
        fail("Predictions have length {n_preds} that is different from label length {n_labels}");
    if(n_preds!=n_sensitive)
        fail("Predictions and labels have length {n_preds} that is different from sensitive length {n_sensitive}");
}
