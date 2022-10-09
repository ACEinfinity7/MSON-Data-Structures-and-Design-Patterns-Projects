# interview-questions-stacks-and-queues-elementary-sorts

Update this file with your answers. If you write outside files drop them in the repository

1. **Queue with two stacks.**

Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.

Hint: If you push elements onto a stack and then pop them all, they appear in reverse order. If you repeat this process, they're now back in order.


2. **Stack with max.**

Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.

Hint: Use two stacks, one to store all of the items and a second stack to store the maximums.

3. **Java generics.** 

Explain why Java prohibits generic array creation.

Hint: to start, you need to understand that Java arrays are covariant but Java generics are not: that is, `String[]` is a subtype of `Object[]`, but `Stack<String>` is not a subtype of `Stack<Object>`.

ANSWER: Java creates an array of the objects themselves, where as a structure
like a Stack or List is an object itself around the internal objects, this
wrapping structure allows for the internal objects to be generic, because
the outer structure still exists. But in arrays, the outer structure is not an
object, so the created classes must be not generic, and at the last of Object.


### Bonus

**4. Dutch national flag.**

Given an array of nn buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:

- `swap(i,j)`:  swap the pebble in bucket i with the pebble in bucket j.

- `color(i)`: determine the color of the pebble in bucket i.

The performance requirements are as follows:

- At most n calls to color().

- At most n calls to swap().

Constant extra space.

Hint: 3-way partitioning.
