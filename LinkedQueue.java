
public class LinkedQueue<T> implements QueueInterface<T> {
	private Node firstNode; // References node at front of queue
	private Node lastNode;  // References node at back of queue

	public LinkedQueue()
	{
		firstNode = null;
		lastNode = null;
	} 

	/** Adds a new entry to the back of this queue.
	      @param newEntry  An object to be added. */

	public void enqueue(T newEntry) {

		Node newNode = new Node(newEntry, null);

		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);

		lastNode = newNode;  

	}

	/** Removes and returns the entry at the front of this queue.
	      @return  The object at the front of the queue. 
	*/
	public T dequeue() {
		T front = getFront();  
		firstNode.setData(null);
		firstNode = firstNode.getNextNode();

		if (firstNode == null)
			lastNode = null;

		return front;


	}
	/**  Retrieves the entry at the front of this queue.
	      @return  The object at the front of the queue.*/
	public T getFront() {

		if (isEmpty())
			try {
				throw new EmptyQueueException();
			} catch (EmptyQueueException e) {
				System.out.println(e.getMessage());
			}
	
			return firstNode.getData();



	}

	/** Detects whether this queue is empty.
	      @return  True if the queue is empty, or false otherwise. */
	public boolean isEmpty() {

		return (firstNode == null) && (lastNode == null);


	}

	/** Removes all entries from this queue. */
	public void clear() {
		firstNode = null;
		lastNode = null;


	}
	private class Node{

		private T    data; // Entry in bag
		private Node next; // Link to next node

		public Node(T dataPortion)
		{
			this(dataPortion, null);
		} // end constructor

		public Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;

		}
		public T getData()
		{
			return data;
		} // end getData
		/**
		 * set the data to newData
		 * @param newData the newData
		 */

		protected void setData(T newData)
		{
			data = newData;
		} // end setData
		/**
		 * get the next node in the linked list
		 * @return the next node
		 */
		protected Node getNextNode()
		{
			return next;
		} // end getNextNode
		/**
		 * set the data in the nextNode
		 * @param nextNode the next node in the double linked list
		 */
		protected void setNextNode(Node nextNode)
		{
			next = nextNode;
		} // end setNextNode
	}

}