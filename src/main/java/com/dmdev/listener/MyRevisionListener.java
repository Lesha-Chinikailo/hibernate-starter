package com.dmdev.listener;

import com.dmdev.entity.Revision;
import org.hibernate.envers.RevisionListener;

public class MyRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        ((Revision) o).setUsername("Lesha");
    }
}
