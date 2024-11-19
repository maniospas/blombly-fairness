#include "libs/loop"
#include "fairbench/metrics"
#include "fairbench/reduce"
#include "fairbench/fork"
#include "ops/strings"
#include "ops/html"


configs =
    {measure=metrics.acc; reduce=reduce.min;},
    {measure=metrics.pr; reduce=reduce.minratio;},
    {measure=metrics.tpr; reduce=reduce.maxdiff;},
    {measure=metrics.tpr; reduce=reduce.maxdiff;},
    {measure=metrics.tnr; reduce=reduce.maxdiff;}


preds  = (1,0,1,0,1,0,1,0,1,0)|vector;
labels = (1,1,0,1,0,0,0,1,1,1)|vector;
men    = (1,1,0,0,1,0,1,1,0,0)|vector;
women  = 1-men;
sensitive = new{fork: sensitive=men,women}


result = "table"|dom;
while(config as loop::next(configs)) {
    value = sensitive(config: preds=preds; labels=labels);
    print("{value|left} | {value|bar}");
    row = "tr"|dom;
    row += "td"|dom+value.name|str;
    row += "td"|dom+value|float;
    result += row;
}

routes = server(5000);
routes["/test"] = {return "Up and running";}
routes["/results"] = {return html("Title", result);}
while(true) {}
