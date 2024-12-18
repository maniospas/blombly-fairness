!include "libs/html"
!include "fairbench/metrics"
!include "fairbench/reduce"
!include "fairbench/fork"
!include "ops/strings"


configs =
    {measure=metrics.acc; reduce=reduce.min},
    {measure=metrics.pr; reduce=reduce.minratio},
    {measure=metrics.tpr; reduce=reduce.maxdiff},
    {measure=metrics.tnr; reduce=reduce.maxdiff}


preds  = (1,0,1,0,1,0,1,0,1,0)|vector;
labels = (1,1,0,1,0,0,0,1,1,1)|vector;
men    = (1,1,0,0,1,0,1,1,0,0)|vector;
women  = 1-men;
sensitive = new {fork: sensitive=men,women}


print("---- Fairness report ----");
while(config in configs) {
    value = sensitive(config: preds=preds; labels=labels);
    print("{value|left} | {value|bar}");
}


create_page = {
    // create results table
    results = "table"|dom|cssclass("table");
    results_body = "tbody"|dom;
    results += results_body;

    // fill results table body
    while(config in configs) {
        value = sensitive(config: preds=preds; labels=labels);
        results_body += ("tr"|dom) + ("td"|dom + value.name|str) + ("td"|dom + value|float);
    }

    // centering div
    center = "div"|dom|cssclass("container d-flex justify-content-center");
    center += results;

    src = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js";
    stylesheet = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css";
    return html("Title", center :: src=src; stylesheet=stylesheet);
}


routes = server(5000);
routes["/test"] = {return "Up and running"}
routes["/results"] = create_page;
while(true) {}
