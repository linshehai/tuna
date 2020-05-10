package com.hy.tuna.handler;

public class TextTokenizer {

    private String text;

    private String openToken;

    private String closeToken;

    private TokenHandler tokenHandler;

    public TextTokenizer(String openToken,String closeToken){
       this(openToken,closeToken,null);
    }
    public TextTokenizer(String openToken,String closeToken,TokenHandler tokenHandler){
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.tokenHandler = tokenHandler;
    }

    public String parse(String text){
        StringBuilder sqlBuilder = new StringBuilder();
        int offset = 0;
        int start = text.indexOf(openToken);
        int end;
        char[] chs = text.toCharArray();
        StringBuilder expression;
        while (start!=-1){
            sqlBuilder.append(chs,offset,start-offset);
            offset = start+1;
            end = text.indexOf(closeToken,offset);
            if(end!=-1){
                expression = new StringBuilder();
                int propertyLen = end-offset-1;
                expression.append(chs,offset+1,propertyLen);
                if(tokenHandler!=null){
                    sqlBuilder.append(tokenHandler.handleToken(expression.toString()));
                }
                offset = end+closeToken.length();

            }
            start = text.indexOf(openToken,offset);
        }
        return sqlBuilder.toString();
    }
}
