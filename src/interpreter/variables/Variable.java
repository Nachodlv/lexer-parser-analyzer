package interpreter.variables;

import parser.nodes.TreeNode;

public interface Variable {
    Variable calculateWith(Variable variable, TreeNode treeNode);
    Variable calculateWithNumber(NumberVariable numberVariable, TreeNode treeNode);
    Variable calculateWithString(StringVariable stringVariable, TreeNode treeNode);
    String toString();
}
