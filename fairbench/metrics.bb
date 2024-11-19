#include "fairbench/utils"
#include "ops/sort"

final metrics = new {
    final prepare = prepare;
    final explainable = explainable;
}

metrics.acc = {
    prepare:
    correct = preds*labels + (1 - preds) * (1 - labels);
    correct = sum(correct * sensitive);
    samples = sum(sensitive);
    return new {
        explainable:
        name = "acc";
        desc = "accuracy";
        value = try if (samples == 0) return 1 else return correct / samples;
        correct = correct;
        samples = samples;
    }
}

metrics.pr = {
    prepare:
    positives = preds * sensitive;
    total_samples = sum(sensitive);
    positive_count = sum(positives);
    return new {
        explainable:
        name = "pr";
        desc = "positive rate";
        value = try if (total_samples == 0) return 0 else return positive_count / total_samples;
        positive_count = positive_count;
        total_samples = total_samples;
    }
}

metrics.tpr = {
    prepare:
    true_positives = preds * labels * sensitive;
    positives = labels * sensitive;
    tp_sum = sum(true_positives);
    pos_sum = sum(positives);
    return new {
        explainable:
        name = "tpr";
        desc = "true positive rate";
        value = try if (pos_sum == 0) return 0 else return tp_sum / pos_sum;
        true_positives = tp_sum;
        positives = pos_sum;
    }
}

metrics.tnr = {
    prepare:
    true_negatives = (1 - preds) * (1 - labels) * sensitive;
    negatives = (1 - labels) * sensitive;
    tn_sum = sum(true_negatives);
    neg_sum = sum(negatives);
    return new {
        explainable:
        name = "tnr";
        desc = "true negative rate";
        value = try if (neg_sum == 0) return 0 else return tn_sum / neg_sum;
        true_negatives = tn_sum;
        negatives = neg_sum;
    }
}
