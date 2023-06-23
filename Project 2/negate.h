#pragma once
class Negate : public SubExpression
{
public:
    Negate(Expression* left) : SubExpression(left)
    {
    }
    double evaluate()
    {
        return !left->evaluate();
    }
};