package parser.states;

import parser.ParseAutomaton;
import parser.nodes.TreeNode;
import parser.rules.Rule;

import java.util.Map;
import java.util.Stack;

public class RuleState extends ParserStateImpl {
    private Map<TreeNodeMatcher, Rule> rules;

    public RuleState(Map<TreeNodeMatcher, Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean isAccepting() {
        return false;
    }

    @Override
    public Stack<TreeNode> match(Stack<TreeNode> treeNodes, TreeNode lookAhead, ParseAutomaton automaton) {
        TreeNode lastNode = treeNodes.peek();
        for (Map.Entry<TreeNodeMatcher, Rule> entry : rules.entrySet()) {
            if(entry.getKey().match(lastNode, lookAhead)) {
                treeNodes = entry.getValue().apply(treeNodes);
                match(treeNodes, lookAhead, automaton);
            }
        }
        return super.match(treeNodes, lookAhead, automaton);
    }
}
