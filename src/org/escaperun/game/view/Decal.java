package org.escaperun.game.view;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public final class Decal implements Saveable {

    public final char ch;
    public final Color background;
    public final Color foreground;

    public static final Decal BLANK = new Decal('\u0000', Color.BLACK, Color.BLACK);
    public static final Decal EMPTY_ITEM_SLOT = new Decal('_', Color.BLACK, Color.WHITE);

    public Decal(char ch, Color background, Color foreground) {
        this.ch = ch;
        this.background = background;
        this.foreground = foreground;
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element decal = dom.createElement("Decal");
        parent.appendChild(decal);

        decal.setAttribute("Char", String.valueOf(ch + ""));
        decal.setAttribute("Background", String.valueOf(background.getRGB()));
        decal.setAttribute("Foreground", String.valueOf(foreground.getRGB()));

        return decal;
    }

    @Override
    public Decal load(Element node) {
        if (node == null) return null;
        Element decal;
        if (node.getElementsByTagName("Decal") != null && node.getElementsByTagName("Decal").getLength() > 0)
            decal = (Element) node.getElementsByTagName("Decal").item(0);
        else
            decal = node;
        if (decal == null) return null;

        char ch = decal.getAttribute("Char").charAt(0);
        int background = Integer.parseInt(decal.getAttribute("Background"));
        int foreground = Integer.parseInt(decal.getAttribute("Foreground"));

        return new Decal(ch, new Color(background), new Color(foreground));
    }
}
