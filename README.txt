.
├── Makefile
├── README.txt
├── Task1Main.java (New)
├── Task2Main.java (New)
├── input
│   ├── BinarySearch.java
│   ├── BinaryTree.java
│   ├── BubbleSort.java
│   ├── Factorial.java
│   ├── LinearSearch.java
│   ├── LinkedList.java
│   ├── QuickSort.java
│   └── TreeVisitor.java (Modified, more test errors for task 2)
├── minijava.jj (Modified, operator precedence, inject_token)
├── syntaxtree (Some classes are inherited from HasToken to preserve the token when parsing)
│   ├── And.java
│   ├── ArrayAssign.java
│   ├── ArrayLength.java
│   ├── ArrayLookup.java
│   ├── Assign.java
│   ├── Block.java
│   ├── BooleanType.java
│   ├── Call.java
│   ├── ClassDecl.java
│   ├── ClassDeclExtends.java
│   ├── ClassDeclList.java
│   ├── ClassDeclSimple.java
│   ├── DoubleType.java
│   ├── Exp.java
│   ├── ExpList.java
│   ├── False.java
│   ├── FloatingPointLiteral.java
│   ├── Formal.java
│   ├── FormalList.java
│   ├── HasToken.java
│   ├── Identifier.java
│   ├── IdentifierExp.java
│   ├── IdentifierType.java
│   ├── If.java
│   ├── IntArrayType.java
│   ├── IntegerLiteral.java
│   ├── IntegerType.java
│   ├── LessThan.java
│   ├── MainClass.java
│   ├── MethodDecl.java
│   ├── MethodDeclList.java
│   ├── Minus.java
│   ├── NewArray.java
│   ├── NewObject.java
│   ├── Not.java
│   ├── Plus.java
│   ├── Print.java
│   ├── Program.java
│   ├── Statement.java
│   ├── StatementList.java
│   ├── This.java
│   ├── Times.java
│   ├── True.java
│   ├── Type.java
│   ├── VarDecl.java
│   ├── VarDeclList.java
│   └── While.java
└── visitor
    ├── BuildSymbolTableVisitor.java (Modified)
    ├── DepthFirstVisitor.java
    ├── SymbolTable.java (Modified)
    ├── Task1TypeCheckExpVisitor.java (New)
    ├── Task1Visitor.java (New)
    ├── Task2TypeCheckExpVisitor.java (New)
    ├── Task2Visitor.java (New)
    ├── TypeDepthFirstVisitor.java
    ├── TypeVisitor.java
    └── Visitor.java

a. Instructions to run your programs.

make clean && make && make test1
make clean && make && make test2


b. The assumptions you make, if any.
1. All type errors will not stop the type checking.
2. Redeclared variable will not override the first declared variable.
3. All type checking errors are outputted to "System.err".
4. Done overloading. (Bonus)


c. Description of the input files you used to test your programs.
I have added more test cases in the ./input/TreeVisitor.java.
1. Redeclaration
2. Unknown assignment
3. Type Promotion
4. Mixed integer and double in calculation
5. Comparision between integer and double
6. Mixed boolean with integer
7. Undefined method
8. Called method with wrong parameters
9. Redeclared variable assignment (Assumption 2)
10. Overloading (Bonus)


d. Acknowledgement of third party code, files, and library (besides those included in chap4.zip) you used in your solution.

Java source files obtained from
http://www.cambridge.org/resources/052182060X/

minijava.jj obtained from
http://www.cambridge.org/resources/052182060X/lecturers/default.htm


e. Any additional information that could help us understand your implementation.
No.
