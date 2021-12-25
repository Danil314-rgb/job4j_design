package tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> par = findBy(parent);
        Optional<Node<E>> chi = findBy(child);
        boolean result = par.isPresent() && chi.isEmpty();
        if (result) {
            par.get().children.add(new Node<>(child));
        }
        return result;
    }

    public boolean isBinary() {
        return findByPredicate(el -> el.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(el -> el.value.equals(value));
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        Optional<Node<E>> rsl = Optional.empty();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                return rsl;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}

