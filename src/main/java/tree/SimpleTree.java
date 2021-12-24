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
        boolean rsl = false;
        Optional<Node<E>> par = findBy(parent);
        Optional<Node<E>> chi = findBy(child);
        if (par.isPresent() && chi.isEmpty()) {
            Node<E> children = new Node<>(child);
            par.get().children.add(children);
            rsl = true;
        }
        return rsl;
    }

    public boolean isBinary() {
        return findByPredicate(el -> el.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return Optional.ofNullable(findByPredicate(el -> el.value.equals(value)).orElse(null));
    }

    private Optional<Node> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        Optional<Node> rsl = Optional.empty();
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
