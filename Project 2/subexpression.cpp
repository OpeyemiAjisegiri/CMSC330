#include <iostream>
#include <fstream>
using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "operand.h"
#include "plus.h"
#include "minus.h"
#include "times.h"
#include "divide.h"
#include "and.h"
#include "greaterthan.h"
#include "lessthan.h"
#include "or.h"
#include "equal.h"
#include "negate.h"
#include "ternarycondition.h"

SubExpression::SubExpression(Expression* left, Expression* right, Expression* last)
{
    this->left = left;
    this->right = right;
    this->third = last;
}

SubExpression::SubExpression(Expression* left, Expression* right)
{
    this->left = left;
    this->right = right;
    this->third = nullptr;
}

SubExpression::SubExpression(Expression* left)
{
    this->left = left;
    this->right = nullptr;
    this->third = nullptr;
}


Expression* SubExpression::parse(ifstream& file)
{
    Expression* left;
    Expression* right;
    Expression* first = nullptr;
    Expression* second = nullptr;
    Expression* third = nullptr;
    char operation, paren;

    left = Operand::parse(file);
    file >> operation;
    //cin >> operation;
    right = Operand::parse(file);
    if (operation == ':') { 
        //Tried refactoring the lines below to work in the switch statement 
        // to no avail due to the scope of initialization for the first, 
        //second and third pointer
        first = left;
        second = right;
        file >> operation;
        third = Operand::parse(file);
        file >> paren;
        return new TernaryCondition(first, second, third);
    }
    else {
        file >> paren;
        //cin >> paren;
    }



    switch (operation)
    {
    case '+':
        return new Plus(left, right);
    case '-':
        return new Minus(left, right);
    case '*':
        return new Times(left, right);
    case '/':
        return new Divide(left, right);
    case '&':
        return new And(left, right);
    case '>':
        return new GreaterThan(left, right);
    case '<':
        return new LessThan(left, right);
    case '=':
        return new Equal(left, right);
    case '|':
        return new Or(left, right);
    case '!':
        return new Negate(left);
    /*case ':':
        //ternaryOperation(left, right, file);

       // first = left;
        //second = right;
        //left = Operand::parse(file);
        //file >> operation;
        //right = Operand::parse(file);
        cout << operation;
        third = Operand::parse(file);
        return new TernaryCondition(first, left, right);

    case '?':
        cout << "Entered";
        break;*/
    }
    return 0;
}