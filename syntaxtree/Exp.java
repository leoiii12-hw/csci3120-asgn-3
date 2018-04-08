package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public abstract class Exp extends HasToken {
  public abstract void accept(Visitor v);

  public abstract Type accept(TypeVisitor v);
}
