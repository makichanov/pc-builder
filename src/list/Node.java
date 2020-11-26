package list;

//Звено списка
public class Node<E> {
    public E item; //данные
    public Node<E> next; //следующий элемент
    public Node<E> prev; //предыдущий элемент
    public Node(E item) {
        this.item = item;
    }
}
