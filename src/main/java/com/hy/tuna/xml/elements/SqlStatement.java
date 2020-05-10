package com.hy.tuna.xml.elements;

public abstract class SqlStatement extends AbstractNode implements ExecutableStatement{

    private StatementType statementType;

    @Override
    public StatementType getStatementType() {
        return statementType;
    }
}
