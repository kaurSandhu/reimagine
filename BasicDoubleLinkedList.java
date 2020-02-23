
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;
/**
 * A generic doubly-linked list class with an internal iterator 
 * 
 * 
 */
public class BasicDoubleLinkedList <T> {

	protected Node head;
	protected Node tail;
	protected int size;


	public BasicDoubleLinkedList()
	{
		size = 0;
	}
	/**
	 * This method just returns the value of the instance variable you use to keep track of size. 
	 * @return the size of the linked list
	 */
	public int getSize()
	{
		return size;
	}
	/**
	 * Adds an element to the end of the list. 
	 * @param data- the data for the Node within the linked list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data)
	{
		Node tmp = new Node(data, null, tail);
		if(tail != null) {tail.next = tmp;}
		tail = tmp;
		if(head == null) { head = tmp;}
		size++;
		return this;
	}
	/**
	 * Adds element to the front of the list. 
	 * @param data- the data for the Node within the linked list
	 * @return reference to the current object
	 */

	public BasicDoubleLinkedList<T> addToFront(T data)
	{
		Node tmp = new Node(data, head, null);
		if(head != null ) {head.prev = tmp;}
		head = tmp;
		if(tail == null) { tail = tmp;}
		size++;
		return this;
	}
	/**
	 *  Returns but does not remove the first element from the list. 
	 *  If there are no elements the method returns null
	 * @return the data element or null
	 */
	public T getFirst()
	{
		if (head==null)
			return null;
		else
			return head.getData();
	}
	/**
	 * Returns but does not remove the last element from the list.
	 *  If there are no elements the method returns null.
	 * @return the data element or null
	 */
	public T getLast()
	{
		if(tail==null)
			return null;
		else
			return tail.getData();

	}
	/**
	 * This method implemented by using an inner class that implements ListIterator
	 * @return DoublyLinkedIterator
	 * @throws UnsupportedOperationException ListIterator's remove(), 
	 * add(), nextIndex() and previousIndex() and set() methods, throw UnsupportedOperationException
	 * @throws NoSuchElementException if there are no more elements
	 *  (at the end of the list and calling next() or at the beginning of the list and calling previous()).
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException
	{
		return new DoublyLinkedListIterator() ;

	}
	/**
	 * Removes and returns the first element from the list. If there are no elements the method returns null. 
	 * @return the first element from the List
	 */
	public T retrieveFirstElement()
	{
		T front = getFirst(); 
		assert head != null;
		head= head.getNextNode();
		if (head == null)
		{
			tail = null;
		}
		else
			head.setPrevNode(null);
		return front;
	} // end removeFront
	/**
	 * Removes and returns the last element from the list. If there are no elements the method returns null
	 * @return the last element from the list
	 */
	public T retrieveLastElement()
	{
		T back = getLast(); 
		assert tail != null;
		tail = tail.getPrevNode();
		if (tail == null)
			head = null;
		else
			tail.setNextNode(null);
		return back;
	}

	/**
	 * Returns an arraylist of the items in the list from head of list to tail of list
	 * @return the arrayList of all the elements.
	 */
	public ArrayList<T> toArrayList()

	{
		ArrayList<T> result = new ArrayList<T>(size);
		Node prev = null, curr = head;
		while(curr!=null)
		{
			result.add(curr.getData());
			prev = curr;
			curr = curr.next;

		}
		return result ;
	}
	/**
	 * Removes the first instance of the targetData from the list
	 * @param targetData the data element to be removed
	 * @param comparator the comparator to determine equality of data elements
	 * @return data element or null
	 */
	public BasicDoubleLinkedList<T> remove (T targetData, Comparator<T> comparator){
		Node prev = null, curr = head;
		while (curr != null) {
			if (comparator.compare(curr.data, targetData) == 0) {
				//Check to see if we need to remove the very first element
				if (curr == head) {
					head = head.next;
					curr = head;
				} 
				//Check to see if we need to remove the last element, in which case update the tail
				else if(curr == tail){
					curr = null;
					tail = prev;
					prev.next = null;
				}
				//If anywhere else in the list
				else {
					prev.next = curr.next;
					curr = curr.next;
				}
				size--;

			} else {
				prev = curr;
				curr = curr.next;
			}
		}
		return this;



	}
	protected class Node
	{
		protected T    data; // Entry in list
		protected Node next; // Link to next node
		protected Node prev;
		/**
		 *Constructor that initialize data to dataPortion, next and prev to null
		 * @param dataPortion the dataportion of the node
		 */
		protected Node(T dataPortion)
		{
			data = dataPortion;
			next = null;
			prev=null;
		} // end constructor
		/**
		 * 
		 * @param dataPortion the data 
		 * @param nextNode the address to next node
		 * @param prevNode the address of the previous node
		 */
		protected Node(T dataPortion, Node nextNode, Node prevNode)
		{
			data = dataPortion;
			next = nextNode;
			prev=prevNode;
		} // end constructor
		/**
		 * Get the data.
		 * @return the data portion.
		 */
		protected T getData()
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
		/**
		 * get the previous node
		 * @return the reference to the previous node
		 */
		protected Node getPrevNode()
		{
			return prev;
		}
		/**
		 *set the previous node 
		 * @param prevNode pass the agrument previous to the linked list
		 */
		protected void setPrevNode(Node prevNode)
		{
			prev=prevNode;
		}

	}

	protected class DoublyLinkedListIterator implements ListIterator<T>
	{
		private Node nextNode;
		//private Node lastNode;
		/**
		 * Constructor intialize the nextNode to the head;
		 */
		protected DoublyLinkedListIterator()
		{
			nextNode =head ;
			//	lastNode=tail;;
		}
		/**
		 * Method check if the next node exist or not
		 */
		@Override
		public boolean hasNext()   
		{
			return nextNode != null;

		}
		/**
		 * Iterator go to the next node.
		 */
		@Override
		public T next() {
			T result;
			if (hasNext())
			{
				result = nextNode.getData();
				nextNode = nextNode.getNextNode(); // Advance iterator
			}

			else
				throw new NoSuchElementException("Illegal call to next(); " +
						"ListIterator is after end of list.");
			return result; // Return next entry in iteration
		}
		/**
		 * Method check if there is previous node exist or not
		 */
		@Override
		public boolean hasPrevious() 
		{
			if(nextNode==null)
				return true;
			else if (nextNode!=null)

				return nextNode.prev!=null;
			else
				return false;
		}
		/**
		 * Iterator points to the previous node
		 */
		@Override
		public T previous() {

			T result;
			if (hasPrevious())

			{
				if (nextNode!=null) {
					result=nextNode.prev.getData();
					nextNode=nextNode.getPrevNode();
				}
				else 
				{
					result=tail.getData();
					nextNode=tail;
				}
			}
			else
			{	throw new NoSuchElementException("Illegal call to previous();"
					+"ListIterator is after end of list.");
			}

			return result;
		}
		/**
		 * throws UnsupportedOperationException
		 */
		@Override
		public void add(T e) {
			throw new  UnsupportedOperationException("Can not add the data by using this method");


		}
		/**
		 *  throws UnsupportedOperationException
		 */
		@Override
		public int nextIndex() {
			throw new  UnsupportedOperationException("Can not return the next index");
		}
		/**
		 *  throws UnsupportedOperationException
		 */
		@Override
		public int previousIndex() {
			throw new  UnsupportedOperationException("Can not return the previous index");

		}
		/**
		 *  throws UnsupportedOperationException
		 */
		@Override
		public void remove() {

			throw new  UnsupportedOperationException("Can not remove by this method");
		}
		/**
		 *  throws UnsupportedOperationException
		 */
		@Override
		public void set(T e) {

			throw new  UnsupportedOperationException("Can not set the data by using this method");
		}
	}

}




































