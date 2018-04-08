package visitor;

import syntaxtree.*;

public class Task2TypeCheckExpVisitor extends TypeDepthFirstVisitor {

  // Exp e1,e2;
  public Type visit(And n) {
    if (!(n.e1.accept(this) instanceof BooleanType)) {
      System.err.printf("Left side of AND must be of type boolean (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (!(n.e2.accept(this) instanceof BooleanType)) {
      System.err.printf("Right side of AND must be of type boolean (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    return new BooleanType();
  }

  // Exp e1,e2;
  public Type visit(LessThan n) {
    if (!(n.e1.accept(this) instanceof IntegerType || n.e1.accept(this) instanceof DoubleType)) {
      System.err.printf("Left side of LESS THAN must be either integer or double (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (!(n.e2.accept(this) instanceof IntegerType || n.e2.accept(this) instanceof DoubleType)) {
      System.err.printf("Right side of LESS THAN must be either integer or double (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (n.e1.accept(this) instanceof IntegerType && n.e2.accept(this) instanceof IntegerType) {
      return new BooleanType();
    }

    if (n.e1.accept(this) instanceof DoubleType && n.e2.accept(this) instanceof IntegerType) {
      return new BooleanType();
    }

    if (n.e1.accept(this) instanceof IntegerType && n.e2.accept(this) instanceof DoubleType) {
      return new BooleanType();
    }

    if (n.e1.accept(this) instanceof DoubleType && n.e2.accept(this) instanceof DoubleType) {
      return new BooleanType();
    }

    return null;
  }

  // Exp e1,e2;
  public Type visit(Plus n) {
    if (!(n.e1.accept(this) instanceof IntegerType || n.e1.accept(this) instanceof DoubleType)) {
      System.err.printf("Left side of PLUS must be either integer or double (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (!(n.e2.accept(this) instanceof IntegerType || n.e2.accept(this) instanceof DoubleType)) {
      System.err.printf("Right side of PLUS must be either integer or double (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (n.e1.accept(this) instanceof IntegerType && n.e2.accept(this) instanceof IntegerType) {
      return new IntegerType();
    }

    if (n.e1.accept(this) instanceof DoubleType && n.e2.accept(this) instanceof IntegerType) {
      return new DoubleType();
    }

    if (n.e1.accept(this) instanceof IntegerType && n.e2.accept(this) instanceof DoubleType) {
      return new DoubleType();
    }

    if (n.e1.accept(this) instanceof DoubleType && n.e2.accept(this) instanceof DoubleType) {
      return new DoubleType();
    }

    return null;
  }

  // Exp e1,e2;
  public Type visit(Minus n) {
    if (!(n.e1.accept(this) instanceof IntegerType || n.e1.accept(this) instanceof DoubleType)) {
      System.err.printf("Left side of MINUS must be either integer or double (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (!(n.e2.accept(this) instanceof IntegerType || n.e2.accept(this) instanceof DoubleType)) {
      System.err.printf("Right side of MINUS must be either integer or double (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (n.e1.accept(this) instanceof IntegerType && n.e2.accept(this) instanceof IntegerType) {
      return new IntegerType();
    }

    if (n.e1.accept(this) instanceof DoubleType && n.e2.accept(this) instanceof IntegerType) {
      return new DoubleType();
    }

    if (n.e1.accept(this) instanceof IntegerType && n.e2.accept(this) instanceof DoubleType) {
      return new DoubleType();
    }

    if (n.e1.accept(this) instanceof DoubleType && n.e2.accept(this) instanceof DoubleType) {
      return new DoubleType();
    }

    return null;
  }

  // Exp e1,e2;
  public Type visit(Times n) {
    if (!(n.e1.accept(this) instanceof IntegerType || n.e1.accept(this) instanceof DoubleType)) {
      System.err.printf("Left side of MINUS must be either integer or double (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (!(n.e2.accept(this) instanceof IntegerType || n.e2.accept(this) instanceof DoubleType)) {
      System.err.printf("Right side of MINUS must be either integer or double (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (n.e1.accept(this) instanceof IntegerType && n.e2.accept(this) instanceof IntegerType) {
      return new IntegerType();
    }

    if (n.e1.accept(this) instanceof DoubleType && n.e2.accept(this) instanceof IntegerType) {
      return new DoubleType();
    }

    if (n.e1.accept(this) instanceof IntegerType && n.e2.accept(this) instanceof DoubleType) {
      return new DoubleType();
    }

    if (n.e1.accept(this) instanceof DoubleType && n.e2.accept(this) instanceof DoubleType) {
      return new DoubleType();
    }

    return new IntegerType();
  }

  // Exp e1,e2;
  public Type visit(ArrayLookup n) {
    if (!(n.e1.accept(this) instanceof IntArrayType)) {
      System.out.printf("Left side of ArrayLookup must be of type integer (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    if (!(n.e2.accept(this) instanceof IntegerType)) {
      System.out.printf("Right side of ArrayLookup must be of type integer (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    return new IntegerType();
  }

  // Exp e;
  public Type visit(ArrayLength n) {
    if (!(n.e.accept(this) instanceof IntArrayType)) {
      System.out.printf("Left side of ArrayLength must be of type integer (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    return new IntegerType();
  }

  // Exp e;
  // Identifier i;
  // ExpList el;
  public Type visit(Call n) {
    String methodId = n.i.toString();
    String classId = ((IdentifierType) n.e.accept(this)).s;

    if (!(n.e.accept(this) instanceof IdentifierType)) {
      System.err.printf("Method %s should be called on Class or Object, while %s is not a valid class (%s,%s:%s)%n", methodId, classId, n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    Method calledMethod = Task2Visitor.symbolTable.getMethod(methodId, classId);

    if (calledMethod == null) {
      System.err.printf("Method %s not defined in %s (%s,%s:%s)%n", methodId, classId, n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    for (int i = 0; i < n.el.size(); i++) {
      Type t1 = calledMethod.getParamAt(i) != null ? calledMethod.getParamAt(i).getType() : null;
      Type t2 =  n.el.elementAt(i).accept(this);

      if (!Task2Visitor.symbolTable.compareTypes(t1, t2)) {
        System.err.printf("Passed arguments not matched with the definition %s (%s,%s:%s)%n", calledMethod.getUniqueId(), n.getToken().next.next.beginLine, n.getToken().next.next.beginColumn, Task2Visitor.currMethod.getInternalId());
        break;
      }
    }

    return Task2Visitor.symbolTable.getMethodType(methodId, classId);
  }

  // int i;
  public Type visit(IntegerLiteral n) {
    return new IntegerType();
  }

  // double d;
  public Type visit(FloatingPointLiteral n) {
    return new DoubleType();
  }

  public Type visit(True n) {
    return new BooleanType();
  }

  public Type visit(False n) {
    return new BooleanType();
  }

  // String s;
  public Type visit(IdentifierExp n) {
    return Task2Visitor.symbolTable.getVarType(Task2Visitor.currMethod, Task2Visitor.currClass, n.s);
  }

  public Type visit(This n) {
    return Task2Visitor.currClass.getType();
  }

  // Exp e;
  public Type visit(NewArray n) {
    if (!(n.e.accept(this) instanceof IntegerType)) {
      System.err.printf("NewArray operand must be of type boolean (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    return new IntArrayType();
  }

  // Identifier i;
  public Type visit(NewObject n) {
    return new IdentifierType(n.i.s);
  }

  // Exp e;
  public Type visit(Not n) {
    if (!(n.e.accept(this) instanceof BooleanType)) {
      System.err.printf("Not operand must be of type boolean (%s,%s:%s)%n", n.getBeginLine(), n.getBeginColumn(), Task2Visitor.currMethod.getInternalId());
      return null;
    }

    return new BooleanType();
  }

}
