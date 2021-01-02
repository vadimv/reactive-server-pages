package rsp.dom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DefaultDomChangesPerformer implements DomChangesPerformer {
    public final Set<VirtualDomPath> elementsToRemove = new HashSet<>();
    public final List<DomChange> commands = new ArrayList<>();

    @Override
    public void removeAttr(VirtualDomPath path, XmlNs xmlNs, String name, boolean isProperty) {
        commands.add(new RemoveAttr(path, xmlNs, name, isProperty));
    }

    @Override
    public void removeStyle(VirtualDomPath path, String name) {
        commands.add(new RemoveStyle(path, name));
    }

    @Override
    public void remove(VirtualDomPath parentPath, VirtualDomPath path) {
        commands.add(new Remove(parentPath, path));
        elementsToRemove.add(path);
    }

    @Override
    public void setAttr(VirtualDomPath path, XmlNs xmlNs, String name, String value, boolean isProperty) {
        commands.add(new SetAttr(path, xmlNs, name, value, isProperty));
    }

    @Override
    public void setStyle(VirtualDomPath path, String name, String value) {
        commands.add(new SetStyle(path, name, value));
    }

    @Override
    public void createText(VirtualDomPath parentPath, VirtualDomPath path, String text) {
        commands.add(new CreateText(parentPath, path, text));
    }

    @Override
    public void create(VirtualDomPath path, XmlNs xmlNs, String tag) {
        commands.add(new Create(path, xmlNs, tag));
    }

    public interface DomChange {}

    public static final class RemoveAttr implements DomChange {
        public final VirtualDomPath path;
        public final XmlNs xmlNs;
        public final String name;
        public final boolean isProperty;

        public RemoveAttr(VirtualDomPath path, XmlNs xmlNs, String name, boolean isProperty) {
            this.path = path;
            this.xmlNs = xmlNs;
            this.name = name;
            this.isProperty = isProperty;
        }
    }

    public static final class RemoveStyle implements DomChange {
        public final VirtualDomPath path;
        public final String name;
        public RemoveStyle(VirtualDomPath path, String name) {
            this.path = path;
            this.name = name;
        }
    }

    public static class Remove implements DomChange {
        public final VirtualDomPath parentPath;
        public final VirtualDomPath path;
        public Remove(VirtualDomPath parentPath, VirtualDomPath path) {
            this.parentPath = parentPath;
            this.path = path;
        }
    }

    public static class SetAttr implements DomChange {
        public final VirtualDomPath path;
        public final XmlNs xmlNs;
        public final String name;
        public final String value;
        public final boolean isProperty;

        public SetAttr(VirtualDomPath path, XmlNs xmlNs, String name, String value, boolean isProperty) {
            this.path = path;
            this.xmlNs = xmlNs;
            this.name = name;
            this.value = value;
            this.isProperty = isProperty;
        }
    }

    public static class SetStyle implements DomChange {
        public final VirtualDomPath path;
        public final String name;
        public final String value;
        public SetStyle(VirtualDomPath path, String name, String value) {
            this.path = path;
            this.name = name;
            this.value = value;
        }
    }

    public static class CreateText implements DomChange {
        public final VirtualDomPath parentPath;
        public final VirtualDomPath path;
        public final String text;
        public CreateText(VirtualDomPath parentPath, VirtualDomPath path, String text) {
            this.parentPath = parentPath;
            this.path = path;
            this.text = text;
        }
    }

    public static class Create implements DomChange {
        public final VirtualDomPath path;
        public final XmlNs xmlNs;
        public final String tag;
        public Create(VirtualDomPath path, XmlNs xmlNs, String tag) {
            this.path = path;
            this.xmlNs = xmlNs;
            this.tag = tag;
        }
    }
}