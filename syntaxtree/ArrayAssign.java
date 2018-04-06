package syntaxtree;
import myparser.Token;
import visitor.Visitor;
import visitor.TypeVisitor;

// i [ e1 ] = e2;
public class ArrayAssign extends Statement {
  public Identifier i;
  public Exp e1,e2;
  public Token token;

  public ArrayAssign(Identifier ai, Exp ae1, Exp ae2, Token token) {
    i=ai; e1=ae1; e2=ae2; this.token=token;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

}

