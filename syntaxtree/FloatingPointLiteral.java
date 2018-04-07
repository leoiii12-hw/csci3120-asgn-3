package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class FloatingPointLiteral extends Exp {
  public double d;

  public FloatingPointLiteral(double ad) {
    d = ad;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
