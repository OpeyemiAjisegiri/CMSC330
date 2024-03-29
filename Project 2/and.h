#pragma once
class And : public SubExpression
{
public:
    And(Expression* left, Expression* right) :
        SubExpression(left, right)
    {
    }
    double evaluate()
    {
        return left->evaluate() && right->evaluate();
    }
};