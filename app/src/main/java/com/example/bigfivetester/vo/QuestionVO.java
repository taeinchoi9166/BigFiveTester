package com.example.bigfivetester.vo;

public class QuestionVO {
    private String factorType;
    private boolean plusFlag;
    private String text;

    public QuestionVO() {
    }

    public QuestionVO(String factorType, boolean plusFlag, String text) {
        this.factorType = factorType;
        this.plusFlag = plusFlag;
        this.text = text;
    }

    public String getFactorType() {
        return factorType;
    }

    public void setFactorType(String factorType) {
        this.factorType = factorType;
    }

    public boolean isPlusFlag() {
        return plusFlag;
    }

    public void setPlusFlag(boolean plusFlag) {
        this.plusFlag = plusFlag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "QuestionVO{" +
                "factorType='" + factorType + '\'' +
                ", plusFlag=" + plusFlag +
                ", text='" + text + '\'' +
                '}';
    }
}
