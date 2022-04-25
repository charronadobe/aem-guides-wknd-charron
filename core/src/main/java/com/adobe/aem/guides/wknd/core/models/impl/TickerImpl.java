package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.Ticker;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Ticker.class, resourceType = TickerImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TickerImpl implements Ticker {
    protected static final String RESOURCE_TYPE = "wknd/components/ticker";

    private static final Logger LOGGER = LoggerFactory.getLogger(BylineImpl.class);

    @ValueMapValue
    String symbol;

    @ValueMapValue
    String index;

    @ValueMapValue
    String interval;

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getIndex() {
        return index;
    }

    @Override
    public String getInterval() {
        return interval;
    }

    @Override
    public boolean isEmpty() {
        if (StringUtils.isBlank(symbol)) {
            return true;
        } else if (StringUtils.isBlank(index)) {
            return true;
        } else if (StringUtils.isBlank(interval)) {
            return true;
        } else {
            return false;
        }
    }

}
