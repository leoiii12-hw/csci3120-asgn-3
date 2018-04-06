import myparser.MiniJavaParser;
import myparser.ParseException;
import syntaxtree.Program;
import visitor.NameScopeVisitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Task1Main {
  public static void main(String[] args) throws FileNotFoundException {
    if (args.length == 0) {
      System.err.println("Missing an argument");
      System.exit(1);
    }

    String identifier = args[0].equals("null") ? null : args[0];
    InputStream inputStream;

    if (args.length == 1) {
      inputStream = System.in;
    } else {
      File initialFile = new File(args[1]);
      inputStream = new FileInputStream(initialFile);
    }

    try {
      Program root = new MiniJavaParser(inputStream).Goal();


      NameScopeVisitor nameScopeVisitor = new NameScopeVisitor(identifier);
      root.accept(nameScopeVisitor);

    } catch (ParseException e) {
      System.out.println(e.toString());
    }
  }
}
