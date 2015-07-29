package br.talkitbr.example.model.bean;

/**
 * Bean que representa os dados do app.
 */
public class Bean {
    private String mText;

    public Bean(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }
}
