#pragma once
class Equal : public SubExpression
{
public:
    Equal(Expression* left, Expression* right) :
        SubExpression(left, right)
    {
    }
    double evaluate()
    {
        return left->evaluate() == right->evaluate();
    }
};