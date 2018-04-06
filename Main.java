import myparser.MiniJavaParser;
import myparser.ParseException;
import syntaxtree.Program;
import visitor.BuildSymbolTableVisitor;
import visitor.TypeCheckVisitor;

public class Main {
    public static void main(String[] args) {
        try {
            Program root = new MiniJavaParser(System.in).Goal();

            // Build the symbol table
            BuildSymbolTableVisitor buildSymTab = new BuildSymbolTableVisitor();
            root.accept(buildSymTab);

            // Type check
            TypeCheckVisitor typeCheck =
                    new TypeCheckVisitor(buildSymTab.getSymTab());
            root.accept(typeCheck);
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
    }
}
