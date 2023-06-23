#include <iostream>
#include <string>
#include <vector>
#include <fstream>
using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "symboltable.h"
#include "parse.h"

SymbolTable symbolTable;

void parseAssignments(ifstream& file);

int main()
{
    Expression* expression;
    char paren, comma;
    ifstream file;
    file.open("inputFile.txt");
    if (!file) {
        cout << "No such file";
    }
    else {
        cout << "File opened and Expressions are parsed from the file. \n\n";
        while (!file.eof()) {

            //cout << "Enter Expression: ";
            file /*cin*/ >> paren;
            expression = SubExpression::parse(file);
            file/*cin*/ >> comma;
            parseAssignments(file);
            cout << "Value = " << expression->evaluate() << endl;
        }
    }
    file.close();

    /*cout << "Enter expression: ";
    cin >> paren;
    expression = SubExpression::parse();
    cin >> comma;
    parseAssignments();
    cout << "Value = " << expression->evaluate() << endl;*/
    return 0;
}

void parseAssignments(ifstream& file)
{
    char assignop, delimiter;
    string variable;
    double value;
    do
    {
        variable = parseName(file);
        file/*cin*/ >> ws >> assignop >> value >> delimiter;
        symbolTable.insert(variable, value);
    } while (delimiter == ',');
}