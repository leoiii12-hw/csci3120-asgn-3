package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class Formal {
  public Type t;
  public Identifier i;
  public Token token;

  public Formal(Type at, Identifier ai, Token token) {
    t = at;
    i = ai;
    this.token = token;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
