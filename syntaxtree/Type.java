package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

// Data type
public abstract class Type {
  public abstract void accept(Visitor v);

  public abstract Type accept(TypeVisitor v);

  public Class c;

  @Override()
  public String toString() {
    if (this instanceof IntArrayType) {
      return "int[]";
    }

    if (this instanceof BooleanType) {
      return "boolean";
    }

    if (this instanceof IntegerType) {
      return "int";
    }

    if (this instanceof DoubleType) {
      return "double";
    }

    if (this instanceof IdentifierType) {
      return ((IdentifierType) this).s;
    }

    return null;
  }
}
