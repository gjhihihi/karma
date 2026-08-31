package com.gjhi.karma.library.caps;

public interface IKarmaData {
    int getKarma();
    void setKarma(int value);

    default void addKarma(int value) {
        setKarma(getKarma() + value);
    }

    default void removeKarma(int value) {
        setKarma(getKarma() - value);
    }
}
