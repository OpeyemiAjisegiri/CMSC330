#include <cctype> 
#include <iostream> 
#include <fstream>
#include <string> 
using namespace std;

#include "parse.h"

string parseName(ifstream& file)
{
    char alnum;
    string name = "";

    //cin >> ws;
    file >> ws;
    while (isalnum(/*cin*/file.peek()))
    {
        //cin >> alnum;
        file >> alnum;
        name += alnum;
    }
    return name;
}