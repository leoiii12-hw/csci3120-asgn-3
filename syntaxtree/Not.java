package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class Not extends Exp {
  public Exp e;

  public Not(Exp ae) {
    e = ae;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
