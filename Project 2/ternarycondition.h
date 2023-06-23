#pragma once
class TernaryCondition : public SubExpression
{
public:
    TernaryCondition(Expression* left, Expression* right, Expression* third) :
        SubExpression(left, right, third)
    {
    }
    double evaluate()
    {
        //return left->evaluate() || right->evaluate();
        return third->evaluate() ? left->evaluate() : right->evaluate();
    }
};