# swissknife

A collection of data structures and algorithms for programming contests written in Java.

## Algorithms

## Data Structures
* AA Tree: Variant of the Arne Andersson tree, where an additional integer is stored in each node, in order to be able to perform order-statistic queries. An integer may be inserted multiple times, which increases the occurrence counter in the node.

| Operation        | Complexity  | Description                                                                              |
| ---------------- | ----------- | ---------------------------------------------------------------------------------------- |
| `aaContains`     | `O(log(n))` | Checks whether the given integer exists in the tree                                      |
| `aaDelete`       | `O(log(n))` | Deletes the given integer from the tree                                                  |
| `aaDifference`   | `O(m+n)`    | Calculates the (multi)set difference between two AA trees (recursive implementation !)   |
| `aaInorder`      | `O(n)`      | Performs a Morris inorder tree traversal                                                 |
| `aaInsert`       | `O(log(n))` | Inserts an integer into the tree                                                         |
| `aaIntersection` | `O(m+n)`    | Calculates the (multi)set intersection between two AA trees (recursive implementation !) |
| `aaOccurrence`   | `O(log(n))` | Returns the occurrence count of the integer passed as parameter                          |
| `aaPostorder`    | `O(n)`      | Performs a Morris postorder tree traversal                                               |
| `aaPreorder`     | `O(n)`      | Performs a Morris preorder tree traversal                                                |
| `aaRank`         | `O(log(n))` | Calculates the rank of the integer, wrt to the values stored in the tree                 |
| `aaSelect`       | `O(log(n))` | Returns the node with the given rank                                                     |
| `aaUnion`        | `O(m+n)`    | Calculates the (multi)set union between two AA trees (recursive implementation !)        |

Tree internals: each node stores a tuple `(AANodeInt[] children, int level, int nNodes, int occurrence, int size, int value)`. Of those fields, only `children`, `level` and `value` are strictly necessary for an AATree. The other ones are used as bookkeeping values for order-statistics / tree traversal operations. While the the tree may store up to INTEGER.MAX_VALUE elements, you might want to use a B-Tree well before reaching this limit.

## Submission Utilities
