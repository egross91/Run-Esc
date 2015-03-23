package org.escaperun.game.serialization;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Saveable {

    public Element save(Document dom, Element parent);
    public Object load(Element node);
}