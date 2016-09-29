package ca.pfv.spmf.output;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    public Rule(Integer support, Double confidence, int[] antecedent, int[] consequent) {
        this.support = support;
        this.confidence = confidence;
        this.antecedent = antecedent;
        this.consequent = consequent;

    }

    private Double confidence;

    private Integer support;

    private int[] antecedent

    private int[] consequent

    public List<Integer> getAntecedent() {
        return antecedent;
    }

    public List<Integer> getConsequent() {
        return consequent;
    }

    public Integer getSupport() {
        return support;
    }

    public Double getConfidence() {
        return confidence;
    }
}
