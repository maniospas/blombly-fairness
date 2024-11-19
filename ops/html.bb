#include "libs/loop"


final html(title, body) = {
    default script = "";
    return "<!DOCTYPE html>
        <html>
        <head>
            <title>{title|str}</title>
            <script>{script|str}</script>
        </head>
        <body>
        {body}
        </body>
        </html>
    ";
}

final dom(name) = {
    return new {
        name = name;
        contents = list();
        \str = {
            contents = "";
            while(element as loop::next(this.contents))
                contents = contents + element|str;
            ret = "<{this.name}>{contents}</{this.name}>";
            print(ret);
            return ret;
        }
        \add(other) = {push(this.contents, other);return this}
    }
}
