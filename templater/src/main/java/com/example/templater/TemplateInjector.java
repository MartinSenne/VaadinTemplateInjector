package com.example.templater;

import com.example.templater.client.TemplateInjectorState;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Server side component.
 */
public class TemplateInjector extends AbstractLayout {

    private static final long serialVersionUID = -7063177153212665187L;

    private final HashMap<String, Component> idToComponent = new HashMap<String, Component>();

    public TemplateInjector() {
        super();
    }

    @Override
    public TemplateInjectorState getState() {
        TemplateInjectorState state = (TemplateInjectorState) super.getState();
        if (state.html == null) state.html = "";
        if (state.connectorsToIds == null) state.connectorsToIds = new HashMap<>();
        return state;
    }

    public void setComponent(Component c, String location) {
        final Component old = idToComponent.get(location);
        if (old != null) {
            removeComponent(old);
        }
        idToComponent.put(location, c);
        getState().connectorsToIds.put(c, location);

        super.addComponent(c);
    }

    public void removeComponent(Component c) {
        if (c == null) {
            return;
        }
        idToComponent.values().remove(c);
        getState().connectorsToIds.remove(c);
        super.removeComponent(c);
    }

    public Component getComponentWithId(String id) {
        return idToComponent.get(id);
    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {
        // TODO: Implement
        System.out.println("Replace Component is called, but is not implemented.");
    }

    public void setHtml(String html) {
        getState().html = html;
    }

    public String getHtml() {
        return getState().html;
    }


    @Override
    public int getComponentCount() {
        return idToComponent.size();
    }

    @Override
    public Iterator<Component> iterator() {
        return idToComponent.values().iterator();
    }
}
