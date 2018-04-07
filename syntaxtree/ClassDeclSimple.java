package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class ClassDeclSimple extends ClassDecl {
  public Identifier i;
  public VarDeclList vl;
  public MethodDeclList ml;
  public Token token;

  public ClassDeclSimple(Identifier ai, VarDeclList avl, MethodDeclList aml, Token token) {
    i = ai;
    vl = avl;
    ml = aml;
    this.token = token;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
