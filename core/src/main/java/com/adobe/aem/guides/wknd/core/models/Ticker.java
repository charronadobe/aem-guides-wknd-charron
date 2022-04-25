package com.adobe.aem.guides.wknd.core.models;

public interface Ticker {
    String getSymbol();

    String getIndex();

    String getInterval();

    boolean isEmpty();
}
