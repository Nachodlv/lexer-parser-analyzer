package interpreter;

import interpreter.variables.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    private Map<String, Variable> variables;
    private List<String> output;
    private List<ContextListener> listeners;

    public Context() {
        output = new ArrayList<>();
        listeners = new ArrayList<>();
        variables = new HashMap<>();
    }

    public void addListener(ContextListener contextListener) {
        listeners.add(contextListener);
    }

    public void declareVariable(String id, Variable variable) {
        if(variables.containsKey(id)) throw new RuntimeException("Variable " + id + " already declared");
        variables.put(id, variable);
    }

    public void reAssignVariable(String id, Variable variable) {
        variables.put(id, variable);
    }

    public Variable getVariable(String id) {
        if(!variables.containsKey(id)) throw new RuntimeException("Variable " + id + " not declared");
        return variables.get(id);
    }

    public void addOutput(String newOutput) {
        output.add(newOutput);
        listeners.forEach(contextListener -> contextListener.receiveNewOutput(newOutput));
    }

}
