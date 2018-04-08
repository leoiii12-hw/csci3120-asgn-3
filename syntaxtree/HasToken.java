package syntaxtree;

import myparser.Token;

public abstract class HasToken {

  private Token token;

  public void setToken(Token token) {
    this.token = token;
  }

  public Token getToken() {
    return this.token;
  }

  public int getBeginLine() {
    return this.token != null ? this.token.beginLine : 0;
  }

  public int getBeginColumn() {
    return this.token != null ? this.token.beginColumn : 0;
  }

}
