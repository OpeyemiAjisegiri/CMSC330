class Operand : public Expression
{
public:
    static Expression* parse(ifstream& file);
};