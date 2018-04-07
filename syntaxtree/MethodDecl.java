package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class MethodDecl {
  public Type t;
  public Identifier i;
  public FormalList fl;
  public VarDeclList vl;
  public StatementList sl;
  public Exp e;
  public Token token;

  public MethodDecl(Type at, Identifier ai, FormalList afl, VarDeclList avl,
                    StatementList asl, Exp ae, Token token) {
    t = at;
    i = ai;
    fl = afl;
    vl = avl;
    sl = asl;
    e = ae;
    this.token = token;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
