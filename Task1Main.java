import myparser.MiniJavaParser;
import myparser.ParseException;
import syntaxtree.Program;
import visitor.BuildSymbolTableVisitor;
import visitor.Task1Visitor;

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

      // Build the symbol table
      BuildSymbolTableVisitor buildSymTab = new BuildSymbolTableVisitor();
      root.accept(buildSymTab);

      Task1Visitor task1Visitor = new Task1Visitor(buildSymTab.getSymTab(), identifier);
      root.accept(task1Visitor);

    } catch (ParseException e) {
      System.out.println(e.toString());
    }
  }
}
