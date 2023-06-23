class SubExpression : public Expression
{
public:
    SubExpression(Expression* left);
    SubExpression(Expression* left, Expression* right);
    //The line below is to be used for ternary operation
    SubExpression(Expression* left, Expression* right,Expression* third);
    static Expression* parse(ifstream& file);
protected:
    Expression* left;
    Expression* right;
    Expression* third;
};