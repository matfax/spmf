package ca.pfv.spmf.output;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {

    private List<Rule> rules = new ArrayList<>();

    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }
}
