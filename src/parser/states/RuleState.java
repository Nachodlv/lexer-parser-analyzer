package parser.states;

import parser.ParseAutomaton;
import parser.nodes.TreeNode;
import parser.rules.Rule;

import java.util.Map;
import java.util.Stack;

public class RuleState extends ParserStateImpl {
    private Map<TreeNode, Rule> rules;

    public RuleState(Map<TreeNode, Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean isAccepting() {
        return false;
    }

    @Override
    public Stack<TreeNode> match(Stack<TreeNode> treeNodes, ParseAutomaton automaton) {
        TreeNode node = treeNodes.peek();
        for (Map.Entry<TreeNode, Rule> entry : rules.entrySet()) {
            if(entry.getKey().getNodeType() == node.getNodeType()) {
                treeNodes = entry.getValue().apply(treeNodes);
                match(treeNodes, automaton);
            }
        }
        return super.match(treeNodes, automaton);
    }
}
