package visitor;

import syntaxtree.*;

public class Task2Visitor extends DepthFirstVisitor {

  static Class currClass;
  static Method currMethod;
  static SymbolTable symbolTable;

  public Task2Visitor(SymbolTable s) {
    symbolTable = s;
  }

  // MainClass m;
  // ClassDeclList cl;
  public void visit(Program n) {
    n.m.accept(this);
    for (int i = 0; i < n.cl.size(); i++) {
      n.cl.elementAt(i).accept(this);
    }
  }

  // Identifier i1,i2;
  // Statement s;
  public void visit(MainClass n) {
    String i1 = n.i1.toString();
    currClass = symbolTable.getClass(i1);

    n.i2.accept(this);
    n.s.accept(this);
  }

  // Identifier i;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclSimple n) {
    String id = n.i.toString();
    currClass = symbolTable.getClass(id);


    for (int i = 0; i < n.vl.size(); i++) {
      n.vl.elementAt(i).accept(this);
    }
    for (int i = 0; i < n.ml.size(); i++) {
      n.ml.elementAt(i).accept(this);
    }
  }

  // Identifier i;
  // Identifier j;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclExtends n) {
    String id = n.i.toString();
    currClass = symbolTable.getClass(id);

    n.j.accept(this);
    for (int i = 0; i < n.vl.size(); i++) {
      n.vl.elementAt(i).accept(this);
    }
    for (int i = 0; i < n.ml.size(); i++) {
      n.ml.elementAt(i).accept(this);
    }
  }

  // Type t;
  // Identifier i;
  public void visit(VarDecl n) {
    n.t.accept(this);
    n.i.accept(this);
  }

  // Type t;
  // Identifier i;
  // FormalList fl;
  // VarDeclList vl;
  // StatementList sl;
  // Exp e;
  public void visit(MethodDecl n) {
    n.t.accept(this);
    String id = n.i.toString();
    currMethod = currClass.getMethod(id);

    Type retType = currMethod.getType();
    for (int i = 0; i < n.fl.size(); i++) {
      n.fl.elementAt(i).accept(this);
    }
    for (int i = 0; i < n.vl.size(); i++) {
      n.vl.elementAt(i).accept(this);
    }
    for (int i = 0; i < n.sl.size(); i++) {
      n.sl.elementAt(i).accept(this);
    }

    if (symbolTable.compareTypes(retType, n.e.accept(new Task2TypeCheckExpVisitor())) == false) {
      System.err.printf("Wrong return type for method %s (%s,%s)%n", currMethod.getUniqueId(), n.getBeginLine(), n.getBeginColumn());
    }
  }

  // Type t;
  // Identifier i;
  public void visit(Formal n) {
    n.t.accept(this);
    n.i.accept(this);
  }

  // Exp e;
  // Statement s1,s2;
  public void visit(If n) {
    if (!(n.e.accept(new Task2TypeCheckExpVisitor()) instanceof BooleanType)) {
      System.out.printf("The condition of while must be of type boolean (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
    }

    n.s1.accept(this);
    n.s2.accept(this);
  }

  // Exp e;
  // Statement s;
  public void visit(While n) {
    if (!(n.e.accept(new Task2TypeCheckExpVisitor()) instanceof BooleanType)) {
      System.err.printf("The condition of while must be of type boolean (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
    }

    n.s.accept(this);
  }

  // Exp e;
  public void visit(Print n) {
    if (!(n.e.accept(new Task2TypeCheckExpVisitor()) instanceof IntegerType)) {
      System.err.printf("The argument of System.out.println must be of type int (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
    }
  }

  // Identifier i;
  // Exp e;
  public void visit(Assign n) {
    String id = n.i.toString();
    Type t1 = symbolTable.getVarType(currMethod, currClass, id);
    Type t2 = n.e.accept(new Task2TypeCheckExpVisitor());

    if (t1 == null) {
      System.err.printf("%s: Unknown identifier (%s,%s:%s)%n", id, n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
      return;
    }

    if (symbolTable.compareTypes(t1, t2) == false) {
      System.err.printf("%s: Type error in assignment (%s,%s:%s)%n", id, n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
      return;
    }
  }

  // Identifier i;
  // Exp e1,e2;
  public void visit(ArrayAssign n) {
    Type typeI = symbolTable.getVarType(currMethod, currClass, n.i.toString());

    if (typeI == null) {
      System.err.printf("%s: Unknown identifier (%s,%s:%s)%n", n.i.toString(), n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
      return;
    }

    if (!(typeI instanceof IntArrayType)) {
      System.err.printf("The identifier in an array assignment must be of type int [] (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
      return;
    }

    if (!(n.e1.accept(new Task2TypeCheckExpVisitor()) instanceof IntegerType)) {
      System.err.printf("The first expression in an array assignment must be of type int (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
      return;
    }

    if (!(n.e1.accept(new Task2TypeCheckExpVisitor()) instanceof IntegerType)) {
      System.err.printf("The second expression in an array assignment must be of type int (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), currMethod.getInternalId());
      return;
    }
  }
}



