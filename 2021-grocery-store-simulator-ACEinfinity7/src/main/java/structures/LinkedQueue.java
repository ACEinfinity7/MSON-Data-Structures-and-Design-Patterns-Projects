package structures;

public class LinkedQueue<T> implements QueueInterface<T> {

    /**
     * LinearNode reference for the front of the queue (the item that will be returned on a dequeue)
     */
    private LinearNode<T> front;

    /**
     * LinearNode reference for the back of the queue (the item that was most recently enqueued)
     */
    private LinearNode<T> back;

    /**
     * counter to keep track of the number of nodes in the queue
     */
    private int size;

    /**
     * Constructor for LinkedQueue
     */
    public LinkedQueue() {
        front = back = null;
        size = 0;
    }

    @Override
    public T dequeue() {

        if (isEmpty())
            throw new IllegalStateException("Cannot dequeue from an empty queue");

        T elem = front.getElement();
        front = front.getNext();
        size--;

        if (isEmpty())
            back = null;

        return elem;
    }

    @Override
    public T peek() {
        return front.getElement();
    }

    @Override
    public QueueInterface<T> enqueue(T elem) {
        if (elem == null)
            throw new NullPointerException("Cannot enqueue null");

        LinearNode<T> node = new LinearNode<T>(elem);

        if (isEmpty())
            front = node;
        else
            back.setNext(node);

        back = node;
        size++;

        return (QueueInterface<T>) this;

    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        // for empty queue
        if (isEmpty())
            return "[]";

        // use stringbuilder because it's faster and more memory efficient
        StringBuilder result = new StringBuilder();
        result.append("[");
        // start at the front of the list
        LinearNode<T> copy = this.front;
        // add first element
        result.append(copy.getElement().toString());
        // move to the next node
        copy = copy.getNext();

        // add remaining elemets
        if (size > 1) {
            while (copy != null) {
                result.append(", ");
                result.append(copy.getElement().toString());

                // move to the next node
                copy = copy.getNext();
            }

        }

        return result.append("]").toString();
    }

}
