package syntaxtree;
import myparser.Token;
import visitor.Visitor;
import visitor.TypeVisitor;

/*
   class i extends j {
     (Variable declarations)*
     (Method declaration)*
   }
*/
public class ClassDeclExtends extends ClassDecl {
  public Identifier i;
  public Identifier j;
  public VarDeclList vl;      // Sequence of variable declarations
  public MethodDeclList ml;   // Sequence of method declarations
  public Token token;
 
  public ClassDeclExtends(Identifier ai, Identifier aj, 
                  VarDeclList avl, MethodDeclList aml, Token token) {
    i=ai; j=aj; vl=avl; ml=aml; this.token = token;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
