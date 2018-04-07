import myparser.MiniJavaParser;
import myparser.ParseException;
import syntaxtree.Program;
import visitor.BuildSymbolTableVisitor;
import visitor.Task2Visitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Task2Main {
  public static void main(String[] args) throws FileNotFoundException {
    InputStream inputStream;

    if (args.length == 0) {
      inputStream = System.in;
    } else {
      File initialFile = new File(args[0]);
      inputStream = new FileInputStream(initialFile);
    }

    try {
      Program root = new MiniJavaParser(inputStream).Goal();

      // Build the symbol table
      BuildSymbolTableVisitor buildSymTab = new BuildSymbolTableVisitor();
      root.accept(buildSymTab);

      Task2Visitor task2Visitor = new Task2Visitor(buildSymTab.getSymTab());
      root.accept(task2Visitor);

    } catch (ParseException e) {
      System.out.println(e.toString());
    }
  }
}
