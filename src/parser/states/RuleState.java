package parser.states;

import parser.NodeType;
import parser.ParseAutomaton;
import parser.nodes.TreeNode;
import parser.rules.Rule;

import java.util.Map;
import java.util.Stack;

public class RuleState extends ParserStateImpl {
    private Map<TreeNodeMatcher, Rule> rules;

    @Override
    public boolean isAccepting() {
        return false;
    }

    @Override
    public Stack<TreeNode> match(Stack<TreeNode> treeNodes, TreeNode lookAhead, ParseAutomaton automaton) {
        NodeType lastNode = treeNodes.empty()? NodeType.$ : treeNodes.peek().getNodeType();
        for (Map.Entry<TreeNodeMatcher, Rule> entry : rules.entrySet()) {
            if(entry.getKey().match(lastNode, lookAhead.getNodeType())) {
                treeNodes = entry.getValue().apply(treeNodes);
                return this.match(treeNodes, lookAhead, automaton);
            }
        }
        return super.match(treeNodes, lookAhead, automaton);
    }

    public void setRules(Map<TreeNodeMatcher, Rule> rules) {
        this.rules = rules;
    }
}
