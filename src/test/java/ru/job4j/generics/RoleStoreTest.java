package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddOneCheckOneThentOne() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Danil"));
        Role res = store.findById("1");
        assertThat(res.getName(), is("Danil"));
    }

    @Test
    public void whenAddOneCheckTwoThenNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Danil"));
        Role res = store.findById("2");
        assertNull(res);
    }

    @Test
    public void whenReplaceOneNameThenNewName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Danil"));
        store.replace("1", new Role("1", "Daniil"));
        Role res = store.findById("1");
        assertThat(res.getName(), is("Daniil"));
    }

    @Test
    public void whenReplaceOneNameThenOldName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Danil"));
        store.replace("10", new Role("10", "Daniil"));
        Role res = store.findById("1");
        assertThat(res.getName(), is("Danil"));
    }

    @Test
    public void whenDeleteOneThenNameIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Danil"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenDeleteTwoThenNameIsDanil() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Danil"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getName(), is("Danil"));
    }
}