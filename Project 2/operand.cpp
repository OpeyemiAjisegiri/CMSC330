#include <cctype>
#include <iostream>
#include <fstream>
#include <list>
#include <string>

using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "operand.h"
#include "variable.h"
#include "literal.h"
#include "parse.h"

Expression* Operand::parse(ifstream& file)
{
    char paren;
    double value;

    //cin >> ws;
    file >> ws;
    if (isdigit(/*cin*/file.peek()))
    {
        //cin >> value;
        file >> value;
        Expression* literal = new Literal(value);
        return literal;
    }
    if (/*cin*/file.peek() == '(')
    {
        //cin >> paren;
        file >> paren;
        return SubExpression::parse(file);
    }
    else
        return new Variable(parseName(file));
    return 0;
}