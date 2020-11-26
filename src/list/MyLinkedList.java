package list;

//пользовательский LinkedList
public class MyLinkedList<E> {
    private Node<E> first; // Ссылка на первый элемент списка
    private Node<E> last; // Ссылка на последний элемент списка
    private int size = 0;

    public MyLinkedList() // Конструктор
    {
        first = null; // Список пока не содержит элементов
        last = null;
    }

    public boolean isEmpty() {// true, если список пуст
        return first == null;
    }

    public int size() {
        return size;
    }

    public Node<E> getFirst() {
        return first;
    }

    public Node<E> getLast() {
        return last;
    }

    public E get(E item) {
        Node<E> current = first;
        while (current != item) {
            if (current.next == null) return null;
            else current = current.next;
        }
        return current.item;
    }

    public E get(int pos) {
        Node<E> current = first;
        for (int i = 0; i < pos; i++) {
            if (current.next == null) return null;
            else current = current.next;
        }
        return current.item;
    }

    public void setFirst(Node<E> first) {
        this.first = first;
    }

    public void addFirst(E item) {// Вставка элемента в начало списка
        Node<E> newLink = new Node<E>(item); // Создание нового элемента
        if (isEmpty()) // Если список не содержит элементов,
            last = newLink; // newLink <-- last
        else
            first.prev = newLink; // newLink <-- старое значение firstДвусвязные списки 223
        newLink.next = first; // newLink --> старое значение first
        first = newLink; // first --> newLink
        size++;
    }

    public void addLast(E item) // элемент в конец списка
    {
        Node<E> newLink = new Node<E>(item); // Создание нового элемента
        if (isEmpty()) // Если список не содержит элементов,
            first = newLink; // first --> newLink
        else {
            last.next = newLink; // старое значение last --> newLink
            newLink.prev = last; // старое значение last <-- newLink
        }
        last = newLink; // newLink <-- last
        size++;
    }

    public void addIndex(E item, int pos) {
        Node<E> current = first;

        pos--;
        for (int i = 0; i < pos; i++) {
            current = current.next;
            if (current.next == null) return;
        }
        Node<E> newNode = new Node<E>(item); // Создание нового элемента
        if (current == last) // Для последнего элемента списка
        {
            newNode.next = null; // newLink --> null
            last = newNode; // newLink <-- last
        } else // Не последний элемент
        {
            newNode.next = current.next; // newLink --> старое значение next
            current.next.prev = newNode;
        }
        newNode.prev = current; // старое значение current <-- newLink
        current.next = newNode; // старое значение current --> newLink
        size++;
    }

    public Node<E> deleteFirst() // Удаление первого элемента
    { // (предполагается, что список не пуст)
        Node<E> temp = first;
        if (first.next == null) // Если только один элемент
            last = null; // null <-- last
        else
            first.next.prev = null; // null <-- старое значение next
        first = first.next; // first --> старое значение next
        size--;
        return temp;
    }

    public Node<E> deleteLast() // Удаление последнего элемента
    { // (предполагается, что список не пуст)
        Node<E> temp = last;
        if (first.next == null) // Если только один элемент
            first = null; // first --> null
        else
            last.prev.next = null; // старое значение previous --> null
        last = last.prev; // старое значение previous <-- last
        size--;
        return temp;
    }

    public Node<E> deleteKey(E key) // Удаление элемента с заданным ключом
    { // (предполагается, что список не пуст)
        Node<E> current = first; // От начала списка
        while (current.item != key) // Пока не будет найдено совпадение
        {
            current = current.next; // Переход к следующему элементу
            if (current == null)
                return null; // Ключ не найден
        }
        if (current == first) // Ключ найден; это первый элемент?
            first = current.next; // first --> старое значение next
        else // Не первый элемент
// старое значение previous --> старое значение next
            current.prev.next = current.next;
        if (current == last) // Последний элемент?
            last = current.prev; // старое значение previous <-- last
        else // Не последний элемент
// Старое значение previous <-- старое значение next
            current.next.prev = current.prev;
        size--;
        return current; // Возвращение удаленного элемента
    }

    public Node<E> deleteIndex (int pos) {
        Node<E> current = first;
        for (int i = 0; i < pos; i++) {
            if (current.next == null) return null;
            else current = current.next;
        }
        if (current == first) // Ключ найден; это первый элемент?
            first = current.next; // first --> старое значение next
        else // Не первый элемент
// старое значение previous --> старое значение next
            current.prev.next = current.next;
        if (current == last) // Последний элемент?
            last = current.prev; // старое значение previous <-- last
        else // Не последний элемент
// Старое значение previous <-- старое значение next
            current.next.prev = current.prev;
        size--;
        return current; // Возвращение удаленного элемента
    }
}


