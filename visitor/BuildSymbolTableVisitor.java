package visitor;

import syntaxtree.*;

public class BuildSymbolTableVisitor extends TypeDepthFirstVisitor {

  private SymbolTable symbolTable;

  public BuildSymbolTableVisitor() {
    symbolTable = new SymbolTable();
  }

  public SymbolTable getSymTab() {
    return symbolTable;
  }

  // In global scope => both currClass and currMethod are null
  //   Contains class declaration
  // Inside a class (but not in a method) => currMethod is null
  //   Contains field and method declarations
  // Inside a method
  //   Contains declaration of local variables 
  // These two variables help keep track of the current scope.
  private Class currClass;
  private Method currMethod;

  // Note: Because in MiniJava there is no nested scopes and all local 
  // variables can only be declared at the beginning of a method. This "hack"
  // uses two variables instead of a stack to track nested level.


  // MainClass m;
  // ClassDeclList cl;
  public Type visit(Program n) {
    // Main class declaration
    n.m.accept(this);

    // Declaration of remaining classes
    for (int i = 0; i < n.cl.size(); i++) {
      n.cl.elementAt(i).accept(this);
    }

    return null;
  }

  // Identifier i1 (name of class),i2 (name of argument in main();
  // Statement s;
  public Type visit(MainClass n) {
    // this is an ugly hack.. but its not worth having a Void and
    // String[] type just for one occourance
    // The beginLine and beginColumn are no meaning here

    symbolTable.addClass(n.i1.toString(), null, -1, -1);
    currClass = symbolTable.getClass(n.i1.toString());
    currMethod = new Method("main", new IdentifierType("void"), currClass, -1, -1);
    currMethod.addVar(n.i2.toString(), new IdentifierType("String[]"), -1, -1);

    n.s.accept(this);

    currMethod = null;

    return null;
  }

  // Identifier i;  (Class name)
  // VarDeclList vl;  (Field declaration)
  // MethodDeclList ml; (Method declaration)
  public Type visit(ClassDeclSimple n) {
    String id = n.i.toString();

    if (!symbolTable.addClass(id, null, n.getBeginLine(), n.getBeginColumn())) {
      Class conflictedClass = symbolTable.getClass(id);
      System.err.printf("%s: class Redeclaration (%s,%s; %s,%s)%n", id, conflictedClass.getBeginLine(), conflictedClass.getBeginColumn(), n.getBeginLine(), n.getBeginColumn());
    }

    // Entering a new class scope (no need to explicitly leave a class scope)
    currClass = symbolTable.getClass(id);

    // Process field declaration
    for (int i = 0; i < n.vl.size(); i++) {
      n.vl.elementAt(i).accept(this);
    }

    // Process method declaration
    for (int i = 0; i < n.ml.size(); i++) {
      n.ml.elementAt(i).accept(this);
    }
    return null;
  }

  // Identifier i; (Class name)
  // Identifier j; (Superclass's name)
  // VarDeclList vl;  (Field declaration)
  // MethodDeclList ml; (Method declaration)
  public Type visit(ClassDeclExtends n) {
    String id = n.i.toString();

    if (!symbolTable.addClass(id, n.j.toString(), n.getBeginLine(), n.getBeginColumn())) {
      Class conflictedClass = symbolTable.getClass(id);
      System.err.printf("%s: class Redeclaration (%s,%s; %s,%s)%n", id, conflictedClass.getBeginLine(), conflictedClass.getBeginColumn(), n.getBeginLine(), n.getBeginColumn());
    }

    // Entering a new class scope (no need to explicitly leave a class scope)
    currClass = symbolTable.getClass(id);

    for (int i = 0; i < n.vl.size(); i++) {
      n.vl.elementAt(i).accept(this);
    }
    for (int i = 0; i < n.ml.size(); i++) {
      n.ml.elementAt(i).accept(this);
    }
    return null;
  }

  // Type t;
  // Identifier i;
  //
  // Field delcaration or local variable declaration
  public Type visit(VarDecl n) {
    Type t = n.t.accept(this);
    String id = n.i.toString();

    // Not inside a method => a field declaration
    if (currMethod == null) {
      // Add a field

      if (!currClass.addVar(id, t, n.token.beginLine, n.token.beginColumn)) {
        Variable conflictedVar = currClass.getVar(id);
        System.err.printf("%s: Redeclaration (%s,%s:%s; %s,%s)%n", id, conflictedVar.getBeginLine(), conflictedVar.getBeginColumn(), conflictedVar.getScopingMethod().getInternalId(), n.token.beginLine, n.token.beginColumn);
      }
    } else {
      // Add a local variable

      for (Variable conflictedParam : currMethod.params) {
        if (conflictedParam.getId().equals(id)) {
          System.err.printf("%s: Redeclaration (%s,%s:%s; %s,%s)%n", id, conflictedParam.getBeginLine(), conflictedParam.getBeginColumn(), conflictedParam.getScopingMethod().getInternalId(), n.token.beginLine, n.token.beginColumn);
          return null;
        }
      }

      if (!currMethod.addVar(id, t, n.token.beginLine, n.token.beginColumn)) {
        Variable conflictedVar = currMethod.getVar(id);
        System.err.printf("%s: Redeclaration (%s,%s:%s; %s,%s)%n", id, conflictedVar.getBeginLine(), conflictedVar.getBeginColumn(), conflictedVar.getScopingMethod().getInternalId(), n.token.beginLine, n.token.beginColumn);
      }
    }

    return null;
  }

  // Type t;  (Return type)
  // Identifier i; (Method name)
  // FormalList fl; (Formal parameters)
  // VarDeclList vl; (Local variables)
  // StatementList sl; 
  // Exp e; (The expression that evaluates to the return value)
  //
  // Method delcaration
  public Type visit(MethodDecl n) {
    Type t = n.t.accept(this);
    String id = n.i.toString();

    if (!currClass.addMethod(id, t, n.getBeginLine(), n.getBeginColumn())) {
      Method conflictedMethod = currClass.getMethod(id);
      System.err.printf("%s: method Redeclaration (%s,%s; %s,%s)%n", id, conflictedMethod.getBeginLine(), conflictedMethod.getBeginColumn(), n.getBeginLine(), n.getBeginColumn());
    }

    // Entering a method scope 
    currMethod = currClass.getMethod(id);

    for (int i = 0; i < n.fl.size(); i++) {
      n.fl.elementAt(i).accept(this);
    }
    for (int i = 0; i < n.vl.size(); i++) {
      n.vl.elementAt(i).accept(this);
    }
    for (int i = 0; i < n.sl.size(); i++) {
      n.sl.elementAt(i).accept(this);
    }

    n.e.accept(this);

    // Leaving a method scope (return to class scope)
    currMethod = null;
    return null;
  }

  // Type t;
  // Identifier i;
  // 
  // Register a formal parameter
  public Type visit(Formal n) {
    Type t = n.t.accept(this);
    String id = n.i.toString();

    if (!currMethod.addParam(id, t, n.token.beginLine, n.token.beginColumn)) {
      Variable param = currMethod.getParam(id);
      System.err.printf("%s: Redeclaration (%s,%s:%s; %s,%s)%n", id, param.getBeginLine(), param.getBeginColumn(), param.getScopingMethod().getInternalId(), n.token.beginLine, n.token.beginColumn);
    }

    return null;
  }

  public Type visit(IntArrayType n) {
    return n;
  }

  public Type visit(BooleanType n) {
    return n;
  }

  public Type visit(IntegerType n) {
    return n;
  }

  public Type visit(DoubleType n) {
    return n;
  }

  // String s;
  public Type visit(IdentifierType n) {
    return n;
  }

  // StatementList sl;
  // Optional for MiniJava (unless variable declaration is allowed inside
  // a block
  public Type visit(Block n) {
    for (int i = 0; i < n.sl.size(); i++) {
      n.sl.elementAt(i).accept(this);
    }
    return null;
  }
}
