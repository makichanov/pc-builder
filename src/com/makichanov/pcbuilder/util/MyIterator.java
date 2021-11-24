package com.makichanov.pcbuilder.util;

//пользовательский итератор
public class MyIterator<E> {
    private Node<E> current; //ссылка на текущий элемент, на который указывает итератор
    private Node<E> previous; //ссылка на предыдущий элемент относительно того, на который указывает итератор
    private final MyLinkedList<E> ourList; //связанный список

    //конструктор
    public MyIterator(MyLinkedList<E> list) {
        ourList = list;
        reset();
    }

    //вернутьитератор в начало
    public void reset() {
        current = ourList.getFirst();
        previous = null;
    }

    //true, если следующий объект - null
    public boolean atEnd() {
        return (current.next == null);
    }

    //к следующему звену списка
    public void nextNode() {
        previous = current;
        current = current.next;
    }

    //получить текущий элемент
    public Node<E> getCurrent() {
        return current;
    }

    //вставить элемент данных E после текущего
    public void insertAfter(E item) {
        Node<E> newLink = new Node<>(item);
        if (ourList.isEmpty()) {
            ourList.setFirst(newLink);
            current = newLink;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            nextNode();
        }
    }

    //вставить элемент данных перед текущим
    public void insertBefore(E item) {
        Node<E> newLink = new Node<>(item);
        if (previous == null) {
            newLink.next = ourList.getFirst();
            ourList.setFirst(newLink);
            reset();
        } else {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    //удалить текущий элемент
    public E deleteCurrent() {
        E value = current.item;
        if (previous == null) {
            ourList.setFirst(current.next);
            reset();
        } else {
            previous.next = current.next;
            if (atEnd())
                reset();
            else
                current = current.next;
        }
        return value;
    }

}
